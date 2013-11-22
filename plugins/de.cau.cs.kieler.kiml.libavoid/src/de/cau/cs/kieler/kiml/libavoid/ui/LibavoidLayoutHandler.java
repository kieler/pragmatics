/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.kiml.libavoid.ui;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.kiml.config.ILayoutConfig;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.service.DiagramLayoutEngine;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Handler for execution of libavoid layout triggered by menu, toolbar, or keyboard command. First a
 * usual layout run is performed using the algorithm specified within the layout view. Afterwards
 * another run is performed with the libavoid connector routing algorihtm.
 * 
 * @author uru
 * @author msp
 */
public class LibavoidLayoutHandler extends AbstractHandler {

    /** preference identifier for animation of layout. */
    public static final String PREF_ANIMATION = "de.cau.cs.kieler.kiml.animation";
    /** preference identifier for zoom-to-fit after layout. */
    public static final String PREF_ZOOM = "de.cau.cs.kieler.kiml.zoomToFit";
    /** preference identifier for progress dialog. */
    public static final String PREF_PROGRESS = "de.cau.cs.kieler.kiml.progressDialog";
    /** parameter identifier for the scope of automatic layout. */
    public static final String PARAM_LAYOUT_SCOPE = "de.cau.cs.kieler.kiml.ui.layoutScope";
    /** value for diagram scope. */
    public static final String VAL_DIAGRAM = "diagram";
    /** value for selection scope. */
    public static final String VAL_SELECTION = "selection";

    /** a list holding the required layout configs. */
    private final List<ILayoutConfig> layoutConfigs;

    /**
     * Initializes the handler with the appropriate layout configs.
     */
    public LibavoidLayoutHandler() {
        LightLayoutConfig libavoidConfig = new LightLayoutConfig();
        libavoidConfig.setOption(LayoutOptions.ALGORITHM, "org.adaptagrams.cola.libavoid");

        layoutConfigs = Lists.newArrayList(new VolatileLayoutConfig(), libavoidConfig);
    }

    /**
     * {@inheritDoc}
     */
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        ISelection selection = null;

        // check parameter for layout scope, default is diagram scope
        String layoutScope = event.getParameter(PARAM_LAYOUT_SCOPE);
        if (layoutScope != null && layoutScope.equals(VAL_SELECTION)) {
            selection = HandlerUtil.getCurrentSelection(event);
        }

        // fetch general settings from preferences
        IPreferenceStore preferenceStore = KimlUiPlugin.getDefault().getPreferenceStore();
        boolean animation = preferenceStore.getBoolean(PREF_ANIMATION);
        boolean zoomToFit = preferenceStore.getBoolean(PREF_ZOOM);
        boolean progressDialog = preferenceStore.getBoolean(PREF_PROGRESS);

        // get the active editor, which is expected to contain the diagram for applying layout
        IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
        if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
            // perform layout with the given selection (only the first element is considered)
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Object diagramPart;
            if (structuredSelection.size() == 1) {
                diagramPart = structuredSelection.getFirstElement();
            } else {
                diagramPart = structuredSelection.toList();
            }
            DiagramLayoutEngine.INSTANCE.layout(editorPart, diagramPart, animation, progressDialog,
                    false, zoomToFit, layoutConfigs);
        } else {
            // perform layout on the whole diagram
            DiagramLayoutEngine.INSTANCE.layout(editorPart, null, animation, progressDialog, false,
                    zoomToFit, layoutConfigs);
        }
        return null;
    }

}
