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
package de.cau.cs.kieler.klighd.kdiagram.jvmmodel

import com.google.common.collect.Iterators

import de.cau.cs.kieler.core.kgraph.KNode
import de.cau.cs.kieler.core.krendering.KRendering

import java.util.Iterator
import javax.inject.Inject

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import org.eclipse.xtext.xbase.lib.Procedures

/**
 * An helper class contributing a code generation template.<br>
 * The template has been outsourced from the KDiagramJvmModelInferrer for reasons of clarity and comprehensibility.
 *   
 * @author chsch
 */
class NodeMappingTemplate {
    
    @Inject
    extension CodeGenHelper

    def Procedures.Procedure1<ITreeAppendable> nodeMapping(EObject context, String figureType) {
        setContext(context);
        return [
            it + typeof(Iterator).type + '''<?> it;''' + newline
            
            + '''if (elements instanceof ''' + typeof(Iterator).type + ''') {''';
                it.increaseIndentation + newline
                + '''it = (Iterator<?>) elements;''';
                it.decreaseIndentation + newline
            + '''} else if (elements instanceof ''' + typeof(Iterable).type + ''') {''';
                it.increaseIndentation + newline
                + '''it = ((Iterable<?>) elements).iterator();''';
                it.decreaseIndentation + newline
            + '''} else {''';
                it.increaseIndentation + newline
                + '''it = ''' + typeof(Iterators).type + '''.singletonIterator(elements);''';
                it.decreaseIndentation + newline
            + '''}''' + newline
            + newline
            
            + '''while(it.hasNext()) { ''';
                it.increaseIndentation + newline
                + ('''final Object obj = it.next();''') + newline
                + ('''final ''') + typeof(KNode).type + ''' node = this.kNodeExtensions.createNode(obj);''' + newline
                + '''this.putToLookUpWith(node, obj);''' + newline                
                + '''parent.getChildren().add(node);''' + newline
                + newline
                
                + '''final ''' + typeof(KRendering).type + ''' r = this.kRenderingExtensions.add«figureType»(node);'''
                + newline
                + '''node.getData().add(r);''';
                it.decreaseIndentation + newline
            + '''}''';
        ]
    }
}

