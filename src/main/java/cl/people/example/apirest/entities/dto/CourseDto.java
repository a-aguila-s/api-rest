package cl.people.example.apirest.entities.dto;

import java.io.Serializable;

/**
 * @author Alex Águila date 26-08-2020
 */
public class CourseDto implements Serializable {

    private static final long serialVersionUID = 7263940273369292029L;
    
    private String name;
    private String code;

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
    
}