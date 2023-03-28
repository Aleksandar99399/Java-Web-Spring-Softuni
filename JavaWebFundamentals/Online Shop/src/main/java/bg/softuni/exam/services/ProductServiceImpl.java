package bg.softuni.exam.services;

import bg.softuni.exam.models.enities.NameType;
import bg.softuni.exam.models.enities.Product;
import bg.softuni.exam.models.services.ProductServiceModel;
import bg.softuni.exam.models.views.ProductViewModel;
import bg.softuni.exam.repositories.ProductRepository;
import bg.softuni.exam.services.interfaces.CategoryService;
import bg.softuni.exam.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public List<ProductViewModel> getProductByType(NameType type) {
        List<Product> entities = this.productRepository.findByCategoryName(type);


        List<ProductViewModel>products= Arrays.asList(this.modelMapper.map(entities,ProductViewModel[].class));
        products.forEach(e->e.setImgUrl(String.format("\\img\\%s.png",e.getCategoryName().toLowerCase())));
        return products;
    }

    @Override
    public BigDecimal getProductSum() {

        return this.productRepository.findSum();
    }

    @Override
    public boolean isProductNameFree(String name) {

       return this.productRepository.findByName(name)==null;
    }

    @Override
    public void register(ProductServiceModel productServiceModel) {
        Product product=this.modelMapper.map(productServiceModel,Product.class);

        product.setCategory(this.categoryService.getCategoryByNameType(productServiceModel.getNameType()));

        this.productRepository.save(product);
    }

    @Override
    public void buyOne(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        this.productRepository.deleteAll();
    }
}
