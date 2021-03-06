package cl.people.example.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import cl.people.example.apirest.entities.Student;
import cl.people.example.apirest.repository.StudentRepository;
import cl.people.example.apirest.service.StudentService;

/**
 * @author Alex Águila
 * date 26-08-2020
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudent(Long studentId) {
        Student student = this.getStudentById(studentId);
        try {
            studentRepository.delete(student);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
        return student;
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public List<Student> getStudentsList() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student studentUpdate) {
        return studentRepository.save(studentUpdate);
    }
    
}