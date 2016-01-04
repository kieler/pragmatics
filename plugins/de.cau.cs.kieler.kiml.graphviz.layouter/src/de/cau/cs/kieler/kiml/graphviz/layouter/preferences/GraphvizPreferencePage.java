/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizLayoutProvider;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizTool;

/**
 * The Graphviz preference page.
 * 
 * @author ars
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class GraphvizPreferencePage extends FieldEditorPreferencePage implements
        IWorkbenchPreferencePage {

    /** identifier of the preference page. */
    public static final String ID = "de.cau.cs.kieler.kiml.graphviz.preferences";

    /**
     * Creates a Graphviz preference page.
     */
    public GraphvizPreferencePage() {
        super(GRID);
        setDescription("Controls how KIELER interacts with the Graphviz layout tools. "
                + "The Graphviz layout tools are available at http://www.graphviz.org/. "
                + "If the 'dot' executable cannot be found in default locations, "
                + "its path must be entered here.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createFieldEditors() {
        // Process group
        Composite executableEditorParent = getFieldEditorParent();
        FileFieldEditor executableEditor = new FileFieldEditor(GraphvizTool.PREF_GRAPHVIZ_EXECUTABLE,
                "Path to 'dot' executable:", executableEditorParent);
        executableEditor.setValidateStrategy(FileFieldEditor.VALIDATE_ON_KEY_STROKE);
        addField(executableEditor);

        Composite timeoutEditorParent = getFieldEditorParent();
        IntegerFieldEditor timeoutEditor = new IntegerFieldEditor(GraphvizTool.PREF_TIMEOUT,
                "Timeout (ms):", timeoutEditorParent);
        timeoutEditor.setValidRange(GraphvizTool.PROCESS_MIN_TIMEOUT, Integer.MAX_VALUE);
        addField(timeoutEditor);

        BooleanFieldEditor restartGraphvizProcessCheckbox = new BooleanFieldEditor(
                GraphvizLayoutProvider.PREF_GRAPHVIZ_REUSE_PROCESS,
                "Reuse single graphviz process for better performance", getFieldEditorParent());
        addField(restartGraphvizProcessCheckbox);
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(GraphvizLayouterPreferenceStore.getInstance().getPreferenceStore());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performOk() {
        // dispose all cached Graphviz instances to ensure creation of new processes
        for (LayoutAlgorithmData data : LayoutMetaDataService.getInstance().getAlgorithmData()) {
            if ("de.cau.cs.kieler.kiml.categories.graphviz".equals(data.getCategory())) {
                data.getInstancePool().clear();
            }
        }
        return super.performOk();
    }

}
