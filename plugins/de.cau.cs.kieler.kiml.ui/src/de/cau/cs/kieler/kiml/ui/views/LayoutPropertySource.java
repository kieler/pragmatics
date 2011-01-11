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

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import de.cau.cs.kieler.core.util.Pair;
import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutProviderData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.layout.EclipseLayoutServices;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

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
    
    /** the layout configuration for this property source. */
    private ILayoutConfig layoutConfig;
    /** the editing domain that is used for model changes. */
    private TransactionalEditingDomain editingDomain;
    /** array of property descriptors for the option data. */
    private IPropertyDescriptor[] propertyDescriptors;

    /**
     * Creates a layout property source for the given layout configuration.
     * 
     * @param config a layout configuration
     * @param theeditingDomain the editing domain
     */
    public LayoutPropertySource(final ILayoutConfig config,
            final TransactionalEditingDomain theeditingDomain) {
        this.layoutConfig = config;
        this.editingDomain = theeditingDomain;
    }

    /**
     * {@inheritDoc}
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            if (layoutHintChoices == null) {
                createLayoutHintChoices();
            }
            List<LayoutOptionData<?>> optionData = layoutConfig.getOptionData();
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
        LayoutOptionData<?> optionData = layoutServices.getLayoutOptionData((String) id);
        Object value;
        if (LayoutOptions.LAYOUTER_HINT_ID.equals(id)) {
            value = layoutConfig.getContentLayouterData().getId();
        } else {
            value = layoutConfig.getProperty(optionData);
        }
        return translateValue(value, optionData);
    }
    
    /**
     * Translate a layout option value into an object that can be handled by the cell
     * editors of the layout view.
     * 
     * @param value a layout option value
     * @param optionData the corresponding layout option data
     * @return a cell editor value
     */
    private static Object translateValue(final Object value, final LayoutOptionData<?> optionData) {
        if (value == null) {
            return "";
        }
        switch (optionData.getType()) {
        case STRING:
            if (LayoutOptions.LAYOUTER_HINT_ID.equals(optionData.getId())) {
                return layoutHintIndexMap.get(value);
            } else {
                return value;
            }
        case INT:
        case FLOAT:
            return value.toString();
        case BOOLEAN:
            if (value instanceof Boolean) {
                return Integer.valueOf(((Boolean) value) ? 1 : 0);
            } else if (value instanceof String) {
                return Integer.valueOf(Boolean.valueOf((String) value) ? 1 : 0);
            } else {
                return value;
            }
        case ENUM:
            if (value instanceof Enum<?>) {
                return ((Enum<?>) value).ordinal();
            } else {
                return value;
            }
        case OBJECT:
            return value.toString();
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
                LayoutOptionData<?> optionData = LayoutServices.getInstance()
                        .getLayoutOptionData((String) id);
                if (LayoutOptions.LAYOUTER_HINT_ID.equals(optionData.getId())) {
                    layoutConfig.setProperty(optionData,
                            layoutHintValues[((Integer) value).intValue()]);
                    LayoutViewPart layoutView = LayoutViewPart.findView();
                    if (layoutView != null) {
                        layoutView.refresh();
                    }
                } else {
                    switch (optionData.getType()) {
                    case BOOLEAN:
                        value = Boolean.valueOf((Integer) value == 1);
                        break;
                    case ENUM:
                        value = optionData.getEnumValue((Integer) value);
                        break;
                    default:
                        value = optionData.parseValue((String) value);
                    }
                    layoutConfig.setProperty(optionData, value);
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
        LayoutOptionData<?> optionData = LayoutServices.getInstance().getLayoutOptionData((String) id);
        return !layoutConfig.isDefault(optionData);
    }

    /**
     * {@inheritDoc}
     */
    public void resetPropertyValue(final Object id) {
        final LayoutOptionData<?> optionData = LayoutServices.getInstance()
                .getLayoutOptionData((String) id);
        Runnable modelChange = new Runnable() {
            public void run() {
                layoutConfig.setProperty(optionData, null);
            }
        };
        KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.12"));
        if (LayoutOptions.LAYOUTER_HINT_ID.equals(optionData.getId())
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
                key = "zzz";
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
        String[] types = typeMap.keySet().toArray(new String[typeMap.keySet().size()]);
        Arrays.sort(types);
        int index = 0;
        for (String typeId : types) {
            String typeName = layoutServices.getLayoutTypeName(typeId);
            if (typeName == null) {
                typeName = Messages.getString("kiml.ui.8");
            }
            layoutHintValues[index] = typeId;
            layoutHintIndexMap.put(typeId, Integer.valueOf(index));
            layoutHintChoices[index++] = typeName + " " + Messages.getString("kiml.ui.9");
            for (LayoutProviderData providerData : typeMap.get(typeId)) {
                String providerName = "  - " + providerData.getName();
                String category = layoutServices.getCategoryName(providerData.getCategory());
                if (category != null) {
                    providerName += " (" + category + ")";
                }
                layoutHintValues[index] = providerData.getId();
                layoutHintIndexMap.put(providerData.getId(), Integer.valueOf(index));
                layoutHintChoices[index++] = providerName;
            }
        }
    }
    
    /**
     * Return an array of choices for the layout hint option.
     * 
     * @return the layout hint choices
     */
    public static String[] getLayoutHintChoices() {
        if (layoutHintChoices == null) {
            createLayoutHintChoices();
        }
        return layoutHintChoices;
    }
    
    /**
     * Return the layout hint at the given index of the layout hint choices array.
     * 
     * @param choiceIndex an index for {@link #getLayoutHintChoices()}
     * @return the corresponding layout hint
     */
    public static String getLayoutHint(final int choiceIndex) {
        if (layoutHintValues == null) {
            createLayoutHintChoices();
        }
        return layoutHintValues[choiceIndex];
    }
    
    /**
     * Returns an identifier for a displayed layout hint name.
     * 
     * @param displayedName a displayed name of a layout provider or a layout type
     * @return the corresponding identifier, or the empty string if no match is found
     */
    public static String getLayoutHint(final String displayedName) {
        // look for a matching layout provider
        String bestHint = null;
        int bestLength = 0;
        for (LayoutProviderData providerData : LayoutServices.getInstance().getLayoutProviderData()) {
            String name = providerData.getName();
            if (displayedName.startsWith(name) && name.length() > bestLength) {
                bestHint = providerData.getId();
                bestLength = name.length();
            }
        }
        if (bestHint == null) {
            // look for a matching layout type
            for (Pair<String, String> layoutType : LayoutServices.getInstance().getLayoutTypes()) {
                String typeId = layoutType.getFirst();
                String typeName = layoutType.getSecond();
                if (displayedName.startsWith(typeName) && typeName.length() > bestLength) {
                    bestHint = typeId;
                    bestLength = typeName.length();
                }
            }
        }
        return bestHint;
    }

}
