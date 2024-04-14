package com.zettamine.mpa.scm.dto;

import com.zettamine.mpa.scm.constants.SurveyConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * Data transfer object (DTO) representing a survey type.
 */
@Data
public class SurveyTypeDto {
	private Integer surveyTypeId;
	
	/**
     * Name of the survey type.
     */
	@NotNull(message = SurveyConstants.NOT_NULL)
	@NotBlank(message = SurveyConstants.NOT_BLANK)
	@Pattern(regexp = SurveyConstants.REGEX_TEXT, message = SurveyConstants.PROVIDE_VALUE)
	private String surveyTypeName;
	
	/**
     * Description of the survey type.
     */
	//@Pattern(regexp = SurveyConstants.REGEX_TEXT, message = SurveyConstants.PROVIDE_VALUE)
	private String description;
	//private List<Integer> surveyCompanyIds = new ArrayList<>();

}