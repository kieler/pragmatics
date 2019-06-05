package de.scheidtbachmann.osgimodel.visualization

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.klighd.DisplayedActionData
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.kgraph.KNode
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
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.Bundle
import de.scheidtbachmann.osgimodel.BundleCategory
import de.scheidtbachmann.osgimodel.Feature
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.PackageObject
import de.scheidtbachmann.osgimodel.Product
import de.scheidtbachmann.osgimodel.ServiceInterface
import de.scheidtbachmann.osgimodel.visualization.OsgiOptions.BundleTextType
import de.scheidtbachmann.osgimodel.visualization.actions.UnfocusAction
import de.scheidtbachmann.osgimodel.visualization.actions.UnfocusAllAction
import java.util.LinkedHashSet
import java.util.List
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.core.options.CoreOptions
import org.eclipse.elk.graph.properties.MapPropertyHolder

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

import static extension de.cau.cs.kieler.klighd.syntheses.DiagramSyntheses.*

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
        return #[
            DisplayedActionData.create(UnfocusAction.ID, "Unfocus",
                "Removes the focus of the current element and returns it to the last focused element"),
            DisplayedActionData.create(UnfocusAllAction.ID, "Unfocus all",
                "Returns the focus back to the overview.")
        ]
    }
    
    override getDisplayedLayoutOptions() {
        return ImmutableList::of(
            specifyLayoutOption(LayeredOptions::HIGH_DEGREE_NODES_TREATMENT, #[true, false])
        )
    }
       
    override getDisplayedSynthesisOptions() {
        val options = new LinkedHashSet()
        // TODO: Text filter to white-/blacklist shown features/packages/...
        
        // Add Bundle options
        options.addAll(BUNDLE, BUNDLE_TEXT, DESCRIPTION_LENGTH)
        
        // Add general options
        options.addAll(SHORTEN_BY_DE_SCHEIDTBACHMANN, FILTER_BY_DE_SCHEIDTBACHMANN)
        
        return options.toList
    }
    // TODO: Maybe remember a map of all representations of every bundle, ... to calculate which nodes should be 
    // rendered differently later (after synthesis hook) to highlight multiple uses. 
    
    override transform(OsgiProject model) {
        val modelNode = createNode.associateWith(model)
        
        // Create a view of the last focused element, otherwise create an overview of the OSGi Project.
        val focusedElementStack = usedContext.getProperty(OsgiSynthesisProperties.FOCUSED_ELEMENTS)
        val focusedElement = if (focusedElementStack.empty) {
                null
            } else {
                focusedElementStack.head
            }
        if (focusedElement !== null) {
            val requiredSynthesis = SynthesisUtils.requiredSynthesis(focusedElement)
            
            val newBundleContainer = LightDiagramServices.translateModel(
                focusedElement,
                this.usedContext,
                new MapPropertyHolder => [
                    setProperty(KlighdSynthesisProperties.REQUESTED_DIAGRAM_SYNTHESIS, requiredSynthesis
                    )
                ]
            )
            
            modelNode.children += newBundleContainer.children
            
            return modelNode
        }
        
        // The overview of the entire OSGi Project.
        modelNode.children += createNode => [
            associateWith(model)
            addOverviewRendering("Overview")
            
            children += createNode => [
                associateWith(model)
                addInvisibleContainerRendering
                children += transformProductOverview(model.products).associateWith(model)
                children += transformFeatureOverview(model.features).associateWith(model)
                children += transformBundleOverview(model.bundles).associateWith(model)
                children += transformServiceInterfacesOverview(model.serviceInterfaces).associateWith(model)
                children += transformImportedPackagesOverview(model.importedPackages).associateWith(model)
                children += transformBundleCategoriesOverview(model.bundleCategories).associateWith(model)
                
                // remove the padding of the invisible container.
//                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0)) // TODO: test this.
            ]
        ]
        
        return modelNode
    }
    
    /**
     * Transformation as an overview of all products in the given list of products.
     * 
     * @param products The list of products to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformProductOverview(List<Product> products) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Products")
            children += products.map[ transform ]
        ]
    }
    
    /**
     * Transformation of a simple view of a product that provides functionality to be expanded, when the specific 
     * synthesis for products is called.
     * 
     * @param p The product that should be transformed into a simple product rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(Product p) {
        return p.createNode() => [
            associateWith(p)
            initiallyCollapse
            val label = p.descriptiveName
            setLayoutOption(CoreOptions::PRIORITY, priorityOf(label))
            addProductInOverviewRendering(p, label)
        ]
    }
    
    /**
     * Transformation as an overview of all features in the given list of features.
     * 
     * @param features The list of features to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformFeatureOverview(List<Feature> features) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Features")
            children += features.map[ transform ]
            initiallyCollapse
        ]
    }
    
    /**
     * Transformation of a simple view of a feature that provides functionality to be expanded, when the specific 
     * synthesis for features is called.
     * 
     * @param f The feature that should be transformed into a simple feature rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(Feature f) {
        return f.createNode() => [
            associateWith(f)
            addGenericRendering(f.descriptiveName)
        ]
    }
    
    /**
     * Transformation as an overview of all bundles in the given list of bundles.
     * 
     * @param bundles The list of bundles to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformBundleOverview(List<Bundle> bundles) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Bundles")
            val filteredBundles = SynthesisUtils.filteredBundles(bundles, usedContext)
            children += filteredBundles.map[ transform ]
            initiallyCollapse
        ]
    }
    
    /**
     * Transformation of a simple view of a bundle that provides functionality to be expanded, when the specific 
     * synthesis for bundles is called.
     * 
     * @param b The bundle that should be transformed into a simple bundle rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(Bundle b) {
        return b.createNode() => [
            associateWith(b)
            initiallyCollapse
            val label = switch usedContext.getOptionValue(BUNDLE_TEXT) {
                case BundleTextType.Id: {
                    SynthesisUtils.getId(b.uniqueId, usedContext)
                }
                case BundleTextType.Name: {
                    b.descriptiveName
                }
            } ?: ""
            setLayoutOption(CoreOptions::PRIORITY, priorityOf(label))
            addBundleInOverviewRendering(b, label)
        ]
    }
    
    /**
     * Turns any string into an int roughly matching its alphabetical ordering.
     * It does that by taking the first 6 alphabetical characters as lowercase characters and maps them to ascending
     * numbers based on those first characters.
     * 
     * @param label The string that should be converted.
     * @return A number for the string that is higher for lexicographic earlier strings and lower for later elements.
     */
    private def int priorityOf(String label) {
        // Take the first 6 alphabetical chars as lowercase chars to limit them to one of 26 values each.
        // 6 chars are taken, because 26^6 < 2^32 < 26^7, so 6 chars is the limit of what can be represented as a unique
        // orderable int, which can take 2^32 different values.
        val alphabeticalChars = label.chars.filter [
            Character.isAlphabetic(it)
        ].map [
            Character.toLowerCase(it)
        ].limit(6).toArray.toList
        val numChars = alphabeticalChars.size
        
        // Convert the remaining string into a number as if the string would be a base-26 number with 'a' as the lowest
        // and 'z' as the highest digit for that number. 
        val number = alphabeticalChars.fold(0, [ prevValue, nextChar |
            return prevValue * 26 + nextChar - 'a' + 1
        ])
        // Always have the *26 happen 6 times, so do the remaining ones here if the fold exited earlier.
        // Also return the negative value to have the ordering going a->z and not z->a.
        return -number * Math.toIntExact(Math.round(Math.pow(26, 6 - numChars)))
    }
    
    /**
     * Transformation as an overview of all service interfaces in the given list of service interfaces.
     * 
     * @param serviceInterfaces The list of service interfaces to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformServiceInterfacesOverview(List<ServiceInterface> serviceInterfaces) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Service Interfaces")
            children += serviceInterfaces.map[ transform ]
            initiallyCollapse
        ]
    }
    
    /**
     * Transformation of a simple view of a service interface that provides functionality to be expanded, when the
     * specific synthesis for service interfaces is called.
     * 
     * @param s The service interface that should be transformed into a simple service interface rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(ServiceInterface s) {
        return s.createNode() => [
            associateWith(s)
            addGenericRendering(s.name)
        ]
    }
    
    /**
     * Transformation as an overview of all imported packages in the given list of imported packages.
     * 
     * @param packages The list of packages to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformImportedPackagesOverview(List<PackageObject> packages) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Imported Packages")
            children += packages.map[ transform ]
            initiallyCollapse
        ]
    }
    
    /**
     * Transformation of a simple view of a packages that provides functionality to be expanded, when the specific
     * synthesis for packages is called.
     * 
     * @param p The package that should be transformed into a simple package rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(PackageObject p) {
        return p.createNode() => [
            associateWith(p)
            addGenericRendering(p.uniqueId)
        ]
    }
    
    /**
     * Transformation as an overview of all bundle categories in the given list of bundle categories.
     * 
     * @param bundleCategory The list of bundle categories to show in the overview.
     * @return A node containing the overview representation.
     */
    def KNode transformBundleCategoriesOverview(List<BundleCategory> bundleCategory) {
        return createNode => [
            configureOverviewLayout
            addOverviewRendering("Bundle Categories")
            children += bundleCategory.map[ transform ]
            initiallyCollapse
        ]
    }
    
    /**
     * Transformation of a simple view of a bundle category that provides functionality to be expanded, when the
     * specific synthesis for bundle categories is called.
     * 
     * @param b The bundle category that should be transformed into a simple bundle category rendering.
     * @return A node containing the simple representation.
     */
    def KNode transform(BundleCategory b) {
        return b.createNode() => [
            associateWith(b)
            addGenericRendering(b.categoryName)
        ]
    }
    
    /**
     * Configures the layout of any overview node. Configures the box layout algorithm of elk.
     */
    private def void configureOverviewLayout(KNode node) {
        node => [
            setLayoutOption(CoreOptions::ALGORITHM, "org.eclipse.elk.box")
//            setLayoutOption(CoreOptions::EXPAND_NODES, true) // TODO: why does this not work on bundles?
        ]
    }
}