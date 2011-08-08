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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfigs.ServerConfigError;

/**.
 *
 * @kieler.rating 2011-05-14
 * @author swe
 */
public class CheckAvailabilityDialog extends Dialog {

    /** The server configuration to be tested. */
    private ServerConfig serverConfig;
    
    /** The label displaying the status of the test. */
    private Label message;
    
    /** The button for closing this dialog. */
    private Button okButton;

    /** The button for viewing the error log. */
    private Button errorButton;
    
    /** The tester thread. */
    private Thread thread;
    
    /** The client used to test connectivity. */
    private ILayoutServiceClient client;
    
    /**
     * Creates a dialog for testing the availability of the layout service of a given server
     * configuration.
     * 
     * @param parentShell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to test
     */
    protected CheckAvailabilityDialog(final Shell parentShell, final ServerConfig theserverConfig) {
        super(parentShell);
        serverConfig = theserverConfig;
        thread = new Thread(
            new Runnable() {
                public void run() {
                    setMessageText("Trying to connect to layout service...");
                    ServerConfigError why = ServerConfigs.getInstance().
                        isValidServerConfig(serverConfig);
                    if (why == ServerConfigError.ERROR_OK) {
                        client = LayoutServiceClients.getInstance().
                            createClientForServerConfig(serverConfig);
                        try {
                            client.connect();
                            setMessageText("The layout service you selected is reachable.");
                        } catch (Exception e) {
                            setMessageText("The layout service you selected is not reachable.");
                            setButtonVisible(errorButton, true);
                            setButtonEnabled(errorButton, true);
                        } finally {
                            client.disconnect();
                        }
                    } else {
                        String text = "The selected server configuration is invalid."
                                      + " An availability test can not be performed."
                                      + " The reason why this server configuration is not valid is:\n\n";
                        if (why == ServerConfigError.ERROR_NOSERVERCONFIG) {
                            text += "The server configuration does not contain a name and an address.";
                        } else if (why == ServerConfigError.ERROR_INVALIDNAME) {
                            text += "The name of the server configuration is empty.";
                        } else if (why == ServerConfigError.ERROR_INVALIDADDRESS) {
                            text += "The address is empty or not correct.";
                        } else if (why == ServerConfigError.ERROR_NOPROTOCOL) {
                            text += "The address does not specify a protocol.";
                        } else if (why == ServerConfigError.ERROR_PROTOCOLNOTSUPPORTED) {
                            text += "The protocol is not supported."
                                    + "\n\nPlease make sure that the appropriate plug-in"
                                    + " for the protocol is available in your KIELER installation.";
                        } else if (why == ServerConfigError.ERROR_NOTRUSTSTORE) {
                            text += "You have specified a server configuration which is using"
                                    + " a HTTPS based connection but the trust store for"
                                    + " establishing the connection is missing.";
                        } else if (why == ServerConfigError.ERROR_NOTRUSTSTOREPASS) {
                            text += "You have specified a server configuration which is using"
                                    + " a HTTPS based connection but the password for the"
                                    + " trust store is missing.";
                        }
                        setMessageText(text);
                    }
                    setButtonEnabled(okButton, true);
                }            
            }
        );
    }
    
    /**
     * Sets the text of the message label.
     * 
     * @param text
     *            the text to be set
     */
    private void setMessageText(final String text) {
        Display display = Display.getDefault();
        display.syncExec(
            new Runnable() {
                public void run() {
                    message.setText(text);
                };                
            }
        );
    }

    /**
     * Sets the enabled state of the OK button.
     * 
     * @param button
     *            the button to be enabled or disabled
     * @param enabled
     *            whether the button shall be enabled
     */
    private void setButtonEnabled(final Button button, final boolean enabled) {
        Display display = Display.getDefault();
        display.syncExec(
            new Runnable() {
                public void run() {
                    button.setEnabled(enabled);
                };                
            }
        );
    }

    /**
     * Sets the visible state of the OK button.
     * 
     * @param button
     *            the button to be made visible or not visible
     * @param visible
     *            whether the button shall be visible
     */
    private void setButtonVisible(final Button button, final boolean visible) {
        Display display = Display.getDefault();
        display.syncExec(
            new Runnable() {
                public void run() {
                    button.setVisible(visible);
                };                
            }
        );
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected final void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText("Checking Availability");
    }

    /** Standard width of labels. */
    private static final int LABEL_WIDTHHINT
        = 400;

    /** Standard height of labels. */
    private static final int LABEL_HEIGHTHINT
        = 60;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {        
        Composite composite = (Composite) super.createDialogArea(parent);
        GridData layoutLabel = new GridData(SWT.LEFT, SWT.BOTTOM, true, false, 2, 1);        
        layoutLabel.widthHint = LABEL_WIDTHHINT;        
        layoutLabel.heightHint = LABEL_HEIGHTHINT;
        message = new Label(composite, SWT.WRAP);
        message.setLayoutData(layoutLabel);
        thread.start();
        return composite;        
    }

    /** Id for the view error log button. */
    private static final int ID_ERRRORVIEW 
        = 10000;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        errorButton = super.createButton(parent, ID_ERRRORVIEW, "View error", true);
        errorButton.setEnabled(false);
        errorButton.setVisible(false);
        errorButton.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.getSource() == errorButton) {
                        String[] messages = client.getLastError();
                        String  text = "\n\nThe error occurred was:\n\n"; 
                        if (messages != null) {                            
                            for (String line : messages) {
                                text += line + "\n";
                            }
                        } else {
                            text += "No error description available.";
                        }    
                        MessageBox box = new MessageBox(getShell(), SWT.OK);
                        box.setText("Error log");
                        box.setMessage(text);
                        box.open();
                    }
                };
            }
        );
        okButton = super.createButton(parent, OK, "OK", true);
        okButton.setEnabled(false);
    }
    
}
