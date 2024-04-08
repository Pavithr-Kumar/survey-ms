package com.zettamine.mpa.scm.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.Surveyor;
import com.zettamine.mpa.scm.exception.DuplicationException;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.mapper.SurveyorMapper;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyorRepository;
import com.zettamine.mpa.scm.services.ISurveyorService;
import com.zettamine.mpa.scm.util.SurveyUtility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyorService implements ISurveyorService {
	private SurveyorRepository surveyorRepository;
	
	private SurveyCompanyRepository surveyCompanyRepository;

	@Override
	public boolean saveSurveyor(SurveyorDto surveyorDto) {
		Optional<SurveyCompany> optSurveyCompany=surveyCompanyRepository.findById(surveyorDto.getSurveyCompanyId());
		
		if(optSurveyCompany.isEmpty()) {
			throw new ResourceNotFoundException("No Companies found with Id : "+surveyorDto.getSurveyCompanyId());
		}
		else if(surveyorRepository.findBySurveyorLicenceId(surveyorDto.getSurveyorLicenceId().toUpperCase()).isPresent()) {
			throw new DuplicationException("Surveyor ALready exists with Licence Id : "+surveyorDto.getSurveyorLicenceId());
		}
		else if(surveyorRepository.findByEmail(surveyorDto.getEmail().toUpperCase()).isPresent())
			throw new DuplicationException("Surveyor ALready exists with Email Id : "+ surveyorDto.getEmail());
		else if(surveyorRepository.findByPhone(surveyorDto.getPhone().toUpperCase()).isPresent())
			throw new DuplicationException("Surveyor ALready exists with Phone Number : "+surveyorDto.getPhone());
		
		
		Surveyor surveyor=SurveyorMapper.mapToSurveyor(surveyorDto, new Surveyor());
		surveyor.setSurveyCompany(optSurveyCompany.get());
		surveyorRepository.save(surveyor);
		
		return true;
	}

	@Override
	public SurveyorDto fetchSurveyor(Integer id) {
		return SurveyorMapper.mapToSurveyorDto(surveyorRepository.findById(id).orElseThrow(()->new RuntimeException("No Users Found with Id "+id)), new SurveyorDto());
	}
	
	
	@Override
	public void updateSurveyor(SurveyorDto surveyorDto, Integer id) {
		if(surveyCompanyRepository.findById(surveyorDto.getSurveyCompanyId()).isEmpty()) {
			throw new ResourceNotFoundException("No Companies found with Id : "+surveyorDto.getSurveyCompanyId());
		}
		
		Optional<Surveyor> optSurveyor =surveyorRepository.findBySurveyorLicenceId(SurveyUtility.safeUpperCaseTrim(surveyorDto.getSurveyorLicenceId()));
		
		if(optSurveyor.isPresent() && optSurveyor.get().getSurveyorId()!=id) {
			throw new DuplicationException("A different surveyor exists with License Id "+surveyorDto.getSurveyorLicenceId());
		}
		
		 optSurveyor =surveyorRepository.findByPhone(surveyorDto.getPhone());
		
		if(optSurveyor.isPresent() && optSurveyor.get().getSurveyorId()!=id) {
			throw new DuplicationException("A different surveyor exists with phone "+surveyorDto.getPhone());
		}
		
		optSurveyor =surveyorRepository.findByEmail(SurveyUtility.safeUpperCaseTrim(surveyorDto.getEmail()));
		
		if(optSurveyor.isPresent() && optSurveyor.get().getSurveyorId()!=id) {
			
			throw new DuplicationException("A different surveyor exists with email "+surveyorDto.getEmail());
		}
		
		
		surveyorDto.setSurveyorId(id);
		Surveyor surveyor=SurveyorMapper.mapToSurveyor(surveyorDto, new Surveyor());
		surveyor.setSurveyCompany(surveyCompanyRepository.findById(surveyorDto.getSurveyCompanyId()).get());
		
		surveyorRepository.save(surveyor);
		
	}

	
	

}
