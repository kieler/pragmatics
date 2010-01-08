/**
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * The KSBasE preference page, contains the transformation and
 * post-transformation pages.
 * 
 * @author mim
 * 
 */
public class KSBasEPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    /**
     * Default constructor.
     */
    public KSBasEPreferencePage() {
        setDescription(Messages.kSBasEPreferencePageTitle);
        noDefaultAndApplyButton();
    }

    /**
     * Creates the contents of this page. Since this is only a group-page there
     * is no content here.
     * 
     * @param parent
     *            The parent composite.
     * @return The created control.
     */
    @Override
    protected Control createContents(final Composite parent) {
        Link editorLink = new Link(parent, SWT.NONE);
        editorLink.setText("\nThis page contains a subpage for the <A>editors preference page</A>"
                + "\nUse this page to create structure based editing features for a new editor");
        editorLink.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                PreferencesUtil.createPreferenceDialogOn(
                        getShell(), "de.cau.cs.kieler.ksbase.EditorPreferencePage", null, null);
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });
        new Label(parent, SWT.NONE).setText("");
        Link transLink = new Link(parent, SWT.NONE);
        transLink.setText("And if the KIELER Viewmanagement plug-in is installed a page for "
                + "configuring the <A>post-transformation settings</A>");
        transLink.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                PreferencesUtil.createPreferenceDialogOn(
                        getShell(), "de.cau.cs.kieler.ksbase.PostPreferencePage", null, null);

            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        return null;
    }

    /**
     * Initializes this preference page.
     * 
     * @param workbench
     *            The workbench for this preference page.
     */
    public void init(final IWorkbench workbench) {
        return;
    }

}
