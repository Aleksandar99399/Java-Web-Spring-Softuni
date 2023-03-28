package prep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prep.models.entities.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero,String> {

}
