package Tests;

import Interfaces.IScrubEmails;
import Services.EmailScrubber;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScrubEmailsTest {

    @Test
    public void test_scrubEmails_correctEmail() {
        // Arrange
        IScrubEmails scrubber = new EmailScrubber();
        String input = "user.name@gmail.com + first.last@sub.domain.co.uk";
        String expected = "[EMAIL_HIDDEN] + [EMAIL_HIDDEN]";
        // Act
        String actual = scrubber.scrub(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void test_scrubEmails_emailWithSpecialCharacters() {
        // Arrange
        IScrubEmails scrubber = new EmailScrubber();
        String input = "dan+-@mail.com";
        String expected = "[EMAIL_HIDDEN]";
        // Act
        String actual = scrubber.scrub(input);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void test_scrubEmails_invalidEmail() {
        // Arrange
        IScrubEmails scrubber = new EmailScrubber();
        String input = "notanemail + user@domain";
        // Act
        String actual = scrubber.scrub(input);
        // Assert
        assertEquals(input, actual);
    }

    @Test
    public void test_scrubEmails_throwsException_whenInputIsEmpty() {
        // Arrange
        IScrubEmails scrubber = new EmailScrubber();
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
    public void test_scrubEmails_throwsException_whenInputIsNull() {
        // Arrange
        IScrubEmails scrubber = new EmailScrubber();
        String input = null;

        // Act & Assert
        try {
            scrubber.scrub(input);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }
}
