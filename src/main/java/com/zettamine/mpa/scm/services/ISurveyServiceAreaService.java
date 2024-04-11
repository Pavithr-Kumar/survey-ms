package com.zettamine.mpa.scm.services;

import java.util.List;

import com.zettamine.mpa.scm.dto.ServiceAreasDto;
import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;

/**
 * Interface defining operations related to survey service areas.
 */
public interface ISurveyServiceAreaService {

    /**
     * Saves a new survey service area.
     *
     * @param serviceAreaDto The DTO containing survey service area details to be saved.
     * @return True if the survey service area is successfully saved, otherwise false.
     */
    boolean saveSurveyServiceArea(ServiceAreasDto serviceAreasDto);

    /**
     * Fetches a survey service area by ID.
     *
     * @param id The ID of the survey service area to fetch.
     * @return SurveyServiceAreaDto containing the information of the fetched survey service area.
     */
    SurveyServiceAreaDto fetchSurveyor(Integer id);

    /**
     * Updates an existing survey service area.
     *
     * @param serviceAreaDto The DTO containing updated survey service area information.
     * @param id The ID of the survey service area to be updated.
     */
    void updateSurveyor(SurveyServiceAreaDto serviceAreaDto, Integer id);
    
    
    List<SurveyServiceAreaDto> getAllByCompanyId(Integer surveyCompanyId);
}
