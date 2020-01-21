/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 * 
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2020 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.klighd.lsp.interactive.rectpack

import com.google.inject.Inject
import com.google.inject.Injector
import de.cau.cs.kieler.klighd.internal.util.KlighdInternalProperties
import de.cau.cs.kieler.klighd.kgraph.KNode
import de.cau.cs.kieler.klighd.lsp.KGraphDiagramState
import de.cau.cs.kieler.klighd.lsp.LSPUtil
import javax.inject.Singleton
import org.eclipse.elk.alg.packing.rectangles.options.RectPackingOptions
import org.eclipse.elk.graph.ElkNode
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtext.ide.server.ILanguageServerAccess
import org.eclipse.xtext.ide.server.ILanguageServerExtension
import org.eclipse.xtext.resource.XtextResourceSet

/**
 * @author sdo
 * 
 */
@Singleton
class RectPackInterativeLanguageServerExtension implements ILanguageServerExtension {

    @Accessors LanguageClient client;

    @Inject
    KGraphDiagramState diagramState

    @Inject
    Injector injector

    override initialize(ILanguageServerAccess access) {
    }
    
    /**
     * Set order constraint of node specified by node id.
     * This changes all order values of all constraints of a previous layout run.
     * 
     * @param constraint constraint to be set
     * @param clientId identifier of diagram
     */
    def setOrderConstraint(RectPackSetOrderConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val root = LSPUtil.getRoot(diagramState, uri)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id, root, RectPackingOptions.INTERACTIVE)
        val previousPosition = kNode.getProperty(RectPackingOptions.PREVIOUS_POSITION)
        val desiredPosition = kNode.getProperty(RectPackingOptions.DESIRED_POSITION)

        // Find current actual position of node (it is the desired position or the previous if desired is not set)
        var actualPosition = previousPosition
        if (desiredPosition !== null) {
            actualPosition = desiredPosition
        }
        kNode.setProperty(RectPackingOptions.DESIRED_POSITION, constraint.order)
        val modifier = constraint.order > actualPosition ? 1 : -1
        for (var position = actualPosition + modifier; position <= actualPosition; position += modifier) {
            var KNode currentNode = root.children.get(position)

            // Update node position of already fixed nodes
            if (currentNode.hasProperty(RectPackingOptions.DESIRED_POSITION)) {
                currentNode.setProperty(RectPackingOptions.DESIRED_POSITION, position - modifier)
            }
        }
        refreshModelInEditor(kNode, uri)
    }

    def deleteOrderConstraint(RectPackDeleteOrderConstraint constraint, String clientId) {
        val uri = diagramState.getURIString(clientId)
        val root = LSPUtil.getRoot(diagramState, uri)
        val kNode = LSPUtil.getKNode(diagramState, uri, constraint.id, root, RectPackingOptions.INTERACTIVE)
        val previousPosition = kNode.getProperty(RectPackingOptions.PREVIOUS_POSITION)
        val desiredPosition = kNode.getProperty(RectPackingOptions.DESIRED_POSITION)

        kNode.setProperty(RectPackingOptions.DESIRED_POSITION, null)
        val modifier = desiredPosition > previousPosition ? -1 : 1
        for (var position = desiredPosition + modifier; position <= previousPosition; position += modifier) {
            var KNode currentNode = root.children.get(position)

            // Update node position of already fixed nodes
            if (currentNode.hasProperty(RectPackingOptions.DESIRED_POSITION)) {
                currentNode.setProperty(RectPackingOptions.DESIRED_POSITION, position - modifier)
            }
        }
        refreshModelInEditor(kNode, uri)
    }

    /**
     * Changes property changes defined by changedNodes to the resource
     * @param kNode just some kNode of the correct graph
     * @param uri uri of resource
     * TODO use some kind of updateDocument mechanism and do not just modify the contents of the resource directly
     */
    def refreshModelInEditor(KNode kNode, String uri) {
        val resource = injector.getInstance(XtextResourceSet).getResource(URI.createURI(uri), true)

        val elkNode = kNode.getProperty(KlighdInternalProperties.MODEL_ELEMEMT)
        if (elkNode instanceof ElkNode) {
            val elkGraph = EcoreUtil.getRootContainer(elkNode)
            resource.contents.clear
            resource.contents += elkGraph
            resource.save(emptyMap)
        }

    }
}
