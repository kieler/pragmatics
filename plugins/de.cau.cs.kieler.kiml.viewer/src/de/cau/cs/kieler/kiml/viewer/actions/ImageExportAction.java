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
package de.cau.cs.kieler.kiml.viewer.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import de.cau.cs.kieler.kiml.viewer.KimlViewerPlugin;
import de.cau.cs.kieler.kiml.viewer.Messages;
import de.cau.cs.kieler.kiml.viewer.views.LayoutGraphCanvas;
import de.cau.cs.kieler.kiml.viewer.views.LayoutGraphView;


/**
 * Action that exports the currently displayed layout graph into an
 * image file.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class ImageExportAction extends Action {

	/** identifier string for this action */
    private static final String ACTION_ID = "de.cau.cs.kieler.kiml.viewer.imageExport";
    /** relative path to the icon to use for this action */
    private static final String ICON_PATH = "icons/pngfile.gif";
    /** preference identifier for the last used file name */
    private static final String LAST_FILE_NAME_PREF = "kiml.viewer.lastFileName";
	
	/** the layout graph view associated with this action */
	private LayoutGraphView view;
	
	/**
	 * Creates an image export action for a given layout graph view.
	 * 
	 * @param view layout graph view that created this action
	 */
	public ImageExportAction(LayoutGraphView view) {
		this.view = view;
		setId(ACTION_ID);
		setText(Messages.getString("kiml.viewer.2"));
		setToolTipText(Messages.getString("kiml.viewer.3"));
		setImageDescriptor(KimlViewerPlugin.imageDescriptorFromPlugin(
			KimlViewerPlugin.PLUGIN_ID, ICON_PATH));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		final LayoutGraphCanvas canvas = view.getActiveCanvas();
		if (canvas != null) {
			// let the user select an output file
			final String fileName = getFileName(canvas.getDisplay());
			
			if (fileName != null) {
				final Point size = canvas.getSize();
				final Image image = new Image(canvas.getDisplay(), size.x, size.y);
				
				// create a job for painting and exporting the image
				Job saveJob = new Job(Messages.getString("kiml.viewer.6")) {
					protected IStatus run(IProgressMonitor monitor) {
						try {
							monitor.beginTask(Messages.getString("kiml.viewer.6"), 100);
							
							// paint the layout graph
							canvas.paintLayoutGraph(new GC(image), size);
							monitor.worked(50);
							if (monitor.isCanceled())
								return new Status(IStatus.INFO, KimlViewerPlugin.PLUGIN_ID,
										0, "Aborted", null);
							
							// save the image into the selected file
							ImageLoader imageLoader = new ImageLoader();
							ImageData[] imageData = new ImageData[] { image.getImageData() };
							imageLoader.data = imageData;
							imageLoader.save(fileName, SWT.IMAGE_PNG);
							monitor.worked(50);
							
							return new Status(IStatus.INFO, KimlViewerPlugin.PLUGIN_ID,
									0, "OK", null);
						} catch (SWTException exception) {
							return new Status(IStatus.ERROR, KimlViewerPlugin.PLUGIN_ID, exception.code,
									Messages.getString("kiml.viewer.7"), exception);
						}
						finally {
							monitor.done();
						}
					}
				};
				
				// process the image export job
				IProgressMonitor monitor = Job.getJobManager().createProgressGroup();
				saveJob.setProgressGroup(monitor, 100);
				saveJob.setPriority(Job.SHORT);
				saveJob.setUser(true);
				saveJob.schedule();
			}
		}
	}
	
	/**
	 * Opens a file dialog for the user to choose an output file for
	 * the exported image.
	 * 
	 * @param display display to use as parent for the file dialog
	 * @return file name, or null if no file was selected
	 */
	private static String getFileName(Display display) {
		IPreferenceStore preferenceStore = KimlViewerPlugin.getDefault().getPreferenceStore();
		
		// create and configure file dialog
		FileDialog fileDialog = new FileDialog(display.getActiveShell(), SWT.SAVE);
		String[] extensions = new String[] { "*.png" };
		fileDialog.setFilterExtensions(extensions);
		String[] names = new String[] { Messages.getString("kiml.viewer.5") };
		fileDialog.setFilterNames(names);
		fileDialog.setOverwrite(true);
		fileDialog.setText(Messages.getString("kiml.viewer.4"));
		fileDialog.setFileName(preferenceStore.getString(LAST_FILE_NAME_PREF));
		
		// open the file dialog and check the output
		String fileName = checkFileName(fileDialog.open());
		
		if (fileName != null)
			preferenceStore.setValue(LAST_FILE_NAME_PREF, fileName);
		return fileName;
	}
	
	/**
	 * Checks whether the extension of the given file name is compatible
	 * with the PNG format and changes it if needed.
	 * 
	 * @param fileName file name given by the user
	 * @return file name with .png as extension
	 */
	private static String checkFileName(String fileName) {
		if (fileName == null)
			return null;
		else {
			String nameCopy = new String(fileName).toLowerCase();
			if (nameCopy.endsWith(".png"))
				return fileName;
			else if (nameCopy.endsWith(".pn"))
				return fileName + "g";
			else if (nameCopy.endsWith(".p"))
				return fileName + "ng";
			else if (nameCopy.endsWith("."))
				return fileName + "png";
			else
				return fileName + ".png";
		}
	}

}
