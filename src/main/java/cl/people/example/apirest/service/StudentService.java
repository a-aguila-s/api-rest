package cl.people.example.apirest.service;

import cl.people.example.apirest.entities.*;

import java.util.List;

/**
 * @author Alex Águila
 * date 25-08-2020
 */
public interface StudentService {

    public List<Student> getStudentsList();

    public Student getStudentById(Long studentId);

    public Student createStudent(Student student);

    public Student updateStudent(Student studentUpdate);

    public Student deleteStudent(Long studentId);
    
}