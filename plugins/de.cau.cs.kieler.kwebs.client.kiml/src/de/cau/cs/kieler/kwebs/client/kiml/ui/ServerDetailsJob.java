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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfig;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.ServiceData;
import de.cau.cs.kieler.kwebs.servicedata.SupportedFormat;
import de.cau.cs.kieler.kwebs.servicedata.transformation.ServiceDataXmiTransformer;

/**
 * This job tests layout service availability and displays the layout servers capabilities
 * afterwards or an error message if the service is not available.
 *
 * @kieler.rating 2011-05-14
 * 
 * @author swe
 */
public class ServerDetailsJob extends AbstractServerBasedJob {

    /**
     * Creates a Job for testing server availability.
     * 
     * @param theparentShell
     *            the parent shell
     * @param theserverConfig
     *            the server configuration to be tested
     */
    public ServerDetailsJob(final Shell theparentShell, final ServerConfig theserverConfig) {
        super("Service Details", theparentShell, theserverConfig);
    }
    
    /**
     * Runs the job and checks for service availability. Display a message box with the result
     * of the test.
     * 
     * @param monitor
     *             the progress monitor used
     * @return the status
     */
    protected IStatus run(final IProgressMonitor monitor) {
        super.checkAvailability();
        return Status.OK_STATUS;
    }

    private static final String HTML_PREFIX
        = "<html>\n"
          + "<head>\n"
          + "<style type='text/css'>\n"
          + "<!--\n"
          + "body { font-family : Verdana, Arial; font-size : 8pt; }\n"
          + "p { font-family : Verdana, Arial; font-size : 8pt; margin : 10pt; }\n"
          + "table { border-width : 1px; border-style : solid; border-color : #000000; }\n"
          + "td { font-family : Verdana, Arial; font-size : 8pt; border-top: 1px solid; }\n"
          + "th { font-family : Verdana, Arial; font-size : 8pt; font-weight : bold; }\n"
          + "tr:first-child td { border: none; }\n"
          + ".title { font-size : 10pt; font-weight : bold; }\n"
          + "//-->\n"
          + "</style>\n"
          + "</head>\n"
          + "<body>\n";
    
    private static final String HTML_POSTFIX
        = "</body>\n"
          + "</html>";
    
    /**
     * {@inheritDoc}
     */
    protected void available(final ILayoutServiceClient client, final String message) {
        String serviceDataXMI = null;
        ServiceData serviceData = null;
        try {
            serviceDataXMI = client.getServiceData();
            serviceData = new ServiceDataXmiTransformer().deserialize(serviceDataXMI);
        } catch (Exception e) { e.printStackTrace();
            super.processError(e);
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(HTML_PREFIX);
        
        sb.append("<p class='title'>Details on service:</p>\n");
        
        sb.append("<p>\n");
        sb.append("Name: " + client.getServerConfig().getName() + "<br/>\n");
        sb.append("Address: " + client.getServerConfig().getAddress() + "<br/>\n");
        sb.append("Version: " + serviceData.getVersion() + "<br/>\n");
        sb.append("</p>\n");
        
        sb.append("<p class='title'>Supported Algorithms:</p>\n");

        sb.append("<p>\n");        
        sb.append("<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Category</th><th>Type</th><th>Version</th></th></thead>\n");
        sb.append("<tbody>\n");        
        for (LayoutAlgorithm algorithm : serviceData.getLayoutAlgorithms()) {
            String category = (algorithm.getCategory() != null ? algorithm.getCategory().getName() : null);
            String type = (algorithm.getType() != null ? algorithm.getType().getName() : null);
            String version = algorithm.getVersion();
            if (category == null || category.length() == 0) {
                category = "&nbsp;";
            }
            if (type == null || type.length() == 0) {
                type = "&nbsp;";
            }
            if (version == null || version.length() == 0) {
                version = "&nbsp;";
            }
            sb.append(
                "<tr>"
                + "<td>" + algorithm.getName() + "</td>" 
                + "<td>" + category + "</td>"
                + "<td>" + type + "</td>"
                + "<td>" + version + "</td>"
                + "</tr>\n"
            );
        }        
        sb.append("</tbody>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");

        sb.append("<p class='title'>Supported Formats:</p>\n");

        sb.append("<p>\n");        
        sb.append("<table cellspacing='0' cellpadding='5'>\n");        
        sb.append("<thead><tr><th>Name</th><th>Identifier</th><th>Description</th></tr></thead>\n");
        sb.append("<tbody>\n");

        for (SupportedFormat format : serviceData.getSupportedFormats()) {
            sb.append(
                "<tr>"
                + "<td>" + format.getName()  + "</td>" 
                + "<td>" + format.getId()  + "</td>"
                + "<td>" + format.getDescription() + "</td>"
                + "</tr>\n"
            );
        }

        sb.append("</tbody>\n");
        sb.append("</table>\n");
        sb.append("</p>\n");
        
        sb.append(HTML_POSTFIX);
        
        final String html = sb.toString();
        Display.getDefault().syncExec(
            new Runnable() { public void run() { new BrowserDialog(getShell(), html).open(); }; }
        );
        
    }

    /**
     * {@inheritDoc}
     */
    protected void unavailable(final ILayoutServiceClient client, final String message) {
        processMessage("Layout Server is not available", message);
    }
    
    /**
     * Private dialog class used for showing the server details via HTML browser widget. 
     * 
     * @author swe
     *
     */
    private class BrowserDialog extends Dialog {

        /** The browser widget. */
        private Browser browser;
        
        /** The HTML to display in the browser widget. */
        private String html;
        
        /**
         * Creates the browser dialog instance.
         * 
         * @param parentShell
         *            the parent shell for this dialog instance
         * @param thehtml
         *            the HTML to display in the browser widget
         */
        protected BrowserDialog(final Shell parentShell, final String thehtml) {
            super(parentShell);
            parentShell.setText("Edit Server Configuration");
            html = thehtml;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Control createDialogArea(final Composite parent) {
            Composite composite = (Composite) super.createDialogArea(parent);
            try {
                browser = new Browser(composite, SWT.BORDER);
                browser.setSize(400, 600);
                browser.setText(html);
            } catch (Exception e) { e.printStackTrace();
                processError(e);
            }
            composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
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
    
}
