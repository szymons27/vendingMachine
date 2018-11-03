package pl.sdaacademy.vending.model;

import pl.sdaacademy.vending.util.Configuration;

public class VendingMachine {

    private final Configuration configuration;
    private final Long rowsCount;
    private final long colsCount;

    public VendingMachine(Configuration configuration){
        this.configuration = configuration;
        rowsCount = configuration.getLongProperty(
                "machine.size.rows",
                6L);
        if(rowsCount <= 0 || rowsCount >26){
            throw new IllegalArgumentException("Row count" + rowsCount + "is invalid");
        }
        colsCount = configuration.getLongProperty(
                "machine.size.rows",
                6L);
        if(colsCount <= 0 || colsCount >9){
            throw new IllegalArgumentException("Col count" + colsCount + "is invalid");
        }
    }
    public Long rowsCount() {
        return rowsCount;
    }

    public Long colsCount() {

        return configuration.getLongProperty(
                "machine.size.cols",
                4L);
    }
}
