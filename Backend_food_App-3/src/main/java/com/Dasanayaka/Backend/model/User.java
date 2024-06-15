package com.Dasanayaka.Backend.model;

import com.Dasanayaka.Backend.dto.RestorentsDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "User")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passWord;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private List<Oder> oders = new ArrayList<>();

    @ElementCollection
    private List<RestorentsDto> favourites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFullName() {
        return fullName;
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

    public USER_ROLE getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }

    public List<RestorentsDto> getFavourites() {
        return favourites;
    }

    public Long getId() {
        return  id;
    }

    public List<Address> getAddress() {
        return  addresses;
    }
}
