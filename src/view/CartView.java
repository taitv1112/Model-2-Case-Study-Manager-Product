package view;

import controller.CartController;
import controller.ManagerController;
import controller.ProductController;
import model.Cart;
import model.Manager;
import model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartView {
    ProductController productController = new ProductController();
    CartController cartController = new CartController();
    ManagerController managerController = new ManagerController();
    List<Cart> cartList = cartController.showListCart();
    Scanner sc = new Scanner(System.in);

    public CartView() throws IOException {
    }

    public void viewCreateCart() throws IOException {
        while (true) {
            int idCart;
            if(cartList.size()==0){
                idCart =1;
            } else {
                idCart = cartList.get(cartList.size()-1).getIdCart()+1;
            }
            Manager manager = addInforManagerToCart();
            if(manager == null){
                return;
            }
            List<Product> listProductInCard= new ArrayList<>();
            while (true){
                System.out.println("Enter idProduct - Enter 99999 next Step ");
                int idProduct = sc.nextInt();
                if(idProduct == 99999){
                    break;
                }
                int index = productController.findIndexById(idProduct);
                if (index > -1) {
                    if(!productController.checkQuantityProduct(index)){
                        System.err.println("The product you just imported is out of stock");
                        System.out.println("Enter any Key to continue enter username - Enter QUIT to back MENU: ");
                        String back = sc.next();
                        if (back.equalsIgnoreCase("quit")) {
//                            viewMenu();
                        }
                        continue;
                    }
                    while (true){
                        System.out.println("Enter quantity");
                        int quantityProductInCart = sc.nextInt();
                        if (productController.checkQuantitytoCart(quantityProductInCart, index)) {
                            Product product = productController.showListProduct().get(index);
                            product.setQuantity(quantityProductInCart);
                            productController.showListProduct().get(index).setQuantity(productController.showListProduct().get(index).getQuantity() - quantityProductInCart);
                            productController.showListProduct().get(index).setQuantitySale(productController.showListProduct().get(index).getQuantitySale() + quantityProductInCart);
                            listProductInCard.add(product);
                            break;
                        } else{
                            System.err.println("The number of products you entered exceeds the number of products available"+ "\n"
                                    + "or you entered a negative number  ");
                            System.err.println("Product is available."+productController.showListProduct().get(index).getQuantity() );
                            System.err.println("Enter any Key to continue enter username - Enter QUIT to back MENU: ");
                            String back = sc.next();
                            if (back.equalsIgnoreCase("quit")) {
                            break;
                            }
                        }
                    }

                }else {
                    System.err.println("IdProduct not found ");
                    System.err.println("Enter any Key to continue enter username - Enter QUIT to back MENU: ");
                    String back = sc.next();
                    if (back.equalsIgnoreCase("quit")) {
                        break;
                    }
                }
            }
            double bill = productController.sumProductSaleMoney(listProductInCard);
            Cart cart = new Cart(idCart,manager,listProductInCard,bill);
            cartController.addCart(cart);
            System.err.println("Enter any Key to continue enter username - Enter QUIT to back MENU: ");
            String back = sc.next();
            if (back.equalsIgnoreCase("quit")) {
                break;
            }
        }
    }
    public void viewEditCart() throws IOException {
        while (true){
            System.out.println("Enter IdCart");
            int idCart = sc.nextInt();
            int index = cartController.findIndexByIdCart(idCart);
            if(index>-1){
                Cart cart = cartList.get(index);
                Manager manager = addInforManagerToCart();
                if(manager == null){
                    return;
                }
                while (true){
                    System.out.println("Enter IdProduct");
                    int idProduct = sc.nextInt();
                    int indexProductListCart= productController.findIndexByIdInList(idProduct,cartList.get(index).getProductList());
                    if(indexProductListCart>-1){
                       while (true){
                           Product product = cartList.get(index).getProductList().get(indexProductListCart);
                           //doi tuong trong kho
                           Product product1 = productController.showListProduct().get(productController.findIndexByNameProduct(product.getNameProduct()));
                           System.out.println("Enter quantity");
                           int quantity = sc.nextInt();
                           if(quantity>product.getQuantity()&&product1.getQuantity()>(quantity-product.getQuantity())){
                               product1.setQuantity(product1.getQuantity()-(quantity-product.getQuantity()));
                               productController.editProduct(productController.findIndexByNameProduct(product.getNameProduct()),product1);
                               product.setQuantity(quantity);
                               cartController.showListCart().get(index).getProductList().set(indexProductListCart,product);
                               break;
                           }else if(quantity<product.getQuantity()&& quantity>0){
                               product1.setQuantity(product1.getQuantity()+(product.getQuantity()-quantity));
                               productController.editProduct(productController.findIndexByNameProduct(product.getNameProduct()),product1);
                               product.setQuantity(quantity);
                               cartController.showListCart().get(index).getProductList().set(indexProductListCart,product);
                               break;
                           }else {
                               System.err.println("The quantity you entered is not valid.");
                               System.err.println("TThe current inventory quantity of the product is" + product1.getQuantity());
                               System.err.println("Enter any Key to continue  - Enter QUIT to next step : ");
                               String back = sc.next();
                               if (back.equalsIgnoreCase("quit")) {
                                   break;
                               }
                           }
                       }
                    }else {
                        System.err.println("IdProduct not found");
                        System.err.println("Enter any Key to continue  - Enter QUIT to next step : ");
                        String back = sc.next();
                        if (back.equalsIgnoreCase("quit")) {
                            break;
                        }
                    }

                }
                double bill = productController.sumProductSaleMoney(cartList.get(index).getProductList());
                cartList.get(index).setBill(bill);
                cartList.get(index).setManager(manager);
                cartController.showListCart();
                System.out.println("Edit success");
                break;
            }else {
                System.err.println("IdCard not found");
                System.err.println("Enter any Key to continue  - Enter QUIT to next step : ");
                String back = sc.next();
                if (back.equalsIgnoreCase("quit")) {
                    break;
                }
            }
        }
    }
    public void viewDeleteCart() throws IOException {
        while (true){
            System.out.println("Enter idCart");
            int idCart = sc.nextInt();
            if(cartController.deleteCategory(idCart)){
                cartController.showListCart();
                System.out.println("Delete success !");
            }else {
                System.err.println("IdCard not found");
                System.err.println("Enter any Key to continue  - Enter QUIT to next step : ");
                String back = sc.next();
                if (back.equalsIgnoreCase("quit")) {
                    break;
                }
            }
        }
    }
    public void viewShowDetailCart() throws IOException {
        while (true){
            System.out.println("Enter idCart");
            int idCart = sc.nextInt();
            int index = cartController.findIndexByIdCart(idCart);
            if(index>-1){
                Cart cart = cartController.detailCategory(idCart);
                System.out.println("Cart " +"\n"+
                        ", idCart=" + idCart +"\n"+
                        ", manager=" + cart.getManager().getUserManager() );
                for (Product p:cart.getProductList()) {
                    System.out.println(p.toStringUser());
                }
                System.out.println(
                        ", date=" + cart.getDate()+"\n"+
                        ", bill=" + cart.getBill());
                break;
            }else {
                System.err.println("IdCard not found");
                System.err.println("Enter any Key to continue  - Enter QUIT to next step : ");
                String back = sc.next();
                if (back.equalsIgnoreCase("quit")) {
                    break;
                }
            }

        }
    }
    public void viewShowAllCart(){
        for (Cart c:cartList) {
            System.out.println(c.toString());
        }
    }
    public  void viewMenuCartAdmin() throws IOException {
        while (true){
            System.out.println("1. Create Cart Shopping");
            System.out.println("2. Edit Cart Shopping");
            System.out.println("3. Delete Cart Shopping");
            System.out.println("4. Show Cart Shopping");
            int choice = sc.nextInt();
            switch (choice){
                case 1:viewCreateCart();break;
                case 2:viewEditCart();break;
                case 3:viewDeleteCart();
            }
        }
    }

    private Manager addInforManagerToCart() {
        boolean checkUserName;
        do {
            checkUserName = true;
            System.out.println("Enter UserName ");
            String userName = sc.nextLine();
            int indexManager = managerController.findIndexByUserName(userName);
            if (indexManager > -1) {
                Manager manager = managerController.detailManagerByUser(userName);
                return manager;
            } else {
                System.err.println("username not found ");
                checkUserName = false;
                System.out.println("Enter any Key to continue enter username - Enter QUIT to back MENU: ");
                String back = sc.next();
                if (back.equalsIgnoreCase("quit")) {
                    return null;
                }
            }
        }
        while (!checkUserName);
        return null;
    }

}
