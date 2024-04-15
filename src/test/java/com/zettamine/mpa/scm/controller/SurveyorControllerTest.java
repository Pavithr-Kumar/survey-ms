package com.zettamine.mpa.scm.controller;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zettamine.mpa.scm.constants.SurveyConstants;
import com.zettamine.mpa.scm.dto.SurveyorDto;
import com.zettamine.mpa.scm.services.ISurveyorService;


@AutoConfigureMockMvc
@SpringBootTest
public class SurveyorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ISurveyorService surveyorService;
    
    @InjectMocks
    private SurveyorController surveyorController;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(surveyorController).build();
	}

    @Test
   void testCreateSurveyor() throws Exception {
	   SurveyorDto surveyorDto = new SurveyorDto(null,"SMDJDkii99933","Ravi","Kumar","ravi@gmail.com","888-999-8768",true,true,1,"ABC Surveying Company");
	   
	   doNothing().when(surveyorService).saveSurveyor(surveyorDto);
	   
	   mockMvc.perform(post("/api/v1/survey/surveyor/create")
			   .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(surveyorDto)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.statusCode").value(SurveyConstants.STATUS_201))
			   .andExpect(jsonPath("$.statusMsg").value(SurveyConstants.SURVEYOR_SAVE_MSG));;
	   
	          
   }
    
    @Test
    void testFetchSurveyor() throws Exception {
    	Integer surveyorId=29;
    	 SurveyorDto surveyorDto = new SurveyorDto(29,"SMDJDkii99933","Ravi","Kumar","ravi@gmail.com","888-999-8768",true,true,1,"ABC Surveying Company");
    	when(surveyorService.fetchSurveyor(surveyorId)).thenReturn(surveyorDto);
    	
    	mockMvc.perform(get("/api/v1/survey/surveyor/fetch/{id}",29)
    			.contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	       // .andExpect()
    	        .andExpect(jsonPath("$.surveyorId").value( surveyorId));
    	
    }
    
    
    @Test
    void testUpdateSurveyor() throws Exception {
   	 SurveyorDto surveyorDto = new SurveyorDto(29,"SMDJDkii99933","Ravi","Kumar","ravi@gmail.com","888-999-8768",true,true,1,"ABC Surveying Company");
   	  doNothing().when(surveyorService).updateSurveyor(surveyorDto,29);
   	  
   	mockMvc.perform(put("/api/v1/survey/surveyor/update/{id}",29)
			.contentType(MediaType.APPLICATION_JSON)
   	        .content(objectMapper.writeValueAsString(surveyorDto)))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.statusCode").value(SurveyConstants.STATUS_200))
			   .andExpect(jsonPath("$.statusMsg").value(SurveyConstants.SURVEYOR_UPDATE_MSG));;
	      
	   
    }
    
    @Test
    void testGetAllSurveyors() throws Exception {
    	List<SurveyorDto> list = new ArrayList<SurveyorDto>();
    	SurveyorDto survDto1 = new SurveyorDto();
    	survDto1.setFirstName("Ravi");
    	SurveyorDto survDto2 = new SurveyorDto();
    	survDto2.setFirstName("Raju");
    	list.add(survDto1);
    	list.add(survDto2);
    	
    	
    	when(surveyorService.getAllSurveyors()).thenReturn(list);
    	
    	mockMvc.perform(get("/api/v1/survey/surveyor/fetch-all")
    			.contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$[1].firstName").value("Raju"))
    	        .andExpect(jsonPath("$[0].firstName").value("Ravi"));
    	
    	
    	
    }
    
    @Test
   void testGetAllSurveyorsByCompany() throws Exception{
    	Integer companyId=1;
    	List<SurveyorDto> list = new ArrayList<SurveyorDto>();
    	SurveyorDto survDto1 = new SurveyorDto();
    	survDto1.setSurveyCompanyId(companyId);;	
        survDto1.setFirstName("Ravi");
    	SurveyorDto survDto2 = new SurveyorDto();
    	survDto2.setSurveyCompanyId(1);;	
    	survDto2.setFirstName("Raju");
    	list.add(survDto1);
    	list.add(survDto2);
    	when(surveyorService.getAllSurveyorsByCompanyId(companyId)).thenReturn(list);
    	
    	mockMvc.perform(get("/api/v1/survey/surveyor/fetch/company/{id}",1)
    			.contentType(MediaType.APPLICATION_JSON))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$[1].firstName").value("Raju"))
    	        .andExpect(jsonPath("$[0].firstName").value("Ravi"));
    	
    }
    
    
    
    
}
