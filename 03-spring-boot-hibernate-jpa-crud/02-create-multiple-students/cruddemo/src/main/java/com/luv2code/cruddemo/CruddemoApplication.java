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
//			createStudent(studentDao);
			createMultipleStudents(studentDao);
		};
	}

	private void createMultipleStudents(StudentDao studentDao) {
		//create multiple students
		System.out.println("Creating 3 student object ...");
		Student tempStudent1 = new Student("Paul", "Doe", "paul@gmail.com");
		Student tempStudent2 = new Student("Akshda", "Pawar", "akshada@gmail.com");
		Student tempStudent3 = new Student("Mahesh", "Patil", "mahesh@gmail.com");

		// save the student objects
		System.out.println("Saving the student");
		studentDao.save(tempStudent1);
		studentDao.save(tempStudent2);
		studentDao.save(tempStudent3);
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
