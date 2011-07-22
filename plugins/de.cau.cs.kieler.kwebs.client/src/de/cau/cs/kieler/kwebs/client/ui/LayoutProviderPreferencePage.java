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

package de.cau.cs.kieler.kwebs.client.ui;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kwebs.client.preferences.Preferences;
import de.cau.cs.kieler.kwebs.client.providers.Providers;
import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.client.ui.testers.Availability;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.Severity;

/**
 * Preference page for general KIML preferences.
 *
 * @kieler.rating 2011-05-14 red
 * @author swe
 */
public class LayoutProviderPreferencePage extends PreferencePage implements
    IWorkbenchPreferencePage {

    /** Radio button for using local layout. */
    private Button providerRadio1;
    
    /** Radio button for using remote layout. */
    private Button providerRadio2;

    /** Button for creating a new provider. */
    private Button prEditButton1;
    
    /** Button for editing an existing provider. */
    private Button prEditButton2;
    
    /** Button for deleting a provider. */
    private Button prEditButton3;
    
    /** Button for testing the availability of a provider. */
    private Button prEditButton4;

    /** The table viewer used to display the user defined providers. */
    private TableViewer providerViewer;

    /** The table used to display the user defined providers. */
    private Table providerTable;

    /** The preference store used to store user selections. */
    private IPreferenceStore store;

    /**
     * Creates the preference page for the remote layout options.
     */
    public LayoutProviderPreferencePage() {
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
    public LayoutProviderPreferencePage(final String title, final ImageDescriptor image) {
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
    public LayoutProviderPreferencePage(final String title, final String description,
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

        Group layoutGroup3 = createLayoutGroup2(composite);
        layoutGroup3.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        initRemoteLayoutOptionsView();

        composite.setLayout(new GridLayout(1, false));

        return composite;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean performCancel() {
        Providers.read();
        return super.performCancel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean performOk() {
        // Check whether the provider list has been changed.
        // If it has been changed, store it.
        boolean isDirty = Providers.isDirty();
        if (isDirty) {
            Providers.store();
        }
        boolean remoteLayout = providerRadio2.getSelection();
        isDirty |= store.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE) != remoteLayout;
        store.setValue(Preferences.PREFID_LAYOUT_USE_REMOTE, remoteLayout);
        if (remoteLayout) {
            IStructuredSelection selection = (IStructuredSelection) providerViewer.getSelection();
            if (!selection.isEmpty()) {
                Provider provider = (Provider) selection.getFirstElement();
                int index = Providers.getIndexByProvider(provider);
                isDirty |= store.getInt(Preferences.PREFID_LAYOUT_PROVIDER_INDEX) != index;
                store.setValue(
                    Preferences.PREFID_LAYOUT_PROVIDER_INDEX,
                        index
                );
            }
        }
        // Fire property change event so that the RemoteGraphLayoutEngine can
        // initialize itself on the new conditions.
        if (isDirty) { 
            Logger.log(Severity.DEBUG, "Layout Provider Preferences Change notified");
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
     * Creates the group for layout provider options.
     *
     * @param parent
     *           the parent control
     * @return a group with general options
     */
    private Group createLayoutGroup1(final Composite parent) {

        Group generalGroup = new Group(parent, SWT.NONE);

        generalGroup.setText("Which Layout do you want to use?");

        // add radio buttons for selection of local or remote provider
        providerRadio1 = new Button(generalGroup, SWT.RADIO | SWT.LEFT);
        providerRadio1.setText("Use local Layout");

        providerRadio1.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == providerRadio1) {
                        updateRemoteLayoutOptionsView();
                    }
                }
            }
        );

        providerRadio2 = new Button(generalGroup, SWT.RADIO | SWT.LEFT);
        providerRadio2.setText("Use remote Layout");

        providerRadio2.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == providerRadio2) {
                        updateRemoteLayoutOptionsView();
                    }
                }
            }
        );

        FillLayout layout = new FillLayout();

        layout.marginWidth = MARGIN_WIDTH;

        generalGroup.setLayout(layout);

        return generalGroup;

    }

    /** Width of the provider name column. */
    private static final int PROVIDERNAME_WIDTH
        = 100;

    /** Width of the provider address column. */
    private static final int PROVIDERADDRESS_WIDTH
        = 300;

    /**
     * Creates the group containing the layout provider table.
     *
     * @param parent 
     *            the parent control
     * @return a group with the layout provider table
     */
    private Group createLayoutGroup2(final Composite parent) {

        Group generalGroup = new Group(parent, SWT.NONE);

        generalGroup.setText("Available Layout Web Services:");

        // add table with list of available providers
        providerViewer = new TableViewer(generalGroup,
            SWT.BORDER | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
        );

        TableViewerColumn col = null;

        col = createProviderViewerColumn("Name", PROVIDERNAME_WIDTH, 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                return ((Provider) element).getName();
            }
        });


        col = createProviderViewerColumn("Address", PROVIDERADDRESS_WIDTH, 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                return ((Provider) element).getAddress();
            }
        });

        providerTable = providerViewer.getTable();

        providerTable.setHeaderVisible(true);
        providerTable.setLinesVisible(true);

        providerViewer.setContentProvider(
            new IStructuredContentProvider() {
                public void inputChanged(final Viewer v,
                    final Object oldInput, final Object newInput) {
                }
                public void dispose() {
                }
                public Object[] getElements(final Object parent) {
                    if (parent instanceof Providers) {
                        return Providers.toObjectArray();
                    }
                    return new Object[0];
                }
            }
        );

        providerViewer.setInput(Providers.getInstance());

        Provider provider = Providers.getProviderByIndex(
            store.getInt(Preferences.PREFID_LAYOUT_PROVIDER_INDEX)
        );

        if (provider != null) {
            providerViewer.setSelection(new StructuredSelection(provider));
        }

        GridData tableLayoutData = new GridData(
            SWT.FILL, SWT.TOP, true, false, 1, 1
        );

        tableLayoutData.heightHint = OPTIONS_TABLE_HEIGHT;
        tableLayoutData.widthHint = OPTIONS_TABLE_WIDTH;

        providerTable.setLayoutData(tableLayoutData);
        providerTable.pack();

        // add buttons for testing, editing, creating and removing layout providers
        Composite comp = new Composite(generalGroup, SWT.NONE);

        prEditButton1 = new Button(comp, SWT.PUSH | SWT.CENTER);
        prEditButton1.setText("New");
        prEditButton1.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == prEditButton1) {
                        new NewProviderDialog(parent.getShell()).open();
                        refreshProviderViewer();
                    }
                }
            }
        );

        prEditButton2 = new Button(comp, SWT.PUSH | SWT.CENTER);
        prEditButton2.setText("Edit");
        prEditButton2.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == prEditButton2) {
                        IStructuredSelection selection
                            = (IStructuredSelection) providerViewer.getSelection();
                        if (!selection.isEmpty()) {
                            Provider provider = (Provider) selection.getFirstElement();
                            if (!provider.isFixed()) {
                                new EditProviderDialog(parent.getShell(), provider).open();
                            } else {
                                MessageBox box = new MessageBox(
                                    parent.getShell(), SWT.OK
                                );
                                box.setText("Edit Provider");
                                box.setMessage(
                                    " This provider is fixed and can not be edited."    
                                );
                                box.open();
                            }
                        }
                        refreshProviderViewer();
                    }
                }
            }
        );

        prEditButton3 = new Button(comp, SWT.PUSH | SWT.CENTER);
        prEditButton3.setText("Delete");
        prEditButton3.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == prEditButton3) {
                        IStructuredSelection selection
                            = (IStructuredSelection) providerViewer.getSelection();
                        if (!selection.isEmpty()) {
                            Provider provider = (Provider) selection.getFirstElement();
                            MessageBox box = null;
                            if (!provider.isFixed()) {
                                box = new MessageBox(
                                    parent.getShell(), SWT.OK | SWT.CANCEL
                                );
                                box.setText("Delete Provider");
                                box.setMessage(
                                    " Do you really want to delete the provider \n\n"
                                    + provider.getName()
                                    + "\n\n" + provider.getAddress()
                                    + "\n\n"
                                    + "from your list ?"
    
                                );
                                if (box.open() == SWT.OK) {
                                    Providers.removeProvider(provider);
                                }
                            } else {
                                box = new MessageBox(
                                    parent.getShell(), SWT.OK
                                );
                                box.setText("Delete Provider");
                                box.setMessage(
                                    " This provider is fixed and can not be deleted."    
                                );
                                box.open();
                            }
                        }
                        refreshProviderViewer();
                    }
                }
            }
        );

        prEditButton4 = new Button(comp, SWT.PUSH | SWT.CENTER);
        prEditButton4.setText("Check");
        prEditButton4.addSelectionListener(
            new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent e) {
                    if (e.widget == prEditButton4) {
                        IStructuredSelection selection
                            = (IStructuredSelection) providerViewer.getSelection();
                        if (!selection.isEmpty()) {
                            Provider provider = (Provider) selection.getFirstElement();
                            Availability.checkAvailability(getShell(), provider);
                        }
                    }
                }
            }
        );

        comp.setLayout(new FillLayout(SWT.VERTICAL));
        comp.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));

        generalGroup.setLayout(new GridLayout(2, false));

        return generalGroup;

    }

    /**
     *
     * @param title
     * @param width
     * @return
     */
    private TableViewerColumn createProviderViewerColumn(final String title,
        final int width, final int index) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(providerViewer, SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(width);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;
    }

    /**
     * Refreshes the viewer of the provider table. Used to reflect model
     * changes.
     */
    private void refreshProviderViewer() {
        try {
            Display display = Display.getDefault();
            if (!display.isDisposed()) {
                display.asyncExec(new Runnable() {
                    public void run() { providerViewer.refresh(); } }
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the selected status of the ui elements according to their
     * state from the preference store. 
     */
    private void initRemoteLayoutOptionsView() {
        boolean remoteLayout = store.getBoolean(Preferences.PREFID_LAYOUT_USE_REMOTE);
        (remoteLayout ? providerRadio2 : providerRadio1).setSelection(true);
        updateRemoteLayoutOptionsView();
    }

    /**
     * Sets the enabled status of the ui elements according to their
     * state from the preference store. 
     */
    private void updateRemoteLayoutOptionsView() {
        boolean remoteLayout = providerRadio2.getSelection();
        providerTable.setEnabled(remoteLayout);
        prEditButton1.setEnabled(remoteLayout);
        prEditButton2.setEnabled(remoteLayout);
        prEditButton3.setEnabled(remoteLayout);
        prEditButton4.setEnabled(remoteLayout);
    }

}
