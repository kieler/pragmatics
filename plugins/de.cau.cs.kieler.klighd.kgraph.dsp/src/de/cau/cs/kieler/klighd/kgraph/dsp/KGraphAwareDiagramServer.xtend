/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright ${year} by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.inject.Inject
import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.ActionMessage
import io.typefox.sprotty.api.SModelRoot
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer

/**
 * @author stu114054
 *
 */
class KGraphAwareDiagramServer extends LanguageAwareDiagramServer {
    
    @Inject KGraphDiagramState diagramState
    @Inject com.google.inject.Provider<KGraphDiagramGenerator> diagramGeneratorProvider
    
    var SModelRoot currentRoot
    
    
    def requestTextSizesAndUpdateModel(SModelRoot newRoot) {
        currentRoot = newRoot
        if (newRoot !== null) {
            val texts = diagramState.getTexts(newRoot.id)
            if (texts === null) {
                throw new NullPointerException("The id of the SGraph was not found in the diagramState")
            }
            val diagramGenerator = diagramGeneratorProvider.get
            val textDiagram = diagramGenerator.generateTextDiagram(texts, newRoot.id)
            dispatch(new RequestTextBoundsAction(textDiagram))
            // the updateModel is then executed after the client returns with its ComputedTextBoundsAction
        } else {
            updateModel(newRoot)
        }
    }
    
    
    override void accept(ActionMessage message) {
        val clientId = getClientId();
        if (clientId !== null && clientId.equals(message.getClientId())) {
            val Action action = message.getAction();
            if (action.getKind() === ComputedTextBoundsAction.KIND) {
                handle(action as ComputedTextBoundsAction)
            } else {
                super.accept(message)
            }
        }
    }
    
    def handle(ComputedTextBoundsAction action) {
        // assume the model is still stored in 'currentRoot', since the ComputedTextBoundsAction only gets issued
        // after a RequestTextBoundsAction, where it got stored before.
        
        val textMapping = diagramState.getTextMapping(currentRoot.id)
        for (elementAndBound : action.bounds) {
            val elementId = elementAndBound.elementId
            val newBounds = elementAndBound.newBounds
            textMapping.get(elementId)//.properties.add(BoundsProperty, newBounds) or something // TODO:
        }
        
        updateModel(currentRoot)
    }
}