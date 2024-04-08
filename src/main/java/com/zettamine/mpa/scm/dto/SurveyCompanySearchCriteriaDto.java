package com.zettamine.mpa.scm.dto;

import lombok.Data;

@Data
public class SurveyCompanySearchCriteriaDto {
	private Integer surveyCompanyId;
	private String name;
	private String state;
	private String city;
	private String zipcode;

}
