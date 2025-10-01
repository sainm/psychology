package com.mindvoice.psych.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mindvoice.psych.system.model.entity.DictItem;
import com.mindvoice.psych.system.model.form.DictItemForm;
import com.mindvoice.psych.system.model.vo.DictPageVO;
import com.mindvoice.psych.common.model.Option;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 字典项对象转换器
 *
 * @author liu
 * @since 2025/6/8
 */
@Mapper(componentModel = "spring")
public interface DictItemConverter {

    Page<DictPageVO> toPageVo(Page<DictItem> page);

    DictItemForm toForm(DictItem entity);

    DictItem toEntity(DictItemForm formFata);

    Option<Long> toOption(DictItem dictItem);
    List<Option<Long>> toOption(List<DictItem> dictData);
}
