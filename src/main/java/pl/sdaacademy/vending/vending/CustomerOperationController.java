package pl.sdaacademy.vending.vending;

import pl.sdaacademy.vending.model.Tray;
import pl.sdaacademy.vending.model.VendingMachine;

import java.util.Optional;

public class CustomerOperationController {
    private final VendingMachine machine;

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
                printLowerBoundary(currentRow, currentCol);
            }
            System.out.println();
        }

    }

    private void printUpperBoundary(int currentRow, int currentCol) {
        System.out.print("+--------+");
    }

    private void printSymbol(int rowNo, int colNo) {
            Optional<Tray> tray = machine.getTrayAtPosition(rowNo,colNo);
            String traySymbol = tray.map(Tray::getSymbol).orElse("--");
            System.out.print("|   " + traySymbol + "   |");
        }

    private void printLowerBoundary(int currentRow, int currentCol) {
        System.out.print("+--------+");
    }
}
