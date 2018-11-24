package pl.sdaacademy.vending.controller;

import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.model.Tray;
import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.service.repositories.CustomerService;
import pl.sdaacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdaacademy.vending.util.StringUtils;

import java.util.Optional;
import java.util.Scanner;

public class CustomerOperationController {
    private final Integer trayWidth = 12;
    private final CustomerService customerService;

    public CustomerOperationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void printMachine() {
        Optional<VendingMachine> loadedMachine = customerService.loadMachineToPrint();
        if (!loadedMachine.isPresent()) {
            System.out.println("Vending Machine out of service");
            return;
        }

        VendingMachine machine = loadedMachine.get();
        for (int currentRow = 0; currentRow < machine.rowsCount(); currentRow++) {
            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printUpperBoundary(machine, currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printSymbol(machine, currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printName(machine, currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printPrice(machine, currentRow, currentCol);
            }
            System.out.println();

            //nazwa produktu
            //cena

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printLowerBoundary(machine, currentRow, currentCol);
            }
            System.out.println();
        }

    }

    public void buyProduct() {
        System.out.println("Choice tray number");
        String traySymbol = new Scanner(System.in).nextLine();
        Optional<Product> boughProduct = customerService.buyProductFromTray(traySymbol);
        if (boughProduct.isPresent()) {
            System.out.println("bough product" + boughProduct.get().getName());
        } else {
            System.out.println("Run out of stock");
        }

    }

    private void printName(VendingMachine machine, int currentRow, int currentCol) {
        Optional<String> productName = machine.productNameAtPosition(currentRow, currentCol);
        String formatedName = productName.orElse("--");
        System.out.print("|" + StringUtils.adjustText(formatedName, trayWidth) + "|");
    }

    private void printPrice(VendingMachine machine, int currentRow, int currentCol) {
        Optional<Tray> tray = machine.getTrayAtPosition(currentRow, currentCol);
        Long price = tray.map(Tray::getPrice).orElse(0L);
        String formatedMoney = StringUtils.formatMoney(price);
        String centredMoney = StringUtils.adjustText(formatedMoney, trayWidth);
        System.out.print("|" + centredMoney + "|");
    }

    private void printUpperBoundary(VendingMachine machine, int currentRow, int currentCol) {
        System.out.print(
                "+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

    private void printSymbol(VendingMachine machine, int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        String traySymbol = tray.map(Tray::getSymbol).orElse("--");
        System.out.print("|" + StringUtils.adjustText(traySymbol, trayWidth) + "|");
    }

    private void printLowerBoundary(VendingMachine machine, int currentRow, int currentCol) {
        System.out.print("+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

}
