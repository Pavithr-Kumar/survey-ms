package com.zettamine.mpa.scm.repository;

import java.util.List;

import com.zettamine.mpa.scm.dto.SurveyCompanyDto;
import com.zettamine.mpa.scm.dto.SurveyCompanySearchCriteriaDto;

public interface ISurveyCompanySearchCriteriaRepository {
	 List<SurveyCompanyDto> searchCompaniesByCriteria(SurveyCompanySearchCriteriaDto searchCriteria);

}
