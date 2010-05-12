package de.cau.cs.kieler.graphs.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.graphs.custom.NodeLayout;
import de.cau.cs.kieler.graphs.diagram.edit.policies.Node4CanonicalEditPolicy;
import de.cau.cs.kieler.graphs.diagram.edit.policies.Node4ItemSemanticEditPolicy;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.graphs.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class Node4EditPart extends AbstractBorderedShapeEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3001;

    /**
     * @generated
     */
    protected IFigure contentPane;

    /**
     * @generated
     */
    protected IFigure primaryShape;

    /**
     * @generated
     */
    public Node4EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        installEditPolicy(EditPolicyRoles.CREATION_ROLE,
                new CreationEditPolicy());
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
                new Node4ItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
                new DragDropEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
                new Node4CanonicalEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * @generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        LayoutEditPolicy lep = new LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(EditPart child) {
                View childView = (View) child.getModel();
                switch (GraphsVisualIDRegistry.getVisualID(childView)) {
                case PortEditPart.VISUAL_ID:
                    return new BorderItemSelectionEditPolicy();
                }
                EditPolicy result = child
                        .getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }
        };
        return lep;
    }

    /**
     * @generated
     */
    protected IFigure createNodeShape() {
        NodeFigure figure = new NodeFigure();
        return primaryShape = figure;
    }

    /**
     * @generated
     */
    public NodeFigure getPrimaryShape() {
        return (NodeFigure) primaryShape;
    }

    /**
     * @generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof NodeNodeLabel2EditPart) {
            ((NodeNodeLabel2EditPart) childEditPart).setLabel(getPrimaryShape()
                    .getFigureNodeLabelFigure());
            return true;
        }
        if (childEditPart instanceof PortEditPart) {
            BorderItemLocator locator = new BorderItemLocator(getMainFigure(),
                    PositionConstants.NONE);
            getBorderedFigure().getBorderItemContainer().add(
                    ((PortEditPart) childEditPart).getFigure(), locator);
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean removeFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof NodeNodeLabel2EditPart) {
            return true;
        }
        if (childEditPart instanceof PortEditPart) {
            getBorderedFigure().getBorderItemContainer().remove(
                    ((PortEditPart) childEditPart).getFigure());
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * @generated
     */
    protected void removeChildVisual(EditPart childEditPart) {
        if (removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * @generated
     */
    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        if (editPart instanceof IBorderItemEditPart) {
            return getBorderedFigure().getBorderItemContainer();
        }
        return getContentPane();
    }

    /**
     * @generated
     */
    protected org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
        return result;
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated
     */
    protected org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure createMainFigure() {
        org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane.
     * Respects layout one may have set for generated figure.
     * @param nodeShape instance of generated figure class
     * @generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
        if (nodeShape.getLayoutManager() == null) {
            ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(5);
            nodeShape.setLayoutManager(layout);
        }
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * @generated
     */
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * @generated
     */
    protected void setForegroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setForegroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setBackgroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setBackgroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setLineWidth(int width) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineWidth(width);
        }
    }

    /**
     * @generated
     */
    protected void setLineType(int style) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineStyle(style);
        }
    }

    /**
     * @generated
     */
    public EditPart getPrimaryChildEditPart() {
        return getChildBySemanticHint(GraphsVisualIDRegistry
                .getType(NodeNodeLabel2EditPart.VISUAL_ID));
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource() {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        types.add(GraphsElementTypes.Edge_4001);
        types.add(GraphsElementTypes.Edge_4002);
        types.add(GraphsElementTypes.Edge_4007);
        types.add(GraphsElementTypes.Edge_4008);
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(
            IGraphicalEditPart targetEditPart) {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        if (targetEditPart instanceof Node2EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof Node3EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof de.cau.cs.kieler.graphs.diagram.edit.parts.Node4EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof Node5EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof Node2EditPart) {
            types.add(GraphsElementTypes.Edge_4002);
        }
        if (targetEditPart instanceof Node3EditPart) {
            types.add(GraphsElementTypes.Edge_4002);
        }
        if (targetEditPart instanceof de.cau.cs.kieler.graphs.diagram.edit.parts.Node4EditPart) {
            types.add(GraphsElementTypes.Edge_4002);
        }
        if (targetEditPart instanceof Node5EditPart) {
            types.add(GraphsElementTypes.Edge_4002);
        }
        if (targetEditPart instanceof PortEditPart) {
            types.add(GraphsElementTypes.Edge_4007);
        }
        if (targetEditPart instanceof PortEditPart) {
            types.add(GraphsElementTypes.Edge_4008);
        }
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(
            IElementType relationshipType) {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_2001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_2002);
        }
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_3001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_3003);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_2001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_2002);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_3001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_3003);
        }
        if (relationshipType == GraphsElementTypes.Edge_4007) {
            types.add(GraphsElementTypes.Port_3002);
        }
        if (relationshipType == GraphsElementTypes.Edge_4008) {
            types.add(GraphsElementTypes.Port_3002);
        }
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        types.add(GraphsElementTypes.Edge_4001);
        types.add(GraphsElementTypes.Edge_4002);
        types.add(GraphsElementTypes.Edge_4005);
        types.add(GraphsElementTypes.Edge_4006);
        return types;
    }

    /**
     * @generated
     */
    public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(
            IElementType relationshipType) {
        List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_2001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_2002);
        }
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_3001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_3003);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_2001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_2002);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_3001);
        }
        if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_3003);
        }
        if (relationshipType == GraphsElementTypes.Edge_4005) {
            types.add(GraphsElementTypes.Port_3002);
        }
        if (relationshipType == GraphsElementTypes.Edge_4006) {
            types.add(GraphsElementTypes.Port_3002);
        }
        return types;
    }

    /**
     * @generated
     */
    public class NodeFigure extends RoundedRectangle {

        /**
         * @generated
         */
        private WrappingLabel fFigureNodeLabelFigure;

        /**
         * @generated
         */
        public NodeFigure() {

            NodeLayout layoutThis = new NodeLayout();

            this.setLayoutManager(layoutThis);

            this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(16),
                    getMapMode().DPtoLP(16)));
            this.setLineWidth(1);
            this.setForegroundColor(THIS_FORE);
            this.setBackgroundColor(THIS_BACK);

            this.setBorder(new MarginBorder(getMapMode().DPtoLP(5),
                    getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
                    getMapMode().DPtoLP(5)));
            createContents();
        }

        /**
         * @generated
         */
        private void createContents() {

            fFigureNodeLabelFigure = new WrappingLabel();
            fFigureNodeLabelFigure.setText("");

            this.add(fFigureNodeLabelFigure);

        }

        /**
         * @generated
         */
        private boolean myUseLocalCoordinates = false;

        /**
         * @generated
         */
        protected boolean useLocalCoordinates() {
            return myUseLocalCoordinates;
        }

        /**
         * @generated
         */
        protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
            myUseLocalCoordinates = useLocalCoordinates;
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureNodeLabelFigure() {
            return fFigureNodeLabelFigure;
        }

    }

    /**
     * @generated
     */
    static final Color THIS_FORE = new Color(null, 0, 20, 0);

    /**
     * @generated
     */
    static final Color THIS_BACK = new Color(null, 80, 230, 154);

}
