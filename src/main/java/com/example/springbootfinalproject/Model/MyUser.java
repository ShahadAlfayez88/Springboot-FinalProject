package com.example.springbootfinalproject.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data
@AllArgsConstructor @Entity
@NoArgsConstructor
//
public class MyUser implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(10) not null unique")
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Pattern(regexp = "(Customer|Provider)", message = "Role must be in Customer or Service Provider only")
    @Column(columnDefinition = "varchar(10) not null check (role='Customer' or role='Provider')")
    private String role;
    @OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Customer customer;
    @OneToOne(mappedBy = "myUser", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    ServiceProvider serviceProvider;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}