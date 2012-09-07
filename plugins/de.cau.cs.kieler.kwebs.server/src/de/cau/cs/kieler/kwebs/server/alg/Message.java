/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.server.alg;

/**
 * 
 * @author swe
 */
public class Message {
    
    /** */
    public static enum Severity {
        /** */
        INFO,
        /** */
        WARNING,
        /** */
        ERROR,
        /** */
        FATAL
    }
    
    //////////
    
    /** */
    private Object source;
    
    /** */
    private Severity severity;
    
    /** */
    private String message;
    
    /** */
    private Throwable cause;
    
    //////////
    
    /**
     * 
     * @param source
     * @param message
     */
    public Message(final Object source, final String message) {
        this(source, Severity.INFO, message, null);
    }
    
    /**
     * 
     * @param source
     * @param severity
     * @param message
     */
    public Message(final Object source, final Severity severity, final String message) {
        this(source, severity, message, null);        
    }
    
    /**
     * 
     * @param source
     * @param severity
     * @param message
     * @param cause
     */
    public Message(final Object source, final Severity severity, 
        final String message, final Throwable cause) {
        this.source = source;
        this.severity = severity;
        this.message = message;
        this.cause = cause;
    }
    
    //////////
    
    /**
     * 
     * @return
     */
    public Object getSource() {
        return source;
    }

    /**
     * 
     * @param source
     */
    public void setSource(final Object source) {
        this.source = source;
    }

    /**
     * 
     * @return
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * 
     * @param severity
     */
    public void setSeverity(final Severity severity) {
        this.severity = severity;
    }

    /**
     * 
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * 
     * @param cause
     */
    public void setCause(final Throwable cause) {
        this.cause = cause;
    }
    
}
