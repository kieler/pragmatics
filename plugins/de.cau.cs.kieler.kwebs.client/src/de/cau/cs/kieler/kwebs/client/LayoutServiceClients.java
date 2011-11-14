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

import java.net.URI;
import java.util.Hashtable;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osgi.framework.Bundle;

/**
 * This class handles the registration of layout service clients and the mapping to their supported
 * protocols. Each layout service client has to register itself through the extension registry using
 * the extension point {@code de.cau.cs.kieler.kwebs.client.protocol.support}.
 *
 * @kieler.rating 2011-08-02 proposed yellow
 *     reviewed by ckru, mri, msp
 *
 * @author swe
 */
public final class LayoutServiceClients {

    /** The singleton instance. */
    private static final LayoutServiceClients INSTANCE
        = new LayoutServiceClients();

    /** The map of available protocols to the compatible client instances. */
    private Map<String, ILayoutServiceClient> clientForProtocol
        = new Hashtable<String, ILayoutServiceClient>();

    /** The map of available protocols to the compatible client classes. */
    private Map<String,  Class<? extends ILayoutServiceClient>> clientClassForProtocol
        = new Hashtable<String, Class<? extends ILayoutServiceClient>>();

    /**
     * Protected constructor.
     */
    private LayoutServiceClients() {
        initialize();
    }
    
    /**
     * Get the singleton instance.
     *
     * @return the singleton instance
     */
    public static LayoutServiceClients getInstance() {
        return INSTANCE;
    }

    /** The id of the extension point for configuration of the supported clients. */
    private static final String EXTENSIONPOINT_ID
        = "de.cau.cs.kieler.kwebs.client.protocols";

    /** Name of the protocolSupport element. */
    private static final String ELEMENT_PROTOCOLSUPPORT
        = "protocolSupport";

    /** This attribute defines a semiconon separated list of protocols supported by the client. */    
    private static final String ATTRIBUTE_PROTOCOLS
        = "protocols";

    /** This attribute defines the fully qualified name of a java class of a client implementation. */
    private static final String ATTRIBUTE_IMPLEMENTATION
        = "implementation";

    /**
     * Reads the registered client implementations from the extension registry.
     */
    private void initialize() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        String protocols = null;
        String implementation = null;
        for (IConfigurationElement element : registry.getConfigurationElementsFor(EXTENSIONPOINT_ID)) {
            if (element.getName().equals(ELEMENT_PROTOCOLSUPPORT)) {
                protocols = element.getAttribute(ATTRIBUTE_PROTOCOLS);
                implementation = element.getAttribute(ATTRIBUTE_IMPLEMENTATION);
                if (protocols == null || protocols.length() == 0) {
                    StatusManager.getManager().handle(
                        new Status(
                            IStatus.WARNING, 
                            KwebsClientPlugin.PLUGIN_ID, 
                            "No supported protocol specified, ignoring client definition", 
                            null
                        )
                    );
                } else if (implementation == null || implementation.length() == 0) {
                    StatusManager.getManager().handle(
                        new Status(
                            IStatus.WARNING, 
                            KwebsClientPlugin.PLUGIN_ID, 
                            "No implementation class specified, ignoring client definition", 
                            null
                        )
                    );
                } else {
                    Class<? extends ILayoutServiceClient> implementationClass = null;
                    ILayoutServiceClient client = null;
                    Bundle contributor = Platform.getBundle(element.getContributor().getName());
                    if (contributor != null) {
                        try {
                            implementationClass = contributor.loadClass(implementation).
                                asSubclass(ILayoutServiceClient.class);
                            client = implementationClass.newInstance();
                            StringTokenizer tokenizer = new StringTokenizer(protocols, ";");
                            String token = null;
                            while (tokenizer.hasMoreTokens()) {
                                token = tokenizer.nextToken().trim().toLowerCase();
                                clientClassForProtocol.put(token, implementationClass);
                                clientForProtocol.put(token, client);
                            }
                        } catch (Exception e) {
                            StatusManager.getManager().handle(
                                new Status(
                                    IStatus.WARNING, 
                                    KwebsClientPlugin.PLUGIN_ID, 
                                    "Implementation class not found, ignoring client definition",
                                    null
                                )
                            );
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the number of protool dependent client implementations available for
     * 
     * doing service based layout.
     * 
     * @return the number of protool dependent client implementations available for
     * doing service based layout.
     */
    public int countClients() {
        return clientForProtocol.size();
    }
    
    /**
     * Returns a client for a specific protocol from the pool or {@code null} if no client
     * is available for the specific protocol.
     *
     * @param serverConfig
     *            the server configuration to whichs protocol a compatible client is to be retrieved
     * @return the client compatible with the given protocol or {@code null}
     */
    public ILayoutServiceClient getClientForServerConfig(final ServerConfigData serverConfig) {
        URI address = serverConfig.getAddress();
        String protocol = address.getScheme();
        if (protocol == null) {
            return null;
        }
        ILayoutServiceClient client = clientForProtocol.get(protocol);
        if (client != null) {
            client.setServerConfig(serverConfig);
        }
        return client;
    }

    /**
     * Returns a newly instantiated client for a specific protocol or {@code null} if no client
     * is available for the specific protocol.
     *
     * @param serverConfig
     *            the server configuration to whose protocol a compatible client is to be created
     * @return the client compatible with the given protocol or {@code null}
     */
    public ILayoutServiceClient createClientForServerConfig(final ServerConfigData serverConfig) {
        URI address = serverConfig.getAddress();
        String protocol = address.getScheme();
        if (protocol == null) {
            return null;
        }
        ILayoutServiceClient client = null;
        try {
            client = clientClassForProtocol.get(protocol).newInstance();
            if (client != null) {
                client.setServerConfig(serverConfig);
            }
        } catch (Exception e) {
            // Protocol not supported
        }
        return client;
    }

    /**
     * Returns whether a client for a particular protocol of a server configuration can be supplied.
     *
     * @param serverConfig
     *            the server configuration
     * @return whether a client for a particular protocol of a server configuration can be supplied
     */
    public boolean isServerConfigSupported(final ServerConfigData serverConfig) {
        URI address = serverConfig.getAddress();
        String protocol = address.getScheme();
        if (protocol == null) {
            return false;
        }
        return clientForProtocol.containsKey(protocol);
    }

}
