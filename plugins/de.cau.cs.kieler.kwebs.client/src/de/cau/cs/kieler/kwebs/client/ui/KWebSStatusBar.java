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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.menus.WorkbenchWindowControlContribution;

import de.cau.cs.kieler.kwebs.client.LayoutHistory;
import de.cau.cs.kieler.kwebs.client.LayoutHistoryPage;
import de.cau.cs.kieler.kwebs.client.KwebsClientPlugin;
import de.cau.cs.kieler.kwebs.client.layout.SwitchLayoutMode;
import de.cau.cs.kieler.kwebs.client.Preferences;

/**
 * This class makes an addition to the status bar so that the user can identify whether he is
 * doing local or remote layout.
 *
 * @author swe
 */
public class KWebSStatusBar extends WorkbenchWindowControlContribution 
    implements IPropertyChangeListener {

    /** The id of this widget. */
    public static final String WIDGET_ID
        = "de.cau.cs.kieler.kwebs.client.ui.kwebsstatusbar";
    
    /** The label containing the image displayed in the status bar. */
    private Label image;
    
    /** Item for the context menus local layout entry. */
    private MenuItem localLayoutItem;
    
    /** Item for the context menus remote layout entry. */
    private MenuItem remoteLayoutItem;
    
    /** Item for the context menus statistics entry. */
    private MenuItem statisticsItem;
    
    /** The popup menu for right mouse click on tray image. */
    private Menu trayPopup;
    
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
        image = new Label(composite, SWT.WRAP);
        setStatusInfo();
        image.addMouseListener(
            new MouseListener() {            
                public void mouseUp(final MouseEvent e) {
                    if (e.getSource() == image) {
                        // Not sure what happens when the used mouse only has two buttons,
                        // so we test for button 2 and 3 which is normally middle/wheelclick and right
                        // mouse button.
                        if (e.button == 2 || e.button == 3) {
                            trayPopup.setVisible(true);
                        }
                    }
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
        trayPopup = new Menu(parent.getShell(), SWT.POP_UP);
        localLayoutItem = new MenuItem(trayPopup, SWT.PUSH);
        localLayoutItem.setText("Local Layout");
        localLayoutItem.setImage(localImage);
        localLayoutItem.addSelectionListener(
            new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    if (e.getSource() == localLayoutItem) {
                        SwitchLayoutMode.toLocal();
                    }
                }
            }
        );
        remoteLayoutItem = new MenuItem(trayPopup, SWT.PUSH);
        remoteLayoutItem.setText("Service Based Layout");
        remoteLayoutItem.setImage(remoteImage);
        remoteLayoutItem.addSelectionListener(
            new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    if (e.getSource() == remoteLayoutItem) {
                        SwitchLayoutMode.toRemote();
                    }
                }
            }
        );
        statisticsItem = new MenuItem(trayPopup, SWT.PUSH);
        statisticsItem.setText("Show Statistics");
        statisticsItem.setImage(statisticsImage);
        statisticsItem.addSelectionListener(
            new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    if (e.getSource() == statisticsItem) {
                        Shell shell = Display.getCurrent().getActiveShell();
                        if (LayoutHistory.getInstance().getStatistics().size() > 0) {
                            BrowserDialog dialog = new BrowserDialog(
                                shell, 
                                LayoutHistoryPage.generateHtml(), 
                                null,
                                "Remote Layout Statistics",
                                new Rectangle(0, 0, 600, 350)
                            );
                            dialog.open();
                        } else {
                            MessageBox box = new MessageBox(shell, SWT.ABORT);
                            box.setText("No Statistical Data");
                            box.setMessage(
                                "You have not done any remote layout yet so no"
                                + " statistical data has been gathered."
                            );
                            box.open();
                        }
                    }
                }
            }
        );
        setStatusInfo();
        return composite;        
        //CHECKSTYLEON MagicNumber
    }

    /**
     * Listen to preference changes and update the context menu, status symbol and text.
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
    private static final String LOCAL_IMAGEPATH
        = "icons/local.gif";

    /** Path to the resource for the image for doing remote layout. */
    private static final String REMOTE_IMAGEPATH
        = "icons/remote.gif";

    /** Path to the resource for the image for showing layout statistics. */
    private static final String STATISTICS_IMAGEPATH
        = "icons/statistics.gif";

    /** Image for doing local layout. */
    private static Image localImage;

    /** Image for doing remote layout. */
    private static Image remoteImage;

    /** Image for showing layout statistics. */
    private static Image statisticsImage;

    /** Initialize the images. */
    static {
        ImageDescriptor descriptor = KwebsClientPlugin.getImageDescriptor(LOCAL_IMAGEPATH);
        if (descriptor != null) {
            localImage = descriptor.createImage();
        }
        descriptor = KwebsClientPlugin.getImageDescriptor(REMOTE_IMAGEPATH);
        if (descriptor != null) {
            remoteImage = descriptor.createImage();
        }
        descriptor = KwebsClientPlugin.getImageDescriptor(STATISTICS_IMAGEPATH);
        if (descriptor != null) {
            statisticsImage = descriptor.createImage();
        }
    }
    
    /**
     * Sets the icon of the status label.
     */
    public void setStatusInfo() {
        final Image icon = (SwitchLayoutMode.isRemoteLayoutActive() ? remoteImage : localImage);
        final String tooltip = "You are using " 
                             + (SwitchLayoutMode.isRemoteLayoutActive() ? "service based" : "local") 
                             + " layout."
                             + (
                                 SwitchLayoutMode.isRemoteLayoutInstalled()
                                 ?
                                 " You can double click on this item to change layout settings"
                                 + " or switch between local and remote layout by using the"
                                 + " right mouse button."
                                 :
                                 " Currently no client feature is installed, therefore service based"
                                 + " layout is not available. You can double click on this item to"
                                 + " see how you can install client support for service based layout."
                             );
        Display.getDefault().syncExec(new Runnable() {            
            public void run() {
                image.setToolTipText(tooltip);
                if (icon != null) {
                    image.setImage(icon);
                }     
                if (localLayoutItem != null) {
                    localLayoutItem.setEnabled(SwitchLayoutMode.isRemoteLayoutActive());
                }
                if (remoteLayoutItem != null) {
                    remoteLayoutItem.setEnabled(!SwitchLayoutMode.isRemoteLayoutActive() 
                        && SwitchLayoutMode.isRemoteLayoutInstalled()
                    );
                }
                if (statisticsItem != null) {
                    statisticsItem.setEnabled(SwitchLayoutMode.isRemoteLayoutInstalled());
                }
            }
        });
    }    

}
