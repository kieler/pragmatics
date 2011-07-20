/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client;

import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;
import de.cau.cs.kieler.kwebs.util.Uris;

/**
 * .
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public final class Clients {

    /** The singleton instance. */
    private static final Clients INSTANCE
        = new Clients();

    /** The list of available providers. */
    private Map<String, IWebServiceClient> clientForProtocol
        = new Hashtable<String, IWebServiceClient>();

    /** The list of available providers. */
    private Map<String,  Class<? extends IWebServiceClient>> clientClassForProtocol
        = new Hashtable<String, Class<? extends IWebServiceClient>>();

    /**
     * Protected constructor.
     */
    private Clients() {
        initialize();
    }

    /** The id of the extension point for logging configuration. */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.client.protocol.support";

    /** Name of the class element. */
    private static final String ELEMENT_PROTOCOLSUPPORT
        = "protocolSupport";

    /** This attribute defines the fully qualified name of a java class for which a
     *  log level is to be defined. */
    private static final String ATTRIBUTE_PROTOCOLS
        = "protocols";

    /** From which log level on shall an logging event be notified to listeners? */
    private static final String ATTRIBUTE_CLASS
        = "class";

    /**
     * .
     */
    private void initialize() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        String protocols = null;
        String clas = null;
        for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSIONPOINT_ID)) {
            if (element.getName().equals(ELEMENT_PROTOCOLSUPPORT)) {
                protocols = element.getAttribute(ATTRIBUTE_PROTOCOLS);
                clas = element.getAttribute(ATTRIBUTE_CLASS);
                if (protocols == null || protocols.length() == 0) {
                    //throw new
                }
                if (clas == null || clas.length() == 0) {
                    //throw new
                }
                Class<? extends IWebServiceClient> clientClass = null;
                IWebServiceClient client = null;
                Bundle contributor = Platform.getBundle(element.getContributor().getName());
                if (contributor != null) {
                    try {
                        clientClass = contributor.loadClass(clas).
                            asSubclass(IWebServiceClient.class);
                        client = clientClass.newInstance();
                        StringTokenizer tokenizer = new StringTokenizer(protocols, ";");
                        String token = null;
                        while (tokenizer.hasMoreTokens()) {
                            token = tokenizer.nextToken().trim().toLowerCase();
                            clientClassForProtocol.put(token, clientClass);
                            clientForProtocol.put(token, client);
                        }
                    } catch (Exception e) {
e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Returns a client for a specific protocol from the pool or {@code null} if no client
     * is available for the specific protocol.
     * 
     * @param provider
     *            the provider to whichs protocol a compatible client is to be retrieved
     * @return the client compatible with the given protocol or {@code null} 
     */
    public static IWebServiceClient getClientForProvider(final Provider provider) {
        if (!Uris.isValidURI(provider.getAddress())) {
            return null;
        }
        String protocol = Uris.getProtocol(provider.getAddress()).trim().toLowerCase();
        IWebServiceClient client = INSTANCE.clientForProtocol.get(protocol);
        if (client != null) {
            client.setProvider(provider);
        }
        return client;
    }

    /**
     * Returns a newly instantiated client for a specific protocol or {@code null} if no client
     * is available for the specific protocol.
     * 
     * @param provider
     *            the provider to whichs protocol a compatible client is to be created
     * @return the client compatible with the given protocol or {@code null} 
     */
    public static IWebServiceClient createClientForProvider(final Provider provider) {
        if (!Uris.isValidURI(provider.getAddress())) {
            return null;
        }
        String protocol = Uris.getProtocol(provider.getAddress()).trim().toLowerCase();
        IWebServiceClient client = null;
        //CHECKSTYLEOFF EmptyBlock
        try {
            client = INSTANCE.clientClassForProtocol.get(protocol).newInstance();
            if (client != null) {
                client.setProvider(provider);
            }
            return client;
        } catch (Exception e) {
        }
        //CHECKSTYLEON EmptyBlock
        return client;
    }
    
    /**
     * Returns whether a client for a particular protocol of a provider can be supplied.
     * 
     * @param provider
     *            the provider
     * @return whether a client for a particular protocol of a provider can be supplied
     */
    public static boolean isProviderSupported(final Provider provider) {
        if (!Uris.isValidURI(provider.getAddress())) {
            return false;
        }
        String protocol = Uris.getProtocol(provider.getAddress()).trim().toLowerCase();
        return INSTANCE.clientForProtocol.containsKey(protocol);        
    }
    
}
