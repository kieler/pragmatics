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
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizLayouter;


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
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	public void createFieldEditors() {
		// executable group
		Group executable = new Group(this.getFieldEditorParent(), SWT.NONE);
		executable.setText("Executable:");

		FileFieldEditor dotExecutable = new FileFieldEditor(
		        GraphvizLayouter.PREF_GRAPHVIZ_EXECUTABLE,
				"Dot Executable:", executable);
		dotExecutable.setValidateStrategy(FileFieldEditor.VALIDATE_ON_KEY_STROKE);
		
		executable.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true,
				false, 2, 1));
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 15;
		gridLayout.marginHeight = 10;
		executable.setLayout(gridLayout);
		
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
