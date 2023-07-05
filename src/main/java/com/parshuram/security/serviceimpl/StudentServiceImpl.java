package com.parshuram.security.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parshuram.security.entity.Student;
import com.parshuram.security.exception.ResourceNotFoundException;
import com.parshuram.security.repository.StudentRepository;
import com.parshuram.security.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student addNewStudent(Student student) {
		
		Student saveStudent = studentRepository.save(student);
		
		return saveStudent;
	}

	@Override
	public Student updateStudent(Integer id, Student student) {
		
		Student std = studentRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Student", "StudentId", id));
		
		std.setId(student.getId());
		std.setName(student.getName());
		std.setCity(student.getCity());
		std.setState(student.getState());
		std.setMobileNumber(student.getMobileNumber());
	
		Student updatedStudent = studentRepository.save(std);
		
		return updatedStudent;
	}

	@Override
	public void deleteStduent(Integer id) {
		
		if(studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("Student","StudentId", id);
		}
	}

	@Override
	public List<Student> getAllStudent() {
		
		List<Student> allStudentDetails = studentRepository.findAll();
		
		return allStudentDetails;
	}

	@Override
	public Student getStudentById(Integer id) {

		Student student = studentRepository.findById(id)
						.orElseThrow(()->new ResourceNotFoundException("Student", "StudentId", id));
		
		
		return student;
	}

	@Override
	public Student getStudentByName(String name) {

		List<Student> listOfStudents = studentRepository.findAll();
		
		Student student = listOfStudents.stream().filter(s->s.getName().equalsIgnoreCase(name))
							.findAny()
							.orElseThrow(()->new ResourceNotFoundException("Student", "Name", name));
		
		return student;		
	}

	@Override
	public List<Student> getStudentByCity(String city) {

		List<Student> byCity = studentRepository.findByCity(city);
		
		if(byCity.isEmpty()) {
			throw new ResourceNotFoundException("Student", "City", city);
		}
		
		return byCity;
	}

	@Override
	public List<Student> getStudentByState(String state) {
		
		List<Student> listOfStudents = studentRepository.findAll();
		
		List<Student> list = listOfStudents.stream().filter(s->s.getState().equalsIgnoreCase(state))
						.collect(Collectors.toList());

		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Student", "State", state);
		}
		
		return list;
	}

	
}
