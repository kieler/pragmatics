package de.scheidtbachmann.osgimodel.visualization

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.klighd.DisplayedActionData
import de.cau.cs.kieler.klighd.LightDiagramServices
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.visualization.actions.UnfocusAction
import de.scheidtbachmann.osgimodel.visualization.actions.UnfocusAllAction
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.BundleCategoryOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.BundleOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.FeatureOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.PackageObjectOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.ProductOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.ServiceInterfaceOverviewSynthesis
import java.util.LinkedHashSet
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.graph.properties.MapPropertyHolder

import static de.scheidtbachmann.osgimodel.visualization.OsgiOptions.*

/**
 * Main diagram synthesis for {@link OsgiProject} models.
 * 
 * @author nre
 */
@ViewSynthesisShared
class OsgiDiagramSynthesis extends AbstractDiagramSynthesis<OsgiProject> {
    @Inject extension KNodeExtensions
    @Inject extension KRenderingExtensions
    @Inject extension OsgiStyles
    
    @Inject BundleCategoryOverviewSynthesis bundleCategoryOverviewSynthesis
    @Inject BundleOverviewSynthesis bundleOverviewSynthesis
    @Inject FeatureOverviewSynthesis featureOverviewSynthesis
    @Inject PackageObjectOverviewSynthesis packageObjectOverviewSynthesis
    @Inject ProductOverviewSynthesis productOverviewSynthesis
    @Inject ServiceInterfaceOverviewSynthesis serviceInterfaceOverviewSynthesis
    
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
                
                val overviewProductNodes = productOverviewSynthesis.transform(model.products)
                overviewProductNodes.forEach [ associateWith(model) ]
                children += overviewProductNodes
                
                val overviewFeatureNodes = featureOverviewSynthesis.transform(model.features)
                overviewFeatureNodes.forEach [ associateWith(model) ]
                children += overviewFeatureNodes
                
                val overviewBundleNodes = bundleOverviewSynthesis.transform(model.bundles)
                overviewBundleNodes.forEach [ associateWith(model) ]
                children += overviewBundleNodes
                
                val overviewServiceInterfaceNodes = serviceInterfaceOverviewSynthesis.transform(model.serviceInterfaces)
                overviewServiceInterfaceNodes.forEach [ associateWith(model) ]
                children += overviewServiceInterfaceNodes
                
                val overviewImportedPackagesNodes = packageObjectOverviewSynthesis.transform(model.importedPackages)
                overviewImportedPackagesNodes.forEach [ associateWith(model) ]
                children += overviewImportedPackagesNodes
                
                val overviewBundleCategoryNodes = bundleCategoryOverviewSynthesis.transform(model.bundleCategories)
                overviewBundleCategoryNodes.forEach [ associateWith(model) ]
                children += overviewBundleCategoryNodes
                
                // remove the padding of the invisible container.
//                addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0)) // TODO: test this.
            ]
        ]
        
        return modelNode
    }
    
}