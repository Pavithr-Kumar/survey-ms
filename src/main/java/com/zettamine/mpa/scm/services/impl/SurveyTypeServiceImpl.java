package com.zettamine.mpa.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.SurveyTypeDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.SurveyType;
import com.zettamine.mpa.scm.exception.DuplicationException;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.mapper.SurveyTypeMapper;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyServiceTypeRepository;
import com.zettamine.mpa.scm.services.ISurveyTypeService;
import com.zettamine.mpa.scm.util.SurveyUtility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyTypeServiceImpl implements ISurveyTypeService {
	private SurveyServiceTypeRepository surveyTypeRepository;
	private SurveyCompanyRepository surveyCompanyRepository;

	@Override
	public void createSurveyType(SurveyTypeDto surveyTypeDto) {
		SurveyType surveyType = new SurveyType();
		if(surveyTypeRepository.findBySurveyTypeName(SurveyUtility.safeUpperCaseTrim(surveyTypeDto.getSurveyTypeName())).isPresent()){
			throw new DuplicationException("Survey Type already exists with name: " + surveyTypeDto.getSurveyTypeName());
		}
		
		SurveyTypeMapper.mapToSurveyType(surveyTypeDto, surveyType);
		surveyTypeRepository.save(surveyType);
		

	}

	@Override
	public SurveyTypeDto fetchSurveyType(Integer id) {
		SurveyTypeDto surveyTypeDto = new SurveyTypeDto();
		Optional<SurveyType> surveyType = surveyTypeRepository.findById(id);
		if(surveyType.isEmpty()){
			throw new ResourceNotFoundException("Survey Type", "surveyTypeId", id);
		}
		surveyTypeDto.setSurveyTypeId(id);
		SurveyTypeMapper.mapToSurveyTypeDto(surveyType.get(), surveyTypeDto);
		return surveyTypeDto;
	}

	@Override
	public void updateSurveyType(Integer id, SurveyTypeDto surveyTypeDto) {
		SurveyType surveyType = new SurveyType();
		
		if(surveyTypeRepository.findById(id).isPresent()){
			Optional<SurveyType> surveyTypeByName = surveyTypeRepository.findBySurveyTypeName(SurveyUtility.safeUpperCaseTrim(surveyTypeDto.getSurveyTypeName()));
			if(surveyTypeByName.isPresent() && surveyTypeByName.get().getSurveyTypeId() != id){
				throw new DuplicationException("Survey Type already exists with name: " + surveyTypeDto.getSurveyTypeName());
			}
		}
		else {
			throw new ResourceNotFoundException("Survey Type", "surveyTypeId", id);
		}
		
		
		
		SurveyTypeMapper.mapToSurveyType(surveyTypeDto, surveyType);
		surveyType.setSurveyTypeId(id);
		surveyTypeRepository.save(surveyType);
		
	}

	@Override
	public List<SurveyTypeDto> getAllSurveyTypesByCompany(Integer companyId) {
		SurveyCompany surveyCompany=surveyCompanyRepository.findById(companyId)
				                                                      .orElseThrow(()->new ResourceNotFoundException("No Company Exists with Id : "+companyId));
		
		
		return surveyCompany.getSurveyTypes().stream()
				                             .map(type->SurveyTypeMapper.mapToSurveyTypeDto(type, new SurveyTypeDto()))
				                             .collect(Collectors.toList());
	}

	@Override
	public List<SurveyTypeDto> getAllSurveyTypes() {
		
		return surveyTypeRepository.findAll().stream()
				.map(type->SurveyTypeMapper.mapToSurveyTypeDto(type, new SurveyTypeDto()))
                .collect(Collectors.toList());
	}
	
	

}