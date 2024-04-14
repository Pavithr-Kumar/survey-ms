package com.zettamine.mpa.scm.dto;

import java.util.List;

import com.zettamine.mpa.scm.constants.SurveyConstants;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceAreasDto {
	 @NotNull(message = SurveyConstants.NOT_NULL)
	private Integer surveyCompanyId;
	private List<SurveyServiceAreaDto> surveyServiceAreas;

}