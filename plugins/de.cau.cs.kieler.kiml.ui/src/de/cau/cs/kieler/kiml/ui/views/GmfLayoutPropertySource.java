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

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.layout.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.LayoutProviderData;
import de.cau.cs.kieler.kiml.layout.LayoutServices;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KStringOption;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
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
    /** the layout option style stored in the notation view. */
    private LayoutOptionStyle optionStyle;
    /** map of layout option data to KOptions from the layout option style. */
    private Map<LayoutOptionData, KOption> koptionMap = new HashMap<LayoutOptionData, KOption>();
    /** layout provider data of the shown edit part. */
    private LayoutProviderData partProviderData;
    /** layout provider data of the containing edit part. */
    private LayoutProviderData containerProviderData;
    /** the edit part for which options are shown. */
    private IGraphicalEditPart shownEditPart;
    /** the edit part that contains the sub-diagram with the shown edit part. */
    private IGraphicalEditPart containerEditPart;
    /** the edit part that contains children of the shown edit part. */
    private IGraphicalEditPart childCompartmentEditPart;
    
    /**
     * Returns the edit part for which layout options would be shown by this property
     * source.
     * 
     * @param sourceEditPart the source edit part
     * @return the shown edit part, or {@code null} if there is none
     */
    public static final IGraphicalEditPart getShownEditPart(final IGraphicalEditPart sourceEditPart) {
        if (sourceEditPart instanceof CompartmentEditPart) {
            return (IGraphicalEditPart) sourceEditPart.getParent();
        }
        if (sourceEditPart instanceof ShapeNodeEditPart) {
            if (LayoutServices.getInstance().isNolayout(sourceEditPart.getClass())
                    || LayoutServices.getInstance().isNolayout(sourceEditPart.getParent().getClass())) {
                return null;
            }
        }
        return sourceEditPart;
    }
    
    /**
     * Creates a layout property source for the given graphical edit part.
     * 
     * @param sourceEditPart a graphical edit part from a GMF diagram
     */
    public GmfLayoutPropertySource(final IGraphicalEditPart sourceEditPart) {
        // find an appropriate property source and set the layout option targets
        IGraphicalEditPart editPart = getShownEditPart(sourceEditPart);
        LayoutOptionData.Target partTarget = findTarget(editPart);
        // check if the selected edit part is not supported
        if (partTarget == null) {
            optionDataList = Collections.emptyList();
            return;
        }
        
        // get default options from the notation view
        shownEditPart = editPart;
        optionStyle = (LayoutOptionStyle) editPart.getNotationView().getStyle(
                LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        String partLayoutHint = getNotationOptions();
        
        // create the list of shown layout options
        createShownOptions(partTarget, partLayoutHint);
    }
    
    /**
     * Determines the type of edit part target for the layout options.
     * 
     * @param editPart an edit part
     * @return the edit part target
     */
    private LayoutOptionData.Target findTarget(final IGraphicalEditPart editPart) {
        LayoutOptionData.Target partTarget = null;
        if (editPart instanceof AbstractBorderItemEditPart) {
            partTarget = LayoutOptionData.Target.PORTS;
            containerEditPart = (IGraphicalEditPart) editPart.getParent().getParent();
        } else if (editPart instanceof ShapeNodeEditPart) {
            // check whether the node is a parent
            partTarget = LayoutOptionData.Target.NODES;
            containerEditPart = (IGraphicalEditPart) editPart.getParent();
            childCompartmentEditPart = findCompartmentEditPart(editPart);
        } else if (editPart instanceof ConnectionEditPart) {
            partTarget = LayoutOptionData.Target.EDGES;
            containerEditPart = (IGraphicalEditPart) ((ConnectionEditPart) editPart)
                    .getSource().getParent();
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
        } else if (editPart instanceof DiagramEditPart) {
            partTarget = LayoutOptionData.Target.PARENTS;
            containerEditPart = editPart;
            childCompartmentEditPart = editPart;
        }
        return partTarget;
    }
    
    /**
     * Sets the child compartment edit part for the given node edit part.
     * 
     * @param editPart a node edit part
     * @return the edit part that contains other node edit parts, or {@code null}
     *     if there is none
     */
    private IGraphicalEditPart findCompartmentEditPart(final IGraphicalEditPart editPart) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        for (Object child : editPart.getChildren()) {
            if (child instanceof ShapeNodeEditPart
                    && !(child instanceof AbstractBorderItemEditPart)
                    && !layoutServices.isNolayout(child.getClass())) {
                return editPart;
            } else if (child instanceof CompartmentEditPart
                    && !layoutServices.isNolayout(child.getClass())) {
                for (Object grandChild : ((CompartmentEditPart) child).getChildren()) {
                    if (grandChild instanceof ShapeNodeEditPart
                            && !layoutServices.isNolayout(grandChild.getClass())) {
                        return (IGraphicalEditPart) child;
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Retrieves the options from the notation view of the selected edit part.
     * 
     * @return the layout hint for the selected edit part
     */
    private String getNotationOptions() {
        String partLayoutHint = null;
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                if (LayoutOptions.LAYOUT_HINT.equals(koption.getKey())) {
                    partLayoutHint = ((KStringOption) koption).getValue();
                }
                LayoutOptionData optionData = LayoutServices.getInstance()
                        .getLayoutOptionData(koption.getKey());
                koptionMap.put(optionData, koption);
            }
        }
        return partLayoutHint;
    }
    
    /**
     * Create the layout options to show in the layout view for this property source.
     * 
     * @param partTarget the edit part target
     * @param partLayoutHint the layout hint for the selected edit part
     */
    private void createShownOptions(final LayoutOptionData.Target partTarget,
            final String partLayoutHint) {
        LayoutServices layoutServices = LayoutServices.getInstance();
        KOption containerLayoutHintOption = KimlUiUtil.getKOption(
                (containerEditPart instanceof CompartmentEditPart
                        ? (IGraphicalEditPart) containerEditPart.getParent() : containerEditPart),
                LayoutOptions.LAYOUT_HINT);
        String containerLayoutHint = containerLayoutHintOption instanceof KStringOption
                ? ((KStringOption) containerLayoutHintOption).getValue() : null;
        String containerDiagramType = (String) layoutServices.getOption(containerEditPart.getClass(),
                LayoutOptions.DIAGRAM_TYPE);
        containerProviderData = layoutServices.getLayoutProviderData(
                containerLayoutHint, containerDiagramType);
        if (containerProviderData == null) {
            optionDataList = Collections.emptyList();
        } else {
            optionDataList = layoutServices.getLayoutOptions(containerProviderData, partTarget);
            if (partTarget == LayoutOptionData.Target.PARENTS) {
                partProviderData = containerProviderData;
            } else if (childCompartmentEditPart != null) {
                String childCompartmentDiagramType = (String) layoutServices.getOption(
                        childCompartmentEditPart.getClass(), LayoutOptions.DIAGRAM_TYPE);
                partProviderData = layoutServices.getLayoutProviderData(
                        partLayoutHint, childCompartmentDiagramType);
                optionDataList.addAll(layoutServices.getLayoutOptions(partProviderData,
                        LayoutOptionData.Target.PARENTS));
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
            if (LayoutOptions.LAYOUT_HINT.equals(id)) {
                value = partProviderData.getId();
            } else {
                boolean hasChildren = childCompartmentEditPart != null;
                if (optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
                    value = KimlUiUtil.getDefault(optionData, partProviderData,
                            shownEditPart, childCompartmentEditPart, hasChildren);
                } else {
                    value = KimlUiUtil.getDefault(optionData, containerProviderData,
                            shownEditPart, containerEditPart, hasChildren);
                }
            }
        } else {
            value = KimlLayoutUtil.getValue(koption, optionData);
        }
        switch (optionData.getType()) {
        case STRING:
            if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                return layoutHintIndexMap.get(value);
            } else {
                return value;
            }
        case INT:
        case FLOAT:
            return value.toString();
        case BOOLEAN:
            return Integer.valueOf(((Boolean) value) ? 0 : 1);
        case ENUM:
            if (value instanceof Enum<?>) {
                return ((Enum<?>) value).ordinal();
            } else {
                return value;
            }
        default:
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
                        shownEditPart.getNotationView().getStyles().add(optionStyle);
                    }
                    koption = KimlUiUtil.addKOption(optionStyle, optionData);
                    koptionMap.put(optionData, koption);
                }
                if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                    KimlLayoutUtil.setValue(koption, optionData,
                            layoutHintValues[((Integer) value).intValue()]);
                    LayoutViewPart.refreshLayoutView();
                } else {
                    switch (optionData.getType()) {
                    case INT:
                        value = Integer.valueOf((String) value);
                        break;
                    case FLOAT:
                        value = Float.valueOf((String) value);
                        break;
                    case BOOLEAN:
                        value = Boolean.valueOf((Integer) value == 0);
                        break;
                    }
                    KimlLayoutUtil.setValue(koption, optionData, value);
                }
            }
        };
        KimlUiUtil.runModelChange(modelChange, shownEditPart.getEditingDomain(),
                Messages.getString("kiml.ui.11"));
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
            KimlUiUtil.runModelChange(modelChange, shownEditPart.getEditingDomain(),
                    Messages.getString("kiml.ui.12"));
            koptionMap.remove(optionData);
        }
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())
                || optionData.getType() == LayoutOptionData.Type.BOOLEAN
                || optionData.getType() == LayoutOptionData.Type.ENUM) {
            LayoutViewPart.refreshLayoutView();
        }
    }

    /**
     * Creates the array of layout hint choices to be displayed to the user.
     */
    private static synchronized void createLayoutHintChoices() {
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
