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
package de.cau.cs.kieler.kiml.ogdf;

import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * The OGDF layout provider, that is the entry class used by KIML to call individual layout
 * algorithms.
 * 
 * @author msp
 * @author mri
 */
public class OgdfLayoutProvider extends AbstractLayoutProvider {

    /** Definition of available layout algorithms. */
    public enum LayoutAlgorithm {
        /** Sugiyama's layout algorithm.  */
        SUGIYAMA,
        /** The planarization layout algorithm. */
        UMLPLANARIZATION
    }
    
    /** the selected layouter. */
    private OgdfLayouter layoutAlgorithm;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final String parameter) {
        switch (LayoutAlgorithm.valueOf(parameter)) {
        case SUGIYAMA:
            layoutAlgorithm = new SugiyamaLayouter();
            break;
        case UMLPLANARIZATION:
            layoutAlgorithm = new PlanarizationLayouter();
            break;
        default:
            layoutAlgorithm = null;
        }
    }

    private static final String PREF_EXPERIMENTAL = "experimental.dialog";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        if (layoutAlgorithm == null) {
            throw new KielerException("The OGDF layout algorithm is not configured correctly."
                    + " Please check the parameter in the extension point");
        }
        
        // display a warning that this feature is experimental
        final IPreferenceStore preferenceStore = OgdfPlugin.getDefault().getPreferenceStore();
        final Maybe<Boolean> docontinue = new Maybe<Boolean>();
        docontinue.set(true);
        if (!preferenceStore.getString(PREF_EXPERIMENTAL).equals(MessageDialogWithToggle.ALWAYS)) {
            final Display display = Display.getDefault();
            display.syncExec(new Runnable() {
                public void run() {
                    Window dialog = MessageDialogWithToggle.openOkCancelConfirm(
                            display.getActiveShell(), "Experimental Feature",
                            "Warning: The OGDF layouters feature is to be regarded as experimental"
                            + " and may lead to sudden crash of the Eclipse process. Continue?",
                            "Don't show this message again", true,
                            preferenceStore, PREF_EXPERIMENTAL);
                    docontinue.set(dialog.getReturnCode() == Window.OK);
                }
            });
        }

        if (docontinue.get()) {
            // layout the graph with the selected algorithm
            layoutAlgorithm.doLayout(layoutNode, progressMonitor);
        } else {
            // mark the graph as not layouted
            for (KNode child : layoutNode.getChildren()) {
                KShapeLayout nodeLayout = KimlLayoutUtil.getShapeLayout(child);
                LayoutOptions.setBoolean(nodeLayout, LayoutOptions.NO_LAYOUT, true);
                for (KPort port : layoutNode.getPorts()) {
                    KShapeLayout portLayout = KimlLayoutUtil.getShapeLayout(port);
                    LayoutOptions.setBoolean(portLayout, LayoutOptions.NO_LAYOUT, true);
                }
                for (KEdge edge : layoutNode.getOutgoingEdges()) {
                    KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
                    LayoutOptions.setBoolean(edgeLayout, LayoutOptions.NO_LAYOUT, true);
                    for (KLabel label : edge.getLabels()) {
                        KShapeLayout labelLayout = KimlLayoutUtil.getShapeLayout(label);
                        LayoutOptions.setBoolean(labelLayout, LayoutOptions.NO_LAYOUT, true);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getDefault(final String optionId) {
        if (layoutAlgorithm == null) {
            return null;
        } else {
            return layoutAlgorithm.getDefault(optionId);
        }
    }
}
