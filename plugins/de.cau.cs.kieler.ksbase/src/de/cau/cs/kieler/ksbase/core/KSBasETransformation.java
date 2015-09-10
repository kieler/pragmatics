/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
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

import org.eclipse.core.runtime.Assert;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.m2m.AbstractTransformation;

/**
 * The connection between the transformations and the KSBasE plug-In. Stores additional information
 * about how the transformation can be executed by the user/workbench. This class is instantiated by
 * the {@link EditorTransformationSettings} class to configure the transformations for one editor.
 * 
 * This class may be serialized.
 * 
 * @author mim
 * @kieler.design 2010-01-22 review by msp, skn
 */
public class KSBasETransformation extends AbstractTransformation implements Serializable, Cloneable {

    /** Serialization Id. **/
    private static final long serialVersionUID = 513784171695543063L;
    /** Menu entry name. **/
    private String name;
    /** Transformation name. **/
    private String transformation;
    /** URI to icon. **/
    private String icon;
    /** Assigned keyboard shortcut. **/
    private String keyboardShortcut;
    /** ToolTip message of this transformation. **/
    private String toolTip;
    /** Ordered parameters. **/
    private LinkedList<List<String>> parameters;
    /** Id for this transformation. **/
    private String transformationId;
    /** Flag for user defined transformations to control visibility. **/
    private boolean visible;
    /** Additional validation transformation. **/
    private String validation;

    private Object transformationClass;
    
    private String commandId;
    
    /**
     * Creates a new Transformation.
     * 
     * @param tName
     *            The name of this transformation which is displayed in the menu.
     * @param tTransName
     *            The name of the transformation to execute.
     */
    public KSBasETransformation(final String tName, final String tTransName) {
        name = tName;
        transformation = tTransName;
        icon = "";
        keyboardShortcut = "";
        transformationId = "";
        visible = true;
        validation = "";
        toolTip = "";
        parameters = new LinkedList<List<String>>();
    }

    /**
     * Copy constructor.
     * 
     * @param t
     *            Transformation which should be copied
     */
    public KSBasETransformation(final KSBasETransformation t) {
        this.name = t.name;
        this.transformation = t.transformation;
        this.icon = t.icon;
        this.transformationId = t.transformationId;
        this.keyboardShortcut = t.keyboardShortcut;
        this.visible = t.visible;
        this.toolTip = t.toolTip;
        this.validation = t.validation;
        this.parameters = new LinkedList<List<String>>(t.parameters);
        this.transformationClass = t.transformationClass;
        this.commandId = t.commandId;

    }

    /**
     * Clone operation.
     * 
     * @return A cloned transformation
     */
    public final KSBasETransformation clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            KSBasEPlugin.getDefault().logError("Error while cloning a transformation.");
        }
        KSBasETransformation t = new KSBasETransformation(this);
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
     * Sets the name of the transformation to be executed. The value is unchecked so giving an
     * invalid name here may result in an error when executing the transfomation.
     * 
     * @param value
     *            The name of the transformation to execute
     */
    @Override
    public void setTransformation(final String value) {
        if (value != null) {
            this.transformation = value;
        }
    }

    /**
     * Sets the iconURI used by the menu contributions.
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
        return this.name;
    }

    /**
     * Returns the transformation name.
     * 
     * @return The name of this transformation
     */
    public final String getTransformation() {
        return this.transformation;
    }

    /**
     * Returns the number of selections this transformation is defined for.
     * 
     * @return The number of selections
     */
    public final int getNumSelections() {
        Assert.isNotNull(parameters);
        return this.parameters.size();
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
     * Returns the toolTip message.
     * 
     * @return A message string
     */
    public String getToolTip() {
        return toolTip;
    }

    /**
     * Sets the toolTip message.
     * 
     * @param message
     *            the message
     */
    public void setToolTip(final String message) {
        this.toolTip = message;
    }

    /**
     * Gets the list of parameters.
     * 
     * @return A list of parameters.
     */
    public final List<List<String>> getParameterList() {
        Assert.isNotNull(this.parameters);
        // never return the references!
        return new LinkedList<List<String>>(parameters);
    }

    /**
     * Sets parameters list.
     * 
     * @param params
     *            the list of list of parameters
     */
    public void setParameters(final List<List<String>> params) {
        this.parameters = new LinkedList<List<String>>(params);
    }

    /**
     * Sets the parameters for this transformation.
     * 
     * @param params
     *            a List of parameters
     */
    public final void addParameters(final List<String> params) {
        if (params != null) {
            this.parameters.add(params);
        }
    }

    /**
     * Returns the Id for this transformation.
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
        if (id != null) {
            this.transformationId = id;
        }
    }

    /**
     * Returns the keyboard shortcut. This is only a string, it's not validated or checked for
     * conflicts.
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
        if (shortcut != null) {
            this.keyboardShortcut = shortcut;
        }
    }

    /**
     * Gets the transformation that is used to validate a selection.
     * 
     * @return A transformation name
     */
    public String getValidation() {
        return validation;
    }

    /**
     * Gets the transformation class of this transformation.
     * @return the transformation class this this transformation belongs to. May be null 
     * if this is no xtend2 transformation.
     */
    public Object getTransformationClass() {
        return this.transformationClass;
    }
    
    /**
     * Sets the transformation class for this transformation.
     * @param transformationClass the transformation class this this transformation belongs to.
     */
    public void setTransformationClass(final Object transformationClass) {
        this.transformationClass = transformationClass;
    }
    
    /**
     * The id of this transformations command.
     * @return the id of this transformations command.
     */
    public String getCommandId() {
        return this.commandId;
    }
    
    /**
     * Sets the id of this transformations command.
     * @param id the id of this transformations command.
     */
    public void setCommandId(final String id) {
        this.commandId = id;
    }
    
    /**
     * Sets the transformation that is used for validation.
     * 
     * @param transformationName
     *            A transformation name
     */
    public void setValidation(final String transformationName) {
        this.validation = transformationName;
    }

    /**
     * Checks if the transformation should be displayed in the menu. Only used for user defined
     * editors.
     * 
     * @return True if the transformation should be displayed
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     * Sets the transformation visibility. Only used for user defined editors.
     * 
     * @param isVisible
     *            New visibility status
     */
    public void setVisible(final Boolean isVisible) {
        this.visible = isVisible;
    }

    /**
     * Serializes this object to the given ObjectOutputStream.
     * 
     * @param writer
     *            A valid an opened ObjectOutputStream
     */
    public void serialize(final ObjectOutputStream writer) {
        try {
            Assert.isNotNull(writer);
            writer.writeObject(this.name);
            writer.writeObject(this.transformation);
            writer.writeObject(this.parameters);
            writer.writeObject(this.icon);
            writer.writeObject(this.visible);
            writer.writeObject(this.validation);
        } catch (IOException e) {
            KSBasEPlugin.getDefault().logError("Transformation settings could not be saved.");
        }
    }

    /**
     * Simple hashCode calculations, uses the hash code of the transformation name and adds the
     * number of selections.
     * 
     * @return The hashCode value
     */
    @Override
    public final int hashCode() {
        return transformation.hashCode();
    }

    /**
     * Two transformations are equal, when they have the same transformation name and the same
     * number of parameters.
     * 
     * @param obj
     *            The object to compare with
     * @return True if the given object is a transformation and has the same name and number of
     *         parameters
     */
    @Override
    public final boolean equals(final Object obj) {
        if (obj != null && obj instanceof KSBasETransformation) {
            return (getNumSelections() == ((KSBasETransformation) obj).getNumSelections())
                    && (transformation.equals(((KSBasETransformation) obj).getTransformation()));
        }
        return false;
    }

    @Override
    public String toString() {
        return transformation + " " + "@" + transformationId + "\n";
    }
}
