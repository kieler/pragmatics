/*
final  * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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

package de.cau.cs.kieler.kwebs.client.kiml.ui;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kwebs.client.ServerConfig;
import de.cau.cs.kieler.kwebs.client.kiml.ServerConfigs;
import de.cau.cs.kieler.kwebs.client.kiml.activator.Activator;
import de.cau.cs.kieler.kwebs.client.kiml.preferences.Preferences;
import de.cau.cs.kieler.kwebs.client.kiml.ui.RemoteLayoutPreferencePage.ServerConfigViewerComparator.SortProperty;

/**
 * Preference page for general KIML preferences.
 *
 * @kieler.rating 2011-05-14 red
 * @author swe
 */
public class RemoteLayoutPreferencePage extends PreferencePage implements
    IWorkbenchPreferencePage {

    /** The id of this preference page. */
    public static final String PAGE_ID
        = "de.cau.cs.kieler.kwebs.client.kiml.ui.remotelayoutpreferencepage";
    
    /** Radio button for using local layout. */
    private Button serverConfigRadio1;
    
    /** Radio button for using remote layout. */
    private Button serverConfigRadio2;

    /** Check box button for using compression. */
    //private Button compressionCheckbox;

    /** Button for creating a new server configuration. */
    private Button scEditButton1;
    
    /** Button for editing an existing server configuration. */
    private Button scEditButton2;
    
    /** Button for deleting a server configuration. */
    private Button scEditButton3;
    
    /** Button for testing the availability of a server configuration. */
    private Button scEditButton4;

    /** Button for selecting a server configuration. */
    private Button scEditButton5;

    /** Button for displaying details on the layout service of a server configuration. */
    private Button scEditButton6;

    /** The table viewer used to display the user defined server configuration. */
    private TableViewer serverConfigViewer;
    
    /** The comparator used to sort the table when clicking on its column headers. */
    private ServerConfigViewerComparator serverConfigViewerComparator;
    
    /** The table used to display the user defined server configuration. */
    private Table serverConfigTable;

    /** The preference store used to store user selections. */
    private IPreferenceStore store;

    /**
     * Creates the preference page for the remote layout options.
     */
    public RemoteLayoutPreferencePage() {
        this(null, "Preferences for the Web Service based Layout", null);
    }

    /**
     * Creates the preference page for the remote layout options.
     * 
     * @param title
     *            the title
     * @param image
     *            the image
     */
    public RemoteLayoutPreferencePage(final String title, final ImageDescriptor image) {
        this(title, null, image);
    }

    /**
     * Creates the preference page for the remote layout options.
     * 
     * @param title
     *            the title
     * @param description
     *            the description
     * @param image
     *            the image
     */
    public RemoteLayoutPreferencePage(final String title, final String description,
        final ImageDescriptor image) {
        super();
        if (title != null && title.length() > 0) {
            setTitle(title);
        }
        if (description != null && description.length() > 0) {
            setDescription(description);
        }
        if (image != null) {
            setImageDescriptor(image);
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void init(final IWorkbench workbench) {
        store = Preferences.getPreferenceStore();
        setPreferenceStore(store);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final Control createContents(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        Group layoutGroup1 = createLayoutGroup1(composite);
        layoutGroup1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        //Group layoutGroup2 = createLayoutGroup2(composite);
        //layoutGroup2.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        Group layoutGroup3 = createLayoutGroup3(composite);
        layoutGroup3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        initRemoteLayoutOptionsView();
        composite.setLayout(new GridLayout(1, false));
        return composite;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performDefaults() {
        ServerConfigs.getInstance().read();        
        // Defaults fall back to standard server configuration
        ServerConfigs.getInstance().setActiveServerConfig(
            ServerConfigs.getInstance().getStandardServerConfig()
        );
        // Defaults fall back to local layout
        serverConfigRadio1.setSelection(true);
        serverConfigRadio2.setSelection(false);       
        // Defaults fall back to not use compression
        //compressionCheckbox.setSelection(false);
        // Update the UI
        refreshServerConfigViewer();
        updateRemoteLayoutOptionsView();
        super.performDefaults();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean performCancel() {
        ServerConfigs.getInstance().read();
        return super.performCancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean performOk() {
        // Check whether the server configuration list has been changed.
        // If it has been changed, store it.
        boolean isDirty = ServerConfigs.getInstance().isDirty();
        if (isDirty) {
            ServerConfigs.getInstance().store();
        }
        // Check whether the layout mode selection has been changed.
        // If it has been changed, store it.
        boolean remoteLayout = serverConfigRadio2.getSelection();
        isDirty |= (store.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE) != remoteLayout);
        store.setValue(Preferences.PREFID_LAYOUT_USE_REMOTE, remoteLayout);
        // Check whether the compression selection has been changed.
        // If it has been changed, store it.
        //boolean compressionLayout = compressionCheckbox.getSelection();
        //isDirty |= (store.getBoolean(Preferences.PREFID_LAYOUT_USE_COMPRESSION) != compressionLayout);
        //store.setValue(Preferences.PREFID_LAYOUT_USE_COMPRESSION, compressionLayout);
        // Fire property change event so that the RemoteGraphLayoutEngine can
        // initialize itself on the new conditions.
        if (isDirty) { 
            store.firePropertyChangeEvent(
                Preferences.PREFID_LAYOUT_SETTINGS_CHANGED, "1", "2"
            );
        }
        return true;
    }

    // **********

    /** margin width for layouts. */
    private static final int MARGIN_WIDTH
        = 10;

    /** fixed height of the options table. */
    private static final int OPTIONS_TABLE_HEIGHT
        = 300;

    /** fixed width of the options table. */
    private static final int OPTIONS_TABLE_WIDTH
        = 300;

    /**
     * Creates the group for selecting local or remote layout options.
     *
     * @param parent
     *           the parent control
     * @return a group with general options
     */
    private Group createLayoutGroup1(final Composite parent) {

        Group generalGroup = new Group(parent, SWT.NONE);
        generalGroup.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        generalGroup.setLayout(new GridLayout(1, true));
        generalGroup.setText("Layout type");

        // add radio buttons for selection of local or remote layout
        serverConfigRadio1 = new Button(generalGroup, SWT.RADIO | SWT.LEFT);
        serverConfigRadio1.setText("Use local Layout");

        serverConfigRadio1.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == serverConfigRadio1) {
                        updateRemoteLayoutOptionsView();
                    }
                }
            }
        );

        serverConfigRadio2 = new Button(generalGroup, SWT.RADIO | SWT.LEFT);
        serverConfigRadio2.setText("Use remote Layout");

        serverConfigRadio2.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == serverConfigRadio2) {
                        updateRemoteLayoutOptionsView();
                    }
                }
            }
        );

        return generalGroup;

    }

    /**
     * Creates the group for compression option.
     *
     * @param parent
     *           the parent control
     * @return a group with general options
     *//*
    private Group createLayoutGroup2(final Composite parent) {
        
        Group generalGroup = new Group(parent, SWT.NONE);
        
        generalGroup.setText("Do you want to use compression?");
    
        // add check box for compression option
        compressionCheckbox = new Button(generalGroup, SWT.CHECK);
        compressionCheckbox.setText("Use compression");
    
        compressionCheckbox.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == compressionCheckbox) {
                    }
                }
            }
        );
        
        FillLayout layout = new FillLayout();
        layout.marginWidth = MARGIN_WIDTH;
        generalGroup.setLayout(layout);
        
        return generalGroup;

    }*/

    /** Width of the server configuration fixed property column. */
    private static final int SERVERCONFIGACTIVE_WIDTH
        = 50;

    /** Width of the server configuration name property column. */
    private static final int SERVERCONFIGNAME_WIDTH
        = 100;

    /** Width of the server configuration address property column. */
    private static final int SERVERCONFIGADDRESS_WIDTH
        = 300;

    /**
     * Creates the group containing the server configuration table.
     *
     * @param parent 
     *            the parent control
     * @return a group with the server configuration table
     */
    private Group createLayoutGroup3(final Composite parent) {

        Group generalGroup = new Group(parent, SWT.NONE);
        
        generalGroup.setText("Available Layout Web Services");

        // Add table with list of available server configuration
        serverConfigViewer = new TableViewer(generalGroup,
            SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
        );
        serverConfigViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        TableViewerColumn col = null;

        col = createServerConfigViewerColumn("Active", SERVERCONFIGACTIVE_WIDTH, 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public Image getImage(final Object element) {
                Image image = null;
                if (((ServerConfig) element).isActive()) {
                    ImageDescriptor descriptor = Activator.getImageDescriptor("icons/active.gif");
                    if (descriptor != null) {
                        image = descriptor.createImage();
                    }
                }
                return image;
            }
            @Override
            public String getText(final Object element) {
                return null;
            }
        });

        col = createServerConfigViewerColumn("Name", SERVERCONFIGNAME_WIDTH, 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public Image getImage(final Object element) {
                Image image = null;
                ImageDescriptor descriptor = null;
                if (((ServerConfig) element).isFixed()) {
                    descriptor = Activator.getImageDescriptor("icons/fixed.gif");
                } else {
                    descriptor = Activator.getImageDescriptor("icons/editable.gif");
                }
                if (descriptor != null) {
                    image = descriptor.createImage();
                }
                return image;
            }
            @Override
            public String getText(final Object element) {
                return ((ServerConfig) element).getName();
            }
        });

        col = createServerConfigViewerColumn("Address", SERVERCONFIGADDRESS_WIDTH, 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                return ((ServerConfig) element).getAddress().toString();
            }
        });

        serverConfigTable = serverConfigViewer.getTable();

        serverConfigTable.setHeaderVisible(true);
        serverConfigTable.setLinesVisible(true);

        serverConfigViewer.setContentProvider(
            new IStructuredContentProvider() {
                public void inputChanged(final Viewer v, final Object oldInput, final Object newInput) {
                }
                public void dispose() {
                }
                public Object[] getElements(final Object parent) {
                    if (parent instanceof ServerConfigs) {
                        return ServerConfigs.getInstance().toObjectArray();
                    }
                    return new Object[0];
                }
            }
        );
        
        serverConfigViewerComparator = new ServerConfigViewerComparator();
        
        serverConfigViewer.setComparator(serverConfigViewerComparator);

        serverConfigViewer.addDoubleClickListener(
            new IDoubleClickListener() {
                public void doubleClick(final DoubleClickEvent event) {
                    ISelection selection = event.getSelection();
                    Object obj = ((IStructuredSelection) selection).getFirstElement();
                    if (obj instanceof ServerConfig) {
                        ServerConfig serverConfig = (ServerConfig) obj;
                        if (!serverConfig.isFixed()) {
                            new EditServerConfigDialog(parent.getShell(), serverConfig).open();
                        }
                    }
                }
            }
        );
        
        serverConfigViewer.addSelectionChangedListener(
            new ISelectionChangedListener() {
                public void selectionChanged(final SelectionChangedEvent event) {
                    updateRemoteLayoutOptionsView();
                }                
            }
        );

        serverConfigViewer.setInput(ServerConfigs.getInstance());

        GridData tableLayoutData = new GridData(
            SWT.FILL, SWT.FILL, true, true);

        tableLayoutData.heightHint = OPTIONS_TABLE_HEIGHT;
        tableLayoutData.widthHint = OPTIONS_TABLE_WIDTH;

        serverConfigTable.setLayoutData(tableLayoutData);
        serverConfigTable.pack();

        // add buttons for testing, editing, creating and removing server configuration
        Composite comp = new Composite(generalGroup, SWT.NONE);

        scEditButton1 = new Button(comp, SWT.PUSH | SWT.CENTER);
        scEditButton1.setText("New...");
        scEditButton1.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == scEditButton1) {
                        new NewServerConfigDialog(parent.getShell()).open();
                        refreshServerConfigViewer();
                    }
                }
            }
        );

        scEditButton2 = new Button(comp, SWT.PUSH | SWT.CENTER);
        scEditButton2.setText("Edit...");
        scEditButton2.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == scEditButton2) {
                        IStructuredSelection selection
                            = (IStructuredSelection) serverConfigViewer.getSelection();
                        if (!selection.isEmpty()) {
                            ServerConfig serverConfig = (ServerConfig) selection.getFirstElement();
                            if (!serverConfig.isFixed()) {
                                new EditServerConfigDialog(parent.getShell(), serverConfig).open();
                            }
                        }
                        refreshServerConfigViewer();
                    }
                }
            }
        );

        scEditButton3 = new Button(comp, SWT.PUSH | SWT.CENTER);
        scEditButton3.setText("Delete");
        scEditButton3.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == scEditButton3) {
                        IStructuredSelection selection
                            = (IStructuredSelection) serverConfigViewer.getSelection();
                        if (!selection.isEmpty()) {
                            ServerConfig serverConfig = (ServerConfig) selection.getFirstElement();
                            if (!serverConfig.isFixed() && !serverConfig.isActive()) {
                                ServerConfigs.getInstance().removeServerConfig(serverConfig);
                            }                                
                        }
                        refreshServerConfigViewer();
                    }
                }
            }
        );

        scEditButton4 = new Button(comp, SWT.PUSH | SWT.CENTER);
        scEditButton4.setText("Check...");
        scEditButton4.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == scEditButton4) {
                        IStructuredSelection selection
                            = (IStructuredSelection) serverConfigViewer.getSelection();
                        if (!selection.isEmpty()) {
                            ServerConfig serverConfig = (ServerConfig) selection.getFirstElement();
                            Job job = new CheckAvailabilityJob(getShell(), serverConfig);
                            job.setUser(true);
                            job.schedule();
                        }
                    }
                }
            }
        );

        scEditButton6 = new Button(comp, SWT.PUSH | SWT.CENTER);
        scEditButton6.setText("Details...");
        scEditButton6.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == scEditButton6) {
                        IStructuredSelection selection
                            = (IStructuredSelection) serverConfigViewer.getSelection();
                        if (!selection.isEmpty()) {
                            ServerConfig serverConfig = (ServerConfig) selection.getFirstElement();
                            Job job = new ServerDetailsJob(getShell(), serverConfig);
                            job.setUser(true);
                            job.schedule();
                        }
                    }
                }
            }
        );

        scEditButton5 = new Button(comp, SWT.PUSH | SWT.CENTER);
        scEditButton5.setText("Select");
        scEditButton5.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == scEditButton5) {
                        IStructuredSelection selection
                            = (IStructuredSelection) serverConfigViewer.getSelection();
                        if (!selection.isEmpty()) {
                            ServerConfig serverConfig = (ServerConfig) selection.getFirstElement();
                            ServerConfigs.getInstance().setActiveServerConfig(serverConfig);
                            refreshServerConfigViewer();
                            updateRemoteLayoutOptionsView();
                        }
                    }
                }
            }
        );

        comp.setLayout(new FillLayout(SWT.VERTICAL));
        comp.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

        generalGroup.setLayout(new GridLayout(2, false));

        return generalGroup;

    }

    /**
     *
     * @author swe
     *
     */
    static class ServerConfigViewerComparator extends ViewerComparator {

        /** Show entries in ascending order. */
        private static final int ASCENDING
            = 0;

        /** Show entries in descending order. */
        private static final int DESCENDING
            = 1;

        /** Enumeration for selecting the property to sort about. */
        public enum SortProperty {
            /** Sort server configurations by their active property. */
            ACTIVE,
            /** Sort server configurations by their name. */
            NAME,
            /** Sort server configurations by their address. */
            ADDRESS
        }

        /** Order by this priority. */
        private SortProperty sortProperty
            = SortProperty.NAME;

        /** The selected order. */
        private int direction
            = ASCENDING;

        /**
         * Sets the property by which the server configurations are compared.
         * 
         * @param theproperty
         *            the property by which the server configurations are compared
         */
        public final void setPropertyToCompare(final SortProperty theproperty) {
            if (sortProperty == theproperty) {
                this.direction = (this.direction == DESCENDING
                    ? ASCENDING
                        : DESCENDING
                );
            } else {
                sortProperty  = theproperty;
                direction = DESCENDING;
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final int compare(final Viewer theviewer, final Object e1, final Object e2) {
            ServerConfig serverConfig1 = (ServerConfig) e1;
            ServerConfig serverConfig2 = (ServerConfig) e2;
            int result = 0;
            switch (sortProperty) {
                case ACTIVE:
                    result = Boolean.valueOf(serverConfig1.isActive()).
                                 compareTo(Boolean.valueOf(serverConfig2.isActive()));
                    break;
                case NAME:
                    result = serverConfig1.getName().compareTo(serverConfig2.getName());
                    break;
                case ADDRESS:
                    result = serverConfig1.getAddress().compareTo(serverConfig2.getAddress());
                    break;
                default:
                    result = 0;
            }
            // Flip direction on descending order
            if (direction == DESCENDING) {
                result = -result;
            }
            return result;
        }

    }

    /**
     *
     * @param title
     * @param width
     * @return
     */
    private TableViewerColumn createServerConfigViewerColumn(final String title,
        final int width, final int index) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(serverConfigViewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(width);
        column.setResizable(true);
        column.setMoveable(true);
        column.addSelectionListener(createColumnSelectionAdapter(column, index));
        return viewerColumn;
    }

    /**
     * Returns a selection adapter.
     * 
     * @param column
     *            the table column
     * @param index
     *            the index
     * @return the selection adapter
     */
    private SelectionAdapter createColumnSelectionAdapter(final TableColumn column, final int index) {
        SelectionAdapter selectionAdapter = new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                serverConfigViewerComparator.setPropertyToCompare(SortProperty.values()[index]);
                int dir = serverConfigViewer.getTable().getSortDirection();
                if (serverConfigViewer.getTable().getSortColumn() == column) {
                    dir = (dir == SWT.UP ? SWT.DOWN : SWT.UP);
                } else {
                    dir = SWT.DOWN;
                }
                serverConfigViewer.getTable().setSortDirection(dir);
                serverConfigViewer.getTable().setSortColumn(column);
                serverConfigViewer.refresh();
            }
        };
        return selectionAdapter;
    }

    /**
     * Refreshes the viewer of the server configuration table. Used to reflect model
     * changes.
     */
    private void refreshServerConfigViewer() {
        try {
            Display display = Display.getDefault();
            if (!display.isDisposed()) {
                display.asyncExec(new Runnable() {
                    public void run() { serverConfigViewer.refresh(); } }
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the enabled status of the UI elements according to the currently selected
     * layout settings.
     */
    private void initRemoteLayoutOptionsView() {
        boolean remoteLayout = store.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        if (remoteLayout) {
            serverConfigRadio2.setSelection(true);
            ServerConfig activeConfig = ServerConfigs.getInstance().getActiveServerConfig();
            if (activeConfig != null) {
                serverConfigViewer.setSelection(new StructuredSelection(activeConfig), true);
            }
        } else {
            serverConfigRadio1.setSelection(true);
        }
        //boolean compressionLayout = store.getBoolean(Preferences.PREFID_LAYOUT_USE_COMPRESSION);
        //compressionCheckbox.setSelection(compressionLayout);
        updateRemoteLayoutOptionsView();
    }

    /**
     * Sets the enabled status of the UI elements according to the currently selected
     * layout settings.
     */
    private void updateRemoteLayoutOptionsView() {
        boolean remoteLayout = serverConfigRadio2.getSelection();
        ServerConfig serverConfig = null;
        ISelection selection = serverConfigViewer.getSelection();
        boolean empty = selection.isEmpty(); 
        if (!empty) {        
            Object obj = ((IStructuredSelection) selection).getFirstElement();        
            if (obj instanceof ServerConfig) {
                serverConfig = (ServerConfig) obj;
            }
        }
        boolean fixed = false;
        boolean active = false;
        if (serverConfig != null) {
            fixed = serverConfig.isFixed();
            active = serverConfig.isActive();
        }
        //compressionCheckbox.setEnabled(remoteLayout);
        serverConfigTable.setEnabled(remoteLayout);
        scEditButton1.setEnabled(remoteLayout);
        scEditButton2.setEnabled(remoteLayout && !empty && !fixed);
        scEditButton3.setEnabled(remoteLayout && !empty && !fixed && !active);
        scEditButton4.setEnabled(remoteLayout && !empty);
        scEditButton5.setEnabled(remoteLayout && !empty && !active);
        scEditButton6.setEnabled(remoteLayout && !empty);
    }

}
