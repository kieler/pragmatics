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
package de.cau.cs.kieler.klighd.kdiagram.ui.contentassist;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import de.cau.cs.kieler.core.krendering.KArc;
import de.cau.cs.kieler.core.krendering.KEllipse;
import de.cau.cs.kieler.core.krendering.KPolygon;
import de.cau.cs.kieler.core.krendering.KPolyline;
import de.cau.cs.kieler.core.krendering.KRectangle;
import de.cau.cs.kieler.core.krendering.KRoundedBendsPolyline;
import de.cau.cs.kieler.core.krendering.KRoundedRectangle;
import de.cau.cs.kieler.core.krendering.KSpline;

/**
 * Customization of the content assist proposals. Currently it only prunes the figure type proposals of
 * {@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.NodeMapping NodeMappings} and
 * {@link de.cau.cs.kieler.klighd.kdiagram.kDiagram.EdgeMapping EdgeMappings}
 * 
 * @author chsch
 */
public class KDiagramProposalProvider extends AbstractKDiagramProposalProvider {

    private static final List<String> figureTypes = Lists.transform(
            Lists.<Class<?>>newArrayList(KArc.class, KEllipse.class, KRectangle.class, KRoundedRectangle.class,
                    KPolygon.class, KPolyline.class, KRoundedBendsPolyline.class, KSpline.class),
            new Function<Class<?>, String>() {
               public String apply(final Class<?> clazz) {
                   return clazz.getSimpleName();
               }
            }
    );
    
    @Override
    @SuppressWarnings("restriction")
    public void completeNodeMapping_FigureType(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        final Predicate<IEObjectDescription> predicate = new Predicate<IEObjectDescription>() {
            public boolean apply(final IEObjectDescription desc) {
                return figureTypes.contains(desc.getQualifiedName().getLastSegment());
            }
        };
        lookupCrossReference(((CrossReference) assignment.getTerminal()), context, acceptor,
                Predicates.and(predicate, getFeatureDescriptionPredicate(context)));
    }
    
    @Override
    @SuppressWarnings("restriction")
    public void completeEdgeMapping_FigureType(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
        final Predicate<IEObjectDescription> predicate = new Predicate<IEObjectDescription>() {
            public boolean apply(final IEObjectDescription desc) {
                return figureTypes.contains(desc.getQualifiedName().getLastSegment());
            }
        };
        lookupCrossReference(((CrossReference) assignment.getTerminal()), context, acceptor,
                Predicates.and(predicate, getFeatureDescriptionPredicate(context)));
    }
}
