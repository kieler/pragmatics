/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.effects;

import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.klighd.viewers.ContextViewer;
import de.cau.cs.kieler.klighd.views.IDiagramWorkbenchPart;


/**
 * Effect for panning to a given element inside a klighd view.
 * 
 * @author ckru
 *
 */
public class KlighdPanningEffect extends AbstractEffect {

    /**
     * The context viewer of the klighd view in which to scroll.
     */
    private ContextViewer viewer;
    /**
     * The element to scroll to.
     */
    private Object element;
    /**
     * The duration to execute the scrolling animation.
     */
    private int animationDuration = 0;
    /**
     * The zoomlevel to be applied after scrolling.
     */
    private float zoomLevel = 0;

    /**
     * Constructor for the panning effect.
     * @param part the workbench part the klighd view is connceted to.
     * @param element the element to scroll to.
     */
    public KlighdPanningEffect(final IWorkbenchPart part, final Object element) {
        if (part instanceof IDiagramWorkbenchPart) {
            viewer = ((IDiagramWorkbenchPart) part).getContextViewer();
        }
        this.element = element;
    }

    /**
     * Constructor for the panning effect.
     * @param part the workbench part the klighd view is connceted to.
     * @param element the element to scroll to.
     * @param animationDuration the duration to execute the scrolling animation. Set 0 for no animation.
     * @param zoomLevel the zoomlevel to be applied after scrolling. Set 0 for no zooming.
     */
    public KlighdPanningEffect(final IWorkbenchPart part, final Object element,
            final int animationDuration, final float zoomLevel) {
        this(part, element);
        this.animationDuration = animationDuration;
        this.zoomLevel = zoomLevel;
    }

    /**
     * Constructor for the panning effect.
     * @param part the workbench part the klighd view is connceted to.
     * @param element the element to scroll to.
     * @param zoomLevel the zoomlevel to be applied after scrolling. Set 0 for no zooming.
     */
    public KlighdPanningEffect(final IWorkbenchPart part, final Object element,
            final float zoomLevel) {
        this(part, element);
        this.zoomLevel = zoomLevel;
    }

    /**
     * Constructor for the panning effect.
     * @param part the workbench part the klighd view is connceted to.
     * @param element the element to scroll to.
     * @param animationDuration the duration to execute the scrolling animation. Set 0 for no animation.
     */
    public KlighdPanningEffect(final IWorkbenchPart part, final Object element,
            final int animationDuration) {
        this(part, element);
        this.animationDuration = animationDuration;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        if (element instanceof KGraphElement) {
            viewer.centerOn((KGraphElement) element, animationDuration);
        } else {
            viewer.centerOn(element, animationDuration);
        }
        if (zoomLevel != 0) {
            viewer.zoomToLevel(zoomLevel, animationDuration);
        }
    }

}
