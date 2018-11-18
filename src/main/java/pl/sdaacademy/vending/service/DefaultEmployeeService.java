package pl.sdaacademy.vending.service;

import pl.sdaacademy.vending.controller.service.EmployeeService;
import pl.sdaacademy.vending.model.Product;
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

    @Override
    public Optional<String> removeTrayWithSymbol(String traySymbol) {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if (loadedMachine.isPresent()){
            VendingMachine machine = loadedMachine.get();
            Optional<Tray> removedTray = machine.removeTrayWithSymbol(traySymbol);
            if (removedTray.isPresent()){
                machineRepository.save(machine);
                return Optional.empty();
            } else {
                return Optional.of("tray could not be removed");
            }

        } else {
            return Optional.of("there is no vending machine");
        }
    }

    @Override
    public Optional<String> addProduct(String traySymbol, String productName, int productNumber) {
        Optional<VendingMachine> loadedMachine = machineRepository.load();
        if (!loadedMachine.isPresent()){
            System.out.println("there is no vending machine, add one by creating it");
        }
        VendingMachine machine = loadedMachine.get();
        for (int addedProductCount = 0; addedProductCount < productNumber; addedProductCount++) {
            Product product = new Product(productName);
            if (!machine.addProductToTray(traySymbol, product)) {
                machineRepository.save(machine);
                return Optional.of("tray overloaded, cannot apply" + (productNumber - addedProductCount) + "products");
            }
        }
        machineRepository.save(machine);
        return Optional.empty();
    }
}
