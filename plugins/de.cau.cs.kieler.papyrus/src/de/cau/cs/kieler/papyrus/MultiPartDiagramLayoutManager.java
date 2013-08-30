/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2012 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.papyrus;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.impl.EdgeImpl;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.IPapyrusEditPart;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Font;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.BiMap;

import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KGraphFactory;
import de.cau.cs.kieler.core.kgraph.KLabel;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.core.math.KVector;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.gmf.GmfDiagramLayoutManager;
import de.cau.cs.kieler.kiml.gmf.GmfLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KEdgeLayoutImpl;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KShapeLayoutImpl;
import de.cau.cs.kieler.kiml.options.EdgeLabelPlacement;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Layout manager wrapper for the Papyrus multi diagram editor.
 * 
 * @author msp original layout manager
 * @author grh adaptions for sequence diagram layout
 * @kieler.design proposed grh
 * @kieler.rating proposed yellow grh
 */
public class MultiPartDiagramLayoutManager extends GmfDiagramLayoutManager {

    /** list of connection edit parts that were found in the diagram. */
    public static final IProperty<List<ConnectionEditPart>> CONNECTIONS = 
            new Property<List<ConnectionEditPart>>("gmf.connections");

    /** editor part of the currently layouted diagram. */
    public static final IProperty<DiagramEditor> DIAGRAM_EDITOR = new Property<DiagramEditor>(
            "gmf.diagramEditor");

    /** diagram edit part of the currently layouted diagram. */
    public static final IProperty<DiagramEditPart> DIAGRAM_EDIT_PART = new Property<DiagramEditPart>(
            "gmf.diagramEditPart");

    /** the command that applies the transferred layout to the diagram. */
    public static final IProperty<Command> LAYOUT_COMMAND = new Property<Command>(
            "gmf.applyLayoutCommand");

    /** the command stack that executes the command. */
    public static final IProperty<CommandStack> COMMAND_STACK = new Property<CommandStack>(
            "gmf.applyLayoutCommandStack");

    /** the volatile layout config for static properties such as minimal node sizes. */
    public static final IProperty<VolatileLayoutConfig> STATIC_CONFIG = 
            new Property<VolatileLayoutConfig>("gmf.staticLayoutConfig");

    /** the map of references and edges. */
    private Map<EReference, KEdge> reference2EdgeMap;

    /** the cached layout configuration for GMF. */
    private GmfLayoutConfig layoutConfig = new PapyrusLayoutConfig();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Object object) {
        if (object instanceof Collection) {
            Collection<?> collection = (Collection<?>) object;
            for (Object o : collection) {
                if (o instanceof IPapyrusEditPart) {
                    return true;
                }
            }
            return false;
        }
        return object instanceof IMultiDiagramEditor || object instanceof IPapyrusEditPart
                || object instanceof IGraphicalEditPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getAdapter(final Object object, final Class adapterType) {
        if (adapterType.isAssignableFrom(GmfLayoutConfig.class)) {
            return layoutConfig;
        }
        if (object instanceof IMultiDiagramEditor) {
            return super.getAdapter(((IMultiDiagramEditor) object).getActiveEditor(), adapterType);
        }
        return super.getAdapter(object, adapterType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LayoutMapping<IGraphicalEditPart> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        if (workbenchPart instanceof IMultiDiagramEditor) {
            IWorkbenchPart part = ((IMultiDiagramEditor) workbenchPart).getActiveEditor();
            if (part.getClass().getSimpleName().equals("UmlSequenceDiagramForMultiEditor")) {
                // Build KGraph in a different way if it is a sequence diagram
                return buildSequenceLayoutGraph(part, diagramPart);
            }
            LayoutMapping<IGraphicalEditPart> mapping = super.buildLayoutGraph(part, diagramPart);
            return mapping;

        } else {
            return super.buildLayoutGraph(workbenchPart, diagramPart);
        }
    }

    /**
     * Special method to build a layoutGraph when the diagram is a sequence diagram.
     * 
     * @param workbenchPart
     *            the workbenchPart
     * @param diagramPart
     *            the diagramPart
     * @return a layoutGraph
     */
    protected LayoutMapping<IGraphicalEditPart> buildSequenceLayoutGraph(
            final IWorkbenchPart workbenchPart, final Object diagramPart) {

        DiagramEditor diagramEditor = null;

        // get the diagram editor part
        if (workbenchPart instanceof DiagramEditor) {
            diagramEditor = (DiagramEditor) workbenchPart;
        }

        // choose the layout root edit part
        IGraphicalEditPart layoutRootPart = null;
        if (diagramPart instanceof ShapeNodeEditPart || diagramPart instanceof DiagramEditPart) {
            layoutRootPart = (IGraphicalEditPart) diagramPart;
        } else if (diagramPart instanceof IGraphicalEditPart) {
            EditPart tgEditPart = ((IGraphicalEditPart) diagramPart).getTopGraphicEditPart();
            if (tgEditPart instanceof ShapeNodeEditPart) {
                layoutRootPart = (IGraphicalEditPart) tgEditPart;
            }
        }
        if (layoutRootPart == null && diagramEditor != null) {
            layoutRootPart = diagramEditor.getDiagramEditPart();
        }
        if (layoutRootPart == null) {
            throw new UnsupportedOperationException(
                    "Not supported by this layout manager: Workbench part " + workbenchPart
                            + ", Edit part " + diagramPart);
        }

        // create the mapping
        LayoutMapping<IGraphicalEditPart> mapping = buildSequenceLayoutGraph(layoutRootPart);

        // set optional diagram editor
        if (diagramEditor != null) {
            mapping.setProperty(DIAGRAM_EDITOR, diagramEditor);
        }

        // create a layout configuration
        mapping.getLayoutConfigs().add(mapping.getProperty(STATIC_CONFIG));
        mapping.getLayoutConfigs().add(layoutConfig);

        return mapping;
    }

    /**
     * Creates the actual mapping given an edit part which functions as the root for the layout.
     * 
     * @param layoutRootPart
     *            the layout root edit part
     * @return a layout graph mapping
     */
    protected LayoutMapping<IGraphicalEditPart> buildSequenceLayoutGraph(
            final IGraphicalEditPart layoutRootPart) {
        LayoutMapping<IGraphicalEditPart> mapping = new LayoutMapping<IGraphicalEditPart>(this);
        mapping.setProperty(CONNECTIONS, new LinkedList<ConnectionEditPart>());
        mapping.setProperty(STATIC_CONFIG, new VolatileLayoutConfig());

        // set the parent element
        mapping.setParentElement(layoutRootPart);

        // find the diagram edit part
        mapping.setProperty(DIAGRAM_EDIT_PART, getDiagramEditPart(layoutRootPart));

        KNode topNode = KimlUtil.createInitializedNode();
        KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
        Rectangle rootBounds = layoutRootPart.getFigure().getBounds();
        if (layoutRootPart instanceof DiagramEditPart) {
            // start with the whole diagram as root for layout
            String labelText = ((DiagramEditPart) layoutRootPart).getDiagramView().getName();
            if (labelText.length() > 0) {
                KLabel label = KimlUtil.createInitializedLabel(topNode);
                label.setText(labelText);
            }
        } else {
            // start with a specific node as root for layout
            shapeLayout.setPos(rootBounds.x, rootBounds.y);
        }
        shapeLayout.setSize(rootBounds.width, rootBounds.height);
        mapping.getGraphMap().put(topNode, layoutRootPart);
        mapping.setLayoutGraph(topNode);

        // traverse the children of the layout root part
        buildSequenceLayoutGraphRecursively(mapping, layoutRootPart, topNode, layoutRootPart);
        // transform all connections in the selected area
        processSequenceConnections(mapping);

        // copy annotations from KShapeLayout to VolatileLayoutConfig
        copyAnnotations(mapping, topNode);

        return mapping;
    }

    /**
     * Copy the annotations to the static config.
     * 
     * @param mapping
     *            the layout mapping
     * @param topNode
     *            the layout root part
     */
    private void copyAnnotations(final LayoutMapping<IGraphicalEditPart> mapping,
            final KNode topNode) {
        KShapeLayout nodelayout = topNode.getData(KShapeLayout.class);
        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);

        // Copy the executions
        List<SequenceExecution> executions = nodelayout.getProperty(PapyrusProperties.EXECUTIONS);
        if (executions != null) {
            staticConfig.setValue(PapyrusProperties.EXECUTIONS, topNode, LayoutContext.GRAPH_ELEM,
                    executions);
        } else {
            for (KNode node : topNode.getChildren()) {
                copyAnnotations(mapping, node);
            }
        }

        // Copy the information to which element a comment is attached to
        List<Object> attachedTo = nodelayout.getProperty(PapyrusProperties.ATTACHED_TO);
        if (attachedTo != null) {
            List<Object> attTo = new LinkedList<Object>();
            BiMap<KGraphElement, IGraphicalEditPart> graphMap = mapping.getGraphMap();
            for (Object att : attachedTo) {
                attTo.add(graphMap.inverse().get(att));
            }
            staticConfig.setValue(PapyrusProperties.ATTACHED_TO, topNode, LayoutContext.GRAPH_ELEM,
                    attTo);
        }

        String attachedElement = nodelayout.getProperty(PapyrusProperties.ATTACHED_ELEMENT);
        if (attachedElement != null) {
            staticConfig.setValue(PapyrusProperties.ATTACHED_ELEMENT, topNode,
                    LayoutContext.GRAPH_ELEM, attachedElement);
        }
    }

    /**
     * Recursively builds a layout graph by analyzing the children of the given edit part.
     * 
     * @param mapping
     *            the layout mapping
     * @param parentEditPart
     *            the parent edit part of the current elements
     * @param parentLayoutNode
     *            the corresponding KNode
     * @param currentEditPart
     *            the currently analyzed edit part
     */
    private void buildSequenceLayoutGraphRecursively(
            final LayoutMapping<IGraphicalEditPart> mapping,
            final IGraphicalEditPart parentEditPart, final KNode parentLayoutNode,
            final IGraphicalEditPart currentEditPart) {
        Maybe<KInsets> kinsets = new Maybe<KInsets>();

        parentLayoutNode.getData(KShapeLayout.class).setProperty(PapyrusProperties.AREAS,
                new LinkedList<SequenceArea>());

        // iterate through the children of the element
        for (Object obj : currentEditPart.getChildren()) {
            // check visibility of the child
            if (obj instanceof IGraphicalEditPart) {
                IFigure figure = ((IGraphicalEditPart) obj).getFigure();
                if (!figure.isVisible()) {
                    continue;
                }
            }

            // process a compartment, which may contain other elements
            if (obj instanceof ResizableCompartmentEditPart
                    && ((CompartmentEditPart) obj).getChildren().size() > 0) {
                CompartmentEditPart compartment = (CompartmentEditPart) obj;
                if (!GmfLayoutConfig.isNoLayout(compartment)) {
                    boolean compExp = true;
                    IFigure compartmentFigure = compartment.getFigure();
                    if (compartmentFigure instanceof ResizableCompartmentFigure) {
                        ResizableCompartmentFigure resizCompFigure = 
                                (ResizableCompartmentFigure) compartmentFigure;
                        // check whether the compartment is collapsed
                        compExp = resizCompFigure.isExpanded();
                    }

                    if (compExp) {
                        buildSequenceLayoutGraphRecursively(mapping, parentEditPart,
                                parentLayoutNode, compartment);
                    }
                }

                // process a node, which may be a parent of ports, compartments, or other nodes
            } else if (obj instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childNodeEditPart = (ShapeNodeEditPart) obj;
                if (!GmfLayoutConfig.isNoLayout(childNodeEditPart)) {
                    createSequenceNode(mapping, childNodeEditPart, parentEditPart, parentLayoutNode,
                            kinsets);
                }

                // process a label of the current node
            } else if (obj instanceof IGraphicalEditPart) {
                createSequenceNodeLabel(mapping, (IGraphicalEditPart) obj, parentEditPart,
                        parentLayoutNode);
            }
        }
    }

    /**
     * Create a node while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the node edit part
     * @param parentEditPart
     *            the parent node edit part that contains the current node
     * @param parentKNode
     *            the corresponding parent layout node
     * @param kinsets
     *            reference parameter for insets; the insets are calculated if this has not been
     *            done before
     */
    private void createSequenceNode(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart nodeEditPart, final IGraphicalEditPart parentEditPart,
            final KNode parentKNode, final Maybe<KInsets> kinsets) {

        IFigure nodeFigure = nodeEditPart.getFigure();
        KNode childLayoutNode = KimlUtil.createInitializedNode();

        String nodeType = "";
        // Add node type information to the KNode
        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
        if (nodeEditPart.getModel() instanceof ShapeImpl) {
            ShapeImpl impl = (ShapeImpl) nodeEditPart.getModel();
            nodeType = impl.getType();
            staticConfig.setValue(PapyrusProperties.NODE_TYPE, childLayoutNode,
                    LayoutContext.GRAPH_ELEM, nodeType);
        }

        // set location and size
        Rectangle childBounds = getAbsoluteBounds(nodeFigure);
        Rectangle containerBounds = getAbsoluteBounds(nodeFigure.getParent());
        KShapeLayout nodeLayout = childLayoutNode.getData(KShapeLayout.class);

        nodeLayout.setXpos(childBounds.x - containerBounds.x);
        nodeLayout.setYpos(childBounds.y - containerBounds.y);
        nodeLayout.setSize(childBounds.width, childBounds.height);

        // the modification flag must initially be false
        ((KShapeLayoutImpl) nodeLayout).resetModificationFlag();

        // determine minimal size of the node
        try {
            Dimension minSize = nodeFigure.getMinimumSize();
            staticConfig.setValue(LayoutOptions.MIN_WIDTH, childLayoutNode,
                    LayoutContext.GRAPH_ELEM, (float) minSize.width);
            staticConfig.setValue(LayoutOptions.MIN_HEIGHT, childLayoutNode,
                    LayoutContext.GRAPH_ELEM, (float) minSize.height);
        } catch (SWTException exception) {
            // ignore exception and leave the default minimal size
        }

        // set insets if not yet defined
        if (kinsets.get() == null) {
            KInsets ki = parentKNode.getData(KShapeLayout.class).getInsets();
            Insets insets = calcSpecificInsets(parentEditPart.getFigure(), nodeFigure);
            ki.setLeft(insets.left);
            ki.setTop(insets.top);
            ki.setRight(insets.right);
            ki.setBottom(insets.bottom);
            kinsets.set(ki);
        }

        parentKNode.getChildren().add(childLayoutNode);
        mapping.getGraphMap().put(childLayoutNode, nodeEditPart);

        // process the child as new current edit part
        if (nodeType.equals("2001")) {
            // Node is the surrounding interaction
            buildSequenceLayoutGraphRecursively(mapping, nodeEditPart, childLayoutNode,
                    nodeEditPart);
        } else if (nodeType.equals("3001")) {
            // Node is lifeline
            handleLifeline(mapping, nodeEditPart, childLayoutNode);
        } else if (nodeType.equals("3002") || nodeType.equals("3004")) {
            // Handle areas such as interactionUse, combinedFragment and interactionOperand
            handleAreas(mapping, nodeEditPart, parentKNode, childLayoutNode);
        } else if (nodeType.equals("3009") || nodeType.equals("3008") || nodeType.equals("3024")
                || nodeType.equals("3020")) {
            handleComments(mapping, nodeEditPart, nodeLayout);
        }
        // store all the connections to process them later
        addConnections(mapping, nodeEditPart);
    }

    /**
     * Handle a node that represents a lifeline. Especially handle sub-nodes like execution
     * specifications.
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the current node edit part
     * @param layoutNode
     *            the created KNode
     */
    private void handleLifeline(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart nodeEditPart, final KNode layoutNode) {
        // handle subnodes like execution specifications
        List<SequenceExecution> executions = new LinkedList<SequenceExecution>();
        for (Object child : nodeEditPart.getChildren()) {
            if (child instanceof ShapeNodeEditPart) {
                ShapeNodeEditPart childEditPart = (ShapeNodeEditPart) child;
                String subNodeType = "";
                if (childEditPart.getModel() instanceof ShapeImpl) {
                    ShapeImpl shape = (ShapeImpl) childEditPart.getModel();
                    subNodeType = shape.getType();
                }
                IFigure subNodeFigure = childEditPart.getFigure();
                KNode subNode = KimlUtil.createInitializedNode();

                mapping.getGraphMap().put(subNode, childEditPart);

                // Copy layout information
                Rectangle subNodeBounds = getAbsoluteBounds(subNodeFigure);
                Rectangle subNodeContainerBounds = getAbsoluteBounds(subNodeFigure.getParent());
                KShapeLayout subNodeLayout = subNode.getData(KShapeLayout.class);
                subNodeLayout.setXpos(subNodeBounds.x - subNodeContainerBounds.x);
                subNodeLayout.setYpos(subNodeBounds.y - subNodeContainerBounds.y);
                subNodeLayout.setSize(subNodeBounds.width, subNodeBounds.height);

                if (subNodeType.equals("3003") || subNodeType.equals("3006")
                        || subNodeType.equals("3019") || subNodeType.equals("3021")) {
                    // Create Execution Object (which handles all these types) and initialize it
                    createExecution(mapping, nodeEditPart, executions, childEditPart, subNodeType,
                            subNode);
                } else if (subNodeType.equals("3022")) {
                    // Subnode is destruction event
                    layoutNode.getData(KShapeLayout.class).setProperty(
                            PapyrusProperties.DESTRUCTION, subNode);
                }

                // the modification flag must initially be false
                ((KShapeLayoutImpl) subNodeLayout).resetModificationFlag();
            }
        }
        if (executions.size() > 0) {
            layoutNode.getData(KShapeLayout.class).setProperty(PapyrusProperties.EXECUTIONS,
                    executions);
        }
    }

    /**
     * Handle nodes that represent area-like objects (interaction use, combined fragments).
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the current node edit part
     * @param parentKNode
     *            the parent KNode
     * @param layoutNode
     *            the created KNode
     */
    private void handleAreas(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart nodeEditPart, final KNode parentKNode, final KNode layoutNode) {
        IFigure nodeFigure = nodeEditPart.getFigure();
        Rectangle bounds = getAbsoluteBounds(nodeFigure);
        Rectangle parentBounds = getAbsoluteBounds(nodeFigure.getParent());

        SequenceArea area = new SequenceArea(layoutNode);

        // Copy layout information
        area.getPosition().x = bounds.x - parentBounds.x;
        area.getPosition().y = bounds.y - parentBounds.y;
        area.getSize().x = bounds.width;
        area.getSize().y = bounds.height;

        List<SequenceArea> areas = parentKNode.getData(KShapeLayout.class).getProperty(
                PapyrusProperties.AREAS);
        areas.add(area);
        
        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
        staticConfig.setValue(PapyrusProperties.AREAS, parentKNode, LayoutContext.GRAPH_ELEM, areas);
        
        // Get coordinates of the interaction operands if existing
        for (Object child : nodeEditPart.getChildren()) {
            if (child instanceof ListCompartmentEditPart) {
                ListCompartmentEditPart lcEditPart = (ListCompartmentEditPart) child;
                for (Object childObj : lcEditPart.getChildren()) {
                    if (childObj instanceof AbstractBorderedShapeEditPart) {
                        AbstractBorderedShapeEditPart ioEditPart = 
                                (AbstractBorderedShapeEditPart) childObj;
                        Rectangle ioBounds = getAbsoluteBounds(ioEditPart.getFigure());
                        KNode areaNode = KimlUtil.createInitializedNode();
                        mapping.getGraphMap().put(areaNode, ioEditPart);
                        SequenceArea subArea = new SequenceArea(areaNode);
                        // Copy layout information
                        subArea.getPosition().x = ioBounds.x - parentBounds.x;
                        subArea.getPosition().y = ioBounds.y - parentBounds.y;
                        subArea.getSize().x = ioBounds.width;
                        subArea.getSize().y = ioBounds.height;
                        area.getSubAreas().add(subArea);
                    }
                }
            }
        }
    }

    /**
     * Handle nodes that represent comments, constraints or observations.
     * 
     * @param mapping
     *            the layout mapping
     * @param nodeEditPart
     *            the current node edit part
     * @param nodeLayout
     *            the node layout
     */
    private void handleComments(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart nodeEditPart, final KShapeLayout nodeLayout) {
        // FIXME time observations are not detected properly

        // Handle comments, constraints and observations
        List<Object> attachedTo = new LinkedList<Object>();
        // Process connections of the object
        for (Object connObj : nodeEditPart.getSourceConnections()) {
            if (connObj instanceof ConnectionEditPart) {
                ConnectionEditPart connedit = (ConnectionEditPart) connObj;
                mapping.getProperty(CONNECTIONS).add(connedit);
                nodeLayout.setProperty(PapyrusProperties.ATTACHED_ELEMENT, connedit.getTarget()
                        .getClass().getSimpleName());
                // If target is lifeline, attach to the nearest message
                if (connedit.getTarget() instanceof ShapeNodeEditPart) {
                    float yPos = connedit.getConnectionFigure().getPoints().getLastPoint().y();
                    ConnectionEditPart nearestMessage = findMessageBelowPoint(
                            (ShapeNodeEditPart) connedit.getTarget(), connedit, yPos);
                    if (nearestMessage != null) {
                        attachedTo.add(nearestMessage);
                    }
                } else {
                    // If target already is a message, attach to that message
                    attachedTo.add(connedit.getTarget());
                }
            }
        }

        // If the object is connected to any other object, attach property with connected
        // objects
        if (attachedTo.size() > 0) {
            nodeLayout.setProperty(PapyrusProperties.ATTACHED_TO, attachedTo);
        }
    }

    /**
     * Creates a SequenceExecution Object and cares about the initialization.
     * 
     * @param mapping
     *            the layout mapping
     * @param lifelineEditPart
     *            the edit part of the current lifeline
     * @param executions
     *            the list of executions at the current lifeline
     * @param childEditPart
     *            the executions edit part
     * @param nodeType
     *            the type of the node
     * @param executionNode
     *            the KNode representation of the execution
     */
    private void createExecution(final LayoutMapping<IGraphicalEditPart> mapping,
            final ShapeNodeEditPart lifelineEditPart, final List<SequenceExecution> executions,
            final ShapeNodeEditPart childEditPart, final String nodeType, final KNode executionNode) {

        KShapeLayout executionLayout = executionNode.getData(KShapeLayout.class);
        IFigure executionFigure = childEditPart.getFigure();
        Rectangle executionBounds = getAbsoluteBounds(executionFigure);

        SequenceExecution execution = new SequenceExecution();

        if (nodeType.equals("3003") || nodeType.equals("3006")) {
            executionNode.getData(KShapeLayout.class).setProperty(PapyrusProperties.NODE_TYPE,
                    "Execution");
            execution.setType("Execution");
        } else if (nodeType.equals("3021")) {
            executionNode.getData(KShapeLayout.class).setProperty(PapyrusProperties.NODE_TYPE,
                    "Duration");
            execution.setType("Duration");
        } else if (nodeType.equals("3019")) {
            execution.setType("TimeConstraint");
        }

        // Walk through the connected messages
        for (Object targetConn : childEditPart.getTargetConnections()) {
            if (targetConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
                mapping.getProperty(CONNECTIONS).add(connectionEditPart);

                execution.addMessage(connectionEditPart);
            }
        }
        for (Object sourceConn : childEditPart.getSourceConnections()) {
            if (sourceConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) sourceConn;

                execution.addMessage(connectionEditPart);
            }
        }
        execution.setOrigin(executionNode);
        executions.add(execution);
        executionLayout.setProperty(PapyrusProperties.EXECUTION, execution);

        // Add messages to duration if their send/receive event is in the area of the
        // duration
        if (nodeType.equals("3021") || nodeType.equals("3019")) {
            // get position of messages and compare to duration
            int from = executionBounds.y();
            int to = executionBounds.y() + executionBounds.height();
            for (Object connObj : lifelineEditPart.getSourceConnections()) {
                if (connObj instanceof ConnectionEditPart) {
                    ConnectionEditPart conn = (ConnectionEditPart) connObj;
                    int point = conn.getConnectionFigure().getPoints().getFirstPoint().y;
                    if (point <= to + 2 && point >= from - 2) {
                        execution.addMessage(conn);
                    }
                }
            }
            for (Object connObj : lifelineEditPart.getTargetConnections()) {
                if (connObj instanceof ConnectionEditPart) {
                    ConnectionEditPart conn = (ConnectionEditPart) connObj;
                    int point = conn.getConnectionFigure().getPoints().getLastPoint().y;
                    if (point <= to + 2 && point >= from - 2) {
                        execution.addMessage(conn);
                    }
                }
            }
        }
    }

    /**
     * Finds the nearest message that is located below a given point or the lowermost message if
     * there is no message below the given point.
     * 
     * @param lifeline
     *            the lifeline, whose messages are searched
     * @param skipConnection
     *            the connection that is not considered in the search
     * @param yPos
     *            the point
     * @return the ConnectionEditPart of the searched message
     */
    private ConnectionEditPart findMessageBelowPoint(final ShapeNodeEditPart lifeline,
            final ConnectionEditPart skipConnection, final float yPos) {
        float minDiff = Float.MAX_VALUE;
        float low = 0;
        ConnectionEditPart next = null;
        ConnectionEditPart lowest = null;
        for (Object connection : lifeline.getSourceConnections()) {
            if (connection instanceof ConnectionEditPart) {
                ConnectionEditPart connect = (ConnectionEditPart) connection;
                if (connect == skipConnection) {
                    continue;
                }
                float connectionYPos = connect.getConnectionFigure().getPoints().getFirstPoint()
                        .y();

                if (connectionYPos >= yPos) {
                    // Message is below comment
                    float currentDiff = Math.abs(connectionYPos - yPos);
                    if (currentDiff < minDiff) {
                        minDiff = currentDiff;
                        next = connect;
                    }
                } else {
                    // Message is above comment
                    if (connectionYPos > low) {
                        low = connectionYPos;
                        lowest = connect;
                    }
                }
            }
        }
        for (Object connection : lifeline.getTargetConnections()) {
            if (connection instanceof ConnectionEditPart) {
                ConnectionEditPart connect = (ConnectionEditPart) connection;
                if (connect == skipConnection) {
                    continue;
                }
                float connectionYPos = connect.getConnectionFigure().getPoints().getLastPoint().y();

                if (connectionYPos > yPos) {
                    // Message is below comment
                    float currentDiff = Math.abs(connectionYPos - yPos);
                    if (currentDiff < minDiff) {
                        minDiff = currentDiff;
                        next = connect;
                    }
                } else {
                    // Message is above comment
                    if (connectionYPos > low) {
                        low = connectionYPos;
                        lowest = connect;
                    }
                }
            }
        }
        if (next != null) {
            return next;
        } else {
            return lowest;
        }
    }

    /**
     * Create a node label while building the layout graph.
     * 
     * @param mapping
     *            the layout mapping
     * @param labelEditPart
     *            the label edit part
     * @param nodeEditPart
     *            the parent node edit part
     * @param knode
     *            the layout node for which the label is set
     */
    private void createSequenceNodeLabel(final LayoutMapping<IGraphicalEditPart> mapping,
            final IGraphicalEditPart labelEditPart, final IGraphicalEditPart nodeEditPart,
            final KNode knode) {
        IFigure labelFigure = labelEditPart.getFigure();
        String text = null;
        Font font = null;
        if (labelFigure instanceof WrappingLabel) {
            WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
            text = wrappingLabel.getText();
            font = wrappingLabel.getFont();
        } else if (labelFigure instanceof Label) {
            Label label = (Label) labelFigure;
            text = label.getText();
            font = label.getFont();
        }
        if (text != null) {
            KLabel label = KimlUtil.createInitializedLabel(knode);
            label.setText(text);
            mapping.getGraphMap().put(label, labelEditPart);
            KShapeLayout labelLayout = label.getData(KShapeLayout.class);
            Rectangle labelBounds = getAbsoluteBounds(labelFigure);
            Rectangle nodeBounds = getAbsoluteBounds(nodeEditPart.getFigure());
            labelLayout.setXpos(labelBounds.x - nodeBounds.x);
            labelLayout.setYpos(labelBounds.y - nodeBounds.y);
            try {
                Dimension size = labelFigure.getPreferredSize();
                labelLayout.setSize(size.width, size.height);
                if (font != null && !font.isDisposed()) {
                    VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
                    staticConfig.setValue(LayoutOptions.FONT_NAME, label, LayoutContext.GRAPH_ELEM,
                            font.getFontData()[0].getName());
                    staticConfig.setValue(LayoutOptions.FONT_SIZE, label, LayoutContext.GRAPH_ELEM,
                            font.getFontData()[0].getHeight());
                }
            } catch (SWTException exception) {
                // ignore exception and leave the label size to (0, 0)
            }
            // the modification flag must initially be false
            ((KShapeLayoutImpl) labelLayout).resetModificationFlag();
        }
    }

    /**
     * Adds all target connections and connected connections to the list of connections that must be
     * processed later.
     * 
     * @param mapping
     *            the layout mapping
     * @param editPart
     *            an edit part
     */
    protected void addConnections(final LayoutMapping<IGraphicalEditPart> mapping,
            final IGraphicalEditPart editPart) {
        for (Object targetConn : editPart.getTargetConnections()) {
            if (targetConn instanceof ConnectionEditPart) {
                ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConn;
                mapping.getProperty(CONNECTIONS).add(connectionEditPart);
            }
        }
    }

    /**
     * Creates new edges and takes care of the labels for each connection identified in the
     * {@code buildLayoutGraphRecursively} method.
     * 
     * @param mapping
     *            the layout mapping
     */
    protected void processSequenceConnections(final LayoutMapping<IGraphicalEditPart> mapping) {
        reference2EdgeMap = new HashMap<EReference, KEdge>();
        for (ConnectionEditPart connection : mapping.getProperty(CONNECTIONS)) {
            boolean isOppositeEdge = false;
            EdgeLabelPlacement edgeLabelPlacement = EdgeLabelPlacement.UNDEFINED;
            KEdge edge;

            // check whether the edge belongs to an Ecore reference, which may have opposites
            EObject modelObject = connection.getNotationView().getElement();
            if (modelObject instanceof EReference) {
                EReference reference = (EReference) modelObject;
                edge = reference2EdgeMap.get(reference.getEOpposite());
                if (edge != null) {
                    edgeLabelPlacement = EdgeLabelPlacement.TAIL;
                    isOppositeEdge = true;
                } else {
                    edge = KimlUtil.createInitializedEdge();
                    reference2EdgeMap.put(reference, edge);
                }
            } else {
                edge = KimlUtil.createInitializedEdge();
            }

            if (connection.getModel() instanceof EdgeImpl) {
                EdgeImpl impl = (EdgeImpl) connection.getModel();
                VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
                staticConfig.setValue(PapyrusProperties.MESSAGE_TYPE, edge,
                        LayoutContext.GRAPH_ELEM, impl.getType());
            }

            BiMap<KGraphElement, IGraphicalEditPart> graphMap = mapping.getGraphMap();

            // find a proper source node and source port
            KGraphElement sourceElem;
            EditPart sourceObj = connection.getSource();
            sourceElem = graphMap.inverse().get(sourceObj);
            KNode sourceNode = null;
            KPort sourcePort = null;
            if (sourceElem instanceof KNode) {
                sourceNode = (KNode) sourceElem;
                String nodeType = sourceNode.getData(KShapeLayout.class).getProperty(
                        PapyrusProperties.NODE_TYPE);
                if (nodeType != null
                        && (nodeType.equals("Execution") || nodeType.equals("Duration"))) {
                    sourceNode = (KNode) graphMap.inverse().get(sourceObj.getParent());
                }
            } else if (sourceElem instanceof KPort) {
                sourcePort = (KPort) sourceElem;
                sourceNode = sourcePort.getNode();
            } else {
                continue;
            }

            // find a proper target node and target port
            KGraphElement targetElem;
            EditPart targetObj = connection.getTarget();
            targetElem = graphMap.inverse().get(targetObj);
            KNode targetNode = null;
            KPort targetPort = null;
            if (targetElem instanceof KNode) {
                targetNode = (KNode) targetElem;
                String nodeType = targetNode.getData(KShapeLayout.class).getProperty(
                        PapyrusProperties.NODE_TYPE);
                if (nodeType != null
                        && (nodeType.equals("Execution") || nodeType.equals("Duration"))) {
                    targetNode = (KNode) graphMap.inverse().get(targetObj.getParent());
                }
            } else if (targetElem instanceof KPort) {
                targetPort = (KPort) targetElem;
                targetNode = targetPort.getNode();
            } else if (targetElem instanceof KEdge) {
                // Handle edges that have edges as target
                KEdge targetEdge = (KEdge) targetElem;
                edge.setSource(sourceNode);
                // Since KEdges cannot have an edge as target, use its target node (this doesn't
                // matter for the results)

                edge.setTarget(targetEdge.getTarget());
                graphMap.put(edge, connection);
                continue;
            } else {
                continue;
            }

            // calculate offset for edge and label coordinates
            KVector offset = new KVector();
            if (KimlUtil.isDescendant(targetNode, sourceNode)) {
                KimlUtil.toAbsolute(offset, sourceNode);
            } else {
                KimlUtil.toAbsolute(offset, sourceNode.getParent());
            }

            if (!isOppositeEdge) {
                // set source and target
                edge.setSource(sourceNode);
                if (sourcePort != null) {
                    edge.setSourcePort(sourcePort);
                }
                edge.setTarget(targetNode);
                if (targetPort != null) {
                    edge.setTargetPort(targetPort);
                }

                if (!graphMap.containsValue(connection)) {
                    graphMap.put(edge, connection);
                }

                List<SequenceExecution> sourceprops = sourceNode.getData(KShapeLayout.class)
                        .getProperty(PapyrusProperties.EXECUTIONS);
                if (sourceprops != null) {
                    // replace ConnectionEditPart by its KEdge in execution
                    for (SequenceExecution sourceprop : sourceprops) {
                        if (sourceprop.getMessages().remove(connection)) {
                            sourceprop.addMessage(edge);
                        }
                    }
                }

                List<SequenceExecution> targetprops = targetNode.getData(KShapeLayout.class)
                        .getProperty(PapyrusProperties.EXECUTIONS);
                if (targetprops != null) {
                    for (SequenceExecution targetprop : targetprops) {
                        // replace ConnectionEditPart by its KEdge in execution
                        if (targetprop.getMessages().remove(connection)) {
                            targetprop.addMessage(edge);
                        }
                    }
                }

                // store the current coordinates of the edge
                KEdgeLayout edgeLayout = edge.getData(KEdgeLayout.class);
                setEdgeLayout(edgeLayout, connection, offset);
            }

            // process edge labels
            processSequenceEdgeLabels(mapping, connection, edge, edgeLabelPlacement, offset);
        }
    }

    /**
     * Stores the layout information of the given connection edit part into an edge layout.
     * 
     * @param edgeLayout
     *            an edge layout
     * @param connection
     *            a connection edit part
     * @param offset
     *            offset to be subtracted from coordinates
     */
    protected void setEdgeLayout(final KEdgeLayout edgeLayout, final ConnectionEditPart connection,
            final KVector offset) {
        Connection figure = connection.getConnectionFigure();
        PointList pointList = figure.getPoints();

        KPoint sourcePoint = edgeLayout.getSourcePoint();
        Point firstPoint = figure.getPoints().getFirstPoint();
        sourcePoint.setX(firstPoint.x - (float) offset.x);
        sourcePoint.setY(firstPoint.y - (float) offset.y);

        for (int i = 1; i < pointList.size() - 1; i++) {
            Point point = figure.getPoints().getPoint(i);
            KPoint kpoint = KLayoutDataFactory.eINSTANCE.createKPoint();
            kpoint.setX(point.x - (float) offset.x);
            kpoint.setY(point.y - (float) offset.y);
            edgeLayout.getBendPoints().add(kpoint);
        }
        KPoint targetPoint = edgeLayout.getTargetPoint();
        Point lastPoint = figure.getPoints().getLastPoint();
        targetPoint.setX(lastPoint.x - (float) offset.x);
        targetPoint.setY(lastPoint.y - (float) offset.y);

        // the modification flag must initially be false
        ((KEdgeLayoutImpl) edgeLayout).resetModificationFlag();
    }

    /**
     * Process the labels of an edge.
     * 
     * @param mapping
     *            the layout mapping
     * @param connection
     *            the connection edit part
     * @param edge
     *            the layout edge
     * @param placement
     *            predefined placement for all labels, or {@code UNDEFINED} if the placement shall
     *            be derived from the edit part
     * @param offset
     *            the offset for coordinates
     */
    private void processSequenceEdgeLabels(final LayoutMapping<IGraphicalEditPart> mapping,
            final ConnectionEditPart connection, final KEdge edge,
            final EdgeLabelPlacement placement, final KVector offset) {
        VolatileLayoutConfig staticConfig = mapping.getProperty(STATIC_CONFIG);
        /*
         * ars: source and target is exchanged when defining it in the gmfgen file. So if Emma sets
         * a label to be placed as target on a connection, then the label will show up next to the
         * source node in the diagram editor. So correct it here, very ugly.
         */
        for (Object obj : connection.getChildren()) {
            if (obj instanceof LabelEditPart) {
                LabelEditPart labelEditPart = (LabelEditPart) obj;
                IFigure labelFigure = labelEditPart.getFigure();

                // Check if the label is visible in the first place
                if (labelFigure != null && !labelFigure.isVisible()) {
                    continue;
                }

                Rectangle labelBounds = getAbsoluteBounds(labelFigure);
                String labelText = null;
                Dimension iconBounds = null;

                if (labelFigure instanceof WrappingLabel) {
                    WrappingLabel wrappingLabel = (WrappingLabel) labelFigure;
                    labelText = wrappingLabel.getText();
                    if (wrappingLabel.getIcon() != null) {
                        iconBounds = new Dimension();
                        iconBounds.width = wrappingLabel.getIcon().getBounds().width
                                + wrappingLabel.getIconTextGap();
                        iconBounds.height = wrappingLabel.getIcon().getBounds().height;
                        labelText = "O " + labelText;
                    }
                } else if (labelFigure instanceof Label) {
                    Label label = (Label) labelFigure;
                    labelText = label.getText();
                    if (label.getIcon() != null) {
                        iconBounds = label.getIconBounds().getSize();
                        iconBounds.width += label.getIconTextGap();
                        labelText = "O " + labelText;
                    }
                }

                if (labelText != null && labelText.length() > 0) {
                    KLabel label = KimlUtil.createInitializedLabel(edge);
                    KShapeLayout labelLayout = label.getData(KShapeLayout.class);
                    if (placement == EdgeLabelPlacement.UNDEFINED) {
                        switch (labelEditPart.getKeyPoint()) {
                        case ConnectionLocator.SOURCE:
                            staticConfig.setValue(LayoutOptions.EDGE_LABEL_PLACEMENT, label,
                                    LayoutContext.GRAPH_ELEM, EdgeLabelPlacement.HEAD);
                            break;
                        case ConnectionLocator.MIDDLE:
                            staticConfig.setValue(LayoutOptions.EDGE_LABEL_PLACEMENT, label,
                                    LayoutContext.GRAPH_ELEM, EdgeLabelPlacement.CENTER);
                            break;
                        case ConnectionLocator.TARGET:
                            staticConfig.setValue(LayoutOptions.EDGE_LABEL_PLACEMENT, label,
                                    LayoutContext.GRAPH_ELEM, EdgeLabelPlacement.TAIL);
                            break;
                        }
                    } else {
                        labelLayout.setProperty(LayoutOptions.EDGE_LABEL_PLACEMENT, placement);
                    }
                    Font font = labelFigure.getFont();
                    if (font != null && !font.isDisposed()) {
                        staticConfig.setValue(LayoutOptions.FONT_NAME, label,
                                LayoutContext.GRAPH_ELEM, font.getFontData()[0].getName());
                        staticConfig.setValue(LayoutOptions.FONT_SIZE, label,
                                LayoutContext.GRAPH_ELEM, font.getFontData()[0].getHeight());
                    }
                    labelLayout.setXpos(labelBounds.x - (float) offset.x);
                    labelLayout.setYpos(labelBounds.y - (float) offset.y);
                    if (iconBounds != null) {
                        labelLayout.setWidth(labelBounds.width + iconBounds.width);
                    } else {
                        labelLayout.setWidth(labelBounds.width);
                    }
                    labelLayout.setHeight(labelBounds.height);
                    ((KShapeLayoutImpl) labelLayout).resetModificationFlag();
                    label.setText(labelText);
                    mapping.getGraphMap().put(label, labelEditPart);
                } else {
                    // add the label to the mapping anyway so it is reset to its reference location
                    KLabel label = KGraphFactory.eINSTANCE.createKLabel();
                    KShapeLayout labelLayout = KLayoutDataFactory.eINSTANCE.createKShapeLayout();
                    label.getData().add(labelLayout);
                    mapping.getGraphMap().put(label, labelEditPart);
                }
            }
        }
    }
}
