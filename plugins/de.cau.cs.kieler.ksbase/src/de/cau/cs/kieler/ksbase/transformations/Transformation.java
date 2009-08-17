package de.cau.cs.kieler.ksbase.transformations;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

public class Transformation implements Serializable {

    private static final long serialVersionUID = -6839991969679808483L;
    private String name; // Menu entry name
    private String transformation; // Xtend command string
    private int visibilityFlags; // Visibility configuration, any combination of visibility Flags from KSBasePlugin
    private int numSelections; // Number of selections;
    private URI iconURI; // URI to icon
    private String[] partConfig; // Parts for which this transformation is
                                 // defined

    /**
     * Creates a new Transformation
     */
    public Transformation(String name, String transformation) {
        this.name = name;
        this.transformation = transformation;
        this.visibilityFlags = KSBasEPlugin.SHOW_MENU | KSBasEPlugin.SHOW_CONTEXT | KSBasEPlugin.SHOW_TOOLBAR | KSBasEPlugin.SHOW_BALLOON;
        numSelections = 1;
        iconURI = URI.create("");
        partConfig = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransformation(String value) {
        this.transformation = value;
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

    public String getTransformation() {
        return this.transformation;
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
    
    public String getPartConfgList() {
        if ( partConfig == null)
            return "";
        
        String res = "";
        for ( int i = 0 ; i < partConfig.length; ++i) {
            res += partConfig[i];
            if ( i < partConfig.length )
                res += ",";
        }
        return res;
    }

    public void serialize(ObjectOutputStream writer) {
        try {
            writer.writeObject(this.name);
            writer.writeObject(this.transformation);
            writer.writeInt(this.visibilityFlags);
            writer.writeInt(this.numSelections);
            writer.writeObject(this.iconURI);
            writer.writeObject(this.partConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
