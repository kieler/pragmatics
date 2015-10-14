/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui;

import java.util.EnumSet;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.cau.cs.kieler.kiml.LayoutAlgorithmData;
import de.cau.cs.kieler.kiml.LayoutMetaDataService;
import de.cau.cs.kieler.kiml.LayoutOptionData;
import de.cau.cs.kieler.kiml.LayoutTypeData;
import de.cau.cs.kieler.kiml.options.LayoutOptions;

/**
 * A label provider for values of layout options. An instance of this label provider must be
 * associated with a specific option in order to correctly translate the passed values.
 *
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 */
public class LayoutOptionLabelProvider extends LabelProvider {
    
    /** the layout option data instance associated with this label provider. */
    private final LayoutOptionData optionData;

    /**
     * Create a label provider for the given layout option.
     * 
     * @param optionData a layout option
     */
    public LayoutOptionLabelProvider(final LayoutOptionData optionData) {
        this.optionData = optionData;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage(final Object element) {
        KimlUiPlugin.Images images = KimlUiPlugin.getDefault().getImages();
        switch (optionData.getType()) {
        case OBJECT:
        case STRING:
            return images.getPropText();
        case BOOLEAN:
            boolean istrue = true;
            if (element instanceof Boolean) {
                istrue = (Boolean) element;
            } else if (element instanceof Integer) {
                istrue = (Integer) element == 1;
            }
            if (istrue) {
                return images.getPropTrue();
            } else {
                return images.getPropFalse();
            }
        case ENUM:
        case ENUMSET:
            return images.getPropChoice();
        case INT:
            return images.getPropInt();
        case FLOAT:
            return images.getPropFloat();
        default:
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("rawtypes")
    public String getText(final Object element) {
        switch (optionData.getType()) {
        case STRING:
            LayoutMetaDataService layoutServices = LayoutMetaDataService.getInstance();
            if (LayoutOptions.ALGORITHM.equals(optionData)) {
                String layoutHint = (String) element;
                LayoutTypeData layoutType = layoutServices.getTypeData(layoutHint);
                if (layoutType != null) {
                    return layoutType.toString();
                }
                LayoutAlgorithmData layouterData = layoutServices.getAlgorithmData(layoutHint);
                if (layouterData != null) {
                    return layouterData.toString();
                }
                return Messages.getString("kiml.ui.8");
            }
            break;
        case BOOLEAN:
            if (element instanceof Boolean) {
                return ((Boolean) element).toString();
            }
            // fall through so the same method as for enums is applied
        case ENUM:
            if (element instanceof Integer) {
                return optionData.getChoices()[(Integer) element];
            }
            break;
        case ENUMSET:
            if (element instanceof String) {
                return (String) element;
            } else if (element instanceof String[]) {
                String[] arr = (String[]) element;
                if (arr.length == 0) {
                    return "";
                } else {
                    StringBuilder builder = new StringBuilder();
                    
                    for (String s : arr) {
                        builder.append(", ").append(s);
                    }
                    
                    return builder.substring(2);
                }
            } else if (element instanceof EnumSet) {
                EnumSet set = (EnumSet) element;
                if (set.isEmpty()) {
                    return "";
                }
                
                StringBuilder builder = new StringBuilder();
                for (Object o : set) {
                    builder.append(", " + ((Enum) o).name());
                }
                return builder.substring(2);
            }
        default:
            return element.toString();
        }
        
        return element.toString();
    }
    
}
