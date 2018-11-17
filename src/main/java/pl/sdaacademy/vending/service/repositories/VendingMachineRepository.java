package pl.sdaacademy.vending.service.repositories;

import pl.sdaacademy.vending.model.VendingMachine;

import java.util.Optional;

public interface VendingMachineRepository {
    VendingMachine save(VendingMachine machine);
    Optional<VendingMachine> load();
}

