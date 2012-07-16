package de.cau.cs.kieler.kaom.diagram.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.core.model.gmf.figures.layout.LabelLocator;
import de.cau.cs.kieler.kaom.custom.figures.DiamondFigure;
import de.cau.cs.kieler.kaom.diagram.edit.policies.RelationItemSemanticEditPolicy;
import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;
import de.cau.cs.kieler.kaom.diagram.providers.KaomElementTypes;
import de.cau.cs.kieler.karma.AdvancedRenderingBorderedShapeEditPart;

/**
 * @generated
 */
public class RelationEditPart extends AdvancedRenderingBorderedShapeEditPart {

    /**
     * @generated
     */
    public static final int VISUAL_ID = 2002;

    /**
     * @generated
     */
    protected IFigure contentPane;

    /**
     * @generated
     */
    public RelationEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new RelationItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * @generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(EditPart child) {
                View childView = (View) child.getModel();
                switch (KaomVisualIDRegistry.getVisualID(childView)) {
                case RelationNameEditPart.VISUAL_ID:
                    return new BorderItemSelectionEditPolicy() {

                        protected List<?> createSelectionHandles() {
                            MoveHandle mh = new MoveHandle((GraphicalEditPart) getHost());
                            mh.setBorder(null);
                            return Collections.singletonList(mh);
                        }
                    };
                }
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
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
        return primaryShape = new RelationFigure();
    }

    /**
     * @generated
     */
    public RelationFigure getPrimaryShape() {
        return (RelationFigure) primaryShape;
    }

    /**
     * @generated
     */
    protected void addBorderItem(IFigure borderItemContainer, IBorderItemEditPart borderItemEditPart) {
        if (borderItemEditPart instanceof RelationNameEditPart) {

            LabelLocator locator = new LabelLocator(getMainFigure(), PositionConstants.SOUTH);
            locator.setBorderItemOffset(new Dimension(-4, -4));
            borderItemContainer.add(borderItemEditPart.getFigure(), locator);
        } else {
            borderItemContainer.add(borderItemEditPart.getFigure(), new BorderItemLocator(
                    getMainFigure()) {
                @Override
                protected Point locateOnBorder(Point suggestedLocation, int suggestedSide,
                        int circuitCount, IFigure borderItem) {
                    return locateOnParent(suggestedLocation, suggestedSide, borderItem);
                }
            });
        }

    }

    /**
     * @generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(10, 10);
        return result;
    }

    /**
     * @generated
     */
    public EditPolicy getPrimaryDragEditPolicy() {
        EditPolicy result = super.getPrimaryDragEditPolicy();
        if (result instanceof ResizableEditPolicy) {
            ResizableEditPolicy ep = (ResizableEditPolicy) result;
            ep.setResizeDirections(PositionConstants.NONE);
        }
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
    protected NodeFigure createMainFigure() {
        NodeFigure figure = createNodePlate();
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
        return getChildBySemanticHint(KaomVisualIDRegistry.getType(RelationNameEditPart.VISUAL_ID));
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnSource() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(KaomElementTypes.Link_4001);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (targetEditPart instanceof Entity2EditPart) {
            types.add(KaomElementTypes.Link_4001);
        }
        if (targetEditPart instanceof de.cau.cs.kieler.kaom.diagram.edit.parts.RelationEditPart) {
            types.add(KaomElementTypes.Link_4001);
        }
        if (targetEditPart instanceof PortEditPart) {
            types.add(KaomElementTypes.Link_4001);
        }
        if (targetEditPart instanceof Entity3EditPart) {
            types.add(KaomElementTypes.Link_4001);
        }
        if (targetEditPart instanceof Relation2EditPart) {
            types.add(KaomElementTypes.Link_4001);
        }
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == KaomElementTypes.Link_4001) {
            types.add(KaomElementTypes.Entity_2001);
            types.add(KaomElementTypes.Relation_2002);
            types.add(KaomElementTypes.Port_3001);
            types.add(KaomElementTypes.Entity_3002);
            types.add(KaomElementTypes.Relation_3003);
        }
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnTarget() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(1);
        types.add(KaomElementTypes.Link_4001);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMATypesForSource(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == KaomElementTypes.Link_4001) {
            types.add(KaomElementTypes.Entity_2001);
            types.add(KaomElementTypes.Relation_2002);
            types.add(KaomElementTypes.Port_3001);
            types.add(KaomElementTypes.Entity_3002);
            types.add(KaomElementTypes.Relation_3003);
        }
        return types;
    }

    /**
     * @generated
     */
    public class RelationFigure extends DiamondFigure {

        /**
         * @generated
         */
        private WrappingLabel fFigureRelationNameLabel;

        /**
         * @generated
         */
        public RelationFigure() {

            this.setForegroundColor(ColorConstants.black);
            this.setBackgroundColor(ColorConstants.black);
            this.setPreferredSize(new Dimension(getMapMode().DPtoLP(10), getMapMode().DPtoLP(10)));
        }

        /**
         * @generated
         */
        public WrappingLabel getFigureRelationNameLabel() {
            return fFigureRelationNameLabel;
        }

    }

}
