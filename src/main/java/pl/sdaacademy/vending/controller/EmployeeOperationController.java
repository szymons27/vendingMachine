package pl.sdaacademy.vending.controller;

import pl.sdaacademy.vending.controller.service.EmployeeService;
import pl.sdaacademy.vending.model.Tray;

import java.util.Optional;
import java.util.Scanner;

public class EmployeeOperationController {
    private final EmployeeService employeeService;

    public EmployeeOperationController(EmployeeService employeeService){this.employeeService = employeeService;}

    public void addTray(){
        String traySymbol = getTraySymbolFromUser();
        System.out.println(" > Provide tray symbol: ");
        String symbol = getUserInput();
        System.out.println(" > Provide tray price");
        Long price = Long.parseLong(getUserInput());
        Long trayPice = getTrayPriceFromUser();
        Tray newTray = Tray.builder(symbol).price(price).build();
        Optional<String> errorMessage = employeeService.addTray(newTray);
        System.out.println(errorMessage.orElse("success"));
    }

    private Long getTrayPriceFromUser() {
        Long price = null;
        while (price == null){
            System.out.println(" > Provide tray price");
            Long.parseLong(getUserInput());
        }
        return price;
    }

    private String getTraySymbolFromUser(){
        System.out.println(" > Provide tray symbol: ");
        return getUserInput().toUpperCase();
    }

    private String getUserInput() {
        return new Scanner(System.in).nextLine();
    }
}