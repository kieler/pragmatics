/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.info.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.klay.info.KimlViewerPlugin;

/**
 * Label provider for execution times retrieved from KIELER progress monitors.
 * 
 * @author msp
 */
public class ExecutionLabelProvider extends LabelProvider {

    /** path to the image used for elements. */
    private static final String IMAGE_PATH = "/icons/execution.gif";

    /** the image used for each element. */
    private Image elementImage;

    /**
     * Creates an execution label provider.
     */
    public ExecutionLabelProvider() {
        elementImage = KimlViewerPlugin.imageDescriptorFromPlugin(
                KimlViewerPlugin.PLUGIN_ID, IMAGE_PATH).createImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final Object element) {
        if (element instanceof IKielerProgressMonitor) {
            return elementImage;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText(final Object element) {
        if (element instanceof IKielerProgressMonitor) {
            IKielerProgressMonitor monitor = (IKielerProgressMonitor) element;
            String baseText = monitor.getTaskName() + ": ";
            double time = monitor.getExecutionTime();
            if (monitor.getSubMonitors().isEmpty()) {
                return baseText + toString(time);
            } else {
                double childrenTime = 0;
                for (IKielerProgressMonitor child : monitor.getSubMonitors()) {
                    childrenTime += child.getExecutionTime();
                }
                double localTime = Math.max(time - childrenTime, 0);
                return baseText + toString(time) + " [" + toString(localTime) + " local]";
            }
        } else {
            return null;
        }
    }
    
    /**
     * Convert the given time (in seconds) into a string.
     * 
     * @param time time in seconds
     * @return a string representation
     */
    private String toString(final double time) {
        if (time >= 1.0) {
            return String.format("%1$.3f s", time);
        } else {
            // SUPPRESS CHECKSTYLE NEXT MagicNumber
            return String.format("%1$.3f ms", time * 1000);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        if (elementImage != null) {
            elementImage.dispose();
            elementImage = null;
        }
    }

}
