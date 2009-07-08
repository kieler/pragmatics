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
package de.cau.cs.kieler.kiml.layout.services;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Data type used to store information for a layout provider.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class LayoutProviderData {

    /** The minimal allowed priority value. Values less or equal to this value are treated as 'not supported'. */
    public static final int MIN_PRIORITY = Integer.MIN_VALUE >> 2;

    /** internal data type for storage of supported diagram information */
    private static class SupportedDiagram {
        /** identifier of the diagram type */
        String type;
        /** associated priority value, must be greater than MIN_PRIORITY */
        int priority = 0;
    }
    
    /** identifier of the layout provider */
    public String id;
    /** user friendly name of the layout provider */
    public String name;
    /** runtime instance of the layout provider */
    public AbstractLayoutProvider instance;
    /** layout type identifier */
    public String type = "";
    /** category identifier */
    public String category = "";
    
    /** list of known layout options */
    private List<String> knownOptions = new LinkedList<String>();
    /** list of supported diagrams */
    private List<SupportedDiagram> supportedDiagrams = new LinkedList<SupportedDiagram>();

    /**
     * Sets the knowledge status of the given layout option.
     * 
     * @param layoutOption identifier of layout option
     * @param known indicates whether the layout provider should know
     *     the option
     */
    public void setOption(String layoutOption, boolean known) {
        if (layoutOption != null) {
            if (known && !knownOptions.contains(layoutOption))
                knownOptions.add(layoutOption);
            else if (!known)
                knownOptions.remove(layoutOption);
        }
    }
    
    /**
     * Determines whether the layout provider knows the given
     * layout option.
     * 
     * @param layoutOption identifier of layout option
     * @return true if the associated layout provider know the option
     */
    public boolean knowsOption(String layoutOption) {
        return knownOptions.contains(layoutOption);
    }
    
    /**
     * Sets support for the given diagram type. If the priority is less or
     * equal to {@link MIN_PRIORITY}, the type is treated as not supported.
     * 
     * @param diagramType identifier of diagram type
     * @param priority priority value, or {@code MIN_PRIORITY} if the diagram type
     *     is not supported
     */
    public void setDiagramSupport(String diagramType, int priority) {
        if (diagramType != null) {
            if (priority > MIN_PRIORITY) {
                SupportedDiagram supportedDiagram0 = null;
                for (SupportedDiagram supportedDiagram1 : supportedDiagrams) {
                    if (diagramType.equals(supportedDiagram1.type)) {
                        supportedDiagram0 = supportedDiagram1;
                        break;
                    }
                }
                if (supportedDiagram0 == null) {
                    supportedDiagram0 = new SupportedDiagram();
                    supportedDiagram0.type = diagramType;
                    supportedDiagrams.add(supportedDiagram0);
                }
                supportedDiagram0.priority = priority;
            }
            else {
                ListIterator<SupportedDiagram> suppdIter = supportedDiagrams.listIterator();
                while (suppdIter.hasNext()) {
                    SupportedDiagram supportedDiagram = suppdIter.next();
                    if (diagramType.equals(supportedDiagram.type)) {
                        suppdIter.remove();
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Returns the supported priority for the given diagram type. If the
     * type is not supported, {@link MIN_PRIORITY} is returned.
     * 
     * @param diagramType diagram type identifier
     * @return associated priority, or {@code MIN_PRIORITY} if the diagram
     *     type is not supported
     */
    public int getSupportedPriority(String diagramType) {
        if (diagramType != null) {
            for (SupportedDiagram supportedDiagram : supportedDiagrams) {
                if (diagramType.equals(supportedDiagram.type))
                    return supportedDiagram.priority;
            }
        }
        return MIN_PRIORITY;
    }
    
}
