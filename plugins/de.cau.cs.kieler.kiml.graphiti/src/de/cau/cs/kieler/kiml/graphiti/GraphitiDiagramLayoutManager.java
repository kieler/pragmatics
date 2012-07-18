/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.graphiti;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementEditPart;
import org.eclipse.ui.IWorkbenchPart;

import de.cau.cs.kieler.core.kgraph.KGraphElement;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.kgraph.KPort;
import de.cau.cs.kieler.kiml.LayoutContext;
import de.cau.cs.kieler.kiml.config.VolatileLayoutConfig;
import de.cau.cs.kieler.kiml.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.klayoutdata.impl.KShapeLayoutImpl;
import de.cau.cs.kieler.kiml.options.LayoutOptions;
import de.cau.cs.kieler.kiml.ui.diagram.LayoutMapping;
import de.cau.cs.kieler.kiml.util.KimlUtil;

/**
 * Generic layout manager implementation for Graphiti diagrams.
 * 
 * @author atr
 * @author soh
 * @author msp
 */
@SuppressWarnings("restriction")
public class GraphitiDiagramLayoutManager extends GefDiagramLayoutManager<PictogramElement> {

    /**
     * {@inheritDoc}
     */
    public boolean supports(final Object object) {
        return object instanceof DiagramEditor || object instanceof IPictogramElementEditPart
                || object instanceof PictogramElement;
    }
    
    /** the cached layout configuration for Graphiti. */
    private GraphitiLayoutConfig layoutConfig = new GraphitiLayoutConfig();

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Object getAdapter(final Object object, final Class adapterType) {
        if (adapterType.isAssignableFrom(GraphitiLayoutConfig.class)) {
            return layoutConfig;
        } else if (adapterType.isAssignableFrom(IPictogramElementEditPart.class)) {
            if (object instanceof IPictogramElementEditPart) {
                return object;
            } else if (object instanceof DiagramEditor) {
                return ((DiagramEditor) object).getGraphicalViewer().getContents();
            }
        } else if (adapterType.isAssignableFrom(EObject.class)) {
            if (object instanceof IPictogramElementEditPart) {
                PictogramElement pe = ((IPictogramElementEditPart) object).getPictogramElement();
                if (pe.getLink() != null) {
                    List<EObject> businessObjects = pe.getLink().getBusinessObjects();
                    if (!businessObjects.isEmpty()) {
                        return businessObjects.get(0);
                    }
                }
            } else if (object instanceof PictogramElement) {
                PictogramElement pe = (PictogramElement) object;
                if (pe.getLink() != null) {
                    List<EObject> businessObjects = pe.getLink().getBusinessObjects();
                    if (!businessObjects.isEmpty()) {
                        return businessObjects.get(0);
                    }
                }
            }
        } else if (adapterType.isAssignableFrom(PictogramElement.class)) {
            if (object instanceof PictogramElement) {
                return object;
            } else if (object instanceof IPictogramElementEditPart) {
                return ((IPictogramElementEditPart) object).getPictogramElement();
            } else if (object instanceof DiagramEditor) {
                EditPart contents = ((DiagramEditor) object).getGraphicalViewer().getContents();
                if (contents instanceof IPictogramElementEditPart) {
                    return ((IPictogramElementEditPart) contents).getPictogramElement();
                }
            }
        } else if (adapterType.isAssignableFrom(TransactionalEditingDomain.class)) {
            if (object instanceof DiagramEditor) {
                return ((DiagramEditor) object).getEditingDomain();
            } else if (object instanceof IPictogramElementEditPart) {
                return ((IPictogramElementEditPart) object).getConfigurationProvider()
                        .getDiagramEditor().getEditingDomain();
            }
        }
        if (object instanceof IAdaptable) {
            return ((IAdaptable) object).getAdapter(adapterType);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { PictogramElement.class };
    }

    /**
     * {@inheritDoc}
     */
    public LayoutMapping<PictogramElement> buildLayoutGraph(final IWorkbenchPart workbenchPart,
            final Object diagramPart) {
        LayoutMapping<PictogramElement> mapping = new LayoutMapping<PictogramElement>(this);
        mapping.setProperty(KimlGraphitiUtil.CONNECTIONS, new LinkedList<Connection>());
        mapping.setProperty(KimlGraphitiUtil.STATIC_CONFIG, new VolatileLayoutConfig());

        if (workbenchPart instanceof DiagramEditor) {
            mapping.setProperty(KimlGraphitiUtil.DIAGRAM_EDITOR, (DiagramEditor) workbenchPart);
        }

        EditPart layoutRootPart = null;
        if (diagramPart instanceof IPictogramElementEditPart) {
            layoutRootPart = (EditPart) diagramPart;
        } else if (mapping.getProperty(KimlGraphitiUtil.DIAGRAM_EDITOR) != null) {
            layoutRootPart = mapping.getProperty(KimlGraphitiUtil.DIAGRAM_EDITOR)
                    .getGraphicalViewer().getContents();
        }
        if (!(layoutRootPart instanceof IPictogramElementEditPart)) {
            throw new UnsupportedOperationException(
                    "Not supported by this layout manager: Workbench part "
                    + workbenchPart + ", Edit part " + diagramPart);
        }
        PictogramElement element = ((IPictogramElementEditPart) layoutRootPart)
                .getPictogramElement();
        mapping.setParentElement(element);

        if (element instanceof Diagram) {
            KNode topNode = KimlUtil.createInitializedNode();
            KShapeLayout shapeLayout = topNode.getData(KShapeLayout.class);
            GraphicsAlgorithm ga = element.getGraphicsAlgorithm();
            shapeLayout.setPos(ga.getX(), ga.getY());
            shapeLayout.setSize(ga.getWidth(), ga.getHeight());
            mapping.getGraphMap().put(topNode, element);

            buildLayoutGraphRecursively(mapping, (Diagram) element, topNode);
            
            mapping.setLayoutGraph(topNode);
        } else if (element instanceof Shape) {
            mapping.setLayoutGraph(createNode(mapping, null, (Shape) element));
        }

        for (Connection entry : mapping.getProperty(KimlGraphitiUtil.CONNECTIONS)) {
            KimlGraphitiUtil.createEdge(mapping, entry);
        }
        
        // create layout configurators for Graphiti
        mapping.getLayoutConfigs().add(mapping.getProperty(KimlGraphitiUtil.STATIC_CONFIG));
        mapping.getLayoutConfigs().add(layoutConfig);

        return mapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void transferLayout(final LayoutMapping<PictogramElement> mapping) {
        DiagramEditor diagramEditor = mapping.getProperty(KimlGraphitiUtil.DIAGRAM_EDITOR);
        GraphitiLayoutCommand command = new GraphitiLayoutCommand(diagramEditor.getEditingDomain(),
                diagramEditor.getDiagramTypeProvider().getFeatureProvider());
        for (Entry<KGraphElement, PictogramElement> entry : mapping.getGraphMap().entrySet()) {
            command.add(entry.getKey(), entry.getValue());
        }
        mapping.setProperty(KimlGraphitiUtil.LAYOUT_COMMAND, command);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void applyLayout(final LayoutMapping<PictogramElement> mapping) {
        TransactionalEditingDomain editingDomain = mapping.getProperty(KimlGraphitiUtil.DIAGRAM_EDITOR)
                .getEditingDomain();
        editingDomain.getCommandStack().execute(mapping.getProperty(KimlGraphitiUtil.LAYOUT_COMMAND));
    }

    /** the fixed minimal size of shapes. */
    private static final float MIN_SIZE = 15.0f;
    
    /**
     * Recursively builds a layout graph by analyzing the children of the given current pictogram
     * Element.
     *
     * @param mapping the mapping of pictogram elements to graph elements
     * @param parentElement the currently analyzed element
     * @param parentNode the corresponding KNode
     */
    protected void buildLayoutGraphRecursively(final LayoutMapping<PictogramElement> mapping,
            final ContainerShape parentElement, final KNode parentNode) {
        for (Shape shape : parentElement.getChildren()) {
            // relevant shapes are those that can be connected
            if (!shape.getAnchors().isEmpty()) {
                createNode(mapping, parentNode, shape);
            }
        }
    }
    
    /**
     * Create a node for the layout graph.
     * 
     * @param mapping the mapping of pictogram elements to graph elements
     * @param parentNode the parent node
     * @param shape the shape for a new node
     * @return a new layout node
     */
    protected KNode createNode(final LayoutMapping<PictogramElement> mapping,
            final KNode parentNode, final Shape shape) {
        KNode childNode = KimlUtil.createInitializedNode();
        childNode.setParent(parentNode);
        VolatileLayoutConfig staticConfig = mapping.getProperty(KimlGraphitiUtil.STATIC_CONFIG);

        // set the node's layout
        KShapeLayout nodeLayout = childNode.getData(KShapeLayout.class);
        GraphicsAlgorithm nodeGa = shape.getGraphicsAlgorithm();
        KInsets nodeInsets = KimlGraphitiUtil.calcInsets(nodeGa);
        nodeLayout.setProperty(GraphitiLayoutCommand.INVIS_INSETS, nodeInsets);
        staticConfig.setValue(GraphitiLayoutCommand.INVIS_INSETS, childNode, LayoutContext.GRAPH_ELEM,
                nodeInsets);
        KInsets parentInsets = parentNode == null ? null : parentNode.getData(KShapeLayout.class)
                .getProperty(GraphitiLayoutCommand.INVIS_INSETS);
        if (parentInsets == null) {
            nodeLayout.setPos(nodeGa.getX() + nodeInsets.getLeft(),
                    nodeGa.getY() + nodeInsets.getTop());
        } else {
            nodeLayout.setPos(nodeGa.getX() + nodeInsets.getLeft() - parentInsets.getLeft(),
                    nodeGa.getY() + nodeInsets.getTop() - parentInsets.getTop());
        }
        nodeLayout.setSize(nodeGa.getWidth() - nodeInsets.getLeft() - nodeInsets.getRight(),
                nodeGa.getHeight() - nodeInsets.getTop() - nodeInsets.getBottom());
        // the modification flag must initially be false
        ((KShapeLayoutImpl) nodeLayout).resetModificationFlag();

        // FIXME find a way to specify the minimal size dynamically
        staticConfig.setValue(LayoutOptions.MIN_WIDTH, childNode, LayoutContext.GRAPH_ELEM, MIN_SIZE);
        staticConfig.setValue(LayoutOptions.MIN_HEIGHT, childNode, LayoutContext.GRAPH_ELEM, MIN_SIZE);

        mapping.getGraphMap().put(childNode, shape);

        if (shape instanceof ContainerShape) {
            // find a label for the container shape
            for (Shape child : ((ContainerShape) shape).getChildren()) {
                GraphicsAlgorithm childGa = child.getGraphicsAlgorithm();
                if (childGa instanceof AbstractText) {
                    KimlGraphitiUtil.createLabel(childNode, (AbstractText) childGa,
                            -nodeInsets.getLeft(), -nodeInsets.getTop());
                }
            }
            
            // process the children of the container shape
            buildLayoutGraphRecursively(mapping, (ContainerShape) shape, childNode);
        }

        for (Anchor anchor : shape.getAnchors()) {
            // box-relative anchors and fixed-position anchors are interpreted as ports
            if (anchor.getGraphicsAlgorithm() != null) {
                if (anchor instanceof BoxRelativeAnchor) {
                    createPort(mapping, childNode, (BoxRelativeAnchor) anchor);
                } else if (anchor instanceof FixPointAnchor) {
                    createPort(mapping, childNode, (FixPointAnchor) anchor);
                }
            }
            // gather all connections in the diagram
            mapping.getProperty(KimlGraphitiUtil.CONNECTIONS).addAll(anchor.getOutgoingConnections());
        }
        
        return childNode;
    }


    /**
     * Create a port for the layout graph using a box-relative anchor. The referenced graphics
     * algorithm of the anchor is assumed to be the same as the one returned by
     * {@link #findVisibleGa(GraphicsAlgorithm)}.
     * 
     * @param mapping the mapping of pictogram elements to graph elements
     * @param parentNode the parent node
     * @param bra the anchor
     * @return a new layout port
     */
    protected KPort createPort(final LayoutMapping<PictogramElement> mapping,
            final KNode parentNode, final BoxRelativeAnchor bra) {
        KPort port = KimlUtil.createInitializedPort();
        port.setNode(parentNode);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);

        GraphicsAlgorithm referencedGa = bra.getReferencedGraphicsAlgorithm();
        if (referencedGa == null) {
            return null;
        }
        mapping.getGraphMap().put(port, bra);

        double relWidth = bra.getRelativeWidth();
        double relHeight = bra.getRelativeHeight();

        double parentWidth = referencedGa.getWidth();
        double parentHeight = referencedGa.getHeight();
        float xPos = (float) (relWidth * parentWidth);
        float yPos = (float) (relHeight * parentHeight);
        
        GraphicsAlgorithm portGa = bra.getGraphicsAlgorithm(); 
        if (portGa != null) {
            xPos += portGa.getX();
            yPos += portGa.getY();
            portLayout.setSize(portGa.getWidth(), portGa.getHeight());
        }
        portLayout.setPos(xPos, yPos);
        // the modification flag must initially be false
        ((KShapeLayoutImpl) portLayout).resetModificationFlag();
        
        return port;
    }

    /**
     * Create a port for the layout graph using a fixed-position anchor.
     * 
     * @param mapping the mapping of pictogram elements to graph elements
     * @param parentNode the parent node
     * @param fpa the anchor
     * @return a new layout port
     */
    protected KPort createPort(final LayoutMapping<PictogramElement> mapping,
            final KNode parentNode, final FixPointAnchor fpa) {
        KPort port = KimlUtil.createInitializedPort();
        port.setNode(parentNode);
        KShapeLayout portLayout = port.getData(KShapeLayout.class);
        mapping.getGraphMap().put(port, fpa);
        
        float xPos = fpa.getLocation().getX();
        float yPos = fpa.getLocation().getY();

        GraphicsAlgorithm portGa = fpa.getGraphicsAlgorithm(); 
        if (portGa != null) {
            xPos += portGa.getX();
            yPos += portGa.getY();
            portLayout.setSize(portGa.getWidth(), portGa.getHeight());
        }
        portLayout.setPos(xPos, yPos);
        // the modification flag must initially be false
        ((KShapeLayoutImpl) portLayout).resetModificationFlag();
        
        return port;
    }
    
}
