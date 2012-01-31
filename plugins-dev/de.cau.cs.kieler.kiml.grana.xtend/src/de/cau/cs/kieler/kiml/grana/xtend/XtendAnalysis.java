/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.grana.xtend;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.model.m2m.TransformException;
import de.cau.cs.kieler.core.model.xtend.transformation.ITransformationFramework;
import de.cau.cs.kieler.kiml.grana.AbstractSimpleInfoAnalysis;
import de.cau.cs.kieler.kiml.service.grana.AnalysisFailed;

/**
 * A Xtend analysis. Instances of this class represent xtend extensions.
 * 
 * @author mri
 */
public class XtendAnalysis extends AbstractSimpleInfoAnalysis {

    /** the prefix for all xtend ids. */
    private static final String ID_PREFIX =
            "de.cau.cs.kieler.kiml.grana.xtend.analyses.";
    /** the xtend grana category. */
    private static final String XTEND_CATEGORY =
            "de.cau.cs.kieler.kiml.grana.xtend.category";

    /** the id of this analysis. */
    private String id;
    /** the name of this analysis. */
    private String name;
    /** name of the file the extension is located in. */
    private String filename;
    /** the extension this analyses represents. */
    private String extension;
    /** the ids of analyses this analysis depends on. */
    private List<String> dependencies = new LinkedList<String>();
    /** the transformation framework. */
    private ITransformationFramework framework;

    /**
     * Constructs a xtend analyses.
     * 
     * @param theName
     *            the name of this analysis
     * @param file
     *            the xtend-file containing the extension
     * @param ext
     *            the extension
     * @param deps
     *            the ids of analyses this analysis depends on
     */
    public XtendAnalysis(final String theName, final String file,
            final String ext, final List<String> deps) {
        name = theName;
        filename = file;
        extension = ext;
        dependencies.addAll(deps);
        // build id
        id =
                ID_PREFIX + name.replace(" ", "_") + "_"
                        + extension.replace(" ", "_");
    }

    /**
     * Constructs a xtend analyses.
     * 
     * @param theName
     *            the name of this analysis
     * @param file
     *            the xtend-file containing the extension
     * @param ext
     *            the extension
     * @param deps
     *            the ids of analyses this analysis depends on
     * @param theFramework
     *            the transformation framework
     */
    public XtendAnalysis(final String theName, final String file,
            final String ext, final List<String> deps,
            final ITransformationFramework theFramework) {
        name = theName;
        filename = file;
        extension = ext;
        dependencies.addAll(deps);
        framework = theFramework;
        // build id
        id = ID_PREFIX + file.hashCode() + "_" + name.hashCode() + "_" + extension.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the analysis.
     * 
     * @param newName
     *            the new name
     */
    public void setName(final String newName) {
        name = newName;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return "A xtend analysis.";
    }

    /**
     * Returns the name of the file the extension is located in this xtend
     * analysis is representing.
     * 
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns the extension that is represented by this xtend analysis.
     * 
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getDependencyIds() {
        return dependencies;
    }

    /**
     * {@inheritDoc}
     */
    public String getCategory() {
        return XTEND_CATEGORY;
    }

    /**
     * Sets the transformation framework.
     * 
     * @param theFramework
     *            the transformation framework
     */
    public void setFramework(final ITransformationFramework theFramework) {
        framework = theFramework;
    }

    /**
     * {@inheritDoc}
     */
    public Object doAnalysis(final KNode parentNode,
            final Map<String, Object> results,
            final IKielerProgressMonitor progressMonitor) {
        progressMonitor.begin("Xtend analysis: " + name, 1);
        // build the parameters
        Object[] parameters = new Object[dependencies.size() + 1];
        parameters[0] = parentNode;
        int i = 1;
        for (String dependencyId : dependencies) {
            Object result = results.get(dependencyId);
            if (result == null) {
                progressMonitor.done();
                return new AnalysisFailed(AnalysisFailed.Type.Dependency);
            }
            parameters[i++] = result;
        }
        // configure the framework
        IPath path = new Path(filename);
        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
        framework.setParameters(parameters);
        if (!framework.initializeTransformation(file.getLocation().toString(),
                extension, "de.cau.cs.kieler.core.kgraph.KGraphPackage")) {
            framework.reset();
            progressMonitor.done();
            return new AnalysisFailed(AnalysisFailed.Type.Failed);
        }
        // execute the transformation
        Object result;
        try {
            result = framework.executeTransformation();
            if (result == null) {
                framework.reset();
                progressMonitor.done();
                return new AnalysisFailed(AnalysisFailed.Type.Failed);
            }
        } catch (TransformException e) {
            framework.reset();
            progressMonitor.done();
            return new AnalysisFailed(AnalysisFailed.Type.Failed);
        }
        progressMonitor.done();
        return result;
    }
}
