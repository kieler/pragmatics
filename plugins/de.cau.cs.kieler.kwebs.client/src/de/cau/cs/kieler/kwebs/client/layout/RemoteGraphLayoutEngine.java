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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.IGraphLayoutEngine;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kiml.service.KGraphHandler;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.Statistics;
import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfigData;
import de.cau.cs.kieler.kwebs.client.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.LayoutHistory;
import de.cau.cs.kieler.kwebs.client.KwebsClientPlugin;
import de.cau.cs.kieler.kwebs.client.Preferences;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.util.Graphs;

/**
 * This class is used to calculate the layout of a graph using a remote layout service.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public class RemoteGraphLayoutEngine implements IGraphLayoutEngine, IPropertyChangeListener {

    /** the service client. */
    private ILayoutServiceClient client;

    /** The cached instance of the ServerConfigs singleton. */
    private ServerConfigs serverConfigs 
        = ServerConfigs.getInstance();
    
    /** The transformer used for normal serialization and deserialization of the KGraph instances. */
    private KGraphHandler kgraphHandler = new KGraphHandler();
    
    /**
     * Creates a layout engine for remote layout.
     *
     */
    public RemoteGraphLayoutEngine() {
        Preferences.getPreferenceStore().addPropertyChangeListener(this);
        initialize();
    }

    /**
     * {@inheritDoc}
     */
    protected final void finalize() throws Throwable {
        Preferences.getPreferenceStore().removePropertyChangeListener(this);
        super.finalize();
    }
    
    /**
     * Initializes the remote graph layout engine from the settings stored in the preference store.
     * 
     * @return whether the initialization was successful
     */
    private synchronized boolean initialize() {
        boolean remoteLayout = Preferences.getPreferenceStore()
                .getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        try {
            if (remoteLayout) {
                if (serverConfigs.countConfigs() == 0) {
                    LayoutDataService.setMode(LayoutDataService.ECLIPSEDATASERVICE);
                    int result = displayMessage(
                        "No Services configured",
                        "You do not have any layout service configured."
                        + " Doing layout remotely is not possible at the moment."
                        + " You can configure layout services by double clicking on the tray icon."
                        + " The layout was temporarily set back to local."
                        + " Do you want to switch to local mode permanently ?",
                        SWT.YES | SWT.NO
                    );               
                    if (result == SWT.YES) {
                        SwitchLayoutMode.toLocal();
                    }
                    return true; 
                }
                ServerConfigData oldserverConfig = null;
                if (client != null) {
                    oldserverConfig = client.getServerConfig();
                }
                ServerConfigData newserverConfig = ServerConfigs.getInstance().getActiveServerConfig();
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
                client = null;
                int result = displayMessage(
                    "Error Occured", 
                    "The remote layout could not be initialized properly."
                    + " The error occurred was\n\n"
                    + "\"" + e.getMessage() + "\".\n\n"
                    + "Perhaps the configured layout server is not available at"
                    + " the moment or the configuration you selected is not"
                    + " accurate. The layout was temporarily set back to local."
                    + " Do you want to switch to local mode permanently ?",
                    SWT.YES | SWT.NO
                );               
                if (result == SWT.YES) {
                    SwitchLayoutMode.toLocal();
                }
            } else {
                throw new LocalServiceException("Local layout failed", e);
            }
        }
        return false;
    }

    /**
     * Displays a message dialog.
     * 
     * @param title
     *            the title of the dialog
     * @param message
     *            the message to be displayed
     * @param style
     *            the style, e.g. visible buttons
     * @return the constant defining which button the user clicked on
     */
    private int displayMessage(final String title, final String message, final int style) {
        final Maybe<Integer> result = new Maybe<Integer>(SWT.ERROR);
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
                    KwebsClientPlugin.PLUGIN_ID, 
                    "Shell object is null, can not display error dialog", 
                    null
                )
            );
        } else {
            display.syncExec(
                new Runnable() {
                    public void run() {
                        MessageBox box = new MessageBox(shell, style);
                        box.setText(title);
                        box.setMessage(message);
                        result.set(box.open());
                    }
                }
            );
        }
        return result.get();
    }
    
    /**
     * Performs remote layout on the given layout graph.
     *
     * @param layoutGraph instance of a layout graph
     * @param progressMonitor monitor to which progress of the layout algorithms is reported
     */
    public final void layout(final KNode layoutGraph, final IKielerProgressMonitor progressMonitor) {
        if (client == null) {
            if (!initialize()) {
                return;
            }
        } else {
            // make sure the remote layout data is active, since it's needed for deserialization
            LayoutDataService.setMode(LayoutDataService.REMOTEDATASERVICE);
        }
        String label = "Remote Graph Layout (" + client.getServerConfig().getAddress() + ")";
        double networkStart = 0;
        double networkTotal = 0;
        double timeStart = 0;
        double timeTotal = 0;
        KNode resultGraph = null;
        String sourceXMI = null;
        String resultXMI = null;
        timeStart = System.nanoTime();
        progressMonitor.begin(label, 1);
        Graphs.annotateGraphWithUniqueID(layoutGraph);
        sourceXMI = kgraphHandler.serialize(layoutGraph);
        try { 
            networkStart = System.nanoTime();
            resultXMI = client.graphLayout(sourceXMI, Formats.FORMAT_KGRAPH_XMI, null);
            networkTotal = (System.nanoTime() - networkStart);
            resultGraph = kgraphHandler.deserialize(resultXMI);
            Graphs.duplicateGraphLayoutByUniqueID(resultGraph, layoutGraph);
        } catch (Exception e) {
            throw new RemoteServiceException("Error occurred while doing remote layout", e);
        }        
        progressMonitor.done();
        timeTotal = (System.nanoTime() - timeStart);
        KIdentifier identifier = resultGraph.getData(KIdentifier.class);
        if (identifier != null) {
            Statistics statistics = new Statistics();
            statistics.fromString(identifier.getProperty(Statistics.STATISTICS));
            statistics.setTimeTotal(timeTotal);
            statistics.setTimeNetwork(networkTotal);
            statistics.setTimeLocalSupplemental(timeTotal - networkTotal);
            LayoutHistory.getInstance().addStatistic(statistics);
        }
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
     * {@inheritDoc}
     */
    public boolean isActive() {
        IPreferenceStore prefStore = Preferences.getPreferenceStore();
        return prefStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
    }

}
