package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private int idProduct;
    private String nameProduct;
    private int quantity;
    private boolean status;
    private double priceInput;
    private double priceSale;
    private double profit=1;
    private int quantitySale;
    private double totalPriceSale;
    public int getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(int quantitySale) {
        this.quantitySale = quantitySale;
    }

    private LocalDate dateInput;
    private CategoryProduct categoryProduct;
    private String descriptionProduct;


    public Product(int id,String nameProduct, int quantity,boolean status, double priceInput,double profit, CategoryProduct categoryProduct, String descriptionProduct) {
        this.idProduct = id;
        this.nameProduct = nameProduct;
        this.status = status;
        this.quantity = quantity;
        this.priceInput = priceInput;
        this.profit = profit;
        this.priceSale = this.profit*this.priceInput;
        this.dateInput = LocalDate.now();
        this.categoryProduct = categoryProduct;
        this.descriptionProduct = descriptionProduct;
    }
    public void setAll(String nameProduct, int quantity,boolean status, double priceInput,double profit, CategoryProduct categoryProduct, String descriptionProduct){
        this.nameProduct = nameProduct;
        this.status = status;
        this.quantity = quantity;
        this.priceInput = priceInput;
        this.profit = profit;
        this.categoryProduct = categoryProduct;
        this.descriptionProduct = descriptionProduct;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(double priceSale) {
        this.priceSale = this.profit*this.priceInput;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceInput() {
        return priceInput;
    }

    public void setPriceInput(double priceInput) {
        this.priceInput = priceInput;
    }

    public LocalDate getDateInput() {
        return dateInput;
    }

    public void setDateInput(LocalDate dateInput) {
        this.dateInput = dateInput;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                ", priceInput=" + priceInput +
                ", priceSale=" + priceSale +
                ", profit=" + profit +
                ", dateInput=" + dateInput +
                ", categoryProduct=" + categoryProduct +
                ", descriptionProduct='" + descriptionProduct + '\'' +
                '}';
    }
    public String toStringUser(){
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", quantity=" + quantity +
                ", priceSale=" + priceSale +
                ", descriptionProduct='" + descriptionProduct + '\'' +
                '}';
    }

}
