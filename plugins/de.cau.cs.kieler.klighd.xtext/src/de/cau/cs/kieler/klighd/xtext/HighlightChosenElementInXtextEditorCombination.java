/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.xtext;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;

import com.google.common.collect.Iterators;

import de.cau.cs.kieler.core.kivi.AbstractCombination;
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger.SelectionState;
import de.cau.cs.kieler.klighd.viewers.KlighdTreeSelection;
import de.cau.cs.kieler.klighd.xtext.effects.XtextEditorHighlightEffect;

/**
 * Combination that listens to {@link KlighdTreeSelection KlighdTreeSelections} and try's highlight
 * the definition of the model element selected in the diagram in the corresponding Xtext editor. If
 * the definition of the model element is aggregated from another model (file), a corresponding is
 * searched and put in front. If none is available a new one is opened.
 * 
 * @author chsch
 */
public class HighlightChosenElementInXtextEditorCombination extends AbstractCombination {

    /**
     * THE 'execute' method identified and invoked by KIVi via reflection.  
     * 
     * @param state triggerState
     */
    public void execute(final SelectionState state) {

        final KlighdTreeSelection selection = state.getSelection(KlighdTreeSelection.class);
        if (selection == null) {
            return;
        }
        
        final Iterator<EObject> it = Iterators.filter(selection.sourceElementIterator(), EObject.class);
        final EObject head = Iterators.getNext(it, null);
        
        if (head == null || !(head.eResource() instanceof XtextResource)) {
            return;
        }
        
        final XtextEditor editor;
        if (selection.getViewContext().getSourceWorkbenchPart() instanceof XtextEditor) {
            editor = (XtextEditor) selection.getViewContext().getSourceWorkbenchPart();
        } else {
            return;
        }

        this.schedule(new XtextEditorHighlightEffect(head, editor));
    }
}
