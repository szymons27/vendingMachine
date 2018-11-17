package pl.sdaacademy.vending.service;

import pl.sdaacademy.vending.controller.service.EmployeeService;
import pl.sdaacademy.vending.model.Tray;
import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdaacademy.vending.util.Configuration;

import java.util.Optional;

public class DefaultEmployeeService implements EmployeeService {
    private final VendingMachineRepository machineRepository;
    private final Configuration configuration;

    public DefaultEmployeeService(VendingMachineRepository machineRepository, Configuration configuration) {
        this.machineRepository = machineRepository;
        this.configuration = configuration;
    }

    @Override
    public Optional<String> addTray(Tray tray) {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        VendingMachine vendingMachine = loadedMachine.orElseGet(() -> new VendingMachine(configuration));
        if (vendingMachine.placeTray(tray)) {
            machineRepository.save(vendingMachine);
            return Optional.empty();
        } else {
            return Optional.of("Could not set up tray, please check provided values");
        }
    }
}
