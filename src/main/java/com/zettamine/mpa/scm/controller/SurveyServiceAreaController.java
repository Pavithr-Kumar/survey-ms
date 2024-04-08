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
import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.repository.SurveyServiceAreaRepository;

import com.zettamine.mpa.scm.services.ISurveyServiceAreaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/survey/service-area")
@AllArgsConstructor
public class SurveyServiceAreaController {
	private ISurveyServiceAreaService serviceAreaService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> handleCreateServiceAreaRequest(@RequestBody SurveyServiceAreaDto serviceAreaDto) {
		serviceAreaService.saveSurveyServiceArea(serviceAreaDto);
		return  ResponseEntity.status(HttpStatus.CREATED)
				              .body(new ResponseDto("201", "Service Area Created Successfully"));
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto> handleUpdtaeServiceAreaRequest(@RequestBody SurveyServiceAreaDto serviceAreaDto, @PathVariable Integer id) {
		serviceAreaService.updateSurveyor(serviceAreaDto, id);
		return  ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto("200", "Service Area Updated Successfully"));
		
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<SurveyServiceAreaDto> handleFetchServiceAreaRequest(@PathVariable Integer id) {
		
		return  ResponseEntity.status(HttpStatus.OK)
				.body(serviceAreaService.fetchSurveyor(id));
		
	}
	
	
	
	

}
