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

import com.zettamine.mpa.scm.dto.ResponseDto;
import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.services.ISurveyorService;

import lombok.AllArgsConstructor;

/**
 * Controller class for managing surveyor-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/survey/surveyor")
@AllArgsConstructor
public class SurveyorController {
    private final ISurveyorService surveyorService;

    /**
     * Handles POST requests to create a new surveyor.
     *
     * @param surveyorDto The DTO containing surveyor details to be created.
     * @return ResponseEntity containing the HTTP status and response message.
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> handlePost(@RequestBody SurveyorDto surveyorDto) {
        surveyorService.saveSurveyor(surveyorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Surveyor added successfully"));
    }

    /**
     * Handles GET requests to fetch a surveyor by ID.
     *
     * @param surveyorId The ID of the surveyor to fetch.
     * @return ResponseEntity containing the HTTP status and surveyor DTO.
     */
    @GetMapping("/fetch/{surveyorId}")
    public ResponseEntity<SurveyorDto> fetchSurveyor(@PathVariable Integer surveyorId) {
        return ResponseEntity.status(HttpStatus.OK).body(surveyorService.fetchSurveyor(surveyorId));
    }

    /**
     * Handles PUT requests to update an existing surveyor.
     *
     * @param surveyorId The ID of the surveyor to update.
     * @param surveyorDto The DTO containing updated surveyor details.
     * @return ResponseEntity containing the HTTP status and response message.
     */
    @PutMapping("/update/{surveyorId}")
    public ResponseEntity<ResponseDto> updateSurveyor(@PathVariable Integer surveyorId, @RequestBody SurveyorDto surveyorDto) {
        surveyorService.updateSurveyor(surveyorDto, surveyorId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "Surveyor details updated successfully"));
    }
    
    
    
    @GetMapping("/fetch/company/{id}")
    public ResponseEntity<List<SurveyorDto>> getAllServiceAreas(@PathVariable Integer id){
    	return ResponseEntity.status(HttpStatus.OK)
                .body(surveyorService.getAllSurveyorsByCompanyId(id));
    }
}
