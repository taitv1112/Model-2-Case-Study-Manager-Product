package controller;

import model.Product;
import service.product_service.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

public class ProductController {
    ProductServiceImpl productService = new ProductServiceImpl();
    public List<Product> showListProduct() throws IOException {
        return this.productService.findALl();
    }
    public void addProduct(Product product) throws IOException {
        productService.saveProduct(product);
        showListProduct();
    }
    public boolean checkQuantityProduct(int index) throws IOException {
        if(productService.findALl().get(index).getQuantity()==0){
            return false;
        }
        return true;
    }
    public double sumProductSaleMoney(List<Product> productList){
        double sum =0;
        for (int i = 0; i < productList.size(); i++) {
            sum+=(productList.get(i).getPriceSale()*productList.get(i).getQuantity());
        }
        return sum;
    }
    public int findIndexByIdInList(int idProduct, List<Product> productList){
        for (int i = 0; i < productList.size(); i++) {
            if(idProduct==productList.get(i).getIdProduct()){
                return i;
            }
        }
        return -1;
    }
    public int findIndexByNameProduct(String nameProduct) throws IOException {
        for (int i = 0; i < showListProduct().size(); i++) {
            if(nameProduct.equals(showListProduct().get(i).getNameProduct())){
                return i;
            }
        }
        return -1;
    }
    public int findIndexByNameProduct(String nameProduct, List<Product> productList){
        for (int i = 0; i < productList.size(); i++) {
            if(nameProduct.equals(productList.get(i).getNameProduct())){
                return i;
            }
        }
        return -1;
    }
    public boolean checkQuantitytoCart(int quantity,int index) throws IOException {
        if(quantity>productService.findALl().get(index).getQuantity()||quantity < 0){
            return false;
        }
        return true;
    }
    public boolean deleteProduct(int id){
        return productService.deleteProduct(id);
    }
    public void editProduct(int id, Product product){
        productService.editProduct( id,product);
    }
    public int findIndexById(int id){return productService.findIndexByIdProduct(id);}
    public Product detailProduct(int id) throws IOException {
        return productService.findALl().get(productService.findIndexByIdProduct(id));
    }
    public List<Product> getListProductByIdCategory(String id){
        return productService.findProductListByIdCategory(id);
    }
    public int sumQuantityProductByIdCategory(String idCategory){
        return productService.sumQuantityProductByIdCategory(idCategory);
    }
    public void sortProductByPrice(){
        productService.sortByPrice();
    }
}
