package bg.softuni.exam.init;

import bg.softuni.exam.models.enities.Category;
import bg.softuni.exam.models.enities.NameType;
import bg.softuni.exam.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Init implements CommandLineRunner {
    private CategoryRepository repository;

    @Autowired
    public Init(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count()==0){
            repository.save(new Category(NameType.DRINK,"Drink"));
            repository.save(new Category(NameType.FOOD,"Food"));
            repository.save(new Category(NameType.HOUSEHOLD,"Household"));
            repository.save(new Category(NameType.OTHER,"Other"));
        }
    }
}
