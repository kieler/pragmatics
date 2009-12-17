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
package de.cau.cs.kieler.kiml.viewer.views;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.core.kgraph.KGraphPackage;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataPackage;
import de.cau.cs.kieler.kiml.viewer.KimlViewerPlugin;
import de.cau.cs.kieler.kiml.viewer.Messages;
import de.cau.cs.kieler.kiml.viewer.actions.GmfDebugGraphicsAction;
import de.cau.cs.kieler.kiml.viewer.actions.ImageExportAction;
import de.cau.cs.kieler.kiml.viewer.actions.PerformLayoutAction;

/**
 * A viewer for layout graphs.
 * 
 * @author msp
 */
public class LayoutGraphView extends ViewPart {

    /** Constants to identify the different layout graphs. */
    public static final int PRE = 0;
    public static final int POST = 1;
    public static final int COMPLAYOUT = 2;
    public static final int COMPGMF = 3;

    /** the view identifier. */
    public static final String VIEW_ID = "de.cau.cs.kieler.kiml.viewer.layoutGraph"; //$NON-NLS-1$

    /** name of the preference for the last active tab. */
    private static final String PREF_ACTIVE_TAB = "kiml.viewer.activeTab";

    /** the tab folder used to hold the canvas controls. */
    private TabFolder tabFolder;
    /** the canvas used to draw pre-layout graphs. */
    private LayoutGraphCanvas preCanvas;
    /** the canvas used to draw post-layout graphs. */
    private LayoutGraphCanvas postCanvas;
    /** the canvas used to draw XMI resources. */
    private LayoutGraphCanvas xmiCanvas;
    /** a debugging canvas to compare layout with GMF values. */
    private GmfDebugCanvas compareCanvas;
    /**
     * New transparent "window" to display on top of the Eclipse window, i.e.
     * the GMF editor
     */
    TransparentShell transparentShell;

    /**
     * Creates a layout graph view.
     */
    public LayoutGraphView() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public void createPartControl(final Composite parent) {
        // create actions in the view toolbar
        IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        toolBarManager.add(new ImageExportAction(this));
        toolBarManager.add(new GmfDebugGraphicsAction(this));
        toolBarManager.add(new PerformLayoutAction(this));

        // create tab folder for layout graphs
        tabFolder = new TabFolder(parent, SWT.BOTTOM);

        // create canvas for pre-layout
        {
            TabItem preItem = new TabItem(tabFolder, SWT.NONE);
            preItem.setText("Pre-Layout"); //$NON-NLS-1$
            ScrolledComposite preScroller = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
            preItem.setControl(preScroller);
            preCanvas = new LayoutGraphCanvas(preScroller);
            preScroller.setContent(preCanvas);
            preCanvas.setToolTipText(Messages.getString("kiml.viewer.0")); //$NON-NLS-1$
        }

        // create canvas for post-layout
        {
            TabItem postItem = new TabItem(tabFolder, SWT.NONE);
            postItem.setText("Post-Layout"); //$NON-NLS-1$
            ScrolledComposite postScroller = new ScrolledComposite(tabFolder, SWT.H_SCROLL
                    | SWT.V_SCROLL);
            postItem.setControl(postScroller);
            postCanvas = new LayoutGraphCanvas(postScroller);
            postScroller.setContent(postCanvas);
            postCanvas.setToolTipText(Messages.getString("kiml.viewer.1")); //$NON-NLS-1$
        }

        // create canvas for XMI resource
        {
            TabItem xmiItem = new TabItem(tabFolder, SWT.NONE);
            xmiItem.setText("XMI Resource"); //$NON-NLS-1$
            ScrolledComposite xmiScroller = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
            xmiItem.setControl(xmiScroller);
            xmiCanvas = new LayoutGraphCanvas(xmiScroller);
            xmiScroller.setContent(xmiCanvas);
            xmiCanvas.setToolTipText(Messages.getString("kiml.viewer.10"));

            // register a part listener to react to loaded XMI files
            getSite().getPage().addPartListener(new IPartListener() {
                public void partActivated(final IWorkbenchPart part) {
                    if (part instanceof IEditorPart) {
                        IEditorInput editorInput = ((IEditorPart) part).getEditorInput();
                        if (editorInput instanceof IFileEditorInput) {
                            String path = ((IFileEditorInput) editorInput).getFile().getLocation()
                                    .toString();
                            try {
                                // activate the needed Ecore packages
                                @SuppressWarnings("unused")
                                KGraphPackage graphPackage = KGraphPackage.eINSTANCE;
                                @SuppressWarnings("unused")
                                KLayoutDataPackage layoutPackage = KLayoutDataPackage.eINSTANCE;
                                // try to load an Ecore object from the editor
                                // input
                                ResourceSet resourceSet = new ResourceSetImpl();
                                Resource resource = resourceSet.getResource(URI.createFileURI(path),
                                        true);
                                resource.load(Collections.EMPTY_MAP);
                                KNode layoutGraph = (KNode) resource.getContents().get(0);
                                xmiCanvas.setLayoutGraph(layoutGraph);
                            } catch (Exception e) {
                            }
                        }
                    }
                }

                public void partBroughtToTop(final IWorkbenchPart part) {
                }

                public void partClosed(final IWorkbenchPart part) {
                }

                public void partDeactivated(final IWorkbenchPart part) {
                }

                public void partOpened(final IWorkbenchPart part) {
                }
            });
        }

        // create canvas for compare view
        {
            TabItem compareItem = new TabItem(tabFolder, SWT.NONE);
            compareItem.setText("Compare with GMF"); //$NON-NLS-1$
            ScrolledComposite compareScroller = new ScrolledComposite(tabFolder, SWT.H_SCROLL
                    | SWT.V_SCROLL);
            compareItem.setControl(compareScroller);
            compareCanvas = new GmfDebugCanvas(compareScroller);
            compareScroller.setContent(compareCanvas);
            compareCanvas.setToolTipText("Compare actual with GMF layout");
            transparentShell = new TransparentShell(compareCanvas);
        }

        // select the last active tab item
        final IPreferenceStore preferenceStore = KimlViewerPlugin.getDefault().getPreferenceStore();
        int activeTab = preferenceStore.getInt(PREF_ACTIVE_TAB);
        tabFolder.setSelection(activeTab);

        // add selection listener to store the currently active tab item
        tabFolder.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
                preferenceStore.setValue(PREF_ACTIVE_TAB, tabFolder.indexOf((TabItem) e.item));
            }

            public void widgetSelected(final SelectionEvent e) {
                preferenceStore.setValue(PREF_ACTIVE_TAB, tabFolder.indexOf((TabItem) e.item));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        tabFolder.setFocus();

    }

    /**
     * Sets the given layout graph as the displayed graph.
     * 
     * @param layoutGraph layout graph to be displayed
     * @param index graph index
     */
    public void setLayoutGraph(final KNode layoutGraph, final int index) {
        switch (index) {
        case PRE:
            preCanvas.setLayoutGraph(layoutGraph);
            break;
        case POST:
            postCanvas.setLayoutGraph(layoutGraph);
            compareCanvas.setLayoutGraph(layoutGraph);
            break;
        case COMPLAYOUT:
        case COMPGMF:
            compareCanvas.setLayoutGraph(layoutGraph);
        default:
            break;
        }
    }

    /**
     * Retrieves the currently active layout graph canvas.
     * 
     * @return the active layout graph canvas
     */
    public LayoutGraphCanvas getActiveCanvas() {
        int tabIndex = tabFolder.getSelectionIndex();
        if (tabIndex >= 0) {
            return (LayoutGraphCanvas) ((ScrolledComposite) tabFolder.getItem(tabIndex).getControl())
                    .getContent();
        } else {
            return null;
        }
    }

    /**
     * Returns the layout graph that is currently displayed in the XMI canvas,
     * or {@code null} if there is none.
     * 
     * @return the layout graph from the XMI canvas
     */
    public KNode getXmiGraph() {
        return xmiCanvas.getLayoutGraph();
    }

    public GmfDebugCanvas getDebugCanvas() {
        return compareCanvas;
    }

    public TransparentShell getTransparentShell() {
        return transparentShell;
    }

}
