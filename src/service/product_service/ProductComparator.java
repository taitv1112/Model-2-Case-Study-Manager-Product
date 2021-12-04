package service.product_service;

import model.Product;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getPriceSale()> o2.getPriceSale())return 1;
        else if(o1.getPriceSale()< o2.getPriceSale()) return -1;
        return 0;
    }
}
