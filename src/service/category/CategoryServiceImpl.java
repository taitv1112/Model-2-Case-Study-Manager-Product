package service.category;

import config.ConfigReadAndWriteFile;
import file.Path;
import model.CategoryProduct;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CategoryServiceImpl implements ICategoryService{
    public String PATH_CATEGORY;
    ConfigReadAndWriteFile<CategoryProduct> configReadAndWriteFile;
    List<CategoryProduct> categoryProductList;

    public CategoryServiceImpl() {
        this.PATH_CATEGORY = Path.PATH+"category.txt";
        this.configReadAndWriteFile = new ConfigReadAndWriteFile<>();
        this.categoryProductList = configReadAndWriteFile.readFromFile(PATH_CATEGORY);
    }

    @Override
    public List<CategoryProduct> findALl() throws IOException {
            configReadAndWriteFile.writeToFile(PATH_CATEGORY,categoryProductList);
        return categoryProductList;
    }
    public void saveCatagory(CategoryProduct categoryProduct){
        categoryProductList.add(categoryProduct);
    }
    public boolean delete(String id){
        int index = findIndexByIdCategory(id);
        if(index>-1){
            categoryProductList.remove(index);
            return true;
        }
        return false;
    }
    public void edit(int index , CategoryProduct categoryProduct){
            categoryProductList.set(index,categoryProduct);
    }

    public int findIndexByIdCategory(String id){
        for (int i = 0; i < categoryProductList.size(); i++) {
            if(id.equals(categoryProductList.get(i).getIdCategory())){
                return i;
            }
        }
        return -1;
    }
}
