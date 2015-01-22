package de.cau.cs.kieler.klay.layered.properties;

public enum GreedyType {
    ONE_SIDED_COUNTER, TWO_SIDED_COUNTER, ONE_SIDED_CROSSING_MATRIX, TWO_SIDED_CROSSING_MATRIX,
    ONE_SIDED_ON_DEMAND_CROSSING_MATRIX;

    public boolean isOneSided() {
        return this == ONE_SIDED_COUNTER || this == ONE_SIDED_CROSSING_MATRIX
                || this == ONE_SIDED_ON_DEMAND_CROSSING_MATRIX;
    }

}
