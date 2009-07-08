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
package de.cau.cs.kieler.kiml.layouter.graphviz.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kiml.layouter.graphviz.Activator;
import de.cau.cs.kieler.kiml.layouter.graphviz.GraphvizLayouter;


/**
 * The Graphviz preference page.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 */
public class GraphvizPreferencePage extends FieldEditorPreferencePage
        implements IWorkbenchPreferencePage {

    /** identifier of the preference page */
	public static final String ID = "de.cau.cs.kieler.kiml.layouter.graphviz.preferences.GraphvizPreferencePage";
	
	/**
	 * Creates a Graphviz preference page.
	 */
	public GraphvizPreferencePage() {
	    super(GRID);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	public void createFieldEditors() {
		GridLayout gl;

		// debug group
		Group debug = new Group(this.getFieldEditorParent(), SWT.NONE);
		debug.setText("Debug:");

		BooleanFieldEditor enableDebug = new BooleanFieldEditor(
		        GraphvizLayouter.PREF_GRAPHVIZ_ENABLE_DEBUG_OUTPUT,
				"Enable debug output (*.dot)", debug);
		DirectoryFieldEditor debugDir = new DirectoryFieldEditor(
		        GraphvizLayouter.PREF_GRAPHVIZ_DEBUG_DIR,
				"Debug directory:", debug);
		Label description = new Label(debug, SWT.WRAP);
		description.setText("If a directory is chosen, debug output of GraphViz (the *.dot files) go there. Otherwise the user home directory is chosen.");
		description.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 2, 1));
		debug.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				false, 3, 2));
		gl = new GridLayout(3, false);
		gl.marginWidth = 15;
		gl.marginHeight = 10;
		debug.setLayout(gl);

		// executable group
		Group executable = new Group(this.getFieldEditorParent(), SWT.NONE);
		executable.setText("Executable:");

		FileFieldEditor dotExecutable = new FileFieldEditor(
		        GraphvizLayouter.PREF_GRAPHVIZ_EXECUTABLE,
				"Dot Executable:", executable);
		dotExecutable.setValidateStrategy(FileFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		executable.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				false, 2, 1));
		gl = new GridLayout(2, false);
		gl.marginWidth = 15;
		gl.marginHeight = 10;
		executable.setLayout(gl);
		
		// add all field editors
		addField(enableDebug);
		addField(debugDir);
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
