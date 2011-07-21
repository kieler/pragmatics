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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.client.ui.testers.Availability;

/**
 * Abstract class for providing basic dialog functionality for {@link NewProviderDialog}
 * and {@link EditProviderDialog}.
 *
 * @kieler.rating 2011-05-14
 * @author swe
 */
public abstract class AbstractProviderDialog extends Dialog {

    /** Return value when user pressed OK. */
    public static final int ACTION_OK
        = 0;

    /** Return value when user pressed ABORT. */
    public static final int ACTION_ABORT
        = 1;

    //CHECKSTYLEOFF VisibilityModifier
    
    /** Name of the service provider. */
    protected Text providerName;

    /** Service address of service provider. */
    protected Text serviceAddress;

    /** Path to trust store for https connections. */
    protected Text truststore;

    /** Password for trust store. */
    protected Text truststorePass;

    /** Button for testing service availability. */
    protected Button checkButton;

    //CHECKSTYLEON VisibilityModifier
    
    /**
     * @param shell
     */
    protected AbstractProviderDialog(final Shell shell) {
        super(shell);
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
                    boolean enabled
                        = serviceAddress.getText().trim().
                              toLowerCase().startsWith("https");
                   truststore.setEnabled(enabled);
                   truststorePass.setEnabled(enabled);
                }
            }
        );

        label = new Label(group, SWT.WRAP);

        label.setText("Path to trust store:");
        label.setLayoutData(layoutLabel);

        truststore = new Text(group, SWT.SINGLE | SWT.BORDER);
        truststore.setLayoutData(layoutText);

        //only enabled on https connections
        truststore.setEnabled(false);

        final Button truststoreButton = new Button(group, SWT.NONE);
        
        truststoreButton.setText("Select");
        truststoreButton.setLayoutData(layoutButton);

        truststoreButton.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == truststoreButton) {
                        FileDialog dialog
                            = new FileDialog(getShell(), SWT.OPEN);
                        dialog.setText("Select trust store");
                        String result = dialog.open();
                        if (result != null) {
                            truststore.setText(result);
                        }
                    }
                }
            }
        );

        label = new Label(group, SWT.WRAP);

        label.setText("Password for trust store:");
        label.setLayoutData(layoutLabel);

        truststorePass = new Text(group, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
        truststorePass.setLayoutData(layoutText);

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
