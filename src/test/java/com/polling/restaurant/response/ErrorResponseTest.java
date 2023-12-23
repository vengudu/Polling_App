package com.polling.restaurant.response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String expectedMessage = "Test Message";
        String expectedDetails = "Test Details";

        // Act
        ErrorResponse errorResponse = new ErrorResponse(expectedMessage, expectedDetails);

        // Assert
        assertEquals(expectedMessage, errorResponse.getMessage());
        assertEquals(expectedDetails, errorResponse.getDetails());
    }

    @Test
    void testSetters() {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse("Initial Message", "Initial Details");
        String newMessage = "New Message";
        String newDetails = "New Details";

        // Act
        errorResponse.setMessage(newMessage);
        errorResponse.setDetails(newDetails);

        // Assert
        assertEquals(newMessage, errorResponse.getMessage());
        assertEquals(newDetails, errorResponse.getDetails());
    }
}
