package bg.softuni.exam.services;

import bg.softuni.exam.models.enities.Category;
import bg.softuni.exam.models.enities.NameType;
import bg.softuni.exam.repositories.CategoryRepository;
import bg.softuni.exam.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryByNameType(NameType nameType) {
        return this.categoryRepository.findCategoryByName(nameType);
    }
}
