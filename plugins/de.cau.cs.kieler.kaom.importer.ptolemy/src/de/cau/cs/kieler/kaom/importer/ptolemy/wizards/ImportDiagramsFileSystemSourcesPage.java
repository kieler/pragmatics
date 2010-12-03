/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2010 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kaom.importer.ptolemy.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.dialogs.WizardResourceImportPage;

import de.cau.cs.kieler.kaom.importer.ptolemy.utils.Utils;


/**
 * A wizard page to import files from the file system.
 * 
 * @author cds
 */
public class ImportDiagramsFileSystemSourcesPage extends WizardResourceImportPage {
    
    /**
     * Provides the folder tree to the resource selection group.
     * 
     * @author cds
     */
    private static class FolderContentProvider implements ITreeContentProvider {
        /**
         * {@inheritDoc}
         */
        public void dispose() {
            // Nothing to be done here
        }
        
        /**
         * {@inheritDoc}
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
        
        /**
         * {@inheritDoc}
         */
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof ExtendedFileSystemInputElement) {
                return new Object[] {
                        ((ExtendedFileSystemInputElement) inputElement).getWrappedElement()
                };
            } else {
                return new Object[0];
            }
        }
        
        /**
         * {@inheritDoc}
         */
        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof ExtendedFileSystemElement) {
                ExtendedFileSystemElement folderElement = (ExtendedFileSystemElement) parentElement;
                
                // Check if we have already visited this element
                if (!folderElement.isVisited()) {
                    File folder = folderElement.getFile();
                    File[] items = folder.listFiles();
                    
                    if (items != null) {
                        for (File item : items) {
                            // If the item is a file, it must be a Ptolemy2 diagram file
                            if (item.isFile() && !Utils.isPtolemyFile(item.getName())) {
                                continue;
                            }
                            
                            new ExtendedFileSystemElement(item, folderElement);
                        }
                    }
                    
                    // We have now visited this element
                    folderElement.visit();
                }

                return folderElement.getFolders().getChildren();
            } else {
                return new Object[0];
            }
        }
        
        /**
         * {@inheritDoc}
         */
        public Object getParent(Object element) {
            if (element instanceof ExtendedFileSystemElement) {
                return ((ExtendedFileSystemElement) element).getParent();
            } else {
                return null;
            }
        }
        
        /**
         * {@inheritDoc}
         */
        public boolean hasChildren(Object element) {
            if (element instanceof ExtendedFileSystemElement) {
                // Check if we already know if this element has children
                ExtendedFileSystemElement folder = (ExtendedFileSystemElement) element;
                
                if (folder.isVisited()) {
                    return folder.isDirectory() && folder.getFolders().size() > 0;
                }
            }
            
            // For performance reason, every folder has children until we know better
            return true;
        }
    }
    
    
    /**
     * Provides the file list to the resource selection group. Only Ptolemy2 diagram
     * files are provided.
     * 
     * @author cds
     */
    private static class FileContentProvider implements ITreeContentProvider {
        /**
         * {@inheritDoc}
         */
        public void dispose() {
            // Nothing to be done here.
        }

        /**
         * {@inheritDoc}
         */
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Nothing to be done here
        }

        /**
         * {@inheritDoc}
         */
        public Object[] getElements(Object inputElement) {
            if (inputElement instanceof ExtendedFileSystemElement) {
                ExtendedFileSystemElement folderElement = (ExtendedFileSystemElement) inputElement;
                
                // Check if we have already visited this element
                if (!folderElement.isVisited()) {
                    File folder = folderElement.getFile();
                    File[] items = folder.listFiles();
                    
                    if (items != null) {
                        for (File item : items) {
                            // If the item is a file, it must be a Ptolemy2 diagram file
                            if (item.isFile() && !Utils.isPtolemyFile(item.getName())) {
                                continue;
                            }
                            
                            new ExtendedFileSystemElement(item, folderElement);
                        }
                    }
                    
                    // We have now visited this element
                    folderElement.visit();
                }

                return folderElement.getFiles().getChildren();
            } else {
                return new Object[0];
            }
        }

        /**
         * {@inheritDoc}
         */
        public Object[] getChildren(Object parentElement) {
            return new Object[0];
        }

        /**
         * {@inheritDoc}
         */
        public Object getParent(Object element) {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public boolean hasChildren(Object element) {
            return false;
        }
        
    }
    
    
    // CONSTANTS
    private static final String PAGE_NAME = "importDiagramsFileSystemSourcesPage";
    
    private static final String SETT_ROOT_DIR = PAGE_NAME + ".rootDir.value";
    private static final String SETT_ROOT_DIR_HISTORY = PAGE_NAME + ".rootDir.history";
    
    private static final int HISTORY_SIZE = 15;
    
    // UI WIDGETS
    private Composite container;
    private Label rootDirLabel;
    private Combo rootDirCombo;
    private Button rootDirBrowseButton;
    private Composite sourceGroupComposite;
    private Composite buttonBarComposite;
    private Button selectAllButton;
    private Button deselectAllButton;
    
    private FolderContentProvider folderContentProvider;
    private FileContentProvider fileContentProvider;
    
    private DirectoryDialog directoryDialog;
    
    
    /**
     * Constructs a new instance.
     * 
     * @param selection the selection the wizard was called on.
     */
    public ImportDiagramsFileSystemSourcesPage(IStructuredSelection selection) {
        super(PAGE_NAME, selection);
        
        this.setTitle("Import from file system");
        this.setMessage("Select the files to import from the local file system.");
    }
    
    /**
     * Returns the selected source files to import. This method may take a while
     * to complete and shows its progress using a progress monitor.
     * 
     * @return list of selected source files.
     */
    public List<File> getSourceFiles() {
        List<File> sourceFiles = new ArrayList<File>();
        List<?> selectedResources = this.getSelectedResources();
        
        for (Object resource : selectedResources) {
            if (resource instanceof ExtendedFileSystemElement) {
                ExtendedFileSystemElement element = (ExtendedFileSystemElement) resource;
                sourceFiles.add(element.getFile());
            }
        }
        
        return sourceFiles;
    }
    
    /**
     * Returns the target container path entered by the user. The container might
     * not exist yet.
     * 
     * @return the container to import to.
     */
    public IPath getTargetContainerPath() {
        return this.getContainerFullPath();
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void createOptionsGroup(Composite parent) {
        // We don't want the options group to be created
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createSourceGroup(Composite parent) {
        GridLayout gl;
        
        // Container
        container = new Composite(parent, SWT.NONE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        gl = new GridLayout(3, false);
        gl.verticalSpacing = 5;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        gl.marginBottom = 5;
        container.setLayout(gl);
        
        // Root Dir Label
        rootDirLabel = new Label(container, SWT.NONE);
        rootDirLabel.setText("From directory:");
        
        // Root Dir Combo
        rootDirCombo = new Combo(container, SWT.DROP_DOWN);
        rootDirCombo.setToolTipText("The contents of this directory will be shown below.");
        rootDirCombo.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        // Root Dir Browse Button
        rootDirBrowseButton = new Button(container, SWT.NONE);
        rootDirBrowseButton.setText("Browse...");
        rootDirBrowseButton.setToolTipText("Opens a folder browse dialog.");
        
        // Directory Dialog
        directoryDialog = new DirectoryDialog(parent.getShell(), SWT.SAVE | SWT.SHEET);
        directoryDialog.setText("Root Directory");
        directoryDialog.setText("Select a directory to import from.");
        
        // Source Group Composite
        sourceGroupComposite = new Composite(container, SWT.NONE);
        sourceGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

        gl = new GridLayout(1, false);
        gl.verticalSpacing = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        sourceGroupComposite.setLayout(gl);
        
        // File Selection Group
        folderContentProvider = new FolderContentProvider();
        fileContentProvider = new FileContentProvider();
        createFileSelectionGroup(sourceGroupComposite);
        
        // Button Bar Composite
        buttonBarComposite = new Composite(container, SWT.NONE);
        buttonBarComposite.setLayoutData(
                new GridData(SWT.END, SWT.BEGINNING, false, false, 3, 1));

        gl = new GridLayout(2, true);
        gl.verticalSpacing = 0;
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        buttonBarComposite.setLayout(gl);
        
        // Select All Button
        selectAllButton = new Button(buttonBarComposite, SWT.NONE);
        selectAllButton.setText("Select All");
        selectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        // Deselect All Button
        deselectAllButton = new Button(buttonBarComposite, SWT.NONE);
        deselectAllButton.setText("Deselect All");
        deselectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        // Event Listeners
        rootDirCombo.addFocusListener(new FocusAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void focusLost(FocusEvent e) {
                applyNewRootDir(rootDirCombo.getText());
            }
        });
        
        rootDirBrowseButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                doBrowse();
            }
        });
        
        selectAllButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                setAllSelections(true);
                updatePageCompletion();
            }
        });
        
        deselectAllButton.addSelectionListener(new SelectionAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                setAllSelections(false);
                updatePageCompletion();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean validateSourceGroup() {
        List<?> selectedResources = this.getSelectedResources();
        
        if (selectedResources == null || selectedResources.isEmpty()) {
            setErrorMessage("Select at least one file to import.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void restoreWidgetValues() {
        super.restoreWidgetValues();
        
        IDialogSettings settings = this.getDialogSettings();
        if (settings == null) {
            return;
        }
        
        // Root Dir Combo
        rootDirCombo.setText(Utils.getSetting(settings, SETT_ROOT_DIR, ""));
        rootDirCombo.setItems(
                Utils.getSettingArray(settings, SETT_ROOT_DIR_HISTORY, new String[0]));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveWidgetValues() {
        super.saveWidgetValues();

        IDialogSettings settings = this.getDialogSettings();
        if (settings == null) {
            return;
        }
        
        // Root Dir Combo
        settings.put(SETT_ROOT_DIR, rootDirCombo.getText());
        settings.put(SETT_ROOT_DIR_HISTORY, rootDirCombo.getItems());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ITreeContentProvider getFileProvider() {
        return fileContentProvider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ITreeContentProvider getFolderProvider() {
        return folderContentProvider;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setWizard(IWizard newWizard) {
        super.setWizard(newWizard);
        
        // Set the options page as the previous page
        this.setPreviousPage(((ImportDiagramsWizard) newWizard).getOptionsPage());
    }
    
    /**
     * Saves the user's settings to be restored the next time the wizard opens.
     */
    public void saveDialogSettings() {
        saveWidgetValues();
    }

    /**
     * Opens the Browse Directory dialog and opens the selected directory, if any.
     */
    private void doBrowse() {
        String pathString = directoryDialog.open();
        
        if (pathString != null) {
            applyNewRootDir(pathString);
        }
    }
    
    /**
     * Applies the given directory as the new root directory.
     * 
     * @param newDir the new root directory. If this is empty, nothing happens.
     */
    @SuppressWarnings("restriction")
    private void applyNewRootDir(String newDir) {
        if (newDir == null || newDir.trim().length() == 0) {
            return;
        }
        
        // Check if this is a directory
        File folder = new File(newDir);
        if (folder.exists() && folder.isDirectory()) {
            // Create a new ExtendedFileSystemElement
            ExtendedFileSystemElement newRoot = new ExtendedFileSystemInputElement(folder);
            
            // Update the combo box history
            rootDirCombo.setItems(Utils.insertOrMoveToTop(
                    rootDirCombo.getItems(), newDir, HISTORY_SIZE));
            rootDirCombo.setText(newDir);
            
            // Direct access to the selection group is discouraged, but we don't have another
            // choice. The superclass doesn't provide a way to change the root element. (which
            // determines what is displayed by the selection group)
            this.selectionGroup.setRoot(newRoot);
        } else {
            this.selectionGroup.setRoot(null);
        }
    }
}
