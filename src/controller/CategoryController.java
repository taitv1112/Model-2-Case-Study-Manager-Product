package controller;

import model.CategoryProduct;
import model.Product;
import service.category.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

public class CategoryController {
    CategoryServiceImpl categoryService = new CategoryServiceImpl();
    public List<CategoryProduct> showListCategory() throws IOException {
        return this.categoryService.findALl();
    }
    public void addCategory(CategoryProduct categoryProduct) throws IOException {
        categoryService.saveCatagory(categoryProduct);
        showListCategory();
    }
    public boolean deleteCategory(String id){
        return categoryService.delete(id);
    }
    public void editCategory(int id, CategoryProduct categoryProduct){
       categoryService.edit(id,categoryProduct);
    }
    public CategoryProduct detailCategory(String id) throws IOException {
        return categoryService.findALl().get(categoryService.findIndexByIdCategory(id));
    }
    public int findIndexByIdCategory(String idCategory){
        return categoryService.findIndexByIdCategory(idCategory);
    }
}
