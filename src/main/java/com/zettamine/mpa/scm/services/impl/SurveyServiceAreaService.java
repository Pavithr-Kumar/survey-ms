package com.zettamine.mpa.scm.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.SurveyServiceArea;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.mapper.SurveyServiceAreaMapper;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyServiceAreaRepository;
import com.zettamine.mpa.scm.services.ISurveyServiceAreaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyServiceAreaService implements ISurveyServiceAreaService {

	private SurveyServiceAreaRepository serviceAreaRepository;
	private SurveyCompanyRepository surveyCompanyRepository;
	
	
	@Override
	public boolean saveSurveyServiceArea(SurveyServiceAreaDto serviceAreaDto) {
		Optional<SurveyCompany> optSurveyCompany=surveyCompanyRepository.findById(serviceAreaDto.getSurveyCompanyId());
		if(optSurveyCompany.isEmpty()) {
			throw new ResourceNotFoundException("No Companies found with Id : "+serviceAreaDto.getSurveyCompanyId());
		}
		
		SurveyServiceArea surveyServiceArea=SurveyServiceAreaMapper.mpaToSurveyServiceArea(new SurveyServiceArea(), serviceAreaDto);
		surveyServiceArea.setSurveyCompany(optSurveyCompany.get());
		
		serviceAreaRepository.save(surveyServiceArea);
		return true;
	}

	@Override
	public SurveyServiceAreaDto fetchSurveyor(Integer id) {
		
		return SurveyServiceAreaMapper.mapToSurveyServiceAreaDto(serviceAreaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Service Areas found with id : "+id)), new SurveyServiceAreaDto());
	}

	@Override
	public void updateSurveyor(SurveyServiceAreaDto serviceAreaDto, Integer id) {
		Optional<SurveyServiceArea> optServiceArea = serviceAreaRepository.findById(id);
		if(optServiceArea.isEmpty()) {
			throw new ResourceNotFoundException("No Service Areas Found with Id : "+id);
		}
		serviceAreaDto.setServiceAreaId(id);
		saveSurveyServiceArea(serviceAreaDto);
		
	}

	
}
