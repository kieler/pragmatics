/*
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The KSBasE preference page, contains the transformation and
 * post-transformation pages.
 * 
 * @author Michael Matzen
 * 
 */
public class KSBasEPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    /**
     * Default constructor.
     */
    public KSBasEPreferencePage() {
        setDescription(Messages.KSBasEPreferencePage_Title);
        noDefaultAndApplyButton();
    }

    /**
     * Creates the contents of this page.
     * Since this is only a group-page there is no content here.
     * @param parent The parent composite.
     * @return The created control.
     */
    @Override
    protected Control createContents(final Composite parent) {
        return null;
    }

    /**
     * Initializes this preference page.
     * @param workbench The workbench for this preference page.
     */
    public void init(final IWorkbench workbench) {
        return;
    }

}
