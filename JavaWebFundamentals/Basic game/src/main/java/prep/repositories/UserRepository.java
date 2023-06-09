package prep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prep.models.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {


    Optional<User> findByUsername(String username);
}
