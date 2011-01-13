package de.cau.cs.kieler.kex.ui.wizards.importing.viewer;

public enum ExampleDescriptorKind {

    DESCRIPTION("description"), //$NON-NLS-1$
    CATEGORY("category"); //$NON-NLS-1$

    private final String value;

    private ExampleDescriptorKind(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * return the enum constant whose {@link #getValue() value} is the same as the given value.
     * 
     * @param value
     *            the string value, or null
     * 
     * @return the corresponding enum constant or null if the given value was null
     * 
     * @throws IllegalArgumentException
     *             if the given value does not correspond to any enum constant
     */
    public static ExampleDescriptorKind fromValue(String value) throws IllegalArgumentException {
        if (value == null) {
            return null;
        }
        for (ExampleDescriptorKind e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

}
