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
 */
package de.cau.cs.kieler.kiml.ui.diagram;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Interface for managers of diagram layout. A diagram layout manager is responsible for transforming
 * the diagram contained in a workbench part into a layout graph, which is an instance of the
 * KGraph meta model. Furthermore it must handle the transfer of concrete layout data from the
 * layout graph back to the diagram after a layout has been computed.
 * <p>
 * The {@code IAdapterFactory} interface is used to access specific elements:
 * <ul>
 *   <li>Implementations of {@link de.cau.cs.kieler.kiml.config.IMutableLayoutConfig} for reading
 *     and writing layout options in a specific diagram</li>
 *   <li>Diagram parts such as GEF edit parts</li>
 *   <li>Domain model elements: {@link org.eclipse.emf.ecore.EObject}</li>
 *   <li>Editing domains: {@link org.eclipse.emf.transaction.TransactionalEditingDomain}
 * </ul>
 * The {@link #getAdapterList()} method should return exactly one element, namely the type of
 * diagram part, which should also match the generic type of the implementation.
 * 
 * @param <T> the type of diagram part that is handled by this diagram layout manager
 * @kieler.rating yellow 2012-07-19 review KI-20 by cds, jjc
 * @author msp
 */
public interface IDiagramLayoutManager<T> extends IAdapterFactory {

    /**
     * Determine whether this layout manager is able to perform layout for the given object.
     * 
     * @param object a workbench part or edit part
     * @return true if this layout manager supports the object
     */
    boolean supports(Object object);

    /**
     * Build a KGraph instance for the given diagram. The resulting layout graph should reflect
     * the structure of the original diagram. All graph elements must have
     * {@link de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout KShapeLayouts} or
     * {@link de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout KEdgeLayouts} attached,
     * and their modification flags must be set to {@code false}.
     * <p/>
     * Layout options should not be directly set for any graph elements, since they would be
     * cleared later by the
     * {@link de.cau.cs.kieler.kiml.ui.service.LayoutOptionManager LayoutOptionManager}.
     * Instead the {@link LayoutMapping#getLayoutConfigs()} list should be augmented
     * with according layout configurators.
     * <p/>
     * At least one of the two parameters must be non-null.
     * 
     * @param workbenchPart
     *            the workbench part for which layout is performed, or {@code null} if there
     *            is no workbench part for the diagram
     * @param diagramPart
     *            the parent object for which layout is performed, or {@code null} if the
     *            whole diagram shall be layouted
     * @return a layout graph mapping
     */
    LayoutMapping<T> buildLayoutGraph(IWorkbenchPart workbenchPart,
            Object diagramPart);

    /**
     * Apply the computed layout back to the diagram. Graph elements whose modification flag
     * was not set during layout should be ignored.
     * 
     * @param mapping a layout mapping that was created by this layout manager
     * @param zoomToFit whether the diagram should zoom to fit
     * @param animationTime the animation time in milliseconds, or 0 for no animation
     * @see de.cau.cs.kieler.kiml.klayoutdata.impl.KShapeLayoutImpl#isModified()
     * @see de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl#isModified()
     */
    void applyLayout(LayoutMapping<T> mapping, boolean zoomToFit,
            int animationTime);
    
    /**
     * Undo the layout in the original diagram (optional operation).
     * 
     * @param mapping a layout mapping that was created by this layout manager
     */
    void undoLayout(LayoutMapping<T> mapping);

}
