package cl.people.example.apirest.Utils;

public class RutValidationFunction {

    public static boolean validRut(int rut, char dv) {
        int m = 0;
        int s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }
    
}