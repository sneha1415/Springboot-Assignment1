package org.cap.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.cap.demo.bo.StudentBO;
import org.cap.demo.model.StudentVO;
import org.cap.demo.modelDTO.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
 class StudentControllerTest {
	
	ObjectMapper obj = new ObjectMapper();
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	StudentBO studentDbService;
	
	@Test
	 void createStudentTest() throws Exception
	{
		
		StudentDTO student=new StudentDTO();
		student.setStudentId(1);
		student.setFirstName("Sneha");
		student.setLastName("khatkole");
		student.setMobileNo("12345");
		 String jsonRequest = obj.writeValueAsString(student);
	     when(studentDbService.saveStudent(any())).thenReturn(student);
	     mvc.perform(post("/app1/students").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)
	                .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
     
	}
	
	@Test
	 void getStudentsTest() throws Exception
	{
		StudentDTO student= new StudentDTO();
		student.setStudentId(1);
		student.setFirstName("Sneha");
		student.setLastName("khatkole");
		student.setMobileNo("12345");
		List<StudentDTO> student1= Arrays.asList(student);
	     when(studentDbService.getAllStudents()).thenReturn(student1);
	     mvc.perform(get("/app1/students")).andExpect(status().isOk());
	     assertEquals("Sneha", student.getFirstName());

	}

}
