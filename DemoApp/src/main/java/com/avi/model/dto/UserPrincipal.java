package com.avi.model.dto;

import com.avi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 6205086018505300790L;

    private User user;

    private Integer id;

    private String name;

    private String email;

    private String password;

    private Integer selected_office;

    private Integer selected_role;

    private Integer selected_sector;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new UserPrincipal(
                user,
                user.getEntityid(),
                user.getName_of_Entity(),
                user.getEmail(),
                user.getPassword(),
                user.getSelected_office(),
                user.getSelected_role(),
                user.getSelected_sector(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
