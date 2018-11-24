package pl.sdaacademy.vending.controller;

import pl.sdaacademy.vending.controller.service.EmployeeService;
import pl.sdaacademy.vending.model.Tray;

import java.util.Optional;
import java.util.Scanner;

public class EmployeeOperationController {
    private final EmployeeService employeeService;

    public EmployeeOperationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void addProduct(){
        String traySymbol = getTraySymbolFromUser();
        String productName = getProductNameFromUser();
        Integer numberOfProduct = getNumberOfProduct();
        Optional<String> errorMessage = employeeService.addProduct(traySymbol, productName, numberOfProduct);
        System.out.println(errorMessage.orElse("success"));

    }

    private Integer getNumberOfProduct() {
        Integer numberOfProducts = null;
        while (numberOfProducts == null) {
            System.out.println(" > Provide number of products");
            try {
                numberOfProducts = Integer.parseInt(getUserInput());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of products. Try again.");
            }
        }
        return numberOfProducts;
    }

    private String getProductNameFromUser() {
        System.out.println(" > Provide product name: ");
        return getUserInput().toUpperCase();
    }

    public void addTray() {
        String traySymbol = getTraySymbolFromUser();
        Long trayPice = getTrayPriceFromUser();
        Tray newTray = Tray.builder(traySymbol).price(trayPice).build();
        Optional<String> errorMessage = employeeService.addTray(newTray);
        System.out.println(errorMessage.orElse("success"));
    }

    public void removeTray() {
        String traySymbol = getTraySymbolFromUser();
        Optional<String> errorMessage = employeeService.removeTrayWithSymbol(traySymbol);
        System.out.println(errorMessage.orElse("success"));

    }

    private Long getTrayPriceFromUser() {
        Long price = null;
        while (price == null) {
            System.out.println(" > Provide tray price");
            try {
                price = Long.parseLong(getUserInput());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Try again.");
            }
        }
        return price;
    }

    private String getTraySymbolFromUser() {
        System.out.println(" > Provide tray symbol: ");
        return getUserInput().toUpperCase();
    }

    private String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

    public void changePrice(){
        //pobierz od użytkownika symbol tacki, nową cenę
        //wywołać odpowiednią metodę z serwisu

        //wyświetlić komunikat błędu lub potwierdzenie udanej operacji
        Optional<String> errorMessage = employeeService.removeTrayWithSymbol(traySymbol);
        System.out.println(errorMessage.orElse("success"));
    }
}
