/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.management.command.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author  swe
 * 
 */
public final class ManagementExchange implements IManagementExchange {
    
    //////////
    
    /** */
    private static final long serialVersionUID = 2422098188190797439L;
    
    //////////
    
    /** */
    private final Map<String, Serializable> parameters
    	= new HashMap<String, Serializable>();
    
    /** */
    private String command;
    
    /** */
    private Serializable response;
    
    //////////
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getParameter(final String name) {
    	return (T) parameters.get(name);
    }
    
    /**
     * {@inheritDoc}
     */
    public <T extends Serializable> void setParameter(final String name, final T value) {
    	parameters.put(name, value);
    }
    
    /**
     * @return
     */
    public String getCommand() {
    	return command;
    }
    
    /**
     * @param command
     */
    public void setCommand(final String command) {
    	this.command = command;
    }
    
    /**
     * @return
     */
    public Serializable getResponse() {
    	return response;
    }
    
    /**
     * @param response
     */
    public void setResponse(final Serializable response) {
    	this.response = response;
    }
    
    //////////
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
    	if (object == this) {
    		return true;
    	}
    	if (!(object instanceof ManagementExchange)) {
    		return false;
    	}
    	ManagementExchange other = (ManagementExchange) object;
    	if (!getClass().equals(other.getClass())) {
    		return false;
    	}
    	if (!parameters.equals(other.parameters)) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
    	return (
    		command != null ? command.hashCode() << 1 : 0 
    		^ parameters.hashCode()
    		^ getClass().hashCode() << 1
    	);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {	
    	StringBuilder builder = new StringBuilder();
    	builder.append(getClass().getCanonicalName());
    	builder.append("@");
    	builder.append(Integer.toHexString(hashCode()));
    	builder.append(" (command=" + (command != null ? command : "null"));
    	builder.append(", parameters=[");
    	for (Entry<String, Serializable> entry : parameters.entrySet()) {
    		builder.append(entry.getKey() + "=" + entry.getValue() + ", ");
    	}
    	builder.append("], result=" + (response != null ? response.toString() : null) + ")");
    	return builder.toString();
    }
	
}
