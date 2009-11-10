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

    /** The minimal allowed priority value. Values less or equal to this value
     *  are treated as 'not supported'. */
    public static final int MIN_PRIORITY = Integer.MIN_VALUE >> 2;

    /** internal data type for storage of supported diagram information. */
    private static final class SupportedDiagram {
        /** identifier of the diagram type. */
        private String type;
        /** associated priority value, must be greater than MIN_PRIORITY. */
        private int priority = 0;
        
        private SupportedDiagram() {
        }
    }
    
    /** identifier of the layout provider. */
    private String id;
    /** user friendly name of the layout provider. */
    private String name;
    /** runtime instance of the layout provider. */
    private AbstractLayoutProvider instance;
    /** layout type identifier. */
    private String type;
    /** category identifier. */
    private String category;
    
    /** list of known layout options. */
    private List<String> knownOptions = new LinkedList<String>();
    /** list of supported diagrams. */
    private List<SupportedDiagram> supportedDiagrams = new LinkedList<SupportedDiagram>();

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (getName() != null && getName().length() > 0) {
            return getName();
        } else {
            return getId();
        }
    }
    
    /**
     * Sets the knowledge status of the given layout option.
     * 
     * @param layoutOption identifier of layout option
     * @param known indicates whether the layout provider should know
     *     the option
     */
    public void setOption(final String layoutOption, final boolean known) {
        if (layoutOption != null) {
            if (known && !knownOptions.contains(layoutOption)) {
                knownOptions.add(layoutOption);
            } else if (!known) {
                knownOptions.remove(layoutOption);
            }
        }
    }
    
    /**
     * Determines whether the layout provider knows the given
     * layout option.
     * 
     * @param layoutOption identifier of layout option
     * @return true if the associated layout provider know the option
     */
    public boolean knowsOption(final String layoutOption) {
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
    public void setDiagramSupport(final String diagramType, final int priority) {
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
        } else {
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
    
    /**
     * Returns the supported priority for the given diagram type. If the
     * type is not supported, {@link MIN_PRIORITY} is returned.
     * 
     * @param diagramType diagram type identifier
     * @return associated priority, or {@code MIN_PRIORITY} if the diagram
     *     type is not supported
     */
    public int getSupportedPriority(final String diagramType) {
        if (diagramType != null) {
            for (SupportedDiagram supportedDiagram : supportedDiagrams) {
                if (diagramType.equals(supportedDiagram.type)) {
                    return supportedDiagram.priority;
                }
            }
        }
        return MIN_PRIORITY;
    }

    /**
     * Sets the id.
     *
     * @param theid the id to set
     */
    public void setId(final String theid) {
        this.id = theid;
    }

    /**
     * Returns the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the name.
     *
     * @param thename the name to set
     */
    public void setName(final String thename) {
        this.name = thename;
    }

    /**
     * Returns the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the instance.
     *
     * @param theinstance the instance to set
     */
    public void setInstance(final AbstractLayoutProvider theinstance) {
        this.instance = theinstance;
    }

    /**
     * Returns the instance.
     *
     * @return the instance
     */
    public AbstractLayoutProvider getInstance() {
        return instance;
    }

    /**
     * Sets the type.
     *
     * @param thetype the type to set
     */
    public void setType(final String thetype) {
        this.type = thetype;
    }

    /**
     * Returns the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the category.
     *
     * @param thecategory the category to set
     */
    public void setCategory(final String thecategory) {
        this.category = thecategory;
    }

    /**
     * Returns the category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }
    
}
