package de.cau.cs.kieler.papyrus.sequence.sorter;

/**
 * Definition of available lifeline sorting strategies for the sequence diagram layouter
 * 
 * @author grh
 */
public enum LifelineSortingStrategy {
    
    /** Sort the lifelines according to their x-coordinates */
    INTERACTIVE,
    /** Sort the lifelines according to the layers of the associated messages */
    LAYER_BASED;

    /**
     * Returns the enumeration value related to the given ordinal.
     * 
     * @param i
     *            ordinal value
     * @return the related enumeration value
     */
    public static LifelineSortingStrategy valueOf(final int i) {
        return values()[i];
    }
}
