package controller;

import model.Cart;
import service.cart.CartServiceImpl;

import java.io.IOException;
import java.util.List;

public class CartController {
    CartServiceImpl cartService = new CartServiceImpl();
    public List<Cart> showListCart() throws IOException {
        return this.cartService.findAll();
    }
    public void addCart(Cart cart) throws IOException {
        cartService.save(cart);
        showListCart();
    }
    public boolean deleteCategory(int idCart ){
        int index = cartService.findIndexByIdCart(idCart);
        if(index>-1){
            cartService.delete(index);
            return true;
        }
        return false;
    }
    public void editCart(int index, Cart cart){
        cartService.edit(index,cart);
    }
    public Cart detailCategory(int idCart) throws IOException {
        return cartService.findAll().get(cartService.findIndexByIdCart(idCart));
    }
    public int findIndexByIdCart(int idCategory){
        return cartService.findIndexByIdCart(idCategory);
    }
}
