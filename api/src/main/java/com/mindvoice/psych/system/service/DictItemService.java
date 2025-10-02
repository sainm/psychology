package com.mindvoice.psych.system.service;

import com.mindvoice.psych.common.pojo.PageResult;
import com.mindvoice.psych.system.model.form.DictItemForm;
import com.mindvoice.psych.system.model.query.DictItemPageQuery;
import com.mindvoice.psych.system.model.vo.DictItemOptionVO;
import com.mindvoice.psych.system.model.vo.DictItemPageVO;

import java.util.List;

/**
 * 字典项接口
 *
 * @author liu
 * @since 2023/3/4
 */
public interface DictItemService {

    /**
     * 获取字典项列表
     *
     * @param dictCode 字典编码
     * @return 字典项列表
     */
    List<DictItemOptionVO> getDictItems(String dictCode);

    /**
     * 获取字典项表单
     *
     * @param itemId 字典项ID
     * @return 字典项表单
     */
    DictItemForm getDictItemForm(Long itemId);

    /**
     * 保存字典项
     *
     * @param formData 字典项表单
     * @return 是否成功
     */
    boolean saveDictItem(DictItemForm formData);

    /**
     * 更新字典项
     *
     * @param formData 字典项表单
     * @return 是否成功
     */
    boolean updateDictItem(DictItemForm formData);

    /**
     * 删除字典项
     *
     * @param ids 字典项ID,多个逗号分隔
     */
    void deleteDictItemByIds(String ids);

    PageResult<DictItemPageVO> getDictItemPage(DictItemPageQuery queryParams);
}
