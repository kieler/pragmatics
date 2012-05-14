/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug.actions;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KGraphData;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.PersistentEntry;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.RecursiveGraphLayoutEngine;
import de.cau.cs.kieler.kiml.debug.KimlViewerPlugin;
import de.cau.cs.kieler.kiml.debug.Messages;
import de.cau.cs.kieler.kiml.debug.UpdateViewerEffect;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * An action for loading a KGraph and performing layout on it.
 *
 * @author msp
 */
public class LoadGraphAction extends Action {
    
    /** identifier string for this action. */
    private static final String ACTION_ID = "de.cau.cs.kieler.kiml.debug.loadGraph";
    /** relative path to the icon to use for this action. */
    private static final String ICON_PATH = "icons/kieler-arrange.gif";
    /** preference identifier for the last used file name. */
    private static final String LAST_FILE_NAME_PREF = "klay.info.lastKgraphFile";
    
    /**
     * Creates a load graph action.
     */
    public LoadGraphAction() {
        setId(ACTION_ID);
        setText(Messages.getString("kiml.viewer.15"));
        setToolTipText(Messages.getString("kiml.viewer.13"));
        setImageDescriptor(KimlViewerPlugin.imageDescriptorFromPlugin(KimlViewerPlugin.PLUGIN_ID,
                ICON_PATH));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        IPreferenceStore preferenceStore = KimlViewerPlugin.getDefault().getPreferenceStore();

        // create and configure file dialog
        FileDialog fileDialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
        fileDialog.setFilterExtensions(new String[] { "*.kgraph", "*.xmi", "*.*" });
        fileDialog.setText(Messages.getString("kiml.viewer.14"));
        fileDialog.setFileName(preferenceStore.getString(LAST_FILE_NAME_PREF));

        // open the file dialog and wait for file name
        String fileName = fileDialog.open();
        
        if (fileName != null) {
            preferenceStore.setValue(LAST_FILE_NAME_PREF, fileName);
            
            // load the file content
            ResourceSet resourceSet = new ResourceSetImpl();
            URI uri = URI.createFileURI(fileName);
            Resource resource = resourceSet.createResource(uri);
            try {
                resource.load(null);
                KNode content = (KNode) resource.getContents().get(0);
                layout(content);
            } catch (IOException exception) {
                throw new WrappedException(exception);
            }
        }
    }
    
    /**
     * Perform layout and draw the resulting graph.
     * 
     * @param graph a graph
     */
    private void layout(final KNode graph) {
        // deserialize layout options
        LayoutDataService dataService = LayoutDataService.getInstance();
        Iterator<EObject> contentIter = graph.eAllContents();
        while (contentIter.hasNext()) {
            EObject obj = contentIter.next();
            if (obj instanceof KGraphData) {
                KGraphData graphData = (KGraphData) obj;
                for (PersistentEntry entry : graphData.getPersistentEntries()) {
                    LayoutOptionData<?> optionData = dataService.getOptionData(entry.getKey());
                    
                    if (optionData != null) {
                        Object value = optionData.parseValue(entry.getValue());
                        
                        if (value != null) {
                            graphData.setProperty(optionData, value);
                        }
                    }
                }
            }
        }
        
        // perform layout using a graph layout engine
        KShapeLayout graphLayout = graph.getData(KShapeLayout.class);
        IKielerProgressMonitor monitor = new BasicProgressMonitor();
        if (graphLayout != null && !graphLayout.getProperty(LayoutOptions.NO_LAYOUT)) {
            IGraphLayoutEngine layoutEngine = new RecursiveGraphLayoutEngine();
            layoutEngine.layout(graph, monitor);
        }
        
        // draw the resulting layout on the canvas
        KiVi.getInstance().executeEffect(new UpdateViewerEffect(graph, monitor));
    }

}
