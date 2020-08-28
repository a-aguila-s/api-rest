package cl.people.example.apirest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cl.people.example.apirest.Utils.RutValidationFunction;

@SpringBootTest
public class ValidRutFunctionTest {

    @Test
    void testValidRutFunction() {
        boolean result = RutValidationFunction.validRut(18298730, '1');
        Assertions.assertTrue(result);
    }
    
}