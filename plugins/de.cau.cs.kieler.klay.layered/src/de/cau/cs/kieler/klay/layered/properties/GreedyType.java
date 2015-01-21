package de.cau.cs.kieler.klay.layered.properties;

public enum GreedyType {
    ONE_SIDED_COUNTER, TWO_SIDED_COUNTER, ONE_SIDED_CROSSING_MATRIX, TWO_SIDED_CROSSING_MATRIX;

    public boolean isOneSided() {
        if (this == ONE_SIDED_COUNTER || this == ONE_SIDED_CROSSING_MATRIX) {
            return true;
        } else {
            return false;
        }
    }

}
