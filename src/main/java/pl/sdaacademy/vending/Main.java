package pl.sdaacademy.vending;

import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.util.Configuration;
import pl.sdaacademy.vending.vending.CustomerOperationController;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        VendingMachine vendingMachine = new VendingMachine(configuration);
        CustomerOperationController customerOperationController = new CustomerOperationController(vendingMachine);

        customerOperationController.printMachine();
    }
}
