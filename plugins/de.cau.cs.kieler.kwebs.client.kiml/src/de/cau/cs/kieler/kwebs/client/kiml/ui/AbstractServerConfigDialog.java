/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.kiml.ui;

import java.net.URI;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.kwebs.client.ServerConfig;
import de.cau.cs.kieler.kwebs.client.kiml.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs.ServerConfigError;

/**
 * Abstract class for providing basic dialog functionality for {@link NewServerConfigDialog}
 * and {@link EditProviderDialog}.
 *
 * @kieler.rating 2011-05-14
 * @author swe
 */
public abstract class AbstractServerConfigDialog extends Dialog {

    //CHECKSTYLEOFF VisibilityModifier
    
    /** Name of the server configuration. */
    protected Text serverConfigName;

    /** Service address of server configuration. */
    protected Text serverConfigAddress;

    /** Path to trust store for HTTPS connections. */
    protected Text truststore;

    /** Password for trust store. */
    protected Text truststorePass;
    
    /** The button for the trust store selection dialog. */
    protected Button truststoreButton;

    /** Button for testing service availability. */
    protected Button checkButton;

    /** Button for showing service details. */
    protected Button detailButton;

    //CHECKSTYLEON VisibilityModifier
    
    /**
     * Creates a dialog with elements for editing a server configuration.
     * 
     * @param parentShell
     *            the parent shell of this dialog
     */
    protected AbstractServerConfigDialog(final Shell parentShell) {
        super(parentShell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createServerConfigGroup(composite);
        return composite;
    }

    // **********

    /** Standard width of text fields. */
    private static final int TEXT_WIDTHHINT
        = 350;
    
    /** Prefix of a HTTPS URI. */
    private static final String HTTPS_PREFIX
        = "https";
    
    /**
     *
     * @param parent
     */
    private void createServerConfigGroup(final Composite parent) {
        
        GridData gd;
        
        GridLayout parentLayout = (GridLayout) parent.getLayout();
        parentLayout.numColumns = 2;
        parentLayout.makeColumnsEqualWidth = false;
        
        Label label = new Label(parent, SWT.NULL);
        label.setText("Configuration Name:");
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        
        serverConfigName = new Text(parent, SWT.SINGLE | SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.minimumWidth = TEXT_WIDTHHINT;
        serverConfigName.setLayoutData(gd);
        
        label = new Label(parent, SWT.NULL);
        label.setText("Address:");
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        
        serverConfigAddress = new Text(parent, SWT.SINGLE | SWT.BORDER);
        serverConfigAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        serverConfigAddress.addModifyListener(
            new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    URI address = getAddress();                    
                    boolean enabled = (
                        address != null && address.toString().toLowerCase().startsWith(HTTPS_PREFIX)
                    );                    
                    truststore.setEnabled(enabled);
                    truststorePass.setEnabled(enabled);
                    truststoreButton.setEnabled(enabled);
                }
            }
        );
        
        Group trustStoreGroup = new Group(parent, SWT.NULL);
        trustStoreGroup.setText("HTTPS Authentication");
        trustStoreGroup.setLayout(new GridLayout(3, false));
        gd = new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1);
        gd.verticalIndent = 10;
        trustStoreGroup.setLayoutData(gd);
        
        label = new Label(trustStoreGroup, SWT.NULL);
        label.setText("Trust Store:");
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        
        truststore = new Text(trustStoreGroup, SWT.SINGLE | SWT.BORDER);
        truststore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Only enabled on HTTPS connections
        truststore.setEnabled(false);

        truststoreButton = new Button(trustStoreGroup, SWT.NONE);        
        truststoreButton.setText("...");
        truststoreButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        truststoreButton.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == truststoreButton) {
                        FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                        dialog.setText("Select trust store");
                        String result = dialog.open();
                        if (result != null) {
                            truststore.setText(result);
                        }
                    }
                }
            }
        );

        // Only enabled on HTTPS connections
        truststoreButton.setEnabled(false);
        
        label = new Label(trustStoreGroup, SWT.NULL);
        label.setText("Password:");
        label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));

        truststorePass = new Text(trustStoreGroup, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
        truststorePass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        // Only enabled on HTTPS connections
        truststorePass.setEnabled(false);
        
        checkButton = new Button(parent, SWT.NONE);
        checkButton.setText("Check Connection");
        gd = new GridData(SWT.RIGHT, SWT.BEGINNING, true, false, 2, 1);
        gd.verticalIndent = 10;
        checkButton.setLayoutData(gd);

        checkButton.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == checkButton) {
                        checkAvailability();
                    }
                }
            }
        );

//        detailButton = new Button(group, SWT.NONE);
//        detailButton.setText("Details...");
//        detailButton.setLayoutData(layoutButton);
//
//        detailButton.addSelectionListener(
//            new SelectionAdapter() {
//                public void widgetSelected(final SelectionEvent e) {
//                    if (e.widget == detailButton) {
//                        displayDetails();
//                    }
//                }
//            }
//        );

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void buttonPressed(final int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
            MessageBox box = null;
            String name = getName();
            if (name == null || name.length() == 0) {
                box = new MessageBox(super.getShell(), SWT.CANCEL);
                box.setText("No Name specified");
                box.setMessage("You have to specify a name for the server configuration.");
                box.open();
                return;
            }
            URI address = getAddress();
            if (address == null) {
                box = new MessageBox(super.getShell(), SWT.CANCEL);
                box.setText("No Address specified");
                box.setMessage("You have to specify an address for the server configuration.");
                box.open();
                return;
            }
            ServerConfig updatedServerConfig = ServerConfigs.getInstance().createServerConfig(
                getName(), getAddress(), getTruststore(), getTruststorePass()
            );   
            if (ServerConfigs.getInstance().containsServerConfig(updatedServerConfig) 
                && warningOnDouble(ServerConfigs.getInstance().findServerConfig(updatedServerConfig))) {
                box = new MessageBox(super.getShell(), SWT.OK | SWT.CANCEL);
                box.setText("Server Configuration already exists");
                box.setMessage(
                    "The server configuration you configured already exists."
                    + " Press Ok to ignore or Cancel to edit."
                );
                int result = box.open();
                if (result == SWT.CANCEL) {
                    return;
                }
            }   
            // If the user entered protocol is not supported the resembled
            // server configuration must not be added to the server configuration 
            // list since its selection would lead to a major exception.
            if (!LayoutServiceClients.getInstance().isServerConfigSupported(updatedServerConfig)) {
                box = new MessageBox(super.getShell());
                box.setText("Unsupported protocol");
                box.setMessage(
                    "The server configuration you configured is not valid, its"
                    + " protocol is not supported. Press Ok to edit."
                );
                box.open();
                return;
            }
            if (ServerConfigs.getInstance().isValidServerConfig(
                    updatedServerConfig
                ) != ServerConfigError.ERROR_OK
                && warningOnInvalid(updatedServerConfig)
            ) {
                box = new MessageBox(super.getShell(), SWT.OK | SWT.CANCEL);
                box.setText("Invalid Server Configuration");
                box.setMessage(
                    "The server configuration you configured is not valid."
                    + " Press Ok to ignore or Cancel to edit."
                );
                int result = box.open();
                if (result == SWT.CANCEL) {
                    return;
                }
            }
            handleServerConfigUpdate(updatedServerConfig);
            okPressed();
        }
        cancelPressed();
    }
    
    /**
     * Returns whether a warning should be displayed if an equal server configuration already
     * exists in the server configuration list.
     * 
     * @param theserverConfig
     *            the server configuration which is equal to the server configuration resembled by the 
     *            currently entered server configuration data
     * @return whether a warning should be displayed if an equal server configuration already
     *         exists in the server configuration list
     */
    protected abstract boolean warningOnDouble(final ServerConfig theserverConfig);

    /**
     * Returns whether a warning should be displayed if the current configuration 
     * resembles an invalid server configuration.
     * 
     * @param theserverConfig
     *            the server configuration resembling the currently entered server configuration data 
     * @return whether a warning should be displayed if the current configuration 
     *         resembles an invalid server configuration
     */
    protected abstract boolean warningOnInvalid(final ServerConfig theserverConfig);
    
    /**
     * To be implemented by inherited classes to handle the server configuration data
     * edited in this dialog. This may be updating an existing server configuration or creating a new
     * server configuration.
     * 
     * @param updatedServerConfig
     *            the server configuration to handle
     */
    protected abstract void handleServerConfigUpdate(final ServerConfig updatedServerConfig);
    
    /**
     * Returns the name of the server configuration.
     *
     * @return the contents of the text element for the server configuration name 
     */
    protected final String getName() {
        String name = serverConfigName.getText();
        if (name != null) {
            name = name.trim();
        }
        return name;
    }

    /**
     * Returns an URI representing the service address used when doing layout with 
     * this server configuration.
     * 
     * @return an URI created from the contents of the text element for the server configuration address
     */
    protected final URI getAddress() {
        URI address = null;
        String tmp = serverConfigAddress.getText();
        if (tmp != null) {
            try {
                address = new URI(tmp);
            } catch (Exception e) {
            }
        }
        return address;
    }

    /**
     * Returns the path to the trust store file used when doing layout with this server configuration.
     * 
     * @return the contents of the text element for the trust store path
     */
    protected final String getTruststore() {
        String truststoreValue = truststore.getText();
        if (truststoreValue != null) {
            truststoreValue = truststoreValue.trim();
        }
        return truststoreValue;
    }

    /**
     * Returns the password for the trust store file used when doing layout with this 
     * server configuration.
     *
     * @return the contents of the text element for the trust store password
     */
    protected final String getTruststorePass() {
        return truststorePass.getText();
    }

    /**
     * Checks whether the layout service derived from the user entered parameters
     * is reachable.
     */
    protected final void checkAvailability() {
        ServerConfig serverConfig = ServerConfigs.getInstance().createServerConfig(
            getName(), getAddress(), getTruststore(), getTruststorePass()
        );
        Job job = new CheckAvailabilityJob(getShell(), serverConfig);
        job.setUser(true);
        job.schedule();
    }

    /**
     * Checks whether the layout service derived from the user entered parameters
     * is reachable.
     */
    protected final void displayDetails() {
        ServerConfig serverConfig = ServerConfigs.getInstance().createServerConfig(
            getName(), getAddress(), getTruststore(), getTruststorePass()
        );
        Job job = new ServerDetailsJob(getShell(), serverConfig);
        job.setUser(true);
        job.schedule();
    }

}
