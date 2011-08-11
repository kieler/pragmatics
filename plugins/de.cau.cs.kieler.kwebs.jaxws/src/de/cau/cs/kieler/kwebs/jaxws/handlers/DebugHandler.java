/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.jaxws.handlers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Debugging handler for the JAX-WS service.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class DebugHandler implements SOAPHandler<SOAPMessageContext> {

    private int faultIndex = 0;
    private int inIndex = 0;
    private int outIndex = 0;
    
    /**
     * {@inheritDoc}
     */
    public Set<QName> getHeaders() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void close(final MessageContext context) {
    }

    /**
     * {@inheritDoc}
     */
    public boolean handleFault(final SOAPMessageContext context) {
        handle(context, "fault", faultIndex++);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean handleMessage(final SOAPMessageContext context) {
        if (((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue()) {
            handle(context, "outgoing", outIndex++);
        } else {
            handle(context, "incoming", inIndex++);
        }

        return true;
    }
    
    /** */
    private static final String ROOT
        = "/home/layout/kwebs/soapdump";

    /**
     * 
     * @param  
     * @param 
     * @param
     */
    private void handle(final SOAPMessageContext context, final String prefix, final int index) {
        SOAPMessage message = context.getMessage();
        try {
            File file = new File(ROOT + "/" + prefix + "_" + index + ".smsg");
            File path = file.getParentFile();
            if (path != null && !path.exists()) {
                path.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            message.writeTo(new FileOutputStream(file));
        } catch (Exception e) {
            // Ignore
        }
    }

}
