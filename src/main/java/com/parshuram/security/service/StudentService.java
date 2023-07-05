package com.parshuram.security.service;

import java.util.List;

import com.parshuram.security.entity.Student;

public interface StudentService {
	
	public Student addNewStudent(Student student);
	
	public Student updateStudent(Integer id,Student student);
	
	public void deleteStduent(Integer id);
	
	public List<Student> getAllStudent();
	
	public Student getStudentById(Integer id);
	
	public Student getStudentByName(String name);
	
	public List<Student> getStudentByCity(String city);
	
	public List<Student> getStudentByState(String state);

}
