package com.zettamine.mpa.scm.mapper;

import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.entity.SurveyServiceArea;
import com.zettamine.mpa.scm.util.SurveyUtility;



public class SurveyServiceAreaMapper {
	
	

	public static SurveyServiceAreaDto mapToSurveyServiceAreaDto(SurveyServiceArea serviceArea, SurveyServiceAreaDto serviceAreaDto) {
//		if() {
		serviceAreaDto.setServiceAreaId(serviceArea.getServiceAreaId());
//		}
//		
		serviceAreaDto.setCity(serviceArea.getCity());
		serviceAreaDto.setCounty(serviceArea.getCounty());
		serviceAreaDto.setState(serviceArea.getState());
		 if (serviceArea.getSurveyCompany() != null) {
		serviceAreaDto.setSurveyCompanyId(serviceArea.getSurveyCompany().getSurveyCompanyId());
		serviceAreaDto.setSurveyCompanyName(serviceArea.getSurveyCompany().getCompanyName());
		 }
		serviceAreaDto.setZipcode(serviceArea.getZipcode());
		
		return serviceAreaDto;
	}
	
	
	public static SurveyServiceArea mpaToSurveyServiceArea(SurveyServiceArea serviceArea, SurveyServiceAreaDto serviceAreaDto) {
		serviceArea.setCity(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getCity()));
		serviceArea.setCounty(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getCounty()));
		serviceArea.setServiceAreaId(serviceAreaDto.getServiceAreaId());
		serviceArea.setState(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getState()));
		serviceArea.setZipcode(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getZipcode()));
		//serviceArea.setSurveyCompany(companyRepository.findById(serviceAreaDto.getSurveyCompanyId()).get());
		
		
		return serviceArea;
	}
	
	
}
