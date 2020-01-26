package spring.studies.todo.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring.studies.todo.app.model.User;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
}
