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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import de.cau.cs.kieler.kiml.layout.klayoutdata.KOption;
import de.cau.cs.kieler.kiml.layout.services.LayoutOptionData;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.ui.layout.KimlUiUtil;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionStyle;
import de.cau.cs.kieler.kiml.ui.layout.layoutoptions.LayoutOptionsPackage;

/**
 * A property source for layout options for GMF diagrams.
 *
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class GmfLayoutPropertySource implements IPropertySource {

    private List<LayoutOptionData> optionDataList;
    
    private IPropertyDescriptor[] propertyDescriptors;
    
    private TransactionalEditingDomain editingDomain;
    
    private View notationView;
    
    private LayoutOptionStyle optionStyle;
    
    private Map<LayoutOptionData, KOption> koptionMap = new HashMap<LayoutOptionData, KOption>();
    
    private Map<LayoutOptionData, Object> defaultValueMap = new HashMap<LayoutOptionData, Object>();
    
    /**
     * Creates a layout property source for the given graphical edit part.
     * 
     * @param editPart a graphical edit part from a GMF diagram
     */
    public GmfLayoutPropertySource(IGraphicalEditPart editPart) {
        optionDataList = new ArrayList<LayoutOptionData>();
        Collection<LayoutOptionData> optionDataCollection = LayoutServices.INSTANCE.getLayoutOptionData();
        for (LayoutOptionData optionData : optionDataCollection) {
            // TODO select appropriate options
            optionDataList.add(optionData);
        }
        notationView = editPart.getNotationView();
        editingDomain = editPart.getEditingDomain();
        optionStyle = (LayoutOptionStyle)notationView.getStyle(LayoutOptionsPackage.eINSTANCE.getLayoutOptionStyle());
        if (optionStyle != null) {
            for (KOption koption : optionStyle.getOptions()) {
                LayoutOptionData optionData = LayoutServices.INSTANCE.getLayoutOptionData(koption.getKey());
                koptionMap.put(optionData, koption);
                Object defaultValue = KimlUiUtil.getValue(koption, optionData);
                defaultValueMap.put(optionData, defaultValue);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        if (propertyDescriptors == null) {
            propertyDescriptors = new IPropertyDescriptor[optionDataList.size()];
            for (int i = 0; i < propertyDescriptors.length; i++) {
                propertyDescriptors[i] = new LayoutPropertyDescriptor(optionDataList.get(i));
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
        if (koption == null)
            return KimlUiUtil.getDefault(optionData);
        else
            return KimlUiUtil.getValue(koption, optionData);
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

}
