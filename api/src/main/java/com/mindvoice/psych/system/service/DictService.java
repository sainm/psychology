package com.mindvoice.psych.system.service;

import com.mindvoice.psych.common.model.Option;
import com.mindvoice.psych.common.pojo.PageResult;
import com.mindvoice.psych.system.model.form.DictForm;
import com.mindvoice.psych.system.model.query.DictPageQuery;
import com.mindvoice.psych.system.model.vo.DictPageVO;

import java.util.List;

/**
 * 字典业务接口
 *
 * @author liu
 * @since 2025/10/12
 */
public interface DictService {



    /**
     * 获取字典列表
     *
     * @return 字典列表
     */
    List<Option<String>> getDictList();

    /**
     * 获取字典表单数据
     *
     * @param id 字典ID
     * @return 字典表单
     */
    DictForm getDictForm(Long id);

    /**
     * 新增字典
     *
     * @param dictForm 字典表单
     * @return 是否成功
     */
    boolean saveDict(DictForm dictForm);

    /**
     * 修改字典
     *
     * @param id     字典ID
     * @param dictForm 字典表单
     * @return 是否成功
     */
    boolean updateDict(Long id, DictForm dictForm);

    /**
     * 删除字典
     *
     * @param ids 字典ID集合
     */
    void deleteDictByIds(List<String> ids);

    /**
     * 根据字典ID列表获取字典编码列表
     *
     * @param ids 字典ID列表
     * @return 字典编码列表
     */
    List<String> getDictCodesByIds(List<String> ids);

    PageResult<DictPageVO> getDictPage(DictPageQuery queryParams);
}
