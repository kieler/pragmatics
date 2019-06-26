package de.scheidtbachmann.osgimodel.visualization

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import de.cau.cs.kieler.klighd.DisplayedActionData
import de.cau.cs.kieler.klighd.krendering.ViewSynthesisShared
import de.cau.cs.kieler.klighd.krendering.extensions.KNodeExtensions
import de.cau.cs.kieler.klighd.krendering.extensions.KRenderingExtensions
import de.cau.cs.kieler.klighd.syntheses.AbstractDiagramSynthesis
import de.scheidtbachmann.osgimodel.OsgiProject
import de.scheidtbachmann.osgimodel.visualization.actions.RedoAction
import de.scheidtbachmann.osgimodel.visualization.actions.ResetViewAction
import de.scheidtbachmann.osgimodel.visualization.actions.UndoAction
import de.scheidtbachmann.osgimodel.visualization.context.BundleOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ContextUtils
import de.scheidtbachmann.osgimodel.visualization.context.IVisualizationContext
import de.scheidtbachmann.osgimodel.visualization.context.OsgiProjectContext
import de.scheidtbachmann.osgimodel.visualization.context.ProductOverviewContext
import de.scheidtbachmann.osgimodel.visualization.context.ServiceInterfaceOverviewContext
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.BundleCategoryOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.BundleOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.FeatureOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.PackageObjectOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.ProductOverviewSynthesis
import de.scheidtbachmann.osgimodel.visualization.subsyntheses.ServiceInterfaceOverviewSynthesis
import java.util.LinkedHashSet
import org.eclipse.elk.alg.layered.options.LayeredOptions
import org.eclipse.elk.core.math.ElkPadding
import org.eclipse.elk.core.options.CoreOptions

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
            DisplayedActionData.create(UndoAction.ID, "Undo",
                "Undoes the last action performed on the view model."),
            DisplayedActionData.create(RedoAction.ID, "Redo",
                "Redoes the last action that was undone on the view model."),
            DisplayedActionData.create(ResetViewAction.ID, "Reset View",
                "Resets the view to its default overview state.")
        ]
    }
    
    override getDisplayedLayoutOptions() {
        return ImmutableList::of(
            specifyLayoutOption(LayeredOptions::HIGH_DEGREE_NODES_TREATMENT, #[true, false])
        )
    }
       
    override getDisplayedSynthesisOptions() {
        val options = new LinkedHashSet()
        // Add Bundle options
        options.addAll(SIMPLE_TEXT, DESCRIPTION_LENGTH)
        
        // Add general options
        options.addAll(SHORTEN_BY, FILTER_BY)
        
        return options.toList
    }
    // TODO: Maybe remember a map of all representations of every bundle, ... to calculate which nodes should be 
    // rendered differently later (after synthesis hook) to highlight multiple uses. 
    
    override transform(OsgiProject model) {
        val modelNode = createNode.associateWith(model)
        
        // Create a view with the currently stored visualization context in mind. If there is no current context, create
        // a new one for the general OSGi model overview and store that for later use.
        val visualizationContexts = usedContext.getProperty(OsgiSynthesisProperties.VISUALIZATION_CONTEXTS)
        var index = usedContext.getProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX)
        var IVisualizationContext<?> visualizationContext = null
        
        if (!visualizationContexts.empty && index !== null) {
            visualizationContext = visualizationContexts.get(index)
        }
        // If the visualization context is for another model than the model this method was called for or does not exist
        // yet, reset the contexts.
        if (visualizationContext === null || !ContextUtils.isRootModel(visualizationContext, model)) {
            visualizationContexts.removeIf [ true ]
            index = 0
            usedContext.setProperty(OsgiSynthesisProperties.CURRENT_VISUALIZATION_CONTEXT_INDEX, index)
            visualizationContext = new OsgiProjectContext(model, null)
            visualizationContexts.add(visualizationContext)
        }

        // Make this variable final here for later usage in the lambda.
        val visContext = visualizationContext
        if (visContext instanceof OsgiProjectContext) {
            // This synthesis can be used.
            
            // The overview of the entire OSGi Project.
            modelNode.children += createNode => [
                associateWith(model)
                addOverviewRendering("Overview")
                
                children += createNode => [
                    associateWith(model)
                    addInvisibleContainerRendering
                    
                    // Product overview
                    val productOverviewContext = visContext.productOverviewContext
                    val overviewProductNodes = productOverviewSynthesis.transform(productOverviewContext)
                    children += overviewProductNodes
                    
                    val overviewFeatureNodes = featureOverviewSynthesis.transform(model.features)
                    children += overviewFeatureNodes
                    
                    val bundleOverviewContext = visContext.bundleOverviewContext
                    val overviewBundleNodes = bundleOverviewSynthesis.transform(bundleOverviewContext)
                    children += overviewBundleNodes
                    
                    val serviceInterfaceOverviewContext = visContext.serviceInterfaceOverviewContext
                    val overviewServiceInterfaceNodes = serviceInterfaceOverviewSynthesis.transform(serviceInterfaceOverviewContext)
                    children += overviewServiceInterfaceNodes
                    
                    val overviewImportedPackagesNodes = packageObjectOverviewSynthesis.transform(model.importedPackages)
                    children += overviewImportedPackagesNodes
                    
                    val overviewBundleCategoryNodes = bundleCategoryOverviewSynthesis.transform(model.bundleCategories)
                    children += overviewBundleCategoryNodes
                    
                    // remove the padding of the invisible container.
                    addLayoutParam(CoreOptions.PADDING, new ElkPadding(0, 0, 0, 0))
                ]
            ]
            
            return modelNode
            
        } else {
            // Delegate the view model generation to another subsynthesis that can show the requested visualization context.
            val children = transformSubModel(visualizationContext)
            
            modelNode.children += children
            
            return modelNode
        }
    }
    
    private def transformSubModel(IVisualizationContext<?> context) {
        switch (context) {
//            case context instanceof BundleCategoryOverviewContext: {
//                return bundleCategoryOverviewSynthesis.transform(context as BundleCategoryOverviewContext)
//                
//            }
            case context instanceof BundleOverviewContext: {
                return bundleOverviewSynthesis.transform(context as BundleOverviewContext)
            }
//            case context instanceof FeatureOverviewContext: {
//                return featureOverviewSynthesis.transform(context as FeatureOverviewContext)
//            }
//            case context instanceof PackageObjectOverviewContext: {
//                return packageObjectOverviewSynthesis.transform(context as PackageObjectOverviewContext)
//            }
            case context instanceof ProductOverviewContext: {
                return productOverviewSynthesis.transform(context as ProductOverviewContext)
            }
            case context instanceof ServiceInterfaceOverviewContext: {
                return serviceInterfaceOverviewSynthesis.transform(context as ServiceInterfaceOverviewContext)
            }
            default: {
                throw new IllegalArgumentException("The context class has no known subsynthesis: " + context.class)
            }
        }
    }
    
}