package com.example.note.services;

import com.example.note.accessingdatarest.AuthorityRepository;
import com.example.note.accessingdatarest.CustomUserDetailsRepository;
import com.example.note.entities.UsersEntity;
import com.example.note.entities.AuthoritiesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsServices implements UserDetailsManager {
    @Autowired
    private CustomUserDetailsRepository customUserDetailsRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    public CustomUserDetailsServices() {

    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = customUserDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Collection<AuthoritiesEntity> authority = authorityRepository.findAllByUsersByUser_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException("Authority not found with username: " + username));

        return User.builder()
                .username(usersEntity.getUsername())
                .password(usersEntity.getPassword())
                .authorities(getAuthorities(authority)).build();
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<AuthoritiesEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }

    @Override
    public void createUser(UserDetails user) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(user.getUsername());
        usersEntity.setPassword(user.getPassword());
        usersEntity.setEnabled(true);

        user.getAuthorities().forEach(c->{
            AuthoritiesEntity authoritiesEntity = new AuthoritiesEntity();
            authoritiesEntity.setAuthority(c.getAuthority());
            authoritiesEntity.setUsersByUser(usersEntity);
        });
    }

    @Override
    public void updateUser(UserDetails user) {
    }

    @Override
    public void deleteUser(String username) {
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
    }

    @Override
    public boolean userExists(String username) {
        return customUserDetailsRepository.findByUsername(username).isPresent();
    }
}
