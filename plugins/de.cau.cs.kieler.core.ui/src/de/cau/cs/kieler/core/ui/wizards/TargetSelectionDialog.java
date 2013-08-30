/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2013 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.core.ui.wizards;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;

import com.google.common.collect.Lists;

/**
 * A dialog that allows the user to select a project or folder to choose. It is a somewhat simpler
 * replacement for {@code ContainerSelectionDialog}, which is in the {@code org.eclipse.ui.ide} plug-in
 * which we don't want to have a dependency on.
 * 
 * <p>Use it like this:</p>
 * <pre>
 * TargetSelectionDialog dialog = new TargetSelectionDialog(shell);
 * dialog.setTitle("My dialog title");
 * dialog.setMessage("Select a target container, user!");
 * dialog.setInitialSelections(new Object[] {initiallySelectedContainer});
 * 
 * if (dialog.open() == Dialog.OK) {
 *     Object[] result = dialog.getResult();
 *     // The result will contain exactly one IPath element you can do stuff with
 * }
 * </pre>
 * 
 * @author cds
 */
public final class TargetSelectionDialog extends SelectionDialog {
    /** The tree viewer used to present the available targets to the user. */
    private TreeViewer targetTreeViewer = null;
    
    
    /**
     * Creates a new instance with the given resource initially selected.
     * 
     * @param parentShell parent shell of the dialog.
     */
    protected TargetSelectionDialog(final Shell parentShell) {
        super(parentShell);
        setShellStyle(getShellStyle() | SWT.SHEET);
    }
    
    
    /** Default width of the selection tree viewer. */
    private static final int WIDTH_HINT = 350;
    /** Default height of the selection tree viewer. */
    private static final int HEIGHT_HINT = 350;
    
    @Override
    protected Control createDialogArea(final Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        
        // Make sure we display the dialog message
        createMessageArea(area);
        
        // Use a drill down composite as a wrapper to the tree viewer to add fancy navigation
        DrillDownComposite drillDownComposite = new DrillDownComposite(area, SWT.BORDER);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.widthHint = WIDTH_HINT;
        gd.heightHint = HEIGHT_HINT;
        drillDownComposite.setLayoutData(gd);
        
        // Create the actual tree viewer with a special filter set on it
        targetTreeViewer = new TreeViewer(drillDownComposite, SWT.H_SCROLL | SWT.V_SCROLL);
        targetTreeViewer.setContentProvider(new BaseWorkbenchContentProvider());
        targetTreeViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
        targetTreeViewer.setFilters(new ViewerFilter[] {new ContainerAndOpenProjectsFilter()});
        
        targetTreeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
        drillDownComposite.setChildTree(targetTreeViewer);
        
        // Show initial selection, if any
        List<?> initialSelections = getInitialElementSelections();
        if (initialSelections != null && !initialSelections.isEmpty()) {
            // We only care for the first element; check if it is a container
            IContainer container = null;
            
            if (initialSelections.get(0) instanceof IContainer) {
                container = (IContainer) initialSelections.get(0);
            } else if (initialSelections.get(0) instanceof IResource) {
                container = ((IResource) initialSelections.get(0)).getParent();
            }
            
            if (container != null) {
                List<IContainer> containerAncestors = Lists.newLinkedList();
                
                IContainer parentContainer = container.getParent();
                while (parentContainer != null) {
                    containerAncestors.add(0, parentContainer);
                    parentContainer = parentContainer.getParent();
                }
                
                targetTreeViewer.setExpandedElements(containerAncestors.toArray());
                targetTreeViewer.setSelection(new StructuredSelection(container), true);
            }
        }
        
        return area;
    }

    @Override
    protected void okPressed() {
        // Assemble the results
        ITreeSelection selection = (ITreeSelection) targetTreeViewer.getSelection();
        if (!selection.isEmpty()) {
            IContainer target = (IContainer) selection.getFirstElement();
            setResult(Lists.newArrayList(target.getFullPath().makeRelative()));
        }
        
        // Hide the dialog
        super.okPressed();
    }
    

    /**
     * A filter that lets everything through unless at least one of the following conditions is met:
     * <ul>
     *   <li>The element is not an {@code IContainer} instance.</li>
     *   <li>The element is not accessible.</li>
     *   <li>The element is an {@code IProject} instance, but the project is not open.</li> 
     * </ul>
     * 
     * @author cds
     */
    private static final class ContainerAndOpenProjectsFilter extends ViewerFilter {
        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            // The element is only allowed if it is a container
            if (!(element instanceof IContainer)) {
                return false;
            }
            
            IContainer container = (IContainer) element;
            
            // The element is only allowed if it is accessible
            if (!container.isAccessible()) {
                return false;
            }
            
            // If the element is a project, it must be open
            if (container instanceof IProject && !((IProject) container).isOpen()) {
                return false;
            }
            
            return true;
        }
    }
    
}
