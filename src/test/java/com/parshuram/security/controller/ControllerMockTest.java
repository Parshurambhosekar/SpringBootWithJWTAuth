package com.parshuram.security.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.parshuram.security.entity.Student;
import com.parshuram.security.exception.ApiResponse;
import com.parshuram.security.service.StudentService;

@SpringBootTest
public class ControllerMockTest {

	@Mock
	private StudentService studentService;

	@InjectMocks
	private StudentController studentController;

	@Test
	public void test_getAllStudent() {

		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Sangli", "Maharashtra", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentService.getAllStudent()).thenReturn(students);

		ResponseEntity<List<Student>> responseEntity = studentController.getAllStudentDetails();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(4, responseEntity.getBody().size());

		assertEquals(students, responseEntity.getBody());
	}

	@Test
	public void test_saveStudent() {

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentService.addNewStudent(student)).thenReturn(student);

		ResponseEntity<Student> responseEntity = studentController.saveStudent(student);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		assertEquals(student, responseEntity.getBody());

	}

	@Test
	public void test_updateStudent() {

		Integer id = 1;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentService.updateStudent(id, student)).thenReturn(student);

		ResponseEntity<Student> responseEntity = studentController.updateStudent(id, student);

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

		assertEquals(id, responseEntity.getBody().getId());

		assertEquals(student.getState(), responseEntity.getBody().getState());

		assertEquals("Parshuram", responseEntity.getBody().getName());

		assertEquals("Sangli", responseEntity.getBody().getCity());

		assertEquals(student.getMobileNumber(), responseEntity.getBody().getMobileNumber());
	}

	@Test
	public void test_deleteStudentById() {

		Integer id = 1;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		studentService.deleteStduent(student.getId());

		ResponseEntity<ApiResponse> responseEntity = studentController.deleteStudent(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals("Deleted Successfully...", responseEntity.getBody().getMessage());
	}

	@Test
	public void test_studentById() {

		Integer id = 1;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentService.getStudentById(id)).thenReturn(student);

		ResponseEntity<Student> responseEntity = studentController.studentById(id);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(id, responseEntity.getBody().getId());
	}

	@Test
	public void test_studentByName() {

		String name = "Parshuram";

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentService.getStudentByName(name)).thenReturn(student);

		ResponseEntity<Student> responseEntity = studentController.studentByName(name);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(name, responseEntity.getBody().getName());
	}

	@Test
	public void test_studentByCity() {

		String city = "sangli";

		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Sangli", "Maharashtra", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentService.getStudentByCity(city)).thenReturn(students);

		ResponseEntity<List<Student>> responseEntity = studentController.studentByCity(city);

		List<Student> listOfCity = responseEntity.getBody().stream().filter(s -> s.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList());

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(3, listOfCity.size());
	}

	@Test
	public void test_studentByState() {

		String state= "telangana";
		
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Hydrabad", "Telangana", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentService.getStudentByState(state)).thenReturn(students);
		
		ResponseEntity<List<Student>> responseEntity = studentController.studentByState(state);
		
		List<Student> listOfState = responseEntity.getBody().stream().filter(s->s.getState().equalsIgnoreCase(state))
								.collect(Collectors.toList());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(2, listOfState.size());
	}

}
