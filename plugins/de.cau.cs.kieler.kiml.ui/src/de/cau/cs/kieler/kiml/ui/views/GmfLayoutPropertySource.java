/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
import java.util.Map.Entry;

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

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortConstraints;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.KimlUiUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsFactory;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * A property source for layout options for GMF diagrams.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class GmfLayoutPropertySource implements IPropertySource {

    /** array of choices for the layout hint option. */
    private static String[] layoutHintChoices;
    /** array of identifiers for the layout hint option. */
    private static String[] layoutHintValues;
    /** map of layout hint identifiers to their respective indices in the above arrays. */
    private static Map<String, Integer> layoutHintIndexMap;
    
    /** list of layout option data. */
    private List<LayoutOptionData> optionDataList;
    /** array of property descriptors for the option data. */
    private IPropertyDescriptor[] propertyDescriptors;
    /** the editing domain used to perform changes to the notation view. */
    private TransactionalEditingDomain editingDomain;
    /** the notation view where layout options are kept persistently. */
    private View notationView;
    /** the layout option style stored in the notation view. */
    private LayoutOptionStyle optionStyle;
    /** map of layout option data to KOptions from the layout option style. */
    private Map<LayoutOptionData, KOption> koptionMap = new HashMap<LayoutOptionData, KOption>();
    /** map of layout option data to the default values that were read on class creation. */
    private Map<LayoutOptionData, Object> defaultValueMap = new HashMap<LayoutOptionData, Object>();
    /** layout provider data of the container edit part. */
    private LayoutProviderData containerProviderData;
    /** edit parts associated with this property source. */
    private IGraphicalEditPart containerEditPart, childCompartmentEditPart;
    
    /**
     * Creates a layout property source for the given graphical edit part.
     * 
     * @param theeditPart a graphical edit part from a GMF diagram
     */
    public GmfLayoutPropertySource(final IGraphicalEditPart theeditPart) {
        // find an appropriate property source and set the layout option targets
        LayoutServices layoutServices = LayoutServices.getInstance();
        IGraphicalEditPart editPart = theeditPart;
        EditPart parent = theeditPart;
        LayoutOptionData.Target partTarget = null;
        do {
            editPart = (IGraphicalEditPart) parent;
            if (editPart instanceof AbstractBorderItemEditPart) {
                partTarget = LayoutOptionData.Target.PORTS;
                containerEditPart = (IGraphicalEditPart) editPart.getParent().getParent();
                break;
            } else if (editPart instanceof ShapeNodeEditPart) {
                if (!layoutServices.isNolayout(editPart.getClass())) {
                    // check whether the node is a parent
                    for (Object child : editPart.getChildren()) {
                        if (child instanceof ShapeNodeEditPart
                                && !(child instanceof AbstractBorderItemEditPart)
                                || child instanceof CompartmentEditPart
                                && ((CompartmentEditPart) child).getChildren().size() > 0) {
                            if (((IGraphicalEditPart) child).getFigure().isVisible()) {
                                if (child instanceof CompartmentEditPart) {
                                    childCompartmentEditPart = (IGraphicalEditPart) child;
                                } else {
                                    childCompartmentEditPart = editPart;
                                }
                                break;
                            }
                        }
                    }
                    partTarget = LayoutOptionData.Target.NODES;
                    containerEditPart = (IGraphicalEditPart) editPart.getParent();
                }
                break;
            } else if (editPart instanceof ConnectionEditPart) {
                partTarget = LayoutOptionData.Target.EDGES;
                containerEditPart = (IGraphicalEditPart) ((ConnectionEditPart) editPart)
                        .getSource().getParent();
                break;
            } else if (editPart instanceof LabelEditPart) {
                partTarget = LayoutOptionData.Target.LABELS;
                containerEditPart = (IGraphicalEditPart) editPart.getParent();
                if (containerEditPart instanceof ConnectionEditPart) {
                    containerEditPart = (IGraphicalEditPart) ((ConnectionEditPart) containerEditPart)
                            .getSource().getParent();
                } else if (containerEditPart instanceof AbstractBorderItemEditPart) {
                    containerEditPart =  (IGraphicalEditPart) containerEditPart.getParent().getParent();
                } else if (containerEditPart instanceof ShapeNodeEditPart) {
                    containerEditPart = (IGraphicalEditPart) containerEditPart.getParent();
                }
                break;
            } else if (editPart instanceof DiagramEditPart) {
                partTarget = LayoutOptionData.Target.PARENTS;
                containerEditPart = editPart;
                childCompartmentEditPart = editPart;
                break;
            }
            parent = editPart.getParent();
        } while (parent instanceof IGraphicalEditPart);
        assert partTarget != null && containerEditPart != null;
        
        // get default options from the notation view or the domain model
        String partLayoutHint = null;
        notationView = editPart.getNotationView();
        editingDomain = editPart.getEditingDomain();
//        EObject domainElement = notationView.getElement();
//        if (domainElement instanceof EModelElement)
//            optionStyle = (LayoutOptionStyle)((EModelElement)domainElement)
//                    .getEAnnotation(LayoutOptionStyle.class.getName());
//        if (optionStyle == null)
        optionStyle = (LayoutOptionStyle) notationView.getStyle(
                LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (LayoutOptions.LAYOUT_HINT.equals(koption.getKey())) {
                    partLayoutHint = ((KStringOption) koption).getValue();
                }
                LayoutOptionData optionData = layoutServices.getLayoutOptionData(koption.getKey());
                koptionMap.put(optionData, koption);
                Object defaultValue = KimlLayoutUtil.getValue(koption, optionData);
                defaultValueMap.put(optionData, defaultValue);
            }
        }

        // set default options from the GMF diagram layout manager
        if (partTarget == LayoutOptionData.Target.NODES) {
            LayoutOptionData portConstraintsOptionData = layoutServices
                    .getLayoutOptionData(LayoutOptions.PORT_CONSTRAINTS);
            if (childCompartmentEditPart == null) {
                LayoutOptionData fixedSizeOptionData = layoutServices
                        .getLayoutOptionData(LayoutOptions.FIXED_SIZE);
                if (!defaultValueMap.containsKey(fixedSizeOptionData)) {
                    defaultValueMap.put(fixedSizeOptionData, Boolean.TRUE);
                }
                if (!defaultValueMap.containsKey(portConstraintsOptionData)) {
                    defaultValueMap.put(portConstraintsOptionData, PortConstraints.FIXED_POS.ordinal());
                }
            } else {
                if (!defaultValueMap.containsKey(portConstraintsOptionData)) {
                    defaultValueMap.put(portConstraintsOptionData, PortConstraints.FREE_PORTS.ordinal());
                }
            }
        }
        
        // create the list of shown layout options
        KOption containerLayoutHintOption = KimlUiUtil.getKOption(containerEditPart,
                LayoutOptions.LAYOUT_HINT);
        String containerLayoutHint = containerLayoutHintOption instanceof KStringOption
                ? ((KStringOption) containerLayoutHintOption).getValue() : null;
        String containerDiagramType = (String) layoutServices.getOption(containerEditPart.getClass(),
                LayoutOptions.DIAGRAM_TYPE);
        containerProviderData = layoutServices.getLayoutProviderData(
                containerLayoutHint, containerDiagramType);
        if (containerProviderData == null) {
            optionDataList = new ArrayList<LayoutOptionData>();
        } else {
            LayoutOptionData layoutHintData = layoutServices.getLayoutOptionData(
                    LayoutOptions.LAYOUT_HINT);
            optionDataList = layoutServices.getLayoutOptions(containerProviderData, partTarget);
            if (partTarget == LayoutOptionData.Target.PARENTS
                    && !defaultValueMap.containsKey(layoutHintData)) {
                defaultValueMap.put(layoutHintData, containerProviderData.getId());
            }
            if (partTarget != LayoutOptionData.Target.PARENTS
                    && childCompartmentEditPart != null) {
                String childCompartmentDiagramType = (String) layoutServices.getOption(
                        childCompartmentEditPart.getClass(), LayoutOptions.DIAGRAM_TYPE);
                LayoutProviderData partProviderData = layoutServices.getLayoutProviderData(
                        partLayoutHint, childCompartmentDiagramType);
                optionDataList.addAll(layoutServices.getLayoutOptions(partProviderData,
                        LayoutOptionData.Target.PARENTS));
                if (!defaultValueMap.containsKey(layoutHintData)) {
                    defaultValueMap.put(layoutHintData, partProviderData.getId());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            if (layoutHintChoices == null) {
                createLayoutHintChoices();
            }
            propertyDescriptors = new IPropertyDescriptor[optionDataList.size()];
            for (int i = 0; i < propertyDescriptors.length; i++) {
                propertyDescriptors[i] = new LayoutPropertyDescriptor(optionDataList.get(i),
                        layoutHintChoices, layoutHintValues);
            }
        }
        return propertyDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    public Object getPropertyValue(final Object id) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        LayoutOptionData optionData = layoutServices.getLayoutOptionData((String) id);
        KOption koption = koptionMap.get(optionData);
        Object value = null;
        if (koption == null) {
            Object defaultValue = defaultValueMap.get(optionData);
            if (defaultValue == null) {
                if (optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
                    LayoutProviderData partProviderData = layoutServices.getLayoutProviderData(
                            (String) defaultValueMap.get(layoutServices
                            .getLayoutOptionData(LayoutOptions.LAYOUT_HINT)));
                    value = KimlUiUtil.getDefault(optionData, partProviderData,
                            childCompartmentEditPart);
                } else {
                    value = KimlUiUtil.getDefault(optionData, containerProviderData, containerEditPart);
                }
            } else {
                value = defaultValue;
            }
        } else {
            value = KimlLayoutUtil.getValue(koption, optionData);
        }
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
            return layoutHintIndexMap.get(value);
        } else if (optionData.getType() == LayoutOptionData.Type.INT
                || optionData.getType() == LayoutOptionData.Type.FLOAT) {
            return value.toString();
        } else {
            return value;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void setPropertyValue(final Object id, final Object thevalue) {
        Runnable modelChange = new Runnable() {
            public void run() {
                Object value = thevalue;
                LayoutOptionData optionData = LayoutServices.getInstance()
                        .getLayoutOptionData((String) id);
                KOption koption = koptionMap.get(optionData);
                if (koption == null) {
                    if (optionStyle == null) {
                        optionStyle = LayoutOptionsFactory.eINSTANCE.createLayoutOptionStyle();
                        notationView.getStyles().add(optionStyle);
                    }
                    koption = KimlUiUtil.addKOption(optionStyle, optionData);
                    koptionMap.put(optionData, koption);
                }
                if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                    KimlLayoutUtil.setValue(koption, optionData,
                            layoutHintValues[((Integer) value).intValue()]);
                    LayoutViewPart.refreshLayoutView();
                } else {
                    if (optionData.getType() == LayoutOptionData.Type.INT) {
                        value = Integer.valueOf((String) value);
                    } else if (optionData.getType() == LayoutOptionData.Type.FLOAT) {
                        value = Float.valueOf((String) value);
                    }
                    KimlLayoutUtil.setValue(koption, optionData, value);
                }
            }
        };
        KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.11"));
    }
    
    /**
     * {@inheritDoc}
     */
    public Object getEditableValue() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPropertySet(final Object id) {
        LayoutOptionData optionData = LayoutServices.getInstance().getLayoutOptionData((String) id);
        KOption koption = koptionMap.get(optionData);
        return koption != null;
    }

    /**
     * {@inheritDoc}
     */
    public void resetPropertyValue(final Object id) {
        final LayoutOptionData optionData = LayoutServices.getInstance()
                .getLayoutOptionData((String) id);
        final KOption koption = koptionMap.get(optionData);
        if (koption != null) {
            Runnable modelChange = new Runnable() {
                public void run() {
                    KimlUiUtil.removeKOption(optionStyle, optionData.getId());
                }
            };
            KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.12"));
            koptionMap.remove(optionData);
        }
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
            LayoutViewPart.refreshLayoutView();
        }
    }

    /**
     * Creates the array of layout hint choices to be displayed to the user.
     */
    private static void createLayoutHintChoices() {
        LayoutServices layoutServices = LayoutServices.getInstance();
        Map<String, List<LayoutProviderData>> typeMap
                = new LinkedHashMap<String, List<LayoutProviderData>>();
        int choicesCount = 0;
        for (LayoutProviderData providerData : layoutServices.getLayoutProviderData()) {
            String key = providerData.getType();
            if (layoutServices.getLayoutTypeName(key) == null) {
                key = "";
            }
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
        layoutHintValues = new String[choicesCount];
        layoutHintIndexMap = new HashMap<String, Integer>();
        int i = 0;
        for (Entry<String, List<LayoutProviderData>> entry : typeMap.entrySet()) {
            String typeId = entry.getKey();
            String typeName = layoutServices.getLayoutTypeName(typeId);
            if (typeName == null) {
                typeName = Messages.getString("kiml.ui.8");
            }
            layoutHintValues[i] = typeId;
            layoutHintIndexMap.put(typeId, Integer.valueOf(i));
            layoutHintChoices[i++] = typeName + " " + Messages.getString("kiml.ui.9");
            for (LayoutProviderData providerData : entry.getValue()) {
                String providerName = "  - " + providerData.getName();
                String category = layoutServices.getCategoryName(providerData.getCategory());
                if (category != null) {
                    providerName += " (" + category + ")";
                }
                layoutHintValues[i] = providerData.getId();
                layoutHintIndexMap.put(providerData.getId(), Integer.valueOf(i));
                layoutHintChoices[i++] = providerName;
            }
        }
    }
    
    /**
     * Returns an identifier for a displayed layout hint name.
     * 
     * @param displayedName a displayed name of a layout provider or a layout type
     * @return the corresponding identifier, or the empty string if no match is found
     */
    public static String getLayoutHint(final String displayedName) {
        // look for a matching layout provider
        for (LayoutProviderData providerData : LayoutServices.getInstance().getLayoutProviderData()) {
            if (displayedName.startsWith(providerData.getName())) {
                return providerData.getId();
            }
        }
        // look for a matching layout type
        for (Pair<String, String> layoutType : LayoutServices.getInstance().getLayoutTypes()) {
            String typeId = layoutType.getFirst();
            String typeName = layoutType.getSecond();
            if (displayedName.startsWith(typeName)) {
                return typeId;
            }
        }
        return "";
    }

}
