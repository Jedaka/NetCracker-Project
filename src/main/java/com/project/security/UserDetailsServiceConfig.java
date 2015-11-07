package com.project.security;

import com.project.database.dao.UserDAO;
import com.project.entity.User;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

/**
 * Created by jedaka on 07.11.2015.
 */
public class UserDetailsServiceConfig implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByEmail(s);
        if (user == null) throw new UsernameNotFoundException("Username not found in the database");
        UserDetails userDetails = convertToUserDetails(user);
        return userDetails;
    }

    private UserDetails convertToUserDetails(User user){
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, Arrays.asList(new GrantedAuthorityImpl("ROLE_USER")));
    }
}
