package com.zettamine.mpa.scm.mapper;

import com.zettamine.mpa.scm.dto.SurveyServiceAreaDto;
import com.zettamine.mpa.scm.entity.SurveyServiceArea;
import com.zettamine.mpa.scm.util.SurveyUtility;

/**
 * Mapper class for mapping between SurveyServiceArea entities and DTOs.
 */
public class SurveyServiceAreaMapper {

    /**
     * Maps a SurveyServiceArea entity to a SurveyServiceAreaDto.
     *
     * @param serviceArea The SurveyServiceArea entity to be mapped.
     * @param serviceAreaDto The SurveyServiceAreaDto to be populated with entity data.
     * @return The populated SurveyServiceAreaDto.
     */
    public static SurveyServiceAreaDto mapToSurveyServiceAreaDto(SurveyServiceArea serviceArea, SurveyServiceAreaDto serviceAreaDto) {
        serviceAreaDto.setServiceAreaId(serviceArea.getServiceAreaId());
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

    /**
     * Maps a SurveyServiceAreaDto to a SurveyServiceArea entity.
     *
     * @param serviceArea The SurveyServiceArea entity to be mapped.
     * @param serviceAreaDto The SurveyServiceAreaDto to be populated with entity data.
     * @return The populated SurveyServiceArea entity.
     */
    public static SurveyServiceArea mpaToSurveyServiceArea(SurveyServiceArea serviceArea, SurveyServiceAreaDto serviceAreaDto) {
        serviceArea.setCity(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getCity()));
        serviceArea.setCounty(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getCounty()));
        serviceArea.setServiceAreaId(serviceAreaDto.getServiceAreaId());
        serviceArea.setState(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getState()));
        serviceArea.setZipcode(SurveyUtility.safeUpperCaseTrim(serviceAreaDto.getZipcode()));
        
        return serviceArea;
    }
}
