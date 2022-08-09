package org.cap.demo.dao;

import java.util.List;

import org.cap.demo.bo.StudentBO;
import org.cap.demo.exception.StudentWrongInfoException;
import org.cap.demo.exception.TableNotAvailableException;
import org.cap.demo.healthcheck.HealthcheckEndpoint;
import org.cap.demo.model.StudentVO;
import org.cap.demo.modelDTO.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IStudentDao 
{
	public List<StudentVO> findAllStudents();
	public List<StudentVO> save(StudentVO student);
	HealthcheckEndpoint healthcheck() throws TableNotAvailableException;
	HealthcheckEndpoint healthcheckPost() throws StudentWrongInfoException;
}

