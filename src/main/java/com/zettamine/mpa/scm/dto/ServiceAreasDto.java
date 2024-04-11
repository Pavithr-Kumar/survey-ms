package com.zettamine.mpa.scm.dto;

import java.util.List;

import lombok.Data;

@Data
public class ServiceAreasDto {
	private Integer surveyCompanyId;
	private List<SurveyServiceAreaDto> surveyServiceAreas;

}
