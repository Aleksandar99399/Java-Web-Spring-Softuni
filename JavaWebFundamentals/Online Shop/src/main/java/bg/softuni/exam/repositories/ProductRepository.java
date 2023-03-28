package bg.softuni.exam.repositories;

import bg.softuni.exam.models.enities.NameType;
import bg.softuni.exam.models.enities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,String> {

    List<Product> findByCategoryName(NameType name);

    Product findByName(String name);

    @Query("SELECT SUM(p.price) FROM Product p ")
    BigDecimal findSum();
}
