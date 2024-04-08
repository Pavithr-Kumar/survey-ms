package com.zettamine.mpa.scm.services;

import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.dto.SurveyorDto;

public interface ISurveyServiceAreaService {
	boolean saveSurveyServiceArea(SurveyServiceAreaDto serviceAreaDto);
	SurveyServiceAreaDto fetchSurveyor(Integer id);
	void updateSurveyor(SurveyServiceAreaDto serviceAreaDto, Integer id);

}
