package cl.people.example.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.people.example.apirest.entities.Course;

/**
 * @author Alex Águila
 * date 23-08-2020
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}