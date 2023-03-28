package com.tabula.users;

import com.tabula.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserEntity getOrCreateUser(String email){

        Objects.requireNonNull(email);

        Optional<UserEntity> opt= userRepository.findByEmail(email);



        return opt.orElseGet(()->createUser(email));
    }



    public boolean existUser(String email){
        Objects.requireNonNull(email);

       return userRepository.findByEmail(email).isPresent();
    }

    public void  createAndLoginUser(String email,String password){
        UserEntity user = createUser(email, password);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, password,userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private UserEntity createUser(String email){
        return createUser(email,null);
    }

    private UserEntity createUser(String email,String password){
        LOGGER.info("Creating a new user with email [GDPR].");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        if (password!=null){
            userEntity.setPasswordHash(passwordEncoder.encode(password));
        }

        RoleEntity userRole=new RoleEntity();
        userRole.setRole("ROLE_USER");
        userEntity.setRoles(List.of(userRole));

        return this.userRepository.save(userEntity);
    }
}
