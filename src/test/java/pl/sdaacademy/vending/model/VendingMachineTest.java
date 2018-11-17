package pl.sdaacademy.vending.model;

import org.junit.Test;
import pl.sdaacademy.vending.util.Configuration;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class VendingMachineTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenZeroRowsConfigured() {
        // Given
        Configuration config = mock(Configuration.class);
        doReturn(0L)
                .when(config)
                .getLongProperty(eq("machine.size.rows"),
                        anyLong());
        doReturn(4L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.cols"),
                        anyLong()
                );
        // When
        new VendingMachine(config);
        // Then
        fail("Exception should be raised");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTooManyRowsConfigured() {
        // Given
        Configuration config = mock(Configuration.class);
        doReturn(27L)
                .when(config)
                .getLongProperty(eq("machine.size.rows"),
                        anyLong());
        doReturn(5L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.cols"),
                        anyLong()
                );
        // When
        new VendingMachine(config);
        // Then
        fail("Exception should be raised");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenZeroColdConfigured() {
        // Given
        Configuration config = mock(Configuration.class);
        doReturn(0L)
                .when(config)
                .getLongProperty(eq("machine.size.rows"),
                        anyLong());
        doReturn(4L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.cols"),
                        anyLong()
                );
        // When
        new VendingMachine(config);
        // Then
        fail("Exception should be raised");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTooManyColdConfigured() {
        // Given
        Configuration config = mock(Configuration.class);
        doReturn(1L)
                .when(config)
                .getLongProperty(eq("machine.size.rows"),
                        anyLong());
        doReturn(13L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.cols"),
                        anyLong()
                );
        // When
        new VendingMachine(config);
        // Then
        fail("Exception should be raised");

    }

    @Test
    public void ShouldCreateWorkingMachine() {
        // Given
        Configuration config = mock(Configuration.class);
        doReturn(1L)
                .when(config)
                .getLongProperty(eq("machine.size.cols"),
                        anyLong());
        doReturn(6L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.rows"),
                        anyLong()
                );
        // When
        new VendingMachine(config);
        // Then
        System.out.println("Machine created");
    }

    @Test
    public void shouldBeAbleToTrayToEmptySpot() {
        // Given
        Tray tray = Tray.builder("A2").build();
        Configuration config = mock(Configuration.class);
        doReturn(4L)
                .when(config)
                .getLongProperty(eq("machine.size.cols"),
                        anyLong());
        doReturn(6L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.rows"),
                        anyLong()
                );
        VendingMachine testedMachine = new VendingMachine(config);
        // When
        boolean success = testedMachine.placeTray(tray);
        // Then
        assertTrue(success);
        assertEquals(tray, testedMachine.getTrayAtPosition(0, 1).get());
    }
    @Test
    public void shouldNotBeAbleToTrayToTakenSpot() {
        // Given
        Tray tray = Tray.builder("A2").build();
        Tray secondTray = Tray.builder("A2").build();
        Configuration config = mock(Configuration.class);
        doReturn(4L)
                .when(config)
                .getLongProperty(eq("machine.size.cols"),
                        anyLong());
        doReturn(6L)
                .when(config)
                .getLongProperty(
                        eq("machine.size.rows"),
                        anyLong()
                );
        VendingMachine testedMachine = new VendingMachine(config);
        // When
        boolean firstTrayPlacementResult = testedMachine.placeTray(tray);
        boolean secondTrayPlacementResult = testedMachine.placeTray(secondTray);

        // Then
//        assertTrue(firstTrayPlacementResult); //obsolete
        assertFalse(secondTrayPlacementResult);
        assertEquals(tray, testedMachine.getTrayAtPosition(0, 1).get());
    }

}