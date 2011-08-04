/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.layout;

import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.client.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.activator.Activator;
import de.cau.cs.kieler.kwebs.client.preferences.Preferences;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfig;
import de.cau.cs.kieler.kwebs.client.providers.ServerConfigs;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.KGraphXmiTransformer;
import de.cau.cs.kieler.kwebs.util.Graphs;

/**
 * .
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public class RemoteGraphLayoutEngine implements IGraphLayoutEngine, IPropertyChangeListener {

    /** the service client. */
    private ILayoutServiceClient client;

    /** the preference store. */
    private IPreferenceStore preferenceStore;

    /** The transformer used for serialization and deserialization of the KGraph instances. */
    private KGraphXmiTransformer transformer
        = new KGraphXmiTransformer();

    /**
     * Creates a layout engine for remote layout.
     *
     */
    public RemoteGraphLayoutEngine() {
        preferenceStore = Preferences.getPreferenceStore();
        preferenceStore.addPropertyChangeListener(this);
        initialize();
    }

    /**
     * {@inheritDoc}
     */
    protected final void finalize() throws Throwable {
        preferenceStore.removePropertyChangeListener(this);
        super.finalize();
    }
    
    /**
     * Initializes the remote graph layout engine from the settings stored in the preference store.
     * 
     * @return whether the initialization was successful
     */
    private synchronized boolean initialize() {
        boolean remoteLayout = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        try {
            if (remoteLayout) {
                ServerConfig oldserverConfig = null;
                if (client != null) {
                    oldserverConfig = client.getServerConfig();
                }
                ServerConfig newserverConfig = ServerConfigs.getInstance().getActiveServerConfig();
                if (newserverConfig == null) {
                    throw new IllegalStateException("ServerConfig object not found");
                }
                if (!newserverConfig.equals(oldserverConfig)) {
                    if (client != null) {
                        client.disconnect();
                    }
                    client = LayoutServiceClients.getInstance().
                        getClientForServerConfig(newserverConfig);
                    if (client == null) {
                        throw new IllegalStateException("Client object could not be generated");
                    }
                    RemoteLayoutDataService.resetInstance();
                    LayoutDataService.setMode(LayoutDataService.REMOTEDATASERVICE);
                    RemoteLayoutDataService.getInstance().initializeWithClient(client);
                }
            } else {
                LayoutDataService.setMode(LayoutDataService.ECLIPSEDATASERVICE);
                client = null;                  
            }
            return true;
        } catch (Exception e) {
            if (remoteLayout) {
                LayoutDataService.setMode(LayoutDataService.ECLIPSEDATASERVICE);
                final Display display = PlatformUI.getWorkbench().getDisplay();
                final Maybe<Shell> maybe = new Maybe<Shell>();
                display.syncExec(
                    new Runnable() {
                        public void run() {
                            maybe.set(display.getActiveShell());
                        }                        
                    }
                );
                final Shell shell = maybe.get();
                if (shell == null) {
                    StatusManager.getManager().handle(
                        new Status(
                            IStatus.WARNING, 
                            Activator.PLUGIN_ID, 
                            "Shell object is null, can not display error dialog", 
                            null
                        )
                    );
                } else {
                    final String message = "The remote layout could not be initialized properly."
                                           + " The error occurred was\n\n"
                                           + "\"" + e.getMessage() + "\".\n\n"
                                           + "The layout was temporarily set back to local.";
                    display.syncExec(
                        new Runnable() {
                            public void run() {
                                MessageBox box = new MessageBox(shell, SWT.OK);
                                box.setText("Error Occurred");
                                box.setMessage(message);
                                box.open();                     
                            }
                        }
                    );
                }
                client = null;                    
            } else {
                throw new LocalServiceException("Local layout failed", e);
            }
        }
        return false;
    }

    /**
     * Performs remote layout on the given layout graph.
     *
     * @param layoutGraph instance of a layout graph
     * @param progressMonitor monitor to which progress of the layout algorithms is reported
     */
    public final void layout(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor) {
        boolean remoteLayout = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        if (remoteLayout && client == null) {
            if (!initialize()) {
                return;
            }
        }
        int nodeCount = Graphs.countNodes(layoutGraph);
        String label = "Doing remote layout " + client.getServerConfig().getAddress();
        String graphLabel = layoutGraph.getLabel().getText();
        if (graphLabel != null && graphLabel.length() > 0) {
            label += " (" + graphLabel + ")";
        }
        progressMonitor.begin(label, nodeCount);
        Graphs.annotateGraphWithUniqueID(layoutGraph);
        String sourceXMI = transformer.serialize(layoutGraph);
        String resultXMI = null;
        //storeXmi(sourceXMI, false);
        try {
            resultXMI = client.graphLayout(sourceXMI, Formats.FORMAT_KGRAPH_XMI, null);
            //storeXmi(resultXMI, true);
            KNode tempGraph = transformer.deserialize(resultXMI);
            Graphs.duplicateGraphLayoutByUniqueID(tempGraph, layoutGraph);
        } catch (Exception e) {
            throw new RemoteServiceException("Error occurred while doing remote layout", e);
        }
        progressMonitor.done();
    }

    /**
     * Listen to preference changes and update service client on
     * change of service address.
     *
     * @param event
     *            the property change event
     */
    public final synchronized void propertyChange(final PropertyChangeEvent event) {
        if (event.getProperty().equals(Preferences.PREFID_LAYOUT_SETTINGS_CHANGED)) {
            initialize();            
        }
    }

    /**
     * Returns always {@code} null since the layout is performed remotely.
     *
     * @return {@code null}
     */
    public final AbstractLayoutProvider getLastLayoutProvider() {
        return null;
    }
/*
    private static final String ROOT
        = "C:/examples";
    
    private void storeXmi(final String xmi, final boolean isResult) {
        final Display display = PlatformUI.getWorkbench().getDisplay();
        final Maybe<String> title = new Maybe<String>();
        display.syncExec(
            new Runnable() {
                public void run() {
                    IWorkbenchWindow activeWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                    if (activeWindow == null) {
                        return;
                    }
                    IWorkbenchPage activePage = activeWindow.getActivePage();
                    if (activePage == null) {
                        return;
                    }
                    IWorkbenchPart activePart = activePage.getActivePart();
                    if (activePart == null) {
                        return;
                    }
                    title.set(activePart.getTitle());            
                }                        
            }
        );        
        String filename = title.get();
        if (filename != null) {
            //CHECKSTYLEOFF EmptyBlock
            try {
                if (!new File(ROOT).exists()) {
                    new File(ROOT).mkdirs();
                }
                filename = ROOT 
                           + "/" + filename.substring(0, filename.indexOf("."))
                           + (isResult ? "_result" : "")
                           + ".xmi";
                File file = new File(filename);
                if (!file.exists()) {
                    FileOutputStream outstream = new FileOutputStream(file);
                    outstream.write(xmi.getBytes());
                    outstream.flush();
                    outstream.close();                    
                }
            } catch (Exception e) {
            }
            //CHECKSTYLEON EmptyBlock
        }
    }
*/
}
