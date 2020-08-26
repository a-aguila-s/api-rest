package cl.people.example.apirest.controller;

import org.springframework.web.bind.annotation.RestController;

import cl.people.example.apirest.entities.Course;
import cl.people.example.apirest.entities.dto.CourseDto;
import cl.people.example.apirest.service.CourseService;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Alex Águila
 * date 25-08-2020
 */
@RestController
@Api(value = "Course", description = "Operations over Course objects")
public class CourseController {

    public static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    //Get List
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Returns a paginated list of existing courses", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "Not Content"),
        @ApiResponse(code = 400, message = "Client Errors"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCoursesList(@RequestHeader Map<String, String> headers) {
        List<Course> course = courseService.getCoursesList();
        return course.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok(course);
    }

    //Course By Id
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Returns the course identified by the given id", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@RequestHeader Map<String, String> headers, @PathVariable("id") Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return course == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(course);
    }

    //Create Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Create a course with the data provided in the json body request", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestHeader Map<String, String> headers, 
                                                    @RequestBody @Valid CourseDto courseDto) {                                                        
        Course newCourse = new Course();
        newCourse.setName(courseDto.getName());
        newCourse.setCode(courseDto.getCode());
        Course courseCreated = courseService.createCourse(newCourse);
        return courseCreated == null ? ResponseEntity.badRequest().build() :
                ResponseEntity.ok(courseCreated);
    }

    //Update Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Update a course with the given id and the body request json", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@RequestHeader Map<String, String> headers, 
                                                    @PathVariable("id") Long courseId,
                                                    @RequestBody @Valid CourseDto courseDto) {                                                        
        Course course = courseService.getCourseById(courseId);
        if (course == null) return ResponseEntity.notFound().build();
        course.setName(courseDto.getName());
        course.setCode(courseDto.getCode());
        Course courseUpdated = courseService.createCourse(course);
        return courseUpdated == null ? ResponseEntity.badRequest().build() :
                ResponseEntity.ok(courseUpdated);
    }

    //Delete Course
    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "Delete a course with the given id", response = Course.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Errors")
    })
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@RequestHeader Map<String, String> headers, 
                                                    @PathVariable("id") Long courseId) {                                                        
        Course course = courseService.deleteCourse(courseId);
        return course == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(course);
    }
    
}