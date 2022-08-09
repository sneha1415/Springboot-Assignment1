package org.cap.demo.service;

import java.util.List;

import org.cap.demo.bo.StudentBO;
import org.cap.demo.bo.StudentBOImpl;
import org.cap.demo.model.StudentVO;
import org.cap.demo.modelDTO.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper 
{
		Logger logger = LoggerFactory.getLogger("mapper is mapping vo to sto");
		
		StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);
		List<StudentVO> toStudentVOs(List<StudentDTO> list);
	    StudentDTO toStudentDTO(StudentVO students);
		StudentVO toStudentVO(StudentDTO student1);
		List<StudentDTO> toStudentDTOs(List<StudentVO> list);
		
	    
}