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
package de.cau.cs.kieler.kivi.core.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.kivi.KiViPlugin;
import de.cau.cs.kieler.kivi.core.CombinationParameter;
import de.cau.cs.kieler.kivi.core.ICombination;
import de.cau.cs.kieler.kivi.core.ITrigger;
import de.cau.cs.kieler.kivi.core.Viewmanagement;

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
    public void trigger(final ITrigger trigger) {
        if (evaluate(trigger)) {
            execute();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Can be overridden when the default mechanism of registering triggers by implementing
     * evaluate(ConcreteTrigger) is not wanted.
     */
    public List<Class<?>> getTriggers() {
        List<Class<?>> triggers = new ArrayList<Class<?>>();
        // retrieve evaluate(XYZTrigger) methods, ignore evaluate(ITrigger)
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("evaluate")) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1 && !params[0].equals(ITrigger.class)) {
                    triggers.add(params[0]);
                }
            }
        }
        return triggers;
    }

    /**
     * {@inheritDoc}
     * 
     * Can be overridden when the default mechanism of registering triggers by implementing
     * evaluate(ConcreteTrigger) is not wanted.
     */
    public boolean evaluate(final ITrigger trigger) {
        try {
            // get the evaluate method for the specific trigger
            Method method = getClass().getMethod("evaluate", trigger.getClass());
            if (method.equals(getClass().getMethod("evaluate", ITrigger.class))) {
                // the current method was reflected because no specific
                // implementation exists, avoid infinite loop;
                return false;
            } else {
                // run evaluate
                return (Boolean) method.invoke(this, trigger);
            }
            // nothing to do for any of these exceptions
        } catch (SecurityException e) {
            return false;
        } catch (NoSuchMethodException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public abstract void execute();

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
            Viewmanagement.getInstance().registerCombination(this, false);
        } else if (!active && a) {
            Viewmanagement.getInstance().registerCombination(this, true);
        }
        active = a;
    }

    /**
     * Get the parameters for this combination.
     * 
     * @return the parameters
     */
    public static CombinationParameter[] getParameters() {
        return new CombinationParameter[0];
    }
}
