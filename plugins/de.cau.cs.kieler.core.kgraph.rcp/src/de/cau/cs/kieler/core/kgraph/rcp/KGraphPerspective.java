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
package de.cau.cs.kieler.core.kgraph.rcp;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IStartup;

import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.core.kivi.CombinationDescriptor;
import de.cau.cs.kieler.core.kivi.KiVi;
import de.cau.cs.kieler.kiml.ui.views.LayoutViewPart;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.ui.parts.DiagramViewPart;

/**
 * @author uru
 * 
 */
public class KGraphPerspective implements IPerspectiveFactory, IStartup {

    public static final String PERSPECTIVE_ID = "de.cau.ca.kieler.core.kgraph.rcp.perspective";

    private static final String GRANA_VIEW_ID = "de.cau.cs.kieler.kiml.grana.views.analysisResults";

    /**
     * {@inheritDoc}
     */
    public void createInitialLayout(final IPageLayout layout) {

        String editorArea = layout.getEditorArea();

        // view layout
        layout.createFolder("left", IPageLayout.LEFT, 0.25f, editorArea);

        IFolderLayout topLeft = layout.createFolder("leftTop", IPageLayout.TOP, 0.5f, "left");
        topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
        IFolderLayout bottomLeft =
                layout.createFolder("leftBottom", IPageLayout.BOTTOM, 0.5f, "left");
        bottomLeft.addView(IPageLayout.ID_OUTLINE);
        bottomLeft.addView(GRANA_VIEW_ID);
        bottomLeft.addView(LayoutViewPart.VIEW_ID);

        // area where the klighd view will be
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, editorArea);
        bottom.addPlaceholder(DiagramViewPart.VIEW_ID);

        // view shortcuts
        layout.addShowViewShortcut(LayoutViewPart.VIEW_ID);
        layout.addShowViewShortcut(GRANA_VIEW_ID);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);

        // new actions
        layout.addNewWizardShortcut("de.cau.cs.kieler.core.kgraph.text.empty.wizard");
        layout.addNewWizardShortcut("de.cau.cs.kieler.core.kgraph.text.random.wizard");

        // perspectives
        layout.addPerspectiveShortcut(PERSPECTIVE_ID);

    }

    /*
     * ---------------------------------------------------------------------------------------
     * Workaround to activate/deactivate KiVi combinations.
     * ---------------------------------------------------------------------------------------
     */

    private final ImmutableList<String> activeCombinations = ImmutableList.of(
    // create a klighd view for kgraph editors
            "de.cau.cs.kieler.klighd.xtext.VisualizeKGraphCombination");

    /**
     * {@inheritDoc}
     */
    public void earlyStartup() {

        // some default settings
        final IPreferenceStore preferenceStore = KlighdPlugin.getDefault().getPreferenceStore();

        // make sure kivi finished loading
        KiVi.getInstance();

        // give the view management some time to boot up
        for (CombinationDescriptor combination : KiVi.getInstance().getAvailableCombinations()) {
            boolean checked =
                    activeCombinations.contains(combination.getClazz().getCanonicalName());
            preferenceStore
                    .setValue(combination.getClazz().getCanonicalName() + ".active", checked);
            combination.setActive(checked);
        }

        // make sure kivi knows about the active states internally!
        KiVi.getInstance().loadActiveStates();
    }

}
