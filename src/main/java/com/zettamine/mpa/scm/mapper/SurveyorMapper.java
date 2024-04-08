package com.zettamine.mpa.scm.mapper;

import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.entity.Surveyor;
import com.zettamine.mpa.scm.util.SurveyUtility;



public class SurveyorMapper {
	
	
	 public static SurveyorDto mapToSurveyorDto(Surveyor surveyor, SurveyorDto dto) {
	        
	        dto.setSurveyorId(surveyor.getSurveyorId());
	        dto.setSurveyorLicenceId(surveyor.getSurveyorLicenceId());
	        dto.setFirstName(SurveyUtility.safeUpperCaseTrim(surveyor.getFirstName()));
	        dto.setLastName(SurveyUtility.safeUpperCaseTrim(surveyor.getLastName()));
	        dto.setEmail(surveyor.getEmail());
	        dto.setPhone(surveyor.getPhone());
	        dto.setGeodeticControl(surveyor.getGeodeticControl());
	        dto.setHistoricPreservation(surveyor.getHistoricPreservation());
	        if (surveyor.getSurveyCompany() != null) {
	            dto.setSurveyCompanyId(surveyor.getSurveyCompany().getSurveyCompanyId());
	            dto.setSurveyCompanyName(surveyor.getSurveyCompany().getCompanyName());
	        }
	        return dto;
	    }

	    public static Surveyor mapToSurveyor(SurveyorDto dto,Surveyor entity) {
	        
	        entity.setSurveyorId(dto.getSurveyorId());

	        entity.setSurveyorLicenceId(SurveyUtility.safeUpperCaseTrim(dto.getSurveyorLicenceId().toUpperCase()));
	        entity.setFirstName(SurveyUtility.safeUpperCaseTrim(dto.getFirstName().toUpperCase()));
	        entity.setLastName(SurveyUtility.safeUpperCaseTrim(dto.getLastName().toUpperCase()));
	        entity.setEmail(SurveyUtility.safeUpperCaseTrim(dto.getEmail().toUpperCase()));
	        entity.setPhone(dto.getPhone());
	        entity.setGeodeticControl(dto.getGeodeticControl());
	        entity.setHistoricPreservation(dto.getHistoricPreservation());
	        
	     

	        return entity;
	    }

}
