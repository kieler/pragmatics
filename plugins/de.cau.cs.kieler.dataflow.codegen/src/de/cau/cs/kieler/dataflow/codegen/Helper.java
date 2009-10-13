/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 ******************************************************************************/

package de.cau.cs.kieler.dataflow.codegen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import de.cau.cs.kieler.dataflow.*;

public class Helper {


	
	/**internal counter to generate aux names*/ 
	private static int n=0;
	
    private static HashSet<Port> inputs = new HashSet<Port>();
    private static HashSet<Port> outputs = new HashSet<Port>();
    private static HashSet<Port> locals = new HashSet<Port>(); 
    
    private static HashSet<String> inputNames = new HashSet<String>();
    private static HashSet<String> outputNames = new HashSet<String>();
    // sample all ports that are targets
    private static HashSet<Port> sources = new HashSet<Port>();
    // sample all ports that are soures
    private static HashSet<Port> targets = new HashSet<Port>();

    //private static HashMap<Port, String> port2Name = new HashMap<Port, String>();
    private static HashMap<Port, Connection> port2Con = new HashMap<Port, Connection>();
   
    
    /**
     * Generate unique name for all connections by mapping target or source name
     * to it. For inter-level transitions, the outermost port wins, otherwise
     * the source-port.
     * 
     * @param box
     */
    public static void init(Box box) {
    	initOutputs(box);
    	propagateOutputs(box);
    	propagateSources(box);
    }
    
    
    //make sure that all outputs of complex nodes have names
    private static void initOutputs(Box box){
    	for(Port p: box.getOutputs()){
    		if(p.getName()==null || p.getName().isEmpty()){
    			p.setName("_L" + n++);
    		}
    	}
    	for(Box b: box.getBoxes()){
    		initOutputs(b); 		
    	}
    }
    
    private static void propagateOutputs(Box box){
    	for(Box b: box.getBoxes()){
    		for (Connection c : b.getConnections()) {
    			if(box.getOutputs().contains(c.getTargetPort())){
    				c.getSourcePort().setName(c.getTargetPort().getName());
    			}
    		}
    		propagateOutputs(b);
    	}
    }
    
    private static void propagateSources(Box box){
    	for (Connection c : box.getConnections()) {  		
        	c.getTargetPort().setName(c.getSourcePort().getName());
           port2Con.put(c.getSourcePort(), c);
            port2Con.put(c.getTargetPort(), c);
        }
        for (Box b : box.getBoxes()) {
        	for (Connection c : b.getConnections()) {
        		
            	c.getTargetPort().setName(c.getSourcePort().getName());
                port2Con.put(c.getSourcePort(), c);
                port2Con.put(c.getTargetPort(), c);
            }
        }
        for (Box b : box.getBoxes()) {
        	propagateSources(b);
        }
    }
    

    public static void initIO(Box box) {

    	
        for (Connection c : box.getConnections()) {
            sources.add(c.getSourcePort());
            targets.add(c.getTargetPort());
        }

        for (Port p : box.getInputs()) {
            if (!targets.contains(p)) {
            	if(!inputNames.contains(p.getName())){
            		inputs.add(p);
            		inputNames.add(p.getName());
            	}
            } else {
                locals.add(p);
            }
        }
        
        for (Port p : box.getOutputs()) {
            if (!sources.contains(p)) {
                outputs.add(p);
            }
        }
        
        for(Port o: outputs){
        	locals.remove(o);
        }

    }

    public static Set<Port> getInputs() {
        return inputs;
    }

    public static Set<Port> getOutputs() {
        return outputs;
    }

    public static Set<Port> getLocals() {
        return locals;
    }

    public static Set<Port> getLocals(Box box) {
        HashSet<Port> res = new HashSet<Port>();
        HashSet<String> inNames = new HashSet<String>();
        HashSet<String> outNames = new HashSet<String>();
        HashSet<String> localNames = new HashSet<String>();
        
        for(Port i: box.getInputs()){
        	inNames.add(i.getName());
        }
        for(Port o: box.getOutputs()){
        	outNames.add(o.getName());
        }
        
        for (Box b : box.getBoxes()) {
            for (Connection c : b.getConnections()) {
            	String source =  c.getSourcePort().getName();
            	String target = c.getTargetPort().getName();
                if (!inNames.contains(source)
                        && !outNames.contains(target) && !localNames.contains(source)) {
                    res.add(c.getSourcePort());
                    localNames.add(source);
                }
            }
        }

        return res;
    }

    public static String timestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getName(Port p) {
        return p.getName();
    }   
}
