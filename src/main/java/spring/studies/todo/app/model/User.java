package spring.studies.todo.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    //By implementing UserDatails spring will recognize this user class as one to integrate with Spring Security

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 8, max = 20)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        //TODO What are the best implementation for this method -> isAccountNonExpired
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //TODO What are the best implementation for this method -> isAccountNonLocked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //TODO What are the best implementation for this method -> isCredentialsNonExpired
        return true;
    }

    @Override
    public boolean isEnabled() {
        //TODO What are the best implementation for this method -> isCredentialsNonExpired
        return true;
    }

}
