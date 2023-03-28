package bg.softuni.exam.repositories;

import bg.softuni.exam.models.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,String> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username,String password);
}
