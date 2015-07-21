/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.examples.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

/**
 * A specialized {@link ArrayList} restricted to {@link EModelElement}s.
 * Is needed for the proper realization of handling model element lists in KLighD. 
 * 
 * @author chsch
 */
public class EModelElementCollection extends ArrayList<EModelElement> {

    private static final long serialVersionUID = 1L;
    
    private EModelElementCollection(Collection<EModelElement> c) {
        super(c);
    }
    
    public static EModelElementCollection of(Collection<EModelElement> elements) {
        return new EModelElementCollection(elements);
    }

    public static EModelElementCollection of(Iterator<? extends EModelElement> elements) {
        return new EModelElementCollection(IteratorExtensions.toList(elements));
    }
}
