package pl.sdaacademy.vending.controller;

import pl.sdaacademy.vending.model.Tray;

import java.util.Optional;
import java.util.Scanner;

public class EmployeeOperationController {
    public void addTray(){
        System.out.println("insert tray symbol");
        String employeeInput1 = new Scanner(System.in).nextLine();
        Integer traySymbolNumber = Integer.valueOf(employeeInput1);
        System.out.println("insert tray price");
        String employeeInput2 = new Scanner(System.in).nextLine();
        Long trayPrice = Long.valueOf(employeeInput2);
        Tray.builder(employeeInput1)
                .price(trayPrice)
                .build();
        //delegate tray save to service
        //print confirmation or error

        Optional<String> messageToPrint;
        System.out.println(messageToPrint.orElse("success"));
    }
}
