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
 */
package de.cau.cs.kieler.dataflow.codegen;

import java.util.HashMap;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.Port;

/**
 * Functions for Type Inference.
 * 
 * @author ctr
 * 
 */
public final class TypeHelper {

    // make sure the class is not instantiated
    private TypeHelper() {
    }

    /**
     * supported data types.
     */
    private enum Type {
        INT, BOOL;
    }

    private static HashMap<String, Type> port2Type = new HashMap<String, Type>();

    /**
     * Infer types from known operators. These are: max, min, +, -, /, *, and,
     * or, xor, <, <=, =, =>, >, <>, not, if, when, true, false, pre and
     * current.
     * 
     * @param box
     *            for which types are infered
     */
    public static void inferTypes(final Box box) {
        // Evaluate inside out!
        for (Box b : box.getBoxes()) {
            inferTypes(b);
        }

        String name = box.getName().toLowerCase();
        if (name.equals("max") || name.equals("min") || name.equals("+")
                || name.equals("-") || name.equals("*") || name.equals("/")) {
            port2Type.put(box.getInputs().get(0).getName(), Type.INT);
            port2Type.put(box.getInputs().get(1).getName(), Type.INT);
            port2Type.put(box.getOutputs().get(0).getName(), Type.INT);
        } else if (name.equals("and") || name.equals("or")
                || name.equals("xor")) {
            port2Type.put(box.getInputs().get(0).getName(), Type.BOOL);
            port2Type.put(box.getInputs().get(1).getName(), Type.BOOL);
            port2Type.put(box.getOutputs().get(0).getName(), Type.BOOL);
        } else if (name.equals("<") || name.equals("<=") || name.equals("=")
                || name.equals(">=") || name.equals(">") || name.equals("<>")) {
            port2Type.put(box.getInputs().get(0).getName(), Type.INT);
            port2Type.put(box.getInputs().get(1).getName(), Type.INT);
            port2Type.put(box.getOutputs().get(0).getName(), Type.BOOL);
        } else if (name.equals("not")) {
            port2Type.put(box.getInputs().get(0).getName(), Type.BOOL);
            port2Type.put(box.getOutputs().get(0).getName(), Type.BOOL);
        } else if (name.equals("if")) {
            port2Type.put(box.getInputs().get(0).getName(), Type.BOOL);
        } else if (name.equals("when")) {
            port2Type.put(box.getInputs().get(0).getName(), Type.BOOL);
        } else if (name.equals("true") || name.equals("false")) {
            port2Type.put(box.getOutputs().get(0).getName(), Type.BOOL);
        //} else if (name.equals("pre") || name.equals("current")) {
        } else if (name.matches("-?\\d*")) {
            port2Type.put(box.getOutputs().get(0).getName(), Type.INT);
        }

        boolean done = box.getBoxes().isEmpty();
        boolean prog = true;
        while (!done && prog) {
            done = true;
            prog = false;
            for (Box b : box.getBoxes()) {
                name = b.getName().toLowerCase();

                if (name.equals("pre") || name.equals("current")
                        || name.equals("->")) {

                    String iName = b.getInputs().get(0).getName();
                    String oName = b.getOutputs().get(0).getName();
                    Type iType = port2Type.get(iName);
                    Type oType = port2Type.get(oName);
                    if (iType != null && oType == null) {
                        port2Type.put(oName, iType);
                        prog = true;
                    } else if (iType == null && oType != null) {
                        port2Type.put(iName, oType);
                        prog = true;
                    } else if (iType == null && oType == null) {
                        done = false;
                    }
                } else if (name.equals("currentwhen") || name.equals("if")) {
                    String iName = b.getInputs().get(1).getName();
                    String oName = b.getOutputs().get(0).getName();
                    Type iType = port2Type.get(iName);
                    Type oType = port2Type.get(oName);
                    if (iType != null && oType == null) {
                        port2Type.put(oName, iType);
                        prog = true;
                    } else if (iType == null && oType != null) {
                        port2Type.put(iName, oType);
                        prog = true;
                    } else if (iType == null && oType == null) {
                        done = false;
                    }
                }
            }
        }

    }

    /**
     * @param p
     *            a port
     * @return inferred type for the given port
     */
    public static String getType(final Port p) {
        Type t = port2Type.get(p.getName());
        if (t != null) {
            switch (t) {
            case INT:
                return "int";
            case BOOL:
                return "bool";
            }
        }
        return "ERROR";
    }
}
