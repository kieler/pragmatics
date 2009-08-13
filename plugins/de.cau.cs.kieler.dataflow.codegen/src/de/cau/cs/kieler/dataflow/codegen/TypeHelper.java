/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
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
 */
package de.cau.cs.kieler.dataflow.codegen;

import java.util.HashMap;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.Port;

/**
 * @author ctr
 *
 */
public class TypeHelper {
	private enum Type{
		INT,
		BOOL;
	}

	private static HashMap<String, Type> port2Type = new HashMap<String, Type>();

	public static void inferTypes(Box box){
		// Evaluate inside out!
		for (Box b: box.getBoxes()){
			inferTypes(b);
		}

		String name = box.getName().toLowerCase();
		if(name.equals("max") ||  name.equals("min") || name.equals("+") || name.equals("-") || name.equals("*") || name.equals("/")){
			port2Type.put(Helper.getName(box.getInputs().get(0)), Type.INT);
			port2Type.put(Helper.getName(box.getInputs().get(1)), Type.INT);
			port2Type.put(Helper.getName(box.getOutputs().get(0)), Type.INT);
		}else if(name.equals("and") || name.equals("or") || name.equals("xor")){
			port2Type.put(Helper.getName(box.getInputs().get(0)), Type.BOOL);
			port2Type.put(Helper.getName(box.getInputs().get(1)), Type.BOOL);
			port2Type.put(Helper.getName(box.getOutputs().get(0)), Type.BOOL);
		}else if(name.equals("<") || name.equals("<=") || name.equals("=") || name.equals(">=") || name.equals(">") || name.equals("<>")){
			port2Type.put(Helper.getName(box.getInputs().get(0)), Type.INT);
			port2Type.put(Helper.getName(box.getInputs().get(1)), Type.INT);
			port2Type.put(Helper.getName(box.getOutputs().get(0)), Type.BOOL);
		}else if(name.equals("not")){
			port2Type.put(Helper.getName(box.getInputs().get(0)), Type.BOOL);
			port2Type.put(Helper.getName(box.getOutputs().get(0)), Type.BOOL);
		}else if(name.equals("if")){
			port2Type.put(Helper.getName(box.getInputs().get(0)), Type.BOOL);
		}else if(name.equals("when")){
			port2Type.put(Helper.getName(box.getInputs().get(0)), Type.BOOL);
		}else if(name.equals("true") || name.equals("false")){
			port2Type.put(Helper.getName(box.getOutputs().get(0)), Type.BOOL);
		}else if(name.equals("pre") || name.equals("current")){
		}else if (name.matches("-?\\d*")){
			port2Type.put(Helper.getName(box.getOutputs().get(0)), Type.INT);
		}

		boolean done = box.getBoxes().isEmpty();
		boolean prog = true;   
		int i =0;
		while(!done && prog){
			done = true;
			prog = false;
			for(Box b : box.getBoxes()){
				name = b.getName().toLowerCase();

				if(name.equals("pre") || name.equals("current") || name.equals("->")){
					
					String iName = Helper.getName(b.getInputs().get(0));
					String oName = Helper.getName(b.getOutputs().get(0));
					Type iType = port2Type.get(iName);
					Type oType = port2Type.get(oName);
					if(iType!=null && oType==null){
						port2Type.put(oName, iType);
						prog=true;
					}else if (iType==null && oType !=null){
						port2Type.put(iName, oType);
						prog=true;
					}else if (iType==null && oType ==null){
						done = false;
					}   				
				}else if(name.equals("currentwhen") || name.equals("if") ){
					String iName = Helper.getName(b.getInputs().get(1));
					String oName = Helper.getName(b.getOutputs().get(0));
					Type iType = port2Type.get(iName);
					Type oType = port2Type.get(oName);
					if(iType!=null && oType==null){
						port2Type.put(oName, iType);
						prog=true;
					}else if (iType==null && oType !=null){
						port2Type.put(iName, oType);
						prog=true;
					}else if (iType==null && oType ==null){
						done = false;
					}   	    			
				}
			}
		}

	} 

	public static String getType(Port p) {
		Type t = port2Type.get(p.getName());
		if(t!=null){
			switch(t){
			case INT: return "int";
			case BOOL: return "bool";
			}
		}
		return "ERROR";
	}
}
