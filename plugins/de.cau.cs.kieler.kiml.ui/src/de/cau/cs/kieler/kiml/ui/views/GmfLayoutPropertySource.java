/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.services.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.services.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.KimlUiUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * A property source for layout options for GMF diagrams.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GmfLayoutPropertySource implements IPropertySource {

    private static String[] layoutHintChoices;
    private static Map<Integer, String> layoutHintValueMap;
    private static Map<String, Integer> layoutHintIndexMap;
    
    /** list of layout option data */
    private List<LayoutOptionData> optionDataList;
    /** array of property descriptors for the option data */
    private IPropertyDescriptor[] propertyDescriptors;
    /** the editing domain used to perform changes to the notation view */
    private TransactionalEditingDomain editingDomain;
    /** the notation view where layout options are kept persistently */
    private View notationView;
    /** the layout option style stored in the notation view */
    private LayoutOptionStyle optionStyle;
    /** map of layout option data to KOptions from the layout option style */
    private Map<LayoutOptionData, KOption> koptionMap = new HashMap<LayoutOptionData, KOption>();
    /** map of layout option data to the default values that were read on class creation */
    private Map<LayoutOptionData, Object> defaultValueMap = new HashMap<LayoutOptionData, Object>();
    
    /**
     * Creates a layout property source for the given graphical edit part.
     * 
     * @param editPart a graphical edit part from a GMF diagram
     */
    public GmfLayoutPropertySource(IGraphicalEditPart editPart) {
        // find an appropriate property source and set the layout option targets
        EditPart parent = editPart;
        LayoutOptionData.Target target1 = LayoutOptionData.Target.NODES, target2 = null;
        IGraphicalEditPart container = editPart;
        do {
            editPart = (IGraphicalEditPart)parent;
            if (editPart instanceof AbstractBorderItemEditPart) {
                target1 = LayoutOptionData.Target.PORTS;
                break;
            }
            else if (editPart instanceof ShapeNodeEditPart) {
                if (!LayoutServices.INSTANCE.isNolayout(editPart.getClass())) {
                    // check whether the node is a parent
                    for (Object child : editPart.getChildren()) {
                        if (child instanceof ShapeNodeEditPart && !(child instanceof AbstractBorderItemEditPart)
                                || child instanceof CompartmentEditPart && ((CompartmentEditPart) child).getChildren().size() > 0) {
                            if (((IGraphicalEditPart)child).getFigure().isVisible()) {
                                if (child instanceof CompartmentEditPart)
                                    container = (IGraphicalEditPart)child;
                                target2 = LayoutOptionData.Target.PARENTS;
                                break;
                            }
                        }
                    }
                }
                break;
            }
            else if (editPart instanceof ConnectionEditPart) {
                target1 = LayoutOptionData.Target.EDGES;
                break;
            }
            else if (editPart instanceof LabelEditPart) {
                target1 = LayoutOptionData.Target.LABELS;
                break;
            }
            else if (editPart instanceof DiagramEditPart) {
                target1 = LayoutOptionData.Target.PARENTS;
                break;
            }
            parent = editPart.getParent();
        } while (parent instanceof IGraphicalEditPart);
        
        // get default options from the notation view
        String diagramType = (String)LayoutServices.INSTANCE.getOption(container.getClass(),
                LayoutOptions.DIAGRAM_TYPE);
        String layoutHint = null;
        notationView = editPart.getNotationView();
        editingDomain = editPart.getEditingDomain();
        optionStyle = (LayoutOptionStyle)notationView.getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (LayoutOptions.LAYOUT_HINT.equals(koption.getKey()))
                    layoutHint = ((KStringOption)koption).getValue();
                LayoutOptionData optionData = LayoutServices.INSTANCE.getLayoutOptionData(koption.getKey());
                koptionMap.put(optionData, koption);
                Object defaultValue = KimlUiUtil.getValue(koption, optionData);
                defaultValueMap.put(optionData, defaultValue);
            }
        }
        
        // create the list of shown layout options
        LayoutProviderData layoutProviderData = LayoutServices.INSTANCE.getLayoutProviderData(
                layoutHint, diagramType);
        LayoutOptionData layoutHintData = LayoutServices.INSTANCE.getLayoutOptionData(LayoutOptions.LAYOUT_HINT);
        if (layoutProviderData == null) {
            optionDataList = new ArrayList<LayoutOptionData>();
            optionDataList.add(layoutHintData);
        }
        else {
            if (!defaultValueMap.containsKey(layoutHintData))
                defaultValueMap.put(layoutHintData, layoutProviderData.id);
            LayoutOptionData.Target[] targets = (target2 == null)
                    ? new LayoutOptionData.Target[] { target1 }
                    : new LayoutOptionData.Target[] { target1, target2 };
            optionDataList = LayoutServices.INSTANCE.getLayoutOptions(layoutProviderData, targets);
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            if (layoutHintChoices == null)
                createLayoutHintChoices();
            propertyDescriptors = new IPropertyDescriptor[optionDataList.size()];
            for (int i = 0; i < propertyDescriptors.length; i++) {
                propertyDescriptors[i] = new LayoutPropertyDescriptor(optionDataList.get(i),
                        layoutHintChoices, layoutHintValueMap);
            }
        }
        return propertyDescriptors;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(Object id) {
        LayoutOptionData optionData = LayoutServices.INSTANCE.getLayoutOptionData((String)id);
        KOption koption = koptionMap.get(optionData);
        Object value = null;
        if (koption == null) {
            Object defaultValue = defaultValueMap.get(optionData);
            if (defaultValue == null)
                value = KimlUiUtil.getDefault(optionData);
            else
                value = defaultValue;
        }
        else
            value = KimlUiUtil.getValue(koption, optionData);
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.id))
            return layoutHintIndexMap.get(value);
        else return value;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        LayoutOptionData optionData = LayoutServices.INSTANCE.getLayoutOptionData((String)id);
        KOption koption = koptionMap.get(optionData);
        if (koption == null) {
            if (optionStyle == null)
                optionStyle = KimlUiUtil.addLayoutOptionStyle(notationView, editingDomain);
            koption = KimlUiUtil.addKOption(optionStyle, optionData, editingDomain);
            koptionMap.put(optionData, koption);
        }
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.id))
            value = layoutHintValueMap.get(value);
        KimlUiUtil.setValue(koption, optionData, value, editingDomain);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        LayoutOptionData optionData = LayoutServices.INSTANCE.getLayoutOptionData((String)id);
        KOption koption = koptionMap.get(optionData);
        if (koption != null) {
            Object defaultValue = defaultValueMap.get(optionData);
            if (defaultValue == null)
                return true;
            return defaultValue.equals(KimlUiUtil.getValue(koption, optionData));
        }
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        LayoutOptionData optionData = LayoutServices.INSTANCE.getLayoutOptionData((String)id);
        KOption koption = koptionMap.get(optionData);
        if (koption != null) {
            Object defaultValue = defaultValueMap.get(optionData);
            if (defaultValue == null)
                defaultValue = KimlUiUtil.getDefault(optionData);
            KimlUiUtil.setValue(koption, optionData, defaultValue, editingDomain);
        }
    }

    /**
     * Creates the array of layout hint choices to be displayed to the user.
     */
    private static void createLayoutHintChoices() {
        Map<String, List<LayoutProviderData>> typeMap = new LinkedHashMap<String, List<LayoutProviderData>>();
        int choicesCount = 0;
        for (LayoutProviderData providerData : LayoutServices.INSTANCE.getLayoutProviderData()) {
            String key = providerData.type;
            if (LayoutServices.INSTANCE.getLayoutTypeName(key) == null)
                key = "";
            List<LayoutProviderData> typeList = typeMap.get(key);
            if (typeList == null) {
                typeList = new LinkedList<LayoutProviderData>();
                typeMap.put(key, typeList);
                choicesCount++;
            }
            typeList.add(providerData);
            choicesCount++;
        }
        layoutHintChoices = new String[choicesCount];
        layoutHintValueMap = new HashMap<Integer, String>();
        layoutHintIndexMap = new HashMap<String, Integer>();
        int i = 0;
        for (String typeId : typeMap.keySet()) {
            String typeName = LayoutServices.INSTANCE.getLayoutTypeName(typeId);
            if (typeName == null)
                typeName = Messages.getString("kiml.ui.8");
            layoutHintValueMap.put(Integer.valueOf(i), typeId);
            layoutHintIndexMap.put(typeId, Integer.valueOf(i));
            layoutHintChoices[i++] = typeName;
            for (LayoutProviderData providerData : typeMap.get(typeId)) {
                String providerName = "   " + providerData.name;
                String category = LayoutServices.INSTANCE.getCategoryName(providerData.category);
                if (category != null)
                    providerName += " (" + category + ")";
                layoutHintValueMap.put(Integer.valueOf(i), providerData.id);
                layoutHintIndexMap.put(providerData.id, Integer.valueOf(i));
                layoutHintChoices[i++] = providerName;
            }
        }
    }

}
