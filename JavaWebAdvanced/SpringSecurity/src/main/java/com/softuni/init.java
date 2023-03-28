package com.softuni;

import com.softuni.model.AuthorityEntity;
import com.softuni.model.UserEntity;
import com.softuni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class init implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        UserEntity user=new UserEntity();
        user.setName("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setEnable(true);

        AuthorityEntity authorityEntity=new AuthorityEntity();
        authorityEntity.setName("ROLE_USER");
        authorityEntity.setUser(user);
        user.setAuthorities(List.of(authorityEntity));

        userRepository.save(user);

        UserEntity admin=new UserEntity();
        admin.setName("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEnable(true);

        AuthorityEntity adminAuthorityEntity=new AuthorityEntity();
        adminAuthorityEntity.setName("ROLE_ADMIN");
        adminAuthorityEntity.setUser(admin);

        AuthorityEntity adminAuthorityEntity2=new AuthorityEntity();
        adminAuthorityEntity2.setName("ROLE_USER");
        adminAuthorityEntity2.setUser(admin);


        admin.setAuthorities(List.of(adminAuthorityEntity,adminAuthorityEntity2));

        userRepository.save(admin);
    }
}
