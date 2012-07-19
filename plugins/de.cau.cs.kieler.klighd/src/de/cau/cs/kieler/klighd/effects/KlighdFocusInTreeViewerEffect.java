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
package de.cau.cs.kieler.klighd.effects;

import java.util.ArrayList;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.KielerModelException;
import de.cau.cs.kieler.core.WrappedException;
import de.cau.cs.kieler.core.kivi.AbstractEffect;

/**
 * Effect that realizes the focusing of element selected in KLighD views in related tree viewers.<br>
 * (e.g. in tree editors or outline views) 
 * 
 * @author chsch
 */
public class KlighdFocusInTreeViewerEffect extends AbstractEffect {

    private Object element = null;
    private TreeViewer treeViewer = null;
    
    /**
     * Constructor.
     * 
     * @param theElement the element to be focused, must not be null!
     * @param theTreeViewer the viewer to set the selection in, must not be null!
     */
    public KlighdFocusInTreeViewerEffect(final Object theElement, final TreeViewer theTreeViewer) {
        this.element = theElement;
        this.treeViewer = theTreeViewer;
    }

    // SUPPRESS CHECKSTYLE NEXT Javadoc, as it wrongly complains about the missing first sentence period
    /**
     * {@inheritDoc}
     * <br><br>
     * Builds up a conform {@link ISelection} and hands it to the given viewer afterwards.  
     */
    public void execute() {
        if (this.element != null && this.treeViewer != null) {
            ArrayList<Object> l = new ArrayList<Object>();
            Resource res = null;
            if (this.element instanceof EObject) {
                // in case of an EObject 
                
                EObject eo = (EObject) this.element;
                res = eo.eResource();
                
                while (eo != null) {
                    l.add(eo);
                    eo = eo.eContainer();
                }
                l.add(res);
                Collections.reverse(l);
            } else if (this.element instanceof Resource) {
                // chsch: maybe this makes sense one day ;-)
                l.add(res);
            } else {
                // there are no other elements visible in an EMF tree editor that might be focused.
                return;
            }
            
            final ISelection selection = new TreeSelection(new TreePath(l.toArray()));

            
            PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
                public void run() {
                    if (treeViewer != null) {
                        treeViewer.setSelection(selection, true);
                    }
                }
            });
        } else {
            if (this.element == null && this.treeViewer == null) {
                throw new WrappedException(new KielerModelException(
                        "KLighD: Cannot execute this effect, since the necessary data are null!\n "
                                + "Do you call it the right way?", this));
            }
            if (this.element == null) {
                throw new WrappedException(new KielerModelException(
                        "KLighD: failed to set selection of "
                                + this.treeViewer.getClass().getName() + " to null element!\n"
                                + "Do you call this effect the right way?", this));
            }
            if (this.treeViewer == null) {
                throw new WrappedException(new KielerModelException(
                        "KLighD: No tree viewer known to set the selection to " + this.element
                                + " in!\n" + "Do you call this effect the right way?", this));
            }
        }
    }
}
