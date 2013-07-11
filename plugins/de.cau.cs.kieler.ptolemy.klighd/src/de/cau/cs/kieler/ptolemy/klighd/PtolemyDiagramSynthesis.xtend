/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.ptolemy.klighd

import de.cau.cs.kieler.klighd.transformations.AbstractDiagramSynthesis
import org.ptolemy.moml.DocumentRoot
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphTransformation
import com.google.inject.Inject
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphOptimization
import de.cau.cs.kieler.ptolemy.klighd.transformation.Ptolemy2KGraphVisualization

/**
 * Synthesis for turning Ptolemy models into KGraphs.
 * 
 * @author cds
 */
public class PtolemyDiagramSynthesis extends AbstractDiagramSynthesis<DocumentRoot> {
    @Inject Ptolemy2KGraphTransformation transformation
    @Inject Ptolemy2KGraphOptimization optimization
    @Inject Ptolemy2KGraphVisualization visualization
    
    
    override transform(DocumentRoot model) {
        val kgraph = transformation.transform(model)
        System::out.println("Transformed")
        optimization.optimize(kgraph)
        System::out.println("Optimized")
        visualization.visualize(kgraph)
        System::out.println("Visualized")
        
        return kgraph
    }
    
}