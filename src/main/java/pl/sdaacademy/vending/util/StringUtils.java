package pl.sdaacademy.vending.util;

public class StringUtils {
    public static String adjustText(String text, Integer expectedLength){
        String expandedText = text;
        while (expandedText.length() < expectedLength){
            expandedText = " " + expandedText + " ";
        }
        return expandedText.substring(0, expectedLength);
    }

    public static String formatMoney(Long amount){
        return formatMoneyIntegrals(amount)
                + ","
                + formattedMoneyDecimals(amount);
    }

    private static String formatMoneyIntegrals(Long amount){
        String integrals = Long.toString(amount / 100);
        StringBuilder formatedMoney = new StringBuilder();
        Integer charactersTillLastSpace = 0;
        for (int charIndex = integrals.length() - 1; charIndex >=0; charIndex--){
            charactersTillLastSpace++;
            formatedMoney = formatedMoney.append(integrals.charAt(charIndex));
            if (charactersTillLastSpace >= 3) {
                formatedMoney = formatedMoney.append(" ");
                charactersTillLastSpace = 0;
            }
        }
        return formatedMoney.reverse().toString().trim();
    }

    private static String formattedMoneyDecimals(Long amount){
        String decimals = Long.toString(amount % 100);
        if (decimals.length() < 2) {
            decimals = "0" + decimals;
        }
        return decimals;
    }
}
