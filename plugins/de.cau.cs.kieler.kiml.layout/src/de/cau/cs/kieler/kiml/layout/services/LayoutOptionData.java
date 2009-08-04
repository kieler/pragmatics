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
 */
package de.cau.cs.kieler.kiml.layout.services;

/**
 * Data type used to store information for a layout option.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutOptionData {

	/** type constant for booleans */
	public static final String TYPE_BOOLEAN = "boolean";
	/** type constant for integer numbers */
	public static final String TYPE_INT     = "int";
	/** type constant for strings */
	public static final String TYPE_STRING  = "string";
	/** type constant for floating point numbers */
	public static final String TYPE_FLOAT   = "float";
	
	/** identifier of the layout option */
    public String id;
    /** type of the layout option, must be one of the predefined type constants */
    public String type;
    /** user friendly name of the layout option */
    public String name;
    /** a description to be displayed in the UI */
    public String description;
    
}
