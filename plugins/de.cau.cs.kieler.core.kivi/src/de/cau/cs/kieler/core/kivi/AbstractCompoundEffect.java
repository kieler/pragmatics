package de.cau.cs.kieler.core.kivi;

import java.util.List;

public abstract class AbstractCompoundEffect implements IEffect {

    /**
     * Get the list of primitive IEffects that this compound effect is composed of.
     * 
     * @return list of IEffects
     */
    public abstract List<IEffect> getPrimitiveEffects();
    
    /**
     * {@inheritDoc}
     */
    public void execute() {
        for (IEffect effect : this.getPrimitiveEffects()){
            effect.execute();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void schedule() {
        KiVi.getInstance().executeEffect(this);
    }

    /**
     * {@inheritDoc}
     */
    public void undo() {
     // needs to be overwritten when required

    }

    /**
     * {@inheritDoc}
     */
    public void scheduleUndo() {
        KiVi.getInstance().undoEffect(this);

    }

    /**
     * {@inheritDoc}
     */
    public boolean isMergeable() {
        // default behaviour: not mergeable
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public IEffect merge(IEffect otherEffect) {
     // default behaviour: no merge possible
        return null;
    }

}
