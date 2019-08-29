package de.scheidtbachmann.osgimodel.visualization.context

import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import java.util.List
import org.eclipse.xtend.lib.annotations.Data

/**
 * Helper data class that contains information about edges that should be visualized for used package associations.
 * This class contains the source bundle, the packages IDs used displayed by the edge, the product they are used in, and
 * the target bundle exporting the package.
 * 
 * @author nre
 */
@Data
class UsedPackagesOfBundleEdgeConnection {
    new(BundleContext sourceBundleContext, List<PackageObject> usedPackages, Product product, BundleContext targetBundleContext) {
        this.sourceBundleContext = sourceBundleContext
        this.usedPackages = usedPackages
        this.product = product
        this.targetBundleContext = targetBundleContext
    }
    
    /**
     * The bundle context of the source bundle the edge should connect to.
     */
    BundleContext sourceBundleContext
    
    /**
     * The packages provided by the target bundle in this context.
     */
    List<PackageObject> usedPackages
    
    /**
     * The product in that this connection is used. May be null if this connection is used in no product.
     */
    Product product
    
    /**
     * The target bundle context the edge should connect to. It is the bundle exporting the packages in this product context.
     */
    BundleContext targetBundleContext
}