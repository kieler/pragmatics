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
/*
 * Created on 23.12.2004
 */
package de.unido.ls5.eti.common;

import java.util.List;
import java.util.Vector;

/**
 * @author Stefan Naujokat &lt;stefan.naujokat@cs.uni-dortmund.de&gt;
 */
public class ParameterArray implements ParameterElement{
	
	/**
	 * the contained parameter elements. may be {@link Parameter} or
	 * {@link ParameterUnion}
	 */
	private List<ParameterElement>arrayElements;
	
	
	/**
	 * The class which all elements of this array must be compatible to
	 */
	private String classIdentifier;
	
	/**
	 * Constructor that initializes the parameter array with empty elements
	 * and no class identifier
	 */
	public ParameterArray() {
		arrayElements = new Vector<ParameterElement>();
		classIdentifier = "";
	}
	
	/**
	 * adds a normal parameter to the list of contained parameters
	 * @param p the parameter to add
	 */
	public void addEtiParameter (Parameter p) {
		arrayElements.add(p);
	}
	
	/**
	 * adds a union of parameters to the list of contained parameters
	 * @param u the union to add
	 */
	public void addEtiParameterUnion (ParameterUnion u) {
		arrayElements.add(u);
	}

	
	/**
	 * @deprecated use {@link #getElements()} instead
	 * @return the contained elements
	 */
	@Deprecated
	public List<ParameterElement> getArrayElements() {
		return arrayElements;
	}
	

	/**
	 * @return The class name which all elements of this array must 
	 * be compatible to
	 */
	public String getClassIdentifier() {
		return classIdentifier;
	}


	/** 
	 * Sets the class name which all elements of this array must 
	 * be compatible to
	 * 
	 * @param classIdentifier the class name to set
	 */
	public void setClassIdentifier(String classIdentifier) {
		this.classIdentifier = classIdentifier;
	}

	/**
	 * @return always {@code true}, because ParameterArray is a ParameterElement
	 * that contains more parameters.
	 * 
	 * @see ParameterElement#isContainer()
	 */
	public boolean isContainer() {
		return true;
	}

	/**
	 * @return the contained parameter elements
	 * @see ParameterElement#getElements()
	 */
	public List<ParameterElement> getElements() {
		return arrayElements;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ParameterArray)) {
			return false;
		}
		else {
			ParameterArray p = (ParameterArray) obj;
			
			if ( 
				classIdentifier.equals(p.classIdentifier) &&
				arrayElements.equals(p.arrayElements)
			)
				return true;
			else {
				return false;
			}
			
		}
	}
}
