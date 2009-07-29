/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphviz.layouter.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kiml.graphviz.layouter.Activator;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizAPI;


/**
 * The Graphviz preference page.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 */
public class GraphvizPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

    /** identifier of the preference page */
	public static final String ID = "de.cau.cs.kieler.kiml.graphviz.preferences";
	
	/**
	 * Creates a Graphviz preference page.
	 */
	public GraphvizPreferencePage() {
	    super(GRID);
	    setDescription("Preferences for the Graphviz layouter.");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	public void createFieldEditors() {
		// executable group
		Group executable = new Group(this.getFieldEditorParent(), SWT.NONE);
		executable.setText("Path to Executable");

		FileFieldEditor dotExecutable = new FileFieldEditor(
		        GraphvizAPI.PREF_GRAPHVIZ_EXECUTABLE,
				"Set a correct path to the 'dot' executable for Graphviz:", executable);
		dotExecutable.getLabelControl(executable).setLayoutData(new GridData(SWT.LEFT, SWT.TOP,
				false, false, 2, 1));
		dotExecutable.setValidateStrategy(FileFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		executable.setLayout(new GridLayout(2, false));
		executable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		// add all field editors
		addField(dotExecutable);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

}
