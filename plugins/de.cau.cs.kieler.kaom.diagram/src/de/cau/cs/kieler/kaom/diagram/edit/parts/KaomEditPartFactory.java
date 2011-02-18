package de.cau.cs.kieler.kaom.diagram.edit.parts;

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

import de.cau.cs.kieler.kaom.diagram.part.KaomVisualIDRegistry;

/**
 * @generated
 */
public class KaomEditPartFactory implements EditPartFactory {

    /**
     * @generated
     */
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            View view = (View) model;
            switch (KaomVisualIDRegistry.getVisualID(view)) {

            case EntityEditPart.VISUAL_ID:
                return new EntityEditPart(view);

            case Entity2EditPart.VISUAL_ID:
                return new Entity2EditPart(view);

            case EntityNameEditPart.VISUAL_ID:
                return new EntityNameEditPart(view);

            case RelationEditPart.VISUAL_ID:
                return new RelationEditPart(view);

            case RelationNameEditPart.VISUAL_ID:
                return new RelationNameEditPart(view);

            case PortEditPart.VISUAL_ID:
                return new PortEditPart(view);

            case PortNameEditPart.VISUAL_ID:
                return new PortNameEditPart(view);

            case Entity3EditPart.VISUAL_ID:
                return new Entity3EditPart(view);

            case EntityName2EditPart.VISUAL_ID:
                return new EntityName2EditPart(view);

            case Relation2EditPart.VISUAL_ID:
                return new Relation2EditPart(view);

            case RelationName2EditPart.VISUAL_ID:
                return new RelationName2EditPart(view);

            case EntityEntityCompartmentEditPart.VISUAL_ID:
                return new EntityEntityCompartmentEditPart(view);

            case EntityEntityCompartment2EditPart.VISUAL_ID:
                return new EntityEntityCompartment2EditPart(view);

            case LinkEditPart.VISUAL_ID:
                return new LinkEditPart(view);

            case LinkNameEditPart.VISUAL_ID:
                return new LinkNameEditPart(view);

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
    public static CellEditorLocator getTextCellEditorLocator(ITextAwareEditPart source) {
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
            if (!text.getFont().isDisposed()) {
                if (getWrapLabel().isTextWrapOn() && getWrapLabel().getText().length() > 0) {
                    rect.setSize(new Dimension(text.computeSize(rect.width, SWT.DEFAULT)));
                } else {
                    int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
                    rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(
                            avr * 2, 0));
                }
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
            if (!text.getFont().isDisposed()) {
                int avr = FigureUtilities.getFontMetrics(text.getFont()).getAverageCharWidth();
                rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT, SWT.DEFAULT)).expand(
                        avr * 2, 0));
            }
            if (!rect.equals(new Rectangle(text.getBounds()))) {
                text.setBounds(rect.x, rect.y, rect.width, rect.height);
            }
        }
    }
}
