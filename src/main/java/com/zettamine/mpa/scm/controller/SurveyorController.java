package com.zettamine.mpa.scm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zettamine.mpa.scm.dto.ResponseDto;
import com.zettamine.mpa.scm.dto.SurveyorDto;

import com.zettamine.mpa.scm.services.ISurveyorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/survey/surveyor")
@AllArgsConstructor
public class SurveyorController {
	private ISurveyorService surveyorService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDto> handlePost(@RequestBody SurveyorDto surveyorDto) {

		surveyorService.saveSurveyor(surveyorDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Surveyor added successfully"));

	}

	@GetMapping("/fetch/{surveyorId}")
	public ResponseEntity<SurveyorDto> fetchSurveyor(@PathVariable Integer surveyorId) {
		
		return ResponseEntity.status((HttpStatus.OK)).body(surveyorService.fetchSurveyor(surveyorId));
	}
	
	@PutMapping("/update/{surveyorId}")
	public ResponseEntity<ResponseDto> updateSurveyor(@PathVariable Integer surveyorId, @RequestBody SurveyorDto surveyorDto){
		surveyorService.updateSurveyor(surveyorDto, surveyorId);
	  	return ResponseEntity.status(HttpStatus.OK)
	  			             .body(new ResponseDto("200", "Surveyor details updated successfully"));
		
	}
	
	
	
	

}
