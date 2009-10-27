/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivik.viewer.content.part.diagram;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.diff.metamodel.AbstractDiffExtension;
import org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DifferenceKind;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeLeftTarget;
import org.eclipse.emf.compare.diff.metamodel.ModelElementChangeRightTarget;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChange;
import org.eclipse.emf.compare.diff.metamodel.util.DiffAdapterFactory;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.ui.util.EMFCompareConstants;
import org.eclipse.emf.compare.ui.util.EMFCompareEObjectUtils;
import org.eclipse.emf.compare.util.AdapterUtils;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.AnimatableScrollPane;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;

import de.cau.cs.kieler.kiml.ui.layout.DiagramLayoutManager;
import de.cau.cs.kieler.kivik.Constants;
import de.cau.cs.kieler.kivik.KivikPlugin;
import de.cau.cs.kieler.kivik.preferences.PreferenceConstants;
import de.cau.cs.kieler.kivik.viewer.content.ModelContentMergeViewer;
import de.cau.cs.kieler.kivik.viewer.content.part.IModelContentMergeViewerTab;
import de.cau.cs.kieler.kivik.viewer.content.part.ModelContentMergeTabFolder;
import de.cau.cs.kieler.kivik.viewer.content.part.ModelContentMergeTabObject;
import de.cau.cs.kieler.kivik.viewer.structure.ModelStructureMergeViewer;

/**
 * Displays differences in two diagrams graphically.
 * <p/>
 * Integrates as a new tab ContentMerge viewer. Provides functionality to
 * navigate easily to the differences in the two diagrams via clicking in the
 * diagram or by selecting an entry in the separate StructureMergeViewer.
 * Zooming and scrolling is done automatically, but the behavior can be adjusted
 * through the preference page.
 * <p/>
 * No means to merge diagrams yet, but should be possible as the diagrams are
 * GMF diagrams and EMF can merge those models.
 * 
 * @author <a href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>
 * @see ModelStructureMergeViewer
 */
public class ModelContentMergeDiagramTab extends DiagramGraphicalViewer
		implements IModelContentMergeViewerTab, IPropertyChangeListener {

	/** <code>int</code> representing this viewer part side. */
	protected final int partSide;

	/** Maps DiffElements to the TreeItems' data. */
	private final Map<EObject, DiffElement> dataToDiff = new EMFCompareMap<EObject, DiffElement>();
	private final Map<EObject, DiffElement> dataToRecursivelyDiff = new EMFCompareMap<EObject, DiffElement>();

	/** Maps a TreeItem to its data. */
	private final Map<DiffElement, ModelContentMergeTabObject> diffToTabObject = new EMFCompareMap<DiffElement, ModelContentMergeTabObject>();

	/** Keeps a reference to the containing tab folder. */
	private final ModelContentMergeTabFolder parent;

	/* often used objects */
	private RenderedDiagramRootEditPart rootEditPart = null;
	private FreeformLayer feedbackLayer = null;
	private Viewport viewport = null;
	private BorderItemsAwareFreeFormLayer primaryLayer = null;
	private ZoomManager zoomManager = null;
	private AdapterFactoryLabelProvider adapterLabelProvider = new AdapterFactoryLabelProvider(
			AdapterUtils.getAdapterFactory());

	/* caching of settings read from the preference store */
	private double prefInitialZoom;
	private boolean prefZoomToElement;
	private boolean prefRelayoutDiagram;
	private boolean prefCollapseUnchanged;
	private boolean prefEnableSelection;
	private boolean prefEnableScrolling;

	/**
	 * Creates a new Diagram meger tab extending a DiagramGraphicalViewer under
	 * the given parent control.
	 * 
	 * @param parentComposite
	 *            The parent {@link Composite} for this diagram viewer.
	 * @param side
	 *            Side of this viewer part.
	 * @param parentFolder
	 *            Parent folder of this tab.
	 */
	public ModelContentMergeDiagramTab(Composite parentComposite, int side,
			ModelContentMergeTabFolder parentFolder) {

		/* standards */
		super();
		updatePreferences();
		createControl(parentComposite);
		partSide = side;
		parent = parentFolder;

		/* read in preferences every time the change */
		KivikPlugin.getDefault().getPreferenceStore()
				.addPropertyChangeListener(this);

		/* initialize the DiagramGraphicalViewer */
		setEditDomain(new EditDomain());
		setEditPartFactory(EditPartService.getInstance());
		setRootEditPart(new RenderedDiagramRootEditPart(
				MeasurementUnit.PIXEL_LITERAL));

		/* fetch some often needed objects */
		rootEditPart = (RenderedDiagramRootEditPart) getRootEditPart();
		zoomManager = (ZoomManager) rootEditPart.getZoomManager();

		/*
		 * create and add a new scalable feedback layer to the viewer to display
		 * the highlighting
		 */
		feedbackLayer = new FreeformLayer();
		feedbackLayer.setEnabled(false);
		ScalableFreeformLayeredPane scalableLayers = (ScalableFreeformLayeredPane) rootEditPart
				.getLayer(DiagramRootEditPart.SCALABLE_LAYERS);
		scalableLayers.add(feedbackLayer,
				DiagramRootEditPart.SCALED_FEEDBACK_LAYER);
		viewport = zoomManager.getViewport();

		primaryLayer = (BorderItemsAwareFreeFormLayer) rootEditPart
				.getLayer(DiagramRootEditPart.PRIMARY_LAYER);

		/* set some initial values and options */
		zoomManager.setZoom(prefInitialZoom);
		if (prefEnableScrolling) {
			zoomManager.setZoomAnimationStyle(ZoomManager.ANIMATE_ZOOM_IN_OUT);
		} else {
			zoomManager.setZoomAnimationStyle(ZoomManager.ANIMATE_NEVER);
		}

		/* add mouse zoom handler */
		setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.MOD1),
				MouseWheelZoomHandler.SINGLETON);
	}

	/**
	 */
	public void dispose() {
		dataToDiff.clear();
		diffToTabObject.clear();
		KivikPlugin.getDefault().getPreferenceStore()
				.removePropertyChangeListener(this);
	}

	/**
	 * Sets the input for the Diagram viewer and marks the differences from this
	 * input in contrast to the input in the opposite viewer.
	 * 
	 * @param object
	 *            The object which should be displayed, Diagram or EObject with
	 *            a resource containing a diagram
	 * @see de.cau.cs.kieler.kivik.viewer.content.part.IModelContentMergeViewerTab#setReflectiveInput(java.lang.Object)
	 */
	public void setReflectiveInput(Object object) {
		boolean collapsedElements = false;
		Diagram diagram = null;

		// We *need* to invalidate the cache here since setInput() would try to
		// use it otherwise
		dataToDiff.clear();
		dataToRecursivelyDiff.clear();
		diffToTabObject.clear();

		if (object instanceof Diagram) {
			diagram = (Diagram) object;

		} else {
			assert object instanceof EObject;
			EObject eobj = (EObject) object;
			Resource resource = eobj.eResource();
			diagram = (Diagram) resource.getContents().get(1);
		}

		/* create editing domain for the diagram and ... */
		DiagramEditingDomainFactory.getInstance().createEditingDomain(
				diagram.eResource().getResourceSet());

		/* ... set diagram as content of this viewer */
		setContents(diagram);

		/* map all the differences provided by EMF Compare */
		mapDifferences();
		mapDiffGroups();
		mapDiffToTabObjects();

		/* collapse unchanged compartments if desired */
		if (prefCollapseUnchanged) {
			collapsedElements = collapseUnchanged(diagram);
		}

		/* if collapsing is desired, or if re-layout is switched on, do it */
		if (prefRelayoutDiagram || collapsedElements) {
			primaryLayer.validate();

			/*
			 * force the diagram layouter to perform several layout steps, as
			 * otherwise the connections and labels are not drawn properly.
			 * Normally, 3 should be enough, don't know why we need 4.
			 */
			DiagramLayoutManager.layout(null, (EditPart)getEditPartRegistry().get(diagram),
			        false, false);
		}

		/* check if user wants to be able to click on changed Elements */
		if (!prefEnableSelection) {
			primaryLayer.setEnabled(false);
		}

		/* colorize the changed EditParts in the respective color */
		colorizeEditParts();

	}

	/**
	 * Takes a diagram as input and closes all the unchanged compartments. The
	 * changes are read out of the global map of changes which must be built up
	 * before.
	 * <p/>
	 * Collapsing is done by by setting the AnimateableScrollPane of the
	 * compartments to collapsed.
	 * 
	 * @param diagram
	 *            The Diagram to collapse
	 */
	private boolean collapseUnchanged(Diagram diagram) {
		TreeIterator<EObject> allContents = diagram.eAllContents();
		boolean collapsedElements = false;

		while (allContents.hasNext()) {
			EObject next = allContents.next();
			if (next instanceof View) {
				View theView = (View) next;
				DiffElement theChange = dataToRecursivelyDiff.get(theView
						.getElement());
				boolean forceCollapse = false;
				if (theChange instanceof DiffGroup) {
					DiffGroup diffGroup = (DiffGroup) theChange;
					forceCollapse = true;
					for (DiffElement element : diffGroup.getSubDiffElements()) {
						if (!(element instanceof ReferenceChange))
							forceCollapse = false;
					}
				}
				if (theChange == null || forceCollapse) {
					Object editPart = getEditPartRegistry().get(theView);
					if (editPart instanceof ShapeCompartmentEditPart) {
						ShapeCompartmentFigure scf = ((ShapeCompartmentEditPart) editPart)
								.getShapeCompartmentFigure();
						((AnimatableScrollPane) scf.getScrollPane()).collapse();
						collapsedElements = true;
					}
				}
			}
		}
		return collapsedElements;
	}

	/**
	 * Colorizes all the changed EditParts in the Diagram with the respective
	 * color.
	 * <p/>
	 * Changes are read out with another function which relies on the global
	 * registry of the changes, so that must be done first.
	 */
	private void colorizeEditParts() {
		for (ModelContentMergeTabObject object : getVisibleElements()) {
			if (object.getActualObject() instanceof AbstractGraphicalEditPart) {

				String backgroundColor = object.getBackgroundColor();
				if (backgroundColor == Constants.DO_NOT_COLORIZE)
					continue;

				Color highlightColor = new Color(null, ModelContentMergeViewer
						.getColor(backgroundColor));

				AbstractGraphicalEditPart changedEditPart = (AbstractGraphicalEditPart) object
						.getActualObject();

				if (changedEditPart instanceof ConnectionEditPart) {
					changedEditPart.getFigure().setForegroundColor(
							brightenColor(highlightColor));
				} else {
					/*
					 * If a compartment is collapsed, then highlight the whole
					 * container
					 */
					if (changedEditPart.getParent() != null
							&& changedEditPart.getParent() instanceof AbstractGraphicalEditPart) {
						if (((AbstractGraphicalEditPart) changedEditPart
								.getParent()).getFigure() instanceof ResizableCompartmentFigure) {
							ResizableCompartmentFigure parentFigure = (ResizableCompartmentFigure) ((AbstractGraphicalEditPart) changedEditPart
									.getParent()).getFigure();
							AnimatableScrollPane asp = (AnimatableScrollPane) parentFigure
									.getScrollPane();
							if (!asp.isExpanded()) {

								parentFigure.getParent().setBackgroundColor(
										highlightColor);
							}
						}
					}
					changedEditPart.getContentPane().setBackgroundColor(
							highlightColor);
				}
				if (changedEditPart.getChildren().size() > 0
						&& changedEditPart.getChildren().get(0) instanceof ShapeCompartmentEditPart) {
					ShapeCompartmentEditPart scep = (ShapeCompartmentEditPart) changedEditPart
							.getChildren().get(0);
					scep.getFigure().setToolTip(
							getToolTip(changedEditPart, highlightColor));
				} else {
					changedEditPart.getContentPane().setToolTip(
							getToolTip(changedEditPart, highlightColor));
				}

			}
		}
	}

	/**
	 * Maps the input's differences if any. That is map a DiffElement to an
	 * EditPart.
	 */
	private void mapDifferences() {

		dataToDiff.clear();
		final Iterator<DiffElement> diffIterator = parent.getDiffAsList()
				.iterator();
		while (diffIterator.hasNext()) {

			final DiffElement diff = diffIterator.next();
			final EObject data;
			final EObject diffContainer = diff.eContainer();
			final EObject dataContainer;

			if (diffContainer instanceof DiffGroup) {
				dataContainer = EMFCompareEObjectUtils
						.getLeftElement(((DiffGroup) diffContainer));
				if (dataContainer != null)
					dataToDiff.put(dataContainer, (DiffElement) diffContainer);
			}
			if (partSide == EMFCompareConstants.ANCESTOR
					&& diff instanceof ConflictingDiffElement)
				data = ((ConflictingDiffElement) diff).getOriginElement();
			else if (partSide == EMFCompareConstants.LEFT)
				data = EMFCompareEObjectUtils.getLeftElement(diff);
			else
				data = EMFCompareEObjectUtils.getRightElement(diff);
			if (data != null)
				dataToDiff.put(data, diff);
			else
				// TODO for now, we're using the first item's data, we should
				// look for the matchedElement
				dataToDiff.put(getDiagram().getElement(), diff);
		}
	}

	/**
	 * Maps also the DiffGroups to EditParts. That are then EditParts with
	 * content. Is done to mark also EditParts whose contained EditParts have
	 * changed as changed. But they are put in another HashMap as they should
	 * not be colorized or merged (maybe in a later implementation).
	 */
	private void mapDiffGroups() {
		dataToRecursivelyDiff.clear();
		final TreeIterator<EObject> diffGroupIterator = parent.getDiffModel()
				.eAllContents();
		while (diffGroupIterator.hasNext()) {
			EObject next = diffGroupIterator.next();
			if (next instanceof DiffGroup) {
				DiffGroup diffGroup = (DiffGroup) next;
				EObject data = EMFCompareEObjectUtils.getLeftElement(diffGroup);

				if (data != null) {
					EObject matchElements = parent.findMatchFromElement(data);
					if (matchElements instanceof Match2Elements) {
						if (partSide == EMFCompareConstants.LEFT) {
							dataToRecursivelyDiff.put(
									((Match2Elements) matchElements)
											.getLeftElement(), diffGroup);
						} else if (partSide == EMFCompareConstants.RIGHT) {
							dataToRecursivelyDiff.put(
									((Match2Elements) matchElements)
											.getRightElement(), diffGroup);
						}

					}
				}
			} else {
				if (next instanceof DiffElement) {
					DiffElement diffElement = (DiffElement) next;
					if (partSide == EMFCompareConstants.LEFT
							&& !dataToRecursivelyDiff
									.containsKey(EMFCompareEObjectUtils
											.getLeftElement(diffElement))) {
						dataToRecursivelyDiff.put(EMFCompareEObjectUtils
								.getLeftElement(diffElement), diffElement);
					}
					if (partSide == EMFCompareConstants.RIGHT
							&& !dataToRecursivelyDiff
									.containsKey(EMFCompareEObjectUtils
											.getRightElement(diffElement))) {
						dataToRecursivelyDiff.put(EMFCompareEObjectUtils
								.getRightElement(diffElement), diffElement);
					}
				}
			}
		}
	}

	/**
	 * This will map all the EditParts in this DiagramGraphicalViewer that need
	 * be taken into account when drawing diff markers to a corresponding
	 * ModelContentMergeTabObject. This will allow us to browse everything once
	 * and for all.
	 * <p/>
	 * The markers are not drawn in the Diagram compare view, as in the case
	 * when having the Differences or Properties tab enabled.
	 */
	private void mapDiffToTabObjects() {
		diffToTabObject.clear();
		for (EObject key : dataToDiff.keySet()) {
			final DiffElement diff = dataToDiff.get(key);
			// Defines the TreeItem corresponding to this difference
			EObject data;
			if (partSide == EMFCompareConstants.ANCESTOR
					&& diff instanceof ConflictingDiffElement)
				data = ((ConflictingDiffElement) diff).getOriginElement();
			else if (partSide == EMFCompareConstants.LEFT)
				data = EMFCompareEObjectUtils.getLeftElement(diff);
			else
				data = EMFCompareEObjectUtils.getRightElement(diff);
			if (data == null) {
				EObject matchElements = parent
						.findMatchFromElement(EMFCompareEObjectUtils
								.getLeftElement(diff));
				if (matchElements instanceof Match2Elements) {
					// data = ((Match2Elements)
					// matchElements).getRightElement();
				}
			}
			final AbstractGraphicalEditPart actualEditPart = findAbstractGraphicalEditPart(data);

			if (actualEditPart == null)
				continue;

			AbstractGraphicalEditPart visibleEditPart = null;
			if (partSide == EMFCompareConstants.LEFT
					&& diff instanceof ModelElementChangeRightTarget
					&& ((ModelElementChangeRightTarget) diff).getRightElement()
							.eContainer() != null) {
				// in the case of a modelElementChangeRightTarget, we know we
				// can't find
				// the element itself, we'll then search for one with the same
				// index
				final EObject right = ((ModelElementChangeRightTarget) diff)
						.getRightElement();
				final EObject left = ((ModelElementChangeRightTarget) diff)
						.getLeftParent();
				final int rightIndex = right.eContainer().eContents().indexOf(
						right);
				// Ensures we cannot trigger ArrayOutOfBounds exeptions
				final int leftIndex = Math.min(rightIndex - 1, left.eContents()
						.size() - 1);
				if (left.eContents().size() > 0)
					visibleEditPart = findAbstractGraphicalEditPart(left
							.eContents().get(leftIndex));
			} else if (partSide == EMFCompareConstants.RIGHT
					&& diff instanceof ModelElementChangeLeftTarget
					&& ((ModelElementChangeLeftTarget) diff).getLeftElement()
							.eContainer() != null) {
				// in the case of a modelElementChangeLeftTarget, we know we
				// can't find
				// the element itself, we'll then search for one with the same
				// index
				final EObject right = ((ModelElementChangeLeftTarget) diff)
						.getRightParent();
				final EObject left = ((ModelElementChangeLeftTarget) diff)
						.getLeftElement();
				final int leftIndex = left.eContainer().eContents().indexOf(
						left);
				// Ensures we cannot trigger ArrayOutOfBounds exeptions
				final int rightIndex = Math.max(0, Math.min(leftIndex - 1,
						right.eContents().size() - 1));
				if (right.eContents().size() > 0)
					visibleEditPart = findAbstractGraphicalEditPart(right
							.eContents().get(rightIndex));
			}

			// and now the color which should be used for this kind of
			// differences
			final String color;
			final String backgroundColor;

			if (diff.getKind() == DifferenceKind.CONFLICT
					|| diff.isConflicting()) {
				color = EMFCompareConstants.PREFERENCES_KEY_CONFLICTING_COLOR;
				backgroundColor = EMFCompareConstants.PREFERENCES_KEY_CONFLICTING_COLOR;

			} else if (diff.getKind() == DifferenceKind.ADDITION) {
				color = EMFCompareConstants.PREFERENCES_KEY_ADDED_COLOR;
				// do not colorize containers of added elements in the left part
				if (partSide == EMFCompareConstants.LEFT) {
					backgroundColor = Constants.DO_NOT_COLORIZE;
				} else {
					backgroundColor = EMFCompareConstants.PREFERENCES_KEY_ADDED_COLOR;
				}

			} else if (diff.getKind() == DifferenceKind.DELETION) {
				color = EMFCompareConstants.PREFERENCES_KEY_REMOVED_COLOR;
				// do not colorize containers of deleted elements in the right
				// part
				if (partSide == EMFCompareConstants.RIGHT) {
					backgroundColor = Constants.DO_NOT_COLORIZE;
				} else {
					backgroundColor = EMFCompareConstants.PREFERENCES_KEY_REMOVED_COLOR;
				}

			} else if (diff.getKind() == DifferenceKind.CHANGE) {
				color = EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR;
				backgroundColor = EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR;

			} else if (diff.getKind() == DifferenceKind.MOVE) {
				color = EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR;
				backgroundColor = EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR;

			} else {
				color = Constants.DO_NOT_COLORIZE;
				backgroundColor = Constants.DO_NOT_COLORIZE;
			}
			final ModelContentMergeTabObject wrappedItem;
			// if (visibleEditPart != null)
			// wrappedItem = new ModelContentMergeTabObject(actualEditPart,
			// visibleEditPart, color, backgroundColor);
			// else
			wrappedItem = new ModelContentMergeTabObject(actualEditPart,
					actualEditPart, color, backgroundColor);
			diffToTabObject.put(diff, wrappedItem);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.cau.cs.kieler.kivik.viewer.content.part.
	 * IModelContentMergeViewerTab#getSelectedElements()
	 */
	public List<? extends AbstractGraphicalEditPart> getSelectedElements() {
		List<AbstractGraphicalEditPart> selectedEditParts = new ArrayList<AbstractGraphicalEditPart>();
		for (Object element : getSelectedEditParts()) {
			if (element instanceof AbstractGraphicalEditPart)
				selectedEditParts.add((AbstractGraphicalEditPart) element);
		}
		return selectedEditParts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.cau.cs.kieler.kivik.viewer.content.part.
	 * IModelContentMergeViewerTab#getUIElement(org.eclipse.emf.ecore.EObject)
	 */
	public ModelContentMergeTabObject getUIElement(EObject data) {
		/*
		 * If the diff is hidden by another (diff extension), the item won't be
		 * returned Same goes for diffs that couldn't be matched
		 */
		final DiffElement diff = dataToDiff.get(data);
		if (diff != null && DiffAdapterFactory.shouldBeHidden(diff))
			return null;

		return diffToTabObject.get(diff);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.cau.cs.kieler.kivik.viewer.content.part.
	 * IModelContentMergeViewerTab#getVisibleElements()
	 */
	public List<ModelContentMergeTabObject> getVisibleElements() {
		final List<ModelContentMergeTabObject> result = new ArrayList<ModelContentMergeTabObject>();
		// This will happen if the user has "merged all"
		if (parent.getDiffAsList().size() == 0)
			return result;
		// At the moment return all
		for (ModelContentMergeTabObject tabObject : diffToTabObject.values()) {
			result.add(tabObject);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.cau.cs.kieler.kivik.viewer.content.part.
	 * IModelContentMergeViewerTab#showElements(java.util.List)
	 */
	public void showElements(List<DiffElement> diffElements) {
		deselectAll();

		final List<AbstractGraphicalEditPart> newSelection = new ArrayList<AbstractGraphicalEditPart>();
		AbstractGraphicalEditPart editPart = null;

		for (DiffElement diffElement : diffElements) {

			if (partSide == EMFCompareConstants.ANCESTOR
					&& diffElement instanceof ConflictingDiffElement) {
				editPart = findAbstractGraphicalEditPart(((ConflictingDiffElement) diffElement)
						.getOriginElement());
			} else if (partSide == EMFCompareConstants.LEFT) {
				editPart = findAbstractGraphicalEditPart(EMFCompareEObjectUtils
						.getLeftElement(diffElement));
			} else {
				// convenience hack, highlight also diffgroup in right diagram
				if (diffElement instanceof DiffGroup
						&& EMFCompareEObjectUtils.getLeftElement(diffElement) != null)
					editPart = findAbstractGraphicalEditPart(EMFCompareEObjectUtils
							.getRightElement(parent
									.findMatchFromElement(EMFCompareEObjectUtils
											.getLeftElement(diffElement))));
				else if (!(diffElement instanceof DiffGroup)) {
					editPart = findAbstractGraphicalEditPart(EMFCompareEObjectUtils
							.getRightElement(diffElement));
				}
			}

			if (editPart != null) {
				newSelection.add(editPart);
			}
		}

		/* scrolls to, zooms to and highlights the selected element */
		if (newSelection.size() == 1)
			markEditParts(newSelection.get(0));

	}

	/**
	 * Scrolls to, zooms to and highlights the selected element. The marker
	 * figure is drawn on the scalable feedback layer.
	 * 
	 * @param editPart
	 *            The EditPart under inspection
	 */
	private void markEditParts(AbstractGraphicalEditPart editPart) {
		feedbackLayer.removeAll();

		/* scrolling and zoom */
		IFigure fig = editPart.getFigure();
		Rectangle figBounds = translateFromTo(fig, viewport);

		if (prefZoomToElement) {
			/* this is ZOOMING and SCROLLING */
			if (editPart.equals(getContents())) {
				/*
				 * if DiagramEditPart selected, zoom to fit all and not to the
				 * bounds
				 */
				zoomManager.setZoomAsText(ZoomManager.FIT_ALL);
			} else {
				zoomManager.zoomTo(figBounds.expand(50, 50));
			}
		} else {
			/* this is SCROLLING */
			Rectangle newZoomLocation = new Rectangle(figBounds.getCenter()
					.translate(viewport.getSize().scale(-0.5)), viewport
					.getBounds().getSize());
			zoomManager.zoomTo(newZoomLocation);
		}

		/* mark the right element if compartment is collapsed */
		if (editPart.getParent() != null
				&& editPart.getParent() instanceof AbstractGraphicalEditPart) {
			if (((AbstractGraphicalEditPart) editPart.getParent()).getFigure() instanceof ResizableCompartmentFigure) {
				ResizableCompartmentFigure parentFigure = (ResizableCompartmentFigure) ((AbstractGraphicalEditPart) editPart
						.getParent()).getFigure();
				AnimatableScrollPane asp = (AnimatableScrollPane) parentFigure
						.getScrollPane();
				if (!asp.isExpanded()) {
					fig = parentFigure.getParent();
				}
			}
		}

		Color highlightColor = new Color(null, ModelContentMergeViewer
				.getColor(EMFCompareConstants.PREFERENCES_KEY_HIGHLIGHT_COLOR));

		/* constructs the marker figure with the right size and position */
		Rectangle newBounds = translateFromTo(fig, feedbackLayer);
		RoundedRectangle markerFigure = new RoundedRectangle();
		markerFigure.setBounds(newBounds.expand(8, 8));
		markerFigure.setLineWidth(5);
		markerFigure.setForegroundColor(highlightColor);
		markerFigure.setFill(false);

		feedbackLayer.add(markerFigure);
	}

	/**
	 * Returns an EditPart to a given EObject.
	 * 
	 * @param target
	 *            The EObject to which the corresponding EditPart should be
	 *            found
	 * @return The EditPart for the provided EObject
	 */
	private AbstractGraphicalEditPart findAbstractGraphicalEditPart(
			EObject target) {

		GraphicalEditPart gepBegin = (GraphicalEditPart) getContents();
		if (target == null || gepBegin == null)
			return null;

		AbstractGraphicalEditPart foundEditPart = (GraphicalEditPart) gepBegin
				.findEditPart(null, target);

		if (foundEditPart == null) {
			GraphicalEditPart gepParent = (GraphicalEditPart) gepBegin
					.findEditPart(null, target.eContainer());
			foundEditPart = (AbstractGraphicalEditPart) findConnectionEditPart(
					gepParent, target);
		}
		return foundEditPart;
	}

	/*
	 * Recursive helper function. Finds an ConnectionEditPart given a starting
	 * EditPart and an EObject
	 */
	private AbstractGraphicalEditPart findConnectionEditPart(
			AbstractGraphicalEditPart epBegin, EObject theElement) {
		if (theElement == null || epBegin == null) {
			return null;
		}

		// check if gmf graphical edit part to be able to get connections
		if (epBegin instanceof GraphicalEditPart) {
			GraphicalEditPart epStart = (GraphicalEditPart) epBegin;

			// source connections
			for (Object obj : epStart.getSourceConnections()) {
				if (obj instanceof ConnectionEditPart) {
					ConnectionEditPart connectionEditPart = (ConnectionEditPart) obj;

					final View view = (View) ((IAdaptable) connectionEditPart)
							.getAdapter(View.class);

					if (view != null) {

						EObject el = ViewUtil.resolveSemanticElement(view);

						if ((el != null) && el.equals(theElement)) {
							return (AbstractGraphicalEditPart) connectionEditPart;
						}
					}
				}
			}

			// same for target connections
			for (Object obj : epStart.getTargetConnections()) {
				if (obj instanceof ConnectionEditPart) {
					ConnectionEditPart connectionEditPart = (ConnectionEditPart) obj;

					final View view = (View) ((IAdaptable) connectionEditPart)
							.getAdapter(View.class);

					if (view != null) {

						EObject el = ViewUtil.resolveSemanticElement(view);

						if ((el != null) && el.equals(theElement)) {
							return (AbstractGraphicalEditPart) connectionEditPart;
						}
					}
				}
			}

			ListIterator<?> childLI = epStart.getChildren().listIterator();
			while (childLI.hasNext()) {
				AbstractGraphicalEditPart epChild = (AbstractGraphicalEditPart) childLI
						.next();

				AbstractGraphicalEditPart elementEP = findConnectionEditPart(
						epChild, theElement);
				if (elementEP != null) {
					return elementEP;
				}
			}
		}
		return null;
	}

	/**
	 * Helper function used when translating the location for a Figure to
	 * another Figure layer. Needed as the feedback layer uses absolute
	 * coordinates in contrast to the relative coordinates of every element's
	 * hierearchy.
	 * 
	 * @param from
	 *            The Figure which should be translated
	 * @param to
	 *            The Figure with the coordinate system to which the given
	 *            Figure should be translated
	 * @return The translated Bounds of the <i>from</i> Figure now in the
	 *         coordinate system of the <b>to</b> Figure
	 */
	private Rectangle translateFromTo(IFigure from, IFigure to) {
		Rectangle newBounds = from.getBounds().getCopy();
		from.translateToAbsolute(newBounds);
		to.translateToRelative(newBounds);
		return newBounds;
	}

	/**
	 * Generates a Figure which should act as a ToolTip for the provided
	 * EditPart, indicating the changes of the EditPart.
	 * 
	 * @param changedEditPart
	 *            The EditPart for which the ToolTip should be created
	 * @param highlightColor
	 *            The color that should be used for the ToolTip, indicating the
	 *            type of change
	 * @return A ToolTip Figure with a String of changes a label
	 */
	private IFigure getToolTip(AbstractGraphicalEditPart changedEditPart,
			Color highlightColor) {

		DiffElement diffElement = dataToRecursivelyDiff
				.get(((View) changedEditPart.getModel()).getElement());

		if (diffElement != null) {
			/* get the nicely formatted text */

			String text = null;
			Image image = null;
			if (diffElement instanceof AbstractDiffExtension) {
				text = ((AbstractDiffExtension) diffElement).getText();
			} else {
				if (diffElement instanceof IFile) {
					text = ((IFile) diffElement).getName();
				} else {
					text = adapterLabelProvider.getText(diffElement);
					image = adapterLabelProvider.getImage(diffElement);
				}
			}

			Label smallToolTip = new Label(text);
			smallToolTip.setBorder(new MarginBorder(8));
			smallToolTip.setBackgroundColor(highlightColor);
			smallToolTip.setIcon(image);
			smallToolTip.setIconAlignment(PositionConstants.LEFT);
			smallToolTip.setIconTextGap(5);
			return smallToolTip;
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse
	 * .jface.util.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent event) {
		updatePreferences();
	}

	/**
	 * Updates the preferences as they are set in the plug-in's preference
	 * store.
	 */
	private void updatePreferences() {
		IPreferenceStore prefs = KivikPlugin.getDefault().getPreferenceStore();
		prefRelayoutDiagram = prefs
				.getBoolean(PreferenceConstants.PREF_KIVIK_ENABLE_AUTO_LAYOUT);
		prefCollapseUnchanged = prefs
				.getBoolean(PreferenceConstants.PREF_KIVIK_ENABLE_COLLAPSING_OF_UNCHANGED_ELEMENTS);
		prefEnableSelection = prefs
				.getBoolean(PreferenceConstants.PREF_KIVIK_ENABLE_SELECTING_IN_DIAGRAM);
		prefZoomToElement = prefs
				.getBoolean(PreferenceConstants.PREF_KIVIK_ENABLE_ZOOMING_TO_CHANGED_ELEMENTS);
		prefEnableScrolling = prefs
				.getBoolean(PreferenceConstants.PREF_KIVIK_ENABLE_SCROLLING_ANIMATION);
		prefInitialZoom = prefs
				.getInt(PreferenceConstants.PREF_KIVIK_INITIAL_ZOOM_FACTOR) / 100.0f;

		if (primaryLayer != null)
			primaryLayer.setEnabled(prefEnableSelection);
	}

	/**
	 * Convenient function which returns the Diagram of this viewer
	 * 
	 * @return The Diagram of this viewer
	 */
	private Diagram getDiagram() {
		return (Diagram) getRootEditPart().getContents().getModel();
	}

	public void redraw() {
	}

	private Color brightenColor(Color highlightColor) {
		int red = (highlightColor.getRed() + 20) > 255 ? 255 : highlightColor
				.getRed() + 20;
		int green = (highlightColor.getGreen() + 20) > 255 ? 255
				: highlightColor.getGreen() + 20;
		int blue = (highlightColor.getBlue() + 20) > 255 ? 255 : highlightColor
				.getBlue() + 20;
		return new Color(null, red, green, blue);
	}
}
