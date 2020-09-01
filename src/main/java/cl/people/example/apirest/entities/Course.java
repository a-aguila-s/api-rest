package cl.people.example.apirest.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;

/**
 * @author Alex Águila
 * date 23-08-2020
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = -1385957604059841158L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcourse")
    private Long idCourse;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    @Max(4)
    private String code;
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Student> studentsList;

    public Course() {
        //ALL public empty construct 
    }

    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Student> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    
    
}