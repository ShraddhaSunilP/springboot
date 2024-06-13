package com.luv2code.cruddemo;

import com.luv2code.cruddemo.Dao.StudentDao;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
        return runner ->{
			createStudent(studentDao);
		};
	}
	private void createStudent(StudentDao studentDao) {
		// Create student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");

		//save the student object
		System.out.println("Saving the student");
		studentDao.save(tempStudent);

		//display id of the saved student
		System.out.println("saved student. Generated id: " +tempStudent.getId());
	}
}
