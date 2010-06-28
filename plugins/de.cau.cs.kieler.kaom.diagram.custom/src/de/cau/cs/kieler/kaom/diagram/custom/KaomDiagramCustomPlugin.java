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
package de.cau.cs.kieler.kaom.diagram.custom;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author schm
 */
public class KaomDiagramCustomPlugin extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.kaom.diagram.custom";

    // The shared instance
    private static KaomDiagramCustomPlugin plugin;

    /**
     * The constructor.
     */
    public KaomDiagramCustomPlugin() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /**
     * Getter for the active editor part.
     * 
     * @return the editor part or null
     */
    public IEditorPart getActiveEditorPart() {
        IEditorPart result = null;
        IWorkbench workbench = getWorkbench();
        if (workbench != null) {
            IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
            if (window != null) {
                IWorkbenchPage page = window.getActivePage();
                if (page != null) {
                    result = page.getActiveEditor();
                }
            }
        }
        return result;
    }

    /**
     * Getter for the display of the active workbench.
     * 
     * @return the display
     */
    public Display getDisplay() {
        return getWorkbench().getDisplay();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static KaomDiagramCustomPlugin getDefault() {
        return plugin;
    }

}
