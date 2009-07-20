/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright ${year} by
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

    private static HashSet<String> defs = new HashSet<String>();
    private static HashSet<String> inputs = new HashSet<String>();
    private static HashSet<String> outputs = new HashSet<String>();
    private static HashSet<String> locals = new HashSet<String>();

    // sample all ports that are targets
    private static HashSet<Port> sources = new HashSet<Port>();
    // sample all ports that are soures
    private static HashSet<Port> targets = new HashSet<Port>();

    private static HashMap<Port, String> port2Name = new HashMap<Port, String>();
    private static HashMap<Port, Connection> port2Con = new HashMap<Port, Connection>();

    /**
     * Generate unique name for all connections by mapping target or source name
     * to it. For inter-level transitions, the outermost port wins, otherwise
     * the source-port.
     * 
     * @param box
     */
    public static void init(Box box) {
        for (Connection c : box.getConnections()) {
            String name = c.getSourcePort().getName();
            if (port2Name.containsKey(c.getSourcePort())) {
                name = port2Name.get(c.getSourcePort());
            } else if (port2Name.containsKey(c.getTargetPort())) {
                name = port2Name.get(c.getTargetPort());
            }
            port2Name.put(c.getSourcePort(), name);
            port2Name.put(c.getTargetPort(), name);
            port2Con.put(c.getSourcePort(), c);
            port2Con.put(c.getTargetPort(), c);
        }

        for (Box b : box.getBoxes()) {
            init(b);
        }
    }

    public static void initIO(Box box) {
        for (Connection c : box.getConnections()) {
            sources.add(c.getSourcePort());
            targets.add(c.getTargetPort());
        }

        for (Port p : box.getInputs()) {
            if (!targets.contains(p)) {
                inputs.add(p.getName());
            } else {
                locals.add(port2Name.get(p));
            }
        }
        for (Port p : box.getOutputs()) {
            if (!sources.contains(p)) {
                outputs.add(p.getName());
            }
        }

    }

    public static Set<String> getInputs() {
        return inputs;
    }

    public static Set<String> getOutputs() {
        return outputs;
    }

    public static Set<String> getLocals() {
        return locals;
    }

    public static Set<String> getLocals(Box box) {
        HashSet<String> res = new HashSet<String>();
        for (Box b : box.getBoxes()) {
            for (Connection c : b.getConnections()) {
                if (!box.getInputs().contains(c.getSourcePort())
                        && !box.getOutputs().contains(c.getTargetPort())) {
                    res.add(getName(c.getSourcePort()));
                }
            }
        }

        return res;
    }

    public static String timestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getName(Port p) {
        return port2Name.get(p);
    }
}
