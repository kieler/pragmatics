package de.cau.cs.kieler.klighd.kgraph.dsp

import com.google.inject.Inject
import io.typefox.sprotty.api.IDiagramServer
import io.typefox.sprotty.api.IPopupModelFactory
import io.typefox.sprotty.api.RequestPopupModelAction
import io.typefox.sprotty.api.SModelElement
import io.typefox.sprotty.server.xtext.tracing.ITraceProvider

class KGraphPopupModelFactory implements IPopupModelFactory {

	@Inject extension ITraceProvider

	override createPopupModel(SModelElement element, RequestPopupModelAction request, IDiagramServer server) {
	    // TODO: program similar to this if needed
//		if (element instanceof Traceable) {
//			val future = element.withSource(server as ILanguageAwareDiagramServer) [ statement, context |
//				if (statement instanceof Statement) 
//					createPopup(statement, element, request)
//				else
//					null
//			]
//			future.get
//		} else {
//			null
//		}
	}

//	protected def createPopup(Statement stmt, SModelElement element, RequestPopupModelAction request) {
//		val popupId = element.id + '-popup'
//		val infos = new ArrayList<Pair<String, String>>
//
//		for (statement : stmt.substatements) {
//			val info = createHtml(statement)
//			if(info !== null) infos.add(info)
//		}
//		if (!infos.empty)
//			new HtmlRoot [
//				type = 'html'
//				id = popupId
//				children = #[
//					new PreRenderedElement [
//						type = 'pre-rendered'
//						id = popupId + '-body'
//						code = '''
//							<div class="infoBlock">
//								«FOR info : infos»
//									<div class="sprotty-infoRow">
//										<div class="sprotty-infoTitle">«info.key»:</div>
//										<div class="sprotty-infoText">«info.value»</div>
//									</div>
//								«ENDFOR»
//							</div>
//						'''
//					]
//				]
//				canvasBounds = request.bounds
//			]
//			
//	}

//	protected def dispatch Pair<String, String> createHtml(Statement statement) {
//	}
//
//	protected def dispatch Pair<String, String> createHtml(Prefix prefixStmt) {
//		'Prefix' -> prefixStmt.prefix
//	}
//
//	protected def dispatch Pair<String, String> createHtml(Namespace namespaceStmt) {
//		'Namespace' -> namespaceStmt.uri
//	}
//
//	protected def dispatch Pair<String, String> createHtml(YangVersion yangVersionStmt) {
//		'Yang version' -> yangVersionStmt.yangVersion
//	}
//
//	protected def dispatch Pair<String, String> createHtml(Description descriptionStmt) {
//		'Description' -> descriptionStmt.description
//	}

}
