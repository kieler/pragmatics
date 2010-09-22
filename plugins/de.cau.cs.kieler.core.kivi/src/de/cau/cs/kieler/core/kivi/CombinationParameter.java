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

/**
 * Describes a parameter of a combination.
 * 
 * @author mmu
 * 
 */
public class CombinationParameter {

    /**
     * Default initializer for strings.
     */
    public static final IParameterType STRING_INITIALIZER = new IParameterType() {
        public void initialize(final IPreferenceStore p, final String k, final Object o) {
            p.setDefault(k, (String) o);
        }
        
        public Class<?> getType() {
            return String.class;
        }
    };
    
    /**
     * Default initializer for integers.
     */
    public static final IParameterType INTEGER_INITIALIZER = new IParameterType() {
        public void initialize(final IPreferenceStore p, final String k, final Object o) {
            p.setDefault(k, (Integer) o);
        }
        
        public Class<?> getType() {
            return Integer.class;
        }
    };
    
    /**
     * Default initializer for floats.
     */
    public static final IParameterType FLOAT_INITIALIZER = new IParameterType() {
        public void initialize(final IPreferenceStore p, final String k, final Object o) {
            p.setDefault(k, (Float) o);
        }
        
        public Class<?> getType() {
            return Float.class;
        }
    };
    
    /**
     * Default initializer for doubles.
     */
    public static final IParameterType DOUBLE_INITIALIZER = new IParameterType() {
        public void initialize(final IPreferenceStore p, final String k, final Object o) {
            p.setDefault(k, (Double) o);
        }
        
        public Class<?> getType() {
            return Double.class;
        }
    };
    
    /**
     * Default initializer for booleans.
     */
    public static final IParameterType BOOLEAN_INITIALIZER = new IParameterType() {
        public void initialize(final IPreferenceStore p, final String k, final Object o) {
            p.setDefault(k, (String) o);
        }
        
        public Class<?> getType() {
            return Boolean.class;
        }
    };
    
    /**
     * Default initializer for RGB color values.
     */
    public static final IParameterType RGB_INITIALIZER = new IParameterType() {
        public void initialize(final IPreferenceStore p, final String k, final Object o) {
            PreferenceConverter.setDefault(p, k, (RGB) o);
        }
        
        public Class<?> getType() {
            return RGB.class;
        }
    };
    
    private String key;
    private IPreferenceStore preferenceStore;
    private String name;
    private String description;
    private Object defaultValue;
    private IParameterType type;

    /**
     * Create a new combination parameter.
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
     * @param t
     *            The parameter type
     */
    public CombinationParameter(final String k, final IPreferenceStore store, final String n,
            final String d, final Object def, final IParameterType t) {
        key = k;
        preferenceStore = store;
        name = n;
        description = d;
        defaultValue = def;
        type = t;
    }

    /**
     * Get the preference key.
     * 
     * @return the key
     */
    public String getKey() {
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
    public Class<?> getType() {
        return type.getType();
    }

    /**
     * Get the default value of this parameter.
     * 
     * @return the default value
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * 
     */
    public void initialize() {
        type.initialize(preferenceStore, key, defaultValue);
    }

    /**
     * Retrieve the combination parameters from a combination class.
     * 
     * @param combination
     *            the combination class to look in
     * @return its combination parameters, or an empty array if there are none
     */
    public static CombinationParameter[] getParameters(final Class<?> combination) {
        try {
            Method method = combination.getMethod("getParameters");
            Object result = method.invoke(null);
            if (result instanceof CombinationParameter[]) {
                return (CombinationParameter[]) result;
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // nothing to do, return empty array later
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new CombinationParameter[0];
    }

    /**
     * Honor type-specific issues.
     * 
     * @author mmu
     * 
     */
    public static interface IParameterType {

        /**
         * Initialize the preference store for the key with the default value.
         * 
         * @param p
         *            the preference store
         * @param k
         *            the key
         * @param o
         *            the default value
         */
        void initialize(IPreferenceStore p, String k, Object o);
        
        /**
         * Get the class for this type.
         * 
         * @return the type class
         */
        Class<?> getType();
    }
}
