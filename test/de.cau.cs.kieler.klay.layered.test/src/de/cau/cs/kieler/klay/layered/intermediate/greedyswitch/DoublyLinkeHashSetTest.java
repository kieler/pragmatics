package de.cau.cs.kieler.klay.layered.test.intermediate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.cau.cs.kieler.klay.layered.intermediate.DoublyLinkedHashSet;

public class DoublyLinkeHashSetTest {

    private DoublyLinkedHashSet<Integer> set;

    @Before
    public void setUp() {
        set = new DoublyLinkedHashSet<Integer>();
    }

    @Test
    public void testAddRemove() {
        set.add(1);
        assertThat(set.size(), is(1));
        set.remove(1);
        assertThat(set.size(), is(0));
    }

    @Test
    public void testRemoveNonExistingElement() {
        set.remove(1);
    }

    @Test
    public void testSetCharacteristic() {
        set.add(1);
        set.add(1);
        assertThat(set.size(), is(1));
    }

    @Test
    public void testRemoveAndCountObjectAddedAfter() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        assertThat(set.removeAndGetAmountOfEntriesAfter(2), is(2));
        assertThat(set.size(), is(3));
    }

    @Test
    public void testRemoveAndCountObjectAddedAfterNonExistingObject() {
        set.add(1);
        assertThat(set.removeAndGetAmountOfEntriesAfter(5), is(0));
        assertThat(set.size(), is(1));
    }

    @Test
    public void testAddSameItemTwiceStillNotLinked() {
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        assertThat(set.removeAndGetAmountOfEntriesAfter(1), is(1));
    }

    @Test
    public void testContains() {
        set.add(1);
        set.add(2);
        set.add(3);
        assertThat(set.contains(1), is(true));
        assertThat(set.contains(4), is(false));
    }
}
