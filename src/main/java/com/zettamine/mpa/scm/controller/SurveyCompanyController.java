package com.zettamine.mpa.scm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.mpa.scm.constants.SurveyConstants;
import com.zettamine.mpa.scm.dto.ResponseDto;
import com.zettamine.mpa.scm.dto.SurveyCompanyDto;
import com.zettamine.mpa.scm.dto.SurveyCompanySearchCriteriaDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.services.ISurveyCompanyService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/survey/company")
public class SurveyCompanyController {
	private ISurveyCompanyService surveyCompanyService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<ResponseDto> createSurveyCompany(@Valid @RequestBody SurveyCompanyDto surveyCompanyDto){
		surveyCompanyService.createSurveyCompany(surveyCompanyDto);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(SurveyConstants.STATUS_201, SurveyConstants.MESSAGE_201));
	}
	
	@GetMapping(value = "/fetch/{id}")
	public ResponseEntity<SurveyCompanyDto> fetchSurveyCompany(@PathVariable Integer id){
		SurveyCompanyDto surveyCompanyDto = surveyCompanyService.getSurveyCompanyById(id);
		return ResponseEntity.status(HttpStatus.OK).body(surveyCompanyDto);
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<ResponseDto> updateSurveyCompany(@PathVariable Integer id, @Valid @RequestBody SurveyCompanyDto surveyCompanyDto){
		surveyCompanyService.updateSurveyCompany(id, surveyCompanyDto);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(SurveyConstants.STATUS_200, SurveyConstants.MESSAGE_200));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<SurveyCompanyDto>> getCompaniesBycriteria(@RequestBody SurveyCompanySearchCriteriaDto searchCriteriaDto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(surveyCompanyService.getCompaniesByCriteria(searchCriteriaDto));
		
	}
	
	@GetMapping("/search-by-serv-area")
	public ResponseEntity<List<SurveyCompanyDto>> getCompaniesByServiceArea(@RequestBody SurveyCompanySearchCriteriaDto searchCriteriaDto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(surveyCompanyService.getCompaniesByServiceArea(searchCriteriaDto));
		
	}
	
	
	

}
