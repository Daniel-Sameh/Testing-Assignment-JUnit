package Tests;

import Interfaces.IScrubDigits;
import Interfaces.IScrubEmails;
import Models.ScrubMode;
import Services.MainScrubber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainScrubberTest {

    private IScrubDigits digitScrubber;
    private IScrubEmails emailScrubber;
    private MainScrubber mainScrubber;

    @Before
    public void setUp() {
        digitScrubber = mock(IScrubDigits.class);
        emailScrubber = mock(IScrubEmails.class);
        mainScrubber = new MainScrubber(digitScrubber, emailScrubber);
    }

    // POSITIVE TESTS

    @Test
    public void testScrub_onlyDigitsMode_callsDigitScrubberOnceWithOriginalInput() {
        String input = "hello 123";
        when(digitScrubber.scrub(input)).thenReturn("scrubbedDigits");

        String result = mainScrubber.scrub(input, ScrubMode.ONLY_DIGITS);

        verify(digitScrubber, times(1)).scrub(input);
        verify(emailScrubber, never()).scrub(any());
        assertEquals("scrubbedDigits", result);
    }

    @Test
    public void testScrub_onlyEmailsMode_callsEmailScrubberOnceWithOriginalInput() {
        String input = "hello test@email.com";
        when(emailScrubber.scrub(input)).thenReturn("scrubbedEmails");

        String result = mainScrubber.scrub(input, ScrubMode.ONLY_EMAILS);

        verify(emailScrubber, times(1)).scrub(input);
        verify(digitScrubber, never()).scrub(any());
        assertEquals("scrubbedEmails", result);
    }

    @Test
    public void testScrub_fullScrubbingMode_callsBothInCorrectOrderWithChainedInput() {
        String input = "hello 123 test@email.com";
        String intermediate = "intermediateResult";
        String finalResult = "finalResult";

        when(digitScrubber.scrub(input)).thenReturn(intermediate);
        when(emailScrubber.scrub(intermediate)).thenReturn(finalResult);

        String result = mainScrubber.scrub(input, ScrubMode.FULL_SCRUBBING);

        // Verify order and arguments
        InOrder inOrder = inOrder(digitScrubber, emailScrubber);
        inOrder.verify(digitScrubber).scrub(input);
        inOrder.verify(emailScrubber).scrub(intermediate);

        // Verify each was called exactly once
        verify(digitScrubber, times(1)).scrub(input);
        verify(emailScrubber, times(1)).scrub(intermediate);

        assertEquals(finalResult, result);
    }

    // NEGATIVE TESTS (Input validation)

    @Test
    public void testScrub_nullInput_scrubberThrowsException_returnsNull() {
        when(digitScrubber.scrub(null)).thenThrow(new NullPointerException());

        String result = mainScrubber.scrub(null, ScrubMode.FULL_SCRUBBING);

        assertNull(result);
        verify(digitScrubber, times(1)).scrub(null);
        verify(emailScrubber, never()).scrub(any());
    }

    @Test
    public void testScrub_blankInput_scrubberThrowsException_returnsNull() {
        String input = "   ";
        when(digitScrubber.scrub(input)).thenThrow(new IllegalArgumentException());

        String result = mainScrubber.scrub(input, ScrubMode.FULL_SCRUBBING);

        assertNull(result);
        verify(digitScrubber, times(1)).scrub(input);
        verify(emailScrubber, never()).scrub(any());
    }

    // NEGATIVE TESTS (Exception handling)

    @Test
    public void testScrub_onlyDigitsMode_digitScrubberThrowsException_returnsNullAndEmailNotCalled() {
        String input = "hello 123";
        when(digitScrubber.scrub(input)).thenThrow(new NullPointerException("test NPE"));

        String result = mainScrubber.scrub(input, ScrubMode.ONLY_DIGITS);

        assertNull(result);
        verify(digitScrubber, times(1)).scrub(input);
        verify(emailScrubber, never()).scrub(any());
    }

    @Test
    public void testScrub_onlyEmailsMode_emailScrubberThrowsException_returnsNullAndDigitNotCalled() {
        String input = "test@email.com";
        when(emailScrubber.scrub(input)).thenThrow(new IllegalArgumentException("test IAE"));

        String result = mainScrubber.scrub(input, ScrubMode.ONLY_EMAILS);

        assertNull(result);
        verify(emailScrubber, times(1)).scrub(input);
        verify(digitScrubber, never()).scrub(any());
    }

    @Test
    public void testScrub_fullScrubbingMode_digitScrubberThrowsException_returnsNullAndEmailNotCalled() {
        String input = "hello 123 test@email.com";
        when(digitScrubber.scrub(input)).thenThrow(new NullPointerException("test NPE"));

        String result = mainScrubber.scrub(input, ScrubMode.FULL_SCRUBBING);

        assertNull(result);
        verify(digitScrubber, times(1)).scrub(input);
        verify(emailScrubber, never()).scrub(any());
    }

    @Test
    public void testScrub_fullScrubbingMode_emailScrubberThrowsException_returnsNullAndDigitCalledOnce() {
        String input = "hello 123 test@email.com";
        String intermediate = "intermediate";
        when(digitScrubber.scrub(input)).thenReturn(intermediate);
        when(emailScrubber.scrub(intermediate)).thenThrow(new IllegalArgumentException("test IAE"));

        String result = mainScrubber.scrub(input, ScrubMode.FULL_SCRUBBING);

        assertNull(result);
        verify(digitScrubber, times(1)).scrub(input);
        verify(emailScrubber, times(1)).scrub(intermediate);
    }
}