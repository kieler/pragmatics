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
package de.cau.cs.kieler.graphs.product;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Perspective for the Graphs Product.
 *
 * @author msp
 */
public class GraphsPerspective implements IPerspectiveFactory {

    private static final float SMALL = 0.2f;
    private static final float MEDIUM = 0.5f;
    private static final float BIG = 0.7f;

    // CHECKSTYLEOFF LineLength
    private static final String VIEW_KIML_EXECUTION = "de.cau.cs.kieler.kiml.viewer.execution";
    private static final String VIEW_KIML_LAYOUTGRAPH = "de.cau.cs.kieler.kiml.viewer.layoutGraph";
    private static final String VIEW_KIML_LAYOUT = "de.cau.cs.kieler.kiml.views.layout";
    private static final String WIZ_GRAPHS = "de.cau.cs.kieler.graphs.diagram.part.GraphsCreationWizardID";
    private static final String WIZ_RANDOM = "de.cau.cs.kieler.graphs.wizards.randomGraph";
    // CHECKSTYLEON LineLength
    
    /** {@inheritDoc} */
    public void createInitialLayout(final IPageLayout layout) {
        createNewWizardShortcuts(layout);
        createViewLayout(layout);
        createViewShortcuts(layout);
    }

    /**
     * Add KIELER Views (and some important Eclipse views) as shortcuts into the View menu.
     * 
     * @param layout the page layout
     */
    private void createViewShortcuts(final IPageLayout layout) {
        layout.addShowViewShortcut(VIEW_KIML_LAYOUT);
        layout.addShowViewShortcut(VIEW_KIML_EXECUTION);
        layout.addShowViewShortcut(VIEW_KIML_LAYOUTGRAPH);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
    }

    /**
     * Create the default view layout.
     * 
     * @param layout the page layout
     */
    private void createViewLayout(final IPageLayout layout) {
        String editor = layout.getEditorArea();

        // TOP LEFT ==============================================
        IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, SMALL, editor);
        topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);

        // BOTTOM LEFT ==============================================
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, MEDIUM,
                "topLeft");
        bottomLeft.addView(IPageLayout.ID_OUTLINE);

        // BOTTOM =================================================
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, BIG, editor);
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        bottom.addView(VIEW_KIML_EXECUTION);
        bottom.addView(VIEW_KIML_LAYOUTGRAPH);
        bottom.addView(VIEW_KIML_LAYOUT);

        layout.setEditorAreaVisible(true);
    }

    /**
     * Add shortcuts to the New-Dialog.
     * 
     * @param layout the page layout
     */
    private void createNewWizardShortcuts(final IPageLayout layout) {
        layout.addNewWizardShortcut(WIZ_GRAPHS);
        layout.addNewWizardShortcut(WIZ_RANDOM);
    }

}
