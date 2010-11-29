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
package de.cau.cs.kieler.kiml.grana.ui;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The general grana preference page.
 * 
 * @author mri
 */
public class GranaGeneralPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * The constructor.
     */
    public GranaGeneralPreferencePage() {
    }
    
    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {

    }
    
    /**
     * {@inheritDoc}
     */
    protected Control createContents(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        Group group = new Group(composite, SWT.NONE);
        group.setText("General Options");
        //checkBox = new Button(group, SWT.CHECK | SWT.LEFT);
        //checkBox.setText("Perform analyses after layout");
        //checkBox.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        GridLayout layout = new GridLayout(1, false);
        group.setLayout(layout);
        group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        GridLayout compositeLayout = new GridLayout(1, false);
        composite.setLayout(compositeLayout);
        return composite;
    }

}
