package pl.sdaacademy.vending.service;

import pl.sdaacademy.vending.model.Product;
import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.service.repositories.CustomerService;
import pl.sdaacademy.vending.service.repositories.VendingMachineRepository;

import java.util.Optional;

public class DefaultCustomerService implements CustomerService {
    private final VendingMachineRepository machineRepository;

    public DefaultCustomerService(VendingMachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }
    @Override
    public Optional<Product> buyProductFromTray(String traySymbol) {
            Optional<VendingMachine> loadedMachine = machineRepository.load();
            if (loadedMachine.isPresent()) {
                VendingMachine machine = loadedMachine.get();
                Optional<Product> boughProduct = machine.buyProductWithSymbol(traySymbol);
                machineRepository.save(machine);
                return boughProduct;
            } else {
                return Optional.empty();
            }
    }

    @Override
    public Optional<VendingMachine> loadMachineToPrint() {
        return machineRepository.load();
    }
}
