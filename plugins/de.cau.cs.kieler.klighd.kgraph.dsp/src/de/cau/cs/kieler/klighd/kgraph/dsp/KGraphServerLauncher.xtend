/*
 * Copyright (C) 2017 TypeFox and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */
package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.gson.GsonBuilder
import com.google.inject.Guice
import com.google.inject.Inject
import io.typefox.sprotty.layout.ElkLayoutEngine
import de.cau.cs.kieler.kgraph.text.KGraphRuntimeModule
import de.cau.cs.kieler.kgraph.text.ide.KGraphIdeModule
import de.cau.cs.kieler.kgraph.text.ide.KGraphIdeSetup
import java.util.concurrent.Executors
import java.util.function.Consumer
import java.util.function.Function
import org.apache.log4j.AppenderSkeleton
import org.apache.log4j.AsyncAppender
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.log4j.spi.LoggingEvent
import org.eclipse.elk.alg.layered.options.LayeredMetaDataProvider
import org.eclipse.lsp4j.MessageParams
import org.eclipse.lsp4j.MessageType
import org.eclipse.lsp4j.jsonrpc.Launcher
import org.eclipse.lsp4j.jsonrpc.MessageConsumer
import org.eclipse.lsp4j.jsonrpc.validation.ReflectiveMessageValidator
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.ide.server.LanguageServerImpl
import org.eclipse.xtext.ide.server.LaunchArgs
import org.eclipse.xtext.ide.server.ServerLauncher
import org.eclipse.xtext.ide.server.ServerModule
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.util.Modules2
import java.util.concurrent.Future
import de.cau.cs.kieler.klighd.kgraph.dsp.gson_utils.KGraphTypeAdapterUtil

class KGraphServerLauncher extends ServerLauncher {
	
	def static void main(String[] args) {
		
		// Initialize ELK
		ElkLayoutEngine.initialize(new LayeredMetaDataProvider)

		// Do a manual setup that includes the KGraph diagram module
		new KGraphIdeSetup {
			override createInjector() {
                Guice.createInjector(Modules2.mixin(
                    new KGraphRuntimeModule, 
                    new KGraphIdeModule, 
                    new KGraphDiagramModule// , // include this next part if KRendering extensions are needed
//                    new Module() {
//                        override configure(Binder binder) {
//                            binder.bindScope(ViewSynthesisShared, Scopes.SINGLETON)
//                        }
//                        
//                    }
                ))
            }
		}.createInjectorAndDoEMFRegistration()
		
		// Launch the server
		launch(ServerLauncher.name, args, Modules2.mixin(new ServerModule, [
			bind(ServerLauncher).to(KGraphServerLauncher)
			bind(IResourceServiceProvider.Registry).toProvider(IResourceServiceProvider.Registry.RegistryProvider)
		]))
	}

	@Inject LanguageServerImpl languageServer
	
	override start(LaunchArgs args) {
		val executorService = Executors.newCachedThreadPool
		val Consumer<GsonBuilder> configureGson = [ gsonBuilder |
            KGraphTypeAdapterUtil.configureGson(gsonBuilder)
		]
		val launcher = Launcher.createIoLauncher(languageServer, LanguageClient, args.in, args.out, executorService,
				args.wrapper , configureGson)
		val client = launcher.remoteProxy
		languageServer.connect(client)
		// Redirect Log4J output to a file
		Logger.rootLogger => [
			removeAllAppenders()
			addAppender(new AsyncAppender() => [
				addAppender(new LanguageClientAppender(client))
			])
		]
		val Future<?> future = launcher.startListening
		while (!future.done) {
			Thread.sleep(10_000l)
		}
	}
	
	private def Function<MessageConsumer, MessageConsumer> getWrapper(LaunchArgs args) {
		[ consumer |
			var result = consumer
			if (args.trace !== null) {
				result = [ message |
					args.trace.println(message)
					args.trace.flush()
					consumer.consume(message)
				]
			}
			if (args.validate) {
				result = new ReflectiveMessageValidator(result)
			}
			return result
		]
	}
	
	@Data static class LanguageClientAppender extends AppenderSkeleton {
		LanguageClient client
		
		override protected append(LoggingEvent event) {
			client.logMessage(new MessageParams => [
				message = event.message.toString 
					+ if(event.throwableStrRep !== null && event.throwableStrRep.length > 0) 
						': ' + event.throwableStrRep?.join('\n')
					  else 
					    ''
				type = switch event.getLevel {
					case Level.ERROR: MessageType.Error
					case Level.INFO : MessageType.Info
					case Level.WARN : MessageType.Warning
					default : MessageType.Log
				}
			])
		}
		
		override close() {
			
		}
		
		override requiresLayout() {
			return false
		}
		
	}
}
