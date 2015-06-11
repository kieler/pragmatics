package de.cau.cs.kieler.uml.sequence.klighd

import javax.inject.Inject

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis

import static de.cau.cs.kieler.klighd.syntheses.DiagramLayoutOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram

class SequenceDiagramSynthesis extends AbstractDiagramSynthesis<SequenceDiagram> {
    
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    extension KRenderingFactory = KRenderingFactory.eINSTANCE
    
    
    override KNode transform(SequenceDiagram model) {
        val root = model.createNode().associateWith(model);
        
        // Your dsl element <-> diagram figure mapping goes here!!
        
        // Notice the statically imported classes 'DiagramSyntheses' and 'DiagramLayoutOptions'
        //  that contribute direct access to a couple of (layout) configurations
        
        return root;
    }
    
}
