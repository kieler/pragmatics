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
package de.cau.cs.kieler.kiml.export.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class that provides functionality that can be accessed by xtend
 * transformations.
 * 
 * @author mri
 */
public final class XtendUtil {

    /** the name-generator names mapped on the counter variables. */
    private static Map<String, Integer> generators =
            new HashMap<String, Integer>();

    /**
     * A private constructor to make the class not instantiable.
     */
    private XtendUtil() {
        // do nothing
    }

    /**
     * Generates a name for the given generator name using the prefix.
     * 
     * @param generatorName
     *            the name of the generator
     * @param namePrefix
     *            a prefix for the generated name
     * @return the generated name
     */
    public static String generateName(final String generatorName,
            final String namePrefix) {
        Integer number = generators.get(generatorName);
        if (number == null) {
            number = 0;
        }
        generators.put(generatorName, number + 1);
        return namePrefix + number.toString();
    }

    /**
     * Resets the generator specified by the given name.
     * 
     * @param generatorName
     *            the generator name
     */
    public static void resetGenerator(final String generatorName) {
        generators.remove(generatorName);
    }

    /**
     * Resets all name generators.
     */
    public static void resetGenerators() {
        generators.clear();
    }

    /**
     * This is a workaround method for xtend to solve the issue of missing
     * methods, which are not really missing, but xtend thinks so. It calls a
     * method of a class instance with a number of parameters.
     * 
     * @param instance
     *            the class instance
     * @param methodName
     *            the method name
     * @param params
     *            the parameters
     */
    public static void callMethod(final Object instance,
            final String methodName, final Object... params) {
        Class<?>[] paramClasses = new Class<?>[params.length];
        int i = 0;
        for (Object param : params) {
            paramClasses[i++] = param.getClass();
        }
        try {
            Method method =
                    instance.getClass().getDeclaredMethod(methodName,
                            paramClasses);
            method.invoke(instance, params);
        } catch (SecurityException e) {
            // do nothing
        } catch (NoSuchMethodException e) {
            // do nothing
        } catch (IllegalArgumentException e) {
            // do nothing
        } catch (IllegalAccessException e) {
            // do nothing
        } catch (InvocationTargetException e) {
            // do nothing
        }
    }

    /**
     * See {@code callMethod}. This method is restricted on methods with a
     * single string parameter.
     * 
     * @param instance
     *            the class instance
     * @param methodName
     *            the method name
     * @param param
     *            the string parameter
     */
    public static void callStringMethod(final Object instance,
            final String methodName, final String param) {
        callMethod(instance, methodName, param);
    }
}
