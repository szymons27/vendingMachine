package pl.sdaacademy.vending.model;

import pl.sdaacademy.vending.util.Configuration;

import java.util.Optional;

public class VendingMachine {

    private final Configuration configuration;
    private final Long rowsCount;
    private final Long colsCount;
    private Tray[][] trays;

    public VendingMachine(Configuration configuration) {
        this.configuration = configuration;
        rowsCount = configuration.getLongProperty(
                "machine.size.rows",
                6L);
        if (rowsCount <= 0 || rowsCount > 26) {
            throw new IllegalArgumentException("Row count" + rowsCount + "is invalid");
        }
        colsCount = configuration.getLongProperty(
                "machine.size.rows",
                6L);
        if (colsCount <= 0 || colsCount > 9) {
            throw new IllegalArgumentException("Col count" + colsCount + "is invalid");
        }
        //stworzyc tablice 2 wymiarowa
        //do kazdego pola tablicy wpisac nowy obiekt tacki
        //obiekt tacki musi miec ustawiony poprawny symbol

        trays = new Tray[rowsCount.intValue()][colsCount.intValue()];
        for (int rowNumber = 0; rowNumber < rowsCount; rowNumber++) {
            for (int colNumber = 0; colNumber < trays.length; colNumber++) {
                char symbolLetter = (char) ('A' + rowNumber);
                int symbolNumber = colNumber + 1;
                String symbol = "" + symbolLetter + symbolNumber;
                trays[rowNumber][colNumber] = new Tray(symbol);
            }
        }
    }
    public Optional<Tray> getTrayAtPosition(int rowNumber, int colNumber) {
        try {
            Tray tray = trays[rowNumber][colNumber];
            Optional<Tray> wrappedTray = Optional.ofNullable(tray);
            return wrappedTray;
        } catch (ArrayIndexOutOfBoundsException e){
            return Optional.empty();
        }
    }
//        //zwróć tackę opakowaną w optional
//        //a jeżeli nie istnieje to pustyt optional

    public Long rowsCount() {
        return rowsCount;
    }

    public Long colsCount() {

        return configuration.getLongProperty(
                "machine.size.cols",
                4L);
    }
}
