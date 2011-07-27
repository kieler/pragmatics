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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kwebs.client.Clients;
import de.cau.cs.kieler.kwebs.client.IWebServiceClient;
import de.cau.cs.kieler.kwebs.client.preferences.Preferences;
import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;
import de.cau.cs.kieler.kwebs.service.LocalServiceException;
import de.cau.cs.kieler.kwebs.service.ServiceException;
import de.cau.cs.kieler.kwebs.transformation.KGraphXmiTransformer;
import de.cau.cs.kieler.kwebs.util.Graphs;

/**
 * .
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public class RemoteGraphLayoutEngine implements IGraphLayoutEngine, IPropertyChangeListener {

    /** the service client. */
    private IWebServiceClient client;

    /** the preference store. */
    private IPreferenceStore preferenceStore;

    /** The provider used. */
    private Provider provider;

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
                Provider newProvider = Providers.getProviderByIndex(
                    preferenceStore.getInt(Preferences.PREFID_LAYOUT_PROVIDER_INDEX)
                );
                if (newProvider == null) {
                    // if this exception occurs, check if the defaultProvider extension is set correctly
                    throw new IllegalStateException("Provider object could not be generated");
                }
                if (!newProvider.equals(provider)) {
                    provider = newProvider;
                    if (client != null) {
                        client.disconnect();
                    }
                    Logger.log(Severity.DEBUG, "Getting client for: " + provider.getAddress());
                    client = Clients.getClientForProvider(provider);                
                    if (client == null) {
                        throw new IllegalStateException("Client object could not be generated");
                    }
                    Logger.log(Severity.DEBUG, 
                        "Client implementation class is " + client.getClass().getCanonicalName()
                    );
                    Logger.log(
                        "Using remote layout (" + provider.getName() + ", " + provider.getAddress() + ")"
                    );                    
                    Logger.log(Severity.DEBUG, "Resetting remote layout data service");
                    RemoteLayoutDataService.resetInstance();
                    Logger.log(Severity.DEBUG, "Switching layout data service to remote mode");
                    LayoutDataService.setMode(LayoutDataService.REMOTEDATASERVICE);
                    Logger.log(Severity.DEBUG, "Retrieving service meta data");
                    RemoteLayoutDataService.getInstance().initializeWithClient(client);
                    Logger.log(Severity.DEBUG, "Meta data retrieved");                    
                    Logger.log(Severity.DEBUG, "Switched layout data service to remote mode");
                }
            } else {
                Logger.log("Using local layout");
                Logger.log(Severity.DEBUG, "Switching layout data service to local mode");
                LayoutDataService.setMode(LayoutDataService.ECLIPSEDATASERVICE);
                Logger.log(Severity.DEBUG, "Switched layout data service to local mode");
                provider = null;
                client = null;                  
            }
            return true;
        } catch (Exception e) {
            if (remoteLayout) {
                Logger.log(Severity.CRITICAL, "Initializing remote layout failed", e);
                Logger.log(Severity.WARNING, "Switching back to local layout");
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
                    Logger.log(Severity.WARNING, "Shell object is null, cant display error dialog");
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
                provider = null;
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
        String label = "Doing remote layout " + client.getProvider().getAddress();
        if (layoutGraph.getLabel().getText() != null) {
            label += " (" + layoutGraph.getLabel().getText() + ")";
        }
        Logger.log(Severity.DEBUG, label);
        progressMonitor.begin(label, nodeCount);
        Graphs.annotateGraphWithUniqueID(layoutGraph);
        String sourceXMI = transformer.serialize(layoutGraph);
        String resultXMI = null;
        try {
            Logger.log(Severity.DEBUG, "Sending graph", sourceXMI);
            resultXMI = client.graphLayout(sourceXMI, Formats.FORMAT_KGRAPH_XMI, null);
            Logger.log(Severity.DEBUG, "Received graph", resultXMI);
            KNode tempGraph = transformer.deserialize(resultXMI);
            Graphs.duplicateGraphLayoutByUniqueID(tempGraph, layoutGraph);
        } catch (Exception e) {
            Logger.log(Severity.CRITICAL, "Error occurred while doing remote layout", e);
            throw new ServiceException("Error occurred while doing remote layout", e);
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
        Logger.log(Severity.DEBUG, "Layout Provider Preferences Change received");
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

}
