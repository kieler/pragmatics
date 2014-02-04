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
package de.cau.cs.kieler.klay.debugview.views;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.core.ui.util.DragDropScrollHandler;
import de.cau.cs.kieler.kiml.graphviz.layouter.GraphvizTool;
import de.cau.cs.kieler.klay.debugview.KlayDebugViewPlugin;
import de.cau.cs.kieler.klay.debugview.Messages;
import de.cau.cs.kieler.klay.debugview.provider.FileTableContentProvider;
import de.cau.cs.kieler.klay.debugview.provider.FileTableLabelProvider;
import de.cau.cs.kieler.klay.debugview.widgets.ImageCanvas;
import de.cau.cs.kieler.klay.debugview.widgets.LegendPage;

// CHECKSTYLEOFF MagicNumber

/**
 * The debug view houses controls that allow the user to inspect the debug output
 * produced by Klay Layered.
 * 
 * @author cds
 */
public class DebugView extends ViewPart {
    
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
            
            if (file1.lastModified() < file2.lastModified()) {
                return -1;
            } else if (file1.lastModified() > file2.lastModified()) {
                return 1;
            } else {
                return file1.getName().compareTo(file2.getName());
            }
        }
    }
    
    
    // CONSTANTS
    /**
     * Setting for the currently displayed path.
     */
    private static final String SETT_PATH = "debugWindow.path"; //$NON-NLS-1$
    
    /**
     * Setting for the zoom level.
     */
    private static final String SETT_ZOOM = "debugWindow.zoom"; //$NON-NLS-1$
    
    /**
     * Setting for the visibility of the color key browser.
     */
    private static final String SETT_COLOR_KEY = "debugWindow.colorKey"; //$NON-NLS-1$
    
    /**
     * Name of the toolbar group for actions related to folders.
     */
    private static final String TOOLBAR_GROUP_FOLDER_ACTIONS = "folderActions"; //$NON-NLS-1$
    
    /**
     * Name of the toolbar group for actions related to help.
     */
    private static final String TOOLBAR_GROUP_HELP_ACTIONS = "helpActions"; //$NON-NLS-1$
    
    /**
     * The default zoom level. (100%)
     */
    private static final int ZOOM_DEFAULT = 100;
    
    /**
     * A hundred percent.
     */
    private static final float HUNDRED_PERCENT = 100.0f;
    
    // UI CONTROLS
    private OpenFolderAction openFolderAction = null;
    private RefreshFolderAction refreshFolderAction = null;
    private EmptyFolderAction emptyFolderAction = null;
    private ToggleLegendPaneAction toggleLegendAction = null;
    private SashForm sashForm = null;
    private Table fileTable = null;
    private TableViewer fileTableViewer = null;
    private ImageCanvas imageCanvas = null;
    private Browser colorKeyBrowser = null;
    private Composite statusBar = null;
    private Label statusBarLabel = null;
    private Scale statusBarZoomScale = null;
    private Image statusBarZoomLabelContextImage = null;
    private CLabel statusBarZoomLabel = null;
    private Menu statusBarZoomMenu = null;
    private MenuItem statusBarZoomOriginalItem = null;
    private MenuItem statusBarZoomToFitItem = null;
    
    // VARIABLES
    /**
     * The currently displayed path.
     */
    private String currentPath = null;
    
    /**
     * The current zoom factor.
     */
    private int zoomPercentage = ZOOM_DEFAULT;
    

    ///////////////////////////////////////////////////////////////////////////////
    // Path Setting
    
    /**
     * Opens a directory dialog for the user to enter a new path. If the user clicks
     * OK, the new path is applied. This is only intended to be used by the corresponding
     * action.
     */
    public void openPathDialog() {
    }
    
    /**
     * Returns the currently opened path. May be empty or {@code null}.
     * 
     * @return the currently opened path.
     */
    String getCurrentPath() {
        return currentPath;
    }
    
    /**
     * Refreshes the file table.
     */
    void refreshFileList() {
        setPath(currentPath);
    }
    
    /**
     * Tries to apply the new path.
     * 
     * @param newPath the new path. May be {@code null}.
     */
    void setPath(final String newPath) {
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
        refreshFolderAction.setEnabled(fileTableInput != null);
        emptyFolderAction.setEnabled(fileTableInput != null);
        statusBarLabel.setText(currentPath);
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // File Operations
    
    /**
     * Deletes all dot and png files in the current directory.
     */
    void removeFiles() {
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
            if (fileName.endsWith(".dot") || fileName.endsWith(".png")) { //$NON-NLS-1$ //$NON-NLS-2$
                file.delete();
            }
        }
        
        refreshFileList();
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Image Display
    
    /**
     * Loads the given image and shows it.
     * 
     * @param modelFile the selected model file or {@code null}.
     */
    private void updateImage(final File modelFile) {
        File imageFile = null;
        
        // If there is a model file, try to create and load the associated image
        // (the model file can be null if the user just deleted all files)
        if (modelFile != null) {
            String path = modelFile.getPath();
            imageFile = new File(path.substring(0, path.length() - 3) + "png"); //$NON-NLS-1$
            
            // Check if the image file already exists
            if (!imageFile.exists()) {
                if (!createImage(modelFile, imageFile)) {
                    // If this doesn't work, set image file to null
                    openErrorDialog(Messages.DebugWindow_Error_ImageCreationFailed);
                    imageFile = null;
                }
            }
        }
        
        // Check if the image file exists
        if (imageFile == null) {
            // Reset
            imageCanvas.clear();
            statusBarLabel.setText(currentPath);
        } else {
            // Load new image
            imageCanvas.loadImage(imageFile);
            statusBarLabel.setText(modelFile.getPath());
        }
    }
    
    /**
     * Changes the zoom.
     * 
     * @param percentage the new zoom level, given as a percentage. 100% displays the image in
     *                   its original size.
     */
    private void changeZoom(final int percentage) {
        zoomPercentage = percentage;
        
        imageCanvas.setZoom(zoomPercentage / HUNDRED_PERCENT);
        
        // Update the zoom label and the zoom scale to show the new value
        statusBarZoomScale.setSelection(zoomPercentage);
        statusBarZoomLabel.setText(zoomPercentage + "% "); //$NON-NLS-1$
        statusBar.layout();
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
        // Find the dot executable
        String dotExecutable = GraphvizTool.getDotExecutable();
        
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
                this.getSite().getShell(),
                Messages.DebugWindow_Error_Title,
                null,
                new Status(IStatus.ERROR, KlayDebugViewPlugin.PLUGIN_ID, message),
                IStatus.ERROR);
        dialog.open();
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Dialog Settings
    
    /* Basing the settings on the memento mechanism didn't work right out of the box. Since I don't
     * have the time to solve that properly, I just decided to not care and not use mementos. Take
     * that, Eclipse!
     */

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void init(final IViewSite site, final IMemento memento) throws PartInitException {
//        setPath(memento.getString(SETT_PATH));
//        
//        Integer zoom = memento.getInteger(SETT_ZOOM);
//        if (zoom != null) {
//            changeZoom(zoom);
//        }
//        
//        Boolean colorKey = memento.getBoolean(SETT_COLOR_KEY);
//        setColorKeyVisible(colorKey == null ? true : colorKey);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void saveState(final IMemento memento) {
//        memento.putString(SETT_PATH, currentPath);
//        memento.putInteger(SETT_ZOOM, zoomPercentage);
//        memento.putBoolean(SETT_COLOR_KEY, colorKeyBrowser.isVisible());
//    }
    
    /**
     * Saves the current settings.
     */
    private void saveDialogSettings() {
        IDialogSettings dialogSettings = KlayDebugViewPlugin.getDefault().getDialogSettings();
        
        dialogSettings.put(SETT_PATH, currentPath);
        dialogSettings.put(SETT_ZOOM, zoomPercentage);
        dialogSettings.put(SETT_COLOR_KEY, colorKeyBrowser.isVisible());
    }
    
    /**
     * Loads the most recently saved dialog settings.
     */
    private void loadDialogSettings() {
        IDialogSettings dialogSettings = KlayDebugViewPlugin.getDefault().getDialogSettings();
        
        setPath(dialogSettings.get(SETT_PATH));
        
        try {
            changeZoom(dialogSettings.getInt(SETT_ZOOM));
        } catch (NumberFormatException e) {
            changeZoom(ZOOM_DEFAULT);
        }
        
        setColorKeyVisible(dialogSettings.getBoolean(SETT_COLOR_KEY));
    }
    

    

    ///////////////////////////////////////////////////////////////////////////////
    // GUI Creation / Disposal

    /**
     * {@inheritDoc}
     */
    @Override
    public void createPartControl(final Composite parent) {
        Composite shellComposite = new Composite(parent, SWT.NULL);
        shellComposite.setLayout(new GridLayout(1, false));
        shellComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        setupToolBar(shellComposite);
        createMainContent(shellComposite);
        createStatusBar(shellComposite);
        
        loadDialogSettings();
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
        sashForm = new SashForm(parent, SWT.HORIZONTAL | SWT.SMOOTH);
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
        
        fileTable.setSortColumn(createdColumn.getColumn());
        fileTable.setSortDirection(SWT.UP);
        
        // Image Canvas
        imageCanvas = new ImageCanvas(sashForm);
        new DragDropScrollHandler(imageCanvas, true);
        
        // Browser
        colorKeyBrowser = new Browser(sashForm, SWT.BORDER);
        colorKeyBrowser.setText(LegendPage.getColorKeyText(colorKeyBrowser));
        
        // Set sash form weights
        sashForm.setWeights(new int[] {30, 50, 30});
        
        // Event listeners
        fileTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(final SelectionChangedEvent event) {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                updateImage((File) selection.getFirstElement());
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
        IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
        toolBarManager.add(new Separator(TOOLBAR_GROUP_FOLDER_ACTIONS));
        toolBarManager.add(new Separator(TOOLBAR_GROUP_HELP_ACTIONS));
        
        openFolderAction = new OpenFolderAction(this);
        toolBarManager.appendToGroup(TOOLBAR_GROUP_FOLDER_ACTIONS, openFolderAction);
        refreshFolderAction = new RefreshFolderAction(this);
        refreshFolderAction.setEnabled(false);
        toolBarManager.appendToGroup(TOOLBAR_GROUP_FOLDER_ACTIONS, refreshFolderAction);
        emptyFolderAction = new EmptyFolderAction(this);
        emptyFolderAction.setEnabled(false);
        toolBarManager.appendToGroup(TOOLBAR_GROUP_FOLDER_ACTIONS, emptyFolderAction);
        toggleLegendAction = new ToggleLegendPaneAction(this);
        toolBarManager.appendToGroup(TOOLBAR_GROUP_HELP_ACTIONS, toggleLegendAction);
    }
    
    /**
     * Creates the status bar.
     * 
     * @param parent the parent composite.
     */
    private void createStatusBar(final Composite parent) {
        // GUI code needs magic numbers
        // CHECKSTYLEOFF MagicNumber
        
        GridData gd;
        
        // Status Bar
        statusBar = new Composite(parent, SWT.NULL);
        statusBar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        GridLayout gl = new GridLayout(4, false);
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        statusBar.setLayout(gl);
        
        // Status Bar Label
        statusBarLabel = new Label(statusBar, SWT.NULL);
        statusBarLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        
        // Status Bar Zoom Label
        statusBarZoomLabel = new CLabel(statusBar, SWT.NULL);
        statusBarZoomLabelContextImage = KlayDebugViewPlugin.loadImage("contextMenu.gif"); //$NON-NLS-1$
        statusBarZoomLabel.setImage(statusBarZoomLabelContextImage);
        
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        statusBarZoomLabel.setLayoutData(gd);
        
        // Status Bar Zoom Menu
        statusBarZoomMenu = new Menu(statusBarZoomLabel);
        
        // Status Bar Zoom Original Item
        statusBarZoomOriginalItem = new MenuItem(statusBarZoomMenu, SWT.PUSH);
        statusBarZoomOriginalItem.setText(Messages.DebugWindow_ZoomMenu_OriginalSize);
        
        // Status Bar Zoom To Fit Item
        statusBarZoomToFitItem = new MenuItem(statusBarZoomMenu, SWT.PUSH);
        statusBarZoomToFitItem.setText(Messages.DebugWindow_ZoomMenu_ZoomToFit);
        
        // Status Bar Zoom Scale
        statusBarZoomScale = new Scale(statusBar, SWT.HORIZONTAL);
        statusBarZoomScale.setIncrement(5);
        statusBarZoomScale.setMaximum((int) (ImageCanvas.MAX_ZOOM * HUNDRED_PERCENT));
        statusBarZoomScale.setMinimum((int) (ImageCanvas.MIN_ZOOM * HUNDRED_PERCENT));
        statusBarZoomScale.setPageIncrement(25);
        
        gd = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gd.widthHint = 100;
        statusBarZoomScale.setLayoutData(gd);
        
        // Event Listeners
        statusBarZoomLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(final MouseEvent e) {
                statusBarZoomMenu.setLocation(statusBarZoomLabel.toDisplay(e.x, e.y));
                statusBarZoomMenu.setVisible(true);
            }
        });
        
        statusBarZoomOriginalItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                changeZoom(100);
            }
        });
        
        statusBarZoomToFitItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                imageCanvas.zoomToFit();
                changeZoom((int) (imageCanvas.getZoom() * HUNDRED_PERCENT));
            }
        });
        
        statusBarZoomScale.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent e) {
                changeZoom(statusBarZoomScale.getSelection());
            }
        });
        
        // CHECKSTYLEON MagicNumber
    }
    
    @Override
    public void dispose() {
        super.dispose();
        
        saveDialogSettings();
        
        if (statusBarZoomLabelContextImage != null && !statusBarZoomLabelContextImage.isDisposed()) {
            statusBarZoomLabelContextImage.dispose();
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setFocus() {
        imageCanvas.setFocus();
    }
    

    ///////////////////////////////////////////////////////////////////////////////
    // Color Key Panel
    
    /**
     * Sets the color key panel's visibility.
     * 
     * @param visible {@code true} if the color key panel should be visible, {@code false}
     *                otherwise.
     */
    void setColorKeyVisible(final boolean visible) {
        toggleLegendAction.setChecked(visible);
        colorKeyBrowser.setVisible(visible);
        sashForm.layout(true);
    }
}
