package com.luv2code.cruddemo.Dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDao {
     void save(Student theStudent);
     Student findById(Integer id);
     List<Student> findAll();
     List<Student> findByLastName(String theLstName);
     void update(Student theStudent);
     void delete(Integer id);
     int deleteAll();

}
