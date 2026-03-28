package de.tilman_neumann.test.junit.util;
import org.junit.Test;
import static org.junit.Assert.*;

import de.tilman_neumann.test.junit.ClassTest;
import de.tilman_neumann.util.StringUtil;

public class StringUtilTest extends ClassTest {
    // Repeat Function Unit tests
    @Test
    public void testRepeat_NonEmptyStringLengthOne_nEqualsOne() {
        // Arrange
        String input = "a";
        int n = 1;
        // Act
        String result = StringUtil.repeat(input, n);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testRepeat_EmptyString_nEqualsOne() {
        // Arrange
        String input = "";
        int n = 1;
        // Act
        String result = StringUtil.repeat(input, n);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testRepeat_StringLengthGreaterThanOne_nEqualsOne() {
        // Arrange
        String input = "abc";
        int n = 1;
        // Act
        String result = StringUtil.repeat(input, n);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testRepeat_NullString_nEqualsOne() {
        // Arrange
        String input = null;
        int n = 1;
        // Act
        String result = StringUtil.repeat(input, n);
        // Assert
        assertNull(result);
    }

    @Test
    public void testRepeat_NonEmptyStringLengthOne_nLessThanOrEqualToZero() {
        assertNull(StringUtil.repeat("a", 0));
        assertNull(StringUtil.repeat("a", -1));
    }

    @Test
    public void testRepeat_NonEmptyStringLengthOne_nGreaterThanOne() {
        // Arrange
        String input = "a";
        int n = 3;
        String expected = "aaa";
        // Act
        String result = StringUtil.repeat(input, n);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testRepeat_StringLengthGreaterThanOne_nGreaterThanOne() {
        // Arrange
        String input = "abc";
        int n = 3;
        String expected = "abcabcabc";
        // Act
        String result = StringUtil.repeat(input, n);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testRepeat_NullString_nGreaterThanOne() {
        // Null string, n > 1 -> should return null
        assertNull(StringUtil.repeat(null, 2));
        assertNull(StringUtil.repeat(null, 10));
    }

    @Test
    public void testRepeat_EmptyString_nGreaterThanOne() {
        // Empty string, n > 1 -> should return empty string
        assertEquals("", StringUtil.repeat("", 5));
        assertEquals("", StringUtil.repeat("", 100));
    }

    @Test
    public void testRepeat_StringLengthGreaterThanOne_nLessThanOrEqualToZero() {
        // Non-null string with length > 1, n <= 0 -> should return null
        assertNull(StringUtil.repeat("abc", 0));
        assertNull(StringUtil.repeat("abc", -1));
        assertNull(StringUtil.repeat("defg", -5));
    }

    // formatLeft function unit tests

    @Test
    public void testLeftFormat_StringLength1_MaskLengthGreaterThan1() {
        // Arrange
        String input = "abc";
        String mask = "123456";
        String expected = "abc456";
        // Act
        String result = StringUtil.formatLeft(input, mask);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testLeftFormat_NullString_MaskLengthGreaterThan1() {
        // Arrange
        String input = null;
        String mask = "123";
        // Act
        String result = StringUtil.formatLeft(input, mask);
        // Assert
        assertEquals(mask, result);
    }

    @Test
    public void testLeftFormat_EmptyString_MaskLengthGreaterThan1() {
        // Arrange
        String input = "";
        String mask = "123";
        // Act
        String result = StringUtil.formatLeft(input, mask);
        // Arrange
        assertEquals(mask, result);
    }

    @Test
    public void testLeftFormat_StringLengthGreaterThan1_MaskLengthGreaterThan1() {
        // Arrange
        String input = "abcdef";
        String mask = "123";
        // Act
        String result = StringUtil.formatLeft(input, mask);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testLeftFormat_StringLength1_NullMask() {
        // Arrange
        String input = "a";
        String mask = null;
        // Act
        String result = StringUtil.formatLeft(input, mask);
        assertEquals(input, result);
    }

    @Test
    public void testLeftFormat_StringLength1_EmptyMask() {
        // Arrange
        String input = "a";
        String mask = "";
        // Act
        String result = StringUtil.formatLeft(input, mask);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testLeftFormat_StringLength1_MaskLength1() {
        // Arrange
        String input = "a";
        String mask = "1";
        String expected = "a";
        // Act
        String result = StringUtil.formatLeft(input, mask);
        // Assert
        assertEquals(expected, result);
    }

    // formatRight function unit tests

    @Test
    public void testRightFormat_StringLength1_MaskLengthGreaterThan1() {
        // Arrange
        String input = "abc";
        String mask = "123456";
        String expected = "123abc";
        // Act
        String result = StringUtil.formatRight(input, mask);
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testRightFormat_NullString_MaskLengthGreaterThan1() {
        // Arrange
        String input = null;
        String mask = "123";
        // Act
        String result = StringUtil.formatRight(input, mask);
        // Assert
        assertEquals(mask, result);
    }

    @Test
    public void testRightFormat_EmptyString_MaskLengthGreaterThan1() {
        // Arrange
        String input = "";
        String mask = "123";
        // Act
        String result = StringUtil.formatRight(input, mask);
        // Arrange
        assertEquals(mask, result);
    }

    @Test
    public void testRightFormat_StringLengthGreaterThan1_MaskLengthGreaterThan1() {
        // Arrange
        String input = "abcdef";
        String mask = "123";
        // Act
        String result = StringUtil.formatRight(input, mask);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testRightFormat_StringLength1_NullMask() {
        // Arrange
        String input = "a";
        String mask = null;
        // Act
        String result = StringUtil.formatRight(input, mask);
        assertEquals(input, result);
    }

    @Test
    public void testRightFormat_StringLength1_EmptyMask() {
        // Arrange
        String input = "a";
        String mask = "";
        // Act
        String result = StringUtil.formatRight(input, mask);
        // Assert
        assertEquals(input, result);
    }

    @Test
    public void testRightFormat_StringLength1_MaskLength1() {
        // Arrange
        String input = "a";
        String mask = "1";
        String expected = "a";
        // Act
        String result = StringUtil.formatRight(input, mask);
        // Assert
        assertEquals(expected, result);
    }

}
