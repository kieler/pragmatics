/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.ui.layout;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Animation;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import de.cau.cs.kieler.core.alg.BasicProgressMonitor;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.ui.KielerProgressMonitor;
import de.cau.cs.kieler.core.util.Maybe;
import de.cau.cs.kieler.kiml.layout.services.LayoutServices;
import de.cau.cs.kieler.kiml.layout.services.RecursiveLayouterEngine;
import de.cau.cs.kieler.kiml.ui.KimlUiPlugin;

/**
 * Abstract superclass for managers of diagram layout. Contains static methods
 * to layout a specific diagram, and manages selection of an appropriate
 * subclass to translate the diagram into the internal {@code KGraph} structure.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public abstract class DiagramLayoutManager {
    
    /** minimal animation time for diagram layout */
    private final static int MIN_ANIMATION_TIME = 500;
    /** maximal animation time for diagram layout */
    private final static int MAX_ANIMATION_TIME = 4000;
    
    /** list of registered diagram layout managers */
    private final static List<DiagramLayoutManager> managers = new LinkedList<DiagramLayoutManager>();

    /** the layouter engine used to layout diagrams */
    private RecursiveLayouterEngine layouterEngine = new RecursiveLayouterEngine();
	
	/**
	 * Registers the given diagram layout manager.
	 * 
	 * @param manager an instance of a diagram layout manager
	 */
	public static void registerManager(DiagramLayoutManager manager) {
	    managers.add(manager);
	}
	
	/**
	 * Performs layout on the given editor by choosing an appropriate
     * layout manager instance.
	 * 
     * @param editorPart the editor for which layout is performed, or {@code null} if
     *     the diagram is not part of an editor
     * @param editPart the parent edit part for which layout is performed, or
     *     {@code null} if the whole diagram shall be layouted
	 * @param animate if true, Draw2D animation is activated
	 * @param progressBar if true, a progress bar is displayed
	 */
	public final static void layout(IEditorPart editorPart, EditPart editPart,
	        boolean animate, boolean progressBar) {
	    for (DiagramLayoutManager manager : managers) {
	        if (manager.supports(editorPart) || manager.supports(editPart)) {
	            IStatus status = manager.doLayout(editorPart, editPart, animate, progressBar);
	            int severity = status.getSeverity();
	            if (severity != IStatus.OK && severity != IStatus.CANCEL) {
	                if (severity == IStatus.ERROR)
	                    StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
	                else
	                	StatusManager.getManager().handle(status, StatusManager.LOG);
	            }
	            return;
	        }
	    }
	    throw new UnsupportedOperationException("No layout manager is available for "
	            + editorPart.getTitle() + ".");
	}
	
	/**
	 * Performs layout on the given editor or edit part using this layout manager.
	 * A progress bar indicating progress of the layout algorithm is shown to the user.
	 * 
	 * @param editorPart the editor for which layout is performed, or {@code null} if
     *     the diagram is not part of an editor
	 * @param editPart the parent edit part for which layout is performed, or
	 *     {@code null} if the whole diagram shall be layouted
	 * @param animate if true, Draw2D animation is activated
	 * @param progressBar if true, a progress bar is displayed
	 * @return a status indicating success or failure
	 */
	public final IStatus doLayout(final IEditorPart editorPart, final EditPart editPart,
            final boolean animate, boolean progressBar) {
	    final Maybe<IStatus> status = new Maybe<IStatus>();
		try {
            if (animate) {
                Animation.markBegin();
            }
            if (progressBar) {
    			PlatformUI.getWorkbench().getProgressService().run(false, false,
    					new IRunnableWithProgress() {
    				public void run(IProgressMonitor monitor) {
    					status.object = doLayout(editorPart, editPart,
    							new KielerProgressMonitor(monitor));
    				}
    			});
            }
            else {
                status.object = doLayout(editorPart, editPart,
                        new BasicProgressMonitor());
            }
			if (animate) {
				Animation.run(calcAnimationTime(status.object.getCode()));
			}
		}
		catch (Exception exception) {
		    return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID,
		            "Failed to perform diagram layout.", exception);
		}
		return status.object;
	}

	/**
	 * Performs layout on the given editor or edit part using this layout manager
	 * and a specific progress monitor.
	 * 
	 * @param editorPart the editor for which layout is performed, or {@code null} if
     *     the diagram is not part of an editor
	 * @param editPart the parent edit part for which layout is performed, or
     *     {@code null} if the whole diagram shall be layouted
	 * @param animate if true, Draw2D animation is activated
	 * @param progressMonitor a progress monitor to which progress of the layout algorithm
	 *     is reported
	 * @return a status indicating success or failure; if successful, the status contains
	 *     the number of layouted nodes as code value
	 */
	public final synchronized IStatus doLayout(IEditorPart editorPart, EditPart editPart,
	            IKielerProgressMonitor progressMonitor) {
		try {
			progressMonitor.begin("Diagram layout", 100);
			
			// transform the diagram into a KGraph instance
			KNode layoutGraph = buildLayoutGraph(editorPart, editPart,
					progressMonitor.subTask(10));

			// notify layout listeners about the layout request
			LayoutServices.INSTANCE.layoutRequested(layoutGraph);

			// perform layout on the layout graph
			layouterEngine.layout(layoutGraph, progressMonitor.subTask(80));

			// apply layout to the model
			applyLayout(progressMonitor.subTask(10));
			
			// notify layout listeners about the performed layout
            progressMonitor.done();
			LayoutServices.INSTANCE.layoutPerformed(layoutGraph, progressMonitor);

			// return a positive status including graph size
			int graphSize = countNodes(layoutGraph);
			return new Status(IStatus.OK, KimlUiPlugin.PLUGIN_ID, graphSize, null, null);
			
		} catch (Throwable exception) {
		    progressMonitor.done();
			String message = "Failed to perform diagram layout.";
			if (layouterEngine != null
					&& layouterEngine.getLastLayoutProvider() != null)
				message += " ("	+ layouterEngine.getLastLayoutProvider()
						.getClass().getSimpleName() + ")";
			return new Status(IStatus.ERROR, KimlUiPlugin.PLUGIN_ID,
			        message, exception);
		}
	}
	
	/**
	 * Counts the total number of children in the given node, including deep
	 * hierarchies.
	 * 
	 * @param node parent node
	 * @return number of children and grandchildren in the given parent
	 */
	private int countNodes(KNode node) {
	    int count = 0;
	    for (KNode child : node.getChildren()) {
	        count += countNodes(child) + 1;
	    }
	    return count;
	}
	
	/**
	 * Calculates animation time for the given graph size.
	 * 
	 * @param graphSize total number of nodes in the graph
	 * @return number of milliseconds to animate
	 */
	private int calcAnimationTime(int graphSize) {
	    int time = MIN_ANIMATION_TIME + (int)(100 * Math.sqrt(graphSize));
	    return time <= MAX_ANIMATION_TIME ? time : MAX_ANIMATION_TIME;
	}
	
	/**
	 * Determines whether this layout manager is able to perform layout for the
	 * given editor.
	 * 
	 * @param editorPart an editor part
	 * @return true if this layout manager supports the editor part
	 */
	protected abstract boolean supports(IEditorPart editorPart);
	
	/**
	 * Determines whether this layout manager is able to perform layout for
	 * the given edit part.
	 * 
	 * @param editPart an edit part
	 * @return true if this layout manager supports the edit part
	 */
	protected abstract boolean supports(EditPart editPart);
	
	/**
	 * Builds a KGraph instance for the given editor or edit part. The resulting
	 * layout graph should reflect the structure of edit parts in the original
	 * diagram.
	 * 
	 * @param editorPart the editor for which layout is performed, or {@code null} if
     *     the diagram is not part of an editor
	 * @param editPart the parent edit part for which layout is performed, or
     *     {@code null} if the whole diagram shall be layouted
     * @param progressMonitor a monitor to keep track of progress
	 * @return a layout graph instance
	 */
	protected abstract KNode buildLayoutGraph(IEditorPart editorPart, EditPart editPart,
			IKielerProgressMonitor progressMonitor);
	
	/**
	 * Applies all layout data from the last created KGraph instance to the original
	 * diagram.
	 * 
	 * @param progressMonitor a monitor to keep track of progress
	 */
	protected abstract void applyLayout(IKielerProgressMonitor progressMonitor);
	
}
