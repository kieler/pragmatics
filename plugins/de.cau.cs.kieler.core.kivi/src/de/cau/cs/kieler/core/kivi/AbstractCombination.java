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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.cau.cs.kieler.core.kivi.EffectTrigger.EffectTriggerState;

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

    private boolean noUndo = false;

    /**
     * {@inheritDoc}
     */
    public List<IEffect> trigger(final ITriggerState triggerState) {
        boolean found = true;
        if (triggerState instanceof EffectTriggerState<?>) {
            found = false; // potentially skip execution if wrong effect type
        }
        Method execute = getExecuteMethod();
        if (execute == null) {
            return new ArrayList<IEffect>();
        }
        Type[] types = execute.getGenericParameterTypes();
        Object[] states = new ITriggerState[types.length];
        for (int i = 0; i < types.length; i++) {
            if (types[i] instanceof Class<?>) { // trigger state without generic parameter
                states[i] = KiVi.getInstance().getTriggerState((Class<?>) types[i]);
            } else if (types[i] instanceof ParameterizedType) { // EffectTriggerState<SomeEffect>
                ParameterizedType paramType = (ParameterizedType) types[i];
                Type[] actualTypes = paramType.getActualTypeArguments();
                if (actualTypes.length == 1 && actualTypes[0] instanceof Class<?>) {
                    states[i] = KiVi.getInstance().getTriggerState((Class<?>) actualTypes[0]);
                    if (states[i] == triggerState) {
                        found = true;
                    } else if (states[i] == null) {
                        try {
                            states[i] = new EffectTriggerState<IEffect>(
                                    (IEffect) ((Class<?>) actualTypes[0]).newInstance(), false);
                        } catch (InstantiationException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        if (!found) {
            return new ArrayList<IEffect>();
        }

        List<IEffect> toUndo = effects;
        effects = new ArrayList<IEffect>();
        try {
            execute.invoke(this, states);
        } catch (IllegalArgumentException e) {
            KiVi.error(e);
        } catch (IllegalAccessException e) {
            KiVi.error(e);
        } catch (InvocationTargetException e) {
            KiVi.error(e);
        }
        if (doNothing) {
            doNothing = false;
            effects = toUndo; // keep old effects
            return new ArrayList<IEffect>();
        }

        List<IEffect> result = new ArrayList<IEffect>();
        if (!noUndo) {
            for (IEffect effect : toUndo) {
                result.add(new UndoEffect(effect));
            }
        } else {
            noUndo = false;
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
     * Called by execute() to make sure previous effects are not undone when an execution wants to
     * keep those effects visible indefinitely.
     */
    protected void dontUndo() {
        noUndo = true;
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
    private Method getExecuteMethod() {
        if (executeCache == null) {
            Method[] methods = getClass().getMethods();
            Method execute = null;
            outer: for (Method m : methods) {
                if (m.getName().equals("execute")
                        && (execute == null || m.getParameterTypes().length > execute
                                .getParameterTypes().length)) {
                    if (m.getReturnType().equals(Void.TYPE)) {
                        for (Class<?> parameterType : m.getParameterTypes()) {
                            if (!ITriggerState.class.isAssignableFrom(parameterType)) {
                                continue outer; // execute() takes a non-ITriggerState-parameter.
                            }
                        }
                        if (execute != null) {
                            KiVi.error("found multiple execute() methods in"
                                    + getClass().getCanonicalName());
                        }
                        execute = m;
                    }
                }
            }
            if (execute == null) {
                KiVi.error("no execute() found for " + getClass().getCanonicalName());
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
