package pl.sdaacademy.vending.controller.service;


import java.util.Optional;

public interface EmployeeService {
    Optional<String> addTray(String traySymbol, Long price);
    Optional<String> removeTrayWithSymbol(String traySymbol);
    Optional<String> addProduct(String traySymbol, String productName, int productNumber);
    Optional<String> changePrice(String traySymbol, Long updatedPrice);
    Optional<String> removeProductWithSymbol(String traySymbol, String productName);
}

