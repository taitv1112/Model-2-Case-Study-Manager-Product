package view;

import controller.CategoryController;
import controller.ProductController;
import model.CategoryProduct;
import model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CategoryView {
    Scanner sc = new Scanner(System.in);
    CategoryController categoryController = new CategoryController();
    ProductController productController = new ProductController();
    List<CategoryProduct> categoryProductList = categoryController.showListCategory();

    public CategoryView() throws IOException {
    }

    public void viewCreateCategory() throws IOException {
        while (true){
            System.out.println("Enter IdCategory ");
            sc.nextLine();
            String IdCategory = sc.nextLine();
            System.out.println("Enter name Category ");
            String nameCategory = sc.nextLine();
            System.out.println("Enter description Category");
            String descriptionCategory = sc.nextLine();
            CategoryProduct categoryProduct = new CategoryProduct(IdCategory,nameCategory,descriptionCategory);
            categoryController.addCategory(categoryProduct);
            System.out.println("Create success !");
            System.out.println("Enter any Key to continue create - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenu();
            }
        }
    }
    public void viewDeleteCategory() throws IOException {
        while (true){
            System.out.println("Enter idCategory want delete ");
            String id = sc.nextLine();
            if(categoryController.deleteCategory(id)){
                categoryController.showListCategory();
                System.out.println("Delete success");
            }else {
                System.out.println("Delete lose . idCategory not found");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenu();
            }
        }
    }
    public void viewEditCategory() throws IOException {
        while (true){
            System.out.println("Enter idCategory want edit ");
            sc.nextLine();
            String idCategory = sc.nextLine();
            int index = categoryController.findIndexByIdCategory(idCategory);
            if(index>-1){
                CategoryProduct categoryProduct = categoryProductList.get(index);
                System.out.println("Enter new IdCategory ");
                String idCategorynew = sc.nextLine();
                System.out.println("Enter new name Category ");
                String nameCategory = sc.nextLine();
                System.out.println("Enter new description Category");
                String descriptionCategory = sc.nextLine();
                categoryProduct.setIdCategory(idCategorynew);
                categoryProduct.setNameCategory(nameCategory);
                categoryProduct.setDescriptionCategory(descriptionCategory);
                categoryController.editCategory(index,categoryProduct);
                categoryController.showListCategory();
                System.out.println("Edit success");
            }else {
                System.out.println("Edit Error. IdCategory not found !");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenu();
            }
        }
    }
    public void viewShowDetailCategoryById() throws IOException {
        while (true){
            System.out.println("Enter idCategory");
            sc.nextLine();
            String idCategory = sc.nextLine();
            int count = 0;
            for (int i = 0; i < categoryProductList.size(); i++) {
                if(idCategory.equals(categoryProductList.get(i).getIdCategory())){
                    System.out.println(categoryProductList.get(i).toString());
                    count++;
                }
            }
            if(count==0){
                System.out.println("Category not found. Please check input information ");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenuSearch();
            }
        }
    }
    public void viewShowDetailCategoryByName() throws IOException {
        while (true){
            System.out.println("Enter name Category");
            sc.nextLine();
            String nameCategory = sc.nextLine();
            int count = 0;
            for (int i = 0; i < categoryProductList.size(); i++) {
                if(nameCategory.equals(categoryProductList.get(i).getNameCategory())){
                    System.out.println(categoryProductList.get(i).toString());
                    count++;
                }
            }
            if(count==0){
                System.out.println("Category not found. Please check input information ");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenuSearch();
            }
        }
    }
    public void viewShowProductOfCategory() throws IOException {
        while (true){
            System.out.println("Enter idCategory");
            sc.nextLine();
            String idCategory = sc.nextLine();
            int index = categoryController.findIndexByIdCategory(idCategory);
            if(index>-1){
                for (Product p:productController.getListProductByIdCategory(idCategory)) {
                    System.out.println(p);
                }
                System.out.println(productController.getListProductByIdCategory(idCategory));
            }else {
                System.out.println("Category not found. Please check input information ");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenuSearch();
            }
        }
    }

    public void viewShowCategory() {
        for (CategoryProduct cp: categoryProductList) {
            System.out.println(cp.toString());
        }
    }

    public void viewMenuSearch() throws IOException {
        while (true){
            System.out.println("1.Find detail category by id");
            System.out.println("2.Find detail category by name");
            System.out.println("3.Show product list of category by id");
            int choice = sc.nextInt();
            switch (choice){
                case 1: viewShowDetailCategoryById();break;
                case 2:viewShowDetailCategoryByName();break;
                case 3:viewShowProductOfCategory();break;
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
                viewMenu();
            }
        }
    }

    public void viewMenu() throws IOException {
        while (true){
            System.out.println("Category Management Center");
            System.out.println("1.Create new category");
            System.out.println("2.Edit category");
            System.out.println("3.Delete category");
            System.out.println("4.Search Category ");
            System.out.println("5.Show category");
            int choice = sc.nextInt();
            switch (choice){
                case 1: viewCreateCategory();break;
                case 2:viewEditCategory();break;
                case 3:viewDeleteCategory();break;
                case 4:viewMenuSearch();break;
                case 5:viewShowCategory();break;
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if(back.equalsIgnoreCase("quit")){
//                Main.menuMain();
            }
        }
    }

}
