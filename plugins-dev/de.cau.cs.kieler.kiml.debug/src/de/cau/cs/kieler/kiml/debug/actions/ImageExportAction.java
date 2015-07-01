/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kiml.debug.actions;

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
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.debug.KimlViewerPlugin;
import de.cau.cs.kieler.kiml.debug.Messages;
import de.cau.cs.kieler.kiml.debug.views.LayoutGraphCanvas;
import de.cau.cs.kieler.kiml.debug.views.LayoutGraphView;
import de.cau.cs.kieler.kiml.klayoutdata.KShapeLayout;

/**
 * Action that exports the currently displayed layout graph into an image file.
 * 
 * @author msp
 */
public class ImageExportAction extends Action {

    /** identifier string for this action. */
    private static final String ACTION_ID = "de.cau.cs.kieler.kiml.debug.imageExport";
    /** relative path to the icon to use for this action. */
    private static final String ICON_PATH = "icons/export.gif";
    /** preference identifier for the last used file name. */
    private static final String LAST_FILE_NAME_PREF = "klay.info.lastImageFile";

    /** the layout graph view associated with this action. */
    private LayoutGraphView view;

    /**
     * Creates an image export action for a given layout graph view.
     * 
     * @param theview layout graph view that created this action
     */
    public ImageExportAction(final LayoutGraphView theview) {
        this.view = theview;
        setId(ACTION_ID);
        setText(Messages.getString("kiml.viewer.2"));
        setToolTipText(Messages.getString("kiml.viewer.3"));
        setImageDescriptor(KimlViewerPlugin.imageDescriptorFromPlugin(KimlViewerPlugin.PLUGIN_ID,
                ICON_PATH));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        final LayoutGraphCanvas canvas = view.getCanvas();
        if (canvas != null) {
            // let the user select an output file
            final String fileName = getFileName(canvas.getDisplay());

            if (fileName != null) {
                // create a job for painting and exporting the image
                Job saveJob = new Job(Messages.getString("kiml.viewer.6")) {
                    protected IStatus run(final IProgressMonitor monitor) {
                        try {
                            monitor.beginTask(Messages.getString("kiml.viewer.6"), 2);

                            // paint the layout graph
                            KNode graph = canvas.getLayoutGraph();
                            KShapeLayout graphSize = graph.getData(KShapeLayout.class);
                            Rectangle area = new Rectangle(0, 0, (int) graphSize.getWidth() + 1,
                                    (int) graphSize.getHeight() + 1);
                            Image image = new Image(canvas.getDisplay(), area.width, area.height);
                            canvas.getRenderer().markDirty(area);
                            canvas.getRenderer().render(graph, new GC(image), area);
                            monitor.worked(1);
                            if (monitor.isCanceled()) {
                                return new Status(IStatus.INFO, KimlViewerPlugin.PLUGIN_ID, 0,
                                        "Aborted", null);
                            }

                            // save the image into the selected file
                            ImageLoader imageLoader = new ImageLoader();
                            ImageData[] imageData = new ImageData[] {image.getImageData()};
                            imageLoader.data = imageData;
                            imageLoader.save(fileName, SWT.IMAGE_PNG);
                            monitor.worked(1);

                            return new Status(IStatus.INFO, KimlViewerPlugin.PLUGIN_ID, 0, "OK", null);
                        } catch (SWTException exception) {
                            return new Status(IStatus.ERROR, KimlViewerPlugin.PLUGIN_ID,
                                    exception.code, Messages.getString("kiml.viewer.7"), exception);
                        } finally {
                            monitor.done();
                        }
                    }
                };

                // process the image export job
                IProgressMonitor monitor = Job.getJobManager().createProgressGroup();
                saveJob.setProgressGroup(monitor, 2);
                saveJob.setPriority(Job.SHORT);
                saveJob.setUser(true);
                saveJob.schedule();
            }
        }
    }

    /**
     * Opens a file dialog for the user to choose an output file for the
     * exported image.
     * 
     * @param display display to use as parent for the file dialog
     * @return file name, or null if no file was selected
     */
    private static String getFileName(final Display display) {
        IPreferenceStore preferenceStore = KimlViewerPlugin.getDefault().getPreferenceStore();

        // create and configure file dialog
        FileDialog fileDialog = new FileDialog(display.getActiveShell(), SWT.SAVE);
        String[] extensions = new String[] {"*.png"};
        fileDialog.setFilterExtensions(extensions);
        String[] names = new String[] {Messages.getString("kiml.viewer.5")};
        fileDialog.setFilterNames(names);
        fileDialog.setOverwrite(true);
        fileDialog.setText(Messages.getString("kiml.viewer.4"));
        fileDialog.setFileName(preferenceStore.getString(LAST_FILE_NAME_PREF));

        // open the file dialog and check the output
        String fileName = checkFileName(fileDialog.open());

        if (fileName != null) {
            preferenceStore.setValue(LAST_FILE_NAME_PREF, fileName);
        }
        return fileName;
    }

    /**
     * Checks whether the extension of the given file name is compatible with
     * the PNG format and changes it if needed.
     * 
     * @param fileName file name given by the user
     * @return file name with .png as extension
     */
    private static String checkFileName(final String fileName) {
        if (fileName == null) {
            return null;
        } else {
            String nameCopy = new String(fileName).toLowerCase();
            if (nameCopy.endsWith(".png")) {
                return fileName;
            } else if (nameCopy.endsWith(".pn")) {
                return fileName + "g";
            } else if (nameCopy.endsWith(".p")) {
                return fileName + "ng";
            } else if (nameCopy.endsWith(".")) {
                return fileName + "png";
            } else {
                return fileName + ".png";
            }
        }
    }

}
