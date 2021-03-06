package com.jcastillo.jumbo.sandbox.locator.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
/**
 * System user definition, for this test only one user was created
 * @author jorge castillo
 *
 */
@Service
public class DefaultUserDetailService  implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new User("user", "user1", new ArrayList<>());
    }
}