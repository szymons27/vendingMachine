package pl.sdaacademy.vending.controller;

import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.model.TraySnapshot;
import pl.sdaacademy.vending.model.VendingMachineSnapshot;
import pl.sdaacademy.vending.service.repositories.CustomerService;
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
        Optional<VendingMachineSnapshot> loadedMachine = customerService.loadMachineToPrint();
        if (!loadedMachine.isPresent()) {
            System.out.println("Vending Machine out of service");
            return;
        }

        VendingMachineSnapshot machine = loadedMachine.get();
        for (int currentRow = 0; currentRow < machine.getRowCount(); currentRow++) {
            for (int currentCol = 0; currentCol < machine.getColsCount(); currentCol++) {
                printUpperBoundary(machine, currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.getRowCount(); currentCol++) {
                printSymbol(machine, currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.getRowCount(); currentCol++) {
                printName(machine, currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.getRowCount(); currentCol++) {
                printPrice(machine, currentRow, currentCol);
            }
            System.out.println();

            //nazwa produktu
            //cena

            for (int currentCol = 0; currentCol < machine.getRowCount(); currentCol++) {
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

    private void printName(VendingMachineSnapshot machine, int currentRow, int currentCol) {
        String formatedName = machine.getTray(currentRow,currentCol).map(TraySnapshot::getProduct).orElse("--");
        System.out.print("|" + StringUtils.adjustText(formatedName, trayWidth) + "|");
    }

    private void printPrice(VendingMachineSnapshot machine, int currentRow, int currentCol) {
        Long price = machine.getTray(currentRow,currentCol).map(TraySnapshot::getPrice).orElse(0L);

        String formatedMoney = StringUtils.formatMoney(price);
        String centredMoney = StringUtils.adjustText(formatedMoney, trayWidth);
        System.out.print("|" + centredMoney + "|");
    }

    private void printUpperBoundary(VendingMachineSnapshot machine, int currentRow, int currentCol) {
        System.out.print(
                "+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

    private void printSymbol(VendingMachineSnapshot machine, int rowNo, int colNo) {
        Optional<TraySnapshot> tray = machine.getTray(rowNo, colNo);
        String traySymbol = tray.map(TraySnapshot::getSymbol).orElse("--");
        System.out.print("|" + StringUtils.adjustText(traySymbol, trayWidth) + "|");
    }

    private void printLowerBoundary(VendingMachineSnapshot machine, int currentRow, int currentCol) {
        System.out.print("+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

}
