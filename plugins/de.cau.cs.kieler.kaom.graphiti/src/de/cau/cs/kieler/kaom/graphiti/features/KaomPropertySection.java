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
package de.cau.cs.kieler.kaom.graphiti.features;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.platform.GFPropertySection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import de.cau.cs.kieler.core.annotations.NamedObject;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * Section for construction of the property view.
 * 
 * @author atr
 */
public class KaomPropertySection extends GFPropertySection implements ITabbedPropertyConstants {

    /** the text widget for editing the name. */
    private Text nameText;

    /** Listener used when focus is lost from the text field.
      * It updates the name of the selected element.
      */
    private class KaomFocusListener implements FocusListener {

        /**
         * {@inheritDoc}
         */
        public void focusGained(final FocusEvent e) {
        }

        /**
         * {@inheritDoc}
         */
        public void focusLost(final FocusEvent e) {
            PictogramElement pe = getSelectedPictogramElement();
            if (pe instanceof ConnectionDecorator) {
                pe = ((ConnectionDecorator) pe).getConnection();
            }
            if (pe != null) {
                final Object bo = Graphiti.getLinkService()
                        .getBusinessObjectForLinkedPictogramElement(pe);
                if (bo instanceof NamedObject) {
                    final DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
                    TransactionalEditingDomain editingDomain = diagramEditor.getEditingDomain();

                    final PictogramElement pictogramElement = pe;
                    KimlUiUtil.runModelChange(new Runnable() {
                        public void run() {
                            String name = nameText.getText();
                            if (name != null) {
                                if (bo instanceof NamedObject) {
                                    ((NamedObject) bo).setName(name);
                                    diagramEditor.getDiagramTypeProvider().getFeatureProvider()
                                            .layoutIfPossible(new LayoutContext(pictogramElement));
                                }
                            }
                        }
                    }, editingDomain, "Automatic Layout");
                }

            }
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(final Composite parent,
            final TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        TabbedPropertySheetWidgetFactory factory = getWidgetFactory();
        Composite composite = factory.createFlatFormComposite(parent);
        FormData data;

        nameText = factory.createText(composite, "");
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, VSPACE);

        // Layouts the nameText field and adds the listener
        nameText.setLayoutData(data);
        nameText.addFocusListener(new KaomFocusListener());
        CLabel valueLabel = factory.createCLabel(composite, "Name:");

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(nameText, -HSPACE);
        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
        valueLabel.setLayoutData(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        PictogramElement pe = getSelectedPictogramElement();
        if (pe instanceof ConnectionDecorator) {
            pe = ((ConnectionDecorator) pe).getConnection();
        }
        if (pe != null) {
            Object bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
            if (bo instanceof NamedObject) {
                String name = ((NamedObject) bo).getName();
                nameText.setText(name == null ? "" : name);
            }
        }
    }

}
