/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kwebs.server.web

/**
 * Presents a HTML page with input elements for a textual graph element and a config area.
 * Upon pressing a 'layout' button, the layouted graph is presented as an svg.
 * 
 * @author uru
 */
class LiveProvider extends AbstractProvider {

	/**
	 * 
	 */
	override getHeaders(ResourceProcessingExchange processingExchange) {
		'''
		<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
		<script	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
		<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
		<script src="scripts/jquery.event.drag.js"></script>
		<script src="scripts/jquery.mousewheel.js"></script>
		<script src="scripts/jquery.svg.js"></script>
		<script src="scripts/jquery.svg.extras.js"></script>
		
		<style>
			body {
				background-color: #5781BB;
			}
		
		  	#srcArea, #configArea {
				width: 100%;
				height: 300px;
				font-size: 10px;
			}
			
			#resGraph {
				width: 100%;
				height: 500px;
			}
			
			.container-fluid {
			}
			
			.row-fluid {
				min-width: 10px;
			}
			
			svg {
				width: 100%;
				height: 100%;
			}
			
			td.body {
				padding-left: 10px;
			} 

			textarea {
				resize: none;
			}
		</style>
		'''
	}
	
	/**
	 * 
	 */
	override getBody(ResourceProcessingExchange processingExchange) {
		val exGraph = "digraph finite_state_machine {\n   rankdir=LR;\n   size=\"8,5\"\n  node [shape = doublecircle]; LR_0 LR_3 LR_4 LR_8;\n node [shape = circle];\n    LR_0 -> LR_2 [ label = \"SS(B)\" ];\n   LR_0 -> LR_1 [ label = \"SS(S)\" ];\n   LR_1 -> LR_3 [ label = \"S($end)\" ];\n LR_2 -> LR_6 [ label = \"SS(b)\" ];\n   LR_2 -> LR_5 [ label = \"SS(a)\" ];\n   LR_2 -> LR_4 [ label = \"S(A)\" ];\n    LR_5 -> LR_7 [ label = \"S(b)\" ];\n    LR_5 -> LR_5 [ label = \"S(a)\" ];\n    LR_6 -> LR_6 [ label = \"S(b)\" ];\n    LR_6 -> LR_5 [ label = \"S(a)\" ];\n    LR_7 -> LR_8 [ label = \"S(b)\" ];\n    LR_7 -> LR_5 [ label = \"S(a)\" ];\n    LR_8 -> LR_6 [ label = \"S(b)\" ];\n    LR_8 -> LR_5 [ label = \"S(a)\" ];\n}";
		val exOptions = "spacing: 100,\nalgorithm: de.cau.cs.kieler.klay.layered";
		
		'''
			<div class="">
				<div class="row-fluid">
					<div id="srcGraph" class="span8">
						<h4>Input Graph</h4>
						<textarea id="srcArea">«exGraph»</textarea>
					</div>
					<div id="config" class="span4">
						<h4>Config</h4>
						<textarea id="configArea">«exOptions»</textarea>
					</div>
				</div>
				<div class="span12 "><button id="layout" class="btn pull-right" style="margin-right: 10px">Layout</button><span id="working"></span></div>
			</div>
			<div id="resGraph" class=""></div>
			<script>
			$(function() {

				// init the svg with zoom and pan
				$('#resGraph').svg();
				$('#resGraph').addScalability();
				$('#resGraph').addDraggable();
				
				var svg = $('#resGraph');
				
				$('#layout').click(function() {
					var graph = $("#srcArea").val();
					var config = '{' + $('#configArea').val() + '}'; 
					
					$.ajax({
						type: 'GET',
						url: '/live', 
						data: {
							graph: graph,
							config: config
						}, 
						success: function(svggraph) {
							svg.html(svggraph);
							console.log("got it");
						}
					});
				});
			});
			</script>
		'''
	}
	
	/**
	 * 
	 */
	override providerOverride(ResourceProcessingExchange processingExchange) {
		return false;
	}
	
}