/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page that configures the post-transformation
 * actions.
 * @author Michael Matzen
 *
 */
public class PostTransformationPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	public PostTransformationPreferencePage() {
		// TODO Auto-generated constructor stub
	}

	public PostTransformationPreferencePage(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public PostTransformationPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
