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
package de.cau.cs.kieler.kiml.evol;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for KIML Evolutionary.
 *
 * @author bdu
 *
 */
public class EvolPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * Creates a new preference page with no title and no image.
     */
    public EvolPreferencePage() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a new preference page with the given title and no image.
     *
     * @param title
     *            the title of this preference page
     */
    public EvolPreferencePage(final String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    /**
     * Creates a new preference page with the given title and image.
     *
     * @param title
     *            the title of this preference page
     * @param image
     *            the image for this preference page, or {@code null} if none
     */
    public EvolPreferencePage(final String title, final ImageDescriptor image) {
        super(title, image);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(EvolPlugin.getDefault().getPreferenceStore());
    }

    @Override
    protected Control createContents(final Composite parent) {
        // TODO Auto-generated method stub
        return null;
    }
}
