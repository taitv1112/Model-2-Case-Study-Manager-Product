package service.product_service;

import config.ConfigReadAndWriteFile;
import file.Path;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductServiceImpl implements IProductService{
    public String PATH_PRODUCT;
    ConfigReadAndWriteFile<Product> configReadAndWriteFile ;
    List<Product> productList;

    public ProductServiceImpl() {
        this.PATH_PRODUCT = Path.PATH+"product.txt";
        this.configReadAndWriteFile = new ConfigReadAndWriteFile<>();
        this.productList = configReadAndWriteFile.readFromFile(PATH_PRODUCT);
    }

    @Override
    public List<Product> findALl() throws IOException {
        this.configReadAndWriteFile.writeToFile(this.PATH_PRODUCT, this.productList);
        return this.productList;

    }
    public int findIndexByIdProduct(int id){
        for (int i = 0; i < productList.size(); i++) {
            if(id == productList.get(i).getIdProduct()){
                return i;
            }
        }
        return -1;
    }
 public boolean deleteProduct(int id){
        int index = findIndexByIdProduct(id);
        if(index>-1){
            productList.remove(index);
            return true;
        }else {
            return false;
        }
 }

 public void editProduct(int index,Product product){
            productList.set(index,product);
 }

    @Override
    public void saveProduct(Product product) {
        productList.add(product);
    }
    public int sumQuantityProductByIdCategory(String id){
        int sum = 0;
        for (int i = 0; i < productList.size(); i++) {
            if(id.equals(productList.get(i).getCategoryProduct().getIdCategory())){
                sum+=productList.get(i).getQuantity();
            }
        }
        return sum;
    }
    public void sortByPrice(){
        ProductComparator productComparator = new ProductComparator();
        productList.sort(productComparator);
    }
    @Override
    public List<Product> findProductListByIdCategory(String idCategory) {
        List<Product> productListByIdCategory = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getCategoryProduct().getIdCategory().equals(idCategory)){
                productListByIdCategory.add(productList.get(i));
            }
        }
        return productListByIdCategory;
    }
}
