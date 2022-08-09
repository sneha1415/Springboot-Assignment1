package org.cap.demo.dao;

import java.util.List;

import org.cap.demo.controller.StudentController;
import org.cap.demo.exception.StudentWrongInfoException;
import org.cap.demo.exception.TableNotAvailableException;
import org.cap.demo.healthcheck.HealthcheckEndpoint;
import org.cap.demo.model.StudentVO;
import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
@Repository("studentDbDao")
public class StudentDAOImpl implements IStudentDao {

	@Autowired  
	JdbcTemplate jdbcTemplate1;  
	@Autowired  
	private NamedParameterJdbcTemplate jdbcTemplate;  
	Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);
	@Override
	
	public List<StudentVO> findAllStudents() {
		String query1="select * from studentvo"; 
		logger.info("FindAllStudents: students are  retriving from database in dao layer");	
		return jdbcTemplate1.query(query1,(rs, rowNum) ->
        new StudentVO(
                rs.getInt("studentid"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("mobilenumber") ));
		}

	@Override
	public List<StudentVO> save(StudentVO student) {
		 String query="insert into studentvo values('"+student.getStudentId()+"','"+student.getFirstName()+"','"+student.getLastName()+"','"+student.getMobileNo()+"')";  
		 jdbcTemplate1.update(query);
		 logger.info("Save: student data getting saved in database in dao layer");	
		return findAllStudents();
		
				  
		
	}
	@Override
	public HealthcheckEndpoint healthcheck() throws TableNotAvailableException{
		logger.debug("Begining of table availablity healthcheck");
		 HealthcheckEndpoint healthcheckEndpoint= new HealthcheckEndpoint();
		healthcheckEndpoint.setSystemName("Check Table is available or not");
		 try
		 {
			if(findAllStudents()!=null)
			{
				healthcheckEndpoint.setSystemCode("Success");
				healthcheckEndpoint.setSystemDescription("Table is available in database");
			}
			 


		 }
		 catch(BadSqlGrammarException ex)
		 {	
			 healthcheckEndpoint.setSystemCode("Failure");
			 healthcheckEndpoint.setSystemDescription("Table is not available in the database");
			 logger.error("Exception in property loading healthcheck");
		 }
		
		return healthcheckEndpoint;
	}
	@Override
	public HealthcheckEndpoint healthcheckPost() throws StudentWrongInfoException {
		StudentVO studentvo = new StudentVO() ;
		logger.debug("Begining of student information healthcheck");
		 HealthcheckEndpoint healthcheckEndpoint= new HealthcheckEndpoint();
		healthcheckEndpoint.setSystemName("Check student informaton");
		
				 
		 try
		 
		 {
			 studentvo.setFirstName(" ");
			 studentvo.setLastName("tom");
			 studentvo.setMobileNo("876987");
			 studentvo.setStudentId(2);
			 List<StudentVO> studentvo1=save(studentvo);
			
			if(studentvo1!=null)
			{
				healthcheckEndpoint.setSystemCode("Success");
				healthcheckEndpoint.setSystemDescription("Student Data is successfully posted in database");
			}
			 


		 }
		 catch(BadSqlGrammarException ex)
		 {	
			 healthcheckEndpoint.setSystemCode("Failure");
			 healthcheckEndpoint.setSystemDescription("Invalid Data.Student Data is not posted in database");
			 logger.error("Exception in property loading healthcheck");
		 }
		
		return healthcheckEndpoint;
	}
	

}
