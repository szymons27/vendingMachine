package pl.sdaacademy.vending.util;

import org.junit.Test;

import static org.junit.Assert.*;

class StringUtilsTest {
    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
    public void shouldTrimTooLongText() {
        // Given
        String textToAdjust = "ala ma kota";
        Integer expectedLength = 6;
        // When
        String cheatMode = StringUtils.adjustText(textToAdjust,expectedLength);
        // Then
        assertEquals("ala ma", cheatMode);
    }

    @org.junit.jupiter.api.Test
    public void shouldConvertTextInTheMiddle() {
        // Given
        String textToAdjust = "abcd";
        Integer expected = 8;
        // When
        String adjustedText = StringUtils.adjustText(textToAdjust,expected);
        // Then
        assertEquals("  abcd  ",adjustedText);

    }
    @org.junit.jupiter.api.Test
    public void shouldConvertOddTextInTheMiddle() {
       // Given
       String textToAdjust = "abc";
       Integer expected = 8;
       // When
       String adjustedText = StringUtils.adjustText(textToAdjust,expected);
       // Then
       assertEquals("   abc  ",adjustedText);
    }
    //"abcd" -> 8 -> " abcd "
    //"abc" -> 8 -> "  abc  "
}