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
import com.zettamine.mpa.scm.dto.SurveyTypeDto;
import com.zettamine.mpa.scm.services.ISurveyTypeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/survey/survey-type")
@AllArgsConstructor
public class SurveyTypeController {
	private ISurveyTypeService surveyTypeService;
	
	@PostMapping(path = "/create")
	public ResponseEntity<ResponseDto> createSurveyType(@RequestBody SurveyTypeDto surveyTypeDto){
		surveyTypeService.createSurveyType(surveyTypeDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(SurveyConstants.STATUS_201, SurveyConstants.MESSAGE_201));
	}
	
	@GetMapping(path = "/fetch/{id}")
	public ResponseEntity<SurveyTypeDto> fetchSurveyType(@PathVariable Integer id){
		SurveyTypeDto surveyTypeDto = surveyTypeService.fetchSurveyType(id);
		return ResponseEntity.status(HttpStatus.OK).body(surveyTypeDto);
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<ResponseDto> updateSurveyType(@PathVariable Integer id, @RequestBody SurveyTypeDto surveyTypeDto){
		surveyTypeService.updateSurveyType(id, surveyTypeDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(SurveyConstants.STATUS_200, "Survey Type updated successfully"));
	}
	
	@GetMapping("/fetch-by-company")
	public ResponseEntity<List<SurveyTypeDto>> getAllSurveyTypesByCompany(Integer companyId){
		return ResponseEntity.status(HttpStatus.OK)
				             .body(surveyTypeService.getAllSurveyTypesByCompany(companyId));
	}
	
	@GetMapping("/fetch-all")
	public ResponseEntity<List<SurveyTypeDto>> getAllSurveyTypes(Integer companyId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(surveyTypeService.getAllSurveyTypes());
	}
	
	

}