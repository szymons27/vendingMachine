package pl.sdaacademy.vending;

import java.util.Arrays;

public enum ServiceMenuSelection {
    ADD_TRAY(1, "Add new tray to machine"),
    REMOVE_TRAY(2, "Remove tray to machine"),
    ADD_PRODUCT(3, "add product to tray"),
    REMOVE_PRODUCT(4, "remove product from tray"),
    CHANGE_PRICE(5, "change price of actual tray"),
    EXIT(9, "Exit from service menu");

    private final Integer optionNumber;
    private final String optionMessage;

    ServiceMenuSelection(Integer optionNumber, String optionMessage){
        this.optionNumber = optionNumber;
        this.optionMessage = optionMessage;
    }

    public Integer getOptionNumber() {
        return optionNumber;
    }

    public String getOptionMessage() {
        return optionMessage;
    }

    public static ServiceMenuSelection selectionForOptionNumber(Integer requestedOptionNumber){
        return Arrays.stream(values())
                .filter(enumValue -> enumValue.getOptionNumber().equals(requestedOptionNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown option number: " + requestedOptionNumber));
    }
}
