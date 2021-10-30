package com.plaincoded.restapi.services.userServices;


import com.plaincoded.restapi.interfaces.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final String ROLE_PREFIX = "ROLE_";

    private final IUserService service;

    @Autowired
    public MyUserDetailsService(IUserService _service) {
        this.service = _service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.plaincoded.restapi.entities.userEntities.User> userOptional = Optional.ofNullable(service.findByUsername(username));
        userOptional.orElseThrow(IllegalArgumentException::new);
        com.plaincoded.restapi.entities.userEntities.User userFromDB = userOptional.get();

        return new User(userFromDB.getUsername(),
                userFromDB.getPassword(),
                new ArrayList<>(Arrays.asList((GrantedAuthority) () -> ROLE_PREFIX+userFromDB.getRole())));
    }

}
