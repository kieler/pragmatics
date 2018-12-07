/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2018 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.inject.Inject
import de.cau.cs.kieler.klighd.microlayout.Bounds
import de.cau.cs.kieler.klighd.util.KlighdProperties
import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.ActionMessage
import io.typefox.sprotty.api.SModelRoot
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer
import org.apache.log4j.Logger

/**
 * Diagram server extension adding functionality to special actions needed for handling KGraphs.
 * 
 * @author nir
 */
public class KGraphAwareDiagramServer extends LanguageAwareDiagramServer {
    private static val LOG = Logger.getLogger(KGraphAwareDiagramServer)
    
    @Inject 
    protected KGraphDiagramState diagramState
    
    protected var SModelRoot currentRoot
    
    protected def requestTextSizesAndUpdateModel(SModelRoot newRoot) {
        currentRoot = newRoot
        if (newRoot !== null) {
            val texts = diagramState.getTexts(newRoot.id)
            if (texts === null) {
                throw new NullPointerException("The id of the SGraph was not found in the diagramState")
            }
            val textDiagram = KGraphDiagramGenerator.generateTextDiagram(texts, newRoot.id)
            dispatch(new RequestTextBoundsAction(textDiagram))
            // the updateModel is then executed after the client returns with its ComputedTextBoundsAction
        } else {
            updateModel(newRoot)
        }
    }
    
    override void accept(ActionMessage message) {
        val clientId = getClientId();
        if (clientId !== null && clientId.equals(message.getClientId())) {
            // call the handle function for the ComputedTextBoundsAction or forward the call to the super implementation
            val Action action = message.getAction();
            if (action.getKind() === ComputedTextBoundsAction.KIND) {
                handle(action as ComputedTextBoundsAction)
            } else {
                super.accept(message)
            }
        }
    }
    
    /**
     * Called when a {@link ComputedTextBoundsAction} is received.
     * Maps the bounds for all texts referenced in the action back to their corresponding {@link KText} elements
     * and updates the model on the client.
     */
    protected def handle(ComputedTextBoundsAction action) {
        // assume the model is still stored in 'currentRoot', since the ComputedTextBoundsAction only gets issued
        // after a RequestTextBoundsAction, where it got stored before.
        
        val textMapping = diagramState.getTextMapping(currentRoot.id)
        for (elementAndBound : action.bounds) {
            val elementId = elementAndBound.elementId
            val newBounds = elementAndBound.newBounds
            if (newBounds === null) {
                throw new NullPointerException("Estimated Bounds for a KText are null!")
            }
            val newBounds_klighd = new Bounds(newBounds.x as float, newBounds.y as float,
                newBounds.width as float, newBounds.height as float)
            val kText = textMapping.get(elementId)
            if (kText === null) {
                LOG.info("The textMapping does not contain the referenced Text anymore. The model has changed before" + 
                    "completion of the request. Terminating this request.")
                return
            }
            kText.properties.put(KlighdProperties.CALCULATED_TEXT_BOUNDS, newBounds_klighd)
        }
        updateModel(currentRoot)
    }
}