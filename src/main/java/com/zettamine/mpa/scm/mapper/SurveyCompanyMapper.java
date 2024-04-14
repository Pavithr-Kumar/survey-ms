package com.zettamine.mpa.scm.mapper;

import java.util.stream.Collectors;

import com.zettamine.mpa.scm.dto.SurveyCompanyDto;
import com.zettamine.mpa.scm.dto.SurveyCompanySearchCriteriaDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.util.SurveyUtility;


public class SurveyCompanyMapper {
	
	public static SurveyCompanyDto mapToSurveyCompanyDto(SurveyCompany surveyCompany, SurveyCompanyDto surveyCompanyDto) {
		surveyCompanyDto.setSurveyCompanyId(surveyCompany.getSurveyCompanyId());
		surveyCompanyDto.setCompanyName(surveyCompany.getCompanyName());
		surveyCompanyDto.setAddress(surveyCompany.getAddress());
		surveyCompanyDto.setState(surveyCompany.getState());
		surveyCompanyDto.setCity(surveyCompany.getCity());
		surveyCompanyDto.setZipcode(surveyCompany.getZipcode());
		surveyCompanyDto.setCountry(surveyCompany.getCountry());
		surveyCompanyDto.setPhone(surveyCompany.getPhone());
		surveyCompanyDto.setEmail(surveyCompany.getEmail());
		surveyCompanyDto.setWebsite(surveyCompany.getWebsite());
		surveyCompanyDto.setNotes(surveyCompany.getNotes());
		surveyCompanyDto.setProvidesTriangulationService(surveyCompany.getTriangularSurvey());
		
		surveyCompanyDto.setSurveyTypeNamesList(surveyCompany.getSurveyTypes().stream()
				                                             .map(type->type.getSurveyTypeName())
				                                             .collect(Collectors.toList()));
		return surveyCompanyDto;

	}
	
	public static SurveyCompany mapToSurveyCompany( SurveyCompanyDto surveyCompanyDto, SurveyCompany surveyCompany) {
		surveyCompany.setCompanyName(surveyCompanyDto.getCompanyName().toUpperCase().trim());
		
		
	    surveyCompany.setAddress(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getAddress()));
		surveyCompany.setCity(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getCity()));
		surveyCompany.setState(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getState()));
		
		surveyCompany.setZipcode(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getZipcode()));
		surveyCompany.setCountry(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getCountry()));
		surveyCompany.setPhone(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getPhone()));
		surveyCompany.setEmail(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getEmail()));
		surveyCompany.setWebsite(surveyCompanyDto.getWebsite());
		surveyCompany.setNotes(SurveyUtility.safeUpperCaseTrim(surveyCompanyDto.getNotes()));
		surveyCompany.setTriangularSurvey(surveyCompanyDto.getProvidesTriangulationService());
		return surveyCompany;

	}
	
	public static SurveyCompany mapSearchCriteriaToSurveyCompany(SurveyCompanySearchCriteriaDto searchCriteriaDto, SurveyCompany surveyCompany) {
		
		surveyCompany.setCompanyName(SurveyUtility.safeUpperCaseTrim(searchCriteriaDto.getName()));
		surveyCompany.setState(SurveyUtility.safeUpperCaseTrim(searchCriteriaDto.getState()));
		surveyCompany.setCity(SurveyUtility.safeUpperCaseTrim(searchCriteriaDto.getCity()));
		surveyCompany.setZipcode(SurveyUtility.safeUpperCaseTrim(searchCriteriaDto.getZipcode()));
		return surveyCompany;
	}

}
