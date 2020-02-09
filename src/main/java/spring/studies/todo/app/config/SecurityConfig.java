package spring.studies.todo.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import spring.studies.todo.app.service.UserService;
import spring.studies.todo.app.web.FlashMessage;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
                                    // Enable the Spring Security Filter Chain
                                    // This chain allow us filter certain requests so that user doesn't have to be authenticated (Ex: To access CSS)

    @Autowired
    private UserService userService;

    @Autowired
    public void configureGloblal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());; // Setting up our UserService to be used in the process of authentication;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

    //Requests that will require authentication and authorization


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Here is possible configure all the flow of requests as instance, configure the log out process, configure the logon page etc

        //Restricting access to the resources to only those user that are authorized to do so;
        http
            .authorizeRequests()
                .anyRequest().hasRole("USER") // "ROLE_USER"
                //Until here we implemented spring security, but all the access are restrict to those users that have the role USER.
                //Even the login page will be only available for those user. This is a problem as it prevents the login process.
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailureHandler())
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login")
                .and()
            .csrf();
    }

    /*
    * When request and responses are occurring, the server requests the user to set a cookie with a specific JSESSIONID.
    * The Browser automatically defines that cook and start using it for all request made for that domain.
    *
    * Each browser will have a unit JSESSIONID
    * */

    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> response.sendRedirect("/");
    }

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
            response.sendRedirect("/login");
        };
    }
}

