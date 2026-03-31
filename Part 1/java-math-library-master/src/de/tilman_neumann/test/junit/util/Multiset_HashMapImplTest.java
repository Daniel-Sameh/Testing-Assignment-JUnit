package de.tilman_neumann.test.junit.util;

import de.tilman_neumann.util.Multiset;
import org.junit.Test;

import static org.junit.Assert.*;

import de.tilman_neumann.test.junit.ClassTest;
import de.tilman_neumann.util.Multiset_HashMapImpl;
import org.junit.jupiter.api.BeforeEach;

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

}