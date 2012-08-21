package de.cau.cs.kieler.keg.diagram.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.BorderedBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.graphics.Color;

import de.cau.cs.kieler.core.model.gmf.figures.layout.LabelLocator;
import de.cau.cs.kieler.keg.custom.KEGPort;
import de.cau.cs.kieler.keg.diagram.edit.policies.PortItemSemanticEditPolicy;
import de.cau.cs.kieler.keg.diagram.part.GraphsDiagramEditorPlugin;
import de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry;
import de.cau.cs.kieler.keg.diagram.providers.GraphsElementTypes;

/**
 * @generated
 */
public class PortEditPart extends BorderedBorderItemEditPart implements KEGPort

{

    /**
     * @generated
     */
    public static final int VISUAL_ID = 3002;

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
    public PortEditPart(View view) {
        super(view);
    }

    /**
     * @generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE, getPrimaryDragEditPolicy());
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new PortItemSemanticEditPolicy());
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
                switch (GraphsVisualIDRegistry.getVisualID(childView)) {
                case PortPortLabelEditPart.VISUAL_ID:
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
        primaryShape = new PortFigure();
        return primaryShape;
    }

    /**
     * @generated
     */
    public PortFigure getPrimaryShape() {
        return (PortFigure) primaryShape;
    }

    /**
     * @generated
     */
    protected void addBorderItem(IFigure borderItemContainer, IBorderItemEditPart borderItemEditPart) {
        if (borderItemEditPart instanceof PortPortLabelEditPart) {

            LabelLocator locator = new LabelLocator(getMainFigure(), PositionConstants.SOUTH);
            locator.setPortBehavior(true);
            locator.setBorderItemOffset(new Dimension(-2, -2));
            borderItemContainer.add(borderItemEditPart.getFigure(), locator);
        } else {
            borderItemContainer.add(borderItemEditPart.getFigure(), new BorderItemLocator(
                    getMainFigure()));
        }

    }

    /**
     * @generated
     */
    protected NodeFigure createNodePlate() {
        DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(8, 8);

        //FIXME: workaround for #154536
        result.getBounds().setSize(result.getPreferredSize());
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
     * Body of this method does not depend on settings in generation model so you may safely remove
     * <i>generated</i> tag and modify it.
     * 
     * @generated
     */
    protected NodeFigure createMainFigure() {
        NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new StackLayout());
        IFigure shape = createNodeShape();
        // set color
        org.eclipse.swt.graphics.RGB rgb = PreferenceConverter.getColor(GraphsDiagramEditorPlugin
                .getInstance().getPreferenceStore(), IPreferenceConstants.PREF_LINE_COLOR);
        Color fgColor = new Color(null, rgb);
        rgb = PreferenceConverter.getColor(GraphsDiagramEditorPlugin.getInstance()
                .getPreferenceStore(), IPreferenceConstants.PREF_FILL_COLOR);
        Color bgColor = new Color(null, rgb);
        primaryShape.setForegroundColor(fgColor);
        primaryShape.setBackgroundColor(bgColor);

        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane. Respects layout one may have set
     * for generated figure.
     * 
     * @param nodeShape
     *            instance of generated figure class
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
        return getChildBySemanticHint(GraphsVisualIDRegistry
                .getType(PortPortLabelEditPart.VISUAL_ID));
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnSource() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(4);
        types.add(GraphsElementTypes.Edge_4003);
        types.add(GraphsElementTypes.Edge_4004);
        types.add(GraphsElementTypes.Edge_4005);
        types.add(GraphsElementTypes.Edge_4006);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnSourceAndTarget(IGraphicalEditPart targetEditPart) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (targetEditPart instanceof de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart) {
            types.add(GraphsElementTypes.Edge_4003);
        }
        if (targetEditPart instanceof de.cau.cs.kieler.keg.diagram.edit.parts.PortEditPart) {
            types.add(GraphsElementTypes.Edge_4004);
        }
        if (targetEditPart instanceof Node2EditPart) {
            types.add(GraphsElementTypes.Edge_4005);
        }
        if (targetEditPart instanceof Node3EditPart) {
            types.add(GraphsElementTypes.Edge_4005);
        }
        if (targetEditPart instanceof Node4EditPart) {
            types.add(GraphsElementTypes.Edge_4005);
        }
        if (targetEditPart instanceof Node5EditPart) {
            types.add(GraphsElementTypes.Edge_4005);
        }
        if (targetEditPart instanceof Node2EditPart) {
            types.add(GraphsElementTypes.Edge_4006);
        }
        if (targetEditPart instanceof Node3EditPart) {
            types.add(GraphsElementTypes.Edge_4006);
        }
        if (targetEditPart instanceof Node4EditPart) {
            types.add(GraphsElementTypes.Edge_4006);
        }
        if (targetEditPart instanceof Node5EditPart) {
            types.add(GraphsElementTypes.Edge_4006);
        }
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == GraphsElementTypes.Edge_4003) {
            types.add(GraphsElementTypes.Port_3002);
        } else if (relationshipType == GraphsElementTypes.Edge_4004) {
            types.add(GraphsElementTypes.Port_3002);
        } else if (relationshipType == GraphsElementTypes.Edge_4005) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        } else if (relationshipType == GraphsElementTypes.Edge_4006) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        }
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMARelTypesOnTarget() {
        ArrayList<IElementType> types = new ArrayList<IElementType>(4);
        types.add(GraphsElementTypes.Edge_4003);
        types.add(GraphsElementTypes.Edge_4004);
        types.add(GraphsElementTypes.Edge_4007);
        types.add(GraphsElementTypes.Edge_4008);
        return types;
    }

    /**
     * @generated
     */
    public List<IElementType> getMATypesForSource(IElementType relationshipType) {
        LinkedList<IElementType> types = new LinkedList<IElementType>();
        if (relationshipType == GraphsElementTypes.Edge_4003) {
            types.add(GraphsElementTypes.Port_3002);
        } else if (relationshipType == GraphsElementTypes.Edge_4004) {
            types.add(GraphsElementTypes.Port_3002);
        } else if (relationshipType == GraphsElementTypes.Edge_4007) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        } else if (relationshipType == GraphsElementTypes.Edge_4008) {
            types.add(GraphsElementTypes.Node_2001);
            types.add(GraphsElementTypes.Node_2002);
            types.add(GraphsElementTypes.Node_3001);
            types.add(GraphsElementTypes.Node_3003);
        }
        return types;
    }

    /**
     * @generated
     */
    public class PortFigure extends RectangleFigure {

        public PortFigure() {
            this.setPreferredSize(new Dimension(getMapMode().DPtoLP(8), getMapMode().DPtoLP(8)));
        }

    }

}
