/*
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
package de.cau.cs.kieler.kex.ui.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;
import org.eclipse.ui.part.FileEditorInput;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.model.SourceType;

/**
 * This class contains the contents to run a quick start example.
 * 
 * @author pkl
 */
public class QuickStartAction implements IIntroAction {

    /**
     * executes a quick start. Filters from the given parameters the sourcetype and prompt depending
     * on that a import. The welcomepage will closed automatically.
     * 
     * @param site
     *            , {@link IIntroSite}
     * @param params
     *            , {@link Properties}
     */
    @SuppressWarnings("restriction")
    public final void run(final IIntroSite site, final Properties params) {

        String sourceType = params.getProperty("sourceType");
        SourceType sourcetype = null;
        try {
            sourcetype = SourceType.valueOf(sourceType);
        } catch (IllegalArgumentException i) {
            showError("Could not identify sourcetype.", i.getMessage());
            return;
        }
        if (sourceType == null) {
            showError("Introtag Error", "Missing property sourceType.");
            return;
        }

        String exampleTitle = params.getProperty("exampleTitle");
        if (exampleTitle == null) {
            showError("Introtag Error", "Missing property exampleTitle.");
            return;
        }

        String projectName = params.getProperty("projectName");
        if (projectName == null) {
            showError("Introtag Error", "Missing property projectName.");
        }

        Example quickStarter = null;
        try {
            // TODO editorabhängig suchen, d.h. pluginid aus link mit
            // reinreichen und dann suchen, da exampleTitle
            // nicht unbedingt eindeutig
            quickStarter = ExampleManager.get().getExample(sourcetype, exampleTitle);
        } catch (KielerException e) {
            showError("Example loading error", e.getMessage());
            return;
        }
        if (quickStarter == null) {
            showError("Example loading error", "Could not find example with title " + exampleTitle);
            return;
        }
        ArrayList<Example> examples = new ArrayList<Example>();
        examples.add(quickStarter);

        IPath projectPath = Path.fromPortableString(projectName);
        ExampleManager.get().generateProject(projectPath);
        IntroPlugin.closeIntro();
        try {
            List<String> directOpens = ExampleManager.get().importExamples(projectPath, examples,
                    false);
            postfix(directOpens);
        } catch (KielerException e) {
            showError("Could not import example", e.getMessage());
            return;
        }
    }

    /**
     * opens up a error dialog with given title and message.
     * 
     * @param title
     *            , String
     * @param message
     *            , String
     */
    private void showError(final String title, final String message) {
        IWorkbench wb = PlatformUI.getWorkbench();
        IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
        MessageDialog.openError(win.getShell(), title, message);
    }

    /**
     * refreshes the workspace and opens up the direct open files with an editor.
     * 
     * @param directOpens
     */
    private void postfix(final List<String> directOpens) {
        // refresh workspace
        IContainer element = ResourcesPlugin.getWorkspace().getRoot();
        try {
            if (element != null) {
                element.refreshLocal(IContainer.DEPTH_INFINITE, null);
            }
        } catch (CoreException e1) {
            // do nothing
        }

        // open directopen files in editor.
        if (directOpens != null) {
            IWorkbenchWindow win = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            IWorkbenchPage page = win.getActivePage();
            for (String path : directOpens) {
                IFile[] files = ResourcesPlugin.getWorkspace().getRoot()
                        .findFilesForLocationURI(URIUtil.toURI(path), IResource.FILE);
                if (files.length == 1) {
                    IEditorDescriptor defaultEditor = PlatformUI.getWorkbench().getEditorRegistry()
                            .getDefaultEditor(files[0].getName());
                    if (defaultEditor == null) {
                        defaultEditor = PlatformUI.getWorkbench().getEditorRegistry()
                                .findEditor(IEditorRegistry.SYSTEM_EXTERNAL_EDITOR_ID);
                    }
                    try {
                        page.openEditor(new FileEditorInput(files[0]), defaultEditor.getId());
                    } catch (PartInitException e) {
                        showError("Opening Editor", e.getLocalizedMessage());
                    }
                }
            }
        }

    }
}
