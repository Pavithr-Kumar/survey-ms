package com.zettamine.mpa.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.Surveyor;
import com.zettamine.mpa.scm.exception.DuplicationException;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.mapper.SurveyServiceAreaMapper;
import com.zettamine.mpa.scm.mapper.SurveyorMapper;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyorRepository;
import com.zettamine.mpa.scm.services.ISurveyorService;
import com.zettamine.mpa.scm.util.SurveyUtility;

import lombok.AllArgsConstructor;

/**
 * Service class implementation for managing surveyors.
 */
@Service
@AllArgsConstructor
public class SurveyorServiceImpl implements ISurveyorService {
    private final SurveyorRepository surveyorRepository;
    private final SurveyCompanyRepository surveyCompanyRepository;

    /**
     * Saves a new surveyor.
     *
     * @param surveyorDto The DTO containing surveyor details to be saved.
     * @return True if the surveyor is successfully saved, otherwise false.
     * @throws ResourceNotFoundException if the survey company with the provided ID is not found.
     * @throws DuplicationException if a surveyor with the same license ID, email, or phone already exists.
     */
    @Override
    public void saveSurveyor(SurveyorDto surveyorDto) {
        Optional<SurveyCompany> optSurveyCompany = surveyCompanyRepository.findById(surveyorDto.getSurveyCompanyId());

        if (optSurveyCompany.isEmpty()) {
            throw new ResourceNotFoundException("No Companies found with Id : " + surveyorDto.getSurveyCompanyId());
        } else if (surveyorRepository.findBySurveyorLicenceId(surveyorDto.getSurveyorLicenceId().toUpperCase()).isPresent()) {
            throw new DuplicationException("Surveyor already exists with License Id : " + surveyorDto.getSurveyorLicenceId());
        } else if (surveyorRepository.findByEmail(surveyorDto.getEmail().toUpperCase()).isPresent())
            throw new DuplicationException("Surveyor already exists with Email Id : " + surveyorDto.getEmail());
        else if (surveyorRepository.findByPhone(surveyorDto.getPhone().toUpperCase()).isPresent())
            throw new DuplicationException("Surveyor already exists with Phone Number : " + surveyorDto.getPhone());

        Surveyor surveyor = SurveyorMapper.mapToSurveyor(surveyorDto, new Surveyor());
        surveyor.setSurveyCompany(optSurveyCompany.get());
        surveyorRepository.save(surveyor);

      
    }

    /**
     * Fetches a surveyor by ID.
     *
     * @param id The ID of the surveyor to fetch.
     * @return SurveyorDto containing the information of the fetched surveyor.
     * @throws RuntimeException if no surveyor is found with the provided ID.
     */
    @Override
    public SurveyorDto fetchSurveyor(Integer id) {
        return SurveyorMapper.mapToSurveyorDto(surveyorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Users Found with Id " + id)), new SurveyorDto());
    }

    /**
     * Updates an existing surveyor.
     *
     * @param surveyorDto The DTO containing updated surveyor information.
     * @param id The ID of the surveyor to be updated.
     * @throws ResourceNotFoundException if the survey company with the provided ID is not found.
     * @throws DuplicationException if a surveyor with the updated license ID, email, or phone already exists.
     */
    @Override
    public void updateSurveyor(SurveyorDto surveyorDto, Integer id) {
	    if(surveyorRepository.findById(id).isEmpty())
    		throw new ResourceNotFoundException("No Surveyors found with Id : " + id);
        if (surveyCompanyRepository.findById(surveyorDto.getSurveyCompanyId()).isEmpty()) {
            throw new ResourceNotFoundException("No Companies found with Id : " + surveyorDto.getSurveyCompanyId());
        }

        Optional<Surveyor> optSurveyor = surveyorRepository
                .findBySurveyorLicenceId(SurveyUtility.safeUpperCaseTrim(surveyorDto.getSurveyorLicenceId()));

        if (optSurveyor.isPresent() && optSurveyor.get().getSurveyorId() != id) {
            throw new DuplicationException("A different surveyor exists with License Id " + surveyorDto.getSurveyorLicenceId());
        }

        optSurveyor = surveyorRepository.findByPhone(surveyorDto.getPhone());

        if (optSurveyor.isPresent() && optSurveyor.get().getSurveyorId() != id) {
            throw new DuplicationException("A different surveyor exists with phone " + surveyorDto.getPhone());
        }

        optSurveyor = surveyorRepository.findByEmail(SurveyUtility.safeUpperCaseTrim(surveyorDto.getEmail()));

        if (optSurveyor.isPresent() && optSurveyor.get().getSurveyorId() != id) {
            throw new DuplicationException("A different surveyor exists with email " + surveyorDto.getEmail());
        }

        surveyorDto.setSurveyorId(id);
        Surveyor surveyor = SurveyorMapper.mapToSurveyor(surveyorDto, new Surveyor());
        surveyor.setSurveyCompany(surveyCompanyRepository.findById(surveyorDto.getSurveyCompanyId()).get());

        surveyorRepository.save(surveyor);
    }
    
    @Override
	public List<SurveyorDto> getAllSurveyorsByCompanyId(Integer surveyCompanyId) {
		SurveyCompany surveyCompany=  surveyCompanyRepository.findById(surveyCompanyId)
                .orElseThrow(()->new ResourceNotFoundException("No Companies found with Id : " + surveyCompanyId));
		return surveyCompany.getSurveyors().stream()
				                              .map(surveyor-> SurveyorMapper.mapToSurveyorDto(surveyor, new SurveyorDto()))
				                              .collect(Collectors.toList());
		
	}

	@Override
	public List<SurveyorDto> getAllSurveyors() {
		
		return surveyorRepository.findAll().stream()
				                           .map(surveyor->SurveyorMapper.mapToSurveyorDto(surveyor, new SurveyorDto()))
				                           .collect(Collectors.toList());
	}
}

