package Tests;

import Interfaces.IScrubDigits;
import Services.DigitScrubber;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScrubDigitsTest {

    @Test
    public void test_scrubDigits_successfully() {
        // Arrange
        String input = "My phone number is 123-456-7890.";
        String expectedOutput = "My phone number is XXX-XXX-XXXX.";
        IScrubDigits scrubber = new DigitScrubber();

        // Act
        String actualOutput = scrubber.scrub(input);

        // Assert
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void test_scrubDigits_throwsException_whenInputIsEmpty() {
        // Arrange
        IScrubDigits scrubber = new DigitScrubber();
        String input = "";

        // Act & Assert
        try {
            scrubber.scrub(input);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void test_scrubDigits_throwsException_whenInputIsNull() {
        // Arrange
        IScrubDigits scrubber = new DigitScrubber();
        String input = null;

        // Act & Assert
        try {
            scrubber.scrub(input);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test
    public void test_scrubDigits_prices() {
        // Arrange
        IScrubDigits scrubber = new DigitScrubber();
        String input = "We sold 5 products by 120$ or $120";
        String expectedOutput = "We sold X products by 120$ or $120";
        // Act
        String actual = scrubber.scrub(input);
        // Assert
        assertEquals(expectedOutput, actual);
    }

    @Test
    public void test_scrubDigits_numbersOnlyInput() {
        // Arrange
        IScrubDigits scrubber = new DigitScrubber();
        String input = "1234567890";
        String expectedOutput = "XXXXXXXXXX";
        // Act
        String actual = scrubber.scrub(input);
        // Assert
        assertEquals(expectedOutput, actual);
    }

    @Test
    public void test_scrubDigits_multipleNumbersInput() {
        // Arrange
        IScrubDigits scrubber = new DigitScrubber();
        String input = "1234567890 and also 548482";
        String expectedOutput = "XXXXXXXXXX and also XXXXXX";
        // Act
        String actual = scrubber.scrub(input);
        // Assert
        assertEquals(expectedOutput, actual);
    }
}
