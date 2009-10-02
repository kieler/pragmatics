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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * The connection between Xtend functions and the KSBasE plug-In. Stores
 * additional information about how the transformation can be executed by the
 * user/workbench.
 * This class is serializable.
 * 
 * @author Michael Matzen
 * 
 */
public class Transformation implements Serializable {

    private static final long serialVersionUID = 513784171695543063L;
    private String name; // Menu entry name
    private String transformationName; // Xtend method name
    private int numSelections; // Number of selections;
    private String icon; // URI to icon
    private String keyboardShortcut; // Assigned keyboard shortcut
    private Object[] partConfig; // Parts for which this transformation is defined
    private String transformationID; //Id for this transformation, defined via the ext. point
    /**
     * Creates a new Transformation
     */
    public Transformation(String name, String transformation) {
        this.name = name;
        this.transformationName = transformation;
        numSelections = 1;
        icon = "";
        keyboardShortcut = "";
        transformationID = "";
        partConfig = null;
    }

    /**
     * Sets the name of the transformation used in the menus
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name of the transformation to be executed.
     * The value is unchecked so giving an invalid name here will result in an Xtend error.
     * @param value 
     */
    public void setTransformationName(String value) {
        this.transformationName = value;
    }

    /**
     * Sets the number of diagram elements that have to be selected in order to 
     * execute this transformation
     * @param value
     */
    public void setNumSelections(int value) {
        this.numSelections = value;
    }

    /**
     * Sets the iconURI used by the toolbar and the balloon menus
     * @param uri
     */
    public void setIcon(String uri) {
        this.icon = uri;
    }

    /**
     * Returns the transformation name used by the menus
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Xtend transformation method name
     * @return
     */
    public String getTransformationName() {
        return this.transformationName;
    }

    /**
     * Returns the number of selections this transformation is defined for
     * @return
     */
    public int getNumSelections() {
        return this.numSelections;
    }

    /**
     * Returns the icon URI as a String
     * Simply calls iconURI.toString
     * @return
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets the diagram parts this transformation is defined for.
     * The parts are fqn of the diagram element classes 
     * @param parts
     */
    public void setPartConfig(Object[] parts) {
        this.partConfig = parts.clone();
    }
    
    /**
     * Returns the array of diagram parts this transformation is defined for
     * @return
     */
    public Object[] getPartConfig() {
        return this.partConfig.clone();
    }

    /**
     * Returns the list of diagram parts this transformation is defined for 
     * @return
     */
    public String getPartConfigList() {
        if (partConfig == null)
            return "";

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < partConfig.length; ++i) {
            result.append(partConfig[i]);
            if (i < partConfig.length - 1)
            	result.append(",");
                
        }
        return result.toString();
    }

    /**
     * Returns the Id for this transformation.
     * This is used for menu contributions only, so 
     * it does not need to be set.
     * @return
     */
    public String getTransformationID() {
        return transformationID;
    }

    /**
     * Sets the Id for this transformation
     * @param transformationID
     */
    public void setTransformationID(String transformationID) {
        this.transformationID = transformationID;
    }

    /**
     * Returns the keyboard shortcut.
     * This is only a string, it's not validated or checked for conflicts.
     * @return
     */
    public String getKeyboardShortcut() {
        return keyboardShortcut;
    }

    /**
     * Sets the keyboard shortcut for this transformation.
     * @param keyboardShortcut
     */
    public void setKeyboardShortcut(String keyboardShortcut) {
        this.keyboardShortcut = keyboardShortcut;
    }

    /**
     * Serializes this object to the given ObjectOutputStream
     * @param writer A valid an opened ObjectOutputStream
     */
    public void serialize(ObjectOutputStream writer) {
        try {
            writer.writeObject(this.name);
            writer.writeObject(this.transformationName);
            writer.writeInt(this.numSelections);
            writer.writeObject(this.icon);
            writer.writeObject(this.partConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Simple hashCode calculations, uses the hash code of 
     * the transformation name and adds the number of selections
     */
    @Override
    public int hashCode() {
        return transformationName.hashCode()+numSelections;
    }
    
    /**
     * Two transformations are equal, when they have the same transformation
     * name and the same number of parameters
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Transformation) {
            return (numSelections == ((Transformation) obj).getNumSelections())
                    && (transformationName.equals(((Transformation) obj)
                            .getTransformationName()));
        }
        return false;
    }
}
