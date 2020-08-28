package cl.people.example.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Alex Águila date 27-08-2020
 * @deprecated
 */
@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@EntityScan(basePackages = {"cl.people.example.apirest.entities"})
@Deprecated(forRemoval = false)
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

}
