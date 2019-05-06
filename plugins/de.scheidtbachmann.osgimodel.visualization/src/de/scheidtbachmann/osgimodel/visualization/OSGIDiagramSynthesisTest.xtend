package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import javax.inject.Inject
import org.eclipse.elk.core.options.CoreOptions
import java.util.EnumSet
import org.eclipse.elk.core.options.SizeConstraint
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.Bundle
import de.cau.cs.kieler.klighd.kgraph.KNode

class OSGIDiagramSynthesisTest extends AbstractDiagramSynthesis<OsgiProject> {
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    extension KRenderingFactory = KRenderingFactory.eINSTANCE
    
    override transform(OsgiProject model) {
        val modelNode = createNode.associateWith(model)
        transform(model.products.head, modelNode)
//        modelNode.addLayoutParam(CoreOptions.ALGORITHM, "org.eclipse.elk.mrtree")
        return modelNode
    }
    
    private def create productNode : product.createNode() transform(Product product, KNode parent) {
        productNode.associateWith(product)
        productNode.addInsideTopCenteredNodeLabel(product.descriptiveName)
        parent.children += productNode
        productNode.addLayoutParam(
            CoreOptions.NODE_SIZE_CONSTRAINTS,
            EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))

        product.features.forEach [
            val edge = createEdge(product, it)
            edge.addPolyline(2).addHeadArrowDecorator
            edge.source = productNode
            val childNode = transform(it, parent)
            edge.target = childNode
            
            productNode.outgoingEdges.add(edge)
            
//            productNode.children += transform(it)
        ]
        println('test')
    }
    
    private def create featureNode : feature.createNode() transform(Feature feature, KNode parent) {
        featureNode.associateWith(feature)
        featureNode.addInsideTopCenteredNodeLabel(feature.descriptiveName)
        parent.children += featureNode
        featureNode.addLayoutParam(
            CoreOptions.NODE_SIZE_CONSTRAINTS,
            EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            
        feature.bundles.forEach [
            if (!uniqueId.startsWith("org.eclipse")) {
                val edge = createEdge(feature, it)
                edge.addPolyline(2).addHeadArrowDecorator
                edge.source = featureNode
                edge.target = transform(it, parent)
                featureNode.outgoingEdges.add(edge)
                
//                featureNode.children += transform(it)
            }
        ]
    }
    
    private def create bundleNode : bundle.createNode() transform(Bundle bundle, KNode parent) {
        bundleNode.associateWith(bundle)
        bundleNode.addInsideCenteredNodeLabel(bundle.uniqueId)
        parent.children += bundleNode
        bundleNode.addLayoutParam(
            CoreOptions.NODE_SIZE_CONSTRAINTS,
            EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))

    }
}