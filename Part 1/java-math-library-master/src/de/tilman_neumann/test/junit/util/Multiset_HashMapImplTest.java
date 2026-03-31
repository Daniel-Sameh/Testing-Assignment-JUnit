package de.tilman_neumann.test.junit.util;
import org.junit.Test;
import static org.junit.Assert.*;

import de.tilman_neumann.test.junit.ClassTest;
import de.tilman_neumann.util.Multiset_HashMapImpl;

import java.util.List;

public class Multiset_HashMapImplTest extends ClassTest {

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
