package com.parshuram.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parshuram.security.entity.Student;
import com.parshuram.security.exception.ApiResponse;
import com.parshuram.security.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/save")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		
		Student newStudent = studentService.addNewStudent(student);
		
		return new ResponseEntity<Student>(newStudent, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Integer id,@RequestBody Student student){
		
		Student updateStudent = studentService.updateStudent(id, student);
		
		return new ResponseEntity<Student>(updateStudent, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Integer id){
		
		studentService.deleteStduent(id);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted Successfully...", true),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudentDetails(){
		
		List<Student> allStudent = studentService.getAllStudent();
		
		return new ResponseEntity<List<Student>>(allStudent, HttpStatus.OK);
	}
	
	@GetMapping("/std/{id}")
	public ResponseEntity<Student> studentById(@PathVariable Integer id){
		
		Student studentById = studentService.getStudentById(id);
		
		return new ResponseEntity<Student>(studentById, HttpStatus.OK);
	}
	
	@GetMapping("/sname/{name}")
	public ResponseEntity<Student> studentByName(@PathVariable String name){
		
		Student studentByName = studentService.getStudentByName(name);
		
		return new ResponseEntity<Student>(studentByName, HttpStatus.OK);
		
	}
	
	@GetMapping("/scity/{city}")
	public ResponseEntity<List<Student>> studentByCity(@PathVariable String city){
		
		List<Student> studentByCity = studentService.getStudentByCity(city);
		
		return new ResponseEntity<List<Student>>(studentByCity, HttpStatus.OK);
	}
	
	@GetMapping("/state/{state}")
	public ResponseEntity<List<Student>> studentByState(@PathVariable String state){
		
		List<Student> studentByState = studentService.getStudentByState(state);
		
		return new ResponseEntity<List<Student>>(studentByState, HttpStatus.OK);
		
	}
}
