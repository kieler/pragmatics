/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://rtsys.informatik.uni-kiel.de/kieler
 * 
 * Copyright 2017 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 */
package de.cau.cs.kieler.graphs.testcases;

import java.util.function.Predicate;

import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkNode;

import de.cau.cs.kieler.klighd.ViewContext;

public interface ITestCaseGraphProvider {

	ViewContext getViewContext();
	
	Iterable<IGraphElementVisitor> getGraphModifiers(); 
	
	Iterable<Predicate<ElkNode>> getGraphFilters();
	
}
