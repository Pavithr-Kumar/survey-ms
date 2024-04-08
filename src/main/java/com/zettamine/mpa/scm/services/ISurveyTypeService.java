package com.zettamine.mpa.scm.services;

import com.zettamine.mpa.scm.dto.SurveyTypeDto;

public interface ISurveyTypeService {
	
	public void createSurveyType(SurveyTypeDto surveyTypeDto);

	public SurveyTypeDto fetchSurveyType(Integer id);

	public void updateSurveyType(Integer id, SurveyTypeDto surveyTypeDto);

}