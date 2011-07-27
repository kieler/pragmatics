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

package de.cau.cs.kieler.kwebs.client.ui;

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

import de.cau.cs.kieler.kwebs.client.Clients;
import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.client.ui.testers.Availability;

/**
 * Abstract class for providing basic dialog functionality for {@link NewProviderDialog}
 * and {@link EditProviderDialog}.
 *
 * @kieler.rating 2011-05-14
 * @author swe
 */
public abstract class AbstractProviderDialog extends Dialog {

    //CHECKSTYLEOFF VisibilityModifier
    
    /** Name of the service provider. */
    protected Text providerName;

    /** Service address of service provider. */
    protected Text serviceAddress;

    /** Path to trust store for https connections. */
    protected Text truststore;

    /** Password for trust store. */
    protected Text truststorePass;
    
    /** The button for the trust store selection dialog. */
    protected Button truststoreButton;

    /** Button for testing service availability. */
    protected Button checkButton;

    //CHECKSTYLEON VisibilityModifier
    
    /**
     * Crreates a dialog with elements for editing a provider.
     * 
     * @param parentShell
     *            the parent shell of this dialog
     */
    protected AbstractProviderDialog(final Shell parentShell) {
        super(parentShell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createProviderGroup(composite);
        return composite;
    }

    // **********

    /** Standard width of labels. */
    private static final int LABEL_WIDTHHINT
        = 400;

    /** Standard width of text fields. */
    private static final int TEXT_WIDTHHINT
        = 400;

    /** Standard width of buttons. */
    private static final int BUTTON_WIDTHHINT
        = 80;

    /** Prefix of a https uri. */
    private static final String HTTPS_PREFIX
        = "https";
    
    /**
     *
     * @param parent
     */
    private void createProviderGroup(final Composite parent) {

        Group group = new Group(parent, SWT.NONE);

        group.setText("Data of the layout provider");
        group.setLayout(new GridLayout(2, false));

        Label label;

        GridData layoutLabel = new GridData(
            SWT.LEFT, SWT.BOTTOM, true, false, 2, 1
        );
        GridData layoutText = new GridData(SWT.FILL, SWT.TOP,  true, false);
        GridData layoutButton = new GridData(SWT.FILL, SWT.TOP,  true, false);

        layoutLabel.widthHint = LABEL_WIDTHHINT;
        layoutText.widthHint = TEXT_WIDTHHINT;
        layoutButton.widthHint = BUTTON_WIDTHHINT;

        label = new Label(group, SWT.WRAP);

        label.setText("Provider name:");
        label.setLayoutData(layoutLabel);

        providerName = new Text(group, SWT.SINGLE | SWT.BORDER);
        providerName.setLayoutData(layoutText);

        label = new Label(group, SWT.WRAP);

        label.setText("Service address:");
        label.setLayoutData(layoutLabel);

        serviceAddress = new Text(group, SWT.SINGLE | SWT.BORDER);
        serviceAddress.setLayoutData(layoutText);
        serviceAddress.addModifyListener(
            new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    String address = getAddress();                    
                    boolean enabled = (
                        address != null && address.trim().toLowerCase().startsWith(HTTPS_PREFIX)
                    );                    
                    truststore.setEnabled(enabled);
                    truststorePass.setEnabled(enabled);
                    truststoreButton.setEnabled(enabled);
                }
            }
        );

        label = new Label(group, SWT.WRAP);

        label.setText("Path to trust store:");
        label.setLayoutData(layoutLabel);

        truststore = new Text(group, SWT.SINGLE | SWT.BORDER);
        truststore.setLayoutData(layoutText);

        // Only enabled on https connections
        truststore.setEnabled(false);

        truststoreButton = new Button(group, SWT.NONE);        
        truststoreButton.setText("Select");
        truststoreButton.setLayoutData(layoutButton);
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

        // Only enabled on https connections
        truststoreButton.setEnabled(false);

        label = new Label(group, SWT.WRAP);

        label.setText("Password for trust store:");
        label.setLayoutData(layoutLabel);

        truststorePass = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
        truststorePass.setLayoutData(layoutText);

        // Only enabled on https connections
        truststorePass.setEnabled(false);

        checkButton = new Button(group, SWT.NONE);
        checkButton.setText("Check");
        checkButton.setLayoutData(layoutButton);

        checkButton.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == checkButton) {
                        checkAvailability();
                    }
                }
            }
        );

        group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

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
                box.setText("No Provider Name");
                box.setMessage("You have to specify a name for the provider.");
                box.open();
                return;
            }
            String address = getAddress();
            if (address == null || address.length() == 0) {
                box = new MessageBox(super.getShell(), SWT.CANCEL);
                box.setText("No Provider Address");
                box.setMessage("You have to specify an address for the provider.");
                box.open();
                return;
            }
            Provider updatedProvider = Providers.createProvider(
                getName(), getAddress(), getTruststore(), getTruststorePass()
            );   
            if (Providers.containsProvider(updatedProvider) 
                && warningOnDouble(Providers.findProvider(updatedProvider))) {
                box = new MessageBox(super.getShell(), SWT.OK | SWT.CANCEL);
                box.setText("Provider already exists");
                box.setMessage(
                    "The provider you configured already exists."
                    + " Press Ok to ignore or Cancel to edit."
                );
                int result = box.open();
                if (result == SWT.CANCEL) {
                    return;
                }
            }   
            // If the user entered protocol is not supported the resembled
            // provider must not be added to the provider list since its selection
            // would lead to a major exception.
            if (!Clients.isProviderSupported(updatedProvider)) {
                box = new MessageBox(super.getShell());
                box.setText("Unsupported protocol");
                box.setMessage(
                    "The provider you configured is not valid, its"
                    + " protocol is not supported. Press Ok to edit."
                );
                box.open();
                return;
            }
            if (!Providers.isValidProvider(updatedProvider)
                && warningOnInvalid(updatedProvider)) {
                box = new MessageBox(super.getShell(), SWT.OK | SWT.CANCEL);
                box.setText("Invalid Provider");
                box.setMessage(
                    "The provider you configured is not valid."
                    + " Press Ok to ignore or Cancel to edit."
                );
                int result = box.open();
                if (result == SWT.CANCEL) {
                    return;
                }
            }
            handleProviderUpdate(updatedProvider);
            okPressed();
        }
        cancelPressed();
    }
    
    /**
     * Returns whether a warning should be displayed if an equal provider already
     * exists in the provider list.
     * 
     * @param theprovider
     *            the provider which is equal to the provider resembled by the 
     *            currently entered provider data
     * @return whether a warning should be displayed if an equal provider already
     *         exists in the provider list
     */
    protected abstract boolean warningOnDouble(final Provider theprovider);

    /**
     * Returns whether a warning should be displayed if the current configuration 
     * resembles an invalid provider.
     * 
     * @param theprovider
     *            the provider resembling the currently entered provider data 
     * @return whether a warning should be displayed if the current configuration 
     *         resembles an invalid provider
     */
    protected abstract boolean warningOnInvalid(final Provider theprovider);
    
    /**
     * To be implemented by inherited classes to handle the provider data
     * edited in this dialog. This may be updating an existing provider or creating a new
     * provider.
     * 
     * @param updatedProvider
     *            the provider to handle
     */
    protected abstract void handleProviderUpdate(final Provider updatedProvider);
    
    /**
     * Returns the name of the provider.
     *
     * @return the contents of the text element for the provider name 
     */
    protected final String getName() {
        String name = providerName.getText();
        if (name != null) {
            name = name.trim();
        }
        return name;
    }

    /**
     * Returns the service address used when doing layout with this provider.
     * 
     * @return the contents of the text element for the provider address
     */
    protected final String getAddress() {
        String address = serviceAddress.getText();
        if (address != null) {
            address = address.trim();
        }
        return address;
    }

    /**
     * Returns the path to the trust store file used when doing layout with this provider.
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
     * Returns the password for the trust store file used when doing layout with this provider.
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
        Availability.checkAvailability(
            getShell(),
            Providers.createProvider(
                getName(), getAddress(), getTruststore(), getTruststorePass()
            )
        );
    }

}
