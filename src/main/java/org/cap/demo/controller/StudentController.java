package org.cap.demo.controller;

import java.util.List;

import org.cap.demo.bo.StudentBO;
import org.cap.demo.dao.IStudentDao;
import org.cap.demo.exception.StudentWrongInfoException;
import org.cap.demo.healthcheck.HealthcheckEndpoint;
import org.cap.demo.model.StudentVO;
import org.cap.demo.modelDTO.StudentDTO;
import org.cap.demo.service.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app1")
public class StudentController {

	@Autowired
	private StudentBO studentDbService;
	
	@Autowired
	private StudentMapper studentmapper;
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@GetMapping("/students")
	public ResponseEntity<List<StudentVO>> getAllStudents(){
		
		
		List<StudentVO> students=studentmapper.toStudentVOs(studentDbService.getAllStudents());
		
		
		if(students==null || students.isEmpty())
			return new ResponseEntity("Sorry! No student available in DB!", 
					HttpStatus.NOT_FOUND);
		 logger.info("GetMapping: Data is retriving from database ");		
		return ResponseEntity.status(HttpStatus.OK).body(students);
	} 
	

	// githubdemo2

	@PostMapping("/students")
	public ResponseEntity <StudentVO> saveStudent(@Validated @RequestBody StudentVO student){
		
		StudentDTO students=studentmapper.toStudentDTO(student);
		studentDbService.saveStudent(students);
		if(students==null)
			return new ResponseEntity("student Details INsertion Error!", 
					HttpStatus.BAD_REQUEST);
		 logger.info("save Student in controller");
		return  ResponseEntity.status(HttpStatus.OK).body(student);
	}
	
	@GetMapping("/healthcheck")
	public ResponseEntity <HealthcheckEndpoint> healthcheck(){
		
		HealthcheckEndpoint haeHealthcheckEndpoint=studentDbService.healthcheck();
		 logger.info("PostMapping: Data is saving in database ");
		return  ResponseEntity.status(HttpStatus.OK).body(haeHealthcheckEndpoint);
	}
	
	@PostMapping("/healthcheckpost")
	public ResponseEntity<HealthcheckEndpoint> healthcheckp() {
		
		HealthcheckEndpoint haeHealthcheckEndpoint=studentDbService.healthcheckPost1();
		 logger.info("PostMapping: Data is saving in database ");
		return  ResponseEntity.status(HttpStatus.OK).body(haeHealthcheckEndpoint);
	}
	
	 @RequestMapping("/log") public String log()
	    {
	        logger.trace("Log level: TRACE");
	        logger.debug("Log level: DEBUG");
	        logger.error("Log level: ERROR");
	        logger.warn("Log level: WARN");
	  
	        return "Hey! You can check the output in the logs";
	    }
	 @GetMapping("/message")
		public String testMassage() {
			return "message from application 1--------";
		}
}
