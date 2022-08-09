package org.cap.demo.bo;

import java.util.List;

import org.cap.demo.exception.StudentWrongInfoException;
import org.cap.demo.exception.TableNotAvailableException;
import org.cap.demo.healthcheck.HealthcheckEndpoint;
import org.cap.demo.model.StudentVO;
import org.cap.demo.modelDTO.StudentDTO;
import org.springframework.stereotype.Service;

public interface StudentBO
{
	 public List<StudentDTO> getAllStudents();

	List<StudentDTO> saveStudent(StudentDTO student);

	HealthcheckEndpoint healthcheck();

	
	
	public HealthcheckEndpoint healthcheckPost1() ;
}
