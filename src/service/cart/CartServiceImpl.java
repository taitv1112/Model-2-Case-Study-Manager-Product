package service.cart;

import config.ConfigReadAndWriteFile;
import file.Path;
import model.Cart;

import java.io.IOException;
import java.util.List;

public class CartServiceImpl {
    static String PATH_CART = Path.PATH+ "cart.txt";
    ConfigReadAndWriteFile<Cart> configReadAndWriteFile = new ConfigReadAndWriteFile<>();
    List<Cart> cartList = configReadAndWriteFile.readFromFile(PATH_CART);
    public List<Cart> findAll() throws IOException {
        configReadAndWriteFile.writeToFile(PATH_CART,cartList);
        return cartList;
    }
    public void save(Cart cart){
        cartList.add(cart);
    }
    public void edit(int index,Cart cart){
        cartList.set(index,cart);
    }
    public void delete(int index){
        cartList.remove(index);
    }
    public int findIndexByIdCart(int idCart){
        for (int i = 0; i < cartList.size(); i++) {
            if(idCart==cartList.get(i).getIdCart()){
                return i;
            }
        }
        return -1;
    }

}
