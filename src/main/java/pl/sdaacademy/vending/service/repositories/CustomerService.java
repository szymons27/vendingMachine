package pl.sdaacademy.vending.service.repositories;

import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.model.VendingMachine;

import java.util.Optional;

public interface CustomerService {
    Optional<Product> buyProductFromTray(String traySymbol);
    Optional<VendingMachine> loadMachineToPrint();
}
