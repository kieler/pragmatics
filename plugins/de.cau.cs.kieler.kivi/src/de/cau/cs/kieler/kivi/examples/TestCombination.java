/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivi.examples;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.cau.cs.kieler.kivi.core.Viewmanagement;
import de.cau.cs.kieler.kivi.core.impl.AbstractCombination;

/**
 * Combination to test the basic view management core.
 * 
 * @author mmu
 * 
 */
public class TestCombination extends AbstractCombination {

    private GraphicalEditPart selected;
    
    private TextEffect effect;

    @Override
    public void execute() {
        if (effect != null) {
            undo();
        }
        effect = new TextEffect("foo", selected);
        Viewmanagement.getInstance().executeEffect(effect);
    }
    
    @Override
    public void undo() {
        if (effect != null) {
            Viewmanagement.getInstance().undoEffect(effect);
            effect = null;
        }
    }

    /**
     * Receive events from selection triggers.
     * 
     * @param trigger
     *            the trigger
     * @return true if execute should be called
     */
    public boolean evaluate(final SelectionTrigger trigger) {
        ISelection s = trigger.getSelection();
        if (s instanceof IStructuredSelection) {
            IStructuredSelection selection = (IStructuredSelection) s;
            Object o = selection.getFirstElement();
            if (o instanceof GraphicalEditPart) {
                selected = (GraphicalEditPart) o;
                return true;
            }
        }
        return false;
    }

}
