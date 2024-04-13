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
import com.zettamine.mpa.scm.dto.ServiceAreasDto;
import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.services.ISurveyServiceAreaService;

import lombok.AllArgsConstructor;

/**
 * Controller class for managing survey service area-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/survey/service-area")
@AllArgsConstructor
public class SurveyServiceAreaController {
    private final ISurveyServiceAreaService serviceAreaService;

    /**
     * Handles POST requests to create a new survey service area.
     *
     * @param serviceAreaDto The DTO containing survey service area details to be created.
     * @return ResponseEntity containing the HTTP status and response message.
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> handleCreateServiceAreaRequest(@RequestBody ServiceAreasDto serviceAreasDto) {
        serviceAreaService.saveSurveyServiceArea(serviceAreasDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(new ResponseDto("201", "Service Areas Created Successfully"));
    }

    /**
     * Handles PUT requests to update an existing survey service area.
     *
     * @param serviceAreaDto The DTO containing updated survey service area details.
     * @param id The ID of the survey service area to update.
     * @return ResponseEntity containing the HTTP status and response message.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> handleUpdateServiceAreaRequest(@RequestBody SurveyServiceAreaDto serviceAreaDto, @PathVariable Integer id) {
        serviceAreaService.updateSurveyServiceArea(serviceAreaDto, id);
        return ResponseEntity.status(HttpStatus.OK)
                             .body(new ResponseDto("200", "Service Area Updated Successfully"));
    }

    /**
     * Handles GET requests to fetch a survey service area by ID.
     *
     * @param id The ID of the survey service area to fetch.
     * @return ResponseEntity containing the HTTP status and survey service area DTO.
     */
    @GetMapping("/fetch/{id}")
    public ResponseEntity<SurveyServiceAreaDto> handleFetchServiceAreaRequest(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(serviceAreaService.fetchSurveyServiceArea(id));
    }
    
    @GetMapping("/fetch/company/{id}")
    public ResponseEntity<List<SurveyServiceAreaDto>> getAllServiceAreasByCompany(@PathVariable Integer id){
    	return ResponseEntity.status(HttpStatus.OK)
                .body(serviceAreaService.getAllByCompanyId(id));
    }
    
    @GetMapping("/fetch-all")
    public ResponseEntity<List<SurveyServiceAreaDto>> getAllServiceAreas(){
    	return ResponseEntity.status(HttpStatus.OK)
    			.body(serviceAreaService.getAllServiceAreas());
    }
}
