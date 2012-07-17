package de.cau.cs.kieler.keg.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.AbstractBackground;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.gef.ui.internal.figures.OvalFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.keg.custom.KEGNode;
import de.cau.cs.kieler.keg.diagram.edit.policies.Node3ItemSemanticEditPolicy;
import de.cau.cs.kieler.keg.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
@SuppressWarnings("restriction")
public class Node3EditPart extends ShapeNodeEditPart

implements KEGNode

{

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
    protected IFigure primaryShape;

    /**
     * @generated
     */
    public Node3EditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new Node3ItemSemanticEditPolicy());
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
        primaryShape = new HypernodeFigure();
        return primaryShape;
    }

    /**
     * @generated
     */
    public HypernodeFigure getPrimaryShape() {
        return (HypernodeFigure) primaryShape;
    }

    /**
     * @generated
     */
    protected NodeFigure createNodePlate() {
        OvalFigure result = new OvalFigure() {
            @Override
            public Dimension getPreferredSize(int wHint, int hHint) {
                return new Dimension(20, 20);
            }

            @Override
            public void paintFigure(Graphics graphics) {
                // this code is copied from the base classes because the
                // implementation of the OvalFigure had to be skipped
                if (isOpaque() && getBorder() != null) {
                    Rectangle tempRect = new Rectangle(getBounds());
                    tempRect.shrink(getBorder().getInsets(this));
                    graphics.fillRectangle(tempRect);
                    return;
                }
                if (isOpaque())
                    graphics.fillRectangle(getBounds());
                if (getBorder() instanceof AbstractBackground)
                    ((AbstractBackground) getBorder()).paintBackground(this, graphics, NO_INSETS);
            }
        };

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
    protected NodeFigure createNodeFigure() {
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
    public List<IElementType> getMARelTypesOnSource() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(4);
        types.add(GraphsElementTypes.Edge_4001);
        types.add(GraphsElementTypes.Edge_4002);
        types.add(GraphsElementTypes.Edge_4007);
        types.add(GraphsElementTypes.Edge_4008);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (targetEditPart instanceof Node2EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof de.cau.cs.kieler.keg.diagram.edit.parts.Node3EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof Node4EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof Node5EditPart) {
            types.add(GraphsElementTypes.Edge_4001);
        }
        if (targetEditPart instanceof Node2EditPart) {
            types.add(GraphsElementTypes.Edge_4002);
        }
        if (targetEditPart instanceof de.cau.cs.kieler.keg.diagram.edit.parts.Node3EditPart) {
            types.add(GraphsElementTypes.Edge_4002);
        }
        if (targetEditPart instanceof Node4EditPart) {
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
    public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        } else if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        } else if (relationshipType == GraphsElementTypes.Edge_4007) {
            types.add(GraphsElementTypes.Port_3002);
        } else if (relationshipType == GraphsElementTypes.Edge_4008) {
            types.add(GraphsElementTypes.Port_3002);
        }
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnTarget() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(4);
        types.add(GraphsElementTypes.Edge_4001);
        types.add(GraphsElementTypes.Edge_4002);
        types.add(GraphsElementTypes.Edge_4005);
        types.add(GraphsElementTypes.Edge_4006);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMATypesForSource(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == GraphsElementTypes.Edge_4001) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        } else if (relationshipType == GraphsElementTypes.Edge_4002) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        } else if (relationshipType == GraphsElementTypes.Edge_4005) {
            types.add(GraphsElementTypes.Port_3002);
        } else if (relationshipType == GraphsElementTypes.Edge_4006) {
            types.add(GraphsElementTypes.Port_3002);
        }
        return types;
    }

    /**
     * @generated
     */
    public class HypernodeFigure extends Ellipse {

        /**
         * @generated
         */
        public HypernodeFigure() {
            this.setBackgroundColor(THIS_BACK);
            this.setPreferredSize(new Dimension(getMapMode().DPtoLP(20), getMapMode().DPtoLP(20)));
        }

    }

    /**
     * @generated
     */
    static final Color THIS_BACK = new Color(null, 0, 60, 0);

}
