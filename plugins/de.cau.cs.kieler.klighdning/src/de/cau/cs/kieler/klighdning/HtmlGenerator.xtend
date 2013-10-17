package de.cau.cs.kieler.klighdning

import java.io.File
import org.eclipse.core.resources.IWorkspaceRoot

class HtmlGenerator {
    
    var IWorkspaceRoot root = null;
    var File rootFile = null;

    def String toHtmlRoot(File root) {
        this.rootFile = root 
           
        val html = root.listFiles.sortBy[it.name].map [ project |
            toHtml(project) 
        ].join("\n")
        
        "<ul id='tree'>" + html + "</ul>"
    }
    
    def dispatch String toHtml(File f) {
    	
    	if(f.directory) {
    		var String html = "<li><a href='#' class='folder' data-path='"+ f.relative + "'>" + f.name + "</a>" 
           
        html = html + "<ul>" + f.listFiles.sortBy[it.name].map [ cont |
            toHtml(cont)
        ].join("\n")
        html = html + "</ul></li>";

        return html
    	} else {
    		 val html = "<li><a href='/resource" + f.relative + "' class='file' data-path='"+ f.relative + "'>" + f.name + "</a></li>"
              
        html
    	}
    	
    }
    
    def String relative(File child) {
    	child.absolutePath.replace(rootFile.absolutePath, "").replace(File::separator, "/")
    }
    
    
    
    def String permaLinkPage(String svgData, boolean mightDiffer, String permaString) {
	'''
	<html encoding='UTF8'>
		<head>
			<meta charset='utf-8'>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
			<script src="/js/jquery.mousewheel.js"></script>
			<script src="/js/jquery.svg.js"></script>
			<script src="/js/jquery.svg.extras.js"></script>
			<script src="/js/jquery.event.drag-2.2.js"></script>
			<style type='text/css'> 
				svg { width:100%; height:100%; } body { width:100%; height:100%; }
				.interactive { position: fixed; top: 10px; left: 10px; padding: 5px; color: #0088cc; }
				.interactive > a, .interactive > a:focus, .interactive > a:hover { text-decoration: underline; color: #0088cc;}
				.interactive > a { text-decoration: none; color: #0088cc;}
				.ctrlBox {
				    margin:auto;
				    margin-bottom: 5px;
				    background-color: #eeeeee;
				    border-radius: 6px 6px 6px 6px;
				    border-width: 1px;
				    border-color: #CCCCCC;
				    border-style: solid;
				    border-color: #CCCCCC;
				    border-style: solid;
				    border-style: solid;
				    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
				}
				.ctrlBox:hover {
				    background-color: #dddddd;
				}
			</style>
		</head>
		<body>
		«IF mightDiffer»
			<div>CARE: The file has likely changed since the permalink was created.</div>
		«ENDIF»
			<div id="data">
				«svgData»
			</div>
			<div class="interactive ctrlBox">
				<a href="/KlighDning.html«permaString»">Switch to interactive mode.</a>
			</div>
		</body>
	</html>
	'''
    }
    	
    
}