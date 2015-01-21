package de.cau.cs.kieler.klay.layered.properties;

public enum GreedyType {
    ONE_SIDED_COUNTER, TWO_SIDED_COUNTER;

    public boolean isOneSided() {
        if (this == ONE_SIDED_COUNTER) {
            return true;
        } else {
            return false;
        }
    }

}
