package com.zettamine.mpa.scm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SurveyTypeDto {
	private Integer surveyTypeId;
	
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z\s]+$")
	private String surveyTypeName;
	
	@Pattern(regexp = "^[A-Za-z\s]+$")
	private String description;
	//private List<Integer> surveyCompanyIds = new ArrayList<>();

}
