package bg.softuni.exam.services.interfaces;

import bg.softuni.exam.models.enities.Category;
import bg.softuni.exam.models.enities.NameType;

public interface CategoryService {

    Category getCategoryByNameType(NameType nameType);
}
