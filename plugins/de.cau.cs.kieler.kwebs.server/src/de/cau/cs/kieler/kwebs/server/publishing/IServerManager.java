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

package de.cau.cs.kieler.kwebs.server.publishing;

/**
 * Interface declaration for the different server managers.
 *
 * @author swe
 *
 */
interface IServerManager {

    /**
     * Publish the given object with the server provided by this manager.
     *
     * @param theobject
     *            the object to be published
     *
     * @return whether publishing was successful
     */
    void publish(final Object theobject);

    /**
     * Unpublish the published object.
     */
    void unpublish();

    /**
     * Tests whether the managed server currently is publishing any
     * service object.
     *
     * @return whether the managed server currently is publishing
     */
    boolean isPublished();

}
