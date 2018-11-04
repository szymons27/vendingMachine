package pl.sdaacademy.vending.vending;

import pl.sdaacademy.vending.model.Tray;
import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.util.StringUtils;

import java.util.Optional;

public class CustomerOperationController {
    private final VendingMachine machine;
    private final Integer trayWidth = 12;

    public CustomerOperationController(VendingMachine machine) {
        this.machine = machine;
    }

    public void printMachine() {
        for (int currentRow = 0; currentRow < machine.rowsCount(); currentRow++) {
            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printUpperBoundary(currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printSymbol(currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printName(currentRow, currentCol);
            }
            System.out.println();

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printPrice(currentRow, currentCol);
            }
            System.out.println();

            //nazwa produktu
            //cena

            for (int currentCol = 0; currentCol < machine.rowsCount(); currentCol++) {
                printLowerBoundary(currentRow, currentCol);
            }
            System.out.println();
        }

    }

    private void printName(int currentRow, int currentCol) {
        Optional<String> productName = machine.productNameAtPosition(currentRow, currentCol);
        String formatedName = productName.orElse("--");
        System.out.print("|" + StringUtils.adjustText(formatedName, trayWidth) + "|");
    }

    private void printPrice(int currentRow, int currentCol) {
        Optional<Tray> tray = machine.getTrayAtPosition(currentRow, currentCol);
        Long price = tray.map(Tray::getPrice).orElse(0L);
        String formatedMoney = StringUtils.formatMoney(price);
        String centredMoney = StringUtils.adjustText(formatedMoney, trayWidth);
        System.out.print("|" + centredMoney + "|");
    }

    private void printUpperBoundary(int currentRow, int currentCol) {
        System.out.print(
                "+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

    private void printSymbol(int rowNo, int colNo) {
        Optional<Tray> tray = machine.getTrayAtPosition(rowNo, colNo);
        String traySymbol = tray.map(Tray::getSymbol).orElse("--");
        System.out.print("|" + StringUtils.adjustText(traySymbol, trayWidth) + "|");
    }

    private void printLowerBoundary(int currentRow, int currentCol) {
        System.out.print("+" + StringUtils.duplicateText("-", trayWidth) + "+");
    }

}
