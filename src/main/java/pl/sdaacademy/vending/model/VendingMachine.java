package pl.sdaacademy.vending.model;

import pl.sdaacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Random;

public class VendingMachine {

    private final Configuration configuration;
    private final Long rowsCount;
    private final Long colsCount;
    private Tray[][] trays;

    public VendingMachine(Configuration configuration) {
        this.configuration = configuration;
        rowsCount = configuration.getLongProperty(
                "machine.size.rows",
                6L);
        if (rowsCount <= 0 || rowsCount > 26) {
            throw new IllegalArgumentException("Row count" + rowsCount + "is invalid");
        }
        colsCount = configuration.getLongProperty(
                "machine.size.cols",
                6L);
        if (colsCount <= 0 || colsCount > 9) {
            throw new IllegalArgumentException("Col count" + colsCount + "is invalid");
        }

        trays = new Tray[rowsCount.intValue()][colsCount.intValue()];
//        Random random = new Random();
//        for (int rowNumber = 0; rowNumber < rowsCount; rowNumber++) {
//            for (int colNumber = 0; colNumber < trays.length; colNumber++) {
//                if (Math.random() < 0.8) {
//                    generateTrayAtPosition(rowNumber, colNumber);
//                    if (Math.random() < 0.5) {
//                        if (Math.random() < 0.1) {
//                        }
//                    }
//                }
//            }
//        }
    }

//    private void generateTrayAtPosition(int rowNumber, int colNumber) {
//        Random random = new Random();
//        long price = random.nextInt(901) + 100;
//        char symbolLetter = (char) ('A' + rowNumber);
//        int symbolNumber = colNumber + 1;
//        String symbol = "" + symbolLetter + symbolNumber;
//
//        Tray.Builder trayBuilder = Tray.builder(symbol).price(price);
//        int productProbability = random.nextInt(10);
//        if (productProbability < 5) {
//            trayBuilder = trayBuilder
//                    .product(new Product("Product " + symbol));
//        }
//        if (productProbability < 1) {
//            trayBuilder = trayBuilder
//                    .product(new Product("Product" + symbol));
//        }
//        trays[rowNumber][colNumber] = trayBuilder.build();
//
//    }

    public Optional<Tray> getTrayAtPosition(int rowNumber, int colNumber) {
        try {
            Tray tray = trays[rowNumber][colNumber];
            Optional<Tray> wrappedTray = Optional.ofNullable(tray);
            return wrappedTray;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
//        //zwróć tackę opakowaną w optional
//        //a jeżeli nie istnieje to pustyt optional

    public Long rowsCount() {
        return rowsCount;
    }

    public Long colsCount() {
        return configuration.getLongProperty(
                "machine.size.cols",
                4L);
    }

    public Optional<String> productNameAtPosition(Integer rowNumber, Integer colNumber) {
        Optional<Tray> tray = getTrayAtPosition(rowNumber, colNumber);
        if (tray.isPresent()) {
            return tray.get().firstProductName();
        } else {
            return Optional.empty();
        }
        //pobierz z trays odpowiednia tacke
        //pobrac nazwe 1 produktu
        //zwrocic optional
    }

    public Optional<Product> buyProductWithSymbol(String traySymbol) {
        if (traySymbol.length() != 2) {
            return Optional.empty();
        }
        char symbolLetter = traySymbol.toUpperCase().charAt(0);
        char symbolNumber = traySymbol.charAt(1);
        int rowNumber = symbolLetter - 'A';
        int colNumber = symbolNumber - '1';
        if (rowNumber < 0 || rowNumber >= rowsCount || colNumber < 0 || colNumber >= colsCount) {
            return Optional.empty();
        }
        Tray tray = trays[rowNumber][colNumber];
        if (tray == null) {
            return Optional.empty();
        } else {
            return tray.buyProduct();
        }

    }

    public boolean placeTray(Tray tray) {
        String traySymbol = tray.getSymbol();
        if (traySymbol.length() != 2){
            return false;
        }
        int rowNumber = traySymbol.charAt(0) - 'A';
        int colNumber = traySymbol.charAt(1) - '1';
        if (rowNumber < 0 || rowNumber >=rowsCount || colNumber < 0 || colNumber >= colsCount){
            return false;
        } else if (trays[rowNumber][colNumber] == null) {
            trays[rowNumber][colNumber] = tray;
            return true;
        }else {
            return false;
        }
    }
}
//        if (productProbability < 1) {
//        Tray tray = Tray
//        .builder(symbol)
//        .price(price)
//        .product(new Product("Product " + symbol))
//        .product(new Product("Product " + symbol))
//        .build();
//        trays[rowNumber][colNumber] = tray;
//        //2 produkt
//        } else if (productProbability < 5) {
//        Tray tray = Tray
//        .builder(symbol)
//        .price(price)
//        .product(new Product("Product " + symbol))
//        .build();
//        trays[rowNumber][colNumber] = tray;
//        //1 produkt
//        } else {
//        Tray tray = Tray
//        .builder(symbol)
//        .price(price)
//        .build();
//        trays[rowNumber][colNumber] = tray;
//        //0 produktów
//        }