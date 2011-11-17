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
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.collect.Maps;

import de.cau.cs.kieler.core.kivi.triggers.EffectTrigger.EffectTriggerState;

/**
 * Abstract base implementation for combinations. It implements many methods of ICombination and
 * adds much extra convenience for Combination developers.
 * <p>
 * In the {@link ICombination} the developer has to implement
 * {@link ICombination#getTriggerStates()} and {@link ICombination#trigger(ITriggerState)}. In this
 * abstract implementation both methods are implemented such that developer instead implements
 * {@code execute()} where the abstract implementation uses reflection to find out (1) which are
 * the trigger classes that the combination listens to (by the execute parameters) and (2) what are
 * other current {@link ITriggerState}s. Such way the execute method has direct access to all states
 * that it requires.
 * <p>
 * Additionally, a static method {@code getParameters()} can be added to specify combination
 * parameters that should be visible in the preference page. The return value must be an array
 * of {@link CombinationParameter}s. <em>Warning:</em> Do not publish the same parameter in the
 * preferences of two different combinations, since that can lead to undesired behavior. However,
 * the value of one parameter may be accessed from multiple combinations.
 * 
 * @author mmu, haf
 */
public abstract class AbstractCombination implements ICombination {

    /**
     * Whether the combination is active.
     */
    private boolean active = false;

    /**
     * Cache of execute methods to speed up reflection.
     */
    private Map<Class<? extends Object>, Method> executeCache = Maps.newHashMap();
    
    /**
     * Effects that have been scheduled in one iteration of the handle method.
     */
    private List<IEffect> effects = new ArrayList<IEffect>();

    /**
     * Undo effects that have to be executed to undo effects of last round.
     */
    private List<IEffect> undoEffects = new ArrayList<IEffect>();

    /**
     * Merged result of merging all effects and undoEffects.
     */
    private List<IEffect> mergedEffects = new ArrayList<IEffect>();

    /**
     * The trigger state that actually triggered execution of the execute method.
     */
    private ITriggerState triggeringState;
    
    /**
     * The trigger state parameters that are passed to the execute method.
     */
    private Object[] stateParameters;

    /**
     * Whether recording of old effects is enabled. This is required for undo to work properly.
     */
    private boolean enableRecording;
    
    /**
     * Whether the execution of undo effects for the old effects is enabled.
     */
    private boolean undo;

    /**
     * The maximal number of recorded effects before a warning is emitted.
     */
    private static final int MAX_RECORD_LENGTH = 100000;
    
    /**
     * {@inheritDoc}
     */
    public final void handle(final ITriggerState triggerState) {
        boolean debug = KiVi.getInstance().isDebug();

        this.triggeringState = triggerState;
        boolean found = true;
        if (triggerState instanceof EffectTriggerState<?>) {
            // potentially skip execution if wrong effect type
            found = false;
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
            // trigger state without generic parameter, may also be directly an IEffect
            if (types[i] instanceof Class) {
                states[i] = KiVi.getInstance().getTriggerState((Class<?>) types[i]);
                // we see an effect trigger parameter
                if (IEffect.class.isAssignableFrom((Class<?>) types[i])) {
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

        // remember old effects for undoing
        if (enableRecording) {
            for (IEffect effect : effects) {
                undoEffects.add(0, new UndoEffect(effect));
            }
            if (undoEffects.size() > MAX_RECORD_LENGTH) {
                String message = "The View Management Combination "
                        + this.getClass().getName()
                        + " records all effects it is scheduling. "
                        + "However, the recorded list has already size "
                        + undoEffects.size()
                        + ". This looks like a potential memory leak. "
                        + "You should either diable recording for this combination "
                        + "or explicitly undoRecordedEffects from time to time!";
                IStatus status = new Status(IStatus.WARNING, KiViPlugin.PLUGIN_ID, message);
                StatusManager.getManager().handle(status);
            }
        }

        // now we ask for new effects
        effects = new ArrayList<IEffect>();
        try {
            if (debug) {
                System.out.println(this);
            }
            stateParameters = states;
            execute.invoke(this, states);
        } catch (IllegalArgumentException e) {
            KiVi.error(e);
        } catch (IllegalAccessException e) {
            KiVi.error(e);
        } catch (InvocationTargetException e) {
            KiVi.error(e);
        }

        // merge all scheduled effects and undo effects
        mergeScheduledEffects();

        if (debug) {
            for (IEffect effect : mergedEffects) {
                System.out.print(effect);
            }
            if (!mergedEffects.isEmpty()) {
                System.out.println();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<IEffect> getEffects() {
        List<IEffect> result = mergedEffects;
        mergedEffects = new ArrayList<IEffect>();
        return result;
    }

    /**
     * Merge all scheduled effects and undo effects.
     */
    private void mergeScheduledEffects() {
        List<IEffect> toRemove = new ArrayList<IEffect>();
        List<IEffect> allEffects = new ArrayList<IEffect>();
        mergedEffects.clear();
        if (undo) {
            // execute all undo effects and reset the undo list
            allEffects.addAll(undoEffects);
            undoEffects.clear();
        }
        allEffects.addAll(effects);

        // iterate from end to start
        for (int i = allEffects.size() - 1; i >= 0; i--) {
            IEffect effect = allEffects.get(i);
            boolean removed = toRemove.contains(effect);
            // only merge if the effect is not already marked to be removed
            if (!removed && effect.isMergeable()) {
                // iterate other effects (only the ones before the current)
                for (int ii = i - 1; ii >= 0; ii--) {
                    IEffect other = allEffects.get(ii);
                    IEffect current = effect.merge(other);
                    if (current != null) {
                        effect = current;
                        toRemove.add(other);
                    }
                }
            }
            removed = toRemove.contains(effect);
            if (!removed) {
                // put in right order into queue
                mergedEffects.add(0, effect);
            }
        }
    }

    /**
     * Schedule an effect for execution, performs merging against all other effects scheduled during
     * this execution before actually executing the effect.
     * 
     * @param effect
     *            the effect to schedule
     */
    protected final void schedule(final IEffect effect) {
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
    protected final void schedule(final IEffectCompound compoundEffect) {
        effects.addAll(compoundEffect.getPrimitiveEffects());
    }

    /**
     * Activate recording of old effects. This way old effects can be easily undone later on.
     * However, if they are never undone, this record may increase over time as arbitrarily many old
     * effects may be stored.
     */
    protected final void enableEffectRecording() {
        enableRecording = true;
    }

    /**
     * Explicit call to enable undoing all old effects since the last call of this method. This will
     * schedule new UndoEffects and merge them with new effects of this round. This should be called
     * from inside the execute method to easily undo old stuff. Don't use the plain undo() method, as
     * it does not only schedule but also execute the undo immediately.
     */
    protected final void undoRecordedEffects() {
        enableRecording = true;
        undo = true;
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
    public final Class<? extends ITriggerState>[] getTriggerStates() {
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
            throw new IllegalArgumentException(
                    this.getClass().getName()
                            + " contains no execute methods with valid trigger parameters "
                            + "of either types ITriggerState or IEffect!");
        }
        Object result = Array.newInstance(Class.class, types.size());
        for (int i = 0; i < ((Class<? extends ITriggerState>[]) result).length; i++) {
            ((Class<? extends ITriggerState>[]) result)[i] = types.get(i);
        }
        return (Class<? extends ITriggerState>[]) result;
    }

    /**
     * Convenience method to obtain the trigger state that actually caused the execute method to be
     * invoked. This can be used to conceptually go back from the states to the event, to find out
     * which state was constructed last.
     * 
     * @return the trigger state that was constructed last
     */
    protected final ITriggerState latestState() {
        return triggeringState;
    }
    
    /**
     * Convenience method to obtain the index of the trigger state that actually caused the execute
     * method to be invoked. Use this index in a {@code switch} statement to implement different
     * behavior depending on the latest trigger state. The result corresponds to the index in the
     * order of parameters as given in the execute method header. <b>Caution:</b> The meaning of
     * the returned indices may change when more parameters are added to the execute method, or
     * when their order is modified.
     * 
     * @return the index of the latest trigger state
     */
    protected final int latestStateIndex() {
        if (triggeringState != null && stateParameters != null) {
            for (int i = 0; i < stateParameters.length; i++) {
                if (triggeringState.equals(stateParameters[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    /** index constant for the first trigger state parameter. */
    protected static final int FIRST = 0;
    /** index constant for the second trigger state parameter. */
    protected static final int SECOND = 1;
    /** index constant for the third trigger state parameter. */
    protected static final int THIRD = 2;
    /** index constant for the fourth trigger state parameter. */
    protected static final int FOURTH = 3;
    /** index constant for the fifth trigger state parameter. */
    protected static final int FIFTH = 4;
    /** index constant for the sixth trigger state parameter. */
    protected static final int SIXTH = 5;
    /** index constant for the seventh trigger state parameter. */
    protected static final int SEVENTH = 6;

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
                        if (triggerState.getKeyClass().isAssignableFrom(parameterType)
                                || triggerState.getClass() == parameterType) {
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
        undoEffects.clear();
        mergedEffects.clear();
    }

    /**
     * {@inheritDoc}
     */
    public final boolean isActive() {
        return active;
    }

    /**
     * {@inheritDoc}
     */
    public final void setActive(final boolean a) {
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
