/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klighd.kdiagram.jvmmodel;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend.core.scoping.ExtensionMethodsFeaturesProvider;
import org.eclipse.xtend.core.scoping.StaticallyImportedFeaturesProvider;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmAnnotationTarget;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.util.AnnotationLookup;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XbaseFactory;
import org.eclipse.xtext.xbase.annotations.scoping.XbaseWithAnnotationsScopeProvider;
import org.eclipse.xtext.xbase.jvmmodel.IJvmModelAssociations;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.scoping.featurecalls.IJvmFeatureDescriptionProvider;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * A specialized {@link XbaseWithAnnotationsScopeProvider} with some code copied (and slightly
 * modified) from {@link org.eclipse.xtend.core.scoping.XtendScopeProvider XtendScopeProvider}.<br>
 * <br>
 * Realizes the visibility of the methods in the K...Extensions (those classes are visible
 * due to synthesis of related fields in the {@link KDiagramJvmModelInferrer}). 
 * 
 * @author chsch
 */
@SuppressWarnings({"restriction", "deprecation"})
public class KDiagramScopeProvider extends XbaseWithAnnotationsScopeProvider {

    private static final int DEFAULT_EXTENSION_PRIORITY = 45;

    private static final int THIS_EXTENSION_PRIORITY_OFFSET = 200;
    private static final int DYNAMIC_EXTENSION_PRIORITY_OFFSET = 210;

    @Inject
    private IJvmModelAssociations associations;

    @Inject
    private AnnotationLookup annotationLookup;

    @Inject
    private Provider<StaticallyImportedFeaturesProvider> staticallyImportedFeaturesProvider;

    @Inject
    private Provider<ExtensionMethodsFeaturesProvider> extensionMethodsFeaturesProvider;

    @Inject
    private TypeReferences typeReferences;
    
    @Override
    protected void addFeatureDescriptionProviders(
                    Resource resource, 
                    JvmDeclaredType contextType,
                    XExpression implicitReceiver,
                    XExpression implicitArgument,
                    int priority,
                    IAcceptor<IJvmFeatureDescriptionProvider> acceptor) {
            super.addFeatureDescriptionProviders(resource, contextType, implicitReceiver, implicitArgument, priority, acceptor);
            
//            if (implicitReceiver == null || implicitArgument != null) {
//                    final StaticallyImportedFeaturesProvider staticProvider = staticallyImportedFeaturesProvider.get();
//                    staticProvider.setResourceContext(resource);
//                    staticProvider.setExtensionProvider(true);
//                    if (implicitArgument != null) {
//                            // use the implicit argument as implicit receiver
//                            SimpleAcceptor casted = (SimpleAcceptor) acceptor;
//                            JvmTypeReference implicitArgumentType = getTypeProvider().getType(implicitArgument, true);
//                            IAcceptor<IJvmFeatureDescriptionProvider> myAcceptor = casted.getParent().curry(implicitArgumentType, casted.getExpression());
//                            addFeatureDescriptionProviders(contextType, staticProvider, implicitArgument, null, priority + STATIC_EXTENSION_PRIORITY_OFFSET, true, myAcceptor);
//                    } else {
//                            addFeatureDescriptionProviders(contextType, staticProvider, implicitReceiver, implicitArgument, priority + STATIC_EXTENSION_PRIORITY_OFFSET, true, acceptor);
//                    }
//            }
            
            // extensions for this
            if (contextType instanceof JvmGenericType) {
                    final JvmGenericType inferredJvmType = (JvmGenericType) contextType;
                    
                    boolean isThis = false;
                    if (implicitReceiver instanceof XFeatureCall) {
                            isThis = ((XFeatureCall) implicitReceiver).getFeature() == inferredJvmType;
                    }
                    if (implicitReceiver == null || isThis) {
                            XFeatureCall callToThis = XbaseFactory.eINSTANCE.createXFeatureCall();
                            callToThis.setFeature(inferredJvmType);
                            // injected extensions
                            Iterable<JvmField> extensionFields = getExtensionDependencies(inferredJvmType);
                            int extensionPriority = priority + DYNAMIC_EXTENSION_PRIORITY_OFFSET;
                            if (isThis && implicitArgument == null)
                                    extensionPriority = DEFAULT_EXTENSION_PRIORITY;
                            boolean isStatic = false; //isStaticContext(((SimpleAcceptor)acceptor).getExpression());
                            for (JvmField dependencyImplicitReceiver : extensionFields) {
                                    XMemberFeatureCall callToDependency = XbaseFactory.eINSTANCE.createXMemberFeatureCall();
                                    callToDependency.setMemberCallTarget(EcoreUtil2.clone(callToThis));
                                    callToDependency.setFeature(dependencyImplicitReceiver);
                                    if (dependencyImplicitReceiver != null) {
                                            ExtensionMethodsFeaturesProvider extensionFeatureProvider = extensionMethodsFeaturesProvider.get();
                                            extensionFeatureProvider.setContext(dependencyImplicitReceiver.getType());
                                            extensionFeatureProvider.setExpectNoParameters(isThis);
                                            addFeatureDescriptionProviders(contextType, extensionFeatureProvider, callToDependency, implicitArgument, extensionPriority, isStatic, acceptor);
                                    }
                            }
                            JvmTypeReference typeRef = typeReferences.createTypeRef(inferredJvmType);
                            ExtensionMethodsFeaturesProvider featureProvider = extensionMethodsFeaturesProvider.get();
                            featureProvider.setContext(typeRef);
                            featureProvider.setExpectNoParameters(isThis);
                            addFeatureDescriptionProviders(contextType, featureProvider, callToThis, implicitArgument, priority + THIS_EXTENSION_PRIORITY_OFFSET, isStatic, acceptor);
                    }
            }
    }
    
    protected Iterable<JvmField> getExtensionDependencies(JvmGenericType type) {
        List<JvmField> result = Lists.newLinkedList();
        collectExtensionDependencies(type, result, true, Sets.<String> newHashSet(),
                Sets.<JvmType> newHashSet());
        return result;
    }

    protected void collectExtensionDependencies(JvmDeclaredType type, List<JvmField> result,
            boolean allowPrivate, Set<String> seenNames, Set<JvmType> seenTypes) {
        if (seenTypes.add(type)) {
            Iterable<JvmField> fields = type.getDeclaredFields();
            for (JvmField field : fields) {
                if ((allowPrivate || (field.getVisibility() != JvmVisibility.PRIVATE))
                        && seenNames.add(field.getSimpleName()) && isExtensionProvider(field)) {
                    result.add(field);
                }
            }
        }
    }

    protected boolean isExtensionProvider(JvmAnnotationTarget annotatedElement) {
        // coding style to simplify debugging
        if (annotationLookup.findAnnotation(annotatedElement, Extension.class) != null) {
            return true;
        }
        return false;
    }

}
