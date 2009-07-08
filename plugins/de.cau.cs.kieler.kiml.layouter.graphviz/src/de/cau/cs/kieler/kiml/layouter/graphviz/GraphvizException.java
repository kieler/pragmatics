package kiel.layouter.graphviz;

import java.io.IOException;

/*
 * Simple Exception that tells about problems with the Graphviz Process.
 */
@SuppressWarnings("serial")
public class GraphvizException extends IOException {
	
	public GraphvizException(Exception e, String message) {
		super(message+" "+e.getMessage());
	}
	
	public GraphvizException(String message){
		super(message);
	}
	
	public GraphvizException(){
		super();
	}
	
	
}
