package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner ->{
//			createCourseAndReviews(appDAO);
//			retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		int theId = 10;

		System.out.println("Deleting course id : "+theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");

	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		//get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());

		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman - How to score one million points ");
		// add some reviews
		tempCourse.addReview(new Review("Great course,... loved it!"));
		tempCourse.addReview(new Review("Cool course,... job well done."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println("tempCourse");
		System.out.println(tempCourse.getReviews());

		appDAO.save(tempCourse);
		System.out.println("Done!");
	}


	private void deleteCourse(AppDAO appDAO) {
		int theId=10;
		System.out.println("Deleting course id : "+theId);

		appDAO.deleteCourseById(theId);

		System.out.println("Done!");
	}


	private void updateCourse(AppDAO appDAO) {
		//define course Id
		int theId = 10;

		// find the instructor
		System.out.println("Finding Course id : "+theId);
		Course tempCourse = appDAO.findCourseById(theId);

		// update the course
		System.out.println("Updating course id : " +theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		appDAO.update(tempCourse);
		System.out.println("done!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		// find the instructor
		System.out.println("Finding  instructor id : "+theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		//update the instructor
		System.out.println("Update instructor id : "+theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding  instructor id : "+theId);

		// The code will still retrieve instructor AND courses because
		// we added JOIN FETCH in our query(daoimpl)
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor : "+tempInstructor);
		System.out.println("The associated courses : "+tempInstructor.getCourses());

		System.out.println("Done!");
	}


	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		//find instructor
		System.out.println("Finding instructor id "+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding course for instructor id : "+ theId);
		List<Course> courses = appDAO.findCoursesByInstruct(theId);

		tempInstructor.setCourses(courses);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorwithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding instructor id "+theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: "+tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the instructor
		Instructor tempInstructor=
				new Instructor("Pradhya", "Mahajan","pradhya@gmail.com");

		//create the instructor detail
		InstructorDetail tempInstructorDeatil =
				new InstructorDetail("http://www.youtube.com",
						"Singing !!!");

		// associated the objects
		tempInstructor.setInstructorDetail(tempInstructorDeatil);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		// add those courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		///save the instructor
		// NOTE : this will ALSO save the courses
		// because of  CascadeType.PERSIST
		System.out.println("Saving instructor: "+tempInstructor);
		System.out.println("The Courses: "+tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting instructor id : "+ theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		System.out.println("tempInstructorDetail : "+tempInstructorDetail);
		System.out.println("the associated instructor : "+tempInstructorDetail.getInstructor());
	}

	// delete
	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id : "+ theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");

	}

	// find_by_id
	private void findInstructor(AppDAO appDAO) {

		int theId = 2;
		System.out.println("Finding instructor id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: "+tempInstructor);
		System.out.println("the associated instructorDetail only : "+ tempInstructor.getInstructorDetail());
	}

	// create
	private void createInstructor(AppDAO appDAO){

		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chand", "Darby", "darby@luv2code.com");

		//create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code !!!");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		System.out.println("Saving instructor : "+ tempInstructor);
		appDAO.save(tempInstructor);
	}

}




