/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivi.preferences;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.text.Collator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.cau.cs.kieler.kivi.KiViPlugin;
import de.cau.cs.kieler.kivi.core.Descriptor;
import de.cau.cs.kieler.kivi.core.Viewmanagement;

/**
 * Preference page for the view management.
 * 
 * @author mmu
 * 
 */
public class ViewmanagementPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage {

    private Text descriptionText;

    private CheckboxTableViewer checkboxViewer;

    private Button kiviActive;

    private List<Descriptor> combinations = Viewmanagement.getInstance().getAvailableCombinations();

    /**
     * Default constructor.
     */
    public ViewmanagementPreferencePage() {
        super("View management preference page");
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public boolean performOk() {
        if (super.performOk()) {
            getPreferenceStore()
                    .setValue(Viewmanagement.PROPERTY_ACTIVE, kiviActive.getSelection());
            Viewmanagement.getInstance().setActive(kiviActive.getSelection());
            for (Descriptor d : combinations) {
                boolean checked = checkboxViewer.getChecked(d);
                getPreferenceStore().setValue(d.getClazz().getCanonicalName() + ".active", checked);
                d.setActive(checked);
            }
            Viewmanagement.getInstance().updateCombinationsByGUI();
            return true;
        }
        return false;
    }

    @Override
    protected Control createContents(final Composite parent) {

        Font font = parent.getFont();

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.verticalSpacing = 10;
        mainComposite.setLayout(layout);

        kiviActive = new Button(mainComposite, SWT.CHECK);
        final Composite combinationsComposite = new Composite(mainComposite, SWT.NONE);

        kiviActive.setText("Enable view management");
        kiviActive.setSelection(Viewmanagement.getInstance().isActive());
        kiviActive.addSelectionListener(new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                combinationsComposite.setEnabled(((Button) e.widget).getSelection());
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        combinationsComposite.setEnabled(Viewmanagement.getInstance().isActive());

        Label topLabel = new Label(combinationsComposite, SWT.NONE);
        topLabel.setText("Combinations");
        topLabel.setFont(font);

        combinationsComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout combinationsLayout = new GridLayout();
        combinationsLayout.marginWidth = 0;
        combinationsLayout.marginHeight = 0;
        combinationsComposite.setLayout(combinationsLayout);
        combinationsComposite.setFont(font);

        checkboxViewer = CheckboxTableViewer.newCheckList(combinationsComposite, SWT.SINGLE
                | SWT.TOP | SWT.BORDER);
        checkboxViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
        checkboxViewer.getTable().setFont(combinationsComposite.getFont());
        checkboxViewer.setLabelProvider(new LabelProvider() {
            public String getText(final Object element) {
                return ((Descriptor) element).getName();
            }
        });
        checkboxViewer.getTable().setFont(font);

        checkboxViewer.setContentProvider(new IStructuredContentProvider() {

            private final Comparator<Descriptor> comparer = new Comparator<Descriptor>() {
                private Collator collator = Collator.getInstance();

                public int compare(final Descriptor arg0, final Descriptor arg1) {
                    String s1 = arg0.getName();
                    String s2 = arg1.getName();
                    return collator.compare(s1, s2);
                }
            };

            public void dispose() {
                // Nothing to do on dispose
            }

            public void inputChanged(final Viewer viewer, final Object oldInput,
                    final Object newInput) {
                // Nothing to do on inputChanged
            }

            public Object[] getElements(final Object inputElement) {
                // Make an entry for each descriptor
                Descriptor[] elements = (Descriptor[]) inputElement;
                Descriptor[] results = new Descriptor[elements.length];
                System.arraycopy(elements, 0, results, 0, elements.length);
                Collections.sort(Arrays.asList(results), comparer);
                return results;
            }

        });

        checkboxViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(final SelectionChangedEvent event) {
                if (event.getSelection() instanceof IStructuredSelection) {
                    IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                    Descriptor descriptor = (Descriptor) sel.getFirstElement();
                    if (descriptor == null) {
                        clearDescription();
                    } else {
                        showDescription(descriptor);
                    }
                }
            }
        });

        checkboxViewer.addCheckStateListener(new ICheckStateListener() {

            public void checkStateChanged(final CheckStateChangedEvent event) {
                checkboxViewer.setSelection(new StructuredSelection(event.getElement()));
            }
        });

        createDescriptionArea(combinationsComposite);

        populateDescriptors();

        return null;
    }

    private void createDescriptionArea(final Composite composite) {

        Font mainFont = composite.getFont();
        Composite textComposite = new Composite(composite, SWT.NONE);
        textComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout textLayout = new GridLayout();
        textLayout.marginWidth = 0;
        textLayout.marginHeight = 0;
        textComposite.setLayout(textLayout);
        textComposite.setFont(mainFont);

        Label descriptionLabel = new Label(textComposite, SWT.NONE);
        descriptionLabel.setText("Description");
        descriptionLabel.setFont(mainFont);

        descriptionText = new Text(textComposite, SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.BORDER
                | SWT.H_SCROLL);
        descriptionText.setLayoutData(new GridData(GridData.FILL_BOTH));
        descriptionText.setFont(mainFont);
    }

    /**
     * Populates the list of descriptors.
     */
    private void populateDescriptors() {
        Descriptor[] descriptors = Viewmanagement.getInstance().getAvailableCombinations()
                .toArray(new Descriptor[0]);
        checkboxViewer.setInput(descriptors);
        for (Descriptor d : descriptors) {
            checkboxViewer.setChecked(d, d.isActive());
        }
    }

    /**
     * Show the selected description in the text.
     */
    private void showDescription(final Descriptor descriptor) {
        if (descriptionText == null || descriptionText.isDisposed()) {
            return;
        }
        String text = descriptor.getDescription();
        if (text == null || text.length() == 0) {
            descriptionText.setText("no description");
        } else {
            descriptionText.setText(text);
        }
    }

    /**
     * Clear the selected description in the text.
     */
    private void clearDescription() {
        if (descriptionText == null || descriptionText.isDisposed()) {
            return;
        }
        descriptionText.setText(""); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    protected IPreferenceStore doGetPreferenceStore() {
        return KiViPlugin.getDefault().getPreferenceStore();
    }

}
