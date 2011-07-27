/**
 * Java Electronic Tool Integration - jETI
 * Copyright (C) 2004-2011 Chair for Programming Systems, TU Dortmund
 *
 * This file is part of jETI.
 *
 * jETI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * jETI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with jETI. If not, see <http://www.gnu.org/licenses/>.
 */
package de.unido.ls5.eti.common;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * This is a bean class for the &lt;tool&gt;....&lt;/tool&gt; block of the tool
 * descriptor file (tools.xml) with some extra fields (regarding the SIB icon)
 * added for convenience.
 * 
 * @author Stefan Naujokat
 * @author David Karla
 *
 */
public class Tool implements ParameterElement{
	
    private static String DEFAULT_ICON = 
    	  "iVBORw0KGgoAAAANSUhEUgAAACQAAAAkCAMAAADW3miqAAADAFBMVEX//////+b/99739973797v"
    	+ "79bm5ubm5tbm5s7m3t7e3sXe1sXW1tbW1sXWzr3Ozr3OxbXFxbXFva21tbWlpZylnJycnJycnJSU"
		+ "lJSUlIyMjIyMjISMhHuEhISEe3t7e3t7e3Nzc3Nza2tra2tra2NjWloAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAB4dh7iAAABAHRSTlMA////////////////////////////////////////////"
		+ "////////////////////////////////////////////////////////////////////////////"
		+ "////////////////////////////////////////////////////////////////////////////"
		+ "////////////////////////////////////////////////////////////////////////////"
		+ "////////////////////////////////////////////////////////////////////Cpf0PAAA"
		+ "BT9JREFUeNoBNAXL+gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMExUYGBgYGBgYGBgYGBgYGBgY"
		+ "FRMMAAAAAAAAAAAAAAAAAAkVHSMlJSUlJSUlJCUlJSUlJSUkJSUjHhUJAAAAAAAAAAAAAAwaIxwR"
		+ "BQMCAgICAgICAgICAgICAgMFDxsjGgwAAAAAAAAAAAkaIxIDAgICAgICAgICAgICAgICAgICAgIC"
		+ "ECMaCQAAAAAAAAAVIxECAgICAgICAgICAgICAgICAgICAgICAgIPIxYAAAAAAAAMHxsCAgICAgIC"
		+ "AgICAgICAgICAgICAgICAgICAhcfDAAAAAAAEyMOAgICAgICAgICAgICAgICAgICAgICAgICAgII"
		+ "IxMAAAAAABYlAwICAgICAgICAgICAgICAgICAgICAgICAgICAyIWAAAAAAAYIgICAgICAgICAgIC"
		+ "AgICAgICAgICAgICAgICAgIfGgAAAAAAGCICAgICAwICAgICAgIDAgICAgICAgMCAgICAgICIBoA"
		+ "AAAAABgiAgICAgICAgICAgICAgICAgICAgICAgICAgICAh8aAAAAAAAYIgICAgICAgICAgICAgIC"
		+ "AgICAgICAgICAgICAgIfGgAAAAAAGCICAgICAgICAgICAgICAgICAgICAgICAgICAgICHxoAAAAA"
		+ "ABgiAgICAgICAgICAgICAgICAgICAgICAgICAgICAh8aAAAAAAAYIgICAgICAgICAgICAgICAgIC"
		+ "AgICAgICAgICAgIfGgAAAAAAGCICAgICAgICAgICAgICAgICAgICAgICAgICAgICHxoAAAAAABgi"
		+ "AgICAgIIEAUCEBAQEBAQBQoQEBAQEBAEDRACAh8aAAAAAAAYIgICAgIDFyUFDiUlIyUlJQgcJSUj"
		+ "JSUlBCQgAgIgGgAAAAAAGCICAgICAggIAhIlEAUEBQQCBAUTJRAFBAojFwICHxoAAAAAABgiAgIC"
		+ "AgIlGwIZJQgEBAQCAgICGSUFAgISJRICAh8aAAAAAAAYIgICAgILJRICHyUjJSMlEAICAiAiAgIC"
		+ "FyMKAgIfGgAAAAAAGCICAgICEiUOBSUcERAREQUCAgglFwICAh0kAgICHxoAAAAAABgiAgICAhcl"
		+ "BBAjEgICAgICAgIQIxMCAgQlGQICAh8aAAAAAAAYIgICAgIiHQIUJRQSEhISBAICFCULAgIOJRQC"
		+ "AgIfGgAAAAAAFiUDAgIIIxUCHCMlIyUjJQICAhwjBAICEiUPAgICIhgAAAAAABMjDQICEyUOAgIC"
		+ "AgICAwICAgICAgIDAgICAgICCCQTAAAAAAAMHxkCHiUgAgICAgICAgICAgICAgICAgICAgICAhQh"
		+ "DAAAAAAAABYjDwICAgICAgICAgICAgICAgICAgICAgICAgsjFgAAAAAAAAAJGiMPAgICAgICAgIC"
		+ "AgICAgICAgICAgICAg0jHQkAAAAAAAAAAAwaIxkNAwICAgICAgICAgICAgICAgIDChcjHQwAAAAA"
		+ "AAAAAAAACRYfIyUiIiIiIiIiIiIiIiIiIiIiJSMfFgkAAAAAAAAAAAAAAAAADBMWGBkZGRkZGRkZ"
		+ "GRkZGRkZGBYTDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
		+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIozKuVnxrO8AAAAAElFTkSuQmCC";
	

    private String description = "";
	private String longDescription = "";
    private String name = "";
    private List<String> certificateTypes = new Vector<String>();
    private List<ParameterElement> parameterElements = new Vector<ParameterElement>();
    private String classIdentifier = "";
    private String methodIdentifier = "";
    private boolean active = false;
    private boolean pngIconAvailable = false;
    private boolean svgIconAvailable = false;
    private int svgWidth = 0;
    private int svgHeight = 0;


    
    
    
    /**
     * Constructor with some parameters to predefine field values 
     * 
     * @param description sets the description for the tool
     * @param name sets the name of the tool
     * @param classIdentifier sets the classname of the tool
     * @param methodIdentifier sets the method name of the tool
     * @param active sets wether the tool shall be active (i.e. available to users)
     */
    public Tool(String description, String name, String classIdentifier, String methodIdentifier, boolean active) {
    	super();
        this.description = description;
        this.active = active;
        this.name = name;
        this.classIdentifier = classIdentifier;
        this.methodIdentifier = methodIdentifier;
    }
    
    /**
     * Constructor that initiallizes all field as empty/false
     */
    public Tool() {
    	super();
    }
    
    public String getCertificates (){
    	String certs = "";
    	Iterator<String> iter = this.certificateTypes.iterator();
    	while (iter.hasNext())
    		certs = certs + ":" + iter.next();
    	return certs;
    }
    
    public boolean isFree() {
    	//FIXME: isn't this against the generic approach of certificates? (~naujokat)
    	if (certificateTypes.contains("free"))
    		return true;
    	return false;
    }
    
    public boolean isCount() {
    	//FIXME: isn't this against the generic approach of certificates? (~naujokat)
    	if (certificateTypes.contains("count"))
    		return true;
    	return false;
    }
    
    
    /**
     * @return true if the tool is active (may be executed by users)
     */
    public boolean isActive() {
    	return active;
    }
    
    /**
     * Sets wether the tool shall be active (may be executed by users)
     * 
     * @param a the value to set
     */
    public void setActive(boolean a) {
    	active = a;
    }
    
    
    /**
     * @return the description of this tool
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description of this tool
     * 
     * @param d the description to set
     */
    public void setDescription(String d) {
        description = d;
    }
    
    /**
     * @return the long description of this tool
     */
    public String getLongDescription() {
        return longDescription;
    }
    /**
     * Sets the long description of this tool
     * 
     * @param d the description to set
     */
    public void setLongDescription(String d) {
        longDescription = d;
    }
    
    
    /**
     * @return the name of this tool
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of this tool
     * 
     * @param n the name to set
     */
    public void setName(String n) {
        name = n;                                  
    }
    
       
    /**
     * @return the class name whose method is executed with this tool
     */
    public String getClassIdentifier() {
		return classIdentifier;
	}
	
    
    /** Sets the class name whose method is executed with this tool
     * 
     * @param classIdentifier the class name to set
     */
    public void setClassIdentifier(String classIdentifier) {
		this.classIdentifier = classIdentifier;
	}
	
    
    /**
     * @return The method name that is executed with this tool
     */
    public String getMethodIdentifier() {
		return methodIdentifier;
	}
	

    /**
     * Sets the method name that is executed with this tool
     * 
     * @param methodIdentifier the method name to set
     */
    public void setMethodIdentifier(String methodIdentifier) {
		this.methodIdentifier = methodIdentifier;
	}
	

    /**
     * @return the parameters of this tool. These may be container-parameters
     * that contain other parameters
     * 
     * @see ParameterElement#isContainer()
     * @see ParameterElement#getElements()
     * @deprecated use {@link #getElements()} instead
     */
    @Deprecated
    public List<ParameterElement> getParameterElements() {
		return parameterElements;
	}
	
    
    /**
     * Append a normal parameter to the List of parameterElements of this
     * tool
     * 
     * @param p the parameter to append
     * 
     * @see #getParameterElements()
     */
    public void addEtiParameter(Parameter p) {
    	parameterElements.add(p);
    }
    
    /**
     * Append a union of parameters to the List of parameterElements of this
     * tool
     * 
     * @param u the union to append
     * 
     * @see #getParameterElements()
     */
    public void addEtiParameterUnion(ParameterUnion u) {
    	parameterElements.add(u);
    }

    /**
     * Append an array of parameters to the List of parameterElements of this
     * tool
     * 
     * @param a the parameter array to append
     * 
     * @see #getParameterElements()
     */
    public void addEtiParameterArray(ParameterArray a) {
    	parameterElements.add(a);
    }

    /**
     * Directly sets the complete ths list of parameter elements of this tool
     * (contrary to the addEtiParameter* methods)
     * 
     * @param parameterElements the list of parameter elements to set
     */
     public void setParameterElements(List<ParameterElement> parameterElements) {
		this.parameterElements = parameterElements;
	}

    /**
     * The tool itself is a parameterElement and contains its parameters.
     * Therefore this always returns {@code true}
     * 
     * @return {@code true}
     */
	public boolean isContainer() {
		return true;
	}

    /**
     * @return the parameters of this tool. These may be container-parameters
     * that contain other parameters
     * 
     * @see ParameterElement#isContainer()
     * @see ParameterElement#getElements()
     */
	public List<ParameterElement> getElements() {
		return parameterElements;
	}

	public List<String> getCertificateTypes() {
		return certificateTypes;
	}

	public void setCertificateTypes(List<String> certificateTypes) {
		this.certificateTypes = certificateTypes;
	}
	
	public void addCertificateType (String type) {
		if (!this.certificateTypes.contains(type))
			this.certificateTypes.add(type);
	}
	
	/**
	 * Remove all certificates for this tool
	 *
	 */
	public void clearCertificateTypes () {
		this.certificateTypes.clear();		
	}
	
	/**
	 * @return The width (in px) of the SVG icon for 
	 * this tool (is 0 if not available)
	 */
	public int getSvgWidth() {
		return svgWidth;
	}
	
	/**
	 * @return The height (in px) of the SVG icon for 
	 * this tool (is 0 if not available)
	 */
	public int getSvgHeight() {
		return svgHeight; 
	}
	
	/**
	 * Retrieve the old base64 encoded jETI default icon. This should not
	 * be used anymore, because icon files are now stored as resources in
	 * the classpath (real files).
	 * 
	 * @return The base64 encoded old jETI default icon. 
	 */
	public String getDefaultIcon() {
		return DEFAULT_ICON;
	}

	/**
	 * @return {@code true} if a PNG icon with toolName.png was found
	 * in the icon directory (only used by SPS)
	 */
	public boolean isPngIconAvailable() {
		return pngIconAvailable;
	}

	/**
	 * Set wether a png icon is available for this tool or not
	 * 
	 * @param pngIconAvailable
	 */
	public void setPngIconAvailable(boolean pngIconAvailable) {
		this.pngIconAvailable = pngIconAvailable;
	}

	/**
	 * @return {@code true} if a SVG icon with toolName.svg was found
	 * in the icon directory (only used by SPS)
	 */
	public boolean isSvgIconAvailable() {
		return svgIconAvailable;
	}
	/**
	 * 
	 * Set wether a svg icon is available for this tool or not
	 * 
	 * @param svgIconAvailable 
	 */
	public void setSvgIconAvailable(boolean svgIconAvailable) {
		this.svgIconAvailable = svgIconAvailable;
	}

	/**
	 * Sets the width of the SVG icon for this tool in amount of pixels
	 * 
	 * @param svgWidth the width to set
	 */
	public void setSvgWidth(int svgWidth) {
		this.svgWidth = svgWidth;
	}

	/**
	 * Sets the height of the SVG icon for this tool in amount of pixels
	 * 
	 * @param svgHeight the heigth to set
	 */
	public void setSvgHeight(int svgHeight) {
		this.svgHeight = svgHeight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Tool)) {
			return false;
		}
		else {
			Tool t = (Tool) obj;
			
			if ( 
				description.equals(t.description) &&
				longDescription.equals(t.longDescription) &&
				name.equals(t.name) &&
				parameterElements.equals(t.parameterElements) &&
				classIdentifier.equals(t.classIdentifier) &&
				methodIdentifier.equals(t.methodIdentifier) &&
				active == t.active &&
				pngIconAvailable == t.pngIconAvailable &&
				svgIconAvailable == t.svgIconAvailable &&
				svgWidth == t.svgWidth &&
				svgHeight == t.svgHeight
				
			)
				return true;
			else {
				return false;
			}
			
		}
	}
	
}
