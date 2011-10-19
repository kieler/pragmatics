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
package de.cau.cs.kieler.core.kivi.preferences;

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

import de.cau.cs.kieler.core.kivi.KiViPlugin;
import de.cau.cs.kieler.core.kivi.CombinationDescriptor;
import de.cau.cs.kieler.core.kivi.KiVi;

/**
 * Preference page for the view management.
 * 
 * @author mmu
 */
public class KiViPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    private Text descriptionText;

    private CheckboxTableViewer checkboxViewer;

    private Button kiviActive;

    private SelectionListener kiviActiveListener;

    private Composite combinationsComposite;

    private List<CombinationDescriptor> combinations = KiVi.getInstance()
            .getAvailableCombinations();

    /**
     * Default constructor.
     */
    public KiViPreferencePage() {
        super("View Management preference page");
    }

    /**
     * {@inheritDoc}
     */
    public void init(final IWorkbench workbench) {
        setPreferenceStore(KiViPlugin.getDefault().getPreferenceStore());
    }

    /**
     * {@inheritDoc}
     */
    public boolean performOk() {
        if (super.performOk()) {
            getPreferenceStore().setValue(KiVi.PROPERTY_ACTIVE, kiviActive.getSelection());
            KiVi.getInstance().setActive(kiviActive.getSelection());
            for (CombinationDescriptor d : combinations) {
                boolean checked = checkboxViewer.getChecked(d);
                getPreferenceStore().setValue(d.getClazz().getCanonicalName() + ".active", checked);
                d.setActive(checked);
            }
            KiVi.getInstance().loadActiveStates();
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    protected void performDefaults() {
        kiviActive.setSelection(getPreferenceStore().getDefaultBoolean(KiVi.PROPERTY_ACTIVE));
        enableComposites(getPreferenceStore().getDefaultBoolean(KiVi.PROPERTY_ACTIVE));
        for (CombinationDescriptor d : combinations) {
            boolean checked = getPreferenceStore().getDefaultBoolean(
                    d.getClazz().getCanonicalName() + ".active");
            checkboxViewer.setChecked(d, checked);
            getPreferenceStore().setValue(d.getClazz().getCanonicalName() + ".active", checked);
            d.setActive(checked);
        }
    }

    @Override
    protected Control createContents(final Composite parent) {
        // GUI code may use magic numbers.
        // CHECKSTYLEOFF MagicNumber

        Font font = parent.getFont();

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

        // TODO check how much of all this layout stuff is actually needed
        GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        layout.verticalSpacing = 10;
        mainComposite.setLayout(layout);

        kiviActive = new Button(mainComposite, SWT.CHECK);
        combinationsComposite = new Composite(mainComposite, SWT.NONE);

        kiviActive.setText("Enable view management");
        kiviActive.setSelection(KiVi.getInstance().isActive());
        kiviActiveListener = new SelectionListener() {

            public void widgetSelected(final SelectionEvent e) {
                enableComposites(((Button) e.widget).getSelection());
            }

            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        };
        kiviActive.addSelectionListener(kiviActiveListener);

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
                return ((CombinationDescriptor) element).getName();
            }
        });
        checkboxViewer.getTable().setFont(font);

        checkboxViewer.setContentProvider(new IStructuredContentProvider() {

            private Comparator<CombinationDescriptor> c = new Comparator<CombinationDescriptor>() {
                private Collator collator = Collator.getInstance();

                public int compare(final CombinationDescriptor arg0,
                        final CombinationDescriptor arg1) {
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
                CombinationDescriptor[] elements = (CombinationDescriptor[]) inputElement;
                CombinationDescriptor[] results = new CombinationDescriptor[elements.length];
                System.arraycopy(elements, 0, results, 0, elements.length);
                Collections.sort(Arrays.asList(results), c);
                return results;
            }

        });

        checkboxViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(final SelectionChangedEvent event) {
                if (event.getSelection() instanceof IStructuredSelection) {
                    IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                    CombinationDescriptor descriptor = (CombinationDescriptor) sel
                            .getFirstElement();
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

        enableComposites(KiVi.getInstance().isActive());

        return null;
        
        // CHECKSTYLEON MagicNumber
    }

    private void enableComposites(final boolean b) {
        combinationsComposite.setEnabled(b);
        checkboxViewer.setAllGrayed(!b);

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
        descriptionText.setText("\n\n\n");
    }

    /**
     * Populates the list of descriptors.
     */
    private void populateDescriptors() {
        CombinationDescriptor[] descriptors = KiVi.getInstance().getAvailableCombinations()
                .toArray(new CombinationDescriptor[0]);
        checkboxViewer.setInput(descriptors);
        for (CombinationDescriptor d : descriptors) {
            checkboxViewer.setChecked(d, d.isActive());
        }
    }

    /**
     * Show the selected description in the text.
     */
    private void showDescription(final CombinationDescriptor descriptor) {
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
