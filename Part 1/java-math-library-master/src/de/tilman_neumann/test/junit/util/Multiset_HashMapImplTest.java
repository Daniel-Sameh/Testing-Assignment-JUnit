package de.tilman_neumann.test.junit.util;

import de.tilman_neumann.util.Multiset;
import org.junit.Test;

import static org.junit.Assert.*;

import de.tilman_neumann.test.junit.ClassTest;
import de.tilman_neumann.util.Multiset_HashMapImpl;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class Multiset_HashMapImplTest extends ClassTest {

    // Remove(object) unit tests
    @Test
    public void testRemove_nullObject() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<Integer>();
        // Act
        Integer old = multiset.remove(null);
        // Assert
        assertEquals(null, old);
    }

    @Test
    public void testRemove_notNullObject_matchType_zeroMultiplicity() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        Integer old = multiset.remove(5);
        // Assert
        assertEquals(null, old);
    }

    @Test
    public void testRemove_notNullObject_MatchType_oneMultiplicity() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        Integer expected = 1;
        multiset.add(5, expected);
        // Act
        Integer newValue = multiset.remove(5);
        // Assert
        assertEquals(expected, newValue);
    }

    @Test
    public void testRemove_notNullObject_MatchType_moreThanOneMultiplicity() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        Integer expected = 2;
        multiset.add(5, expected);
        // Act
        Integer newValue = multiset.remove(5);
        // Assert
        assertEquals(expected, newValue);
    }

    @Test
    public void testRemove_notNullObject_notMatchType(){
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<Integer>();
        // Act
        Integer old = multiset.remove("abc");
        // Assert
        assertNull(old);
    }

    // remove(T key, int mult) tests
    @Test
    public void testRemove_KeyNotNull_PresentOnce_RemoveOne(){
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        // Act
        int old = multiset.remove((Integer) 5, 1);
        // Assert
        assertEquals(1, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemove_KeyNotNull_NotPresent_RemoveOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        int old = multiset.remove((Integer) 5, 1);
        // Assert
        assertEquals(0, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemove_KeyNotNull_PresentMultiple_RemoveOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 3);
        // Act
        int old = multiset.remove((Integer) 5, 1);
        // Assert
        assertEquals(3, old);
        assertEquals(2, multiset.get(5).intValue());

    }

    @Test
    public void testRemove_KeyNotNull_PresentOnce_RemoveZeroOrNegative() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        // Act
        int old = multiset.remove((Integer) 5, -1);
        // Assert
        assertEquals(1, old);
        assertEquals(1, multiset.get(5).intValue());
    }

    @Test
    public void testRemove_KeyNotNull_PresentOnce_RemoveMoreThanOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        // Act
        int old = multiset.remove((Integer) 5, 2);
        // Assert
        assertEquals(1, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemove_NullKey_NotPresent_RemoveOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        int old =  multiset.remove(null, 1);
        // Assert
        assertEquals(0, old);
    }

    @Test
    public void testRemove_KeyNotNull_PresentMultiple_RemoveMoreThanOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 3);
        // Act
        int old = multiset.remove((Integer) 5, 4);
        // Assert
        assertEquals(3, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemove_KeyNotNull_NotPresent_RemoveMoreThanOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        int old = multiset.remove((Integer) 5, 4);
        // Assert
        assertEquals(0, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemove_KeyNotNull_PresentMultiple_RemoveZeroOrNegative() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 3);
        // Act
        int old = multiset.remove((Integer) 5, -1);
        // Assert
        assertEquals(3, old);
        assertEquals(3, multiset.get(5).intValue());
    }

    @Test
    public void testRemove_NullKey_PresentOnce_RemoveMoreThanOne() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(null);
        // Act
        int old = multiset.remove(null, 2);
        // Assert
        assertEquals(1, old);
        assertFalse(multiset.containsKey(null));
    }

    // int removeAll(T key) test cases
    @Test
    public void testRemoveAll_KeyNull_NotPresent() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        int old = multiset.removeAll(null);
        // Assert
        assertEquals(0, old);
        assertFalse(multiset.containsKey(null));
    }

    @Test
    public void testRemoveAll_KeyNull_PresentOnce() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(null);
        // Act
        int old = multiset.removeAll(null);
        // Assert
        assertEquals(1, old);
        assertFalse(multiset.containsKey(null));
    }

    @Test
    public void testRemoveAll_KeyNull_PresentMultiple() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(null, 3);
        // Act
        int old = multiset.removeAll(null);
        // Assert
        assertEquals(3, old);
        assertFalse(multiset.containsKey(null));
    }

    @Test
    public void testRemoveAll_KeyNotNull_NotPresent() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        int old = multiset.removeAll(5);
        // Assert
        assertEquals(0, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemoveAll_KeyNotNull_PresentOnce() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        // Act
        int old = multiset.removeAll(5);
        // Assert
        assertEquals(1, old);
        assertFalse(multiset.containsKey(5));
    }

    @Test
    public void testRemoveAll_KeyNotNull_PresentMultiple() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 4);
        // Act
        int old = multiset.removeAll(5);
        // Assert
        assertEquals(4, old);
        assertFalse(multiset.containsKey(5));
    }


    // intersect(Multiset<T> other) test cases
    @Test
    public void testIntersect_OtherNull_CurrentEmpty() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        // Act
        Multiset<Integer> result = multiset.intersect(null);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testIntersect_OtherEmpty_CurrentSize1() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        Multiset_HashMapImpl<Integer> other = new Multiset_HashMapImpl<>();
        // Act
        Multiset<Integer> result = multiset.intersect(other);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testIntersect_OtherEmpty_CurrentSizeGreater1() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 3);
        Multiset_HashMapImpl<Integer> other = new Multiset_HashMapImpl<>();
        // Act
        Multiset<Integer> result = multiset.intersect(other);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testIntersect_OtherSize1_CurrentEmpty() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<Integer> other = new Multiset_HashMapImpl<>();
        other.add(5);
        // Act
        Multiset<Integer> result = multiset.intersect(other);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testIntersect_OtherNull_CurrentSize1() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        // Act
        Multiset<Integer> result = multiset.intersect(null);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testIntersect_OtherSize1_CurrentSizeGreater1_Overlap() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 3);
        multiset.add(1); multiset.add(2);
        Multiset_HashMapImpl<Integer> other = new Multiset_HashMapImpl<>();
        other.add(5);
        // Act
        Multiset_HashMapImpl<Integer> result =
                (Multiset_HashMapImpl<Integer>) multiset.intersect(other);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(5));
        assertEquals(1, result.get(5).intValue());
    }

    @Test
    public void testIntersect_OtherSizeGreater1_CurrentEmpty() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<Integer> other = new Multiset_HashMapImpl<>();
        other.add(5, 3);
        other.add(1); other.add(2);
        // Act
        Multiset<Integer> result = multiset.intersect(other);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testIntersect_OtherSizeGreater1_CurrentSize1_Overlap() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5);
        Multiset_HashMapImpl<Integer> other = new Multiset_HashMapImpl<>();
        other.add(5, 2);
        other.add(1); other.add(2);
        // Act
        Multiset_HashMapImpl<Integer> result =
                (Multiset_HashMapImpl<Integer>) multiset.intersect(other);
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(5));
        assertEquals(1, result.get(5).intValue());
    }

    @Test
    public void testIntersect_OtherNull_CurrentSizeGreater1() {
        // Arrange
        Multiset_HashMapImpl<Integer> multiset = new Multiset_HashMapImpl<>();
        multiset.add(5, 3);
        multiset.add(1); multiset.add(2);
        // Act
        Multiset<Integer> result = multiset.intersect(null);
        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }
    
    // totalCount test cases
    @Test
    public void testTotalCount_multipleElements_allMultiplicityOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        assertEquals(3,multiset.totalCount());
    }

    @Test
    public void testTotalCount_emptyMultiset_returnsZero() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        assertEquals(0, multiset.totalCount());
    }

    @Test
    public void testTotalCount_oneElement_multiplicityOne(){
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        assertEquals(1,multiset.totalCount());
    }

    @Test
    public void testTotalCount_multipleElements_someMultiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 3);
        multiset.add("b");
        multiset.add("c", 2);
        assertEquals(6, multiset.totalCount());
    }

    @Test
    public void testTotalCount_multipleElements_allMultiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 2);
        multiset.add("b", 3);
        multiset.add("c", 4);
        assertEquals(9, multiset.totalCount());
    }
    @Test
    public void testTotalCount_oneElement_multiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 3);
        assertEquals(3, multiset.totalCount());
    }

    @Test
    public void testToList_multipleElements_allMultiplicityOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        List<String> result = multiset.toList();

        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }

    @Test
    public void testToList_emptyMultiset_returnsEmptyList() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        List<String> result = multiset.toList();

        assertEquals(0, result.size());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testToList_oneElement_multiplicityOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        List<String> result = multiset.toList();

        assertEquals(1, result.size());
        assertTrue(result.contains("a"));
    }

    @Test
    public void testToList_multipleElements_someMultiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 3);
        multiset.add("b");
        multiset.add("c", 2);
        List<String> result = multiset.toList();

        assertEquals(6, result.size());
        assertEquals(3, result.stream().filter(x -> x.equals("a")).count());
        assertEquals(1, result.stream().filter(x -> x.equals("b")).count());
        assertEquals(2, result.stream().filter(x -> x.equals("c")).count());
    }

    @Test
    public void testToList_multipleElements_allMultiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 2);
        multiset.add("b", 3);
        multiset.add("c", 4);
        List<String> result = multiset.toList();

        assertEquals(9, result.size());
        assertEquals(2, result.stream().filter(x -> x.equals("a")).count());
        assertEquals(3, result.stream().filter(x -> x.equals("b")).count());
        assertEquals(4, result.stream().filter(x -> x.equals("c")).count());
    }

    @Test
    public void testToString_multipleElements_allMultiplicityOne(){
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        String result = multiset.toString();

        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        assertFalse(result.contains("^"));
    }
    @Test
    public void testToString_emptyMultiset_returnsEmptyBraces() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        assertEquals("{}", multiset.toString());
    }

    @Test
    public void testToString_oneElement_multiplicityOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        assertEquals("{a}", multiset.toString());
    }

    @Test
    public void testToString_multipleElements_someMultiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 3);
        multiset.add("b");
        multiset.add("c", 2);
        String result = multiset.toString();

        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("a^3"));
        assertTrue(result.contains("c^2"));
        assertTrue(result.contains("b"));
        assertFalse(result.contains("b^"));
    }

    @Test
    public void testToString_multipleElements_allMultiplicityGreaterThanOne() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a", 2);
        multiset.add("b", 3);
        multiset.add("c", 4);
        String result = multiset.toString();

        assertTrue(result.startsWith("{"));
        assertTrue(result.endsWith("}"));
        assertTrue(result.contains("a^2"));
        assertTrue(result.contains("b^3"));
        assertTrue(result.contains("c^4"));
    }

    @Test
    public void testEquals_nullObject_returnsFalse() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        assertFalse(multiset.equals(null));
    }

    @Test
    public void testEquals_notMultisetObject_returnsFalse() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        assertFalse(multiset.equals("not a multiset"));
    }

    @Test
    public void testEquals_differentSize_returnsFalse() {
        Multiset_HashMapImpl<String> multiset1 = new Multiset_HashMapImpl<>();
        multiset1.add("a");
        multiset1.add("b");

        Multiset_HashMapImpl<String> multiset2 = new Multiset_HashMapImpl<>();
        multiset2.add("a");

        assertFalse(multiset1.equals(multiset2));
    }

    @Test
    public void testEquals_sameKeysSameMultiplicities_returnsTrue() {
        Multiset_HashMapImpl<String> multiset1 = new Multiset_HashMapImpl<>();
        multiset1.add("a", 2);
        multiset1.add("b", 3);

        Multiset_HashMapImpl<String> multiset2 = new Multiset_HashMapImpl<>();
        multiset2.add("a", 2);
        multiset2.add("b", 3);

        assertTrue(multiset1.equals(multiset2));
    }

    @Test
    public void testEquals_sameKeysDifferentMultiplicities_returnsFalse() {
        Multiset_HashMapImpl<String> multiset1 = new Multiset_HashMapImpl<>();
        multiset1.add("a", 2);
        multiset1.add("b", 3);

        Multiset_HashMapImpl<String> multiset2 = new Multiset_HashMapImpl<>();
        multiset2.add("a", 2);
        multiset2.add("b", 5);

        assertFalse(multiset1.equals(multiset2));
    }

    @Test
    public void testEquals_differentKeys_returnsFalse() {
        Multiset_HashMapImpl<String> multiset1 = new Multiset_HashMapImpl<>();
        multiset1.add("a", 2);
        multiset1.add("b", 2);

        Multiset_HashMapImpl<String> multiset2 = new Multiset_HashMapImpl<>();
        multiset2.add("a", 2);
        multiset2.add("c", 2);

        assertFalse(multiset1.equals(multiset2));
    }

    @Test
    public void testHashCode_alwaysThrowsIllegalStateException() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        multiset.add("a");
        try {
            multiset.hashCode();
            fail("Expected IllegalStateException to be thrown");
        } catch (IllegalStateException e) {

        }
    }






}
