package com.spring.kurswork_beautysalon_web.service;

import com.spring.kurswork_beautysalon_web.entity.Role;
import com.spring.kurswork_beautysalon_web.entity.User;
import com.spring.kurswork_beautysalon_web.repository.RoleRepository;
import com.spring.kurswork_beautysalon_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByLogin(s);
    }

    public boolean addUser(User user) {
        User userFromDB = userRepository.findByLogin(user.getLogin());
        if (userFromDB != null) {
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var role = roleRepository.findByName("USER");
        if (role == null) {
            role = new Role("USER");
            roleRepository.save(role);
        }
        var list = new ArrayList<Role>();
        list.add(role);
        user.setRoles(list);
        userRepository.save(user);
        return true;
    }
}