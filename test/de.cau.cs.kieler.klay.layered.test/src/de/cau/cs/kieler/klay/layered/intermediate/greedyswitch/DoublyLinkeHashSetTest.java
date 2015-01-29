package de.cau.cs.kieler.klay.layered.intermediate.greedyswitch;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkeHashSetTest {

    private DoublyLinkedHashSet<Integer> set;

    @Before
    public void setUp() {
        set = new DoublyLinkedHashSet<Integer>();
    }

    @Test
    public void addRemove() {
        set.add(1);
        assertThat(set.size(), is(1));
        set.remove(1);
        assertThat(set.size(), is(0));
    }

    @Test
    public void removeNonExistingElement() {
        set.remove(1);
    }

    @Test
    public void setCharacteristic() {
        set.add(1);
        set.add(1);
        assertThat(set.size(), is(1));
    }

    @Test
    public void removeAndCountObjectAddedAfter() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.remove(2);
        assertThat(set.getAmountOfEntriesAfter(1), is(2));
        assertThat(set.size(), is(3));
    }

    @Test
    public void removeYoungestAndCountObjectAddedAfter() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.remove(4);
        assertThat(set.getAmountOfEntriesAfter(1), is(2));
        assertThat(set.size(), is(3));
    }

    @Test
    public void removeEldestAndCountObjectAddedAfter() {
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.remove(1);
        assertThat(set.getAmountOfEntriesAfter(2), is(2));
        assertThat(set.size(), is(3));
    }

    @Test
    public void removeAndReAddOften() {
        set.add(1);
        set.add(2);
        set.remove(2);
        set.add(3);
        set.add(3);
        set.add(3);
        assertThat(set.getAmountOfEntriesAfter(1), is(1));
        set.add(4);
        set.remove(3);
        assertThat(set.size(), is(2));
        set.remove(1);
        assertThat(set.size(), is(1));
    }

    @Test
    public void removeAndCountObjectAddedAfterNonExistingObject() {
        set.add(1);
        assertThat(set.getAmountOfEntriesAfter(5), is(0));
    }

    @Test
    public void addSameItemTwiceStillNotLinked() {
        set.add(1);
        set.add(1);
        set.add(2);
        set.add(2);
        assertThat(set.getAmountOfEntriesAfter(1), is(1));
    }

    @Test
    public void contains() {
        set.add(1);
        set.add(2);
        set.add(3);
        assertThat(set.contains(1), is(true));
        assertThat(set.contains(4), is(false));
    }

}
