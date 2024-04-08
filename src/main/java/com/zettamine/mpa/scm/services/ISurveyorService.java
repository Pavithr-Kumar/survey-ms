package com.zettamine.mpa.scm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.entity.Surveyor;


public interface ISurveyorService {
	boolean saveSurveyor(SurveyorDto surveyorDto);
	SurveyorDto fetchSurveyor(Integer id);
	void updateSurveyor(SurveyorDto surveyorDto, Integer id);
	

}
