/*
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
import java.util.HashSet;
import java.util.Set;

import de.cau.cs.kieler.dataflow.Box;
import de.cau.cs.kieler.dataflow.Connection;
import de.cau.cs.kieler.dataflow.Port;

/**
 * Helper functions for type and io inference of dataflow models.
 * 
 * @author ctr
 * 
 */
public final class Helper {

    private Helper() {
    }

    /** internal counter to generate auxiliary names. */
    private static int n = 0;

    private static HashSet<Port> inputs = new HashSet<Port>();
    private static HashSet<Port> outputs = new HashSet<Port>();
    private static HashSet<Port> locals = new HashSet<Port>();

    private static HashSet<String> inputNames = new HashSet<String>();
    // sample all ports that are targets
    private static HashSet<Port> sources = new HashSet<Port>();
    // sample all ports that are sources
    private static HashSet<Port> targets = new HashSet<Port>();

    // private static HashMap<Port, String> port2Name = new HashMap<Port,
    // String>();
    private static HashMap<Port, Connection> port2Con = new HashMap<Port, Connection>();

    public static void reset() {
        inputs.clear();
        outputs.clear();
        locals.clear();
        inputNames.clear();
        sources.clear();
        targets.clear();
        port2Con.clear();
    }

    /**
     * Generate unique name for all connections by mapping target or source name to it. For
     * inter-level transitions, the outermost port wins, otherwise the source-port.
     * 
     * @param box
     *            unnamed box
     */
    public static void init(final Box box) {
        initOutputs(box);
        propagateOutputs(box);
        propagateSources(box);
    }

    // make sure that all outputs of complex nodes have names
    private static void initOutputs(final Box box) {
        for (Port p : box.getOutputs()) {
            if (p.getName() == null || p.getName().isEmpty()) {
                p.setName("_L" + n++);
            }
        }
        for (Box b : box.getBoxes()) {
            initOutputs(b);
        }
    }

    private static void propagateOutputs(final Box box) {
        for (Box b : box.getBoxes()) {
            for (Connection c : b.getConnections()) {
                if (box.getOutputs().contains(c.getTargetPort())) {
                    c.getSourcePort().setName(c.getTargetPort().getName());
                }
            }
            propagateOutputs(b);
        }
    }

    private static void propagateSources(final Box box) {
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

    /**
     * Determine unconnected input and output ports, these are handled as external io.
     * 
     * @param box
     *            outermost box that represents the complete model
     */
    public static void initIO(final Box box) {
        
        for (Connection c : box.getConnections()) {
            sources.add(c.getSourcePort());
            targets.add(c.getTargetPort());
            if (inputNames.contains(c.getTargetPort().getName())) {
                inputNames.remove(c.getTargetPort().getName());
                inputs.remove(c.getTargetPort());
                locals.add(c.getTargetPort());
            }
        }

        for (Port p : box.getInputs()) {
            if (!targets.contains(p)) {
                if (!inputNames.contains(p.getName())) {
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

        for (Port o : outputs) {
            locals.remove(o);
        }

    }

    /**
     * Returns all global input ports. This function should only be called after initIO.
     * 
     * @return global inputs
     */
    public static Set<Port> getInputs() {
        return inputs;
    }

    /**
     * Returns all global output ports. This function should only be called after initIO.
     * 
     * @return global outputs
     */
    public static Set<Port> getOutputs() {
        return outputs;
    }

    /**
     * This function should only be called after initIO.
     * 
     * @return local variables
     */
    public static Set<Port> getLocals() {
        return locals;
    }

    /**
     * collect all local variables inside a box.
     * 
     * @param box
     *            for which the local variables are collected
     * @return names of all internal connections
     */
    public static Set<Port> getLocals(final Box box) {
        HashSet<Port> res = new HashSet<Port>();
        HashSet<String> inNames = new HashSet<String>();
        HashSet<String> outNames = new HashSet<String>();
        HashSet<String> localNames = new HashSet<String>();

        for (Port i : box.getInputs()) {
            inNames.add(i.getName());
        }
        for (Port o : box.getOutputs()) {
            outNames.add(o.getName());
        }

        for (Box b : box.getBoxes()) {
            for (Connection c : b.getConnections()) {
                String source = c.getSourcePort().getName();
                String target = c.getTargetPort().getName();
                if (!inNames.contains(source) && !outNames.contains(target)
                        && !localNames.contains(source)) {
                    res.add(c.getSourcePort());
                    localNames.add(source);
                }
            }
        }

        return res;
    }
}
