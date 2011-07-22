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

package de.cau.cs.kieler.kwebs.client.providers;

import java.util.Vector;

import de.cau.cs.kieler.kwebs.client.providers.Providers.Provider;

/**
 * This interface defines an api for storage management of the provider list.
 *
 * @kieler.rating 2011-05-03 red
 *
 * @author swe
 */
public interface IProvidersStorageManager {
    
    /**
     * Import a provider list from some sort of storage.
     * 
     */
    void readProviders();
    
    /**
     * Store a provider list to some sort of storage.
     * 
     * @param providerList
     *            the list to be stored
     */
    void storeProviders(final Vector<Provider> providerList);
    
}
