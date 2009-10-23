/******************************************************************************
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The connection between Xtend functions and the KSBasE plug-In. Stores
 * additional information about how the transformation can be executed by the
 * user/workbench.
 * This class is serializable.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public class Transformation implements Serializable {

    private static final long serialVersionUID = 513784171695543063L;
    private String name; // Menu entry name
    private String transformationName; // Xtend method name
    private String icon; // URI to icon
    private String keyboardShortcut; // Assigned keyboard shortcut
    private String[] parameter; //Ordered parameters
    private String transformationId; //Id for this transformation, defined via the ext. point
    
    /**
     * Creates a new Transformation.
     */
    public Transformation(final String name, final String transformation) {
        this.name = name;
        this.transformationName = transformation;
        icon = "";
        keyboardShortcut = "";
        transformationId = "";
        parameter = null;
    }

    /**
     * Sets the name of the transformation used in the menus.
     * @param name 
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the name of the transformation to be executed.
     * The value is unchecked so giving an invalid name here will result in an Xtend error.
     * @param value 
     */
    public final void setTransformationName(final String value) {
        this.transformationName = value;
    }


    /**
     * Sets the iconURI used by the toolbar and the balloon menus.
     * @param uri
     */
    public final void setIcon(final String uri) {
        this.icon = uri;
    }

    /**
     * Gets the name of the transformation.
     * @return The transformation name used by the menus. 
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Returns the Xtend transformation method name.
     * @return The name of this transformation
     */
    public final String getTransformationName() {
        return this.transformationName;
    }

    /**
     * Returns the number of selections this transformation is defined for.
     * @return The number of selections
     */
    public final int getNumSelections() {
        return this.parameter.length;
    }

    /**
     * Returns the icon with relative path.
     * @return A path string
     */
    public final String getIcon() {
        return icon;
    }
    
    /**
     * Gets the list of parameters.
     * @return A list of parameters.
     */
    public final List<String> getParameterList() {
    	ArrayList<String> res = new ArrayList<String>();
    	for (String s: parameter) {
			res.add(s);
		}
    	return res;
    }
    
    /**
     * Gets the list of parameters as an array, may be null if not initialized.
     * @return An array of parameters.
     */
    public final String[] getParameter() {
		return parameter.clone();
	}

    /**
     * @param parameter The parameters for this transformation.
     */
    public final void setParameter(final String[] parameter) {
		this.parameter = parameter.clone();
	}

    /**
     * Returns the Id for this transformation.
     * This is used for menu contributions only, so 
     * it does not need to be set.
     * @return The transformationId
     */
    public final String getTransformationId() {
        return transformationId;
    }

    /**
     * Sets the Id for this transformation.
     * @param transformationId
     */
    public final void setTransformationId(final String transformationId) {
        this.transformationId = transformationId;
    }

    /**
     * This is only a string, it's not validated or checked for conflicts.
     * @return The keyboard shortcut
     */
    public final String getKeyboardShortcut() {
        return keyboardShortcut;
    }

    /**
     * Sets the keyboard shortcut for this transformation.
     * @param keyboardShortcut
     */
    public final void setKeyboardShortcut(final String keyboardShortcut) {
        this.keyboardShortcut = keyboardShortcut;
    }

    /**
     * Serializes this object to the given ObjectOutputStream.
     * @param writer A valid an opened ObjectOutputStream
     */
    public void serialize(final ObjectOutputStream writer) {
        try {
        	assert(writer != null);
            writer.writeObject(this.name);
            writer.writeObject(this.transformationName);
            writer.writeObject(this.parameter);
            writer.writeObject(this.icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Simple hashCode calculations, uses the hash code of 
     * the transformation name and adds the number of selections.
     */
    @Override
    public final int hashCode() {
        return transformationName.hashCode();
    }
    
    /**
     * Two transformations are equal, when they have the same transformation
     * name and the same number of parameters.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof Transformation) {
            return (parameter.length == ((Transformation) obj).getNumSelections())
                    && (transformationName.equals(((Transformation) obj)
                            .getTransformationName()));
        }
        return false;
    }
}
