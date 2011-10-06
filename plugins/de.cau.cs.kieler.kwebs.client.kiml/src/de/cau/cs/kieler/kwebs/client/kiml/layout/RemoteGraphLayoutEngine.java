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

package de.cau.cs.kieler.kwebs.client.kiml.layout;

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
import de.cau.cs.kieler.kiml.klayoutdata.KIdentifier;
import de.cau.cs.kieler.kwebs.LocalServiceException;
import de.cau.cs.kieler.kwebs.RemoteServiceException;
import de.cau.cs.kieler.kwebs.Statistics;
import de.cau.cs.kieler.kwebs.client.ILayoutServiceClient;
import de.cau.cs.kieler.kwebs.client.ServerConfig;
import de.cau.cs.kieler.kwebs.client.kiml.LayoutServiceClients;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.kiml.LayoutHistory;
import de.cau.cs.kieler.kwebs.client.kiml.activator.Activator;
import de.cau.cs.kieler.kwebs.client.kiml.preferences.Preferences;
import de.cau.cs.kieler.kwebs.formats.Formats;
import de.cau.cs.kieler.kwebs.transformation.IGraphTransformer;
import de.cau.cs.kieler.kwebs.transformation.KGraphXmiCompressedTransformer;
import de.cau.cs.kieler.kwebs.transformation.KGraphXmiTransformer;
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

    /** the preference store. */
    private IPreferenceStore preferenceStore;

    /** The cached instance of the ServerConfigs singleton. */
    private ServerConfigs serverConfigs 
        = ServerConfigs.getInstance();
    
    /** The transformer used for normal serialization and deserialization of the KGraph instances. */
    private KGraphXmiTransformer normalTransformer
        = new KGraphXmiTransformer();
    
    /** The transformer used for compressed serialization and deserialization of the KGraph instances. */
    private KGraphXmiTransformer compressedTransformer
        = new KGraphXmiCompressedTransformer(); // !!! EXPERIMENTAL !!!

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
                if( serverConfigs.countConfigs() == 0) {
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
                    Activator.PLUGIN_ID, 
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
        boolean remoteLayout = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        boolean compressedLayout = preferenceStore.getBoolean(Preferences.PREFID_LAYOUT_USE_COMPRESSION);
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
        double networkStart = 0;
        double networkTotal = 0;
        double timeStart = 0;
        double timeTotal = 0;
        KNode resultGraph = null;
        IGraphTransformer<KNode> transformer = null;
        String format = null;
        String sourceXMI = null;
        String resultXMI = null;
        timeStart = System.nanoTime();
        progressMonitor.begin(label, nodeCount);
        Graphs.annotateGraphWithUniqueID(layoutGraph);
        if (compressedLayout) {
            transformer = compressedTransformer;
            format = Formats.FORMAT_KGRAPH_XMI_COMPRESSED;
        } else {
            transformer = normalTransformer;
            format = Formats.FORMAT_KGRAPH_XMI;
        }
        sourceXMI = transformer.serialize(layoutGraph);
        //storeXmi(sourceXMI, false);
        try { 
            networkStart = System.nanoTime();
            resultXMI = client.graphLayout(sourceXMI, format, null);
            networkTotal = (System.nanoTime() - networkStart);
            //storeXmi(resultXMI, true);
            resultGraph = transformer.deserialize(resultXMI);
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
     * Returns always {@code} null since the layout is performed remotely.
     *
     * @return {@code null}
     */
    public final AbstractLayoutProvider getLastLayoutProvider() {
        return null;
    }

    // Utility methods and definitions
    
    /** Root directory for KGraph dumps. */
    private static final String ROOT
        = "/home/layout/kwebs/examples";
    
    /** 
     *  Number of the dumped KGraph. Used for not overwriting existing dump
     *  if a dump already exists of the same model. 
     */
    private int count = 0;
    
    /**
     * Dumps a KGraph to the file system.
     * 
     * @param xmi
     *            the KGraph's XMI representation
     * @param isResult
     *            whether the XMI represents the source graph or the layouted graph
     */
    @SuppressWarnings("unused")
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
        if (filename != null && filename.length() > 0) {
            //CHECKSTYLEOFF EmptyBlock
            try {
                if (!new File(ROOT).exists()) {
                    new File(ROOT).mkdirs();
                }
                filename = ROOT 
                           + "/" + filename.substring(0, 
                               (filename.indexOf(".") > -1 
                                    ? filename.indexOf(".") 
                                        : filename.length())
                           )
                           + (isResult ? "_result" : "")
                           + count++ + ".kgraph";
                File file = new File(filename);
                if (!file.exists()) {
                    FileOutputStream outstream = new FileOutputStream(file);
                    outstream.write(xmi.getBytes());
                    outstream.flush();
                    outstream.close();                    
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            //CHECKSTYLEON EmptyBlock
        }
    }

}
