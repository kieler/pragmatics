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

			case GraphEditPart.VISUAL_ID:
				return new GraphEditPart(view);

			case NodeEditPart.VISUAL_ID:
				return new NodeEditPart(view);

			case NodeLabelEditPart.VISUAL_ID:
				return new NodeLabelEditPart(view);

			case CompoundNodeEditPart.VISUAL_ID:
				return new CompoundNodeEditPart(view);

			case CompoundNodeLabelEditPart.VISUAL_ID:
				return new CompoundNodeLabelEditPart(view);

			case Node2EditPart.VISUAL_ID:
				return new Node2EditPart(view);

			case NodeLabel2EditPart.VISUAL_ID:
				return new NodeLabel2EditPart(view);

			case CompoundNode2EditPart.VISUAL_ID:
				return new CompoundNode2EditPart(view);

			case CompoundNodeLabel2EditPart.VISUAL_ID:
				return new CompoundNodeLabel2EditPart(view);

			case CompoundNodeNodeCompartmentEditPart.VISUAL_ID:
				return new CompoundNodeNodeCompartmentEditPart(view);

			case CompoundNodeNodeCompartment2EditPart.VISUAL_ID:
				return new CompoundNodeNodeCompartment2EditPart(view);

			case EdgeEditPart.VISUAL_ID:
				return new EdgeEditPart(view);

			case EdgeLabelEditPart.VISUAL_ID:
				return new EdgeLabelEditPart(view);

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
