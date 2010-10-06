/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.client;

import java.io.IOException;

import org.apache.commons.httpclient.ConnectMethod;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.AbstractLayoutProvider;

/**
 *
 * @author msp
 */
public class RemoteLayoutProvider extends AbstractLayoutProvider {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doLayout(final KNode layoutNode, final IKielerProgressMonitor progressMonitor)
            throws KielerException {
        HttpClient client = new HttpClient();
        HostConfiguration config = new HostConfiguration();
        config.setHost("effert.local", 80);
        client.setHostConfiguration(config);
        client.setHttpConnectionManager(new SimpleHttpConnectionManager());
        try {
            System.out.println("Client coming");
            client.executeMethod(new ConnectMethod(config));
            System.out.println("  -connect");
            client.executeMethod(new GetMethod());
            System.out.println("  -get");
        } catch (HttpException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }

}
