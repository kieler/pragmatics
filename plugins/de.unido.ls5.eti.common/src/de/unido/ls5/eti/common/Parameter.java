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

import java.util.List;

/**
 * 
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 *
 */
public class Parameter implements ParameterElement{
	private String name;
	private String classIdentifier;
	private String classArgument;
	private boolean required;
    private String value;
	private String defaultValue;
    private String description;

	 private boolean contextExpression;
    

    public Parameter () {
    	name = "";
    	classIdentifier = "";
    	required = false;
    	value = "";
    	defaultValue = "";
    	description = "";
    	classArgument = "";

		contextExpression = false;
    }

    /**
     * @return always false, as Parameter may not contain sub-elements
     */
    public boolean isContainer() {
    	return false;
    }
    
    /**
     * @return always null, as Parameter may not contain sub-elements
     */
    public List<ParameterElement> getElements() {
    	return null;
    }
    
    
    public String getClassArgument() {
    	return classArgument;
    }
    
    public void setClassArgument(String classArgument) {
    	this.classArgument = classArgument;
    }
    
    public String getClassIdentifier() {
		return classIdentifier;
	}
	
	public void setClassIdentifier(String classIdentifier) {
		this.classIdentifier = classIdentifier;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public boolean isContextExpression() {
		return contextExpression;
	}

	public void setContextExpression(boolean contextExpression) {
		this.contextExpression = contextExpression;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Parameter)) {
			return false;
		}
		else {
			Parameter p = (Parameter) obj;
			
			if ( 
				name.equals(p.name) &&
				classIdentifier.equals(p.classIdentifier) &&
				classArgument.equals(p.classArgument) &&
				required == p.required &&
				value.equals(p.value) &&
				defaultValue.equals(p.defaultValue) &&
				description.equals(p.description) &&
				contextExpression == p.contextExpression
			)
				return true;
			else {
				return false;
			}
			
		}
	}






}
