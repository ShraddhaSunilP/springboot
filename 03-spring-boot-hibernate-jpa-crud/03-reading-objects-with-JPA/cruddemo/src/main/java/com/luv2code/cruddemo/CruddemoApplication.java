package com.luv2code.cruddemo;

import com.luv2code.cruddemo.Dao.StudentDao;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.ListResourceBundle;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDao studentDao){
        return runner ->{
//			createStudent(studentDao);           //create
			createMultipleStudents(studentDao);  // create mutliple students
//			readStudent(studentDao);             // read students
//			queryForStudents(studentDao);        // List of all students
//			queryForStudentsByLastName(studentDao);
//			updateStudent(studentDao);
//			deleteStudent(studentDao);
//			deleteAllStudents(studentDao);
			
		};
	}

	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDao.deleteAll();
		System.out.println("Deleted row count: "+numRowsDeleted);

	}

	private void deleteStudent(StudentDao studentDao) {
		int studentId = 3;
		System.out.println("Deleteing student id: "+studentId);
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDao studentDao) {
		// Retrive student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: "+studentId);
		Student myStudent = studentDao.findById(studentId);

		//change first name to "Scooby"
		System.out.println("Updating student...");
		myStudent.setFirstName("Scooby");
		studentDao.update(myStudent);

		//update the student
		studentDao.update(myStudent);

		// dispaly the update student
		System.out.println("Updated student: "+myStudent);
	}

	private void queryForStudentsByLastName(StudentDao studentDao) {
		// get a list of students
		List<Student> theStudents = studentDao.findByLastName("Patil");

		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDao studentDao) {
		// get a list of students
		List<Student> theStudents = studentDao.findAll();
		//display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDao studentDao) {
		//create student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul", "Doe", "paul@gmail.com");

		//save the student
		System.out.println("Saving the student");
		studentDao.save(tempStudent);

		//display id or the saved student
		int theId = tempStudent.getId();
		System.out.println("saved student. Generated id: " +theId);

		//retrieve student based on the id: primary key
		System.out.println("Retriving student with id: " +theId);
		Student myStudent = studentDao.findById(theId);

		//display student
		System.out.println("Found the student : "+myStudent);

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
