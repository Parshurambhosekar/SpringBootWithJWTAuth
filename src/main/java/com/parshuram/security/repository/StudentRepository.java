package com.parshuram.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parshuram.security.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	public List<Student> findByCity(String city);

}
