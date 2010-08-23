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
 * 
 *****************************************************************************/

package de.cau.cs.kieler.karma;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.mwe.core.ConfigurationException;
import org.eclipse.xtend.typesystem.emf.EcoreUtil2;

import de.cau.cs.kieler.core.ui.util.FeatureValueCondition;
import de.cau.cs.kieler.core.ui.util.ListSizeCondition;
import de.cau.cs.kieler.core.util.CompoundCondition;
import de.cau.cs.kieler.core.util.ICondition;
import de.cau.cs.kieler.core.util.Pair;

/**
 * 
 * @author ckru
 * 
 */
public final class ConditionProvider {

    /**
     * HashTable for caching condition pairs so that the ExtensionPoint is parsed only once per edit
     * part.
     */
    private Hashtable<String, List<Pair<String, ICondition<EObject>>>> hashTableConditions = new Hashtable<String, List<Pair<String, ICondition<EObject>>>>();

    /**
     * HashTable for caching figure providers so that the ExtensionPoint is parsed only once per
     * edit part.
     */
    private Hashtable<String, IFigureProvider> hashTableFigureProviders = new Hashtable<String, IFigureProvider>();

    /**
     * HashTable for caching the relevant features and feature ids. Not yet used, will probably removed again. 
     */
    private Hashtable<Integer, EStructuralFeature> hashTableRelevantFeatures = new Hashtable<Integer, EStructuralFeature>();
    
    /**
     * Constructor set to private to ensure usage of singleton instance.
     */
    private ConditionProvider() {

    }

    /**
     * Singleton instance.
     */
    private static ConditionProvider instance;

    /**
     * Standard singleton getInstance.
     * 
     * @return the singleton instance
     */
    public static ConditionProvider getInstance() {
        if (instance == null) {
            instance = new ConditionProvider();
        }
        return instance;
    }

    /**
     * extension point id.
     */
    public static final String EXTENSION_POINT_ID = "de.cau.cs.kieler.karma";

    /**
     * Method that will read the extension point and return a list of pairs with a figure and a
     * condition on which the figure should be shown.
     * 
     * @param callingEditPart
     *            the editor for which the returned conditions will be defined
     * @return list of all condition figure pairs that fit the given editor
     */
    public List<Pair<String, ICondition<EObject>>> getPairs(final String callingEditPart) {
        if (hashTableConditions.containsKey(callingEditPart)) {
            return hashTableConditions.get(callingEditPart);
        }
        List<Pair<String, ICondition<EObject>>> conditionFigurePairs = new LinkedList<Pair<String, ICondition<EObject>>>();
        IConfigurationElement[] configurations = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTENSION_POINT_ID);
        for (IConfigurationElement settings : configurations) {
            if (!settings.getName().equals("configuration")) {
                System.out.println("faulty extensionpoint");
                continue;
            }
            // String editorId = settings.getAttribute("editorId");
            IConfigurationElement[] parts = settings.getChildren("editPart");
            if (checkCompatibleEditParts(parts, callingEditPart)) {
                IConfigurationElement[] packages = settings.getChildren("package");
                IConfigurationElement[] conditionsContainer = settings.getChildren("conditions");
                /*
                IConfigurationElement[] mutatableObjectContainer = settings
                        .getChildren("mutatableObject");
                if (mutatableObjectContainer.length != 0) {
                    // TODO support for mutatable objects, probably wont make it into final version since 
                    //      annotations also work with conditions.
                } else {*/ 
                    for (IConfigurationElement conditionContainer : conditionsContainer) {
                        IConfigurationElement[] conditions = conditionContainer.getChildren();
                        for (IConfigurationElement condition : conditions) {
                            String figureParam = condition.getAttribute("figureParam");
                            if ((figureParam == null) || (figureParam.isEmpty())) {
                            	System.out.println("figureparam empty");
                            }
                            // IFigure figure = figureProvider.getFigureByString(figureParam);
                            ICondition<EObject> cond = getCondition(condition, packages);
                            if ((cond != null) /* && (figure != null) */) {
                                Pair<String, ICondition<EObject>> pair = new Pair<String, ICondition<EObject>>(
                                        figureParam, cond);
                                conditionFigurePairs.add(pair);
                            } else {
                                throw new RuntimeException(
                                        "A failure occured while getting Conditions");
                                // System.out.println("Couldnt get condition. Condition was skipped.");
                            }
                        }
                    }

                
                hashTableConditions.put(callingEditPart, conditionFigurePairs);
                return conditionFigurePairs;
            }
        }
        return null;
    }

    /**
     * Method for getting the condition of an configuration element.
     * 
     * @param condition
     *            the configuration element that represents a condition
     * @param packages
     *            The packages that will be searched for features.
     * @return a condition according to the given configuration element. null if failed
     */
    private ICondition<EObject> getCondition(final IConfigurationElement condition,
            final IConfigurationElement[] packages) {
        if (condition.getName().equals("featureValueCondition")) {
            String featureString = condition.getAttribute("feature");
            String typeString = condition.getAttribute("type");
            EStructuralFeature feature = getFeatureFromPackages(packages, featureString, typeString);
            if (feature != null) {
                if(!(hashTableRelevantFeatures.containsKey(feature.getFeatureID()))) {
                    hashTableRelevantFeatures.put(feature.getFeatureID(), feature);
                }
                Object value = getValueByFeature(feature, condition.getAttribute("value"));
                if (value != null) {
                    FeatureValueCondition cond = new FeatureValueCondition(feature, value);
                    return cond;
                } else {
                    throw new RuntimeException("Could not parse value to type of feature.");
                    // System.out.println("Could not parse value to type of feature.");
                    // return null;
                }
            } else {
                throw new RuntimeException("Could not find specified feature.");
                // System.out.println("Could not find specified feature");
                // return null;
            }
        } else if (condition.getName().equals("listSizeCondition")) {
            String featureString = condition.getAttribute("feature");
            String typeString = condition.getAttribute("type");
            String sizeString = condition.getAttribute("size");
            int size = this.parseListSize(sizeString).getSecond();
            String operator = this.parseListSize(sizeString).getFirst();
            EStructuralFeature feature = getFeatureFromPackages(packages, featureString, typeString);
            if (feature != null) {
                if (!(hashTableRelevantFeatures.containsKey(feature.getFeatureID()))) {
                    hashTableRelevantFeatures.put(feature.getFeatureID(), feature);
                }
                ListSizeCondition cond = new ListSizeCondition(feature, size, operator);
                return cond;
            } else {
                throw new RuntimeException("Could not find specified feature.");
                // System.out.println("Could not find specified feature");
                // return null;
            }
        } else if (condition.getName().equals("compoundCondition")) {
            IConfigurationElement[] compounds = condition.getChildren();
            LinkedList<ICondition<EObject>> compoundList = new LinkedList<ICondition<EObject>>();
            for (IConfigurationElement compound : compounds) {
                ICondition<EObject> compCond = getCondition(compound, packages);
                if (compCond != null) {
                    compoundList.add(compCond);
                } else {
                    throw new RuntimeException("Could build compound.");
                    // System.out.println("Couldnt get compound. Compound was skipped.");
                }
            }
            CompoundCondition<EObject> cond = new CompoundCondition<EObject>(compoundList);
            return cond;

        } else if (condition.getName().equals("customCondition")) {
            try {
                Object customConditionObject = condition.createExecutableExtension("condition");
                String key = condition.getAttribute("key");
                String value = condition.getAttribute("value");
                if (customConditionObject instanceof ICustomCondition<?>) {
                    @SuppressWarnings("unchecked")
                    ICustomCondition<EObject> customCondition = (ICustomCondition<EObject>) customConditionObject;
                    customCondition.initialize(key, value);
                    return customCondition;
                }
            } catch (CoreException e) {
                throw new RuntimeException("customCondition failed to load.");
                // System.out.println("customCondition failed to load.");
                // e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Parses a value out of a given string according to the type of a given feature.
     * 
     * @param feature
     *            the feature for getting the type
     * @param value
     *            string representation of the value
     * @return the value parsed to the correct type
     */
    private Object getValueByFeature(final EObject feature, final String value) {
        if (feature instanceof EAttributeImpl) {
            EAttributeImpl attr = (EAttributeImpl) feature;
            EClassifier type = attr.getEType();
            if (type instanceof EEnum) {
                EEnum eenum = (EEnum) ((EAttribute) feature).getEType();               
                EEnumLiteral literal = eenum.getEEnumLiteral(value);
                return literal.getInstance();
            } else if (type.getName().equals("EBoolean")) {
                return Boolean.parseBoolean(value);
            }
        }
        return null;
    }

    /**
     * Method that searches in packages for a feature.
     * 
     * @param packages
     *            an array of configuration elements that represent the packages
     * @param feature
     *            the name of the feature to be searched
     * @return a feature if found else null
     */
    private EStructuralFeature getFeatureFromPackages(final IConfigurationElement[] packages,
            final String feature, final String type) {
        EStructuralFeature result = null;
        EPackage pack = null;
        for (IConfigurationElement packConfig : packages) {
            String packClassName = packConfig.getAttribute("class");
            try {
                pack = EcoreUtil2.getEPackageByClassName(packClassName);
            } catch (ConfigurationException ce) {
                throw new RuntimeException("Package filed to load.");
            }
            if (pack != null) {

                EClassifier classifier = pack.getEClassifier(type);
                EClass cl = (EClass) classifier;
                if (cl != null) {
                    result = cl.getEStructuralFeature(feature);
                }
                if (result != null) {
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * Checks whether the calling edit part is included in the registered ones.
     * 
     * @param parts
     *            the registered edit parts
     * @param className
     *            the name of the calling edit part
     * @return true if included else false
     */
    private boolean checkCompatibleEditParts(final IConfigurationElement[] parts,
            final String className) {
        for (IConfigurationElement part : parts) {
            String registeredName = part.getAttribute("editPart");
            if (registeredName.equals(className)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the registered figure provider that matches the calling edit part.
     * 
     * @param callingEditPart
     *            name of the calling edit part
     * @return the registered figure provider
     */
    public IFigureProvider getFigureProvider(final String callingEditPart) {
        if (hashTableFigureProviders.containsKey(callingEditPart)) {
            return hashTableFigureProviders.get(callingEditPart);
        }
        IConfigurationElement[] configurations = Platform.getExtensionRegistry()
                .getConfigurationElementsFor(EXTENSION_POINT_ID);
        for (IConfigurationElement settings : configurations) {
            if (!settings.getName().equals("configuration")) {
                System.out.println("faulty extensionpoint");
                continue;
            }
            // String editorId = settings.getAttribute("editorId");
            IConfigurationElement[] parts = settings.getChildren("editPart");
            if (checkCompatibleEditParts(parts, callingEditPart)) {
                IFigureProvider figureProvider = null;
                try {
                    figureProvider = (IFigureProvider) settings
                            .createExecutableExtension("FigureProvider");
                } catch (CoreException e1) {
                    throw new RuntimeException("figureProvider filed to load.");
                    // System.out.println("figureProvider failed to load");
                    // e1.printStackTrace();
                }
                hashTableFigureProviders.put(callingEditPart, figureProvider);
                return figureProvider;
            }
        }
        return null;
    }

    /**
     * Method for parsing the list size string of the extension point to an operator and the number.
     * 
     * @param Input
     *            a string with an operator and number read from the extension point.
     * @return A pair of and operator and an integer.
     */
    private Pair<String, Integer> parseListSize(final String input) {
        Pair<String, Integer> result = null;
        char[] charInput = input.toCharArray();
        boolean isOp = true;
        String operator = "";
        String numberString = "";
        for (char c : charInput) {
            if (isOp) {
                if ((c == '<') || (c == '>') || (c == '=') || (c == '!')) {
                    operator += c;
                } else {
                    isOp = false;
                    numberString += c;
                }
            } else {
                numberString += c;
            }
        }
        int number = Integer.parseInt(numberString);
        result = new Pair<String, Integer>(operator, number);
        return result;
    }
}
