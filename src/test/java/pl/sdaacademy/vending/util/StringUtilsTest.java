package pl.sdaacademy.vending.util;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class StringUtilsTest {
    @Test
    public void shouldReturnUnmodifedTextIfLengthMatched() {
        // Given
        String textToAdjust = "Ala ma kota";
        Integer expectedLength = 11;
        // When
        String adjustText = StringUtils.adjustText(textToAdjust, expectedLength);
        // Then
        assertEquals("Ala ma kota", adjustText);
    }

    @Test
    public void shouldTrimTooLongText() {
        // Given
        String textToAdjust = "ala ma kota";
        Integer expectedLength = 6;
        // When
        String cheatMode = StringUtils.adjustText(textToAdjust,expectedLength);
        // Then
        assertEquals("ala ma", cheatMode);
    }

    @Test
    public void shouldConvertTextInTheMiddle() {
        // Given
        String textToAdjust = "abcd";
        Integer expected = 8;
        // When
        String adjustedText = StringUtils.adjustText(textToAdjust,expected);
        // Then
        assertEquals("  abcd  ",adjustedText);

    }
    @Test
    public void shouldConvertOddTextInTheMiddle() {
       // Given
       String textToAdjust = "abc";
       Integer expected = 8;
       // When
       String adjustedText = StringUtils.adjustText(textToAdjust,expected);
       // Then
       assertEquals("   abc  ",adjustedText);
    }
    @Test
    @Parameters
    public void shouldProperlyFormatMoney(Long  amountToFormat, String expectedResult) {
       // Given - in parameters
       // When
        String formatedMoney = StringUtils.formatMoney(amountToFormat);
        // Then
       assertEquals(expectedResult,formatedMoney);
    }

    public Object[] parametersForShouldProperlyFormatMoney() {
        return new Object[]{
                new Object[]{123L, "1,23"},
                new Object[]{0L, "0,00"},
                new Object[]{5L, "0,05"},
                new Object[]{12L, "0,12"},
                new Object[]{1234L, "12,34"},
                new Object[]{12345L, "123,45"},
                new Object[]{123456L, "1234,56"},
                new Object[]{1234567L, "12 345,67"},
                new Object[]{12345678L, "123 456,78"},
                new Object[]{123456789L, "1 234 567,89"},
                new Object[]{1234567892135324L, "12 345 678 921 353,24"}
        };
    }
}