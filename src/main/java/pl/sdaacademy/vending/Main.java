package pl.sdaacademy.vending;

import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.util.Configuration;
import pl.sdaacademy.vending.vending.CustomerOperationController;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    Configuration configuration = new Configuration();
    VendingMachine vendingMachine = new VendingMachine(configuration);
    CustomerOperationController customerOperationController = new CustomerOperationController(vendingMachine);

    private void startApplication() {
        while (true) {
            customerOperationController.printMachine();
            printMenu();
            try {
                UserMenuSelection userSelection = getUserSelection();
                switch (userSelection) {
                    case BUY_PRODUCT:
                        System.out.println("Choice tray number");
                        String userChoice = new Scanner(System.in).nextLine();
                        Optional<Product> boughProduct = customerOperationController.buyProductForSymbol(userChoice);
                        if (boughProduct.isPresent()){
                            System.out.println("bough product" + boughProduct.get().getName());
                        } else {
                            System.out.println("Run out of reinforcements");
                        }
                        break;
                    case EXIT:
                        System.out.println("Bye");
                        return;
                    default:
                        System.out.println("Invalid selection");
                }
            }catch (IllegalArgumentException e){
                System.out.println("invalid selection");
            }
        }
    }

    private void printMenu() {
        UserMenuSelection[] allPossibleSelections = UserMenuSelection.values();
        //values pobiera wszystkie wartosci
        for (UserMenuSelection menuPosition : allPossibleSelections) {
            System.out.println(menuPosition.getOptionNumber()
                    //przechodzimy przez wszystkie opcje
                    + ". "
                    + menuPosition.getOptionText());
        }
    }

    private UserMenuSelection getUserSelection() {
        System.out.print(" > Your selection: ");
        String userSelection = new Scanner(System.in).nextLine();
        //odczytujemy z tego co wpisuje użytkownik jego wybór
        try {
            Integer menuNumber = Integer.valueOf(userSelection);
            return UserMenuSelection.selectionForOptionNumber(menuNumber);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number");
        }
    }

    public static void main(String[] args) {
        new Main().startApplication();
    }
}