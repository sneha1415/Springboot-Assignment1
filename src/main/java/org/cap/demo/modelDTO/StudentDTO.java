package org.cap.demo.modelDTO;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


public class StudentDTO {
	
	
	
		private int studentId;
		private String firstName;
		private String lastName;
		private String mobileNo;
		
		
		public StudentDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		

		
		public StudentDTO(int studentId, String firstName, String lastName, String mobileNo) {
			super();
			this.studentId = studentId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.mobileNo = mobileNo;
		}

		public int getStudentId() {
			return studentId;
		}

		public void setStudentId(int studentId) {
			this.studentId = studentId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		

		@Override
		public String toString() {
			return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNo="
					+ mobileNo +"]";
		}
				
		
	}



