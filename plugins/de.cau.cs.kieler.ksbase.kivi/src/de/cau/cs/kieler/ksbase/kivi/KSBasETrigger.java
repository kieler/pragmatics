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
package de.cau.cs.kieler.ksbase.kivi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.part.EditorPart;

import de.cau.cs.kieler.kivi.core.Viewmanagement;
import de.cau.cs.kieler.kivi.core.impl.AbstractTrigger;
import de.cau.cs.kieler.ksbase.ui.TransformationUIManager;
import de.cau.cs.kieler.ksbase.ui.listener.ITransformationEventListener;
import de.cau.cs.kieler.ksbase.ui.utils.TransformationUtils;

/**
 * A Trigger that listens to KSBasE transformations.
 * 
 * @author mmu
 * 
 */
public class KSBasETrigger extends AbstractTrigger implements ITransformationEventListener {

    private EObject selection;

    private EditorPart editorPart;

    /**
     * Default constructor.
     */
    public KSBasETrigger() {
    }

    private KSBasETrigger(final EObject s, final EditorPart e) {
        selection = s;
        editorPart = e;
    }

    /**
     * Get the selection of the transformation.
     * 
     * @return the selected EObject
     */
    public EObject getSelection() {
        return selection;
    }

    /**
     * Get the editor part that contained the transformation.
     * 
     * @return the editor part
     */
    public EditorPart getEditorPart() {
        return editorPart;
    }

    @Override
    public void register() {
        TransformationUIManager.INSTANCE.addTransformationListener(this);
    }

    @Override
    public void unregister() {
        TransformationUIManager.INSTANCE.removeTransformationListener(this);
    }

    /**
     * {@inheritDoc}
     */
    public void transformationExecuted(final Object[] args) {
        // post-transformation arguments are [EObject, EditorPart]
        if (args.length == 2 && args[0] instanceof EObject && args[1] instanceof EditorPart) {
            EObject eObject = TransformationUtils.getPostTransformationSelection();
            if (eObject == null) {
                eObject = (EObject) args[0];
            }
            Viewmanagement.getInstance().trigger(new KSBasETrigger(eObject, (EditorPart) args[1]));
        } else {
            System.out.println("ksbase post-transformation fail: " + args); // FIXME remove later
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transformationAboutToExecute(final Object[] args) {
        // pre-transformation is ignored
    }

}
