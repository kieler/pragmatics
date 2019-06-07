package de.scheidtbachmann.osgimodel.visualization.actions

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.actions.SynthesizingAction
import de.cau.cs.kieler.klighd.kgraph.KGraphFactory
import de.cau.cs.kieler.klighd.kgraph.KIdentifier
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.kgraph.KPort
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.visualization.OsgiStyles
import de.scheidtbachmann.osgimodel.visualization.SynthesisUtils
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map

/**
 * Reveals and synthesizes the bundles providing packages used by any bundle into the KNode surrounding the Bundle node
 * this action is performed on and connects the new bundles with a connecting edge from the new bundle to this bundle's
 * 'requiredBundles' port.
 */
class RevealUsedPackagesAction extends SynthesizingAction {
    @Inject extension KEdgeExtensions
    @Inject extension OsgiStyles
    extension KGraphFactory = KGraphFactory.eINSTANCE
    
    /**
     * This action's ID.
     */
    public static val String ID = "de.scheidtbachmann.osgimodel.visualization.actions.RevealUsedPackagesAction"
    
    override execute(ActionContext context) {
        // The KPort that was clicked on.
        val clickedPort = context.KGraphElement as KPort

        // The KNode representing the Bundle that should add the providing package bundles.
        val bundleNode = clickedPort.eContainer as KNode

        // The Bundle element itself that was clicked on.
//        val bundle = context.getDomainElement(bundleNode) as Bundle
        val bundle = SynthesisUtils.getDomainElement(context) as Bundle

        // The KNode containing the bundle in which the providing package bundles should be added as well.
        val containingNode = bundleNode.eContainer as KNode
        
        val importedPackages = bundle.importedPackages
        
        // TODO: implement removing the nodes again.
        // If all bundles are already connected, remove them all. Otherwise, connect them all.
//        if (GenericRevealActionUtil.allConnected(importedPackages, bundleNode, true, context, containingNode)) {
//            // TODO: filter these edges by some new Identifier so only the required bundle edges are removed.
//            val edgesToRemove = bundleNode.outgoingEdges.immutableCopy
//            edgesToRemove.forEach [ edge |
//                val targetNode = edge.target
//                // Remove the edge.
//                KGraphUtil.removeElement(edge)
//                // TODO: filter these edges as well.
//                if (targetNode.incomingEdges.empty && targetNode.outgoingEdges.empty) {
//                    // If the target node now is not connected anymore, remove it.
//                    if (!containingNode.children.contains(targetNode)) {
//                        throw new IllegalStateException("The target of the given bundle node is not contained in the " +
//                            "containingNode and cannot be removed!")
//                    }
//                    KGraphUtil.removeElement(targetNode)
//                } else {
//                    // If the target node is still connected, at least its connections to usedByBundles is not complete
//                    // anymore, so revert the port color of that node to its default.
//                    val connectedPort = targetNode.ports.findFirst [
//                        it.data.filter(KIdentifier)?.head?.id === "usedByBundles"
//                    ]
//                    if (connectedPort !== null) {
//                        connectedPort.unHighlightAllShown
//                    }
//                }
//            ]
//            // Revert the background color of the clicked node to indicate that not all of its children are shown
//            // anymore.
//            clickedPort.unHighlightAllShown
//        } else {
            // Find out which bundle provides this package in which product.
            val osgiModel = context.viewContext.inputModel as OsgiProject
            // Map that tells for each product which of its bundles provides which imported packages.
            val Map<Product, Map<Bundle, List<PackageObject>>> providedPackagesForBundleForProduct = new HashMap
            val Map<Bundle, List<PackageObject>> externalProvidedPackagesForBundle = new HashMap
            
            // Split the imported packages by the ones imported from external packages and the ones provided by some
            // bundle within this project.
            // The external imported packages are all packages that are not found internally and removed from this list.
            val List<PackageObject> externalImportedPackages = new ArrayList(importedPackages)
            
            // Code for all internal packages
            // The relevant products are the products that contain the clicked bundle.
            val relevantProducts = osgiModel.products.filter [ product |
                return product.features.flatMap[ bundles ].toList.contains(bundle)
            ]
            relevantProducts.forEach[ product |
                val Map<Bundle, List<PackageObject>> providedPackagesForBundle = new HashMap
                
                importedPackages.forEach [ importedPackage |
                    // This loops all products for each imported package and looks for the bundle that provides it.
                    val productBundles = product.features.flatMap[ bundles ].toList
                    val bundleContainingPackage = productBundles.findFirst[ productBundle |
                        productBundle.exportedPackages.findFirst [ uniqueId.equals(importedPackage.uniqueId) ] !== null
                    ]
                    if (bundleContainingPackage !== null) {
                        // This package is provided by this bundle in this product.
                        if (!providedPackagesForBundle.containsKey(bundleContainingPackage)) {
                            providedPackagesForBundle.put(bundleContainingPackage, new ArrayList<PackageObject>)
                        }
                        val providedPackages = providedPackagesForBundle.get(bundleContainingPackage)
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
                // Test
                if (bundleContainingPackage !== null) {
                    // This package is provided by this bundle.
                    if (!externalProvidedPackagesForBundle.containsKey(bundleContainingPackage)) {
                        externalProvidedPackagesForBundle.put(bundleContainingPackage, new ArrayList<PackageObject>)
                    }
                    val providedPackages = externalProvidedPackagesForBundle.get(bundleContainingPackage)
                    providedPackages.add(importedPackage)
                } else {
                    println("No bundle found in this product or the other used bundles that contains the package " + importedPackage.uniqueId)
                }
            ]
            if (!externalProvidedPackagesForBundle.empty) {
                println(externalProvidedPackagesForBundle)
            }
            
            // Reveal all necessary bundles.
            val externalBundlesToReveal = externalProvidedPackagesForBundle.keySet.toList
            val internalBundlesToReveal = providedPackagesForBundleForProduct.values.flatMap [ keySet ].toSet
            
            val externalBundleNodes = GenericRevealActionUtil.revealElements(externalBundlesToReveal.toList, context, containingNode)
            val internalBundleNodes = GenericRevealActionUtil.revealElements(internalBundlesToReveal.toList, context, containingNode)
            
            // Connect the new bundles with which packages they import for which project.
            externalBundleNodes.forEach [ usedPackageBundle, usedPackageBundleNode |
                val usedPackages = externalProvidedPackagesForBundle.get(usedPackageBundle)
                connectExternalUsedPackageBundleEdge(bundleNode, usedPackageBundleNode, usedPackageBundle, usedPackages, context)
            ]
            
            providedPackagesForBundleForProduct.forEach [ product, usedPackagesByBundle |
                usedPackagesByBundle.forEach [ usedPackageBundle, usedPackages |
                    val usedPackageBundleNode = internalBundleNodes.get(usedPackageBundle)
                    connectInternalUsedPackageBundleEdge(bundleNode, usedPackageBundleNode, usedPackageBundle, usedPackages, product, context)
                ]
            ]
//            
//            // Change the background color of the clicked node to indicate that all its children are now shown.
//            clickedPort.highlightAllShown
//        }
        
        if (externalBundleNodes.empty && internalBundleNodes.empty) {
            return ActionResult.createResult(false)
        } else {
            return ActionResult.createResult(true)
        }
    }
    
    // TODO: Test this!
    def connectExternalUsedPackageBundleEdge(KNode sourceBundleNode, KNode usedPackageBundleNode,
        Bundle usedPackageBundle, List<PackageObject> usedPackages, ActionContext context) {
        val sourceBundlePort = sourceBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "importedPackages" ]
        // Do not add this edge, if it is already there.
        if (sourceBundlePort.node.outgoingEdges.findFirst[edge |
                edge.target === usedPackageBundleNode
            ] !== null) {
            return
        }
        
        val edge = createEdge(sourceBundleNode, usedPackageBundleNode, usedPackages) => [
            // TODO: other rendering
            addRequiredBundleEdgeRendering
            sourcePort = sourceBundlePort
            source = sourceBundleNode
            target = usedPackageBundleNode
        ]
        sourceBundleNode.outgoingEdges += edge
        
//        val filteredUsedByBundles = SynthesisUtils.filteredBundles(usedPackageBundle.usedByBundle, context.viewContext).toList
//        if (GenericRevealActionUtil.allConnected(filteredUsedByBundles, requiredBundleNode, false, context,
//            sourceBundleNode.parent)) {
//            targetBundlePort.highlightAllShown
//        }
    }
    
    def connectInternalUsedPackageBundleEdge(KNode sourceBundleNode, KNode usedPackageBundleNode,
        Bundle usedPackageBundle, List<PackageObject> usedPackages, Product product, ActionContext context) {
        val sourceBundlePort = sourceBundleNode.ports.findFirst[ data.filter(KIdentifier).head?.id === "importedPackages" ]
        // Do not add this edge, if it is already there.
        if (sourceBundlePort.node.outgoingEdges.findFirst[edge |
                edge.target === usedPackageBundleNode
                && edge.data.filter(KIdentifier).head?.id.equals("usedPackageBy" + product.uniqueId)
            ] !== null) {
            return
        }
        
        val edge = createEdge(sourceBundleNode, usedPackageBundleNode, usedPackages, product) => [
            addInternalUsedPackagesBundleEdgeRendering(usedPackages, product, context.viewContext)
            sourcePort = sourceBundlePort
            source = sourceBundleNode
            target = usedPackageBundleNode
            data += createKIdentifier => [ it.id = "usedPackageBy" + product.uniqueId ]
        ]
        sourceBundleNode.outgoingEdges += edge
        
//        val filteredUsedByBundles = SynthesisUtils.filteredBundles(usedPackageBundle.usedByBundle, context.viewContext).toList
//        if (GenericRevealActionUtil.allConnected(filteredUsedByBundles, requiredBundleNode, false, context,
//            sourceBundleNode.parent)) {
//            targetBundlePort.highlightAllShown
//        }
    }
}