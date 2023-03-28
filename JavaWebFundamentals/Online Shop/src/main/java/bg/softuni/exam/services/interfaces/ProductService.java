package bg.softuni.exam.services.interfaces;

import bg.softuni.exam.models.enities.NameType;
import bg.softuni.exam.models.services.ProductServiceModel;
import bg.softuni.exam.models.views.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductViewModel> getProductByType(NameType type);
    BigDecimal getProductSum();

    boolean isProductNameFree(String name);


    void register(ProductServiceModel productServiceModel);


    void buyOne(String id);

    void buyAll();
}
