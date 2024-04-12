package com.zettamine.mpa.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.ServiceAreasDto;
import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.SurveyServiceArea;
import com.zettamine.mpa.scm.exception.DuplicationException;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.mapper.SurveyServiceAreaMapper;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyServiceAreaRepository;
import com.zettamine.mpa.scm.services.ISurveyServiceAreaService;

import lombok.AllArgsConstructor;

/**
 * Service class implementation for managing survey service areas.
 */
@Service
@AllArgsConstructor
public class SurveyServiceAreaServiceImpl implements ISurveyServiceAreaService {

    private final SurveyServiceAreaRepository serviceAreaRepository;
    private final SurveyCompanyRepository surveyCompanyRepository;

    /**
     * Saves a new survey service area.
     *
     * @param serviceAreaDto The DTO containing survey service area details to be saved.
     * @return True if the survey service area is successfully saved, otherwise false.
     * @throws ResourceNotFoundException if the survey company with the provided ID is not found.
     */
    @Override
    public boolean saveSurveyServiceArea(ServiceAreasDto serviceAreasDto) {
    	SurveyCompany surveyCompany=  surveyCompanyRepository.findById(serviceAreasDto.getSurveyCompanyId())
                                .orElseThrow(()->new ResourceNotFoundException("No Companies found with Id : " + serviceAreasDto.getSurveyCompanyId()));
       List<SurveyServiceArea> surveyAreas = serviceAreasDto.getSurveyServiceAreas().stream()
    		                                                .map(area-> SurveyServiceAreaMapper.mpaToSurveyServiceArea(new SurveyServiceArea(), area))
    		                                                .filter(area->{
    		                                                	area.setSurveyCompany(surveyCompany);
    		                                                	List<SurveyServiceArea> list= serviceAreaRepository.findAll(Example.of(area));
    		                                                	if(list.size()>0) {
    		                                                		throw new DuplicationException("Some service areas alraedy exists");
    		                                                	}
    		                                                	return true;
    		                                                })
    		                                                .collect(Collectors.toList());
       
       surveyCompany.setServiceAreas(surveyAreas);
       surveyCompanyRepository.save(surveyCompany);

       
        return true;
    }

    /**
     * Fetches a survey service area by ID.
     *
     * @param id The ID of the survey service area to fetch.
     * @return SurveyServiceAreaDto containing the information of the fetched survey service area.
     * @throws ResourceNotFoundException if no survey service area is found with the provided ID.
     */
    @Override
    public SurveyServiceAreaDto fetchSurveyServiceArea(Integer id) {
        return SurveyServiceAreaMapper.mapToSurveyServiceAreaDto(serviceAreaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Service Areas found with id : " + id)),
                new SurveyServiceAreaDto());
    }

    /**
     * Updates an existing survey service area.
     *
     * @param serviceAreaDto The DTO containing updated survey service area information.
     * @param id The ID of the survey service area to be updated.
     * @throws ResourceNotFoundException if no survey service area is found with the provided ID.
     */
    @Override
    public void updateSurveyServiceArea(SurveyServiceAreaDto serviceAreaDto, Integer id) {
    	SurveyCompany surveyCompany=  surveyCompanyRepository.findById(serviceAreaDto.getSurveyCompanyId())
    			.orElseThrow(()->new ResourceNotFoundException("No Companies found with Id : " + serviceAreaDto.getSurveyCompanyId()));
        serviceAreaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Service Areas Found with Id : " + id));
        
        SurveyServiceArea surveyServiceArea = SurveyServiceAreaMapper.mpaToSurveyServiceArea(new SurveyServiceArea(), serviceAreaDto);
        surveyServiceArea.setServiceAreaId(null);
        surveyServiceArea.setSurveyCompany(surveyCompany);
        
       if( serviceAreaRepository.findAll(Example.of(surveyServiceArea)).size()>0) {
    	   throw new DuplicationException("Service area alraedy exists");
       }
       
        
       surveyServiceArea.setServiceAreaId(id);

        serviceAreaRepository.save(surveyServiceArea);
       
    }

	@Override
	public List<SurveyServiceAreaDto> getAllByCompanyId(Integer surveyCompanyId) {
		SurveyCompany surveyCompany=  surveyCompanyRepository.findById(surveyCompanyId)
                .orElseThrow(()->new ResourceNotFoundException("No Companies found with Id : " + surveyCompanyId));
		return surveyCompany.getServiceAreas().stream()
				                              .map(area->SurveyServiceAreaMapper.mapToSurveyServiceAreaDto(area, new SurveyServiceAreaDto()))
				                              .collect(Collectors.toList());
		
	}
}
