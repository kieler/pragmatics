/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2014 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.codaflow.util;

import java.io.File;

import org.adaptagrams.ACALayout;
import org.adaptagrams.SWIGTYPE_p_std__valarrayT_double_t;

/**
 * Debug helper for Cola-based layouters.
 * 
 * @author uru
 */
public class ACADebugTestConvergence extends MinMaxTestConvergence {

    private ACALayout layouter;
    private String namePrefix;
    private File absoluteFolder;

    /**
     * @param folder
     *            the folder where to store the debug output
     */
    public ACADebugTestConvergence(final String folder) {
        this.absoluteFolder =
                new File(System.getProperty("user.home") + File.separatorChar + "tmp"
                        + File.separatorChar + folder);
    }

    /**
     * @param layouter
     *            the layouter to set
     */
    public void setLayouter(final ACALayout layouter) {
        this.layouter = layouter;
    }

    /**
     * @param namePrefix
     *            the namePrefix to set
     */
    public void setNamePrefix(final String namePrefix) {
        this.namePrefix = namePrefix;
    }

    // CHECKSTYLEOFF MethodName - I cannot change swig naming ..
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean ColaTestConvergenceOperator(final double arg0,
            final SWIGTYPE_p_std__valarrayT_double_t arg1,
            final SWIGTYPE_p_std__valarrayT_double_t arg2) {

        String pathPrefix = absoluteFolder + File.separator + namePrefix + "_";
//
//        // initial iteration,
        if (getIterations() == 0) {
            // make sure the temp folder exists
            absoluteFolder.mkdirs();

            // remove any old debug output to avoid confusion when
            // a different number of iterations is performed
            for (File f : absoluteFolder.listFiles()) {
                String aPath = f.getAbsolutePath();
                // only delete the files with this namePrefix
                if (aPath.substring(0, aPath.lastIndexOf("_") + 1).equals(pathPrefix)) {
                    f.delete();
                }
            }
        }
//
        String outputName = absoluteFolder + File.separator + namePrefix + "_" + getIterations();
        layouter.outputInstanceToSVG(outputName);

        // we use the default implementation of the convergence test
        return super.ColaTestConvergenceOperator(arg0, arg1, arg2);
    }

}
