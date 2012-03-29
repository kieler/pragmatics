/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs;

import java.lang.reflect.Field;
import java.util.StringTokenizer;

import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

/**
 * Class for holding measured statistical data about a layout process. The measurement is a shared
 * process between server and client since parts of the layout process can only measured at one of
 * each side. This class provides higher order methods for accessing the statistical data which is
 * derived from the underlying measurements.
 *
 * @kieler.rating 2011-05-04 red
 *
 * @author swe
 */
public class Statistics {

    // Property definition

    /** The statistics property. */
    public static final IProperty<String> STATISTICS = new Property<String>(
            "de.cau.cs.kieler.kwebs.statistics", "");
    
    // Private members

    /** Length in bytes of the serial notation. */
    private int bytes 
        = 0;
    
    /** Was compression used? */
    private boolean compression 
        = false;
    
    /** Number of edges. */
    private int edges 
        = 0;
    
    /** Number of labels. */
    private int labels 
        = 0;
    
    /** Number of nodes. */
    private int nodes 
        = 0;
    
    /** Number of ports. */
    private int ports 
        = 0;
    
    /** Total time needed. */
    private double timeTotal 
        = 0;
    
    /** Time needed for layout. */
    private double timeLayout 
        = 0;
    
    /** Time needed for network transfer. */
    private double timeNetwork 
        = 0;
    
    /** Time needed for local supplementary operations. */
    private double timeLocalSupplemental 
        = 0;
    
    /** Time needed for remote supplementary operations. */
    private double timeRemoteSupplemental 
        = 0;
    
    // Getters and setters

    /**
     * Returns the length of the serial notation of the model of which the layout has been calculated.
     * 
     * @return the length of the serial notation of the model of which the layout has been calculated
     */
    public int getBytes() {
        return bytes;
    }

    /**
     * Sets the length of the serial notation of the model of which the layout has been calculated.
     * 
     * @param bytes
     *            the length of the serial notation of the model of which the layout has been calculated.
     */
    public void setBytes(final int bytes) {
        this.bytes = bytes;
    }

    /**
     * Returns whether the serial notation was in compressed form or not.
     * 
     * @return whether the serial notation was in compressed form or not
     */
    public boolean isCompression() {
        return compression;
    }

    /**
     * Sets whether the serial notation was in compressed form or not.
     * 
     * @param compression
     *            whether the serial notation was in compressed form or not
     */
    public void setCompression(final boolean compression) {
        this.compression = compression;
    }

    /**
     * Returns the number of edges of the model which the layout has been calculated for.
     * 
     * @return the number of edges of the model which the layout has been calculated for
     */
    public int getEdges() {
        return edges;
    }

    /**
     * Sets the number of edges of the model which the layout has been calculated for.
     * 
     * @param edges
     *            the number of edges of the model which the layout has been calculated for.
     */
    public void setEdges(final int edges) {
        this.edges = edges;
    }

    /**
     * Returns the number of labels of the model which the layout has been calculated for.
     * 
     * @return the number of labels of the model which the layout has been calculated for.
     */
    public int getLabels() {
        return labels;
    }

    /**
     * Sets the number of edges of the model which the layout has been calculated for.
     * @param labels
     *            the number of edges of the model which the layout has been calculated for
     */
    public void setLabels(final int labels) {
        this.labels = labels;
    }

    /**
     * Returns the number of nodes of the model which the layout has been calculated for.
     * 
     * @return the number of nodes of the model which the layout has been calculated for
     */
    public int getNodes() {
        return nodes;
    }

    /**
     * Sets the number of edges of the model which the layout has been calculated for.
     * 
     * @param nodes
     *            the number of edges of the model which the layout has been calculated for
     */
    public void setNodes(final int nodes) {
        this.nodes = nodes;
    }

    /**
     * Returns the number of ports of the model which the layout has been calculated for.
     * 
     * @return the number of edges of the model which the layout has been calculated for
     */
    public int getPorts() {
        return ports;
    }

    /**
     * Sets the number of ports of the model which the layout has been calculated for.
     * 
     * @param ports
     *            the number of ports of the model which the layout has been calculated for
     */
    public void setPorts(final int ports) {
        this.ports = ports;
    }

    /**
     * Returns the total time the layout took in nanoseconds. This includes supplementary
     * operations and network transfer time.
     * 
     * @return the total time the layout took in nanoseconds
     */
    public double getTimeTotal() {
        return timeTotal;
    }

    /**
     * Sets the total time the layout took in nanoseconds. This includes supplementary
     * operations and network transfer time.
     * 
     * @param timeTotal
     *            the total time the layout took in nanoseconds
     */
    public void setTimeTotal(final double timeTotal) {
        this.timeTotal = timeTotal;
    }

    /**
     * Returns the time the layout took solely in nanoseconds. This excludes supplementary
     * operations and network transfer time.
     * 
     * @return the time the layout took solely in nanoseconds
     */
    public double getTimeLayout() {
        return timeLayout;
    }

    /**
     * Sets the total time the layout took solely in nanoseconds. This excludes supplementary
     * operations and network transfer time.
     * 
     * @param timeLayout
     *            the total time the layout took solely in nanoseconds
     */
    public void setTimeLayout(final double timeLayout) {
        this.timeLayout = timeLayout;
    }

    /**
     * Returns the time the network transfer took in nanoseconds.
     * 
     * @return the time the network transfer took in nanoseconds
     */
    public double getTimeNetwork() {
        return timeNetwork;
    }

    /**
     * Sets the time the network transfer took in nanoseconds.
     * 
     * @param timeNetwork
     *            the time the network transfer took in nanoseconds
     */
    public void setTimeNetwork(final double timeNetwork) {
        this.timeNetwork = timeNetwork;
    }

    /**
     * Returns the time the local supplementary operations took in nanoseconds.
     * 
     * @return the time the local supplementary operations took in nanoseconds
     */
    public double getTimeLocalSupplemental() {
        return timeLocalSupplemental;
    }

    /**
     * Sets the time the local supplementary operations took in nanoseconds.
     * 
     * @param timeLocalSupplemental
     *            the time the local supplementary operations took in nanoseconds
     */
    public void setTimeLocalSupplemental(final double timeLocalSupplemental) {
        this.timeLocalSupplemental = timeLocalSupplemental;
    }

    /**
     * Returns the time the remote supplementary operations took in nanoseconds.
     *  
     * @return the time the remote supplementary operations took in nanoseconds
     */
    public double getTimeRemoteSupplemental() {
        return timeRemoteSupplemental;
    }

    /**
     * Sets the time the remote supplementary operations took in nanoseconds.
     * 
     * @param timeRemoteSupplemental
     *            the time the remote supplementary operations took in nanoseconds
     */
    public void setTimeRemoteSupplemental(final double timeRemoteSupplemental) {
        this.timeRemoteSupplemental = timeRemoteSupplemental;
    }

    // Higher order methods
    
    /**
     * The number of elements in the model which the layout was calculated for.
     * 
     * @return number of elements in the model which the layout was calculated for
     */
    public int getElementCount() {
        return getNodes() + getPorts() + getLabels() + getEdges();
    }

    /** Factor for calculating seconds out of nanoseconds. */
    private static final double FACTOR_NANOTOSECONDS 
        = 1e-9;

    /** Factor for calculating percent values. */
    private static final double FACTOR_PERCENT
        = 100;
    
    /**
     * Returns the speed of the layout process in graph elements per second. This value is being
     * calculated including supplementary operations and network transfer time.
     * 
     * @return the speed of the layout process in graph elements per second
     */
    public double getTotalSpeed() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return getElementCount() / (getTimeTotal() * FACTOR_NANOTOSECONDS);
    }

    /**
     * Returns the speed of the layout process in graph elements per second. This value is being
     * calculated excluding supplementary operations and network transfer time.
     * 
     * @return the speed of the layout process in graph elements per second
     */
    public double getLayoutSpeed() {
        if (getTimeLayout() == 0) {
            return 0;
        }
        return getElementCount() / (getTimeLayout() * FACTOR_NANOTOSECONDS);
    }

    /**
     * Returns the percentual part of the layout process responsible for network transfer.
     * 
     * @return the percentual part of the layout process responsible for network transfer
     */
    public double getNetworkPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (
                 (getTimeNetwork() - getTimeLayout() - getTimeRemoteSupplemental()) 
                 / getTimeTotal()
               ) * FACTOR_PERCENT;
    }

    /**
     * Returns the percentual part of the layout process responsible solely for the layout process.
     * 
     * @return the percentual part of the layout process responsible solely for the layout process
     */
    public double getLayoutPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (getTimeLayout() / getTimeTotal()) * FACTOR_PERCENT;
    }

    /**
     * Returns the percentual part of the layout process responsible for local and remote supplementary
     * operations.
     * 
     * @return the percentual part of the layout process responsible for local and remote supplementary
     * operations
     */
    public double getSupplementalPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (
                 (getTimeLocalSupplemental() + getTimeRemoteSupplemental()) 
                 / getTimeTotal()
               ) * FACTOR_PERCENT;
    }

    /**
     * Returns the percentual part of the layout process responsible for local supplementary
     * operations.
     *  
     * @return the percentual part of the layout process responsible for local supplementary
     * operations
     */
    public double getLocalSupplementalPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (getTimeLocalSupplemental() / getTimeTotal()) * FACTOR_PERCENT;
    }

    /**
     * Returns the percentual part of the layout process responsible for remote supplementary
     * operations.
     * 
     * @return the percentual part of the layout process responsible for remote supplementary
     * operations
     */
    public double getRemoteSupplementalPart() {
        if (getTimeTotal() == 0) {
            return 0;
        }
        return (getTimeRemoteSupplemental() / getTimeTotal()) * FACTOR_PERCENT;
    }
    
    // 

    /**
     * Returns a string representation of this instance.
     * 
     * @return a string representation of this instance
     */
    @Override
    public String toString() {
        return "bytes=" + bytes + ";"
               + "compression=" + compression + ";"
               + "edges=" + edges + ";"
               + "labels=" + labels + ";"
               + "nodes=" + nodes + ";"
               + "ports=" + ports + ";"
               + "timeLayout=" + timeLayout + ";"
               + "timeLocalSupplemental=" + timeLocalSupplemental + ";"
               + "timeNetwork=" + timeNetwork + ";"
               + "timeRemoteSupplemental=" + timeRemoteSupplemental + ";"
               + "timeTotal=" + timeTotal + ";";
    }

    /**
     * Parses statistical data from a string representation.
     * 
     * @param string
     *            string representation from statistical data
     */
    public void fromString(final String string) {
        Class<Statistics> statisticsClass = Statistics.class;
        if (string != null) {
            StringTokenizer st = new StringTokenizer(string, ";");           
            while (st.hasMoreElements()) {
                try {
                    String element = st.nextToken();
                    String key = element.substring(0, element.indexOf("="));
                    String text = element.substring(element.indexOf("=") + 1);
                    Field field = statisticsClass.getDeclaredField(key);
                    Class<?> type = field.getType();
                    Object value = null;
                    if (type.equals(boolean.class)) {
                        value = Boolean.parseBoolean(text);
                    } else if (type.equals(int.class)) {
                        value = Integer.parseInt(text);
                    } else if (type.equals(double.class)) {
                        value = Double.parseDouble(text);
                    }
                    if (value != null) {
                        field.set(this, value);
                    }
                } catch (Exception e) {
                    // Ignore invalid entries
                }
            }               
        }
    }
    
}
