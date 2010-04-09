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
package de.cau.cs.kieler.kivik.viewer.content.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.compare.diff.metamodel.AttributeChange;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSnapshot;
import org.eclipse.emf.compare.diff.metamodel.ComparisonSnapshot;
import org.eclipse.emf.compare.diff.metamodel.ConflictingDiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.ReferenceChange;
import org.eclipse.emf.compare.match.metamodel.Match2Elements;
import org.eclipse.emf.compare.match.metamodel.Match3Elements;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.match.metamodel.UnmatchElement;
import org.eclipse.emf.compare.ui.EMFCompareUIPlugin;
import org.eclipse.emf.compare.ui.ICompareEditorPartListener;
import org.eclipse.emf.compare.ui.ModelCompareInput;
import org.eclipse.emf.compare.ui.util.EMFCompareConstants;
import org.eclipse.emf.compare.ui.util.EMFCompareEObjectUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.cau.cs.kieler.kivik.KivikUIMessages;
import de.cau.cs.kieler.kivik.viewer.content.ModelContentMergeViewer;
import de.cau.cs.kieler.kivik.viewer.content.part.diagram.ModelContentMergeDiagramTab;
import de.cau.cs.kieler.kivik.viewer.content.part.diff.ModelContentMergeDiffTab;
import de.cau.cs.kieler.kivik.viewer.content.part.property.ModelContentMergePropertyTab;


/**
 * Describes a part of a {@link ModelContentMergeViewer}.
 * <p/>
 * Initial implementation by <a href="mailto:laurent.goubet@obeo.fr">Laurent
 * Goubet</a>. Adjustments by Arne Schipper to support, in contrast to EMF
 * Compare, a third panel which displays a diagram.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 * @author ars
 */
public class ModelContentMergeTabFolder {
	/** This keeps track of the parent viewer of this tab folder. */
	/* protected */final ModelContentMergeViewer parentViewer;

	/**
	 * This <code>int</code> represents the side of this viewer part. Must be
	 * one of
	 * <ul>
	 * <li>{@link EMFCompareConstants#RIGHT}</li>
	 * <li>{@link EMFCompareConstants#LEFT}</li>
	 * <li>{@link EMFCompareConstants#ANCESTOR}</li>
	 * </ul>
	 */
	protected final int partSide;

	/** This is the content of the properties tab for this viewer part. */
	protected IModelContentMergeViewerTab properties;

	/** This is the content of the properties tab for this viewer part. */
	protected IModelContentMergeViewerTab diagram;

	/** This is the view displayed by this viewer part. */
	protected CTabFolder tabFolder;

	/** Keeps references to the tabs contained within this folder. */
	protected final List<IModelContentMergeViewerTab> tabs = new ArrayList<IModelContentMergeViewerTab>();

	/** This is the content of the tree tab for this viewer part. */
	protected IModelContentMergeViewerTab tree;

	/**
	 * Indicates that the tree has been expanded since last time we mapped the
	 * TreeItems.
	 */
	/* package */boolean expanded = true;

	/** This contains all the listeners registered for this viewer part. */
	private final List<ICompareEditorPartListener> editorPartListeners = new ArrayList<ICompareEditorPartListener>();

	/**
	 * Instantiates a {@link ModelContentMergeTabFolder} given its parent
	 * {@link Composite} and its side.
	 * 
	 * @param viewer
	 *            Parent viewer of this viewer part.
	 * @param composite
	 *            Parent {@link Composite} for this part.
	 * @param side
	 *            Comparison side of this part. Must be one of
	 *            {@link EMFCompareConstants#LEFT EMFCompareConstants.RIGHT},
	 *            {@link EMFCompareConstants#RIGHT EMFCompareConstants.LEFT} or
	 *            {@link EMFCompareConstants#ANCESTOR
	 *            EMFCompareConstants.ANCESTOR}.
	 */
	public ModelContentMergeTabFolder(ModelContentMergeViewer viewer,
			Composite composite, int side) {
		if (side != EMFCompareConstants.RIGHT
				&& side != EMFCompareConstants.LEFT
				&& side != EMFCompareConstants.ANCESTOR)
			throw new IllegalArgumentException("PartSide cannot be {0}."); //$NON-NLS-1$

		parentViewer = viewer;
		partSide = side;
		createContents(composite);
	}

	/**
	 * Registers the given listener for notification. If the identical listener
	 * is already registered the method has no effect.
	 * 
	 * @param listener
	 *            The listener to register for changes of this input.
	 */
	public void addCompareEditorPartListener(ICompareEditorPartListener listener) {
		editorPartListeners.add(listener);
	}

	/**
	 * Disposes of all resources used by this folder.
	 */
	public void dispose() {
		properties.dispose();
		tree.dispose();
		diagram.dispose();
		tabs.clear();
		tabFolder.dispose();
		editorPartListeners.clear();
	}

	/**
	 * Returns a list of all diffs contained by the input DiffModel except for
	 * DiffGroups.
	 * 
	 * @return List of the DiffModel's differences.
	 */
	public List<DiffElement> getDiffAsList() {
		if (parentViewer.getInput() != null)
			return ((ModelCompareInput) parentViewer.getInput())
					.getDiffAsList();
		return new ArrayList<DiffElement>();
	}

	public ModelCompareInput getModelCompareInput() {
		return (ModelCompareInput) parentViewer.getInput();
	}

	public DiffModel getDiffModel() {
            Object obj = ((ModelCompareInput) parentViewer.getInput()).getDiff();
            return  (DiffModel)obj;
//		return ((ComparisonResourceSnapshot) parentViewer.getInput()).getDiff();
	}

	/**
	 * Returns the properties tab of this tab folder.
	 * 
	 * @return The properties tab of this tab folder.
	 */
	public IModelContentMergeViewerTab getPropertyPart() {
		return properties;
	}

	/**
	 * Returns the tree tab of this tab folder.
	 * 
	 * @return The tree tab of this tab folder.
	 */
	public IModelContentMergeViewerTab getTreePart() {
		return tree;
	}

	/**
	 * Returns the diagram tab of this tab folder.
	 * 
	 * @return The diagram tab of this tab folder.
	 */
	public IModelContentMergeViewerTab getDiagramPart() {
		return diagram;
	}

	/**
	 * This will be used when drawing the center part's marquees.
	 * 
	 * @param element
	 *            The DiffElement which we need UI variables for.
	 * @return The object corresponding to the given DiffElement, wrapped along
	 *         with UI information.
	 */
	public ModelContentMergeTabObject getUIItem(DiffElement element) {
		final EObject data;
		if (partSide == EMFCompareConstants.ANCESTOR
				&& element instanceof ConflictingDiffElement)
			data = ((ConflictingDiffElement) element).getOriginElement();
		else if (partSide == EMFCompareConstants.LEFT)
			data = EMFCompareEObjectUtils.getLeftElement(element);
		else
			data = EMFCompareEObjectUtils.getRightElement(element);

		final EObject featureData;
		if (element instanceof AttributeChange)
			featureData = ((AttributeChange) element).getAttribute();
		else if (element instanceof ReferenceChange)
			featureData = ((ReferenceChange) element).getReference();
		else
			featureData = null;

		ModelContentMergeTabObject result = null;
		if (data != null)
			result = tabs.get(tabFolder.getSelectionIndex()).getUIElement(data);
		if (result == null && featureData != null)
			result = tabs.get(tabFolder.getSelectionIndex()).getUIElement(
					featureData);
		return result;
	}

	/**
	 * Returns the visible elements of the active tab.
	 * 
	 * @return The visible elements of the active tab.
	 */
	public List<ModelContentMergeTabObject> getVisibleElements() {
		return tabs.get(tabFolder.getSelectionIndex()).getVisibleElements();
	}

	/**
	 * Redraws this viewer part.
	 */
	public void layout() {
		tabs.get(tabFolder.getSelectionIndex()).redraw();
	}

	/**
	 * Shows the given item on the tree tab or its properties on the property
	 * tab.
	 * 
	 * @param diff
	 *            Item to scroll to.
	 */
	public void navigateToDiff(DiffElement diff) {
		final List<DiffElement> diffs = new ArrayList<DiffElement>();
		diffs.add(diff);
		navigateToDiff(diffs);
	}

	/**
	 * Ensures the first item of the given list of {@link DiffElement}s is
	 * visible, and sets the selection of the tree to all those items.
	 * 
	 * @param diffs
	 *            Items to select.
	 */
	public void navigateToDiff(List<DiffElement> diffs) {

		EObject target = null;
		DiffElement diff = diffs.get(0);

		if (diff == null)
			return;

		if (partSide == EMFCompareConstants.LEFT) {
			target = EMFCompareEObjectUtils.getLeftElement(diff);
		} else if (partSide == EMFCompareConstants.RIGHT) {
			if (diff instanceof DiffGroup
					&& EMFCompareEObjectUtils.getLeftElement(diff) != null)
				target = EMFCompareEObjectUtils
						.getRightElement(findMatchFromElement(EMFCompareEObjectUtils
								.getLeftElement(diff)));
			else if (!(diff instanceof DiffGroup))
				target = EMFCompareEObjectUtils.getRightElement(diff);
			else
				// fall through.
				return;
		} else
			target = EMFCompareEObjectUtils
					.getAncestorElement(findMatchFromElement(EMFCompareEObjectUtils
							.getLeftElement(diff)));

		final IModelContentMergeViewerTab currentTab = tabs.get(tabFolder
				.getSelectionIndex());

		currentTab.showElements(diffs);

		properties.setReflectiveInput(findMatchFromElement(target));

		parentViewer.getConfiguration().setProperty(
				EMFCompareConstants.PROPERTY_CONTENT_SELECTION, diff);
		parentViewer.updateCenter();
		// We'll assume the tree has been expanded or collapsed during the
		// process
		expanded = true;

	}

	/**
	 * Removes the given listener from this folder's listeners list. This will
	 * have no effect if the listener is not registered against this folder.
	 * 
	 * @param listener
	 *            The listener to remove from this folder.
	 */
	public void removeCompareEditorPartListener(
			ICompareEditorPartListener listener) {
		editorPartListeners.remove(listener);
	}

	/**
	 * Sets the receiver's size and location to the rectangular area specified
	 * by the arguments.
	 * 
	 * @param x
	 *            Desired x coordinate of the part.
	 * @param y
	 *            Desired y coordinate of the part.
	 * @param width
	 *            Desired width of the part.
	 * @param height
	 *            Desired height of the part.
	 */
	public void setBounds(int x, int y, int width, int height) {
		setBounds(new Rectangle(x, y, width, height));
	}

	/**
	 * Sets the receiver's size and location to given rectangular area.
	 * 
	 * @param bounds
	 *            Desired bounds for this receiver.
	 */
	public void setBounds(Rectangle bounds) {
		tabFolder.setBounds(bounds);
		resizeBounds();
	}

	/**
	 * Sets the input of this viewer part.
	 * 
	 * @param input
	 *            New input of this viewer part.
	 */
	public void setInput(Object input) {
		final IModelContentMergeViewerTab currentTab = tabs.get(tabFolder
				.getSelectionIndex());
		if (currentTab == properties)
			currentTab
					.setReflectiveInput(findMatchFromElement((EObject) input));
		else {
			diagram.setReflectiveInput(input);
			tree.setReflectiveInput(input);
		}
	}

	/**
	 * Changes the current tab.
	 * 
	 * @param index
	 *            New tab to set selected.
	 */
	public void setSelectedTab(int index) {

		tabFolder.setSelection(index);

		final IModelContentMergeViewerTab currentTab = tabs.get(tabFolder
				.getSelectionIndex());
		if (partSide == EMFCompareConstants.LEFT) {
			if (currentTab == diagram) {
				ModelContentMergeViewer.drawDiffMarkers = false;
			} else {
				boolean draw = EMFCompareUIPlugin
						.getDefault()
						.getPluginPreferences()
						.getBoolean(
								EMFCompareConstants.PREFERENCES_KEY_DRAW_DIFFERENCES);
				ModelContentMergeViewer.drawDiffMarkers = draw;
			}
		}
		resizeBounds();
	}

	/**
	 * Creates the contents of this viewer part given its parent composite. The
	 * content is one tab for the diagram, one for the structural compare and
	 * one for the properties.
	 * 
	 * @param composite
	 *            Parent composite of this viewer parts's widgets.
	 */
	protected void createContents(Composite composite) {
		tabFolder = new CTabFolder(composite, SWT.BOTTOM);

		final CTabItem diagramTab = new CTabItem(tabFolder, SWT.NONE);
		diagramTab.setText(KivikUIMessages
				.getString("ModelContentMergeTabFolder.diagram")); //$NON-NLS-1$

		final CTabItem treeTab = new CTabItem(tabFolder, SWT.NONE);
		treeTab.setText(KivikUIMessages
				.getString("ModelContentMergeTabFolder.differences")); //$NON-NLS-1$

		final CTabItem propertiesTab = new CTabItem(tabFolder, SWT.NONE);
		propertiesTab.setText(KivikUIMessages
				.getString("ModelContentMergeTabFolder.properties")); //$NON-NLS-1$

		final Composite treePanel = new Composite(tabFolder, SWT.NONE);
		treePanel.setLayout(new GridLayout());
		treePanel.setLayoutData(new GridData(GridData.FILL_BOTH));
		treePanel.setFont(composite.getFont());
		tree = createTreePart(treePanel);
		treeTab.setControl(treePanel);

		final Composite propertyPanel = new Composite(tabFolder, SWT.NONE);
		propertyPanel.setLayout(new GridLayout());
		propertyPanel.setLayoutData(new GridData(GridData.FILL_BOTH));
		propertyPanel.setFont(composite.getFont());
		properties = createPropertiesPart(propertyPanel);
		propertiesTab.setControl(propertyPanel);

		final Composite diagramPanel = new Composite(tabFolder, SWT.NONE);
		diagramPanel.setLayout(new GridLayout());
		diagramPanel.setLayoutData(new GridData(GridData.FILL_BOTH));
		diagramPanel.setFont(composite.getFont());
		diagram = createDiagramPart(diagramPanel);
		diagramTab.setControl(diagramPanel);

		tabs.add(diagram);
		tabs.add(tree);
		tabs.add(properties);

		tabFolder.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				setSelectedTab(tabFolder.getSelectionIndex());
				fireSelectedtabChanged();
			}
		});

		tabFolder.setSelection(diagramTab);
	}

	/**
	 * Returns the {@link Match2Elements} containing the given {@link EObject}
	 * as its left or right element.
	 * 
	 * @param element
	 *            Element we seek the {@link Match2Elements} for.
	 * @return The {@link Match2Elements} containing the given {@link EObject}
	 *         as its left or right element.
	 */
	public EObject findMatchFromElement(EObject element) {
		EObject theElement = null;
		final MatchModel match = ((ComparisonResourceSnapshot) parentViewer.getInput())
				.getMatch();

		final TreeIterator<EObject> iterator = match.eAllContents();
		while (iterator.hasNext()) {
			final Object object = iterator.next();

			if (object instanceof Match3Elements) {
				final Match3Elements matchElement = (Match3Elements) object;
				if (element.equals(matchElement.getLeftElement())
						|| element.equals(matchElement.getRightElement())
						|| element.equals(matchElement.getOriginElement())) {
					theElement = matchElement;
				}
			} else if (object instanceof Match2Elements) {
				final Match2Elements matchElement = (Match2Elements) object;
				if (matchElement.getLeftElement().equals(element)
						|| matchElement.getRightElement().equals(element)) {
					theElement = matchElement;
				}
			} else if (object instanceof UnmatchElement) {
				final UnmatchElement unMatchElement = (UnmatchElement) object;
				if (unMatchElement.getElement().equals(element)) {
					theElement = unMatchElement;
				}
			}
		}

		return theElement;
	}

	/**
	 * Notifies All {@link ICompareEditorPartListener listeners} registered for
	 * this viewer part that the tab selection has been changed.
	 */
	protected void fireSelectedtabChanged() {
		for (ICompareEditorPartListener listener : editorPartListeners) {
			listener.selectedTabChanged(tabFolder.getSelectionIndex());
		}
	}

	/**
	 * Notifies All {@link ICompareEditorPartListener listeners} registered for
	 * this viewer part that the user selection has changed on the properties or
	 * tree tab.
	 * 
	 * @param event
	 *            Source {@link SelectionChangedEvent Selection changed event}
	 *            of the notification.
	 */
	protected void fireSelectionChanged(SelectionChangedEvent event) {
		for (ICompareEditorPartListener listener : editorPartListeners) {
			listener.selectionChanged(event);
		}
	}

	/**
	 * Notifies All {@link ICompareEditorPartListener listeners} registered for
	 * this viewer part that the center part needs to be refreshed.
	 */
	protected void fireUpdateCenter() {
		for (ICompareEditorPartListener listener : editorPartListeners) {
			listener.updateCenter();
		}
	}

	/**
	 * This will resize the tabs displayed by this content merge viewer.
	 */
	protected void resizeBounds() {
		tabs.get(tabFolder.getSelectionIndex()).getControl().setBounds(
				tabFolder.getClientArea());
	}

	/**
	 * Handles the creation of the properties tab of this viewer part given the
	 * parent {@link Composite} under which to create it.
	 * 
	 * @param composite
	 *            Parent {@link Composite} of the table to create.
	 * @return The properties part displayed by this viewer part's properties
	 *         tab.
	 */
	private IModelContentMergeViewerTab createPropertiesPart(Composite composite) {
		final IModelContentMergeViewerTab propertiesPart = new ModelContentMergePropertyTab(
				composite, partSide, this);

		((Scrollable) propertiesPart.getControl()).getVerticalBar()
				.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}

					public void widgetSelected(SelectionEvent e) {
						parentViewer.updateCenter();
					}
				});

		propertiesPart
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						fireSelectionChanged(event);
					}
				});

		return propertiesPart;
	}

	/**
	 * Handles the creation of the tree tab of this viewer part given the parent
	 * {@link Composite} under which to create it.
	 * 
	 * @param composite
	 *            Parent {@link Composite} of the tree to create.
	 * @return The tree part displayed by this viewer part's tree tab.
	 */
	private IModelContentMergeViewerTab createTreePart(Composite composite) {
		final IModelContentMergeViewerTab treePart = new ModelContentMergeDiffTab(
				composite, partSide, this);

		((Scrollable) treePart.getControl()).getVerticalBar()
				.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}

					public void widgetSelected(SelectionEvent e) {
						fireUpdateCenter();
					}
				});

		((Tree) treePart.getControl()).addTreeListener(new TreeListener() {
			public void treeCollapsed(TreeEvent e) {
				((TreeItem) e.item).setExpanded(false);
				e.doit = false;
				parentViewer.update();
				expanded = true;
			}

			public void treeExpanded(TreeEvent e) {
				((TreeItem) e.item).setExpanded(true);
				e.doit = false;
				parentViewer.update();
				expanded = true;
			}
		});

		treePart.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				fireSelectionChanged(event);
			}
		});

		((Tree) treePart.getControl())
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (tree.getSelectedElements().size() > 0
								&& tree.getSelectedElements().get(0) instanceof Item) {
							final Item selected = (Item) tree
									.getSelectedElements().get(0);
							for (final DiffElement diff : ((ModelCompareInput) parentViewer
									.getInput()).getDiffAsList()) {
								if (!(diff instanceof DiffGroup)
										&& partSide == EMFCompareConstants.LEFT) {
									if (selected.getData().equals(
											EMFCompareEObjectUtils
													.getLeftElement(diff)))
										parentViewer.setSelection(diff);
								} else if (!(diff instanceof DiffGroup)
										&& partSide == EMFCompareConstants.RIGHT) {
									if (selected.getData().equals(
											EMFCompareEObjectUtils
													.getRightElement(diff)))
										parentViewer.setSelection(diff);
								}
							}
							if (!selected.isDisposed()
									&& selected.getData() instanceof EObject)
								properties
										.setReflectiveInput(findMatchFromElement((EObject) selected
												.getData()));
						}
					}
				});

		return treePart;
	}

	/**
	 * Handles the creation of the diagram tab of this viewer part given the
	 * parent {@link Composite} under which to create it. Attaches several
	 * listeners to notify the left and top part of the whole compare view.
	 * 
	 * @param composite
	 *            Parent {@link Composite} of the tree to create.
	 * @return The diagram part displayed by this viewer part's diagram tab.
	 */
	private IModelContentMergeViewerTab createDiagramPart(Composite composite) {

		final IModelContentMergeViewerTab diagramPart = new ModelContentMergeDiagramTab(
				composite, partSide, this);

		((Scrollable) diagramPart.getControl()).getVerticalBar()
				.addSelectionListener(new SelectionListener() {
					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}

					public void widgetSelected(SelectionEvent e) {
						parentViewer.updateCenter();
					}
				});

		diagramPart
				.addSelectionChangedListener(new ISelectionChangedListener() {

					IStructuredSelection oldSelection = new StructuredSelection();

					public void selectionChanged(SelectionChangedEvent event) {
						// do this check to prevent listener loops
						if (((StructuredSelection) event.getSelection())
								.equals(oldSelection)
								|| ((DiagramGraphicalViewer) diagramPart)
										.getContents().equals(
												((StructuredSelection) event
														.getSelection())
														.getFirstElement()))
							return;
						else {
							oldSelection = (IStructuredSelection) event
									.getSelection();
							fireSelectionChanged(event);
							graphicalEditPartsSelected(event);
						}

					}

					private void graphicalEditPartsSelected(
							SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						DiffElement newSelection = null;

						AbstractGraphicalEditPart selectedEditPart = (AbstractGraphicalEditPart) selection
								.getFirstElement();

						boolean found = false;

						Iterator<ModelContentMergeTabObject> it = diagramPart
								.getVisibleElements().iterator();
						Object actualObject;
						while (it.hasNext()) {
							actualObject = it.next().getActualObject();
							if (actualObject.equals(selectedEditPart)) {
								found = true;
								break;
							}
						}
						if (!found)
							return;

						final TreeIterator<EObject> diffIterator = getDiffModel()
								.eAllContents();

						while (diffIterator.hasNext()) {
							EObject next = diffIterator.next();
							if (next instanceof DiffGroup) {
								DiffGroup diffGroup = (DiffGroup) next;
								if (partSide == EMFCompareConstants.LEFT
										&& ((View) selectedEditPart.getModel())
												.getElement()
												.equals(
														EMFCompareEObjectUtils
																.getLeftElement(diffGroup))) {
									newSelection = diffGroup;
									break;
								} else if (partSide == EMFCompareConstants.RIGHT) {
									EObject matchElements = findMatchFromElement(EMFCompareEObjectUtils
											.getLeftElement(diffGroup));
									if (matchElements instanceof Match2Elements
											&& ((View) selectedEditPart
													.getModel())
													.getElement()
													.equals(
															((Match2Elements) matchElements)
																	.getRightElement())) {
										newSelection = diffGroup;
										break;
									}
								}

							}
							if (next instanceof DiffElement) {
								DiffElement diffElement = (DiffElement) next;
								if (partSide == EMFCompareConstants.LEFT
										&& ((View) selectedEditPart.getModel())
												.getElement()
												.equals(
														EMFCompareEObjectUtils
																.getLeftElement(diffElement))) {
									newSelection = diffElement;
									break;
								} else if (partSide == EMFCompareConstants.RIGHT
										&& ((View) selectedEditPart.getModel())
												.getElement()
												.equals(
														EMFCompareEObjectUtils
																.getRightElement(diffElement))) {
									newSelection = diffElement;
									break;
								}
							}
						}

						// set the selection in the parent viewer
						parentViewer.setSelection(newSelection);

					}
				});

		return diagramPart;
	}
}
