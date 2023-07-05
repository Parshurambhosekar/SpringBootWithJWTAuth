package com.parshuram.security.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.parshuram.security.entity.Student;
import com.parshuram.security.exception.ResourceNotFoundException;
import com.parshuram.security.repository.StudentRepository;
import com.parshuram.security.serviceimpl.StudentServiceImpl;

@SpringBootTest
public class ServiceMockTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentServiceImpl studentServiceImpl;

	@Test
	public void test_saveStudent() {

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentRepository.save(student)).thenReturn(student);

		assertEquals(student, studentServiceImpl.addNewStudent(student));
	}

	@Test
	public void test_updateStudent() {

		Integer id = 1;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentRepository.findById(id)).thenReturn(Optional.ofNullable(student));

		when(studentRepository.save(student)).thenReturn(student);

		assertEquals(id, studentServiceImpl.updateStudent(id, student).getId());
		
	}

	@Test
	public void test_deleteStudent() {

		Integer id = 1;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentRepository.existsById(student.getId())).thenReturn(true);

		studentServiceImpl.deleteStduent(id);

		verify(studentRepository, times(1)).deleteById(id);
	}

	@Test
	public void test_getAllStudent() {

		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Sangli", "Maharashtra", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentRepository.findAll()).thenReturn(students);

		assertEquals(4, studentServiceImpl.getAllStudent().size());

		assertEquals(students, studentServiceImpl.getAllStudent());
	}

	@Test
	public void test_getStudentById() {

		Integer id = 1;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);

		when(studentRepository.findById(id)).thenReturn(Optional.of(student));
	
		assertEquals(1, studentServiceImpl.getStudentById(id).getId());
	}

	@Test
	public void test_getStudentByName() {

		String name = "Parshuram";

		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Sangli", "Maharashtra", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentRepository.findAll()).thenReturn(students);

		assertEquals(name, studentServiceImpl.getStudentByName(name).getName());
	}

	@Test
	public void test_getStudentByCity() {

		String city = "sangli";

		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Sangli", "Maharashtra", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentRepository.findByCity(city)).thenReturn(students);
		
		List<Student> studentByCity = studentServiceImpl.getStudentByCity(city);
		
		List<Student> listOfCity = studentByCity.stream().filter(s->s.getCity().equalsIgnoreCase(city))
							.collect(Collectors.toList());
		
		assertEquals(3, listOfCity.size());	
	}
	
	@Test
	public void test_getStudentByState() {
		
		String state = "Telangana";

		List<Student> students = new ArrayList<Student>();
		students.add(new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l));
		students.add(new Student(2, "Sushil", "Sangli", "Maharashtra", 7276624426l));
		students.add(new Student(3, "Atharva", "Sangli", "Maharashtra", 9307304360l));
		students.add(new Student(4, "Dominic", "Hydrabad", "Telangana", 9881913245l));

		when(studentRepository.findAll()).thenReturn(students);

		List<Student> studentByState = studentServiceImpl.getStudentByState(state);
		
		List<Student> listOfState = studentByState.stream().filter(s->s.getState().equalsIgnoreCase(state))
						.collect(Collectors.toList());
		
		assertEquals(1, listOfState.size());	
	}
	
	@Test
	public void test_updateStudentExceptionCheck() {
		
		Integer id = 4;

		Student student = new Student(1, "Parshuram", "Sangli", "Maharashtra", 9623771726l);
		
		when(studentRepository.findById(student.getId())).thenThrow(ResourceNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, ()->studentServiceImpl.updateStudent(id, student));
		
		
	}
	
	
	
	

}
