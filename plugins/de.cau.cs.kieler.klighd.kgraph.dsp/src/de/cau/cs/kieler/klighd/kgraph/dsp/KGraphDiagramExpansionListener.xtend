package de.cau.cs.kieler.klighd.kgraph.dsp

import io.typefox.sprotty.api.Action
import io.typefox.sprotty.api.IDiagramExpansionListener
import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.server.xtext.LanguageAwareDiagramServer

class KGraphDiagramExpansionListener implements IDiagramExpansionListener {
	
	override expansionChanged(Action action, IDiagramServer server) {
	    // TODO: implement changing of the rendering / calling the ExpandAndCollapseAction
		if (server instanceof LanguageAwareDiagramServer) {
			val languageServerExtension = server.languageServerExtension
			languageServerExtension.updateDiagram(server)
		}
	}
}