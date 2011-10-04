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

import java.util.List;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutDataService;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.config.DefaultLayoutConfig;
import de.cau.cs.kieler.kiml.config.IMutableLayoutConfig;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.Messages;
import de.cau.cs.kieler.kiml.ui.util.KimlUiUtil;

/**
 * A property source for layout options for GMF diagrams.
 *
 * @kieler.rating 2009-12-11 proposed yellow msp
 * @author msp
 */
public class LayoutPropertySource implements IPropertySource {
    
    /** the layout configuration for this property source. */
    private IMutableLayoutConfig layoutConfig;
    /** the layout context describing which element has been selected. */
    private LayoutContext layoutContext;
    /** the editing domain that is used for model changes. */
    private TransactionalEditingDomain editingDomain;
    /** array of property descriptors for the option data. */
    private IPropertyDescriptor[] propertyDescriptors;

    /**
     * Creates a layout property source for the given layout configuration.
     * 
     * @param config a mutable layout configuration
     * @param context a layout context describing which element has been selected
     * @param theeditingDomain the editing domain
     */
    public LayoutPropertySource(final IMutableLayoutConfig config,
            final LayoutContext context,
            final TransactionalEditingDomain theeditingDomain) {
        this.layoutConfig = config;
        this.layoutContext = context;
        this.editingDomain = theeditingDomain;
    }
    
    /**
     * Return the layout context used for this property source.
     * 
     * @return the layout context
     */
    public LayoutContext getContext() {
        return layoutContext;
    }

    /**
     * {@inheritDoc}
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            layoutContext.setProperty(DefaultLayoutConfig.OPT_MAKE_OPTIONS, true);
            layoutConfig.enrich(layoutContext);
            List<LayoutOptionData<?>> optionData = layoutContext.getProperty(
                    DefaultLayoutConfig.OPTIONS);
            propertyDescriptors = new IPropertyDescriptor[optionData.size()];
            for (int i = 0; i < propertyDescriptors.length; i++) {
                propertyDescriptors[i] = new LayoutPropertyDescriptor(optionData.get(i));
            }
        }
        return propertyDescriptors;
    }

    /**
     * {@inheritDoc}
     */
    public Object getPropertyValue(final Object id) {
        LayoutDataService layoutServices = LayoutDataService.getInstance();
        LayoutOptionData<?> optionData = layoutServices.getOptionData((String) id);
        Object value;
        if (LayoutOptions.ALGORITHM_ID.equals(id)) {
            value = layoutContext.getProperty(DefaultLayoutConfig.CONTENT_ALGO).getId();
        } else {
            value = layoutConfig.getValue(optionData, layoutContext);
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
        case REMOTE_ENUM:
        case ENUM:
            if (value instanceof Enum<?>) {
                return ((Enum<?>) value).ordinal();
            } else if (value instanceof String) {
                String[] choices = optionData.getChoices();
                for (int i = 0; i < choices.length; i++) {
                    if (choices[i].equals(value)) {
                        return i;
                    }
                }
                return 0;
            }
            return value;
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
                LayoutOptionData<?> optionData = LayoutDataService.getInstance()
                        .getOptionData((String) id);
                switch (optionData.getType()) {
                case STRING:
                    break;
                case BOOLEAN:
                    value = Boolean.valueOf((Integer) value == 1);
                    break;
                case ENUM:
                    value = optionData.getEnumValue((Integer) value);
                    break;
                case REMOTE_ENUM:
                    value = optionData.getChoices()[(Integer) value];
                    break;
                default:
                    value = optionData.parseValue((String) value);
                }
                layoutConfig.setValue(optionData, layoutContext, value);
            }
        };
        KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.11"));

        // if the choice of layout algorithm is affected, refresh the whole layout view
        if (LayoutOptions.ALGORITHM_ID.equals(id)) {
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                layoutView.refresh();
            }
        }
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
        LayoutOptionData<?> optionData = LayoutDataService.getInstance().getOptionData((String) id);
        return layoutConfig.isSet(optionData, layoutContext);
    }

    /**
     * {@inheritDoc}
     */
    public void resetPropertyValue(final Object id) {
        final LayoutOptionData<?> optionData = LayoutDataService.getInstance()
                .getOptionData((String) id);
        Runnable modelChange = new Runnable() {
            public void run() {
                layoutConfig.setValue(optionData, layoutContext, null);
            }
        };
        KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.12"));
        
        // if the choice of layout algorithm is affected, refresh the whole layout view
        if (LayoutOptions.ALGORITHM_ID.equals(optionData.getId())) {
            LayoutViewPart layoutView = LayoutViewPart.findView();
            if (layoutView != null) {
                layoutView.refresh();
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
        String bestHint = null;
        int bestLength = 0;
        for (LayoutAlgorithmData layouterData : LayoutDataService.getInstance().getAlgorithmData()) {
            String name = layouterData.getName();
            if (displayedName.startsWith(name) && name.length() > bestLength) {
                bestHint = layouterData.getId();
                bestLength = name.length();
            }
        }
        if (bestHint == null) {
            // look for a matching layout type
            for (LayoutTypeData layoutType : LayoutDataService.getInstance().getTypeData()) {
                String typeId = layoutType.getId();
                String typeName = layoutType.getName();
                if (displayedName.startsWith(typeName) && typeName.length() > bestLength) {
                    bestHint = typeId;
                    bestLength = typeName.length();
                }
            }
        }
        return bestHint;
    }

}
