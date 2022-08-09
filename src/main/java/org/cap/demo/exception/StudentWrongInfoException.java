package org.cap.demo.exception;

public class StudentWrongInfoException extends RuntimeException {
	public  StudentWrongInfoException(String message) {
		super("Student Information is not valid");
		
	}

}
