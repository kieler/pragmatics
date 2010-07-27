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
 * @author atr
 *
 */
public class EntitySection extends GFPropertySection implements ITabbedPropertyConstants {

    private Text nameText;
    

         private FocusListener listener = new FocusListener() {
        

        public void focusGained(final FocusEvent e) {
            // TODO Auto-generated method stub
            
        }

        public void focusLost(final FocusEvent e) {
            // TODO Auto-generated method stub
            PictogramElement pe = getSelectedPictogramElement();
            
            if (pe != null) {
            
              Object bo = Graphiti.getLinkService()
                  .getBusinessObjectForLinkedPictogramElement(pe);
              if (bo instanceof NamedObject) {
                
                     
                    DiagramEditor diagramEditor = (DiagramEditor) getDiagramEditor();
                    TransactionalEditingDomain editingDomain = 
                        diagramEditor.getEditingDomain();
               
                     KimlUiUtil.runModelChange(new Runnable() {
                     public void run() {
                     
                         Object bo = Graphiti.getLinkService()
                             .getBusinessObjectForLinkedPictogramElement(getSelectedPictogramElement());
                          if (bo == null) {
                            return;
                            }
                          String name = nameText.getText();
                          if (name != null) {
                              if (bo instanceof NamedObject) {
                                ((NamedObject) bo).setName(name);
                             }
                              
                           } 
                          }
                     }, editingDomain, "Automatic Layout");
                  }
            
              }
        }
    };
    

    
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
        nameText.setLayoutData(data);
      //  nameText.addModifyListener(listener);
        nameText.addFocusListener(listener);
        CLabel valueLabel = factory.createCLabel(composite, "Name:");

        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(nameText, -HSPACE);
        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
        valueLabel.setLayoutData(data);

    }

  /*  public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        Assert.isTrue(input instanceof ButtonElement);
        this.buttonElement = (ButtonElement) input;
    }
*/

    
  

    @Override

    public void refresh() {

        PictogramElement pe = getSelectedPictogramElement();
        if (pe != null) {

            Object bo = Graphiti.getLinkService()
                 .getBusinessObjectForLinkedPictogramElement(pe);
            // the filter assured, that it is a EClass
            if (bo == null) {
                return;
            }
            /*
            if (bo instanceof Entity) {
                String name = ((Entity) bo).getName();
                nameText.setText(name == null ? "" : name);
            } else if (bo instanceof Link) { 
                String name = ((Link) bo).getName();   
                nameText.setText(name == null ? "" : name);
            }  else if (bo instanceof Relation) { 
                String name = ((Relation) bo).getName();   
                nameText.setText(name == null ? "" : name);
            }  else if (bo instanceof Port) { 
                String name = ((Port) bo).getName();   
                nameText.setText(name == null ? "" : name);
            }*/
            if (bo instanceof NamedObject) {
                String name = ((NamedObject) bo).getName();
                nameText.setText(name == null ? "" : name);
                }
            }

    }

    
}
