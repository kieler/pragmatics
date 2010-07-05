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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.ILayoutInspector;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;
import de.cau.cs.kieler.kiml.util.KimlLayoutUtil;

/**
 * A property source for layout options for GMF diagrams.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutPropertySource implements IPropertySource {

    /** array of choices for the layout hint option. */
    private static String[] layoutHintChoices;
    /** array of identifiers for the layout hint option. */
    private static String[] layoutHintValues;
    /** map of layout hint identifiers to their respective indices in the above arrays. */
    private static Map<String, Integer> layoutHintIndexMap;
    
    /** the layout inspector for this property source. */
    private ILayoutInspector layoutInspector;
    /** array of property descriptors for the option data. */
    private IPropertyDescriptor[] propertyDescriptors;

    /**
     * Creates a layout property source for the given layout manager and edit part.
     * The layout options of the given inspector are initialized in this constructor.
     * 
     * @param inspector a layout inspector
     */
    public LayoutPropertySource(final ILayoutInspector inspector) {
        inspector.initOptions();
        this.layoutInspector = inspector;
    }

    /**
     * {@inheritDoc}
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            if (layoutHintChoices == null) {
                createLayoutHintChoices();
            }
            List<LayoutOptionData> optionData = layoutInspector.getOptionData();
            propertyDescriptors = new IPropertyDescriptor[optionData.size()];
            for (int i = 0; i < propertyDescriptors.length; i++) {
                propertyDescriptors[i] = new LayoutPropertyDescriptor(optionData.get(i),
                        layoutHintChoices, layoutHintValues);
            }
        }
        return propertyDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    public Object getPropertyValue(final Object id) {
        EclipseLayoutServices layoutServices = EclipseLayoutServices.getInstance();
        LayoutOptionData optionData = layoutServices.getLayoutOptionData((String) id);
        KOption koption = layoutInspector.getKOption(optionData, false);
        Object value = null;
        if (koption == null) {
            if (LayoutOptions.LAYOUT_HINT.equals(id)) {
                value = layoutInspector.getFocusLayouterData().getId();
            } else {
                boolean hasChildren = layoutInspector.getContainerPart() != null;
                if (optionData.hasTarget(LayoutOptionData.Target.PARENTS)) {
                    value = layoutServices.getDefault(optionData,
                            layoutInspector.getFocusLayouterData(),
                            layoutInspector.getFocusPart(),
                            layoutInspector.getFocusPart(), hasChildren);
                } else {
                    value = layoutServices.getDefault(optionData,
                            layoutInspector.getContainerLayouterData(),
                            layoutInspector.getFocusPart(),
                            layoutInspector.getContainerPart(),
                            hasChildren);
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
            return Integer.valueOf(((Boolean) value) ? 1 : 0);
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
    public void setPropertyValue(final Object id, final Object thevalue) {
        Runnable modelChange = new Runnable() {
            public void run() {
                Object value = thevalue;
                LayoutOptionData optionData = LayoutServices.getInstance()
                        .getLayoutOptionData((String) id);
                KOption koption = layoutInspector.getKOption(optionData, true);
                if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())) {
                    KimlLayoutUtil.setValue(koption, optionData,
                            layoutHintValues[((Integer) value).intValue()]);
                    LayoutViewPart layoutView = LayoutViewPart.findView();
                    if (layoutView != null) {
                        layoutView.refresh();
                    }
                } else {
                    switch (optionData.getType()) {
                    case INT:
                        value = Integer.valueOf((String) value);
                        break;
                    case FLOAT:
                        value = Float.valueOf((String) value);
                        break;
                    case BOOLEAN:
                        value = Boolean.valueOf((Integer) value == 1);
                        break;
                    }
                    KimlLayoutUtil.setValue(koption, optionData, value);
                }
            }
        };
        KimlUiUtil.runModelChange(modelChange, layoutInspector.getEditingDomain(),
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
        KOption koption = layoutInspector.getKOption(optionData, false);
        return koption != null;
    }

    /**
     * {@inheritDoc}
     */
    public void resetPropertyValue(final Object id) {
        final LayoutOptionData optionData = LayoutServices.getInstance()
                .getLayoutOptionData((String) id);
        KOption koption = layoutInspector.getKOption(optionData, false);
        if (koption != null) {
            Runnable modelChange = new Runnable() {
                public void run() {
                    layoutInspector.removeKOption(optionData);
                }
            };
            KimlUiUtil.runModelChange(modelChange, layoutInspector.getEditingDomain(),
                    Messages.getString("kiml.ui.12"));
        }
        if (LayoutOptions.LAYOUT_HINT.equals(optionData.getId())
                || optionData.getType() == LayoutOptionData.Type.BOOLEAN
                || optionData.getType() == LayoutOptionData.Type.ENUM) {
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                layoutView.refresh();
            }
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
