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
package de.cau.cs.kieler.kiml.ui.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ListDialog;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.preferences.OptionsTableProvider.DataEntry;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutDataService;
import de.cau.cs.kieler.kiml.ui.service.EclipseLayoutInfoService;

/**
 * A dialog to add new default layout options in the preference page.
 *
 * @kieler.rating 2011-01-13 proposed yellow msp
 * @author msp
 */
public class NewOptionDialog extends Dialog {
    
    /** the currently selected element type. */
    private ElementType elementType;
    /** the text for selection of a specific element. */
    private Text elementText;
    /** the value of the specific element or diagram type. */
    private String elementValue;
    /** the browse button for element selection. */
    private Button elementBrowseButton;
    /** the text for selection of a layout option. */
    private Text optionText;
    /** the value of the layout option identifier. */
    private String optionValue;
    
    /**
     * Creates a new option dialog.
     * 
     * @param parentShell the parent shell
     */
    protected NewOptionDialog(final Shell parentShell) {
        super(parentShell);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
        shell.setText(Messages.getString("kiml.ui.46")); //$NON-NLS-1$
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        elementValue = elementText.getText();
        optionValue = optionText.getText();
        return super.close();
    }
    
    /** gap between label and control. */
    private static final int HORIZONTAL_GAP = 8;
    /** minimum width of each group. */
    private static final int MINIMUM_WIDTH = 500;
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        createTypeGroup(composite);
        createElementGroup(composite);
        createOptionGroup(composite);
        return composite;
    }

    /**
     * Create group for element type selection.
     * 
     * @param parent the parent control
     */
    private void createTypeGroup(final Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setText(Messages.getString("kiml.ui.42")); //$NON-NLS-1$
        GridLayout layout = new GridLayout(1, false);
        layout.horizontalSpacing = HORIZONTAL_GAP;
        group.setLayout(layout);
        String[][] labelsAndValues = new String[][] {
                { Messages.getString("kiml.ui.43"), ElementType.EDIT_PART.toString() }, //$NON-NLS-1$
                { Messages.getString("kiml.ui.44"), ElementType.MODEL_ELEM.toString() }, //$NON-NLS-1$
                { Messages.getString("kiml.ui.45"), ElementType.DIAG_TYPE.toString() } //$NON-NLS-1$
        };
        for (int i = 0; i < labelsAndValues.length; i++) {
            Button radio = new Button(group, SWT.RADIO | SWT.LEFT);
            radio.setSelection(i == 0);
            String[] labelAndValue = labelsAndValues[i];
            radio.setText(labelAndValue[0]);
            radio.setData(labelAndValue[1]);
            radio.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(final SelectionEvent event) {
                    elementType = ElementType.valueOf((String) event.widget.getData());
                    elementBrowseButton.setEnabled(elementType == ElementType.DIAG_TYPE);
                }
            });
        }
        elementType = ElementType.EDIT_PART;
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
        gridData.minimumWidth = MINIMUM_WIDTH;
        group.setLayoutData(gridData);
    }
    
    /**
     * Create group for selection of specific element.
     * 
     * @param parent the parent control
     */
    private void createElementGroup(final Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setText(Messages.getString("kiml.ui.47")); //$NON-NLS-1$
        group.setLayout(new GridLayout(2, false));
        Label label = new Label(group, SWT.WRAP);
        label.setText(Messages.getString("kiml.ui.53")); //$NON-NLS-1$
        GridData labelLayoutData = new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 1);
        labelLayoutData.widthHint = MINIMUM_WIDTH - HORIZONTAL_GAP;
        label.setLayoutData(labelLayoutData);
        elementText = new Text(group, SWT.SINGLE | SWT.BORDER);
        elementText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        elementBrowseButton = new Button(group, SWT.PUSH | SWT.CENTER);
        elementBrowseButton.setEnabled(false);
        elementBrowseButton.setText(Messages.getString("kiml.ui.48")); //$NON-NLS-1$
        elementBrowseButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                String id = showBrowseDiagtDialog();
                if (id != null) {
                    elementText.setText(id);
                }
            }
        });
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
        gridData.minimumWidth = MINIMUM_WIDTH;
        group.setLayoutData(gridData);
    }
    
    /**
     * Create group for selection of a layout option.
     * 
     * @param parent the parent control
     */
    private void createOptionGroup(final Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setText(Messages.getString("kiml.ui.49")); //$NON-NLS-1$
        group.setLayout(new GridLayout(2, false));
        optionText = new Text(group, SWT.SINGLE | SWT.BORDER);
        optionText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        Button browseButton = new Button(group, SWT.PUSH | SWT.CENTER);
        browseButton.setText(Messages.getString("kiml.ui.48")); //$NON-NLS-1$
        browseButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                String id = showBrowseOptionDialog();
                if (id != null) {
                    optionText.setText(id);
                }
            }
        });
        GridData gridData = new GridData(SWT.FILL, SWT.TOP, true, false);
        gridData.minimumWidth = MINIMUM_WIDTH;
        group.setLayoutData(gridData);

    }
    
    /** data holder class for selection of a layout option or diagram type. */
    private static final class SelectionData implements Comparable<SelectionData> {
        private String id;
        private String name;
        private LayoutOptionData.Type type;
        
        /**
         * Create a selection data object from a layout option data.
         * 
         * @param optionData a layout option data
         */
        private SelectionData(final LayoutOptionData<?> optionData) {
            this.id = optionData.getId();
            this.name = optionData.getName();
            this.type = optionData.getType();
        }
        
        /**
         * Create a selection data object from a diagram type.
         * 
         * @param diagramType a pair with the diagram type identifier as first element and
         *     the name as second element
         */
        private SelectionData(final Pair<String, String> diagramType) {
            this.id = diagramType.getFirst();
            this.name = diagramType.getSecond();
        }
        
        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return name + " (" + id + ")";
        }

        /**
         * {@inheritDoc}
         */
        public int compareTo(final SelectionData other) {
            int nameComp = this.name.compareTo(other.name);
            if (nameComp == 0) {
                return this.id.compareTo(other.id);
            } else {
                return nameComp;
            }
        }
    }
    
    /**
     * Open a dialog to browse diagram types.
     * 
     * @return the selected diagram type
     */
    private String showBrowseDiagtDialog() {
        ListDialog dialog = new ListDialog(this.getShell());
        dialog.setTitle(Messages.getString("kiml.ui.57")); //$NON-NLS-1$
        dialog.setContentProvider(ArrayContentProvider.getInstance());
        dialog.setLabelProvider(new LabelProvider());
        List<Pair<String, String>> diagramTypes = EclipseLayoutInfoService
                .getInstance().getDiagramTypes();
        SelectionData[] input = new SelectionData[diagramTypes.size()];
        int i = 0;
        for (Pair<String, String> type : diagramTypes) {
            SelectionData seld = new SelectionData(type);
            input[i++] = seld;
        }
        Arrays.sort(input);
        dialog.setInput(input);
        if (dialog.open() == ListDialog.OK) {
            Object[] result = dialog.getResult();
            if (result != null && result.length > 0) {
                return ((SelectionData) result[0]).id;
            }
        }
        return null;
    }
    
    /**
     * Open a dialog to browse layout options.
     * 
     * @return the selected layout option
     */
    private String showBrowseOptionDialog() {
        ListDialog dialog = new ListDialog(this.getShell());
        dialog.setTitle(Messages.getString("kiml.ui.50")); //$NON-NLS-1$
        dialog.setContentProvider(ArrayContentProvider.getInstance());
        dialog.setLabelProvider(new LabelProvider() {
            public Image getImage(final Object element) {
                if (element instanceof SelectionData) {
                    KimlUiPlugin.Images images = KimlUiPlugin.getDefault().getImages();
                    switch (((SelectionData) element).type) {
                    case OBJECT:
                    case STRING:
                        return images.getPropText();
                    case BOOLEAN:
                        return images.getPropTrue();
                    case REMOTE_ENUM:
                    case ENUM:
                        return images.getPropChoice();
                    case INT:
                        return images.getPropInt();
                    case FLOAT:
                        return images.getPropFloat();
                    }
                }
                return null;
            }
        });
        Collection<LayoutOptionData<?>> data = EclipseLayoutDataService
                .getInstance().getOptionData();
        ArrayList<SelectionData> inputList = new ArrayList<SelectionData>(data.size());      
        for (LayoutOptionData<?> optionData : data) {
            // layout options without target definition are now shown to the user
            if (!optionData.getTargets().isEmpty()) {
                inputList.add(new SelectionData(optionData));
            }
        }
        SelectionData[] input = inputList.toArray(new SelectionData[0]);
        Arrays.sort(input);
        dialog.setInput(input);
        if (dialog.open() == ListDialog.OK) {
            Object[] result = dialog.getResult();
            if (result != null && result.length > 0) {
                return ((SelectionData) result[0]).id;
            }
        }
        return null;
    }
    
    /**
     * Create a new data entry for the layout option.
     * 
     * @return a new data entry, or {@code null} if the dialog contents are invalid
     */
    public DataEntry createDataEntry() {
        if (elementValue != null && optionValue != null) {
            String name;
            if (elementType == ElementType.DIAG_TYPE) {
                name = EclipseLayoutInfoService.getInstance().getDiagramTypeName(elementValue);
            } else {
                int dotIndex = elementValue.lastIndexOf('.');
                name = elementValue.substring(dotIndex + 1);
            }
            LayoutOptionData<?> optionData = LayoutDataService.getInstance()
                    .getOptionData(optionValue);
            if (optionData != null) {
                Object value = optionData.getDefault();
                if (value == null) {
                    value = optionData.getDefaultDefault();
                }
                if (name != null && value != null) {
                    return new DataEntry(name, elementValue, elementType, optionData, value);
                }
            }
        }
        return null;
    }

}
