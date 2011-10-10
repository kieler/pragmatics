/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.transformations;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.internal.xtend.expression.ast.Identifier;
import org.eclipse.internal.xtend.xtend.XtendFile;
import org.eclipse.internal.xtend.xtend.ast.Extension;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtend.XtendFacade;
import org.eclipse.xtend.XtendResourceParser;
import org.eclipse.xtend.typesystem.emf.EmfMetaModel;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;

import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.klighd.IModelTransformation;
import de.cau.cs.kieler.klighd.KlighdPlugin;
import de.cau.cs.kieler.klighd.TransformationContext;

/**
 * An implementation of {@link IModelTransformation} enabling Xtend-based model transformations.
 * 
 * @author chsch
 * @author mri
 * 
 * TODO this should propably be reworked using the ITransformationContext from ...core.model
 */
public class XtendBasedTransformation implements IModelTransformation<EObject, EObject> {

    private static final String TRANSFORMATION_EXTENSION_NAME = "transform";
    private static final String DEFAULT_TRANSFORMATION_EXTENSION_NAME = "onError";

    private static final HashMap<String, ENamedElement> NAME_ELEMENT_LOOK_UP
                         = new HashMap<String, ENamedElement>();
    
    // chsch: the sense of these both declarations is not obvious to me!
    //  TODO please clarify this and comment it.
    
    /** the property for the element mapping. */
    private static final Property<BiMap<EObject, EObject>> ELEMENT_MAPPING =
            new Property<BiMap<EObject, EObject>>("klighd.elementMapping",
                    HashBiMap.<EObject, EObject>create());
    
    private static BiMap<Object, Object> elementMapping = null;

    /**
     * adds a element to the source model and graphical target mapping.
     * 
     * @param viewId
     *            id of the view
     * @param source
     *            the source model element
     * @param target
     *            the graphical element
     * @deprecated
     */
    public static void addViewElement(final String viewId, final EObject source,
            final EObject target) {
        elementMapping.put(source, target);
    }

    private URL extfile;
    private String extension;
    private Map<String, EPackage> metamodels;
    private int countParams = 1;

    private boolean triedToInferClasses = false;
    private Class<?> sourceModelClass = null;
    private Class<?> targetModelClass = null;

    /**
     * The constructor.
     * 
     * @param extFileURL
     *            the extend file
     * @param theExtension
     *            the root extend function
     * @param theMetamodels
     *            the involved meta models
     */
    public XtendBasedTransformation(final URL extFileURL, final String theExtension,
            final List<EPackage> theMetamodels) {
        this.extfile = extFileURL;
        this.metamodels = new HashMap<String, EPackage>();

        for (EPackage mm : theMetamodels) {
            this.metamodels.put(mm.getNsPrefix(), mm);
        }

        if (theExtension != null && !theExtension.equals("")) {
            this.extension = theExtension;
        } else {
            this.extension = TRANSFORMATION_EXTENSION_NAME;
        }
    }

    /** the length of the string ".ext". */
    private static final int ENDING_OFFSET = 4;

    /**
     * Fires an instance of {@link XtendFacade} to execute the transformation.
     * 
     * @param model
     *            the source model
     * @param transformationContext
     *            the transformation context
     * @return the transformation result
     */
    public EObject transform(final EObject model,
            final TransformationContext<EObject, EObject> transformationContext) {
        
        // prepare the element mapping
        elementMapping = HashBiMap.create();
        transformationContext.setProperty(ELEMENT_MAPPING, elementMapping);
        
        String url = this.extfile.getFile();
        if (url.endsWith(".ext")) {
            url = url.substring(0, url.length() - ENDING_OFFSET);
        }

        XtendFacade facade = XtendFacade.create(url);
        facade.registerMetaModel(new EmfMetaModel(EcorePackage.eINSTANCE));
        for (EPackage mm : this.metamodels.values()) {
            facade.registerMetaModel(new EmfMetaModel(mm));
        }

        Object[] transformationParams = new Object[this.countParams];
        transformationParams[0] = model;

        EObject result = null;

        // the following allows to handle erroneous transformations while developing those
        // transformations; by this a valid model is returned anyway;
        // due to the performance optimization the light diagram service
        // will most likely mark the initial input model to be not supported
        // if "null" is returned by the transformation
        try {
            result = (EObject) facade.call(DEFAULT_TRANSFORMATION_EXTENSION_NAME, new Object[0]);
        } catch (Exception e) {
            /* nothing */
        }

        try {
            result = (EObject) facade.call(this.extension, transformationParams);
        } catch (Exception e) {
            StatusManager.getManager().handle(
                    new Status(IStatus.ERROR, KlighdPlugin.PLUGIN_ID,
                            "Xtend-based transformation execution failed"));
        }
        elementMapping = null;
        return result;
    }

    
    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object model) {
        /* chsch: do we still need this dummy? */
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Object getSourceElement(final Object element,
            final TransformationContext<EObject, EObject> transformationContext) {
        return transformationContext.getProperty(ELEMENT_MAPPING).inverse().get(element);
    }

    /**
     * {@inheritDoc}
     */
    public Object getTargetElement(final Object element,
            final TransformationContext<EObject, EObject> transformationContext) {
        return transformationContext.getProperty(ELEMENT_MAPPING).get(element);
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getSourceClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return sourceModelClass;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?> getTargetClass() {
        if (!triedToInferClasses) {
            inferSourceAndTargetModelClass();
        }
        return targetModelClass;
    }

    /**
     * Infers concrete information about the interface classes of a transformation specified in Xtend.
     * 
     * @author chsch, mri
     */
    private void inferSourceAndTargetModelClass() {

        triedToInferClasses = true;
        try {

            /* load the Xtend file */
            XtendFile ext =
                    (XtendFile) new XtendResourceParser().parse(
                            new InputStreamReader(extfile.openStream()), extfile.getFile());

            /* search the fitting extension */
            for (Extension e : ext.getExtensions()) {
                // by name...
                if (e.getName().equals(this.extension)) {

                    // reveal the class of the first parameter
                    sourceModelClass =
                            inferClassFromIdentifier(e.getFormalParameters().get(0).getType(), ext);
                    // reveal the class of the return type
                    targetModelClass = inferClassFromIdentifier(e.getReturnTypeIdentifier(), ext);
                    // check if both classes could be found
                    if (sourceModelClass != null && targetModelClass != null) {
                        // remember the actual number of parameters for convenience, needed during the
                        // execution below
                        countParams = e.getFormalParameters().size();
                        break;
                    } else {
                        // reset
                        sourceModelClass = null;
                        targetModelClass = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Infers a class denoted by an Ecore-based FQN or by means of the imported Ecore packages.
     *  
     * @author chsch, mri
     * @param identifier
     * @param ext
     * @return
     */
    private Class<?> inferClassFromIdentifier(final Identifier identifier, final XtendFile ext) {

        String type = identifier.getValue();
        EClassifier clazz = (EClassifier) NAME_ELEMENT_LOOK_UP.get(type);
        
        if (clazz != null) {
            return clazz.getInstanceClass();
        }
        
        int pos = type.lastIndexOf("::");
        String className = (pos == -1 ? type : type.substring(pos + 2));
        String packageName = (pos == -1 ? "" : type.substring(0, pos));
        EPackage ePackage = null;
        
        if (!Strings.isNullOrEmpty(packageName)) {
            ePackage = (EPackage) NAME_ELEMENT_LOOK_UP.get(packageName);
            if (ePackage == null) {
                ePackage = inferEPackage(packageName);
                // here, I assume that we will find the denoted package
                cacheENamedElement(packageName, ePackage);                
            }            
            clazz = ePackage.getEClassifier(className);
            cacheENamedElement(type, clazz);
            
        } else {
            for (String importedPackageName : ext.getImportedNamespaces()) {
                ePackage = (EPackage) NAME_ELEMENT_LOOK_UP.get(importedPackageName);
                if (ePackage == null) {
                    ePackage = inferEPackage(importedPackageName);
                    // here, I assume that we will find the denoted package
                    cacheENamedElement(importedPackageName, ePackage);                
                }            
                clazz = ePackage.getEClassifier(className);
                if (clazz != null) {
                    cacheENamedElement(type, clazz);
                    break;
                }
            }
        }
        
        if (clazz != null) {
            return clazz.getInstanceClass();
        }
        return null;
    }
    
    
    /**
     * Wrapper managing the conversion of Collection<Object> to List<EPackage>.
     * Implementation sucks. Does anybody know something better??<br>
     *   Don't come up with {@link Arrays#copyOf(original, newLength, newType)}
     * 
     * @author chsch
     * @param packageName concatenation of ecore package names separated by "::", NO java package name 
     * @return {@link EPackage} denoted by packageName
     */
    private EPackage inferEPackage(final String packageName) {
        ImmutableList<EPackage> ePackages = ImmutableList.copyOf(
            Collections2.filter(
                Collections2.transform(
                    EPackage.Registry.INSTANCE.values(),               
                    new Function<Object, EPackage>() {
                        public EPackage apply(final Object input) {
                            if (EcorePackage.eINSTANCE.getEPackage().isInstance(input)) {
                                return (EPackage) input;
                            } else {
                                return null;
                            }
                        }
                    }),
                Predicates.notNull()
            )
        );      
        return inferEPackage(packageName, ePackages);        
    }
    
    /** the length of the string "::". */
    private static final int SEP_OFFSET = 2;

    /** 
     * @author chsch
     * @param packageName concatenation of ecore package names separated by "::", NO java package name 
     * @param ePackages collection of ecore packages to look into
     * @return {@link EPackage} denoted by packageName
     */
    private EPackage inferEPackage(final String packageName, final List<EPackage> ePackages) {
        int firstSepPos = packageName.indexOf("::");
        String firstSegment = (firstSepPos == -1 ? packageName : packageName.substring(0, firstSepPos));
        String remainder = (firstSepPos == -1 ? null : packageName.substring(firstSepPos + SEP_OFFSET));
        
        if (ePackages.isEmpty()) {
            throw new IllegalArgumentException("(sub)package list is empty");
        }
        
        for (EPackage ePackage : ePackages) {
            if (ePackage.getName().equals(firstSegment)) {
                if (Strings.isNullOrEmpty(remainder)) {
                    return ePackage;
                } else {                    
                    return inferEPackage(remainder, ePackage.getESubpackages());
                }
            }
        }
        return null;
    }
    
    /**
     * @author chsch
     * @param name the elements name
     * @param namedElement the element to be kept in mind
     */    
    private synchronized void cacheENamedElement(final String name, final ENamedElement namedElement) {
        NAME_ELEMENT_LOOK_UP.put(name, namedElement);
    }
}
