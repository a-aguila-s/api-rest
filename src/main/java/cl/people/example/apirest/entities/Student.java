package cl.people.example.apirest.entities;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * @author Alex Águila
 * date 23-08-2020
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 7214439658586554850L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstudent")
    private Long idStudent;
    @Column(name = "rut")
    private String rut;
    @Column(name = "name")
    private String name;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    @Min(19)
    private int age;
    @ManyToOne
    @JoinColumn(name="courseId", referencedColumnName = "idcourse")
    private Course course;

    public Student() {
        //ALL public empty construct
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

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