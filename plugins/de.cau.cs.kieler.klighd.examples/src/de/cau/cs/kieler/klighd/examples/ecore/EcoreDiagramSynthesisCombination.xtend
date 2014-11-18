package de.cau.cs.kieler.klighd.examples.ecore

import de.cau.cs.kieler.core.kivi.AbstractCombination
import de.cau.cs.kieler.core.kivi.triggers.PartTrigger
import de.cau.cs.kieler.core.kivi.triggers.SelectionTrigger
import de.cau.cs.kieler.klighd.KlighdTreeSelection
import de.cau.cs.kieler.klighd.kivi.effects.KlighdUpdateDiagramEffect
import de.cau.cs.kieler.klighd.krendering.SimpleUpdateStrategy
import de.cau.cs.kieler.klighd.util.KlighdProperties
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties
import java.util.List
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EModelElement
import org.eclipse.emf.ecore.EPackage
import org.eclipse.ui.part.FileEditorInput
import java.lang.reflect.Method
import org.eclipse.ui.IWorkbenchPart

/**
 * Combination that triggers the synthesis of Ecore diagrams.
 * 
 * @author chsch
 */
class EcoreDiagramSynthesisCombination extends AbstractCombination {
    
    new() {
        try {
            navItemClass = Class.forName("org.eclipse.emf.ecoretools.diagram.navigator.EcoreDomainNavigatorItem");
            eObjectGetter = navItemClass.getMethod("getEObject");
        } catch (Throwable t) {
            // nothing
        }
    }
    
    private Class<?> navItemClass = null;
    private Method eObjectGetter = null;
	
	/**
	 * The 'execute()' method, see doc of {@link AbstractCombination}.
	 */
	def public void execute(PartTrigger.PartState es, SelectionTrigger.SelectionState selectionState) {
	    
	    if (selectionState.selection instanceof KlighdTreeSelection) {
	        // do not react on selections in KLighD diagrams
	        return;
	    }
		
		// do not react on partStates as well as on selectionStates in case
		//  a view part has been deactivated recently, as an potentially out-dated selection
		//  is currently about to be processed
		// most certainly a "part activated" event will follow and subsequently a further
		//  selection event if the selection of the newly active part is changed, too! 
		if (this.latestState() == es || es.eventType == PartTrigger.EventType::VIEW_DEACTIVATED) {
		   //inputPath = es.getProperty(PartTrigger::EDITOR_INPUT_PATH) as IPath;
		   return; // do only react on selectionState
		}
		
		val selection = selectionState.selectionElements;
		if (selection.nullOrEmpty) {
		    return;
	    }
	    
        var IPath editorInputPath;
        var String viewId;
        var String viewName;
        var EModelElementCollection modelElements;
        var IWorkbenchPart sourceWorkbenchPart;
        
        if (es.getEditorPart() != null && typeof(FileEditorInput).isInstance(es.getEditorPart().getEditorInput())) {
            //   e.g. by the Ecore tree editor
            
            val FileEditorInput fileEditorInput = typeof(FileEditorInput).cast(es.getEditorPart().getEditorInput());
            if (fileEditorInput.getFile() != null && fileEditorInput.getFile().getLocationURI() != null) {
                editorInputPath = fileEditorInput.getPath();
            }
        }

        if (editorInputPath != null) {
            viewId = editorInputPath.toPortableString().replace(":", "") as String;
            viewName = editorInputPath.lastSegment;
            modelElements = EModelElementCollection::of(
                selectionState.selectionElements.filter(typeof(EModelElement)).toList
            );
        }

        if (modelElements.nullOrEmpty) {
            var List<EModelElement> elements;

            if (selection.forall[typeof(EPackage).isInstance(it) || typeof(EClass).isInstance(it)]) {
                // in case the elements to be depicted are given immediately,
                elements = selection.filter(EModelElement).toList;

            } else if (navItemClass != null && eObjectGetter != null) {
                // this case covers the situation of depicting classes selected in the Project Explorer

                if (selection.forall[navItemClass.isInstance(it)]) {
                    elements = selectionState.selectionElements.filter(navItemClass).map[
                        eObjectGetter.invoke(it);
                    ].filter(typeof(EModelElement)).toList;
                }
            }

            viewId = "de.cau.cs.kieler.klighd.examples.ecore.explorer";
            viewName = "KLighD Class Diagram";

            if (elements != null) {
                // By means of this construction we reduced the conversion of nodes the result from the
                //  insertion of further element between the head and tail of the selection list.
                // We retain all previous selected ones ...
                this.selectedModelElements.retainAll(elements);

                // ... determine the newly selected ones ...
                elements.removeAll(this.selectedModelElements);

                // ... and add them to the selected elements list
                this.selectedModelElements += elements;

                modelElements = EModelElementCollection::of(selectedModelElements);
            }
        }

        if (viewId != null && !modelElements.nullOrEmpty) {
            // in case Ecore elements are selected within the 'Project Explorer' view
            this.schedule(new KlighdUpdateDiagramEffect(viewId, viewName, modelElements, sourceWorkbenchPart) => [
		       	// FIXME reactivate incremental update
                // it.setProperty(LightDiagramServices::REQUESTED_UPDATE_STRATEGY, UpdateStrategy::ID);
                it.setProperty(KlighdSynthesisProperties::REQUESTED_UPDATE_STRATEGY, SimpleUpdateStrategy::ID);
                it.setProperty(KlighdProperties::MODEL_ACCESS, new EcoreModelAccess(this.selectedModelElements));
            ]);
        }
	}
    
    private List<EModelElement> selectedModelElements = newLinkedList();
}