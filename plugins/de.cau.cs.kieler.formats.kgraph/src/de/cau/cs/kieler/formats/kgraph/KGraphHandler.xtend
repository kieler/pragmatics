/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.formats.kgraph

import de.cau.cs.kieler.kiml.formats.AbstractEmfHandler
import de.cau.cs.kieler.klighd.kgraph.KNode
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

class KGraphHandler extends AbstractEmfHandler<KNode>  {

    override protected createResourceSet() {
        return new ResourceSetImpl
    }
    
    override getImporter() {
        return new KGraphImporter
    }
    
    override getExporter() {
        return new KGraphExporter
    }
    
}