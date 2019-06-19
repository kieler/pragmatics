package de.scheidtbachmann.osgimodel.visualization.actions

import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.context.BundleContext
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductContext
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import com.google.common.collect.Iterables
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils

/**
 * Reveals and synthesizes the bundles providing packages used by any bundle into the KNode surrounding the Bundle node
 * this action is performed on and connects the new bundles with a connecting edge from the new bundle to this bundle's
 * 'requiredBundles' port.
 */
class RevealUsedPackagesAction extends AbstractVisualizationContextChangingAction {
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedPackagesAction"
    
    override protected changeVisualization(IVisualizationContext modelVisualizationContext, ActionContext actionContext) {
        // The BundleContext element for the element that was clicked on.
        val bundleContext = modelVisualizationContext as BundleContext
        
        // The bundle itself from the context.
        val bundle = bundleContext.bundle
        
        // The bundle overview context this bundle is shown in.
        val bundleOverviewContext = bundleContext.parent as BundleOverviewContext
        
        // The bundle contexts of all bundles inside this bundle overview, mapped by the bundle.
        val Map<Bundle, BundleContext> bundleContexts = new HashMap
        bundleOverviewContext.childContexts.filter(BundleContext).forEach [
            bundleContexts.put(it.bundle, it)
        ]
        
        
        // Find out which bundle provides this package in which product.
        val osgiModel = actionContext.viewContext.inputModel as OsgiProject
        
        val overviewParentContext = bundleOverviewContext.parent
        
        // Only show the connections for the product in the context, if there is any.
        val List<Product> products = if (overviewParentContext instanceof ProductContext) {
            #[overviewParentContext.product]
        } else {
            osgiModel.products
        }
        
        // Find all bundle contexts that need to be connected.
        val importedPackages = bundle.importedPackages

        // Map that tells for each product which of its bundles provides which imported packages.
        val Map<Product, Map<BundleContext, List<PackageObject>>> providedPackagesForBundleForProduct = new HashMap
        val Map<BundleContext, List<PackageObject>> externalProvidedPackagesForBundle = new HashMap
        
        // Split the imported packages by the ones imported from external packages and the ones provided by some
        // bundle within this project.
        // The external imported packages are all packages that are not found internally and removed from this list.
        val List<PackageObject> externalImportedPackages = new ArrayList(importedPackages)
        
        // Code for all internal packages
        // The relevant products are the products that contain the clicked bundle.
        val relevantProducts = products.filter [ product |
            return product.features.flatMap[ bundles ].toList.contains(bundle)
        ]
        relevantProducts.forEach[ product |
            val Map<BundleContext, List<PackageObject>> providedPackagesForBundle = new HashMap
            
            importedPackages.forEach [ importedPackage |
                // This loops all products for each imported package and looks for the bundle that provides it.
                val productBundles = product.features.flatMap[ bundles ].toList
                val bundleContainingPackage = productBundles.findFirst[ productBundle |
                    productBundle.exportedPackages.findFirst [ uniqueId.equals(importedPackage.uniqueId) ] !== null
                ]
                val bundleContainingPackageContext = bundleContexts.get(bundleContainingPackage)
                if (bundleContainingPackageContext !== null) {
                    // This package is provided by this bundle in this product.
                    if (!providedPackagesForBundle.containsKey(bundleContainingPackageContext)) {
                        providedPackagesForBundle.put(bundleContainingPackageContext, new ArrayList<PackageObject>)
                    }
                    val providedPackages = providedPackagesForBundle.get(bundleContainingPackageContext)
                    providedPackages.add(importedPackage)
                    // The package was found internally, so it does not need to be searched for from the outside anymore.
                    externalImportedPackages.remove(importedPackage)
                }
            ]
            providedPackagesForBundleForProduct.put(product, providedPackagesForBundle)
        ]
        
        // Code for all external packages
        externalImportedPackages.forEach[ importedPackage |
            val usedBundles = osgiModel.bundles
            val bundleContainingPackage = usedBundles.findFirst[ usedBundle |
                usedBundle.exportedPackages.findFirst [ uniqueId.equals(importedPackage.uniqueId) ] !== null
            ]
            val bundleContainingPackageContext = bundleContexts.get(bundleContainingPackage)
            // TODO: Test this
            if (bundleContainingPackageContext !== null) {
                // This package is provided by this bundle.
                if (!externalProvidedPackagesForBundle.containsKey(bundleContainingPackageContext)) {
                    externalProvidedPackagesForBundle.put(bundleContexts.get(bundleContainingPackageContext), new ArrayList<PackageObject>)
                }
                val providedPackages = externalProvidedPackagesForBundle.get(bundleContainingPackageContext)
                providedPackages.add(importedPackage)
            } else {
                println("No bundle found in this product or the other used bundles that contains the package " + importedPackage.uniqueId)
            }
        ]
        if (!externalProvidedPackagesForBundle.empty) {
            println(externalProvidedPackagesForBundle)
        }
        
        // Make all necessary bundles detailed.
        val externalBundlesToReveal = externalProvidedPackagesForBundle.keySet.toList
        val internalBundlesToReveal = providedPackagesForBundleForProduct.values.flatMap [ keySet ].toSet
        Iterables.concat(externalBundlesToReveal, internalBundlesToReveal).forEach [ bundleToMakeDetailedContext |
            if (bundleOverviewContext.collapsedBundleContexts.contains(bundleToMakeDetailedContext)) {
                ContextUtils.makeDetailed(bundleOverviewContext, bundleToMakeDetailedContext)
            }
        ]
        
        // TODO: handle the externalProvidedPackagesForBundle and add some connections for that as well.
        
        // Put the new connections in the overview context.
        providedPackagesForBundleForProduct.forEach [ product, usedPackagesByBundle |
            usedPackagesByBundle.forEach [ usedPackageBundleContext, usedPackages |
                ContextUtils.addUsedPackagesEdge(bundleContext, usedPackages, product, usedPackageBundleContext)
            ]
        ]
    }
    
//    // TODO: Test this!
//    def connectExternalUsedPackageBundleEdge(KNode sourceBundleNode, KNode usedPackageBundleNode,
//        Bundle usedPackageBundle, List<PackageObject> usedPackages, ActionContext context) {
//        val sourceBundlePort = sourceBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "importedPackages" ]
//        // Do not add this edge, if it is already there.
//        if (sourceBundlePort.node.outgoingEdges.findFirst[edge |
//                edge.target === usedPackageBundleNode
//            ] !== null) {
//            return
//        }
//        
//        val edge = createEdge(sourceBundleNode, usedPackageBundleNode, usedPackages) => [
//            // TODO: other rendering
//            addRequiredBundleEdgeRendering
//            sourcePort = sourceBundlePort
//            source = sourceBundleNode
//            target = usedPackageBundleNode
//        ]
//        sourceBundleNode.outgoingEdges += edge
//        
//    }
}