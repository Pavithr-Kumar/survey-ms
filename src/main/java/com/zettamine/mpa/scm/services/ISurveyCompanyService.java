package com.zettamine.mpa.scm.services;

import java.util.List;

import com.zettamine.mpa.scm.dto.SurveyCompanyDto;
import com.zettamine.mpa.scm.dto.SurveyCompanySearchCriteriaDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;

public interface ISurveyCompanyService {
	
	 void createSurveyCompany(SurveyCompanyDto surveyCompanyDto);
	
	 SurveyCompanyDto getSurveyCompanyById(Integer surveyCompanyId);

	 void updateSurveyCompany(Integer surveyCompanyId, SurveyCompanyDto surveyCompanyDto);
	 
	 List<SurveyCompanyDto> getCompaniesByCriteria(SurveyCompanySearchCriteriaDto searchCriteriaDto);
	 List<SurveyCompanyDto> getCompaniesByServiceArea(SurveyCompanySearchCriteriaDto searchCriteriaDto);
	
	

}
