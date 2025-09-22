package com.mindvoice.psych.infra.mapper;

import com.mindvoice.psych.infra.domain.SysQuestionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysQuestionnaireMapper {
    SysQuestionnaire findById(Long id);

    List<SysQuestionnaire> findAll();

    int insert(SysQuestionnaire questionnaire);

    int update(SysQuestionnaire questionnaire);

    int delete(Long id);
}
