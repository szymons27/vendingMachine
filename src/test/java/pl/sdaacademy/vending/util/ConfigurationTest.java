package pl.sdaacademy.vending.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ConfigurationTest {

    private Configuration testedConfig;

    @Before
    public void init() {
        testedConfig = new Configuration();
    }

    @Test
    public void shouldReturnDefaultStringValueWhenPropertyIsUnknown() {
        // Given
        String unknownPropertyName = "jdakdjhawkj";
        String expectedDefault = "javaIsAwesome";

        // When
        String propertyValue =
                testedConfig.getStringProperty(unknownPropertyName, expectedDefault);
        // Then
        assertEquals(expectedDefault, propertyValue);
    }

    @Test
    public void shouldReturnDefaultlongValueWhenPropertyIsUnknown() {
        // Given
        String unknownPropertyValue = "dadsadcxzc";
        Long expectedValue = 7L;
        // When
        Long propertyValue = testedConfig
                .getLongProperty(unknownPropertyValue, expectedValue);
        // Then
        assertEquals(expectedValue, propertyValue);
    }

    @Test
    public void shouldReturnSetValue() {
        // Given
        String expectedDefault = "test.property.long";
        Long setProperty = 123L;
        // When
        Long propertyValue = testedConfig
                .getLongProperty(expectedDefault, setProperty);
        // Then
        assertEquals((Long) 123L, propertyValue);
    }

    @Test
    public void shouldReturnSetString() {
        // Given
        String expectedDefault = "qwerty";
        String setProperty = "qwerty";
        // When
        String propertyValue = testedConfig.getStringProperty("test.property.long", expectedDefault);
        // Then
        assertEquals(setProperty, propertyValue);
    }

}