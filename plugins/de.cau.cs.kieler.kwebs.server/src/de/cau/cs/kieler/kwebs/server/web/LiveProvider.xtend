/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2013 by
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
import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService

class LiveProvider extends AbstractProvider {

	val DEFAULT_INPUT_FORMAT = "org.graphviz.dot"
	val DEFAULT_OUTPUT_FORMAT = "org.w3.svg"

	/**
	 * 
	 */
	override getHeaders(ResourceProcessingExchange processingExchange) {
		'''
		<link href="styles/bootstrap-3.0.2.min.css" rel="stylesheet">
		<link href="styles/prettify.css" type="text/css" rel="stylesheet" />
		
		<script	src="scripts/jquery-1.10.2.min.js"></script>
		<script src="scripts/bootstrap-3.0.2.min.js"></script>
		<script src="scripts/jquery.event.drag.js"></script>
		<script src="scripts/jquery.mousewheel.js"></script>
		<script src="scripts/jquery.svg.js"></script>
		<script src="scripts/jquery.svg.extras.js"></script>
		<script src="scripts/prettify.js"></script>
		
		<style>
			body {
				background-color: #5781BB;
			}
			
			.alert-error {
				font-size: 13px;
			}
		
			[class*="span"] {
				margin-left: 0px;	
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
			
			.row {
			    margin-bottom: 5px;  
			}
			
			svg {
				width: 100%;
				height: 100%;
			}

			textarea {
				resize: none;
			}
			
			.pre-scrollable {
				max-height: 500px;
			}
			
			pre.prettyprint {
				border: 1px solid rgba(0, 0, 0, 0.15);
			}
		</style>
		'''
	}
	
	/**
	 * 
	 */
	override getBody(ResourceProcessingExchange processingExchange) {
		val formats = ServerLayoutDataService::instance.serviceDataModel.supportedFormats
		
		'''
			<div class="container">
				<div class="row">
					<div id="srcGraph" class="col-md-8">
						<h4>Input Graph</h4>
			            <textarea id="srcArea">
			            </textarea>
					</div>
					<div id="config" class="col-md-4">
						<h4>Config</h4>
			            <textarea id="configArea">
			            </textarea>
					</div>
				</div>
				<div class="row">
				    <div class="col-md-12">
					<div class="col-md-3 input-group">
						<span class="input-group-addon">Input Format</span>
						<select id="inputFormat" class="form-control">
							«formats.map(f |
								'''<option «if(f.id == DEFAULT_INPUT_FORMAT) '''selected="selected"'''» value="«f.id»">«f.name»</option>'''
							).join»
						</select>
					</div>
					<div class=" col-md-3 input-group">
						<span class="input-group-addon">Output Format</span>
						<select id="outputFormat" class="form-control">
							«formats.map(f |
								'''<option «if(f.id == DEFAULT_OUTPUT_FORMAT) '''selected="selected"'''» value="«f.id»">«f.name»</option>'''
							).join»
						</select>
					</div>
					<button id="layout" class="btn pull-right" style="margin-right: 10px">Layout</button><span id="working"></span>
					</div>
				</div>
			
				<div class="row">
				    <div class="col-md-12">
					   <div id="errorDiv" class="alert alert-error" style="display: none;"></div>
					</div>
				</div>
				<div class="row">
				    <div id="resGraph" class="col-md-12"></div>
				</div>
			 
			 </div>
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
					var iFormat = $('#inputFormat > option:selected').val();
					var oFormat = $('#outputFormat > option:selected').val();
					
					$.ajax({
						type: 'GET',
						url: '/live', 
						data: {
							graph: graph,
							config: config,
							iFormat: iFormat,
							oFormat: oFormat
						}, 
						success: function(svggraph) {
							
							// if not svg, surround with pretty print
							if (oFormat != "org.w3.svg") {
							  // replace xml markup for displaying
			                  svggraph = svggraph.replace(/>/g, '&gt;').replace(/</g, '&lt;');
			                  // make it look nice 
			                  svggraph = "<pre class='pre-scrollable prettyprint'>" + svggraph + "</pre>";
							}
							
							svg.html(svggraph);
							
							// show graph section and hide errorDiv
							$('#resGraph').show();
							$('#errorDiv').hide();
							
							// call the prettifier
							prettyPrint();
						},
						error: function(error) {
							// hide the graph section
							$('#resGraph').hide();
							// show errorDiv
							$('#errorDiv').html(error.responseText);
							$('#errorDiv').show();	
						}
					});
				});
				
				// add initial example data
				var exGraph ="digraph finite_state_machine {\n  rankdir=LR;\n  size=\"8,5\"\n  LR_0 LR_3 LR_4 LR_8;\n  LR_0 -> LR_2 [ label =\"SS(B)\" ];\n  LR_0 -> LR_1 [ label = \"SS(S)\" ];\n  LR_1 -> LR_3 [ label = \"S($end)\" ];\n  LR_2 -> LR_6 [ label =\"SS(b)\" ];\n  LR_2 -> LR_5 [ label = \"SS(a)\" ];\n  LR_2 -> LR_4 [ label = \"S(A)\" ];\n  LR_5 -> LR_7 [ label = \"S(b)\"];\n  LR_5 -> LR_5 [ label = \"S(a)\" ];\n  LR_6 -> LR_6 [ label = \"S(b)\" ];\n  LR_6 -> LR_5 [ label = \"S(a)\" ];\n  LR_7 -> LR_8 [ label = \"S(b)\" ];\n  LR_7 -> LR_5 [ label = \"S(a)\" ];\n  LR_8 -> LR_6 [ label = \"S(b)\" ];\n  LR_8-> LR_5 [ label = \"S(a)\" ];\n}";
				$('#srcArea').val(exGraph);
				var exConfig = "spacing: 100,\nalgorithm: de.cau.cs.kieler.klay.layered";
				$('#configArea').val(exConfig);
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