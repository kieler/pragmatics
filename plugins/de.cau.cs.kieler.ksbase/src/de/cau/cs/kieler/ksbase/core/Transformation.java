/**
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
public class Transformation implements Serializable,Cloneable {

    /** Serialization Id.  **/
    private static final long serialVersionUID = 513784171695543063L;
    /**  Menu entry name. **/
    private String name;
    /** Xtend method name. **/
    private String transformationName;
    /** URI to icon. **/
    private String icon;
    /** Assigned keyboard shortcut. **/
    private String keyboardShortcut; 
    /** Ordered parameters. **/
    private LinkedList<String> parameter;
    /** Id for this transformation. **/
    private String transformationId;

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
        parameter = new LinkedList<String>();
    }

    /**
     * Copy constructor.
     * @param t Transformation which shall be copied
     */
    public Transformation(final Transformation t) {
        this.name = t.name;
        this.transformationName = t.transformationName;
        this.icon = t.icon;
        this.transformationId = t.transformationId;
        this.keyboardShortcut = t.keyboardShortcut;
        this.parameter = new LinkedList<String>(t.parameter);
        
    }
    
    /**
     * Clone operation.
     * @return A cloned transformation
     */
    public final Transformation clone() {
        Transformation t = new Transformation(this);
        return t;
    }
    /**
     * Sets the name of the transformation used in the menus.
     * 
     * @param value
     *            The name for this transformation
     */
    public final void setName(final String value) {
        if (value != null) {
            this.name = value;
        }
    }

    /**
     * Sets the name of the transformation to be executed. The value is
     * unchecked so giving an invalid name here will result in an Xtend error.
     * 
     * @param value
     *            The name of the Xtend transformation to execute
     */
    public final void setTransformationName(final String value) {
        if (value != null) {
            this.transformationName = value;
        }
    }

    /**
     * Sets the iconURI used by the toolbar and the balloon menus.
     * 
     * @param iconUri
     *            The URI to the icon to use for this transformation.
     */
    public final void setIcon(final String iconUri) {
        if (iconUri != null) {
            this.icon = iconUri;
        }
    }

    /**
     * Gets the name of the transformation.
     * 
     * @return The transformation name used by the menus.
     */
    public final String getName() {
        assert (this.name != null);
        return this.name;
    }

    /**
     * Returns the Xtend transformation method name.
     * 
     * @return The name of this transformation
     */
    public final String getTransformationName() {
        assert (this.transformationName != null);
        return this.transformationName;
    }

    /**
     * Returns the number of selections this transformation is defined for.
     * 
     * @return The number of selections
     */
    public final int getNumSelections() {
        assert (this.parameter != null);
        return this.parameter.size();
    }

    /**
     * Returns the icon with relative path.
     * 
     * @return A path string
     */
    public final String getIcon() {
        assert (this.icon != null);
        return icon;
    }

    /**
     * Gets the list of parameters.
     * 
     * @return A list of parameters.
     */
    public final List<String> getParameterList() {
        assert (this.parameter != null);
        //never return the references!
        return new LinkedList<String>(parameter);
    }

    /**
     * Gets the list of parameters as an array, may be null if not initialized.
     * 
     * @return An array of parameters.
     */
    public final String[] getParameter() {
        assert (this.parameter != null);
        return parameter.toArray(new String[parameter.size()]);
    }

    /**
     * @param param
     *            The parameters for this transformation.
     */
    public final void setParameter(final String[] param) {
        if (param != null && param.length > 0) {
            this.parameter.clear();
            for (String para : param) {
                this.parameter.add(para);
            }
        }
    }

    /**
     * Returns the Id for this transformation. This is used for menu
     * contributions only, so it does not need to be set.
     * 
     * @return The transformationId
     */
    public final String getTransformationId() {
        assert (transformationId != null);
        return transformationId;
    }

    /**
     * Sets the Id for this transformation.
     * 
     * @param id
     *            The new Id for this transformation
     */
    public final void setTransformationId(final String id) {
        if (id != null) {
            this.transformationId = id;
        }
    }

    /**
     * This is only a string, it's not validated or checked for conflicts.
     * 
     * @return The keyboard shortcut
     */
    public final String getKeyboardShortcut() {
        assert (this.keyboardShortcut != null);
        return keyboardShortcut;
    }

    /**
     * Sets the keyboard shortcut for this transformation.
     * 
     * @param shortcut
     *            The new shortcut assigned to this transformation
     */
    public final void setKeyboardShortcut(final String shortcut) {
        if (shortcut != null) {
            this.keyboardShortcut = shortcut;
        }
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
        assert (this.transformationName != null);
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
        if (obj != null && obj instanceof Transformation) {
            return (getNumSelections() == ((Transformation) obj)
                    .getNumSelections())
                    && (transformationName.equals(((Transformation) obj)
                            .getTransformationName()));
        }
        return false;
    }
}
