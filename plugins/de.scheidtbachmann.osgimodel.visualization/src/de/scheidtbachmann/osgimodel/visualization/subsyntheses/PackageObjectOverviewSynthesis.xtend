package de.scheidtbachmann.osgimodel.visualization.subsyntheses

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractSubSynthesis
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import java.util.List

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*
import static extension de.scheidtbachmann.osgimodel.visualization.SynthesisUtils.*

/**
 * Transformation as an overview of all imported packages in the given list of imported packages.
 * 
 * @author nre
 */
class PackageObjectOverviewSynthesis extends AbstractSubSynthesis<List<PackageObject>, KNode> {
    @Inject extension KNodeExtensions
    @Inject extension OsgiStyles
    @Inject SimplePackageObjectSynthesis simplePackageObjectSynthesis
    
    override transform(List<PackageObject> packageObjects) {
        return #[
            createNode => [
                configureBoxLayout
                associateWith(packageObjects)
                addOverviewRendering("Imported Packages")
                children += packageObjects.flatMap[ simplePackageObjectSynthesis.transform(it)]
                initiallyCollapse
            ]
        ]
    }
}