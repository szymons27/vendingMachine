package pl.sdaacademy.vending;

import pl.sdaacademy.vending.controller.CustomerOperationController;
import pl.sdaacademy.vending.controller.EmployeeOperationController;
import pl.sdaacademy.vending.controller.service.EmployeeService;
import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.repository.HardDriveVendingMachineRepository;
import pl.sdaacademy.vending.service.DefaultCustomerService;
import pl.sdaacademy.vending.service.DefaultEmployeeService;
import pl.sdaacademy.vending.service.repositories.CustomerService;
import pl.sdaacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdaacademy.vending.util.Configuration;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Configuration configuration = new Configuration();
    private VendingMachineRepository vendingMachineRepository = new HardDriveVendingMachineRepository(configuration);
    private EmployeeService employeeService = new DefaultEmployeeService(vendingMachineRepository, configuration);
    private CustomerService customerService = new DefaultCustomerService(vendingMachineRepository);
    private EmployeeOperationController employeeOperationController = new EmployeeOperationController(employeeService);
    private CustomerOperationController customerOperationController = new CustomerOperationController(customerService);

    private void startApplication() {
        while (true) {
            customerOperationController.printMachine();
            printMenu();
            try {
                UserMenuSelection userSelection = getUserSelection();
                switch (userSelection) {
                    case BUY_PRODUCT:
                        customerOperationController.buyProduct();
                        break;
                    case EXIT:
                        System.out.println("Bye");
                        return;
                    case SERVICE_MENU:
                        handleServiceUser();
                        break;
                    default:
                        System.out.println("Invalid selection");
                }
            } catch (IllegalArgumentException e) {
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

    private void handleServiceUser() {
        while (true) {
            customerOperationController.printMachine();
            printServiceMenu();
            ServiceMenuSelection selection = getServiceSelection();
            switch (selection) {
                case ADD_TRAY:
                    System.out.println("Add tray");
                    employeeOperationController.addTray();
                    break;
                case REMOVE_TRAY:
                    System.out.println("Remove tray");
                    employeeOperationController.removeTray();
                    break;
                case ADD_PRODUCT:
                    employeeOperationController.addProduct();
                    break;
                case REMOVE_PRODUCT:
                    employeeOperationController.removeProduct();
                    break;
                case CHANGE_PRICE:
                    employeeOperationController.changePrice();
                    break;
                case EXIT:
                    System.out.println("going back to user menu");
                    return;
            }
        }

    }

    private ServiceMenuSelection getServiceSelection() {
        System.out.print(" > Your selection: ");
        String userSelection = new Scanner(System.in).nextLine();
        //odczytujemy z tego co wpisuje użytkownik jego wybór
        try {
            Integer menuNumber = Integer.valueOf(userSelection);
            return ServiceMenuSelection.selectionForOptionNumber(menuNumber);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number");
        }
    }

    private void printServiceMenu() {
        ServiceMenuSelection[] allPossibleSelections = ServiceMenuSelection.values();
        for (ServiceMenuSelection menuPosition : allPossibleSelections) {
            System.out.println(menuPosition.getOptionNumber()
                    + ". "
                    + menuPosition.getOptionMessage());
        }
    }
}