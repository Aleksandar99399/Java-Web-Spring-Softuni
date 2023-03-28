package com.softuni.user;

import com.softuni.model.AuthorityEntity;
import com.softuni.model.UserEntity;
import com.softuni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DemoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public DemoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity =
                this.userRepository.findByName(username);

        return userEntity
                .map(this::map)
                .orElseThrow(()->new UsernameNotFoundException("User" + username + "not found"));
    }

    private UserDetails map(UserEntity userEntity){
        return new User(
                userEntity.getName(),
                userEntity.getPassword(),
                userEntity.getAuthorities()
                .stream()
                .map(this::map)
                .collect(Collectors.toList())
        );
    }
    private GrantedAuthority map(AuthorityEntity authorityEntity){
        return new SimpleGrantedAuthority(authorityEntity.getName());
    }
}
