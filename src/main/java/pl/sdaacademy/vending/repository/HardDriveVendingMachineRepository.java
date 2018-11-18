package pl.sdaacademy.vending.repository;

import pl.sdaacademy.vending.model.VendingMachine;
import pl.sdaacademy.vending.service.repositories.VendingMachineRepository;
import pl.sdaacademy.vending.util.Configuration;
import sun.security.krb5.Config;

import java.io.*;
import java.util.Optional;

public class HardDriveVendingMachineRepository implements VendingMachineRepository {
    private final String repoLocation;

    public HardDriveVendingMachineRepository(Configuration configuration) {
        repoLocation = configuration.getStringProperty("repository.harddrive.vendingMachine.path",
                "VendingMachine.ser"
        );
    }

    @Override
    public VendingMachine save(VendingMachine machine) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(repoLocation))) {
            objectOutputStream.writeObject(machine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return machine;
    }

    @Override
    public Optional<VendingMachine> load() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(
                repoLocation))) {
            VendingMachine deserialized = (VendingMachine) objectInputStream.readObject();
            System.out.println(deserialized);
            return Optional.ofNullable(deserialized);
        } catch (IOException e) {
            System.out.println(" Vending Machine repo file not found");
        } catch (ClassNotFoundException e) {
            System.out.println("could not find Vending Machine class");
        }
        return Optional.empty();
    }
}
