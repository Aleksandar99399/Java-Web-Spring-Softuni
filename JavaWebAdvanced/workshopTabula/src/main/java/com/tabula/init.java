package com.tabula;

import com.tabula.announcement.model.AnnouncementEntity;
import com.tabula.repository.AnnouncementRepository;
import com.tabula.users.RoleEntity;
import com.tabula.users.UserEntity;
import com.tabula.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Component
public class init implements CommandLineRunner {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (announcementRepository.count()==0){
            AnnouncementEntity entity=new AnnouncementEntity();
            entity.setTitle("Hello, Softuni!");
            entity.setDescription("Welcome to the Spring Advanced");
            entity.setCreatedOn(Instant.now());
            entity.setUpdatedOn(Instant.now());

            announcementRepository.save(entity);
        }

        if(userRepository.count()==0){
            //admin
            UserEntity admin=new UserEntity();
            admin.setEmail("admin@abv.bg");
            admin.setPasswordHash(passwordEncoder.encode("topsecret"));

            RoleEntity adminRole=new RoleEntity();
            adminRole.setRole("ROLE_ADMIN");


            RoleEntity userRole=new RoleEntity();
            userRole.setRole("ROLE_USER");
            admin.setRoles(List.of(adminRole,userRole));

            userRepository.save(admin);

            //normal
            UserEntity user=new UserEntity();
            user.setEmail("user@abv.bg");
            user.setPasswordHash(passwordEncoder.encode("userpass"));

            RoleEntity userUserRole=new RoleEntity();
            userUserRole.setRole("ROLE_USER");

            user.setRoles(List.of(userUserRole));
            userRepository.save(user);
        }
    }
}
