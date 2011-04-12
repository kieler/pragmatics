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

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;
import de.cau.cs.kieler.core.ui.UnsupportedPartException;

/**
 * Abstract base implementation for combinations. It implements many methods of ICombination and
 * adds much extra convenience for Combination developers.
 * <p>
 * In the {@link ICombination} the developer has to implement
 * {@link ICombination#getTriggerStates()} and {@link ICombination#trigger(ITriggerState)}. In this
 * abstract implementation both methods are implemented such that developer instead implements
 * {@link #execute()} where the abstract implementation uses reflection to find out (1) which are
 * the trigger classes that the combination listens to (by the execute parameters) and (2) what are
 * other current {@link ITriggerState}s. Such way the execute method has direct access to all states
 * that it requires.
 * 
 * @author mmu, haf
 * 
 */
public abstract class AbstractCombination implements ICombination {

    private boolean active = false;

    private Map<Class<? extends Object>, Method> executeCache = Maps.newHashMap(); // speed up
                                                                                   // reflection

    private List<IEffect> effects = new ArrayList<IEffect>();

    private boolean doNothing = false;

    private boolean noUndo = false;

    private ITriggerState triggeringState;

    /**
     * {@inheritDoc}
     */
    public void handle(final ITriggerState triggerState) {
        boolean debug = KiVi.getInstance().isDebug();

        this.triggeringState = triggerState;
        boolean found = true;
        if (triggerState instanceof EffectTriggerState<?>) {
            found = false; // potentially skip execution if wrong effect type
        }
        // get the relevant execute method for the given triggerState
        Method execute = getExecuteMethod(triggerState);
        if (execute == null) {
            effects.clear();
            return;
        }
        // retrieve all TriggerStates that are parameters for the relevant execute method
        Type[] types = execute.getGenericParameterTypes();
        Object[] states = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            if (types[i] instanceof Class) { // trigger state without generic parameter, may also be
                                             // directly an IEffect
                states[i] = KiVi.getInstance().getTriggerState((Class<?>) types[i]);
                if (IEffect.class.isAssignableFrom((Class<?>) types[i])) { // we see an effect
                                                                           // trigger parameter
                    IEffect effectParam = ((EffectTriggerState<?>) states[i]).getEffect();
                    states[i] = effectParam;

                    if (types[i] == triggerState.getKeyClass()) {
                        found = true;
                    }
                }
            } else if (types[i] instanceof ParameterizedType) {
                // explicit parameter case EffectTriggerState<SomeEffect>
                ParameterizedType paramType = (ParameterizedType) types[i];
                Type[] actualTypes = paramType.getActualTypeArguments();
                if (actualTypes.length == 1 && actualTypes[0] instanceof Class<?>) {
                    states[i] = KiVi.getInstance().getTriggerState((Class<?>) actualTypes[0]);
                    if (states[i] == triggerState) {
                        found = true;
                    } else if (states[i] == null) {
                        states[i] = new EffectTriggerState<IEffect>(null, false);
                    }
                }
            }
        }

        if (!found) {
            effects.clear();
            return;
        }

        List<IEffect> toUndo = effects;
        effects = new ArrayList<IEffect>();
        try {
            if (debug) {
                System.out.println(this);
            }
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
            return;
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
        if (debug) {
            for (IEffect effect : result) {
                System.out.print(effect);
            }
            if (!effects.isEmpty()) {
                System.out.println();
            }
        }
        return;
    }

    /**
     * {@inheritDoc}
     */
    public List<IEffect> getEffects() {
        List<IEffect> result = effects;
        effects = new ArrayList<IEffect>();
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
     * Schedule a compound effect for execution, performs merging against all other effects
     * scheduled during this execution before actually executing the effect. This is equivalent to
     * schedule all primitive effects of an ICompoundEffect.
     * 
     * @param compoundEffect
     *            the compound effect to schedule
     */
    protected void schedule(final IEffectCompound compoundEffect) {
        effects.addAll(compoundEffect.getPrimitiveEffects());
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
     * Use reflection to find out which {@link ITriggerState} classes this combination listens to.
     * Read all methods called "execute" and get all parameters that they have. Return an array of
     * these parameters. Can be overridden when the default mechanism of registering triggers by
     * implementing execute(ConcreteTrigger) is not wanted.
     * <p>
     * Will throw the unchecked {@link IllegalArgumentException} if there are execute methods with
     * overlapping parameters found. In such case KIVi cannot decide in which order it should
     * execute such methods. So this case is currently not supported.
     * 
     * @returns array of trigger states that this combination listens to.
     */
    @SuppressWarnings("unchecked")
    public Class<? extends ITriggerState>[] getTriggerStates() {
        Method[] methods = getClass().getMethods();
        boolean existsExecute = false;
        List<Class<? extends ITriggerState>> types = new ArrayList<Class<? extends ITriggerState>>();
        // search all execute methods
        for (Method method : methods) {
            if (method.getName().equals("execute") && method.getReturnType().equals(Void.TYPE)) {
                Class<?>[] params = method.getParameterTypes();
                for (Class<?> param : params) {
                    if (IEffect.class.isAssignableFrom(param)) {
                        param = EffectTriggerState.class;
                    }
                    if (types.contains(param)) {
                        throw new IllegalArgumentException(this.getClass().getName()
                                + " contains multiple" + " execute methods with the parameter "
                                + param.getName() + ". Only"
                                + "disjoint parameter lists are supported and "
                                + "only one may contain effects!");
                    }
                    if (ITriggerState.class.isAssignableFrom(param)) {
                        existsExecute = true;
                        types.add((Class<? extends ITriggerState>) param);
                    }
                }
            }
        }
        if (!existsExecute) {
            throw new IllegalArgumentException(this.getClass().getName()
                    + " contains no execute methods with valid trigger parameters!");
        }
        Object result = Array.newInstance(Class.class, types.size());
        for (int i = 0; i < ((Class<? extends ITriggerState>[]) result).length; i++) {
            ((Class<? extends ITriggerState>[]) result)[i] = types.get(i);
        }
        return (Class<? extends ITriggerState>[]) result;
    }

    /**
     * Convenience method to obtain the ITriggerState that actually caused the execute method to be
     * invoked. This can be used to conceptually go back from the states to the event, to find out,
     * which state was constructed last.
     * 
     * @return the trigger state that was constructed last
     */
    protected ITriggerState getTriggerState() {
        return triggeringState;
    }

    /**
     * Convenience method to retrieve the method called execute with the longest list of parameters.
     * 
     * @param triggerState
     * 
     * @return execute method
     */
    private Method getExecuteMethod(final ITriggerState triggerState) {
        Method execute = null;
        // lookup an earlier cached method to avoid doing reflection all the time
        execute = executeCache.get(triggerState.getClass());
        if (execute == null) {
            Method[] methods = getClass().getMethods();
            // search all execute methods
            outer: for (Method method : methods) {
                if (method.getName().equals("execute") && method.getReturnType().equals(Void.TYPE)) {
                    boolean relevant = false;
                    for (Class<?> parameterType : method.getParameterTypes()) {
                        if (!(ITriggerState.class.isAssignableFrom(parameterType) || IEffect.class
                                .isAssignableFrom(parameterType))) {
                            KiVi.error("The execute method should only contain ITriggerState and/or "
                                    + "IEffect parameters. Found " + parameterType.getName() + ".");
                            continue outer; // execute() takes a
                                            // non-ITriggerState/IEffect-parameter.
                        }
                        if (triggerState.getKeyClass().isAssignableFrom(parameterType) || 
                                triggerState.getClass() == parameterType) { 
                         // EffectTriggerState matches effects parameter or
                         // EffectTriggerState is same as EffectTriggerState parameter
                            relevant = true;
                        }
                    }
                    if (relevant) {
                        // found an execute method that has the given parameter
                        if (execute != null) {
                            KiVi.error("found multiple execute() methods with overlapping parameters in"
                                    + getClass().getCanonicalName()
                                    + ". Make sure, execute methods have only disjoint parameters!");
                        }
                        execute = method;
                    }
                }
            }
            if (execute != null) {
                // now at last we found a valid execute method
                executeCache.put(triggerState.getClass(), execute);
            }
        }
        return execute;
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
            active = a;
            undo();
            // haf: potential stack overflow by infinite loop of register-setActive
            KiVi.getInstance().registerCombination(this, false);
        } else if (!active && a) {
            active = a;
            KiVi.getInstance().registerCombination(this, true);
        }
    }

    @Override
    public String toString() {
        String name = this.getClass().getName();
        int index = name.lastIndexOf(".");
        return "Combination[" + name.substring(index) + "]";
    }
}
