package service.product_service;

import model.Product;
import service.IService;

import java.util.List;

public interface IProductService extends IService {
    void saveProduct(Product product);
    List<Product> findProductListByIdCategory(String idCategory);
}
