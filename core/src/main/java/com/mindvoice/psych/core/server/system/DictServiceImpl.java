package com.mindvoice.psych.core.server.system;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.mindvoice.psych.common.exception.BusinessException;
import com.mindvoice.psych.common.model.Option;
import com.mindvoice.psych.common.util.BeanUtils;
import com.mindvoice.psych.system.entity.Dict;
import com.mindvoice.psych.system.entity.DictItem;
import com.mindvoice.psych.system.mapper.DictItemMapper;
import com.mindvoice.psych.system.mapper.DictMapper;
import com.mindvoice.psych.system.model.form.DictForm;
import com.mindvoice.psych.system.model.query.DictPageQuery;
import com.mindvoice.psych.system.model.vo.DictPageVO;
import com.mindvoice.psych.system.service.DictItemService;
import com.mindvoice.psych.system.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典业务实现类
 *
 * @author liu
 * @since 2025/10/12
 */
@Service
@RequiredArgsConstructor
public class DictServiceImpl implements DictService {

    private final DictItemMapper dictItemMapper;
    private final DictMapper dictMapper;

    /**
     * 字典分页列表
     *
     * @param queryParams 分页查询对象
     */
    public Page<DictPageVO> getDictPage(DictPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();

        // 查询数据
        return dictMapper.getDictPage(new Page<>(pageNum, pageSize), queryParams);
    }

    /**
     * 获取字典列表
     *
     * @return 字典列表
     */
    @Override
    public List<Option<String>> getDictList() {
        return dictMapper.selectList(new LambdaQueryWrapper<Dict>().eq(Dict::getStatus, 1))
                .stream()
                .map(item -> new Option<>(item.getDictCode(), item.getName()))
                .toList();
    }


    /**
     * 新增字典
     *
     * @param dictForm 字典表单数据
     */
    @Override
    public boolean saveDict(DictForm dictForm) {
        // 保存字典
        Dict entity = BeanUtils.toBean(dictForm, Dict.class);

        // 校验 code 是否唯一
        String dictCode = entity.getDictCode();

        long count = dictMapper.selectCount(new LambdaQueryWrapper<Dict>()
                .eq(Dict::getDictCode, dictCode)
        );

        Assert.isTrue(count == 0, "字典编码已存在");

        return SqlHelper.retBool(dictMapper.insert(entity));
    }


    /**
     * 获取字典表单详情
     *
     * @param id 字典ID
     */
    @Override
    public DictForm getDictForm(Long id) {
        // 获取字典
        Dict entity = dictMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException("字典不存在");
        }
        return BeanUtils.toBean(entity, DictForm.class);
    }

    /**
     * 修改字典
     *
     * @param id       字典ID
     * @param dictForm 字典表单
     */
    @Override
    @Transactional
    public boolean updateDict(Long id, DictForm dictForm) {
        // 获取字典
        Dict entity = dictMapper.selectById(id);
        if (entity == null) {
            throw new BusinessException("字典不存在");
        }
        // 校验 code 是否唯一
        String dictCode = dictForm.getDictCode();
        if (!entity.getDictCode().equals(dictCode)) {
            long count = dictMapper.selectCount(new LambdaQueryWrapper<Dict>()
                    .eq(Dict::getDictCode, dictCode)
            );
            Assert.isTrue(count == 0, "字典编码已存在");
        }
        // 更新字典
        Dict dict = BeanUtils.toBean(entity, Dict.class);
        dict.setId(id);
        boolean result = SqlHelper.retBool(dictMapper.updateById(dict));
        if (result) {
            // 更新字典数据
            List<DictItem> dictItemList = dictItemMapper.selectList(
                    new LambdaQueryWrapper<DictItem>()
                            .eq(DictItem::getDictCode, entity.getDictCode())
                            .select(DictItem::getId)
            );
            if (!dictItemList.isEmpty()){
                List<Long> dictItemIds = dictItemList.stream().map(DictItem::getId).toList();
                DictItem dictItem = new DictItem();
                dictItem.setDictCode(dict.getDictCode());
                dictItemMapper.update(dictItem,
                        new LambdaQueryWrapper<DictItem>()
                                .in(DictItem::getId, dictItemIds)
                );
            }
        }
        return result;
    }

    /**
     * 删除字典
     *
     * @param ids 字典ID，多个以英文逗号(,)分割
     */
    @Transactional
    @Override
    public void deleteDictByIds(List<String> ids) {
        // 删除字典
        dictMapper.deleteByIds(ids);

        // 删除字典项
        List<Dict> list = dictMapper.selectByIds(ids);
        if (!list.isEmpty()) {
            List<String> dictCodes = list.stream().map(Dict::getDictCode).toList();
            dictItemMapper.delete(new LambdaQueryWrapper<DictItem>()
                    .in(DictItem::getDictCode, dictCodes)
            );
        }
    }

    /**
     * 根据字典ID列表获取字典编码列表
     *
     * @param ids 字典ID列表
     * @return 字典编码列表
     */
    @Override
    public List<String> getDictCodesByIds(List<String> ids) {
        List<Dict> dictList = dictMapper.selectByIds(ids);
        return dictList.stream().map(Dict::getDictCode).toList();
    }

}




