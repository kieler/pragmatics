package de.scheidtbachmann.osgimodel.visualization

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.krendering.KRenderingFactory
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KColorExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KContainerRenderingExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KEdgeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KLabelExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPolylineExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KPortExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.BundleCategory
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.ServiceInterface
import java.util.EnumSet
import java.util.LinkedHashSet
import java.util.List
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.core.options.SizeConstraint

/**
 * Main diagram synthesis for {@link OsgiProject} models.
 * 
 * @author nre
 */
@ViewSynthesisShared
class OsgiDiagramSynthesis extends AbstractDiagramSynthesis<OsgiProject> {
    @Inject extension KNodeExtensions
    @Inject extension KEdgeExtensions
    @Inject extension KPortExtensions
    @Inject extension KLabelExtensions
    @Inject extension KContainerRenderingExtensions
    @Inject extension KRenderingExtensions
    @Inject extension KPolylineExtensions
    @Inject extension KColorExtensions
    @Inject extension OsgiStyles
    extension KRenderingFactory = KRenderingFactory.eINSTANCE
    
    override getInputDataType() {
        OsgiProject
    }
    
    override getDisplayedActions() {
        val actions = new LinkedHashSet()
        // TODO: Add action that resets the current focus.
        
        return actions.toList
    }
       
    override getDisplayedSynthesisOptions() {
        val options = new LinkedHashSet()
        // TODO: Option that tells how many levels of usedBy/requires should be synthesized.
        // TODO: Text filter to white-/blacklist shown features/packages/...
        
        return options.toList
    }
    // TODO: Maybe remember a map of all representations of every bundle, ... to calculate which nodes should be 
    // rendered differently later (after synthesis hook) to highlight multiple uses. 
    
    override transform(OsgiProject model) {
        val modelNode = createNode.associateWith(model)
        
        // Create a view of the last focused element, otherwise create an overview of the OSGi Project.
        switch focusedElement : usedContext.getProperty(OsgiSynthesisProperties.MAIN_ELEMENT) {
            case focusedElement instanceof Product: {
                transform(focusedElement as Product)
                return modelNode
            }
            case focusedElement instanceof Feature: {
                transform(focusedElement as Feature)
                return modelNode
            }
            // TODO: etc.
        }
        
        // The overview of the entire OSGi Project.
        modelNode.children += createNode => [
            associateWith(model)
            addOverviewRendering("Overview")
            
            children += createNode => [
                associateWith(model)
                addInvisibleContainerRendering
                children += transformProductOverview(model.products, model)
                children += transformFeatureOverview(model.features, model)
                children += transformBundleOverview(model.bundles, model)
                children += transformServiceInterfacesOverview(model.serviceInterfaces, model)
                children += transformImportedPackagesOverview(model.importedPackages, model)
                children += transformBundleCategoriesOverview(model.bundleCategories, model)
            ]
        ]
        return modelNode
    }
    
    private def transformProductOverview(List<Product> products, OsgiProject model) {
        return createNode => [
            associateWith(model)
            addOverviewRendering("Products")
            children += products.map[ transform ]
        ]
    }
    
    // TODO: If cycles are possible, put these transformations in child view contexts and transform them individually.
    private def transform(Product p) {
        return p.createNode() => [
            associateWith(p)
            addInsideTopCenteredNodeLabel(p.descriptiveName)
            addLayoutParam(
                CoreOptions.NODE_SIZE_CONSTRAINTS,
                EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            // TODO: Does this need to search for the parent viewContext
    //        usedContext.setProperty(OsgiSynthesisProperties.MAIN_ELEMENT, p)
    
            addProductRendering
            // TODO: add action on a new focus button that causes this element to focus.
        ]
    }
    
    private def transformFeatureOverview(List<Feature> products, OsgiProject model) {
        return createNode => [
            associateWith(model)
            addOverviewRendering("Features")
            children += products.map[ transform ]
        ]
    }
    
    private def transform(Feature p) {
        return p.createNode() => [
            associateWith(p)
            addInsideTopCenteredNodeLabel(p.descriptiveName)
            addLayoutParam(
                CoreOptions.NODE_SIZE_CONSTRAINTS,
                EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            addProductRendering // TODO: own rendering for stuff other than products
        ]
    }
    
    private def transformBundleOverview(List<Bundle> products, OsgiProject model) {
        return createNode => [
            associateWith(model)
            addOverviewRendering("Bundles")
            children += products.map[ transform ]
        ]
    }
    
    private def transform(Bundle p) {
        return p.createNode() => [
            associateWith(p)
            addInsideTopCenteredNodeLabel(p.descriptiveName)
            addLayoutParam(
                CoreOptions.NODE_SIZE_CONSTRAINTS,
                EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            addProductRendering // TODO: own rendering for stuff other than products
        ]
    }
    
    private def transformServiceInterfacesOverview(List<ServiceInterface> products, OsgiProject model) {
        return createNode => [
            associateWith(model)
            addOverviewRendering("Service Interfaces")
            children += products.map[ transform ]
        ]
    }
    
    private def transform(ServiceInterface p) {
        return p.createNode() => [
            associateWith(p)
            addInsideTopCenteredNodeLabel(p.name)
            addLayoutParam(
                CoreOptions.NODE_SIZE_CONSTRAINTS,
                EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            addProductRendering // TODO: own rendering for stuff other than products
        ]
    }
    
    private def transformImportedPackagesOverview(List<PackageObject> products, OsgiProject model) {
        return createNode => [
            associateWith(model)
            addOverviewRendering("Imported Packages")
            children += products.map[ transform ]
        ]
    }
    
    private def transform(PackageObject p) {
        return p.createNode() => [
            associateWith(p)
            addInsideTopCenteredNodeLabel(p.uniqueId)
            addLayoutParam(
                CoreOptions.NODE_SIZE_CONSTRAINTS,
                EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            addProductRendering // TODO: own rendering for stuff other than products
        ]
    }
    
    private def transformBundleCategoriesOverview(List<BundleCategory> products, OsgiProject model) {
        return createNode => [
            associateWith(model)
            addOverviewRendering("Bundle Categories")
            children += products.map[ transform ]
        ]
    }
    
    private def transform(BundleCategory p) {
        return p.createNode() => [
            associateWith(p)
            addInsideTopCenteredNodeLabel(p.categoryName)
            addLayoutParam(
                CoreOptions.NODE_SIZE_CONSTRAINTS,
                EnumSet.of(SizeConstraint.MINIMUM_SIZE, SizeConstraint.NODE_LABELS))
            addProductRendering // TODO: own rendering for stuff other than products
        ]
    }
}