/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klay.debugview;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import de.cau.cs.kieler.core.ui.util.DragDropScrollHandler;

// CHECKSTYLEOFF MagicNumber

/**
 * The debug window houses controls that allow the user to inspect the debug output
 * produced by Klay Layered. This could at some point also support zooming, but that's
 * not implemented yet.
 * 
 * @author cds
 */
public class DebugWindow extends Window {
    
    /**
     * Content provider for the file table. Expects the table viewer to get a
     * {@code File} as input that denotes a directory.
     * 
     * @author cds
     */
    private static class FileTableContentProvider implements IStructuredContentProvider {
        /**
         * {@inheritDoc}
         */
        public void dispose() {
            // Nothing to do here.
        }

        /**
         * {@inheritDoc}
         */
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            // Nothing to do.
        }

        /**
         * {@inheritDoc}
         */
        public Object[] getElements(final Object inputElement) {
            if (inputElement instanceof File) {
                // Return a list of .dot files
                return ((File) inputElement).listFiles(new FileFilter() {
                    public boolean accept(final File file) {
                        return file.isFile() && file.getName().endsWith(".dot"); //$NON-NLS-1$
                    }
                });
                
            } else {
                return new Object[0];
            }
        }
    }
    
    /**
     * Label provider for the file table.
     * 
     * @author cds
     */
    private static class FileTableLabelProvider extends ColumnLabelProvider {
        /**
         * The name column.
         */
        public static final int COL_NAME = 0;
        
        /**
         * The creation time column.
         */
        public static final int COL_CREATED = 1;
        
        /**
         * Image for files whose PNG hasn't been created yet.
         */
        private Image unconverted = KlayDebugViewPlugin.loadImage("notconverted.gif"); //$NON-NLS-1$

        /**
         * Image for files whose PNG is available.
         */
        private Image converted = KlayDebugViewPlugin.loadImage("converted.gif"); //$NON-NLS-1$
        
        /**
         * The column to provide labels for.
         */
        private int column = 0;
        
        
        /**
         * Creates a new label provider for the given column.
         * 
         * @param column the column.
         */
        public FileTableLabelProvider(final int column) {
            this.column = column;
        }
        
        
        /**
         * {@inheritDoc}
         */
        public void dispose() {
            super.dispose();
            
            if (converted != null) {
                converted.dispose();
            }
            
            if (unconverted != null) {
                unconverted.dispose();
            }
        }

        /**
         * {@inheritDoc}
         */
        public Image getImage(final Object element) {
            if (column == COL_NAME) {
                String path = ((File) element).getPath();
                
                if (new File(path.substring(0, path.length() - 3) + "png").exists()) { //$NON-NLS-1$
                    return converted;
                } else {
                    return unconverted;
                }
            } else {
                return null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public String getText(final Object element) {
            File file = (File) element;
            
            if (column == COL_NAME) {
                return file.getName();
            } else if (column == COL_CREATED) {
                return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(
                        new Date(file.lastModified()));
            } else {
                return null;
            }
        }
    }
    
    /**
     * Compares {@code File}s.
     * 
     * @author cds
     */
    private static class FileViewerComparator extends ViewerComparator {
        /**
         * {@inheritDoc}
         */
        @Override
        public int compare(final Viewer viewer, final Object e1, final Object e2) {
            File file1 = (File) e1;
            File file2 = (File) e2;
            
            return file1.getName().compareTo(file2.getName());
        }
    }
    
    /**
     * A customized version of the drag drop scroll handler that handles value
     * change notifications more efficiently.
     * 
     * @author cds
     */
    private class CustomScrollHandler extends DragDropScrollHandler {
        
        /**
         * Constructs a new instance for the given control.
         * 
         * @param control the control.
         */
        public CustomScrollHandler(final Scrollable control) {
            super(control, true);
        }

        @Override
        protected void notifiyOfValueChange() {
            handleScrollBarChange(
                    getHorizontalBar().getSelection(),
                    getVerticalBar().getSelection());
        }
        
    }
    
    
    // CONSTANTS
    /**
     * Setting for the currently displayed path.
     */
    private static final String SETT_PATH = "debugWindow.path"; //$NON-NLS-1$
    
    /**
     * Setting for the window bounds.
     */
    private static final String SETT_BOUNDS = "debugWindow.bounds"; //$NON-NLS-1$
    
    /**
     * How much the zoom factor is changed when zooming in or out.
     */
    private static final float ZOOM_DELTA = 0.1f;
    
    /**
     * Default locations of Graphviz dot.
     */
    private static final String[] DEFAULT_DOT_LOCS = {
        "/opt/local/bin/", "/usr/local/bin/", "/usr/bin/", "/bin/" };
    
    // UI CONTROLS
    private ToolBar toolBar = null;
    private Image folderBrowseImage = null;
    private ToolItem folderBrowseButton = null;
    private Image folderRefreshImage = null;
    private ToolItem folderRefreshButton = null;
    private Image folderRemoveAllImage = null;
    private ToolItem folderRemoveAllButton = null;
    private Image zoomInImage = null;
    private ToolItem zoomInButton = null;
    private Image zoomOutImage = null;
    private ToolItem zoomOutButton = null;
    private Table fileTable = null;
    private TableViewer fileTableViewer = null;
    private Canvas imageCanvas = null;
    private Label statusBar = null;
    
    // VARIABLES
    /**
     * The currently displayed path.
     */
    private String currentPath = null;
    
    /**
     * The currently displayed image file.
     */
    private File currentImageFile = null;
    
    /**
     * The currently displayed image.
     */
    private Image currentImage = null;
    
    /**
     * The image point that will land in the top left corner.
     */
    private Point origin = new Point(0, 0);
    
    /**
     * The current zoom factor.
     */
    private float zoom = 1.0f;
    

    /**
     * Constructs a new instance with the given parent shell.
     * 
     * @param parentShell the window's parent.
     */
    protected DebugWindow(final Shell parentShell) {
        super(parentShell);
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Path Setting
    
    /**
     * Opens a directory dialog for the user to enter a new path. If the user clicks
     * OK, the new path is applied.
     */
    private void openPathDialog() {
        DirectoryDialog dialog = new DirectoryDialog(getShell());
        dialog.setMessage(Messages.DebugWindow_PathDialog_Message);
        dialog.setFilterPath(currentPath);
        
        String newPath = dialog.open();
        if (newPath != null) {
            setPath(newPath);
        }
    }
    
    /**
     * Refreshes the file table.
     */
    private void refresh() {
        setPath(currentPath);
    }
    
    /**
     * Tries to apply the new path.
     * 
     * @param newPath the new path. May be {@code null}.
     */
    private void setPath(final String newPath) {
        String thePath = newPath;
        if (thePath == null) {
            thePath = ""; //$NON-NLS-1$
        }
        
        // Find the path
        File pathFile = new File(thePath);
        File fileTableInput = null;
        if (!pathFile.isDirectory()) {
            if (thePath.length() > 0) {
                // Error
                openErrorDialog(Messages.DebugWindow_Error_DirectoryCouldNotBeOpened);
            }
        } else {
            fileTableInput = pathFile;
        }
        
        fileTableViewer.setInput(fileTableInput);
        
        // Resize the table columns
        for (TableColumn column : fileTable.getColumns()) {
            column.pack();
        }
        
        // Show the new path to the user
        currentPath = thePath;
        folderRefreshButton.setEnabled(fileTableInput != null);
        folderRemoveAllButton.setEnabled(fileTableInput != null);
        statusBar.setText(currentPath);
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // File Operations
    
    /**
     * Deletes all dot and png files in the current directory.
     */
    private void removeFiles() {
        // Check if we have a file to display
        File pathFile = (File) fileTableViewer.getInput();
        if (pathFile == null) {
            return;
        }
        
        // Enumerate all subfiles
        for (File file : pathFile.listFiles()) {
            if (!file.isFile()) {
                // We're not interested in directories
                continue;
            }
            
            // We're only removing .dot and .png files
            String fileName = file.getName();
            if (fileName.endsWith(".dot") || fileName.endsWith(".png")) { //$NON-NLS-1$
                file.delete();
            }
        }
        
        refresh();
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Image Display
    
    /**
     * Loads the given image and shows it.
     */
    private void updateImage(final File modelFile) {
        String path = modelFile.getPath();
        File imageFile = new File(path.substring(0, path.length() - 3) + "png"); //$NON-NLS-1$
        
        // Check if the image file already exists
        if (!imageFile.exists()) {
            if (!createImage(modelFile, imageFile)) {
                // If this doesn't work, set image file to null
                openErrorDialog(Messages.DebugWindow_Error_ImageCreationFailed);
                imageFile = null;
            }
        }
        
        // Dispose the current image, if any
        if (currentImage != null) {
            currentImage.dispose();
        }
        
        if (imageFile == null) {
            // Reset
            currentImageFile = null;
            currentImage = null;
            statusBar.setText(currentPath);
        } else {
            // Load new image
            currentImage = new Image(getShell().getDisplay(), imageFile.getPath());
            currentImageFile = modelFile;
            statusBar.setText(currentImageFile.getPath());
        }

        origin.x = 0;
        origin.y = 0;
        zoom = 1.0f;
        
        updateCanvas();
    }
    
    /**
     * Zooms the image.
     * 
     * @param in {@code true} if we should zoom in, {@code false} if we should zoom
     *           out.
     */
    private void zoom(final boolean in) {
        if (in) {
            zoom += ZOOM_DELTA;
        } else {
            zoom = Math.max(0.0f, zoom - ZOOM_DELTA);
        }
        
        updateCanvas();
    }
    
    /**
     * Redraws the canvas and sets properties on its scroll bars.
     */
    private void updateCanvas() {
        Rectangle rect = currentImage == null ? new Rectangle(0, 0, 0, 0) : currentImage.getBounds();
        Rectangle client = imageCanvas.getClientArea();
        ScrollBar hBar = imageCanvas.getHorizontalBar();
        ScrollBar vBar = imageCanvas.getVerticalBar();
        
        hBar.setMaximum(rect.width);
        vBar.setMaximum(rect.height);
        hBar.setThumb(Math.min(rect.width, client.width));
        vBar.setThumb(Math.min(rect.height, client.height));
        
        int hPage = rect.width - client.width;
        int vPage = rect.height - client.height;
        int hSelection = hBar.getSelection();
        int vSelection = vBar.getSelection();
        
        if (hSelection >= hPage) {
                if (hPage <= 0) {
                    hSelection = 0;
                }
                origin.x = -hSelection;
        }
        
        if (vSelection >= vPage) {
                if (vPage <= 0) {
                    vSelection = 0;
                }
                origin.y = -vSelection;
        }
        
        imageCanvas.redraw();
    }
    
    /**
     * Handles a change in scroll bar values.
     * 
     * @param newHorizontalValue the horizontal scroll bar's new value, or {@code -1} if
     *                           no change occurred.
     * @param newVerticalValue the vertical scroll bar's new value, or {@code -1} if
     *                         no change occurred.
     */
    private void handleScrollBarChange(final int newHorizontalValue, final int newVerticalValue) {
        
        if (currentImage == null) {
            // Ignore
            return;
        }
        
        if (newHorizontalValue >= 0) {
            int destX = -newHorizontalValue - origin.x;
            Rectangle rect = currentImage.getBounds();
            imageCanvas.scroll(destX, 0, 0, 0, rect.width, rect.height, false);
            origin.x = -newHorizontalValue;
        }
        
        if (newVerticalValue >= 0) {
            int destY = -newVerticalValue - origin.y;
            Rectangle rect = currentImage.getBounds();
            imageCanvas.scroll(0, destY, 0, 0, rect.width, rect.height, false);
            origin.y = -newVerticalValue;
        }
    }
    
    /**
     * Paints the canvas.
     * 
     * @param gc graphics context.
     */
    private void handlePaintCanvas(final GC gc) {
        Rectangle imageRect = new Rectangle(0, 0, 0, 0);
        
        if (currentImage != null) {
            // Draw image
            gc.drawImage(currentImage, origin.x, origin.y);
            imageRect = currentImage.getBounds();
        }
        
        // Draw background
        Rectangle client = imageCanvas.getClientArea();
        
        int marginWidth = client.width - imageRect.width;
        if (marginWidth > 0) {
                gc.fillRectangle(imageRect.width, 0, marginWidth, client.height);
        }
        
        int marginHeight = client.height - imageRect.height;
        if (marginHeight > 0) {
                gc.fillRectangle(0, imageRect.height, client.width, marginHeight);
        }
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Miscellaneous
    
    /**
     * Tries to turn the given model file into the given image file.
     * 
     * @param modelFile the model file.
     * @param imageFile the image file.
     * @return {@code true} if the image file was successfully created.
     */
    private boolean createImage(final File modelFile, final File imageFile) {
        // Find the dot executable. This could at some point be bound to the preference store
        // and use the functionality provided by kieler.kiml.graphviz.layouter, but then again
        // this is only a debug tool used by local developers, so what the hell...
        String dotExecutable = "dot";
        
        if (!new File(dotExecutable).exists()) {
            boolean foundExec = false;
            for (String location : DEFAULT_DOT_LOCS) {
                dotExecutable = location + "dot";
                if (new File(dotExecutable).exists()) {
                    foundExec = true;
                    break;
                }
            }
            if (!foundExec) {
                throw new RuntimeException(
                        "Graphviz dot not found in the default locations."); //$NON-NLS-1$
            }
        }
        
        try {
            String[] cmdLine = new String[] {
                    dotExecutable,
                    "-Tpng", //$NON-NLS-1$
                    modelFile.getCanonicalPath(),
                    "-o", //$NON-NLS-1$
                    imageFile.getCanonicalPath()
            };
                
            Process p = Runtime.getRuntime().exec(cmdLine);
            p.waitFor();
            
            fileTableViewer.update(modelFile, null);
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
        
        return imageFile.exists();
    }
    
    /**
     * Shows an error dialog with the given message.
     * 
     * @param message the message.
     */
    private void openErrorDialog(final String message) {
        ErrorDialog dialog = new ErrorDialog(
                getShell(),
                Messages.DebugWindow_Error_Title,
                null,
                new Status(IStatus.ERROR, KlayDebugViewPlugin.PLUGIN_ID, message),
                IStatus.ERROR);
        dialog.open();
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Dialog Settings
    
    /**
     * Saves the current settings.
     */
    private void saveDialogSettings() {
        IDialogSettings dialogSettings = KlayDebugViewPlugin.getDefault().getDialogSettings();
        
        Point size = getShell().getSize();
        dialogSettings.put(SETT_BOUNDS + ".x", size.x); //$NON-NLS-1$
        dialogSettings.put(SETT_BOUNDS + ".y", size.y); //$NON-NLS-1$
        
        dialogSettings.put(SETT_PATH, currentPath);
    }
    
    /**
     * Loads the most recently saved dialog settings.
     */
    private void loadDialogSettings() {
        IDialogSettings dialogSettings = KlayDebugViewPlugin.getDefault().getDialogSettings();
        
        setPath(dialogSettings.get(SETT_PATH));
    }
    
    

    ///////////////////////////////////////////////////////////////////////////////
    // GUI Creation / Disposal
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean close() {
        saveDialogSettings();
        
        boolean closed = super.close();
        
        if (closed) {
            if (folderBrowseImage != null) {
                folderBrowseImage.dispose();
            }
            
            if (folderRefreshImage != null) {
                folderRefreshImage.dispose();
            }
            
            if (folderRemoveAllImage != null) {
                folderRemoveAllImage.dispose();
            }
            
            if (zoomInImage != null) {
                zoomInImage.dispose();
            }
            
            if (zoomOutImage != null) {
                zoomOutImage.dispose();
            }
        }
        
        return closed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Control createContents(final Composite parent) {
        Composite shellComposite = new Composite(parent, SWT.NULL);
        shellComposite.setLayout(new GridLayout(1, false));
        shellComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        setupToolBar(shellComposite);
        createMainContent(shellComposite);
        createStatusBar(shellComposite);
        
        loadDialogSettings();
        
        return shellComposite;
    }

    /**
     * Creates the main controls to be displayed in the window.
     * 
     * @param parent the parent composite.
     */
    private void createMainContent(final Composite parent) {
        // GUI code needs magic numbers
        // CHECKSTYLEOFF MagicNumber
        
        // Sash Form
        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL | SWT.SMOOTH);
        sashForm.setLayout(new FillLayout());
        sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        // Table
        fileTable = new Table(sashForm, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
        fileTable.setHeaderVisible(true);
        fileTable.setLinesVisible(true);
        
        // Table Viewer
        fileTableViewer = new TableViewer(fileTable);
        fileTableViewer.setContentProvider(new FileTableContentProvider());
        fileTableViewer.setComparator(new FileViewerComparator());
        
        TableViewerColumn nameColumn = new TableViewerColumn(fileTableViewer, SWT.LEFT);
        nameColumn.getColumn().setText(Messages.DebugWindow_FileTable_NameColumn_Text);
        nameColumn.getColumn().setMoveable(false);
        nameColumn.getColumn().setResizable(true);
        nameColumn.getColumn().pack();
        nameColumn.setLabelProvider(new FileTableLabelProvider(
                FileTableLabelProvider.COL_NAME));
        
        TableViewerColumn createdColumn = new TableViewerColumn(fileTableViewer, SWT.LEFT);
        createdColumn.getColumn().setText(Messages.DebugWindow_FileTable_ModifiedColumn_Text);
        createdColumn.getColumn().setMoveable(false);
        createdColumn.getColumn().setResizable(true);
        createdColumn.getColumn().pack();
        createdColumn.setLabelProvider(new FileTableLabelProvider(
                FileTableLabelProvider.COL_CREATED));
        
        // Image Canvas
        imageCanvas = new Canvas(sashForm,
                SWT.BORDER | SWT.NO_BACKGROUND | SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
        imageCanvas.getHorizontalBar().setMaximum(1);
        imageCanvas.getHorizontalBar().setThumb(1);
        imageCanvas.getVerticalBar().setMaximum(1);
        imageCanvas.getVerticalBar().setThumb(1);
        new CustomScrollHandler(imageCanvas);
        
        // Set sash form weights
        sashForm.setWeights(new int[] {30, 70});
        
        // Event listeners
        fileTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                updateImage((File) selection.getFirstElement());
            }
        });
        
        imageCanvas.addListener(SWT.Paint, new Listener() {
            public void handleEvent(final Event event) {
                handlePaintCanvas(event.gc);
            }
        });
        
        imageCanvas.addListener(SWT.Resize, new Listener() {
            public void handleEvent(final Event event) {
                updateCanvas();
            }
        });
        
        imageCanvas.getHorizontalBar().addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                handleScrollBarChange(imageCanvas.getHorizontalBar().getSelection(), -1);
            }
        });
        
        imageCanvas.getVerticalBar().addListener(SWT.Selection, new Listener() {
            public void handleEvent(final Event event) {
                handleScrollBarChange(-1, imageCanvas.getVerticalBar().getSelection());
            }
        });
        
        // CHECKSTYLEON MagicNumber
    }
    
    /**
     * Creates the tool bar.
     * 
     * @param parent the parent composite.
     */
    private void setupToolBar(final Composite parent) {
        toolBar = new ToolBar(parent, SWT.FLAT | SWT.HORIZONTAL | SWT.RIGHT);
        toolBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        // Folder Browse Button
        folderBrowseButton = new ToolItem(toolBar, SWT.NULL);
        folderBrowseButton.setText(Messages.DebugWindow_Toolbar_BrowseFolder_Text);
        folderBrowseButton.setToolTipText(Messages.DebugWindow_Toolbar_BrowseFolder_ToolTip);
        folderBrowseImage = KlayDebugViewPlugin.loadImage("open.png"); //$NON-NLS-1$
        folderBrowseButton.setImage(folderBrowseImage);
        
        // Folder Refresh Button
        folderRefreshButton = new ToolItem(toolBar, SWT.NULL);
        folderRefreshButton.setEnabled(false);
        folderRefreshButton.setToolTipText(Messages.DebugWindow_Toolbar_RefreshFolder_ToolTip);
        folderRefreshImage = KlayDebugViewPlugin.loadImage("refresh.gif"); //$NON-NLS-1$
        folderRefreshButton.setImage(folderRefreshImage);
        
        // Folder Remove All Button
        folderRemoveAllButton = new ToolItem(toolBar, SWT.NULL);
        folderRemoveAllButton.setEnabled(false);
        folderRemoveAllButton.setToolTipText(Messages.DebugWindow_Toolbar_RemoveAll_ToolTip);
        folderRemoveAllImage = KlayDebugViewPlugin.loadImage("remall.gif"); //$NON-NLS-1$
        folderRemoveAllButton.setImage(folderRemoveAllImage);
        
        // Separator
        new ToolItem(toolBar, SWT.SEPARATOR);
        
        // Zoom Buttons
        zoomInButton = new ToolItem(toolBar, SWT.NULL);
        zoomInButton.setEnabled(false);
        zoomInButton.setToolTipText(Messages.DebugWindow_Toolbar_ZoomIn_ToolTip);
        zoomInImage = KlayDebugViewPlugin.loadImage("zoomin.gif"); //$NON-NLS-1$
        zoomInButton.setImage(zoomInImage);

        zoomOutButton = new ToolItem(toolBar, SWT.NULL);
        zoomOutButton.setEnabled(false);
        zoomOutButton.setToolTipText(Messages.DebugWindow_Toolbar_ZoomOut_ToolTip);
        zoomOutImage = KlayDebugViewPlugin.loadImage("zoomout.gif"); //$NON-NLS-1$
        zoomOutButton.setImage(zoomOutImage);
        
        // Event listeners
        folderBrowseButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                openPathDialog();
            }
        });

        folderRefreshButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                refresh();
            }
        });

        folderRemoveAllButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                removeFiles();
            }
        });

        zoomInButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                zoom(true);
            }
        });

        zoomOutButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                zoom(false);
            }
        });
    }
    
    /**
     * Creates the status bar.
     * 
     * @param parent the parent composite.
     */
    private void createStatusBar(final Composite parent) {
        statusBar = new Label(parent, SWT.NULL);
        statusBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        
        newShell.setText(Messages.DebugWindow_Title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Point getInitialSize() {
        IDialogSettings dialogSettings = KlayDebugViewPlugin.getDefault().getDialogSettings();
        
        try {
            return new Point(
                    dialogSettings.getInt(SETT_BOUNDS + ".x"), //$NON-NLS-1$
                    dialogSettings.getInt(SETT_BOUNDS + ".y")); //$NON-NLS-1$
        } catch (NumberFormatException e) {
            return super.getInitialSize();
        }
    }
}
