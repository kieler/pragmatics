/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.m2m;

import java.util.List;

/**
 * Abstract transformation that may be executed by an ITransformationFramework.
 * 
 * @author mim
 */
public abstract class AbstractTransformation {
    /**
     * Sets the transformation.
     * 
     * @param transformationName
     *            The transformation name
     */
    public abstract void setTransformation(String transformationName);

    /**
     * Gets the transformation name.
     * 
     * @return Name of the transformation
     */
    public abstract String getTransformation();

    /**
     * Gets the parameters as a List.
     * 
     * @return a string list of parameters
     */
    public abstract List<List<String>> getParameterList();

    /**
     * Adds a parameters. This is used to support multiple parameter types, i.e. polymorphism, for
     * transformations
     * 
     * @param params
     *            a string list of parameters
     */
    public abstract void addParameters(List<String> params);

    /**
     * Sets the parameters from a list.
     * 
     * @param params
     *            a string list of parameters
     */
    public abstract void setParameters(List<List<String>> params);
}
