package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.Product
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

/**
 * Helper data class that contains information about edges that should be visualized for used package associations.
 * This class contains the source bundle, the packages IDs used displayed by the edge, the product they are used in, and
 * the target bundle exporting the package.
 */
@Data
class UsedPackageEdgeConnection {
    /**
     * The source bundle the edge should connect to.
     */
    Bundle sourceBundle
    
    /**
     * The IDs of the packages provided by the target bundle in this context.
     */
    List<String> usedPackageIds
    
    /**
     * The product in that this connection is used.
     */
    Product product
    
    /**
     * The target bundle the edge should connect to. It is the bundle exporting the packages in this product context.
     */
    Bundle targetBundle
}