package de.cau.cs.kieler.ksbase.transformations;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.antlr.runtime.TokenStream;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.internal.xtend.xtend.parser.XtendParser;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

public class EditorTransformationSettings implements Serializable {
    
    private static final long serialVersionUID = -7706521956908478893L;
    private String modelURI;
    private String menuName;
    private String menuLocation;
    private String toolbarLocation;
    private int visibilityFlags;
    private boolean performAutoLayout;
    private URI defaultIconURI;
    private String editor;
    private String extFile;
    private LinkedList<Transformation> transformations;
    
    public EditorTransformationSettings(String name) {
        this.editor = name;
        this.visibilityFlags = KSBasEPlugin.SHOW_MENU | KSBasEPlugin.SHOW_CONTEXT | KSBasEPlugin.SHOW_TOOLBAR | KSBasEPlugin.SHOW_BALLOON;
        this.modelURI = "";
        this.menuName = "KIELER";
        this.menuLocation = "menu:org.eclipse.ui.main.menu?after=additions";
        this.toolbarLocation = "menu:org.eclipse.ui.main.menu?after=additions";
        this.defaultIconURI = URI.create("");
        this.extFile = "";
        this.transformations = new LinkedList<Transformation>();
        Transformation tmp = new Transformation("Test", "Void setState");
        
        this.transformations.add(tmp);
        this.performAutoLayout = true;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
    
    public String getEditor() {
        return editor;
    }
    
    public String getModelURI() {
        return modelURI;
    }

    public void setModelURI(String modelURI) {
        this.modelURI = modelURI;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getVisibility() {
        return visibilityFlags;
    }

    public void setVisibilityFlags(int flags) {
        this.visibilityFlags = flags;
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
    
    public URI getDefaultIconURI() {
        return defaultIconURI;
    }

    public void setDefaultIconURI(URI defaultIconURI) {
        this.defaultIconURI = defaultIconURI;
    }

    public LinkedList<Transformation> getTransformations() {
        return transformations;
    }

    public void setTransformations(LinkedList<Transformation> transformations) {
        this.transformations = transformations;
    }
    
    public void addTransformation(Transformation t) {
        this.transformations.add(t);
    }
    
    public void removeTransformation(int index) {
        this.transformations.remove(index);
    }
    
    public void modifyTransformation(Transformation oldVal, Transformation newVal) {
        if (this.transformations.contains(oldVal)) 
            transformations.remove(oldVal);
        transformations.add(newVal);
    }

    public String getMenuLocation() {
        return menuLocation;
    }

    public void setMenuLocation(String menuLocation) {
        this.menuLocation = menuLocation;
    }

    public String getToolbarLocation() {
        return toolbarLocation;
    }

    public void setToolbarLocation(String toolbarLocation) {
        this.toolbarLocation = toolbarLocation;
    }

	public boolean isPerformAutoLayout() {
		return performAutoLayout;
	}

	public void setPerformAutoLayout(boolean performAutoLayout) {
		this.performAutoLayout = performAutoLayout;
	}

	public String getExtFile() {
		return extFile;
	}

	public void setExtFile(String extFile) {
		this.extFile = extFile;
	}
    
	public void parseTransformationsFromFile() {
		IPath path = new Path(extFile);
		
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);

		if (!file.exists()) {
			return;
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(file.getContents()));
			while (reader.ready()) {
				String in = reader.readLine();
				//Ugly isn't it? Parsing xtend by hand, assuming we get a correct file
				String[] token = in.split(" ");
				if ( token != null && token.length > 0) {
					if ( token[0].equalsIgnoreCase("import") ) {
						//handle import statement
					}
					else if ( token[0].equalsIgnoreCase("void")) {
						String methodName = token[1].substring(0,token[1].indexOf('('));
						String[] params = token[1].substring(token[1].indexOf('(')+1, token[1].indexOf('(')).split(",");
						
						//String params = token[1].split(regex,1)
						//Transformation tnew = new Transformation(methodName, methodName);
					}
				}
			}
		} catch (CoreException e) {
			//ignore
		} catch (IOException e) {
			//ignore
		} catch (IndexOutOfBoundsException e) {
			//ignore
		}
	}
}
