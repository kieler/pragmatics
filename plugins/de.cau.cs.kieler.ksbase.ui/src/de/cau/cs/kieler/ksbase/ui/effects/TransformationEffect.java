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
package de.cau.cs.kieler.ksbase.ui.effects;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.kivi.AbstractEffect;
import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.m2m.ITransformationContext;
import de.cau.cs.kieler.ksbase.m2m.TransformationDescriptor;
import de.cau.cs.kieler.ksbase.m2m.TransformationObserver;

/**
 * A KiVi effect that executes a specific transformation.
 * 
 * @author uru
 * @kieler.design proposed 2012-11-06 cds msp
 * @kieler.rating 2011-02-14 yellow
 *      review by msp, haf
 */
public class TransformationEffect extends AbstractEffect {

    /**
     * The transformation context containing all necessary information in order to execute the
     * transformation.
     */
    private ITransformationContext context;
    
    /**
     * Transformation descriptor holding information about the name of a transformation method
     * and the parameters that should be passed to this method.
     */
    private TransformationDescriptor descriptor;
    
    /**
     * Whether the transformation should be processed as a monitored operation.
     */
    private boolean monitored = false;

    
    /**
     * default constructor.
     */
    public TransformationEffect() {
    }

    
    /**
     * @param context
     *            {@link ITransformationContext} containing all necessary information in order to
     *            execute the transformation.
     * @param descriptor
     *            The transformation's {@link TransformationDescriptor}
     */
    public TransformationEffect(final ITransformationContext context,
            final TransformationDescriptor descriptor) {
        this(context, descriptor, false);
    }

    /**
     * @param context
     *            {@link ITransformationContext} containing all necessary information in order to
     *            execute the transformation.
     * @param descriptor
     *            The transformation's {@link TransformationDescriptor}.
     * @param monitored
     *            Determines whether the execution should be processed as a monitored operation.
     */
    public TransformationEffect(final ITransformationContext context,
            final TransformationDescriptor descriptor, final boolean monitored) {
        this.context = context;
        this.descriptor = descriptor;
        this.monitored = monitored;
    }

    /**
     * {@inheritDoc}
     */
    public void execute() {
        if (context != null && descriptor != null) {
            // execute the transformation
            if (monitored) {
                try {
                    PlatformUI.getWorkbench().getProgressService().run(
                            false, true, new IRunnableWithProgress() {
                        public void run(final IProgressMonitor uiMonitor) {
                            context.execute(descriptor);
                            TransformationObserver.getInstance().notifyListeners(context, descriptor);
                        }
                    });
                } catch (Exception exception) {
                    IStatus status = new Status(Status.ERROR, KSBasEPlugin.PLUGIN_ID,
                            "An error occurred while executiong the transformation.", exception);
                    StatusManager.getManager().handle(status);
                }
            } else {
                context.execute(descriptor);
                TransformationObserver.getInstance().notifyListeners(context, descriptor);
            }
        } else {
            IStatus status = new Status(Status.ERROR, KSBasEPlugin.PLUGIN_ID,
                    "A TransformationEffect could not be executed. Either the "
                            + "TransformationContext or the TransformationDescriptor has not been set.");
            StatusManager.getManager().handle(status);
        }
    }

    /**
     * @return the result of the executed transformation.
     */
    public Object getResult() {
        return descriptor.getResult();
    }

    /**
     * @param context
     *            the context to set
     */
    public void setContext(final ITransformationContext context) {
        this.context = context;
    }

    /**
     * @param descriptor
     *            the descriptor to set
     */
    public void setDescriptor(final TransformationDescriptor descriptor) {
        this.descriptor = descriptor;
    }
}
