package com.zettamine.mpa.scm.mapper;

import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.entity.Surveyor;
import com.zettamine.mpa.scm.util.SurveyUtility;

/**
 * Mapper class for mapping between Surveyor entities and DTOs.
 */
public class SurveyorMapper {
	
    /**
     * Maps a Surveyor entity to a SurveyorDto.
     *
     * @param surveyor The Surveyor entity to be mapped.
     * @param dto The SurveyorDto to be populated with entity data.
     * @return The populated SurveyorDto.
     */
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

    /**
     * Maps a SurveyorDto to a Surveyor entity.
     *
     * @param dto The SurveyorDto to be mapped.
     * @param entity The Surveyor entity to be populated with DTO data.
     * @return The populated Surveyor entity.
     */
    public static Surveyor mapToSurveyor(SurveyorDto dto, Surveyor entity) {
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
