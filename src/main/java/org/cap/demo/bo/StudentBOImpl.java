package org.cap.demo.bo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.transaction.Transactional;

import org.cap.demo.dao.IStudentDao;
import org.cap.demo.dao.StudentDAOImpl;
import org.cap.demo.exception.StudentWrongInfoException;
import org.cap.demo.exception.TableNotAvailableException;
import org.cap.demo.healthcheck.HealthcheckEndpoint;
import org.cap.demo.model.StudentVO;
import org.cap.demo.modelDTO.StudentDTO;
import org.cap.demo.service.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("studentDbService")
public class StudentBOImpl implements StudentBO {

	
	@Autowired 
	private IStudentDao studentDbDao;
	
	@Autowired
	private StudentMapper studentmapper;
	
	Logger logger = LoggerFactory.getLogger(StudentBOImpl.class);
	@Transactional
	@Override
	public List<StudentDTO> getAllStudents() 
	{
	
		logger.info("GetAllStudents: students are  retriving from database in service layer ");	
		return studentmapper.toStudentDTOs(studentDbDao.findAllStudents());
	}
	
	@Transactional
	@Override
	public StudentDTO saveStudent(StudentDTO student) 
	{
	
		studentDbDao.save(studentmapper.toStudentVO(student));
		logger.info("Save: student data getting saved in database in service layer");
		return student;
	}
	
	@Transactional
	@Override
	public HealthcheckEndpoint healthcheck()  {
		
		HealthcheckEndpoint healthcheckEndpoint=studentDbDao.healthcheck();
		
		return healthcheckEndpoint;
	}

	@Override
	public HealthcheckEndpoint healthcheckPost1() {
		HealthcheckEndpoint healthcheckEndpoint= studentDbDao.healthcheckPost();
		
		return healthcheckEndpoint;
	
	
	}

	
}
