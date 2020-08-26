package cl.people.example.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import cl.people.example.apirest.entities.Course;
import cl.people.example.apirest.repository.CourseRepository;
import cl.people.example.apirest.service.CourseService;

/**
 * @author Alex Águila
 * date 25-08-2020
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course deleteCourse(Long courseId) {
        Course course = courseRepository.getOne(courseId);
        try {
            courseRepository.delete(course);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ResourceNotFoundException();
        }
        return course;
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getOne(courseId);
    }

    @Override
    public List<Course> getCoursesList() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long courseId, Course courseUpdate) {
        Course course = courseRepository.getOne(courseId);
        course.setName(courseUpdate.getName());
        course.setCode(courseUpdate.getCode());
        return courseRepository.save(course);
    }

    
    
}