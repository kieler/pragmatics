package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.Product

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link Product} views.
 * 
 * @author nre
 */
@ViewSynthesisShared
class ProductSynthesis extends AbstractDiagramSynthesis<Product> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    
    override transform(Product p) {
        return p.createNode => [
            associateWith(p)
            children += createNode() => [
                associateWith(p)
                addProductRendering(p, usedContext)
            ]
        ]
    }
    
}