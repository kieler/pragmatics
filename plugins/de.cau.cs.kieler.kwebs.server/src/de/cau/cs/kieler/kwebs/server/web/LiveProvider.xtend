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

import de.cau.cs.kieler.kwebs.server.layout.ServerLayoutDataService

/**
 * Presents a HTML page with input elements for a textual graph element and a config area.
 * Upon pressing a 'layout' button, the layouted graph is presented as an svg.
 * 
 * @author uru
 */
class LiveProvider extends AbstractProvider {

    // default formats
	private val String DEFAULT_INPUT_FORMAT = "org.json"
	private val String DEFAULT_OUTPUT_FORMAT = "org.w3.svg"

	/**
	 * 
	 */
	override getHeaders(ResourceProcessingExchange processingExchange) {
		'''
		««««<link href="styles/bootstrap-3.0.2.min.css" rel="stylesheet">
		<link href="styles/prettify.css" type="text/css" rel="stylesheet" />
		
		««««<script	src="scripts/jquery-1.10.2.min.js"></script>
		««««<script src="scripts/bootstrap-3.0.2.min.js"></script>
		<script src="scripts/jquery.event.drag.js"></script>
		<script src="scripts/jquery.mousewheel.js"></script>
		<script src="scripts/jquery.svg.js"></script>
		<script src="scripts/jquery.svg.extras.js"></script>
		<script src="scripts/prettify.js"></script>
		
		<style>
			body {
				««««background-color: #5781BB;
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
						<h4>Input Graph <small><a href="Providedlayout.html#formats">See Formats</a></small></h4>
			            <textarea id="srcArea">
			            </textarea>
					</div>
					<div id="config" class="col-md-4">
						<h4>Config <small><a href="Providedlayout.html#algorithms">See Layout Options</a></small></h4>
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
				var exGraph = "{\n  id: \"root\",  \n   children: [{\n      id: \"n1\",  \n     labels: [ { text: \"n1\" } ],\n     width: 100, \n      height: 100\n   },{\n       id: \"n2\", \n      labels: [ { text: \"n2\" } ],\n     width: 100,\n       height: 50,\n       ports: [{\n         id: \"n2_p1\",\n            width: 10,\n            height: 10\n        }],\n       children: [{  \n            id: \"n3\",  \n         labels: [ { text: \"n3\" } ],\n         width: 20,\n            height: 20\n        },{\n           id: \"n4\", \n          labels: [ { text: \"n4\" } ],\n         width: 20,\n            height: 20}\n       ],\n        edges: [{\n         id: \"e4\",  \n         source: \"n3\",\n           target: \"n4\"\n        }]\n    },{\n       id: \"n5\",\n       labels: [ { text: \"n5\" } ],\n     width: 100,\n       height: 50\n    }],\n   edges: [{\n     id: \"e1\",  \n     labels: [ { text: \"e1\" } ],\n     source: \"n1\",\n       target: \"n2\",\n       targetPort: \"n2_p1\"\n },{\n       id: \"e2\",  \n     labels: [ { text: \"e2\" } ],\n     source: \"n1\",\n       target: \"n5\"\n    }]\n}";
				$('#srcArea').val(exGraph);
				var exConfig = "spacing: 100,\nalgorithm: de.cau.cs.kieler.klay.layered,\nedgeRouting: ORTHOGONAL";
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