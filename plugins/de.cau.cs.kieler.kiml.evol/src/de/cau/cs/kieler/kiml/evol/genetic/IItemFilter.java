package de.cau.cs.kieler.kiml.evol.genetic;

public interface IItemFilter<T> {
    boolean isMatch(T item);
}
