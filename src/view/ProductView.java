package view;

import controller.CategoryController;
import controller.ProductController;
import model.CategoryProduct;
import model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    ProductController productController = new ProductController();
    CategoryController categoryController = new CategoryController();
    List<Product> productList = productController.showListProduct();
    Scanner sc = new Scanner(System.in);

    public ProductView() throws IOException {
    }

    public void viewCreateProduct() throws IOException {
        while (true) {
            int id;
            if(productList.size()==0){
                id =1;
            } else {
                id = productList.get(productList.size()-1).getIdProduct()+1;
            }
            System.out.println("Enter name Product ");
            sc.nextLine();
            String nameProduct = sc.nextLine();
            System.out.println("Enter quantity Product");
            int quantity = sc.nextInt();
            System.out.println("Enter status Product (true or false)");
            boolean status = sc.nextBoolean();
            System.out.println("Enter price input product");
            double priceInput = sc.nextDouble();
            System.out.println("Enter profit");
            double profit = sc.nextDouble();
            System.out.println("Enter idCategory");
            sc.nextLine();
            String idCategory = sc.nextLine();
            CategoryProduct categoryProduct = categoryController.detailCategory(idCategory);
            System.out.println("Enter description product ");
            String descriptionProduct = sc.nextLine();
            Product product = new Product(id,nameProduct, quantity, status, priceInput, profit, categoryProduct, descriptionProduct);
            productController.addProduct(product);
            System.out.println("Create success !");
            System.out.println("Enter any Key to continue create - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuProduct();
            }
        }
    }

    public void viewDeleteProduct() throws IOException {
        while (true) {
            System.out.println("Enter idProduct want delete ");
            int id = sc.nextInt();
            if (productController.deleteProduct(id)) {
                productController.showListProduct();
                System.out.println("Delete success");
            } else {
                System.out.println("idProduct not found");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuProduct();
            }
        }
    }

    public void viewEditProduct(int type) throws IOException {
        while (true) {
            System.out.println("Enter idProduct want edit ");
            sc.nextLine();
            int idProduct = sc.nextInt();
            int index = productController.findIndexById(idProduct);
            if (index > -1) {
                Product product = productList.get(index);
                if (type == 1) {
                    System.out.println("Enter name Product ");
                    sc.nextLine();
                    String nameProduct = sc.nextLine();
                    System.out.println("Enter quantity Product");
                    int quantity = sc.nextInt();
                    System.out.println("Enter profit");
                    double profit = sc.nextDouble();
                    product.setNameProduct(nameProduct);
                    product.setQuantity(quantity);
                    product.setProfit(profit);
                    productController.editProduct(index, product);
                } else {
                    System.out.println("Enter name Product ");
                    sc.nextLine();
                    String nameProduct = sc.nextLine();
                    System.out.println("Enter quantity Product");
                    int quantity = sc.nextInt();
                    System.out.println("Enter status Product (true or false)");
                    boolean status = sc.nextBoolean();
                    System.out.println("Enter price input product");
                    double priceInput = sc.nextDouble();
                    System.out.println("Enter profit");
                    double profit = sc.nextDouble();
                    System.out.println("Enter idCategory");
                    sc.nextLine();
                    String idCategory = sc.nextLine();
                    CategoryProduct categoryProduct = categoryController.detailCategory(idCategory);
                    System.out.println("Enter description product ");
                    String descriptionProduct = sc.nextLine();
                    product.setAll(nameProduct, quantity, status, priceInput, profit, categoryProduct, descriptionProduct);
                    productController.editProduct(index, product);
                }
                productController.showListProduct();
                System.out.println("Edit success");
            } else {
                System.out.println("Edit Error. IdProduct not found !");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuProduct();
            }
        }
    }

    public void viewShowProductByPrice() throws IOException {
        while (true) {
            System.out.println("Enter price star");
            double priceStar = sc.nextDouble();
            System.out.println("Enter price last");
            double priceLast = sc.nextDouble();
            int count =0;
            if (priceStar > priceLast) {
                for (Product product : productList) {
                    if (product.getPriceSale() >= priceLast && product.getPriceSale() < priceStar) {
                        System.out.println(product.toString());
                        count++;
                    }
                }
            } else {
                for (Product product : productList) {
                    if (product.getPriceSale() >= priceStar && product.getPriceSale() <= priceLast) {
                        System.out.println(product.toString());
                        count++;
                    }
                }

            }
            if(count == 0){
                System.out.println("There are no products in the above price range");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuProduct();
            }
        }
    }
    public void viewSearchProductByName() throws IOException {
        while (true) {
            System.out.println("Enter name Product");
            sc.nextLine();
            String name = sc.nextLine();
            int count =0;
            for (Product product : productList) {
                if (product.getNameProduct().contains(name)) {
                    System.out.println(product);
                    count++;
                }
            }
            if(count == 0){
                System.out.println("Name not found");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuProduct();
            }
        }
    }
    public void viewSearchProductByNameCatefory() throws IOException {
        while (true) {
            System.out.println("Enter name Category");
            sc.nextLine();
            String name = sc.nextLine();
            int count =0;
            for (Product product : productList) {
                if (product.getCategoryProduct().getNameCategory().equals(name)) {
                    System.out.println(product);
                    count++;
                }
            }
            if(count == 0){
                System.out.println("Name Category not found");
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                viewMenuProduct();
            }
        }
    }
    public void viewShowQuantityProduct() throws IOException {
        for (int i = 0; i < categoryController.showListCategory().size(); i++) {
            System.out.println(categoryController.showListCategory().get(i).toString() + " Sum quantity product: " +productController.sumQuantityProductByIdCategory(categoryController.showListCategory().get(i).getIdCategory()));
        }
    }
    public void viewSortProductByPrice(){
        productController.sortProductByPrice();
    }
    public void viewShowProduct(){
        for (Product p:productList) {
            System.out.println(p);
        }
    }
    public void viewMenuProduct() throws IOException {
        while (true){
            System.out.println("Menu Product");
            System.out.println("1,Add product");
            System.out.println("2,Edit product");
            System.out.println("3,Delete product");
            System.out.println("4,Show product");
            System.out.println("5,Search product by name category");
            System.out.println("6,Search product by name");
            System.out.println("7,Search product by range price");
            System.out.println("8,Show Category and quantity product ");
            System.out.println("9,Sort Product By Price ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:viewCreateProduct();break;
                case 2:viewMenuEditProduct();break;
                case 3:viewDeleteProduct();break;
                case 4:viewShowProduct();break;
                case 5:viewSearchProductByNameCatefory();break;
                case 6:viewSearchProductByName();break;
                case 7:viewShowProductByPrice();break;
                case 8:viewShowQuantityProduct();break;
                case 9:viewSortProductByPrice();break;
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                return;
            }
        }
    }

    private void viewMenuEditProduct() throws IOException {
        while (true){
            System.out.println("1.Edit name, quantity, profit");
            System.out.println("2.Edit all");
            int choice = sc.nextInt();
            switch (choice){
                case 1:viewEditProduct(1);break;
                case 2:viewEditProduct(2);break;
            }
            System.out.println("Enter any Key to continue  - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
            viewMenuProduct();
            }
        }
    }
}
