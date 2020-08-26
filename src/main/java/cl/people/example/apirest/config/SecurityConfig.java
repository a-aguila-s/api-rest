package cl.people.example.apirest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author Alex Águila date 26-08-2020
 * @deprecated
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Deprecated(forRemoval = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.user}")
    private String user;

    @Value("${spring.security.user.password}")
    private String user_pwd;

    @Value("${spring.security.admin}")
    private String admin;

    @Value("${spring.security.admin.password}")
    private String admin_pwd;

    @Value("${spring.security.role_user}")
    private String role_user;

    @Value("${spring.security.role_admin}")
    private String role_admin;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(user).password(user_pwd).roles(role_user)
                .and()
                .withUser(admin).password(admin_pwd).roles(role_admin);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests().anyRequest().authenticated();
    }

    
    
}