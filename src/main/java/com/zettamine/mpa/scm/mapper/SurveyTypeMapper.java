package com.zettamine.mpa.scm.mapper;

import com.zettamine.mpa.scm.dto.SurveyTypeDto;
import com.zettamine.mpa.scm.entity.SurveyType;
import com.zettamine.mpa.scm.util.SurveyUtility;

public class SurveyTypeMapper {

	public static SurveyTypeDto mapToSurveyTypeDto(SurveyType surveyType, SurveyTypeDto surveyTypeDto) {
		surveyTypeDto.setSurveyTypeId(surveyType.getSurveyTypeId());
		surveyTypeDto.setSurveyTypeName(SurveyUtility.safeUpperCaseTrim(surveyType.getSurveyTypeName()));
		surveyTypeDto.setDescription(SurveyUtility.safeUpperCaseTrim(surveyType.getDescription()));
		return surveyTypeDto;
	}

	public static SurveyType mapToSurveyType(SurveyTypeDto surveyTypeDto, SurveyType surveyType) {
		 surveyType.setSurveyTypeId(surveyTypeDto.getSurveyTypeId());
		surveyType.setSurveyTypeName(SurveyUtility.safeUpperCaseTrim(surveyTypeDto.getSurveyTypeName()));
		surveyType.setDescription(SurveyUtility.safeUpperCaseTrim(surveyTypeDto.getDescription()));
      System.err.println(surveyType);
		return surveyType;
	}

}
