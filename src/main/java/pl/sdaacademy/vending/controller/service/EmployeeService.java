package pl.sdaacademy.vending.controller.service;

import pl.sdaacademy.vending.model.Tray;

import java.util.Optional;

public interface EmployeeService {
    Optional<String> addTray(Tray tray);
}
