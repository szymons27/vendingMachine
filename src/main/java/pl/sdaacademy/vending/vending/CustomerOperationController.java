package pl.sdaacademy.vending.vending;

import pl.sdaacademy.vending.model.VendingMachine;

public class CustomerOperationController {
    private VendingMachine machine;

    public CustomerOperationController() {
        machine = new VendingMachine();
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
        System.out.print("+-----------------+");
    }

    private void printSymbol(int currentRow, int currentCol) {
        char symbolLetter = (char) ('A' + currentRow);
        int symbolNumber = currentCol + 1;
        System.out.print("|        " + symbolLetter + symbolNumber + "       |");
    }

    private void printLowerBoundary(int currentRow, int currentCol) {
        System.out.print("+-----------------+");
    }
}
