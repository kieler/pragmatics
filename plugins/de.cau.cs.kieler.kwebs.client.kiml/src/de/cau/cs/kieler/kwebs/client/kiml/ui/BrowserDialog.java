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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.kwebs.client.kiml.activator.Activator;

/**
 * This class provides a dialog which can show arbitrary HTML content in a browser widget.
 *
 * @kieler.rating 2011-05-14
 * 
 * @author swe
 */
public class BrowserDialog extends Dialog {

    /** The browser widget. */
    private Browser browser;
    
    /** The HTML to display in the browser widget. */
    private String html;

    /** The title of the dialog. */
    private String title;
    
    /** The size of the browser widget. */
    private Rectangle size
        = new Rectangle(0, 0, 500, 400);
    
    /**
     * Creates the browser dialog instance.
     * 
     * @param parentShell
     *            the parent shell for this dialog instance
     * @param thehtml
     *            the HTML to display in the browser widget
     * @param thetitle
     *            the title of the dialog
     */
    public BrowserDialog(final Shell parentShell, final String thehtml, final String thetitle,
        final Rectangle thesize) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.RESIZE);
        html = thehtml;
        title = thetitle;
        size = thesize;        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(title);
     }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite composite = (Composite) super.createDialogArea(parent);
        
        try {
            browser = new Browser(composite, SWT.BORDER);                
            browser.setText(html);
            
            GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
            gd.minimumWidth = size.width;
            gd.minimumHeight = size.height;
            browser.setLayoutData(gd);
        } catch (Exception e) {
            StatusManager.getManager().handle(
                new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not create browser widget", e)
            );
        }
        
        return composite;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        super.createButton(parent, OK, "OK", true);
    }
    
}
