package com.zettamine.mpa.scm.services;

import java.util.List;

import com.zettamine.mpa.scm.dto.SurveyorDto;

/**
 * Interface defining operations related to surveyors.
 */
public interface ISurveyorService {

    /**
     * Saves a new surveyor.
     *
     * @param surveyorDto The DTO containing surveyor details to be saved.
     * @return True if the surveyor is successfully saved, otherwise false.
     */
    boolean saveSurveyor(SurveyorDto surveyorDto);

    /**
     * Fetches a surveyor by ID.
     *
     * @param id The ID of the surveyor to fetch.
     * @return SurveyorDto containing the information of the fetched surveyor.
     */
    SurveyorDto fetchSurveyor(Integer id);

    /**
     * Updates an existing surveyor.
     *
     * @param surveyorDto The DTO containing updated surveyor information.
     * @param id The ID of the surveyor to be updated.
     */
    void updateSurveyor(SurveyorDto surveyorDto, Integer id);
    List<SurveyorDto> getAllSurveyorsByCompanyId(Integer surveyCompanyId);
}
