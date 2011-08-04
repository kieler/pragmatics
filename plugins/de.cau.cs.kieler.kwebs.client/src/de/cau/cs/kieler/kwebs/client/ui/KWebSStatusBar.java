/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

import de.cau.cs.kieler.kwebs.client.activator.Activator;
import de.cau.cs.kieler.kwebs.client.preferences.Preferences;

/**
 * This class makes an addition to the status bar so that the user can identify whether he is
 * doing local or remote layout.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class KWebSStatusBar extends WorkbenchWindowControlContribution 
    implements IPropertyChangeListener {

    /** The id of this widget. */
    public static final String WIDGET_ID
        = "de.cau.cs.kieler.kwebs.client.ui.kwebsstatusbar";
    
    /** The label containing the image displayed in the status bar. */
    private Label image;
    
    /** The preference store. */
    private IPreferenceStore preferenceStore
        = Preferences.getPreferenceStore();
    
    /**
     * Creates a new status bar contribution instance.
     */
    public KWebSStatusBar() {
        preferenceStore.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        preferenceStore.removePropertyChangeListener(this);
        super.dispose();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        preferenceStore.removePropertyChangeListener(this);
        super.finalize();
    }
    
    /**
     * {@inheritDoc}
     */
    protected Control createControl(final Composite parent) {
        // UI code needs magic numbers
        //CHECKSTYLEOFF MagicNumber
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
        layout.marginLeft = 0;
        layout.marginRight = 0;
        layout.marginHeight = 6;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));    
        image = new Label(composite, SWT.NONE);
        setStatusInfo();
        image.addMouseListener(
            new MouseListener() {            
                public void mouseUp(final MouseEvent e) {
                }                
                public void mouseDown(final MouseEvent e) {
                }                
                public void mouseDoubleClick(final MouseEvent e) {
                    PreferencesUtil.createPreferenceDialogOn(
                        null, 
                        RemoteLayoutPreferencePage.PAGE_ID, 
                        null, 
                        null
                    ).open();
                }
            }
        );
        return composite;        
        //CHECKSTYLEON MagicNumber
    }

    /**
     * Listen to preference changes and update status text.
     *
     * @param event
     *            the property change event
     */
    public final synchronized void propertyChange(final PropertyChangeEvent event) {
        if (event.getProperty().equals(Preferences.PREFID_LAYOUT_SETTINGS_CHANGED)) {
            setStatusInfo();
        }
    }

    /** Path to the resource for the image for doing local layout. */
    private static final String LOCAL_IMAGE
        = "icons/local.gif";

    /** Path to the resource for the image for doing remote layout. */
    private static final String REMOTE_IMAGE
        = "icons/remote.gif";

    /**
     * Sets the text of the status label.
     */
    public void setStatusInfo() {
        boolean remoteLayout = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        ImageDescriptor descriptor = null;
        if (remoteLayout) {
            descriptor = Activator.getImageDescriptor(REMOTE_IMAGE);
        } else {
            descriptor = Activator.getImageDescriptor(LOCAL_IMAGE);
        }          
        if (descriptor != null) {
            image.setImage(descriptor.createImage());
        }
    }    

}
