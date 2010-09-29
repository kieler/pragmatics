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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.preference.IPreferenceStore;

import de.cau.cs.kieler.core.model.transformation.AbstractTransformation;
import de.cau.cs.kieler.core.model.transformation.ITransformationFramework;
import de.cau.cs.kieler.core.model.transformation.xtend.XtendTransformationFramework;

/**
 * A manager class for creating and managing xtend analyses.<br>
 * This class is a singleton.
 * 
 * @author mri
 */
public final class XtendAnalysesManager {

    /** the xtend analyses preference key. */
    private static final String XTEND_ANALYSES_PREFERENCE =
            "de.cau.cs.kieler.kiml.grana.xtend.preference.analyses";

    /** the singleton instance. */
    private static XtendAnalysesManager instance = null;
    /** the currently registered analyses. */
    private List<XtendAnalysis> analyses = new LinkedList<XtendAnalysis>();
    /** the preference store. */
    private IPreferenceStore preferenceStore;

    /** the transformation framework. */
    private ITransformationFramework framework =
            new XtendTransformationFramework();

    /**
     * The private constructor.
     */
    private XtendAnalysesManager() {
        preferenceStore = GranaXtendPlugin.getDefault().getPreferenceStore();
        loadRegisteredXtendAnalyses();
    }

    /**
     * Returns the singleton instance.
     * 
     * @return the singleton instance
     */
    public static XtendAnalysesManager getInstance() {
        if (instance == null) {
            instance = new XtendAnalysesManager();
        }
        return instance;
    }

    /**
     * Adds a xtend analysis.
     * 
     * @param xtendAnalysis
     *            the xtend analysis
     */
    public void addXtendAnalysis(final XtendAnalysis xtendAnalysis) {
        analyses.add(xtendAnalysis);
        xtendAnalysis.setFramework(framework);
        XtendAnalysesBundle.addXtendAnalysis(xtendAnalysis);
        saveRegisteredXtendAnalyses();
    }

    /**
     * Adds a list of xtend analyses.
     * 
     * @param xtendAnalyses
     *            the xtend analyses
     */
    public void addXtendAnalyses(final List<XtendAnalysis> xtendAnalyses) {
        analyses.addAll(xtendAnalyses);
        for (XtendAnalysis xtendAnalysis : xtendAnalyses) {
            xtendAnalysis.setFramework(framework);
            XtendAnalysesBundle.addXtendAnalysis(xtendAnalysis);
        }
        saveRegisteredXtendAnalyses();
    }

    /**
     * Removes a xtend analysis.
     * 
     * @param xtendAnalysis
     *            the xtend analysis
     */
    public void removeXtendAnalysis(final XtendAnalysis xtendAnalysis) {
        analyses.remove(xtendAnalysis);
        XtendAnalysesBundle.removeXtendAnalysis(xtendAnalysis);
        saveRegisteredXtendAnalyses();
    }

    /**
     * Removes a list of xtend analyses.
     * 
     * @param xtendAnalyses
     *            the xtend analyses
     */
    public void removeXtendAnalyses(final List<XtendAnalysis> xtendAnalyses) {
        analyses.removeAll(xtendAnalyses);
        for (XtendAnalysis xtendAnalysis : xtendAnalyses) {
            XtendAnalysesBundle.removeXtendAnalysis(xtendAnalysis);
        }
        saveRegisteredXtendAnalyses();
    }

    /**
     * Returns the currently registered xtend analyses.
     * 
     * @return the xtend analyses
     */
    public List<XtendAnalysis> getXtendAnalyses() {
        return analyses;
    }

    /**
     * Returns a list of xtend analysis candidate transformations that are
     * defined inside the xtend file. Candidates are all extension that take a
     * 'KNode' as first parameter.
     * 
     * @param filename
     *            the xtend filename
     * @return a list of xtend tranformations that are candidates for a xtend
     *         analysis
     * @throws MalformedURLException
     *             thrown if the filename causes problems when converted to an
     *             url
     */
    public List<AbstractTransformation> getXtendAnalysisCandidates(
            final String filename) throws MalformedURLException {
        URI uri = URI.createPlatformResourceURI(filename, true);
        List<AbstractTransformation> transformations =
                framework.parseTransformations(new URL(uri.toString()), false);
        List<AbstractTransformation> qualifiedTransformations =
                new LinkedList<AbstractTransformation>();
        for (AbstractTransformation transformation : transformations) {
            if (transformation.getParameterList().size() > 0
                    && transformation.getParameterList().get(0)
                            .contains("KNode")) {
                qualifiedTransformations.add(transformation);
            }
        }
        return qualifiedTransformations;
    }

    /**
     * Saves the registered xtend analyses to the preference store.
     */
    private void saveRegisteredXtendAnalyses() {
        String analysesString = "";
        boolean first = true;
        for (XtendAnalysis xtendAnalysis : analyses) {
            if (first) {
                first = false;
            } else {
                analysesString += ";";
            }
            analysesString += serializeXtendAnalysis(xtendAnalysis);
        }
        preferenceStore.setValue(XTEND_ANALYSES_PREFERENCE, analysesString);
    }

    /**
     * Loads the registered xtend analyses from the preference store.
     */
    private void loadRegisteredXtendAnalyses() {
        for (XtendAnalysis xtendAnalysis : analyses) {
            XtendAnalysesBundle.removeXtendAnalysis(xtendAnalysis);
        }
        analyses.clear();
        String analysesString =
                preferenceStore.getString(XTEND_ANALYSES_PREFERENCE);
        String[] token = analysesString.split(";");
        for (String serializedAnalysis : token) {
            XtendAnalysis xtendAnalysis =
                    unserializeXtendAnalysis(serializedAnalysis);
            if (xtendAnalysis != null) {
                analyses.add(xtendAnalysis);
                XtendAnalysesBundle.addXtendAnalysis(xtendAnalysis);
            }
        }
    }

    /**
     * Serializes a xtend analysis.
     * 
     * @param analysis
     *            the xtend analysis
     * @return the serialized xtend analysis
     */
    private String serializeXtendAnalysis(final XtendAnalysis analysis) {
        String serializedAnalysis =
                analysis.getName() + "," + analysis.getFilename() + ","
                        + analysis.getExtension();
        for (String analysisId : analysis.getDependencies()) {
            serializedAnalysis += "," + analysisId;
        }
        return serializedAnalysis;
    }

    /**
     * the offset of the unserialize token array that seperates informations and
     * dependecies.
     */
    private static final int TOKEN_OFFSET = 3;

    /**
     * Unserializes a xtend analysis.
     * 
     * @param data
     *            the serialized xtend analysis
     * @return the xtend analysis
     */
    private XtendAnalysis unserializeXtendAnalysis(final String data) {
        String[] token = data.split(",");
        if (token.length < TOKEN_OFFSET) {
            return null;
        }
        List<String> dependencies = new LinkedList<String>();
        for (int i = TOKEN_OFFSET; i < token.length; ++i) {
            dependencies.add(token[i]);
        }
        XtendAnalysis xtendAnalysis =
                new XtendAnalysis(token[0], token[1], token[2], dependencies,
                        framework);
        return xtendAnalysis;
    }
}
