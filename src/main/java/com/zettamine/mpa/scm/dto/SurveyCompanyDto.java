package com.zettamine.mpa.scm.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zettamine.mpa.scm.constants.SurveyConstants;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Uppuluru Mounika
 * Data transfer object (DTO) representing a survey company.
 */
@Data
public class SurveyCompanyDto {
	private Integer surveyCompanyId;
	
	@NotBlank(message = SurveyConstants.PROVIDE_VALUE)
	private String companyName;
	
	private String address;
	
	/**
     * City where the survey company is located.
     */
	@Pattern(regexp = SurveyConstants.REGEX_ADDRESS, message = SurveyConstants.VALID_NAME)
	private String city;
	
	/**
     * State where the survey company is located.
     */
	@Pattern(regexp = SurveyConstants.REGEX_ADDRESS, message = SurveyConstants.VALID_NAME)
	private String state;
	
	/**
     * Zip code of the survey company's location.
     */
	@Pattern(regexp = SurveyConstants.REGEX_ZIPCODE, message = SurveyConstants.VALID_ZIPCODE)
	private String zipcode;
	
	/**
     * Country where the survey company is located.
     */
	@Pattern(regexp = SurveyConstants.REGEX_ADDRESS, message = SurveyConstants.VALID_NAME)
	private String country;
	
	/**
     * Phone number of the survey company.
     * Format: XXX-XXX-XXXX
     */
	@NotNull
	@Pattern(regexp = SurveyConstants.REGEX_PHONE, message = SurveyConstants.VALID_PHONE)
	private String phone;
	
	 /**
     * Email address of the survey company.
     */
	@NotNull
	@Email(message = SurveyConstants.VALID_EMAIL)
	private String email;
	
	 /**
     * Website URL of the survey company.
     */
	private String website;
	
	 /**
     * Additional notes or comments about the survey company.
     */
	private String notes;
	
	/**
     * Indicates whether the survey company provides triangulation services.
     */
	private Boolean providesTriangulationService;
	
	/**
     * List of survey type names associated with the survey company.
     */
	//@JsonIgnore
	private List<String> surveyTypeNamesList = new ArrayList<>();
	

}