/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Preference store implementation that uses maps to hold preference values
 * in memory. Subclasses may access {@link #currentMap} and {@link #defaultMap}
 * and use their {@code keySet} methods to obtain the sets of all stored current
 * and default values. This can be used to write these keys together with
 * their values to a file for permanent storage.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class MapPreferenceStore implements IKielerPreferenceStore {

    /** hash map used to store current preference values in memory */
    protected Map<String, Object> currentMap = new LinkedHashMap<String, Object>();
    /** hash map used to store default preference values in memory */
    protected Map<String, Object> defaultMap = new LinkedHashMap<String, Object>();
    
    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#contains(java.lang.String)
     */
    public boolean contains(String name) {
        return currentMap.get(name) != null || defaultMap.get(name) != null;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getBoolean(java.lang.String)
     */
    public boolean getBoolean(String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Boolean)
            return ((Boolean)currentValue).booleanValue();
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Boolean)
            return ((Boolean)defaultValue).booleanValue();
        return false;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDefaultBoolean(java.lang.String)
     */
    public boolean getDefaultBoolean(String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Boolean)
            return ((Boolean)defaultValue).booleanValue();
        return false;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDefaultDouble(java.lang.String)
     */
    public double getDefaultDouble(String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Double)
            return ((Double)defaultValue).doubleValue();
        return 0.0;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDefaultFloat(java.lang.String)
     */
    public float getDefaultFloat(String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Float)
            return ((Float)defaultValue).floatValue();
        return 0.0f;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDefaultInt(java.lang.String)
     */
    public int getDefaultInt(String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Integer)
            return ((Integer)defaultValue).intValue();
        return 0;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDefaultLong(java.lang.String)
     */
    public long getDefaultLong(String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Long)
            return ((Long)defaultValue).longValue();
        return 0;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDefaultString(java.lang.String)
     */
    public String getDefaultString(String name) {
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof String)
            return (String)defaultValue;
        return "";
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getDouble(java.lang.String)
     */
    public double getDouble(String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Double)
            return ((Double)currentValue).doubleValue();
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Double)
            return ((Double)defaultValue).doubleValue();
        return 0.0;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getFloat(java.lang.String)
     */
    public float getFloat(String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Float)
            return ((Float)currentValue).floatValue();
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Float)
            return ((Float)defaultValue).floatValue();
        return 0.0f;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getInt(java.lang.String)
     */
    public int getInt(String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Integer)
            return ((Integer)currentValue).intValue();
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Integer)
            return ((Integer)defaultValue).intValue();
        return 0;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getLong(java.lang.String)
     */
    public long getLong(String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof Long)
            return ((Long)currentValue).longValue();
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof Long)
            return ((Long)defaultValue).longValue();
        return 0;
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#getString(java.lang.String)
     */
    public String getString(String name) {
        Object currentValue = currentMap.get(name);
        if (currentValue instanceof String)
            return (String)currentValue;
        Object defaultValue = defaultMap.get(name);
        if (defaultValue instanceof String)
            return (String)defaultValue;
        return "";
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#isDefault(java.lang.String)
     */
    public boolean isDefault(String name) {
        Object currentValue = currentMap.get(name);
        Object defaultValue = defaultMap.get(name);
        return defaultValue != null && (currentValue == null
                || currentValue.equals(defaultValue));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setDefault(java.lang.String, double)
     */
    public void setDefault(String name, double value) {
        defaultMap.put(name, Double.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setDefault(java.lang.String, float)
     */
    public void setDefault(String name, float value) {
        defaultMap.put(name, Float.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setDefault(java.lang.String, int)
     */
    public void setDefault(String name, int value) {
        defaultMap.put(name, Integer.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setDefault(java.lang.String, long)
     */
    public void setDefault(String name, long value) {
        defaultMap.put(name, Long.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setDefault(java.lang.String, java.lang.String)
     */
    public void setDefault(String name, String defaultObject) {
        defaultMap.put(name, defaultObject);
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setDefault(java.lang.String, boolean)
     */
    public void setDefault(String name, boolean value) {
        defaultMap.put(name, Boolean.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setToDefault(java.lang.String)
     */
    public void setToDefault(String name) {
        currentMap.remove(name);
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setValue(java.lang.String, double)
     */
    public void setValue(String name, double value) {
        currentMap.put(name, Double.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setValue(java.lang.String, float)
     */
    public void setValue(String name, float value) {
        currentMap.put(name, Float.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setValue(java.lang.String, int)
     */
    public void setValue(String name, int value) {
        currentMap.put(name, Integer.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setValue(java.lang.String, long)
     */
    public void setValue(String name, long value) {
        currentMap.put(name, Long.valueOf(value));
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setValue(java.lang.String, java.lang.String)
     */
    public void setValue(String name, String value) {
        currentMap.put(name, value);
    }

    /* (non-Javadoc)
     * @see de.cau.cs.kieler.core.IKielerPreferenceStore#setValue(java.lang.String, boolean)
     */
    public void setValue(String name, boolean value) {
        currentMap.put(name, Boolean.valueOf(value));
    }

}
