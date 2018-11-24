package pl.sdaacademy.vending.model;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class Tray implements Serializable {
    public static final long serialVersionUID = 1L;
    public static final int MAX_SIZE = 10;
    private String symbol;
    private Long price;
    private Queue<Product> products;

    public Tray(Builder builder) {
        symbol = builder.symbol;
        price = builder.price;
        products = builder.products;

    }


    public static Builder builder(String symbol){return new Builder(symbol);}

    public String getSymbol() {
        return symbol;
    }

    public Long getPrice() {
        return price;
    }


    public Optional<String> firstProductName(){
        return Optional.ofNullable(products.peek()).map(Product::getName);
    }

    public Optional<Product> buyProduct() {
        return Optional.ofNullable(products.poll());
    }

    public boolean addProduct(Product product) {
            if (products.size() < MAX_SIZE){
                return products.add(product);
            } else {
                return false;
            }
    }

    public boolean removeProduct(Product product) {
        if (!products.isEmpty()){
            products.remove(product);
            return true;
        } else {
            return false;
        }
    }

    public void updatedPrice(Long price) {
        this.price = price;
    }


    public static class Builder {

        private String symbol;
        private Long price;
        private Queue<Product> products;

        private Builder(String symbol){
            this.symbol = symbol;
            products = new ArrayDeque<>();
        }

        public Builder product(Product product){
            products.add(product);
            return this;
        }

        public Builder price(Long price){
            this.price = price;
            return this;
        }
        public Tray build(){
            if(price == null || price < 0){
                price = 0L;
            }
            return new Tray(this);
        }

    }

}
