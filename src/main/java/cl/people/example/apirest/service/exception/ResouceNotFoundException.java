package cl.people.example.apirest.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResouceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
}