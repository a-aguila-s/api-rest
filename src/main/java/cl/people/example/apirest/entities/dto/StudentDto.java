package cl.people.example.apirest.entities.dto;

import cl.people.example.apirest.entities.Course;

/**
 * @author Alex Águila date 26-08-2020
 */
public class StudentDto {

    private String rut;
    private String name;
    private String lastName;
    private int age;
    private Course course;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    
}