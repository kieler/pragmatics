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

import de.cau.cs.kieler.kiml.ILayoutConfig;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutServices;
import de.cau.cs.kieler.kiml.LayoutTypeData;
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
            List<LayoutOptionData<?>> optionData = layoutConfig.getOptionData();
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
        EclipseLayoutServices layoutServices = EclipseLayoutServices.getInstance();
        LayoutOptionData<?> optionData = layoutServices.getOptionData((String) id);
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
                default:
                    value = optionData.parseValue((String) value);
                }
                layoutConfig.setProperty(optionData, value);
                if (LayoutOptions.LAYOUTER_HINT_ID.equals(optionData.getId())) {
                    LayoutViewPart layoutView = LayoutViewPart.findView();
                    if (layoutView != null) {
                        layoutView.refresh();
                    }
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
        LayoutOptionData<?> optionData = LayoutServices.getInstance().getOptionData((String) id);
        return !layoutConfig.isDefault(optionData);
    }

    /**
     * {@inheritDoc}
     */
    public void resetPropertyValue(final Object id) {
        final LayoutOptionData<?> optionData = LayoutServices.getInstance()
                .getOptionData((String) id);
        Runnable modelChange = new Runnable() {
            public void run() {
                layoutConfig.setProperty(optionData, null);
            }
        };
        KimlUiUtil.runModelChange(modelChange, editingDomain, Messages.getString("kiml.ui.12"));
        LayoutViewPart layoutView = LayoutViewPart.findView();
        if (layoutView != null) {
            layoutView.refresh();
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
        for (LayoutAlgorithmData layouterData : LayoutServices.getInstance().getAlgorithmData()) {
            String name = layouterData.getName();
            if (displayedName.startsWith(name) && name.length() > bestLength) {
                bestHint = layouterData.getId();
                bestLength = name.length();
            }
        }
        if (bestHint == null) {
            // look for a matching layout type
            for (LayoutTypeData layoutType : LayoutServices.getInstance().getTypeData()) {
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
