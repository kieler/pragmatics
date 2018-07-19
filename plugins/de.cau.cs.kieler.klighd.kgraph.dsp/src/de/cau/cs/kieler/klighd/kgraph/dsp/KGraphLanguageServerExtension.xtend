/*
 * Copyright (C) 2017 TypeFox and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
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

@Singleton
class KGraphLanguageServerExtension extends IdeLanguageServerExtension {
    
    @Inject Provider<KGraphDiagramGenerator> diagramGeneratorProvider
    
    @Inject KGraphDiagramState diagramState
	
	override protected initializeDiagramServer(IDiagramServer server) {
		super.initializeDiagramServer(server)
		val kGraphAware = server as KGraphAwareDiagramServer
		kGraphAware.needsServerLayout = true
		kGraphAware.needsClientLayout = false
		LOG.info("Created diagram server for " + server.clientId)
	}
	
	override didClose(String clientId) {
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
                        val kgraphContext = diagramGenerator.translateModel(context.resource.contents.head)
                        diagramState.putKGraphContext(context.resource.URI.toString, kgraphContext)
                        val sgraph = diagramGenerator.generate(
                            context.resource, kgraphContext.viewModel, server.diagramState, context.cancelChecker
                        )
                        diagramState.putElementMapping(context.resource.URI.toString, diagramGenerator.mapping)
                        diagramState.putTexts(context.resource.URI.toString, diagramGenerator.getModelLabels)
                        diagramState.putTextMapping(context.resource.URI.toString, diagramGenerator.textMapping)
                        sgraph
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
