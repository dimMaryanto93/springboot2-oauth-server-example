package com.tabeldata.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserDetails implements UserDetails {

    private final static Logger console = LoggerFactory.getLogger(SecurityUserDetails.class);

    private final User user;

    public SecurityUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        List<GroupUserRoles> groups = this.user.getGroups();
        for (GroupUserRoles userRole : groups) {
            GroupRoles group = userRole.getGroup();
            List<GroupRolesManagement> roles = group.getRoles();
            for (GroupRolesManagement role : roles) {
                list.add(new SimpleGrantedAuthority(role.getRole().getName()));
            }
        }
        console.info("username: {}, roles: {}", user.getUsername(), list);
        return list;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
