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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base implementation for combinations.
 * 
 * @author mmu
 * 
 */
public abstract class AbstractCombination implements ICombination {

    private boolean active = false;

    private Method executeCache = null; // speed up reflection

    private List<IEffect> effects = new ArrayList<IEffect>();

    private boolean doNothing = false;

    /**
     * {@inheritDoc}
     */
    public List<IEffect> trigger() {
        Method execute = getExecuteMethod();
        if (execute == null) {
            System.err.println("no execute() found for " + getClass().getCanonicalName());
            // FIXME error log
            return new ArrayList<IEffect>();
        }
        Class<?>[] types = execute.getParameterTypes();
        Object[] states = new ITriggerState[types.length];
        for (int i = 0; i < types.length; i++) {
            states[i] = KiVi.getInstance().getTriggerState(types[i]);
        }

        List<IEffect> toUndo = effects;
        effects = new ArrayList<IEffect>();
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
        if (doNothing) {
            doNothing = false;
            effects = toUndo; // keep old effects
            return new ArrayList<IEffect>();
        }

        List<IEffect> result = new ArrayList<IEffect>();
        for (IEffect effect : toUndo) {
            result.add(new UndoEffect(effect));
        }

        List<IEffect> toRemove = new ArrayList<IEffect>();
        for (IEffect effect : effects) {
            if (effect.isMergeable()) {
                for (Iterator<IEffect> iterator = result.iterator(); iterator.hasNext();) {
                    IEffect other = iterator.next();
                    IEffect current = effect.merge(other);
                    if (current != null) {
                        effect = current;
                        iterator.remove();
                        toRemove.add(other);
                    }
                }
            }
            result.add(effect);
        }
        effects.removeAll(toRemove);
        return result;
    }

    /**
     * Schedule an effect for execution, performs merging against all other effects scheduled during
     * this execution before actually executing the effect.
     * 
     * @param effect
     *            the effect to schedule
     */
    protected void schedule(final IEffect effect) {
        effects.add(effect);
    }

    /**
     * Called by execute() to make sure previous effects are not undone when an execution wants to
     * perform no changes.
     */
    protected void doNothing() {
        doNothing = true;
    }

    /**
     * {@inheritDoc}
     * 
     * Can be overridden when the default mechanism of registering triggers by implementing
     * evaluate(ConcreteTrigger) is not wanted.
     */
    @SuppressWarnings("unchecked")
    public Class<? extends ITriggerState>[] getTriggerStates() {
        Method execute = getExecuteMethod();
        if (execute == null) {
            System.err.println("no execute() found for " + getClass().getCanonicalName());
            // FIXME error log
            return (Class<? extends ITriggerState>[]) new Class<?>[0];
        } else { // FIXME is there any way to check against this type?
            return (Class<? extends ITriggerState>[]) execute.getParameterTypes();
        }
    }

    /**
     * Convenience method to retrieve the method called execute with the longest list of parameters.
     * 
     * @return execute method
     */
    private Method getExecuteMethod() { // FIXME check for correct return types, multiple execute
                                        // methods -> error log
        if (executeCache == null) {
            Method[] methods = getClass().getMethods();
            Method execute = null;
            for (Method m : methods) {
                if (m.getName().equals("execute")
                        && (execute == null || m.getParameterTypes().length > execute
                                .getParameterTypes().length)) {
                    execute = m;
                }
            }
            executeCache = execute;
        }
        return executeCache;
    }

    /**
     * {@inheritDoc}
     */
    public void undo() {
        for (IEffect effect : effects) {
            KiVi.getInstance().undoEffect(effect);
        }
        effects.clear();
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
}
