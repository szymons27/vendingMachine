package pl.sdaacademy.vending.controller;

import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.model.Tray;
import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdaacademy.vending.util.StringUtils;

import java.util.Optional;

public class CustomerOperationController {
    private final Integer trayWidth = 12;
    private final VendingMachineRepository machineRepository;

    public CustomerOperationController(VendingMachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public void printMachine() {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if(!loadedMachine.isPresent()){
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

    public Optional<Product> buyProductForSymbol(String traySymbol){
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if (loadedMachine.isPresent()) {
            VendingMachine machine = loadedMachine.get();
            Optional<Product> boughProduct = machine.buyProductWithSymbol(traySymbol);
            machineRepository.save(machine);
            return boughProduct;
        } else {
            System.out.println("Vending machine out of service");
            return Optional.empty();
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

    private void printUpperBoundary(VendingMachine machine,int currentRow, int currentCol) {
        System.out.print(
                "+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

    private void printSymbol(VendingMachine machine ,int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        String traySymbol = tray.map(Tray::getSymbol).orElse("--");
        System.out.print("|" + StringUtils.adjustText(traySymbol, trayWidth) + "|");
    }

    private void printLowerBoundary(VendingMachine machine, int currentRow, int currentCol) {
        System.out.print("+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

}
