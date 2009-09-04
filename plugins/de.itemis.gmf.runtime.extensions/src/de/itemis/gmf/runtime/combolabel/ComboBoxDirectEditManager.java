package de.itemis.gmf.runtime.combolabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.label.ILabelDelegate;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.jface.resource.DeviceResourceException;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Scrollable;

import de.itemis.gmf.runtime.extensions.Activator;


public class ComboBoxDirectEditManager extends DirectEditManager {

	protected static final String UNSET = "<UNSET>";

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	private List<FontDescriptor> cachedFontDescriptors = new ArrayList<FontDescriptor>();

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	private Font zoomLevelFont;

	public ComboBoxDirectEditManager(ISemanticRedirectingEditPart source,
			CellEditorLocator locator) {
		super(source, ComboBoxCellEditor.class, locator);
	}

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			final ITextAwareEditPart source) {

		final ILabelDelegate label = (ILabelDelegate) source
				.getAdapter(ILabelDelegate.class);
		if (label != null) {
			return new CellEditorLocator() {

				public void relocate(CellEditor celleditor) {
					Scrollable control = (Scrollable) celleditor.getControl();

					Rectangle rect = label.getTextBounds().getCopy();
					if (label.getText().length() <= 0) {
						// if there is no text, let's assume a default size
						// because it looks silly when the cell editor it tiny.
						rect.setSize(new Dimension(control.computeSize(
								SWT.DEFAULT, SWT.DEFAULT)));

						if (label.isTextWrapOn()) {
							// adjust the location of the cell editor based on
							// text
							// justification (i.e. where the cursor will be
							if (label.getTextJustification() == PositionConstants.RIGHT) {
								rect.translate(-rect.width, 0);
							} else if (label.getTextJustification() == PositionConstants.CENTER) {
								rect.translate(-rect.width / 2, 0);
							}
						}
					}

					if (label.isTextWrapOn()) {
						if (!control.getFont().isDisposed()) {
							// When zoomed in, the height of this rectangle is
							// not
							// sufficient because the text is shifted downwards
							// a
							// little bit. Add some to the height to compensate
							// for
							// this. I'm not sure why this is happening, but I
							// can
							// see the text shifting down even in a label on a
							// GEF
							// logic diagram when zoomed into 400%.
							int charHeight = FigureUtilities.getFontMetrics(
									control.getFont()).getHeight();
							rect.resize(0, charHeight / 2);
						}

					} else {

						rect.setSize(new Dimension(control.computeSize(
								SWT.DEFAULT, SWT.DEFAULT)));

						// If SWT.WRAP is not passed in as a style of the
						// TextCellEditor, then for some reason the first
						// character disappears upon entering the second
						// character. This should be investigated and an
						// SWT bug logged.
						int avr = FigureUtilities.getFontMetrics(
								control.getFont()).getAverageCharWidth();
						rect.setSize(new Dimension(control.computeSize(
								SWT.DEFAULT, SWT.DEFAULT)).expand(avr * 2, 0));
					}

					org.eclipse.swt.graphics.Rectangle newRect = control
							.computeTrim(rect.x, rect.y, rect.width,
									rect.height);
					if (!newRect.equals(control.getBounds())) {
						control.setBounds(newRect.x, newRect.y, newRect.width,
								newRect.height);
					}
				}
			};
		}

		// return a default figure locator
		return new CellEditorLocator() {
			public void relocate(CellEditor celleditor) {
				Control control = celleditor.getControl();
				Rectangle rect = source.getFigure().getBounds().getCopy();
				source.getFigure().translateToAbsolute(rect);
				if (!rect.equals(new Rectangle(control.getBounds()))) {
					control.setBounds(rect.x, rect.y, control.getSize().x,
							control.getSize().y);
				}
			}
		};
	}

	@Override
	protected CellEditor createCellEditorOn(Composite composite) {
		List<?> choices = new ArrayList<Object>(
				((ISemanticRedirectingEditPart) getEditPart()).getCandidates());
		choices.add(0, null);
		ExtendedComboBoxCellEditor cellEditor = new ExtendedComboBoxCellEditor(
				composite, choices, getLabelProvider(), SWT.SIMPLE);
		return cellEditor;
	}

	private ILabelProvider getLabelProvider() {
		return new LabelProvider() {
			public Image getImage(Object element) {
				if (element == null)
					return null;
				IItemLabelProvider itemLabelProvider = getItemLabelProvider(element);
				return (Image) itemLabelProvider.getImage(element);
			}

			public String getText(Object element) {
				if (element == null)
					return UNSET;
				return getItemLabelProvider(element).getText(element);
			}

			private IItemLabelProvider getItemLabelProvider(Object element) {
				EObject eObject = (EObject) element;
				EClass eClass = eObject.eClass();
				Descriptor descriptor = ComposedAdapterFactory.Descriptor.Registry.INSTANCE
						.getDescriptor(Arrays
								.asList(new Object[] {
										eClass.getEPackage().getNsURI(),
										"org.eclipse.emf.edit.provider.IItemLabelProvider" }));
				IItemLabelProvider itemLabelProvider = (IItemLabelProvider) descriptor
						.createAdapterFactory().adapt(eObject,
								IItemLabelProvider.class);
				return itemLabelProvider;
			}
		};
	}

	protected EObject getSemanticElement() {
		IGraphicalEditPart editPart = (IGraphicalEditPart) getEditPart();
		return editPart.resolveSemanticElement();
	}

	@Override
	protected void initCellEditor() {
		IFigure label = getEditPart().getFigure();
		ExtendedComboBoxCellEditor cellEditor = (ExtendedComboBoxCellEditor) getCellEditor();
		CCombo comboBox = (CCombo) cellEditor.getControl();
		// scale the font accordingly to the zoom level
		comboBox.setFont(getScaledFont(label));
		cellEditor.setValue(((ISemanticRedirectingEditPart) getEditPart())
				.getValue());
		cellEditor.getControl().pack();
		comboBox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				commit();
			}
		});
	}

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 * 
	 * @param label
	 * @return
	 */
	protected Font getScaledFont(IFigure label) {
		Font scaledFont = label.getFont();
		FontData data = scaledFont.getFontData()[0];
		Dimension fontSize = new Dimension(0, MapModeUtil.getMapMode(label)
				.DPtoLP(data.getHeight()));
		label.translateToAbsolute(fontSize);

		if (Math.abs(data.getHeight() - fontSize.height) < 2)
			fontSize.height = data.getHeight();

		try {
			FontDescriptor fontDescriptor = FontDescriptor.createFrom(data);
			cachedFontDescriptors.add(fontDescriptor);
			return getResourceManager().createFont(fontDescriptor);
		} catch (DeviceResourceException e) {
			Activator.logError("Error getting scaled font", e);
		}
		return JFaceResources.getDefaultFont();
	}

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	protected ResourceManager getResourceManager() {
		return ((DiagramGraphicalViewer) getEditPart().getViewer())
				.getResourceManager();
	}

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	protected void bringDown() {
		// myee - RATLC00523014: crashes when queued in asyncExec()
		eraseFeedback();

		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				// Content Assist hack - allow proper cleanup on childen
				// controls
				ComboBoxDirectEditManager.super.bringDown();
			}
		});

		for (Iterator<FontDescriptor> iter = cachedFontDescriptors.iterator(); iter
				.hasNext();) {
			getResourceManager().destroyFont((FontDescriptor) iter.next());
		}
		cachedFontDescriptors.clear();
	}

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	private Font getZoomLevelFont(Font actualFont, Display display) {
		Object zoom = getEditPart().getViewer().getProperty(
				ZoomManager.class.toString());

		if (zoom != null) {
			double zoomLevel = ((ZoomManager) zoom).getZoom();

			if (zoomLevel == 1.0f)
				return actualFont;

			FontData[] fd = new FontData[actualFont.getFontData().length];
			FontData tempFD = null;

			for (int i = 0; i < fd.length; i++) {
				tempFD = actualFont.getFontData()[i];

				fd[i] = new FontData(tempFD.getName(),
						(int) (zoomLevel * tempFD.getHeight()), tempFD
								.getStyle());
			}

			try {
				FontDescriptor fontDescriptor = FontDescriptor.createFrom(fd);
				cachedFontDescriptors.add(fontDescriptor);
				return getResourceManager().createFont(fontDescriptor);
			} catch (DeviceResourceException e) {
				Activator.logError("Error gettint zoom font", e);
				return actualFont;
			}
		} else
			return actualFont;
	}

	/**
	 * Copied from
	 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.TextDirectEditManager}
	 */
	public void show() {
		super.show();
		IFigure fig = getEditPart().getFigure();
		Control control = getCellEditor().getControl();
		this.zoomLevelFont = getZoomLevelFont(fig.getFont(), control
				.getDisplay());
		control.setFont(this.zoomLevelFont);
		// since the font's have been resized, we need to resize the Text
		// control...
		getLocator().relocate(getCellEditor());
	}

	@Override
	protected void commit() {
		setDirty(true);
		super.commit();
	}
}
