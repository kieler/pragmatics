/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kivi.internal;

import org.eclipse.core.resources.IResource;

import de.cau.cs.kieler.klighd.IDiagramWorkbenchPart;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.internal.IKlighdTrigger;
import de.cau.cs.kieler.klighd.kivi.triggers.KlighdResourceDropTrigger;
import de.cau.cs.kieler.klighd.kivi.triggers.KlighdResourceDropTrigger.KlighdResourceDropState;
import de.cau.cs.kieler.klighd.kivi.triggers.KlighdStatusTrigger;
import de.cau.cs.kieler.klighd.kivi.triggers.KlighdStatusTrigger.KlighdStatusState;

/**
 * Standard implementation of {@link IKlighdTrigger}.<br>
 * This class is instantiated by {@link de.cau.cs.kieler.klighd.KlighdPlugin#getTrigger()
 * KlighdPlugin.getTrigger()} via {@link Class#forName(String)} and {@link Class#newInstance()}.
 * Therefore, this class does not declare any specialized constructor. Although only a singleton
 * instance is created by {@link de.cau.cs.kieler.klighd.KlighdPlugin KlighdPlugin} this class shall
 * not declare any private fields. That might lead to trouble if this class is wrongly instantiated
 * in application code.
 * 
 * @author chsch
 */
public final class KlighdTrigger implements IKlighdTrigger {

    /**
     * {@inheritDoc}
     */
    public void triggerStatus(final Status status, final ViewContext viewContext) {
        if (KlighdStatusTrigger.getInstance() != null) {
            final IDiagramWorkbenchPart part = viewContext.getDiagramWorkbenchPart();
            final KlighdStatusState state =
                    new KlighdStatusState(status, part == null ? null : part.getPartId(),
                            viewContext, null);
            KlighdStatusTrigger.getInstance().trigger(state);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public void triggerDrop(final String viewId, final IResource resource) {
        KlighdResourceDropTrigger trigger = KlighdResourceDropTrigger.getInstance();
        if (trigger != null) {
            final KlighdResourceDropState state = new KlighdResourceDropState(viewId, resource);
            trigger.trigger(state);
        }
    }
}
