package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.context.PackageObjectContext
import de.scheidtbachmann.osgimodel.visualization.context.PackageObjectOverviewContext

/**
 * Sub-synthesis of {@link OsgiProject}s that handles expanded {@link PackageObject} views.
 * 
 * @author nre
 */
class PackageObjectSynthesis extends AbstractSubSynthesis<PackageObjectContext, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
        
    override transform(PackageObjectContext poc) {
        val package = poc.modelElement
        return #[
            poc.createNode() => [
                associateWith(poc)
                data += createKIdentifier => [ it.id = poc.hashCode.toString ]
                addPackageObjectRendering(package,
                    poc.parentVisualizationContext instanceof PackageObjectOverviewContext, usedContext)
            ]
        ]
    }
    
}