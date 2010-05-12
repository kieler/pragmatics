package de.cau.cs.kieler.graphs.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class GraphsEditPartFactory implements EditPartFactory {

    /**
     * @generated
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            View view = (View) model;
            switch (GraphsVisualIDRegistry.getVisualID(view)) {

            case NodeEditPart.VISUAL_ID:
                return new NodeEditPart(view);

            case Node2EditPart.VISUAL_ID:
                return new Node2EditPart(view);

            case NodeNodeLabelEditPart.VISUAL_ID:
                return new NodeNodeLabelEditPart(view);

            case Node3EditPart.VISUAL_ID:
                return new Node3EditPart(view);

            case Node4EditPart.VISUAL_ID:
                return new Node4EditPart(view);

            case NodeNodeLabel2EditPart.VISUAL_ID:
                return new NodeNodeLabel2EditPart(view);

            case PortEditPart.VISUAL_ID:
                return new PortEditPart(view);

            case PortPortLabelEditPart.VISUAL_ID:
                return new PortPortLabelEditPart(view);

            case Node5EditPart.VISUAL_ID:
                return new Node5EditPart(view);

            case NodeNodeCompartmentEditPart.VISUAL_ID:
                return new NodeNodeCompartmentEditPart(view);

            case NodeNodeCompartment2EditPart.VISUAL_ID:
                return new NodeNodeCompartment2EditPart(view);

            case EdgeEditPart.VISUAL_ID:
                return new EdgeEditPart(view);

            case EdgeHeadLabel1EditPart.VISUAL_ID:
                return new EdgeHeadLabel1EditPart(view);

            case EdgeHeadLabel2EditPart.VISUAL_ID:
                return new EdgeHeadLabel2EditPart(view);

            case EdgeMidLabelEditPart.VISUAL_ID:
                return new EdgeMidLabelEditPart(view);

            case EdgeTailLabel1EditPart.VISUAL_ID:
                return new EdgeTailLabel1EditPart(view);

            case EdgeTailLabel2EditPart.VISUAL_ID:
                return new EdgeTailLabel2EditPart(view);

            case Edge2EditPart.VISUAL_ID:
                return new Edge2EditPart(view);

            case EdgeHeadLabel12EditPart.VISUAL_ID:
                return new EdgeHeadLabel12EditPart(view);

            case EdgeHeadLabel22EditPart.VISUAL_ID:
                return new EdgeHeadLabel22EditPart(view);

            case EdgeMidLabel2EditPart.VISUAL_ID:
                return new EdgeMidLabel2EditPart(view);

            case EdgeTailLabel12EditPart.VISUAL_ID:
                return new EdgeTailLabel12EditPart(view);

            case EdgeTailLabel22EditPart.VISUAL_ID:
                return new EdgeTailLabel22EditPart(view);

            case Edge3EditPart.VISUAL_ID:
                return new Edge3EditPart(view);

            case EdgeHeadLabel13EditPart.VISUAL_ID:
                return new EdgeHeadLabel13EditPart(view);

            case EdgeHeadLabel23EditPart.VISUAL_ID:
                return new EdgeHeadLabel23EditPart(view);

            case EdgeMidLabel3EditPart.VISUAL_ID:
                return new EdgeMidLabel3EditPart(view);

            case EdgeTailLabel13EditPart.VISUAL_ID:
                return new EdgeTailLabel13EditPart(view);

            case EdgeTailLabel23EditPart.VISUAL_ID:
                return new EdgeTailLabel23EditPart(view);

            case Edge4EditPart.VISUAL_ID:
                return new Edge4EditPart(view);

            case EdgeHeadLabel14EditPart.VISUAL_ID:
                return new EdgeHeadLabel14EditPart(view);

            case EdgeHeadLabel24EditPart.VISUAL_ID:
                return new EdgeHeadLabel24EditPart(view);

            case EdgeMidLabel4EditPart.VISUAL_ID:
                return new EdgeMidLabel4EditPart(view);

            case EdgeTailLabel14EditPart.VISUAL_ID:
                return new EdgeTailLabel14EditPart(view);

            case EdgeTailLabel24EditPart.VISUAL_ID:
                return new EdgeTailLabel24EditPart(view);

            case Edge5EditPart.VISUAL_ID:
                return new Edge5EditPart(view);

            case EdgeHeadLabel15EditPart.VISUAL_ID:
                return new EdgeHeadLabel15EditPart(view);

            case EdgeHeadLabel25EditPart.VISUAL_ID:
                return new EdgeHeadLabel25EditPart(view);

            case EdgeMidLabel5EditPart.VISUAL_ID:
                return new EdgeMidLabel5EditPart(view);

            case EdgeTailLabel15EditPart.VISUAL_ID:
                return new EdgeTailLabel15EditPart(view);

            case EdgeTailLabel25EditPart.VISUAL_ID:
                return new EdgeTailLabel25EditPart(view);

            case Edge6EditPart.VISUAL_ID:
                return new Edge6EditPart(view);

            case EdgeHeadLabel16EditPart.VISUAL_ID:
                return new EdgeHeadLabel16EditPart(view);

            case EdgeHeadLabel26EditPart.VISUAL_ID:
                return new EdgeHeadLabel26EditPart(view);

            case EdgeMidLabel6EditPart.VISUAL_ID:
                return new EdgeMidLabel6EditPart(view);

            case EdgeTailLabel16EditPart.VISUAL_ID:
                return new EdgeTailLabel16EditPart(view);

            case EdgeTailLabel26EditPart.VISUAL_ID:
                return new EdgeTailLabel26EditPart(view);

            case Edge7EditPart.VISUAL_ID:
                return new Edge7EditPart(view);

            case EdgeHeadLabel17EditPart.VISUAL_ID:
                return new EdgeHeadLabel17EditPart(view);

            case EdgeHeadLabel27EditPart.VISUAL_ID:
                return new EdgeHeadLabel27EditPart(view);

            case EdgeMidLabel7EditPart.VISUAL_ID:
                return new EdgeMidLabel7EditPart(view);

            case EdgeTailLabel17EditPart.VISUAL_ID:
                return new EdgeTailLabel17EditPart(view);

            case EdgeTailLabel27EditPart.VISUAL_ID:
                return new EdgeTailLabel27EditPart(view);

            case Edge8EditPart.VISUAL_ID:
                return new Edge8EditPart(view);

            case EdgeHeadLabel18EditPart.VISUAL_ID:
                return new EdgeHeadLabel18EditPart(view);

            case EdgeHeadLabel28EditPart.VISUAL_ID:
                return new EdgeHeadLabel28EditPart(view);

            case EdgeMidLabel8EditPart.VISUAL_ID:
                return new EdgeMidLabel8EditPart(view);

            case EdgeTailLabel18EditPart.VISUAL_ID:
                return new EdgeTailLabel18EditPart(view);

            case EdgeTailLabel28EditPart.VISUAL_ID:
                return new EdgeTailLabel28EditPart(view);

            }
        }
        return createUnrecognizedEditPart(context, model);
    }

    /**
     * @generated
     */
    private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
        // Handle creation of unrecognized child node EditParts here
        return null;
    }

    /**
     * @generated
     */
    public static CellEditorLocator getTextCellEditorLocator(
            ITextAwareEditPart source) {
        if (source.getFigure() instanceof WrappingLabel)
            return new TextCellEditorLocator((WrappingLabel) source.getFigure());
        else {
            return new LabelCellEditorLocator((Label) source.getFigure());
        }
    }

    /**
     * @generated
     */
    static private class TextCellEditorLocator implements CellEditorLocator {

        /**
         * @generated
         */
        private WrappingLabel wrapLabel;

        /**
         * @generated
         */
        public TextCellEditorLocator(WrappingLabel wrapLabel) {
            this.wrapLabel = wrapLabel;
        }

        /**
         * @generated
         */
        public WrappingLabel getWrapLabel() {
            return wrapLabel;
        }

        /**
         * @generated
         */
        public void relocate(CellEditor celleditor) {
            Text text = (Text) celleditor.getControl();
            Rectangle rect = getWrapLabel().getTextBounds().getCopy();
            getWrapLabel().translateToAbsolute(rect);
            if (getWrapLabel().isTextWrapOn()
                    && getWrapLabel().getText().length() > 0) {
                rect.setSize(new Dimension(text.computeSize(rect.width,
                        SWT.DEFAULT)));
            } else {
                int avr = FigureUtilities.getFontMetrics(text.getFont())
                        .getAverageCharWidth();
                rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
                        SWT.DEFAULT)).expand(avr * 2, 0));
            }
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }

    /**
     * @generated
     */
    private static class LabelCellEditorLocator implements CellEditorLocator {

        /**
         * @generated
         */
        private Label label;

        /**
         * @generated
         */
        public LabelCellEditorLocator(Label label) {
            this.label = label;
        }

        /**
         * @generated
         */
        public Label getLabel() {
            return label;
        }

        /**
         * @generated
         */
        public void relocate(CellEditor celleditor) {
            Text text = (Text) celleditor.getControl();
            Rectangle rect = getLabel().getTextBounds().getCopy();
            getLabel().translateToAbsolute(rect);
            int avr = FigureUtilities.getFontMetrics(text.getFont())
                    .getAverageCharWidth();
            rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
                    SWT.DEFAULT)).expand(avr * 2, 0));
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
}
