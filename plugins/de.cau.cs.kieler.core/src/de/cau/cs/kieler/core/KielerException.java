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
package de.cau.cs.kieler.core;

/**
 * Exception for error handling in KIELER projects.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KielerException extends Exception {

	private static final long serialVersionUID = 366091287522261006L;
	
	/**
	 * Definition of exception types, may be extended to match more cases.
	 */
	public enum Type {
		/** default value */
		UNDEFINED,
		/** a file system path does not point to a valid target */
		INVALID_PATH,
		/** an external library or program reported failure */
		EXTERNAL_ERROR,
		/** a timeout occurred while waiting for some operation */
		TIMEOUT,
		/** a constructed LP can't be solved because it is infeasible */
		INFEASIBLE_LP,
		/** layout is not applicable, maybe there is no diagram or the wrong editor type... */
		LAYOUT_NOT_APPLICABLE,
	}
	
	private Type type = Type.UNDEFINED;
	
	/**
	 * Constructs a KIELER exception with given message.
	 * 
	 * @param message readable exception message
	 */
	public KielerException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a KIELER exception with given message and type.
	 * 
	 * @param message readable exception message
	 * @param type type of KIELER exception
	 */
	public KielerException(String message, Type type) {
		super(message);
		this.type = type;
	}

	/**
	 * Constructs a KIELER exception with given message and cause.
	 * 
	 * @param message readable exception message
	 * @param cause exception that caused this exception
	 */
	public KielerException(String message, Exception cause) {
	    super(message, cause);
	}
	
	/**
	 * Returns the type of KIELER exception.
	 * 
	 * @return the exception type
	 */
	public Type getType() {
		return type;
	}

}
