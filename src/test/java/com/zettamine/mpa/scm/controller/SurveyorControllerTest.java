package com.zettamine.mpa.scm.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	   SurveyorDto surveyorDto = new SurveyorDto(29292,"SMDJDkii99933","Ravi","Kumar","ravi@gmail.com","888-999-8768",true,true,1,"ABC Surveying Company");
	   
	   doNothing().when(surveyorService).saveSurveyor(any(SurveyorDto.class));
	   
	   
	   
	   mockMvc.perform(post("/api/v1/survey/surveyor/create")
			   .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(surveyorDto)))
               .andExpect(status().isCreated());
	   
	          
   }
}
