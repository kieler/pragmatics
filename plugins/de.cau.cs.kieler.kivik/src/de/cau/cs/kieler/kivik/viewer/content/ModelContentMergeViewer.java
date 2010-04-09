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
package de.cau.cs.kieler.kivik.viewer.content;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareViewerPane;
import org.eclipse.compare.HistoryItem;
import org.eclipse.compare.contentmergeviewer.ContentMergeViewer;
import org.eclipse.compare.contentmergeviewer.IMergeViewerContentProvider;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.emf.compare.EMFComparePlugin;
import org.eclipse.emf.compare.diff.metamodel.DiffElement;
import org.eclipse.emf.compare.diff.metamodel.DiffGroup;
import org.eclipse.emf.compare.diff.metamodel.ComparisonResourceSnapshot;
import org.eclipse.emf.compare.diff.metamodel.DiffModel;
import org.eclipse.emf.compare.diff.metamodel.util.DiffAdapterFactory;
import org.eclipse.emf.compare.match.metamodel.MatchModel;
import org.eclipse.emf.compare.ui.EMFCompareUIMessages;
import org.eclipse.emf.compare.ui.EMFCompareUIPlugin;
import org.eclipse.emf.compare.ui.ICompareEditorPartListener;
import org.eclipse.emf.compare.ui.ModelCompareInput;
import org.eclipse.emf.compare.ui.TypedElementWrapper;
import org.eclipse.emf.compare.ui.util.EMFCompareConstants;
import org.eclipse.emf.compare.util.EMFCompareMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;

import de.cau.cs.kieler.kivik.internal.KivikComparator;
import de.cau.cs.kieler.kivik.viewer.content.part.AbstractCenterPart;
import de.cau.cs.kieler.kivik.viewer.content.part.IModelContentMergeViewerTab;
import de.cau.cs.kieler.kivik.viewer.content.part.ModelContentMergeTabFolder;
import de.cau.cs.kieler.kivik.viewer.content.part.ModelContentMergeTabObject;


/**
 * Compare and merge viewer with two side-by-side content areas and an optional
 * content area for the ancestor. getKind
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ModelContentMergeViewer extends ContentMergeViewer {
	/** Name of the bundle resources property file. */
	public static final String BUNDLE_NAME = "de.cau.cs.kieler.kivik.emfcompare.content.ModelMergeViewerResources"; //$NON-NLS-1$

	/** Width to affect to the center area. */
	public static final int CENTER_WIDTH = 34;

	/** Keeps references to the colors to use when drawing differences markers. */
	/* package */static Map<String, RGB> colors = new EMFCompareMap<String, RGB>();

	/**
	 * Indicates that the diff markers should be drawn. This allows defining a
	 * threshold to avoid too long drawing times.
	 */
	public static boolean drawDiffMarkers;

	/** Keeps track of the currently selected tab for this viewer part. */
	protected int activeTabIndex;

	/** Ancestor part of the three possible parts of this content viewer. */
	protected ModelContentMergeTabFolder ancestorPart;

	/** Keeps track of the current diff Selection. */
	protected final List<DiffElement> currentSelection = new ArrayList<DiffElement>();

	/** Left of the three possible parts of this content viewer. */
	protected ModelContentMergeTabFolder leftPart;

	/** Right of the three possible parts of this content viewer. */
	protected ModelContentMergeTabFolder rightPart;

	/**
	 * this is the "center" part of the content merge viewer where we handle all
	 * the drawing operations.
	 */
	private AbstractCenterPart canvas;

	/**
	 * {@link CompareConfiguration} controls various aspect of the GUI elements.
	 * This will keep track of the one used to created this compare editor.
	 */
	private final CompareConfiguration configuration;

	

	/** Indicates that this is a three-way comparison. */
	private boolean isThreeWay;

	/**
	 * Used for history comparisons, this will keep track of modification time
	 * of the last {@link HistoryItem} we compared.
	 */
	private long lastHistoryItemDate;

	/**
	 * This listener will be registered for notifications against all tab
	 * folders.
	 */
	private EditorPartListener partListener;

	/**
	 * This will listen for changes made on this plug-in's
	 * {@link PreferenceStore} to update the GUI colors as needed.
	 */
	private final IPropertyChangeListener preferenceListener;

	/**
	 * This will listen for changes of the {@link CompareConfiguration}
	 * concerning the structure's input and selection.
	 */
	private final IPropertyChangeListener structureSelectionListener;

	/**
	 * Creates a new model content merge viewer and intializes it.
	 * 
	 * @param parent
	 *            Parent composite for this viewer.
	 * @param config
	 *            The configuration object.
	 */
	public ModelContentMergeViewer(Composite parent, CompareConfiguration config) {
		super(SWT.NONE, null, config);
		configuration = config;
		buildControl(parent);
		updatePreferences();
		setContentProvider(new ModelContentMergeContentProvider(config));

		structureSelectionListener = new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(
						EMFCompareConstants.PROPERTY_STRUCTURE_SELECTION)) {
					final List<?> elements = (List<?>) event.getNewValue();
					// We'll remove all diffgroups without subDiffs from the
					// selection
					final List<DiffElement> selectedDiffs = new ArrayList<DiffElement>();
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i) instanceof DiffElement
								&& !(elements.get(i) instanceof DiffGroup && ((DiffGroup) elements
										.get(i)).getSubDiffElements().size() == 0))
							selectedDiffs.add((DiffElement) elements.get(i));
					}
					setSelection(selectedDiffs);
				}
			}
		};
		configuration.addPropertyChangeListener(structureSelectionListener);

		preferenceListener = new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().matches(".*(color|differences)")) { //$NON-NLS-1$
					updatePreferences();
				}
			}
		};
		EMFCompareUIPlugin.getDefault().getPreferenceStore()
				.addPropertyChangeListener(preferenceListener);
	}

	/**
	 * Returns the color identified by the given key in {@link #colors}.
	 * 
	 * @param key
	 *            Key of the color to return.
	 * @return The color identified by the given key in the map.
	 */
	public static final RGB getColor(String key) {
		return colors.get(key);
	}

	/**
	 * Returns <code>True</code> if the trees and center have to draw markers
	 * over the differences.
	 * 
	 * @return <code>True</code> if the trees and center have to draw markers
	 *         over the differences, <code>False</code> otherwise.
	 */
	public static boolean shouldDrawDiffMarkers() {
		return drawDiffMarkers;
	}

	/**
	 * Returns the center {@link Canvas} appearing between the viewer parts.
	 * 
	 * @return The center {@link Canvas}.
	 */
	public Canvas getCenterPart() {
		if (canvas == null && !getControl().isDisposed())
			canvas = new AbstractCenterPart((Composite) getControl()) {
				@Override
				public void doPaint(GC gc) {
					if (!ModelContentMergeViewer.shouldDrawDiffMarkers()
							|| getInput() == null)
						return;
					final List<DiffElement> diffList = new ArrayList<DiffElement>(
							((ModelCompareInput) getInput()).getDiffAsList());
					final List<ModelContentMergeTabObject> leftVisible = leftPart
							.getVisibleElements();
					final List<ModelContentMergeTabObject> rightVisible = rightPart
							.getVisibleElements();
					diffList.removeAll(currentSelection);
					final List<DiffElement> visibleDiffs = retainVisibleDiffs(
							diffList, leftVisible, rightVisible);
					// we don't clear selection when the last diff is merged so
					// this could happen
					if (currentSelection.size() > 0
							&& currentSelection.get(0) != null
							&& currentSelection.get(0).eContainer() != null)
						visibleDiffs.addAll(currentSelection);
					for (final DiffElement diff : visibleDiffs) {
						if (!(diff instanceof DiffGroup)) {
							final ModelContentMergeTabObject leftUIItem = leftPart
									.getUIItem(diff);
							final ModelContentMergeTabObject rightUIItem = rightPart
									.getUIItem(diff);
							if (leftUIItem != null && rightUIItem != null)
								drawLine(gc, leftUIItem, rightUIItem);
						}
					}
				}
			};
		if (canvas != null)
			canvas.moveAbove(null);
		return canvas;
	}

	/**
	 * Returns the compare configuration of this viewer.
	 * 
	 * @return The compare configuration of this viewer.
	 */
	public CompareConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInput(Object input) {
		// We won't compare again if the given input is the same as the last.
		boolean changed = false;
		if (input instanceof ICompareInput
				&& ((ICompareInput) input).getAncestor() != null)
			isThreeWay = true;
		if (input instanceof ICompareInput
				&& ((ICompareInput) input).getRight() instanceof HistoryItem) {
			changed = lastHistoryItemDate != ((HistoryItem) ((ICompareInput) input)
					.getRight()).getModificationDate();
			if (changed)
				lastHistoryItemDate = ((HistoryItem) ((ICompareInput) input)
						.getRight()).getModificationDate();
		}
		final KivikComparator kivikComparator = KivikComparator
				.getKivikComparator(configuration);
		if (kivikComparator.getDomainComparisonResult() != null && !changed) {
			final ComparisonResourceSnapshot snapshot = kivikComparator
					.getDomainComparisonResult();
                        final ModelCompareInput mci = new ModelCompareInput((MatchModel)snapshot.getMatch(), (DiffModel)snapshot
                                .getDiff(), kivikComparator.getComparator());
			super.setInput(mci);

		} else if (input instanceof ModelCompareInput) {
			final ModelCompareInput snapshot = (ModelCompareInput) input;
			super.setInput(new ModelCompareInput((MatchModel)snapshot.getMatch(), (DiffModel)snapshot
					.getDiff(), kivikComparator.getComparator()));

		} else if (input instanceof ICompareInput) {
			kivikComparator.loadResources((ICompareInput) input);
			final ComparisonResourceSnapshot snapshot = kivikComparator
					.compareDomainModel();
			super.setInput(new ModelCompareInput((MatchModel)snapshot.getMatch(), (DiffModel)snapshot
					.getDiff(), kivikComparator.getComparator()));
		}
	}

	/**
	 * Sets the parts' tree selection given the {@link DiffElement} to select.
	 * 
	 * @param diff
	 *            {@link DiffElement} backing the current selection.
	 */
	public void setSelection(DiffElement diff) {
		final List<DiffElement> diffs = new ArrayList<DiffElement>();
		diffs.add(diff);
		setSelection(diffs);
	}

	/**
	 * Sets the parts' tree selection given the list of {@link DiffElement}s to
	 * select.
	 * 
	 * @param diffs
	 *            {@link DiffElement} backing the current selection.
	 */
	public void setSelection(List<DiffElement> diffs) {
		currentSelection.clear();
		if (diffs.size() > 0) {
			currentSelection.addAll(diffs);
			if (leftPart != null)
				leftPart.navigateToDiff(diffs);
			if (rightPart != null)
				rightPart.navigateToDiff(diffs);
			if (isThreeWay)
				ancestorPart.navigateToDiff(diffs.get(0));
		}
	}

	/**
	 * Redraws this viewer.
	 */
	public void update() {
		if (isThreeWay)
			ancestorPart.layout();
		rightPart.layout();
		leftPart.layout();
		updateCenter();
		updateToolItems();
	}

	/**
	 * Redraws the center Control.
	 */
	public void updateCenter() {
		if (getCenterPart() != null)
			getCenterPart().redraw();
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see ContentMergeViewer#createControls(Composite)
	 */
	@Override
	protected void createControls(Composite composite) {
		leftPart = new ModelContentMergeTabFolder(this, composite,
				EMFCompareConstants.LEFT);
		rightPart = new ModelContentMergeTabFolder(this, composite,
				EMFCompareConstants.RIGHT);
		ancestorPart = new ModelContentMergeTabFolder(this, composite,
				EMFCompareConstants.ANCESTOR);

		partListener = new EditorPartListener(leftPart, rightPart, ancestorPart);
		leftPart.addCompareEditorPartListener(partListener);
		rightPart.addCompareEditorPartListener(partListener);
		ancestorPart.addCompareEditorPartListener(partListener);

		createPropertiesSyncHandlers(leftPart, rightPart, ancestorPart);
		createTreeSyncHandlers(leftPart, rightPart, ancestorPart);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ContentMergeViewer#createToolItems(ToolBarManager)
	 */
	@Override
	protected void createToolItems(ToolBarManager tbm) {
		tbm.removeAll();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.Viewer#fireSelectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	protected void fireSelectionChanged(final SelectionChangedEvent event) {
		super.fireSelectionChanged(event);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ContentMergeViewer#getContents(boolean)
	 */
	@Override
	protected byte[] getContents(boolean left) {
		byte[] contents = null;

		EObject root = ((TypedElementWrapper) ((IMergeViewerContentProvider) getContentProvider())
				.getLeftContent(getInput())).getObject();
		if (!left)
			root = ((TypedElementWrapper) ((IMergeViewerContentProvider) getContentProvider())
					.getRightContent(getInput())).getObject();

		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			root.eResource().save(stream, null);
			contents = stream.toByteArray();
		} catch (IOException e) {
			EMFComparePlugin.log(e, false);
		}

		return contents;
	}

	/**
	 * This will minimize the list of differences to the visible differences.
	 * Differences are considered "visible" if
	 * {@link DiffAdapterFactory#shouldBeHidden(EObject)} returns false on it.
	 * 
	 * @return {@link List} of the visible differences for this comparison.
	 */
	protected List<DiffElement> getVisibleDiffs() {
		final List<DiffElement> diffs = ((ModelCompareInput) getInput())
				.getDiffAsList();
		final List<DiffElement> visibleDiffs = new ArrayList<DiffElement>(diffs
				.size());

		for (int i = 0; i < diffs.size(); i++) {
			if (!DiffAdapterFactory.shouldBeHidden(diffs.get(i)))
				visibleDiffs.add(diffs.get(i));
		}

		return visibleDiffs;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.compare.contentmergeviewer.ContentMergeViewer#handleDispose(org.eclipse.swt.events.DisposeEvent)
	 */
	@Override
	protected void handleDispose(DisposeEvent event) {
		super.handleDispose(event);
		configuration.removePropertyChangeListener(structureSelectionListener);
		EMFCompareUIPlugin.getDefault().getPreferenceStore()
				.removePropertyChangeListener(preferenceListener);
		leftPart.removeCompareEditorPartListener(partListener);
		leftPart.dispose();
		leftPart = null;
		rightPart.removeCompareEditorPartListener(partListener);
		rightPart.dispose();
		rightPart = null;
		ancestorPart.removeCompareEditorPartListener(partListener);
		ancestorPart.dispose();
		ancestorPart = null;
		canvas.dispose();
		canvas = null;
		currentSelection.clear();
		KivikComparator.removeKivikComparator(configuration);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ContentMergeViewer#handleResizeAncestor(int, int, int, int)
	 */
	@Override
	protected void handleResizeAncestor(int x, int y, int width, int height) {
		ancestorPart.setBounds(x, y, width, height);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ContentMergeViewer#handleResizeLeftRight(int, int, int, int)
	 */
	@Override
	protected void handleResizeLeftRight(int x, int y, int leftWidth,
			int centerWidth, int rightWidth, int height) {
		if (getCenterPart() != null)
			getCenterPart().setBounds(leftWidth - (CENTER_WIDTH / 2), y,
					CENTER_WIDTH, height);
		leftPart.setBounds(x, y, leftWidth - (CENTER_WIDTH / 2), height);
		rightPart.setBounds(x + leftWidth + (CENTER_WIDTH / 2), y, rightWidth
				- (CENTER_WIDTH / 2), height);
		update();
	}

	/**
	 * Selects the next or previous {@link DiffElement} as compared to the
	 * currently selected one.
	 * 
	 * @param down
	 *            <code>True</code> if we seek the next {@link DiffElement},
	 *            <code>False</code> for the previous.
	 */
	protected void navigate(boolean down) {
		final List<DiffElement> diffs = getVisibleDiffs();
		if (diffs.size() != 0) {
			final DiffElement theDiff;
			if (currentSelection.size() > 0
					&& !(currentSelection.get(0) instanceof DiffGroup))
				theDiff = currentSelection.get(0);
			else if (diffs.size() == 1)
				theDiff = diffs.get(0);
			else if (down)
				theDiff = diffs.get(diffs.size() - 1);
			else
				theDiff = diffs.get(1);
			for (int i = 0; i < diffs.size(); i++) {
				if (diffs.get(i).equals(theDiff) && down) {
					DiffElement next = diffs.get(0);
					if (diffs.size() > i + 1) {
						next = diffs.get(i + 1);
					}
					if (next != null
							&& !DiffAdapterFactory.shouldBeHidden(next)) {
						setSelection(next);
						break;
					}
				} else if (diffs.get(i).equals(theDiff) && !down) {
					DiffElement previous = diffs.get(diffs.size() - 1);
					if (i > 0) {
						previous = diffs.get(i - 1);
					}
					if (previous != null
							&& !DiffAdapterFactory.shouldBeHidden(previous)) {
						setSelection(previous);
						break;
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see ContentMergeViewer#updateContent(Object, Object, Object)
	 */
	@Override
	protected void updateContent(Object ancestor, Object left, Object right) {
		Object ancestorObject = ancestor;
		Object leftObject = left;
		Object rightObject = right;
		if (ancestorObject instanceof TypedElementWrapper)
			ancestorObject = ((TypedElementWrapper) ancestorObject).getObject();
		if (leftObject instanceof TypedElementWrapper)
			leftObject = ((TypedElementWrapper) leftObject).getObject();
		if (rightObject instanceof TypedElementWrapper)
			rightObject = ((TypedElementWrapper) rightObject).getObject();

		if (ancestorObject != null)
			ancestorPart.setInput(ancestorObject);
		if (leftObject != null)
			leftPart.setInput(leftObject);
		if (rightObject != null)
			rightPart.setInput(rightObject);
		update();
	}

	/**
	 * Updates the values of all the variables using preferences values.
	 */
	protected void updatePreferences() {
		final IPreferenceStore comparePreferences = EMFCompareUIPlugin
				.getDefault().getPreferenceStore();
		updateColors(comparePreferences);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.compare.contentmergeviewer.ContentMergeViewer#updateToolItems()
	 */
	@Override
	protected void updateToolItems() {
		super.updateToolItems();
		CompareViewerPane.getToolBarManager(getControl().getParent()).update(
				true);
	}

	/**
	 * Takes care of the creation of the synchronization handlers for the
	 * properties tab of our viewer parts.
	 * 
	 * @param parts
	 *            The other parts to synchronize with.
	 */
	private void createPropertiesSyncHandlers(
			ModelContentMergeTabFolder... parts) {
		if (parts.length < 2)
			throw new IllegalArgumentException(EMFCompareUIMessages
					.getString("ModelContentMergeViewer.illegalSync")); //$NON-NLS-1$

		// horizontal synchronization
		handleHSync(leftPart.getPropertyPart(), rightPart.getPropertyPart(),
				ancestorPart.getPropertyPart());
		handleHSync(ancestorPart.getPropertyPart(),
				rightPart.getPropertyPart(), leftPart.getPropertyPart());
		handleHSync(rightPart.getPropertyPart(), leftPart.getPropertyPart(),
				ancestorPart.getPropertyPart());
		// Vertical synchronization
		handleVSync(leftPart.getPropertyPart(), rightPart.getPropertyPart(),
				ancestorPart.getPropertyPart());
		handleVSync(rightPart.getPropertyPart(), leftPart.getPropertyPart(),
				ancestorPart.getPropertyPart());
		handleVSync(ancestorPart.getPropertyPart(),
				rightPart.getPropertyPart(), leftPart.getPropertyPart());
	}

	/**
	 * Takes care of the creation of the synchronization handlers for the tree
	 * tab of our viewer parts.
	 * 
	 * @param parts
	 *            The other parts to synchronize with.
	 */
	private void createTreeSyncHandlers(ModelContentMergeTabFolder... parts) {
		if (parts.length < 2)
			throw new IllegalArgumentException(EMFCompareUIMessages
					.getString("ModelContentMergeViewer.illegalSync")); //$NON-NLS-1$

		handleHSync(leftPart.getTreePart(), rightPart.getTreePart(),
				ancestorPart.getTreePart());
		handleHSync(rightPart.getTreePart(), leftPart.getTreePart(),
				ancestorPart.getTreePart());
		handleHSync(ancestorPart.getTreePart(), rightPart.getTreePart(),
				leftPart.getTreePart());
	}

	/**
	 * Allows synchronization of the properties viewports horizontal scrolling.
	 * 
	 * @param parts
	 *            The other parts to synchronize with.
	 */
	private void handleHSync(IModelContentMergeViewerTab... parts) {
		// inspired from TreeMergeViewer#hsynchViewport
		final Scrollable scroll1 = (Scrollable) parts[0].getControl();
		final Scrollable scroll2 = (Scrollable) parts[1].getControl();
		final Scrollable scroll3;
		if (parts.length > 2)
			scroll3 = (Scrollable) parts[2].getControl();
		else
			scroll3 = null;
		final ScrollBar scrollBar1 = scroll1.getHorizontalBar();

		scrollBar1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int max = scrollBar1.getMaximum() - scrollBar1.getThumb();
				double v = 0.0;
				if (max > 0)
					v = (double) scrollBar1.getSelection() / (double) max;
				if (scroll2.isVisible()) {
					final ScrollBar scrollBar2 = scroll2.getHorizontalBar();
					scrollBar2
							.setSelection((int) ((scrollBar2.getMaximum() - scrollBar2
									.getThumb()) * v));
				}
				if (scroll3 != null && scroll3.isVisible()) {
					final ScrollBar scrollBar3 = scroll3.getHorizontalBar();
					scrollBar3
							.setSelection((int) ((scrollBar3.getMaximum() - scrollBar3
									.getThumb()) * v));
				}
				if (SWT.getPlatform().equals("carbon") && getControl() != null //$NON-NLS-1$
						&& !getControl().isDisposed()) {
					getControl().getDisplay().update();
				}
			}
		});
	}

	/**
	 * Allows synchronization of the viewports vertical scrolling.
	 * 
	 * @param parts
	 *            The other parts to synchronize with.
	 */
	private void handleVSync(IModelContentMergeViewerTab... parts) {
		// inspired from TreeMergeViewer#hsynchViewport
		final Scrollable table1 = (Scrollable) parts[0].getControl();
		final Scrollable table2 = (Scrollable) parts[1].getControl();
		final Scrollable table3;
		if (parts.length > 2)
			table3 = (Scrollable) parts[2].getControl();
		else
			table3 = null;
		final ScrollBar scrollBar1 = table1.getVerticalBar();

		scrollBar1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int max = scrollBar1.getMaximum() - scrollBar1.getThumb();
				double v = 0.0;
				if (max > 0)
					v = (double) scrollBar1.getSelection() / (double) max;
				if (table2.isVisible()) {
					final ScrollBar scrollBar2 = table2.getVerticalBar();
					scrollBar2
							.setSelection((int) ((scrollBar2.getMaximum() - scrollBar2
									.getThumb()) * v));
				}
				if (table3 != null && table3.isVisible()) {
					final ScrollBar scrollBar3 = table3.getVerticalBar();
					scrollBar3
							.setSelection((int) ((scrollBar3.getMaximum() - scrollBar3
									.getThumb()) * v));
				}
				if (SWT.getPlatform().equals("carbon") && getControl() != null //$NON-NLS-1$
						&& !getControl().isDisposed()) {
					getControl().getDisplay().update();
				}
			}
		});
	}

	/**
	 * Updates the value of the colors as they are changed on the preference
	 * page.
	 * 
	 * @param comparePreferences
	 *            Preference store where to retrieve our values.
	 */
	private void updateColors(IPreferenceStore comparePreferences) {
		final RGB highlightColor = PreferenceConverter.getColor(
				comparePreferences,
				EMFCompareConstants.PREFERENCES_KEY_HIGHLIGHT_COLOR);
		final RGB changedColor = PreferenceConverter.getColor(
				comparePreferences,
				EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR);
		final RGB conflictingColor = PreferenceConverter.getColor(
				comparePreferences,
				EMFCompareConstants.PREFERENCES_KEY_CONFLICTING_COLOR);
		final RGB addedColor = PreferenceConverter.getColor(comparePreferences,
				EMFCompareConstants.PREFERENCES_KEY_ADDED_COLOR);
		final RGB removedColor = PreferenceConverter.getColor(
				comparePreferences,
				EMFCompareConstants.PREFERENCES_KEY_REMOVED_COLOR);
		colors.put(EMFCompareConstants.PREFERENCES_KEY_HIGHLIGHT_COLOR,
				highlightColor);
		colors.put(EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR,
				changedColor);
		colors.put(EMFCompareConstants.PREFERENCES_KEY_CONFLICTING_COLOR,
				conflictingColor);
		colors.put(EMFCompareConstants.PREFERENCES_KEY_ADDED_COLOR, addedColor);
		colors.put(EMFCompareConstants.PREFERENCES_KEY_REMOVED_COLOR,
				removedColor);
	}
	
	public String getTitle(){
		return "Visualization of Diagram Differences";
	}
	

	/**
	 * Basic implementation of an {@link ICompareEditorPartListener}.
	 */
	private class EditorPartListener implements ICompareEditorPartListener {
		/** Viewer parts this listener is registered for. */
		private final ModelContentMergeTabFolder[] viewerParts;

		/**
		 * Instantiate this {@link EditorPartListener} given the left, right and
		 * ancestor viewer parts.
		 * 
		 * @param parts
		 *            The viewer parts.
		 */
		public EditorPartListener(ModelContentMergeTabFolder... parts) {
			viewerParts = parts;
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see ICompareEditorPartListener#selectedTabChanged()
		 */
		public void selectedTabChanged(int newIndex) {
			for (int i = 0; i < viewerParts.length; i++) {
				viewerParts[i].setSelectedTab(newIndex);
			}
			updateCenter();
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see ICompareEditorPartListener#selectionChanged()
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			fireSelectionChanged(event);
		}

		/**
		 * {@inheritDoc}
		 * 
		 * @see ICompareEditorPartListener#updateCenter()
		 */
		public void updateCenter() {
			ModelContentMergeViewer.this.updateCenter();
		}
	}


	@Override
	protected void copy(boolean leftToRight) {
		;// nothing implemented yet
	}
}
