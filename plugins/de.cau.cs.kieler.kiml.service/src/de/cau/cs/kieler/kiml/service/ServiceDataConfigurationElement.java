/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kiml.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.emf.common.util.EList;

import de.cau.cs.kieler.kwebs.servicedata.Category;
import de.cau.cs.kieler.kwebs.servicedata.KnownOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutAlgorithm;
import de.cau.cs.kieler.kwebs.servicedata.LayoutOption;
import de.cau.cs.kieler.kwebs.servicedata.LayoutType;
import de.cau.cs.kieler.kwebs.servicedata.RemoteEnum;
import de.cau.cs.kieler.kwebs.servicedata.SupportedDiagram;

/**
 * .
 *
 * @kieler.rating 2011-05-17 red
 * @author swe
 */
public class ServiceDataConfigurationElement implements IConfigurationElement {
   
    /** */
    private String name;
    
    /** */
    private String value;
    
    /** */
    private Map<String, String> attributes
        = new HashMap<String, String>();
    
    /** */
    private List<IConfigurationElement> children
        = new ArrayList<IConfigurationElement>();
    
    /** */
    private Object parent;

    /**
     * 
     * @param thename
     */
    public ServiceDataConfigurationElement(final String thename) {
        name = thename;
    }

    /**
     * 
     * @param thename
     * @param thevalue
     */
    public ServiceDataConfigurationElement(final String thename, final String thevalue) {
        name = thename;
        value = thevalue;
    }
    
    /**
     * 
     * @param thename
     * @param thevalue
     * @param theattributes
     * @param thechildren
     */
    public ServiceDataConfigurationElement(final String thename, final String thevalue, 
        final Map<String, String> theattributes, final List<IConfigurationElement> thechildren) {
        name = thename;
        value = thevalue;
        attributes = theattributes;
        children = thechildren;
    }
    
    /**
     * 
     * @param child
     */
    public void addChild(final IConfigurationElement child) {
        if (child != null && !children.contains(child)) {
            children.add(child);
        }
    }
    
    /**
     * 
     * @param name
     * @param value
     */
    public void addAttribute(final String name, final String value) {
        attributes.put(name, value);
    }
    
    /**
     * 
     * @param category
     * @return
     */
    public static ServiceDataConfigurationElement getCategoryElementFromModel(final Category category) {
        ServiceDataConfigurationElement element = new ServiceDataConfigurationElement(
            ExtensionLayoutDataService.ELEMENT_CATEGORY
        );
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_ID, category.getId());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_NAME, category.getName());
        return element;
    }
    
    /**
     * 
     * @param type
     * @return
     */
    public static ServiceDataConfigurationElement getLayoutTypeElementFromModel(final LayoutType type) {
        ServiceDataConfigurationElement element = new ServiceDataConfigurationElement(
            ExtensionLayoutDataService.ELEMENT_LAYOUT_TYPE
        );
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_ID, type.getId());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_NAME, type.getName());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_DESCRIPTION, type.getDescription());
        return element;
    }

    /**
     * 
     * @param option
     * @return
     */
    public static ServiceDataConfigurationElement getLayoutOptionElementFromModel(final LayoutOption option) {
        ServiceDataConfigurationElement element = new ServiceDataConfigurationElement(
            ExtensionLayoutDataService.ELEMENT_LAYOUT_OPTION
        );
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_ID, option.getId());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_NAME, option.getName());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_TYPE, option.getType());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_DESCRIPTION, option.getDescription());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_DEFAULT, option.getDefault());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_IMPLEMENTATION, option.getImplementation());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_APPLIESTO, option.getAppliesTo());
        RemoteEnum remoteEnum = option.getRemoteEnum();
        if (remoteEnum != null) {
            EList<String> remoteEnumValues = remoteEnum.getValues();
            if (remoteEnumValues != null) {
                String enumValues = "";
                for (String enumValue : remoteEnumValues) {
                    if (enumValue.length() > 0) {
                        enumValues += " ";
                        enumValues += enumValue;
                    }
                }
                element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_ENUMVALUES, enumValues);
            }
        }
        return element;
    }
    
    /** Name of the previewImagePath attribute. */
    private static final String ATTRIBUTE_PREVIEWIMAGEPATH
        = "previewImagePath";

    /** Name of the previewImagePort attribute. */
    //private static final String ATTRIBUTE_PREVIEWIMAGEPORT
    //    = "previewImagePort";
    
    /**
     * 
     * @param algorithm
     * @return
     */
    public static ServiceDataConfigurationElement getLayoutAlgorithmElementFromModel(final LayoutAlgorithm algorithm) {
        ServiceDataConfigurationElement element = new ServiceDataConfigurationElement(
            ExtensionLayoutDataService.ELEMENT_LAYOUT_ALGORITHM
        );
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_ID, algorithm.getId());
        element.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_NAME, algorithm.getName());
        String previewImagePath = algorithm.getPreviewImagePath();
        if (previewImagePath != null) {
            element.addAttribute(ATTRIBUTE_PREVIEWIMAGEPATH, previewImagePath);
        }
/*        
        int previewImagePort = algorithm.getPreviewImagePort();
        element.addAttribute(ATTRIBUTE_PREVIEWIMAGEPORT, new Integer(previewImagePort).toString());
*/        
        LayoutType tmpType = algorithm.getType();
        if (tmpType != null) {
            element.addAttribute(
                ExtensionLayoutDataService.ATTRIBUTE_TYPE, tmpType.getId()
            );
        }
        element.addAttribute(
            ExtensionLayoutDataService.ATTRIBUTE_DESCRIPTION, algorithm.getDescription()
        );
        Category tmpCategory = algorithm.getCategory();
        if (tmpCategory != null) {
            element.addAttribute(
                ExtensionLayoutDataService.ATTRIBUTE_CATEGORY, tmpCategory.getId()
            );
        }
        for (KnownOption option : algorithm.getKnownOptions()) {
            ServiceDataConfigurationElement subElement
                = new ServiceDataConfigurationElement(ExtensionLayoutDataService.ELEMENT_KNOWN_OPTION);
            subElement.addAttribute(
                ExtensionLayoutDataService.ATTRIBUTE_OPTION, option.getOption().getId()
            );
            subElement.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_DEFAULT, option.getDefault());
            element.addChild(subElement);
        }
        for (SupportedDiagram diagram : algorithm.getSupportedDiagrams()) {
            ServiceDataConfigurationElement subElement = new ServiceDataConfigurationElement(
                ExtensionLayoutDataService.ELEMENT_SUPPORTED_DIAGRAM
            );
            subElement.addAttribute(ExtensionLayoutDataService.ATTRIBUTE_TYPE, diagram.getType());
            subElement.addAttribute(
                ExtensionLayoutDataService.ATTRIBUTE_PRIORITY, 
                Integer.toString(diagram.getPriority())
            );
            element.addChild(subElement);
        }
        return element;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object createExecutableExtension(final String propertyName) throws CoreException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getAttribute(final String name) {
        return attributes.get(name);
    }

    /**
     * {@inheritDoc}
     */
    public String getAttributeAsIs(final String name) {
        return getAttribute(name);
    }

    /**
     * {@inheritDoc}
     */
    public String[] getAttributeNames() {
        return attributes.keySet().toArray(new String[attributes.size()]);
    }

    /**
     * {@inheritDoc}
     */
    public IConfigurationElement[] getChildren() {
        return children.toArray(new IConfigurationElement[children.size()]);
    }

    /**
     * {@inheritDoc}
     */
    public IConfigurationElement[] getChildren(final String name) {
        java.util.List<IConfigurationElement> result =
            new java.util.ArrayList<IConfigurationElement>(children.size());
        for (IConfigurationElement next : children) {
            if (next.getName().equals(name)) {
                result.add(next);
            }
        }
        return result.toArray(new IConfigurationElement[result.size()]);
    }

    /**
     * {@inheritDoc}
     */
    public IExtension getDeclaringExtension() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    public String getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public String getValueAsIs() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer(64);

        result.append("ConfigurationElement[");
        result.append(getName());
        result.append(": ");
        result.append(attributes);
        result.append("]");
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    public Object getParent() {
        return parent;
    }

    /**
     * {@inheritDoc}
     */
    public String getNamespace() throws InvalidRegistryObjectException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public String getNamespaceIdentifier() throws InvalidRegistryObjectException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public IContributor getContributor() throws InvalidRegistryObjectException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getAttribute(final String attrName, final String locale) 
        throws InvalidRegistryObjectException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getValue(final String locale) throws InvalidRegistryObjectException {
        return null;
    }
    
}
