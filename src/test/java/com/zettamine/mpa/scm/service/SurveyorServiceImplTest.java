package com.zettamine.mpa.scm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.entity.SurveyCompany;
import com.zettamine.mpa.scm.entity.Surveyor;
import com.zettamine.mpa.scm.exception.DuplicationException;
import com.zettamine.mpa.scm.exception.ResourceNotFoundException;
import com.zettamine.mpa.scm.repository.SurveyCompanyRepository;
import com.zettamine.mpa.scm.repository.SurveyorRepository;
import com.zettamine.mpa.scm.services.impl.SurveyorServiceImpl;

public class SurveyorServiceImplTest {

    @Mock
    private SurveyorRepository surveyorRepository;

    @Mock
    private SurveyCompanyRepository surveyCompanyRepository;

    @InjectMocks
    private SurveyorServiceImpl surveyorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void testSaveSurveyor_Success() {
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        SurveyCompany surveyCompany = new SurveyCompany(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(surveyCompany));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByPhone(anyString())).thenReturn(Optional.empty());
//
//        assertDoesNotThrow(() -> surveyorService.saveSurveyor(surveyorDto));
//    }
//
//    @Test
//    public void testSaveSurveyor_SurveyCompanyNotFound() {
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> surveyorService.saveSurveyor(surveyorDto));
//    }
//
//    @Test
//    public void testSaveSurveyor_DuplicateLicenceId() {
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.of(new Surveyor()));
//
//        assertThrows(DuplicationException.class, () -> surveyorService.saveSurveyor(surveyorDto));
//    }
//
//    @Test
//    public void testSaveSurveyor_DuplicateEmail() {
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByEmail(anyString())).thenReturn(Optional.of(new Surveyor()));
//
//        assertThrows(DuplicationException.class, () -> surveyorService.saveSurveyor(surveyorDto));
//    }
//
//    @Test
//    public void testSaveSurveyor_DuplicatePhone() {
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByPhone(anyString())).thenReturn(Optional.of(new Surveyor()));
//
//        assertThrows(DuplicationException.class, () -> surveyorService.saveSurveyor(surveyorDto));
//    }
//
    @Test
    public void testFetchSurveyor_Success() {
        int surveyorId = 1;
        Surveyor surveyor = new Surveyor();
        when(surveyorRepository.findById(surveyorId)).thenReturn(Optional.of(surveyor));

        SurveyorDto fetchedSurveyor = surveyorService.fetchSurveyor(surveyorId);

        assertNotNull(fetchedSurveyor);
       
       
    }

    @Test
    public void testFetchSurveyor_NotFound() {
        int surveyorId = 1;
        when(surveyorRepository.findById(surveyorId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> surveyorService.fetchSurveyor(surveyorId));
    }

//    @Test
//    public void testUpdateSurveyor_Success() {
//        int surveyorId = 1;
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByPhone(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findById(surveyorId)).thenReturn(Optional.of(new Surveyor()));
//
//        assertDoesNotThrow(() -> surveyorService.updateSurveyor(surveyorDto, surveyorId));
//    }
//
//    @Test
//    public void testUpdateSurveyor_SurveyCompanyNotFound() {
//        int surveyorId = 1;
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> surveyorService.updateSurveyor(surveyorDto, surveyorId));
//    }
//
//    @Test
//    public void testUpdateSurveyor_DuplicateLicenceId() {
//        int surveyorId = 1;
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.of(new Surveyor()));
//
//        assertThrows(DuplicationException.class, () -> surveyorService.updateSurveyor(surveyorDto, surveyorId));
//    }
//
//    @Test
//    public void testUpdateSurveyor_DuplicateEmail() {
//        int surveyorId = 1;
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByEmail(anyString())).thenReturn(Optional.of(new Surveyor()));
//
//        assertThrows(DuplicationException.class, () -> surveyorService.updateSurveyor(surveyorDto, surveyorId));
//    }
//
//    @Test
//    public void testUpdateSurveyor_DuplicatePhone() {
//        int surveyorId = 1;
//        SurveyorDto surveyorDto = new SurveyorDto(/* provide necessary details */);
//        when(surveyCompanyRepository.findById(anyInt())).thenReturn(Optional.of(new SurveyCompany()));
//        when(surveyorRepository.findBySurveyorLicenceId(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//        when(surveyorRepository.findByPhone(anyString())).thenReturn(Optional.of(new Surveyor()));
//
//        assertThrows(DuplicationException.class, () -> surveyorService.updateSurveyor(surveyorDto, surveyorId));
//    }
//
//    @Test
//    public void testGetAllSurveyorsByCompanyId_Success() {
//        int surveyCompanyId = 1;
//        List<Surveyor> surveyors = new ArrayList<>();
//        // Add some surveyors to the list
//        SurveyCompany surveyCompany = new SurveyCompany(/* provide necessary details */);
//        surveyCompany.setSurveyors(surveyors);
//        when(surveyCompanyRepository.findById(surveyCompanyId)).thenReturn(Optional.of(surveyCompany));
//
//        List<SurveyorDto> fetchedSurveyors = surveyorService.getAllSurveyorsByCompanyId(surveyCompanyId);
//
//        assertNotNull(fetchedSurveyors);
//        // Add assertions to verify if the fetchedSurveyors match the expected surveyors
//    }
//
//    @Test
//    public void testGetAllSurveyorsByCompanyId_CompanyNotFound() {
//        int surveyCompanyId = 1;
//        when(surveyCompanyRepository.findById(surveyCompanyId)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> surveyorService.getAllSurveyorsByCompanyId(surveyCompanyId));
//    }
//
//    @Test
//    public void testGetAllSurveyors_Success() {
//        List<Surveyor> surveyors = new ArrayList<>();
//        // Add some surveyors to the list
//        when(surveyorRepository.findAll()).thenReturn(surveyors);
//
//        List<SurveyorDto> fetchedSurveyors = surveyorService.getAllSurveyors();
//
//        assertNotNull(fetchedSurveyors);
//        // Add assertions to verify if the fetchedSurveyors match the expected surveyors
//    }
}
