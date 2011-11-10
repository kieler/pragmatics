/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.kivi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.RGB;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Describes a parameter of a combination.
 * 
 * @param <T> the type of parameter
 * @author mmu
 * @author msp
 */
public class CombinationParameter<T> implements IProperty<T> {

    /** the parameter key. */
    private String key;
    /** the preference store where values are set. */
    private IPreferenceStore preferenceStore;
    /** a user-friendly name. */
    private String name;
    /** a user-friendly description. */
    private String description;
    /** the default value. */
    private T defaultValue;
    /** the type of parameter. */
    private Class<T> type;

    /**
     * Create a new combination parameter with default value.
     * 
     * @param k
     *            the preference key
     * @param store
     *            the preference store
     * @param n
     *            the readable name
     * @param d
     *            the readable description
     * @param def
     *            the default value
     */
    @SuppressWarnings("unchecked")
    public CombinationParameter(final String k, final IPreferenceStore store, final String n,
            final String d, final T def) {
        key = k;
        preferenceStore = store;
        name = n;
        description = d;
        defaultValue = def;
        type = (Class<T>) def.getClass();
    }

    /**
     * Create a new combination parameter without a default value.
     * 
     * @param k
     *            the preference key
     * @param store
     *            the preference store
     * @param n
     *            the readable name
     * @param d
     *            the readable description
     * @param clazz
     *            The parameter class
     */
    public CombinationParameter(final String k, final IPreferenceStore store, final String n,
            final String d, final Class<T> clazz) {
        key = k;
        preferenceStore = store;
        name = n;
        description = d;
        type = clazz;
    }

    /**
     * Get the preference key.
     * 
     * @return the key
     */
    public String getId() {
        return key;
    }

    /**
     * Get the preference store used to save this key-value pair.
     * 
     * @return the preference store
     */
    public IPreferenceStore getPreferenceStore() {
        return preferenceStore;
    }

    /**
     * Get a readable name for this parameter.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get a readable description for this parameter.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the type of this parameter.
     * 
     * @return the type
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * Get the default value of this parameter.
     * 
     * @return the default value
     */
    public T getDefault() {
        return defaultValue;
    }

    /**
     * Initialize the default value of the combination parameter.
     */
    public void initialize() {
        if (type == String.class) {
            preferenceStore.setDefault(key, (String) defaultValue);
        } else if (type == Integer.class) {
            preferenceStore.setDefault(key, (Integer) defaultValue);
        } else if (type == Float.class) {
            preferenceStore.setDefault(key, (Float) defaultValue);
        } else if (type == Double.class) {
            preferenceStore.setDefault(key, (Double) defaultValue);
        } else if (type == Boolean.class) {
            preferenceStore.setDefault(key, (Boolean) defaultValue);
        } else if (type == RGB.class) {
            PreferenceConverter.setDefault(preferenceStore, key, (RGB) defaultValue);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Comparable<T> getLowerBound() {
        return (Comparable<T>) Property.NEGATIVE_INFINITY;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public Comparable<T> getUpperBound() {
        return (Comparable<T>) Property.POSITIVE_INFINITY;
    }

    /**
     * Retrieve the combination parameters from a combination class.
     * 
     * @param combination
     *            the combination class to look in
     * @return its combination parameters, or an empty array if there are none
     */
    public static CombinationParameter<?>[] getParameters(
            final Class<? extends ICombination> combination) {
        try {
            Method method = combination.getMethod("getParameters");
            Object result = method.invoke(null);
            if (result instanceof CombinationParameter[]) {
                return (CombinationParameter[]) result;
            }
        } catch (SecurityException e) {
            KiVi.error(e);
        } catch (NoSuchMethodException e) {
            // nothing to do, return empty array later
        } catch (IllegalArgumentException e) {
            KiVi.error(e);
        } catch (IllegalAccessException e) {
            KiVi.error(e);
        } catch (InvocationTargetException e) {
            KiVi.error(e);
        }
        return new CombinationParameter[0];
    }
    
}
