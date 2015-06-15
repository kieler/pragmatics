package de.cau.cs.kieler.uml.sequence.klighd

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRenderingFactory
import de.cau.cs.kieler.core.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.core.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.core.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.core.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.core.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.core.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.kiml.options.LayoutOptions
import de.cau.cs.kieler.kiml.options.SizeConstraint
import de.cau.cs.kieler.klighd.KlighdConstants
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.uml.sequence.text.sequence.Lifeline
import de.cau.cs.kieler.uml.sequence.text.sequence.SequenceDiagram
import java.util.EnumSet
import javax.inject.Inject
import de.cau.cs.kieler.papyrus.sequence.SequenceDiagramLayoutProvider

//import de.cau.cs.kieler.papyrus.sequence;

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
        
        root.addLayoutParam(LayoutOptions.ALGORITHM, SequenceDiagramLayoutProvider.ID)
        root.addInsideTopLeftNodeLabel(model.diagramName)
        model.lifelines.forEach[s|root.children += transformLifeline(s)]
        
        return root
    }
    
    private def KNode transformLifeline(Lifeline lifeline) {
        val lifelineNode = lifeline.createNode().associateWith(lifeline)

        lifelineNode.addRoundedRectangle(4, 4, 2)
        lifelineNode.addInsideCenteredNodeLabel(lifeline.caption, KlighdConstants.DEFAULT_FONT_SIZE,
            KlighdConstants.DEFAULT_FONT_NAME);
        lifelineNode.addLayoutParam(LayoutOptions.SIZE_CONSTRAINT,
            EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS));

        return lifelineNode
    }
    
    private def KNode destroyLifeline(Lifeline lifeline) {
        val lifelineDestroy =lifeline.createNode().associateWith(lifeline)
        
//        lifelineDestroy
        
        return lifelineDestroy;     
    }
    
//        private def KEdge transform(TwoLifelineMessage two) {
//        val transEdge = two.createEdge().associateWith(two);
//
//        return
//    }
//
//    private def KEdge transform(OneLifelineMessage one) {
//        val transEdge = two.createEdge().associateWith(one);
//
//        return
//    }
    
}
