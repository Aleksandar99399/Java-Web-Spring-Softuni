package bg.softuni.exam.repositories;

import bg.softuni.exam.models.enities.Category;
import bg.softuni.exam.models.enities.NameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    Category findCategoryByName(NameType type);
}
