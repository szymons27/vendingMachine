package pl.sdaacademy.vending.model;

import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TrayTest {
    @Test
    public void shouldBeAbleToBuyLastAvilableProduct() {
       // Given
        Product definedProduct = new Product("P1");
        Tray tray = Tray.builder("A1")
                .product(definedProduct)
                .build();
        // When
       Optional<Product> boughtProduct = tray.buyProduct();
       // Then
       assertTrue(boughtProduct.isPresent());
       assertEquals(definedProduct, boughtProduct.get());
    }

    @Test
    public void shouldAddProductToTray() {
       // Given
       Product definedProduct = new Product("P1");
       Tray tray = Tray.builder("A1").build();

       // When
       boolean boughProduct = tray.addProduct(definedProduct);

       // Then
        assertTrue(boughProduct);

    }
        @Test
    public void shouldNotAddMoreThan10ProductToTray() {
       // Given
       Product definedProduct = new Product("P1");
       Tray tray = Tray.builder("A1").build();
       for (int productNumber = 0; productNumber < Tray.MAX_SIZE; productNumber++){
           tray.addProduct(definedProduct);
       }
       // When
       boolean boughProduct = tray.addProduct(definedProduct);

       // Then
        assertFalse(boughProduct);
    }


}