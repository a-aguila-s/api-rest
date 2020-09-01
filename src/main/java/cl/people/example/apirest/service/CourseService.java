package cl.people.example.apirest.service;

import java.util.List;

import cl.people.example.apirest.entities.Course;

/**
 * @author Alex Águila
 * date 25-08-2020
 */
public interface CourseService {

    public List<Course> getCoursesList();

    public Course getCourseById(Long courseId);

    public Course createCourse(Course course);

    public Course updateCourse(Course courseUpdate);

    public Course deleteCourse(Long courseId);
    
}