package com.mindvoice.psych.system.converter;


import com.mindvoice.psych.system.model.entity.Dept;
import com.mindvoice.psych.system.model.form.DeptForm;
import com.mindvoice.psych.system.model.vo.DeptVO;
import org.mapstruct.Mapper;

/**
 * 部门对象转换器
 *
 * @author liu
 * @since 2025/7/29
 */
@Mapper(componentModel = "spring")
public interface DeptConverter {

    DeptForm toForm(Dept entity);
    
    DeptVO toVo(Dept entity);

    Dept toEntity(DeptForm deptForm);

}