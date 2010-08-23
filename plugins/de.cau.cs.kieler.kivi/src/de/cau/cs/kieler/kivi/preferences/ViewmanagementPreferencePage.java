/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.kivi.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for the view management.
 * 
 * @author mmu
 *
 */
public class ViewmanagementPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * Default constructor.
     */
    public ViewmanagementPreferencePage() {
        super("View management preference page");
    }
    
    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Control createContents(final Composite parent) {
        // TODO Auto-generated method stub
        return null;
    }

}
