/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
 * 
 *****************************************************************************/

package de.cau.cs.kieler.karma;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * interface for a class that provides feature from a package 
 * 
 * @author ckru
 *
 */
public interface IFeatureProvider {
    
    /**
     * method that returns a feature according to a given string
     * @param pack the package which contains the feature
     * @param feature a string representation of the feature
     * @return the feature
     */
    EStructuralFeature getFeatureFromPackages(final EPackage pack, final String feature);
    
    
    
}
