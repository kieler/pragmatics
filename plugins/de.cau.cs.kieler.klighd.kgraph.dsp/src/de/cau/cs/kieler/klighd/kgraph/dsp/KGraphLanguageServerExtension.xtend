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
import com.google.inject.Provider
import com.google.inject.Singleton
import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.server.xtext.ILanguageAwareDiagramServer
import io.typefox.sprotty.server.xtext.ide.IdeLanguageServerExtension
import java.util.List
import java.util.concurrent.CompletableFuture

import static io.typefox.sprotty.api.ServerStatus.Severity.*

/**
 * Language server extension that implements functionality for the generation of diagrams and handling of their diagram
 * servers.
 * Based on the yang-lsp implementation by TypeFox.
 * 
 * @author nir
 * @see <a href="https://github.com/theia-ide/yang-lsp/blob/master/yang-lsp/io.typefox.yang.diagram/src/main/java/io/typefox/yang/diagram/YangLanguageServerExtension.xtend">
 *      YangLanguageServerExtension</a>
 */
@Singleton
class KGraphLanguageServerExtension extends IdeLanguageServerExtension {
    
    @Inject
    Provider<KGraphDiagramGenerator> diagramGeneratorProvider
    
    @Inject
    KGraphDiagramState diagramState
	
	override protected initializeDiagramServer(IDiagramServer server) {
		super.initializeDiagramServer(server)
		val kGraphAware = server as KGraphAwareDiagramServer
		kGraphAware.needsServerLayout = true
		kGraphAware.needsClientLayout = false
		LOG.info("Created diagram server for " + server.clientId)
	}
	
	override didClose(String clientId) {
	    synchronized(diagramState) {
	        diagramState.remove(clientId)
	    }
		super.didClose(clientId)
		LOG.info("Removed diagram server for " + clientId)
	}
	
	override protected doUpdateDiagrams(String path, List<? extends ILanguageAwareDiagramServer> diagramServers) {
        if (diagramServers.empty) {
            return CompletableFuture.completedFuture(null)
        }
        return path.doRead [ context |
            val status = context.resource.shouldGenerate(context.cancelChecker)
            
            return (diagramServers as List<KGraphAwareDiagramServer>).map [ server |
                server -> {
                    server.status = status
                    if (status.severity !== ERROR) {
                        val diagramGenerator = diagramGeneratorProvider.get
                        val kGraphContext = KGraphDiagramGenerator.translateModel(context.resource.contents.head)
                        synchronized(diagramState) {
                            diagramState.putURIString(server.clientId, context.resource.URI.toString)
                            diagramState.putKGraphContext(context.resource.URI.toString, kGraphContext)
                        }
                        
                        val sGraph = diagramGenerator.toSGraph(
                            kGraphContext.viewModel, context.resource.URI.toString, context.cancelChecker)
                        synchronized(diagramState) {
                            diagramState.putKGraphToSModelElementMap(context.resource.URI.toString,
                                diagramGenerator.getKGraphToSModelElementMap)
                            diagramState.putTexts(context.resource.URI.toString, diagramGenerator.getModelLabels)
                            diagramState.putTextMapping(context.resource.URI.toString, diagramGenerator.getTextMapping)
                        }
                        sGraph
                    } else {
                        null
                    }
                }
            ]
        ].thenAccept [ resultList |
            resultList.filter[value !== null].forEach[key.requestTextSizesAndUpdateModel(value)]
        ].exceptionally [ throwable |
            LOG.error('Error while processing build results', throwable)
            return null
        ]
    }
}
