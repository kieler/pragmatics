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
import java.net.URI;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * The connection between Xtend functions and the KSBasE plugin Stores
 * additional information about how the transformation can be executed by the
 * user/workbench
 * 
 * @author Michael Matzen
 * 
 */
public class Transformation implements Serializable {

    private static final long serialVersionUID = -6839991969679808483L;
    private String name; // Menu entry name
    private String transformationName; // Xtend method name
    private int visibilityFlags; // Visibility configuration, any combination of
                                 // visibility Flags from KSBasePlugin
    private int numSelections; // Number of selections;
    private URI iconURI; // URI to icon
    private String keyboardShortcut; // Assigned keyboard shortcut
    private String[] partConfig; // Parts for which this transformation is

    // defined

    /**
     * Creates a new Transformation
     */
    public Transformation(String name, String transformation) {
        this.name = name;
        this.transformationName = transformation;
        this.visibilityFlags = KSBasEPlugin.SHOW_MENU
                | KSBasEPlugin.SHOW_CONTEXT | KSBasEPlugin.SHOW_TOOLBAR
                | KSBasEPlugin.SHOW_BALLOON;
        numSelections = 1;
        iconURI = URI.create("");
        keyboardShortcut = "";
        partConfig = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransformationName(String value) {
        this.transformationName = value;
    }

    public void setVisibility(int value) {
        this.visibilityFlags = value;
    }

    public void setNumSelections(int value) {
        this.numSelections = value;
    }

    public void setIconURI(URI uri) {
        this.iconURI = uri;
    }

    public String getName() {
        return this.name;
    }

    public String getTransformationName() {
        return this.transformationName;
    }

    public boolean isShownInMenu() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_MENU) == KSBasEPlugin.SHOW_MENU;
    }

    public boolean isShownIToolbar() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_TOOLBAR) == KSBasEPlugin.SHOW_TOOLBAR;
    }

    public boolean isShownInContext() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_CONTEXT) == KSBasEPlugin.SHOW_CONTEXT;
    }

    public boolean isShownInBalloon() {
        return (this.visibilityFlags & KSBasEPlugin.SHOW_BALLOON) == KSBasEPlugin.SHOW_BALLOON;
    }

    public int getVisiblity() {
        return this.visibilityFlags;
    }

    public int getNumSelections() {
        return this.numSelections;
    }

    public String getIconString() {
        if (iconURI == null)
            return "";
        else
            return iconURI.toString();
    }

    public URI getIconURI() {
        return this.iconURI;
    }

    public void setPartConfig(String[] parts) {
        this.partConfig = parts;
    }

    public String[] getPartConfig() {
        return this.partConfig;
    }

    public String getPartConfigList() {
        if (partConfig == null)
            return "";

        String res = "";
        for (int i = 0; i < partConfig.length; ++i) {
            res += partConfig[i];
            if (i < partConfig.length - 1)
                res += ",";
        }
        return res;
    }

    public String getKeyboardShortcut() {
        return keyboardShortcut;
    }

    public void setKeyboardShortcut(String keyboardShortcut) {
        this.keyboardShortcut = keyboardShortcut;
    }

    public void serialize(ObjectOutputStream writer) {
        try {
            writer.writeObject(this.name);
            writer.writeObject(this.transformationName);
            writer.writeInt(this.visibilityFlags);
            writer.writeInt(this.numSelections);
            writer.writeObject(this.iconURI);
            writer.writeObject(this.partConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
