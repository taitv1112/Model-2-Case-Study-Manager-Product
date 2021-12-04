package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Cart implements Serializable {
    private List<Product> productList;
    private LocalDate date;
    private double bill;
    private int idCart;
    private Manager manager;
    public Cart() {

        this.date = LocalDate.now();
    }
    public Cart(int idCart,Manager manager,List<Product> productList, double bill) {
        this.manager = manager;
        this.productList = productList;
        this.date = LocalDate.now();
        this.bill = bill;
        this.idCart = idCart;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    @Override
    public String toString() {
        return "Cart{" +
                ", idCart=" + idCart +
                ", manager=" + manager.getUserManager() +
                ", date=" + date +
                ", bill=" + bill +
                '}';
    }
}
