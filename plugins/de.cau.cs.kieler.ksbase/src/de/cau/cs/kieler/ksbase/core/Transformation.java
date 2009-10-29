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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The connection between Xtend functions and the KSBasE plug-In. Stores
 * additional information about how the transformation can be executed by the
 * user/workbench. This class is serializable.
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
    private String[] parameter; // Ordered parameters
    private String transformationId; // Id for this transformation, defined via

    // the ext. point

    /**
     * Creates a new Transformation.
     * 
     * @param tName
     *            The name of this transformation which is displayed in the
     *            menu.
     * @param tXtendName
     *            The name of the Xtend transformation to execute.
     */
    public Transformation(final String tName, final String tXtendName) {
        this.name = tName;
        this.transformationName = tXtendName;
        icon = "";
        keyboardShortcut = "";
        transformationId = "";
        parameter = null;
    }

    /**
     * Sets the name of the transformation used in the menus.
     * 
     * @param value
     *            The name for this transformation
     */
    public final void setName(final String value) {
        this.name = value;
    }

    /**
     * Sets the name of the transformation to be executed. The value is
     * unchecked so giving an invalid name here will result in an Xtend error.
     * 
     * @param value
     *            The name of the Xtend transformation to execute
     */
    public final void setTransformationName(final String value) {
        this.transformationName = value;
    }

    /**
     * Sets the iconURI used by the toolbar and the balloon menus.
     * 
     * @param iconUri
     *            The URI to the icon to use for this transformation.
     */
    public final void setIcon(final String iconUri) {
        this.icon = iconUri;
    }

    /**
     * Gets the name of the transformation.
     * 
     * @return The transformation name used by the menus.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Returns the Xtend transformation method name.
     * 
     * @return The name of this transformation
     */
    public final String getTransformationName() {
        return this.transformationName;
    }

    /**
     * Returns the number of selections this transformation is defined for.
     * 
     * @return The number of selections
     */
    public final int getNumSelections() {
        if (parameter != null) {
            return this.parameter.length;
        } else {
            return 0;
        }
    }

    /**
     * Returns the icon with relative path.
     * 
     * @return A path string
     */
    public final String getIcon() {
        return icon;
    }

    /**
     * Gets the list of parameters.
     * 
     * @return A list of parameters.
     */
    public final List<String> getParameterList() {
        if (parameter != null) {
            ArrayList<String> res = new ArrayList<String>();
            for (String s : parameter) {
                res.add(s);
            }
            return res;
        } else
            return new LinkedList<String>();
    }

    /**
     * Gets the list of parameters as an array, may be null if not initialized.
     * 
     * @return An array of parameters.
     */
    public final String[] getParameter() {
        if (parameter != null) {
            return parameter.clone();
        } else {
            return null;
        }
    }

    /**
     * @param param
     *            The parameters for this transformation.
     */
    public final void setParameter(final String[] param) {
        if (param != null) {
            this.parameter = param.clone();
        }
    }

    /**
     * Returns the Id for this transformation. This is used for menu
     * contributions only, so it does not need to be set.
     * 
     * @return The transformationId
     */
    public final String getTransformationId() {
        return transformationId;
    }

    /**
     * Sets the Id for this transformation.
     * 
     * @param id
     *            The new Id for this transformation
     */
    public final void setTransformationId(final String id) {
        this.transformationId = id;
    }

    /**
     * This is only a string, it's not validated or checked for conflicts.
     * 
     * @return The keyboard shortcut
     */
    public final String getKeyboardShortcut() {
        return keyboardShortcut;
    }

    /**
     * Sets the keyboard shortcut for this transformation.
     * 
     * @param shortcut
     *            The new shortcut assigned to this transformation
     */
    public final void setKeyboardShortcut(final String shortcut) {
        this.keyboardShortcut = shortcut;
    }

    /**
     * Serializes this object to the given ObjectOutputStream.
     * 
     * @param writer
     *            A valid an opened ObjectOutputStream
     */
    public void serialize(final ObjectOutputStream writer) {
        try {
            assert (writer != null);
            writer.writeObject(this.name);
            writer.writeObject(this.transformationName);
            writer.writeObject(this.parameter);
            writer.writeObject(this.icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simple hashCode calculations, uses the hash code of the transformation
     * name and adds the number of selections.
     * 
     * @return The hashCode value
     */
    @Override
    public final int hashCode() {
        return transformationName.hashCode();
    }

    /**
     * Two transformations are equal, when they have the same transformation
     * name and the same number of parameters.
     * 
     * @param obj
     *            The object to compare with
     * @return True if the given object is a transformation and has the same
     *         name and number of parameters
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj instanceof Transformation) {
            return (parameter.length == ((Transformation) obj)
                    .getNumSelections())
                    && (transformationName.equals(((Transformation) obj)
                            .getTransformationName()));
        }
        return false;
    }
}
