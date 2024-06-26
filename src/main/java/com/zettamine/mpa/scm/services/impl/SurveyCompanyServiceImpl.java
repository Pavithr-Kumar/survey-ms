package com.zettamine.mpa.scm.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.zettamine.mpa.scm.dto.SurveyCompanyDto;
import com.zettamine.mpa.scm.dto.SurveyCompanySearchCriteriaDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.SurveyServiceArea;
import com.zettamine.mpa.scm.entity.SurveyType;
import com.zettamine.mpa.scm.exception.DuplicationException;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.mapper.SurveyCompanyMapper;
import com.zettamine.mpa.scm.repository.ISurveyCompanySearchCriteriaRepository;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyServiceAreaRepository;
import com.zettamine.mpa.scm.repository.SurveyServiceTypeRepository;
import com.zettamine.mpa.scm.services.ISurveyCompanyService;
import com.zettamine.mpa.scm.util.SurveyUtility;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SurveyCompanyServiceImpl implements ISurveyCompanyService {
	private SurveyCompanyRepository surveyCompanyRepo;
	private SurveyServiceTypeRepository surveyTypeRepository;
	private SurveyServiceAreaRepository serviceAreaRepository;
	private ISurveyCompanySearchCriteriaRepository searchCriteriaRepository;
	

	@Override
	public void createSurveyCompany(SurveyCompanyDto surveyCompanyDto) {
		SurveyCompany company = new SurveyCompany();
		if (surveyCompanyRepo.findByCompanyName(surveyCompanyDto.getCompanyName()).isPresent()) {
			throw new DuplicationException(
					"Survey Company already exists with name: " + surveyCompanyDto.getCompanyName());
		}
		if (surveyCompanyRepo.findByPhone(surveyCompanyDto.getPhone()).isPresent()) {
			throw new DuplicationException(
					"Survey Company already exists with Mobile Number: " + surveyCompanyDto.getPhone());
		}
		if (surveyCompanyRepo.findByEmail(surveyCompanyDto.getEmail()).isPresent()) {
			throw new DuplicationException(
					"Survey Company already exists with Email: " + surveyCompanyDto.getEmail());

		}
		
		List<SurveyType> surveyTypeList = new ArrayList<>();
		for(String surveyTypeName:surveyCompanyDto.getSurveyTypeNamesList()) {
			
			Optional<SurveyType> surveyType = surveyTypeRepository.findBySurveyTypeName(SurveyUtility.safeUpperCaseTrim(surveyTypeName));
			if(surveyType.isPresent()) {
				surveyTypeList.add(surveyType.get());
				
			}else {
				throw new ResourceNotFoundException("No Survey Type found with name " + surveyTypeName);
			}
		}
		company.setSurveyTypes(surveyTypeList);
		SurveyCompanyMapper.mapToSurveyCompany(surveyCompanyDto, company);
		surveyCompanyRepo.save(company);

	}

	@Override
	public SurveyCompanyDto getSurveyCompanyById(Integer surveyCompanyId) {
		SurveyCompany company = surveyCompanyRepo.findById(surveyCompanyId).orElseThrow(
				() -> new ResourceNotFoundException("Survey Company", "survey company ID", surveyCompanyId));
		SurveyCompanyDto surveyCompanyDto = new SurveyCompanyDto();
		SurveyCompanyMapper.mapToSurveyCompanyDto(company, surveyCompanyDto);

		return surveyCompanyDto;
	}

	@Override
	public void updateSurveyCompany(Integer surveyCompanyId, SurveyCompanyDto surveyCompanyDto) {
		if(surveyCompanyRepo.findById(surveyCompanyId).isPresent()) {
			if(surveyCompanyRepo.findByCompanyName(surveyCompanyDto.getCompanyName()).isPresent() && 
					(surveyCompanyRepo.findByCompanyName(surveyCompanyDto.getCompanyName()).get().getSurveyCompanyId() != surveyCompanyId)){
				throw new DuplicationException("Survey company already exists with name " + surveyCompanyDto.getCompanyName());
				
			}
			
			if(surveyCompanyRepo.findByPhone(surveyCompanyDto.getPhone()).isPresent() && 
					(surveyCompanyRepo.findByPhone(surveyCompanyDto.getPhone()).get().getSurveyCompanyId() != surveyCompanyId)){
				throw new DuplicationException("Survey company already exists with mobile number " + surveyCompanyDto.getPhone());
				
			}
			
			if(surveyCompanyRepo.findByEmail(surveyCompanyDto.getEmail()).isPresent() && 
					(surveyCompanyRepo.findByEmail(surveyCompanyDto.getEmail()).get().getSurveyCompanyId() != surveyCompanyId)){
				throw new DuplicationException("Survey company already exists with email " + surveyCompanyDto.getEmail());
				
			}
			
		}else {
			throw new ResourceNotFoundException("Survey Company", "survey company ID", surveyCompanyId);
		}
		
		SurveyCompany company = new SurveyCompany();
		List<SurveyType> surveyTypeList = new ArrayList<>();
		for(String surveyTypeName:surveyCompanyDto.getSurveyTypeNamesList()) {
			
			Optional<SurveyType> surveyType = surveyTypeRepository.findBySurveyTypeName(SurveyUtility.safeUpperCaseTrim(surveyTypeName));
			if(surveyType.isPresent()) {
				surveyTypeList.add(surveyType.get());
				
			}else {
				throw new ResourceNotFoundException("No Survey Type found with name " + surveyTypeName);
			}
		}
		company.setSurveyTypes(surveyTypeList);
		SurveyCompanyMapper.mapToSurveyCompany(surveyCompanyDto, company);
		company.setSurveyCompanyId(surveyCompanyId);

		surveyCompanyRepo.save(company);
				
	}

	
	@Override
	public List<SurveyCompanyDto> getCompaniesByCriteria(SurveyCompanySearchCriteriaDto searchCriteriaDto) {
		SurveyCompany surveyCompany= SurveyCompanyMapper.mapSearchCriteriaToSurveyCompany(searchCriteriaDto, new SurveyCompany());
		List<SurveyCompany> list = surveyCompanyRepo.findAll(Example.of(surveyCompany));
		return list.stream()
				            .map(company->SurveyCompanyMapper.mapToSurveyCompanyDto(company, new SurveyCompanyDto()))
				            .toList();
		
	}
	
//	//@Override
//	public List<SurveyCompanyDto> getCompaniesByServiceArea(SurveyCompanySearchCriteriaDto searchCriteriaDto) {
//		
//		List<SurveyCompany> list = surveyCompanyRepo.findByCompanyNameContaining(searchCriteriaDto.getName());
//        Set<SurveyCompany> result = new HashSet<>();
//        
//        
//        SurveyServiceArea area = new SurveyServiceArea();
//        area.setCity(searchCriteriaDto.getCity());
//        area.setState(searchCriteriaDto.getState());
//        area.setZipcode(searchCriteriaDto.getZipcode());
//        List<SurveyServiceArea> areas = serviceAreaRepository.findAll(Example.of(area));
//        
//        if(searchCriteriaDto.getName()==null && searchCriteriaDto.getState() ==null && searchCriteriaDto.getCity()==null && searchCriteriaDto.getZipcode()==null) {
//        	surveyCompanyRepo.findAll().forEach(com->result.add(com));
//        }
//        
//        if(searchCriteriaDto.getName()!=null && searchCriteriaDto.getState() ==null && searchCriteriaDto.getCity()==null && searchCriteriaDto.getZipcode()==null) {
//        	list.forEach(com->result.add(com));
//        }
//        
//        
//        if(list.size()>0 )
//        list.forEach(company->{
//        	areas.forEach(a->{
//        		if(a.getSurveyCompany().getSurveyCompanyId().equals(company.getSurveyCompanyId())){
//        			result.add(company);
//        		}
//        	});
//        });
//        else
//        {
//        	areas.forEach(a->{
//        		
//        			result.add(a.getSurveyCompany());
//        		});
//        }
//        
//		return result.stream()
//				.map(company->SurveyCompanyMapper.mapToSurveyCompanyDto(company, new SurveyCompanyDto()))
//				.toList();
//		
//	}

	@Override
	public List<SurveyCompanyDto> getAllCompanies() {
		return surveyCompanyRepo.findAll().stream()
				                          .map(company->SurveyCompanyMapper.mapToSurveyCompanyDto(company, new SurveyCompanyDto()))
				                          .toList();
	}
	
	
	@Override
    public List<SurveyCompanyDto> getAllCompaniesByServiceAreaCriteria(SurveyCompanySearchCriteriaDto searchCriteria) {
       
        if (searchCriteria.getName() == null && searchCriteria.getCity() == null && searchCriteria.getState() == null && searchCriteria.getZipcode() == null) {
            return getAllCompanies();
        }
        
       return searchCriteriaRepository.searchCompaniesByCriteria(searchCriteria);
       
    }


}
