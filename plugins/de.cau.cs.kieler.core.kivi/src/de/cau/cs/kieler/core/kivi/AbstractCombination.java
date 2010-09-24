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

/**
 * Abstract base implementation for combinations.
 * 
 * @author mmu
 * 
 */
public abstract class AbstractCombination implements ICombination {

    private boolean active = false;

    /**
     * {@inheritDoc}
     */
    public void trigger(final ITriggerState triggerState) {
        Method execute = getExecuteMethod();
        if (execute == null) {
            return; // no execute method found :(
        }
        Class<?>[] types = execute.getParameterTypes();
        Object[] states = new ITriggerState[types.length];
        for (int i = 0; i < types.length; i++) {
            states[i] = KiVi.getInstance().getTriggerState(types[i]);
        }
        try {
            execute.invoke(this, states);
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
    }

    /**
     * {@inheritDoc}
     * 
     * Can be overridden when the default mechanism of registering triggers by implementing
     * evaluate(ConcreteTrigger) is not wanted.
     */
    public Class<?>[] getTriggerStates() { // FIXME more precise return type
        Method execute = getExecuteMethod();
        if (execute == null) {
            return new Class<?>[0];
        } else {
            return execute.getParameterTypes();
        }
    }

    /**
     * Convenience method to retrieve the method called execute with the longest list of parameters.
     * 
     * @return execute method
     */
    private Method getExecuteMethod() { // FIXME check for correct return types, multiple execute
                                        // methods -> error log
        Method[] methods = getClass().getMethods();
        Method execute = null;
        for (Method m : methods) {
            if (m.getName().equals("execute")
                    && (execute == null || m.getParameterTypes().length > execute
                            .getParameterTypes().length)) {
                execute = m;
            }
        }
        return execute;
    }

    /**
     * {@inheritDoc}
     */
    public void undo() {
        // nothing to do, overridden when required
    }

    /**
     * {@inheritDoc}
     */
    public boolean isActive() {
        return active;
    }

    /**
     * {@inheritDoc}
     */
    public void setActive(final boolean a) {
        if (active && !a) {
            undo();
            KiVi.getInstance().registerCombination(this, false);
        } else if (!active && a) {
            KiVi.getInstance().registerCombination(this, true);
        }
        active = a;
    }

    // /**
    // * Get the parameters for this combination.
    // *
    // * @return the parameters
    // */
    // public static CombinationParameter[] getParameters() {
    // return new CombinationParameter[0];
    // }
}
