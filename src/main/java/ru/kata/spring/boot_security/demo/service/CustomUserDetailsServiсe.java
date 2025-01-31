package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiсe implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsServiсe(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional // РАЗОБРАТЬСЯ С ЭТИМ
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("пользователь - " + username + "отсутствует!!!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole()))
                        .collect(Collectors.toSet()));
    }

}
