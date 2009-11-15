/**
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
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.LinkedList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;

/**
 * The main storage and management class. Contains a list of currently registered editors. Handles
 * import, export and Xtend file parsing.
 * 
 * @author Michael Matzen - mim AT informatik.uni-kiel.de
 * 
 */
public final class TransformationManager {

    /** The currently registered editors. **/
    private LinkedList<EditorTransformationSettings> registeredEditors;
    /**
     * Manage state, set to true when {@link TransformationManager.initializeTransformations} has
     * been called.
     **/
    private boolean isInitialized;

    /** Logging interface. **/
    private static final ILog LOG = KSBasEPlugin.getDefault().getLog();

    /** Transformation-Manager instance. **/
    public static final TransformationManager INSTANCE = new TransformationManager();

    /**
     * FileNameFilter to check a file for a valid extension. The extension has to be '.sbase' to be
     * valid.
     * 
     * @author Michael Matzen - mim AT informatik.uni-kiel.de
     * 
     */
    private static class XtendFileNameFilter implements FilenameFilter {
        /**
         * Checks if the given input is valid.
         * 
         * @param dir
         *            The file directory.
         * @param name
         *            The filename.
         * @return True if the extension of the given file is .sbase
         */
        public boolean accept(final File dir, final String name) {
            if (name.endsWith(".sbase")) {
                return true;
            }
            return false;
        }

    }

    /**
     * Since this is a singleton class the constructor is private.
     */
    private TransformationManager() {
        isInitialized = false;
        registeredEditors = new LinkedList<EditorTransformationSettings>();
    }

    /**
     * Gets the currently registered editors.
     * 
     * @return A list of EditorTransformationSettings
     */
    public LinkedList<EditorTransformationSettings> getEditors() {
        return registeredEditors;
    }

    /**
     * Gets the list of user defined editors. This is done by checking if the contributor is 'null'
     * and is used by the preference page.
     * 
     * @return A list of EditorTransformationSettings
     */
    public LinkedList<EditorTransformationSettings> getUserDefinedEditors() {
        LinkedList<EditorTransformationSettings> userSettings = new LinkedList<EditorTransformationSettings>();
        for (EditorTransformationSettings ed : registeredEditors) {
            if (ed.getContributor() == null) {
                userSettings.add(ed);
            }
        }
        return userSettings;
    }

    /**
     * Tries to find an editor with it's name.
     * 
     * @param editor
     *            The editor's name.
     * @return The first editor in the list of registered editors which has the given name
     */
    public EditorTransformationSettings getEditorById(final String editor) {
        if (editor != null && editor.length() > 0) {
            for (EditorTransformationSettings settings : registeredEditors) {
                if (settings.getEditor().equals(editor)) {
                    return settings;
                }
            }
        }
        return null;
    }

    /**
     * Tries to find an user defined editor with it's name. Only returns an editor if the name
     * matches and the editor has no contributor. Called by the preference pages.
     * 
     * @param editor
     *            The editor's name.
     * @return The first editor in the list of registered editors which has the given name
     */
    public EditorTransformationSettings getUserDefinedEditorByName(final String editor) {
        if (editor != null && editor.length() > 0) {
            for (EditorTransformationSettings settings : registeredEditors) {
                if (settings.getContributor() == null && settings.getEditor().equals(editor)) {
                    return settings;
                }
            }
        }
        return null;
    }

    /**
     * @return True if the manager is initialized.
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Adds a new editor to the list of registered editors.
     * 
     * @param editor
     *            The EditorTransformationSetting that describes the editor
     */
    public void addEditor(final EditorTransformationSettings editor) {
        if (editor != null) {
            if (registeredEditors == null) {
                registeredEditors = new LinkedList<EditorTransformationSettings>();
            }
            if (registeredEditors.contains(editor)) {
                LOG.log(new Status(IStatus.INFO, KSBasEPlugin.PLUGIN_ID,
                        "Unable to add the same editor twice."));

            } else {
                registeredEditors.add(editor);
            }
        }
    }

    /**
     * Adds a new editor to the list of registered editors. This class creates an empty
     * EditorTransformationSetting with the given editorName.
     * 
     * @param editorName
     *            The name of the new editor
     * @return The newly created EditorTransformationSettings
     */
    public EditorTransformationSettings addEditor(final String editorName) {
        if (editorName != null && editorName.length() > 0) {
            if (editorName.length() > 0) {
                EditorTransformationSettings editor = new EditorTransformationSettings(editorName);
                registeredEditors.add(editor);
                return editor;
            }
        }
        return null;
    }

    /**
     * Removes an editor from the list of registered user defined editors.
     * 
     * @param editor
     *            The fqn class name of the editor
     */
    public void removeEditor(final String editor) {
        if (editor != null && editor.length() > 0) {
            for (int i = 0; i < registeredEditors.size(); ++i) {
                if (registeredEditors.get(i).getEditor().equals(editor)
                        && registeredEditors.get(i).getContributor() == null) {
                    registeredEditors.remove(i);
                    // Remove exsiting .sbase file settings for editor:
                    try {
                        IPath metaPath = KSBasEPlugin.getDefault().getStateLocation();
                        File storedSettings = metaPath.append(editor + ".sbase").toFile();
                        if (storedSettings.exists()) {
                            if (!storedSettings.delete()) {
                                throw new SecurityException("Unable to delete stored settings!");
                            }
                        }
                    } catch (IllegalStateException e) {
                        LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while deleting settings: file could not be found."));
                    } catch (SecurityException e) {
                        LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings: not allowed to delete file."));
                    }
                }
            }
        }
    }

    /**
     * Stores the user defined settings in the KSbasE state location folder.
     */
    public void storeUserDefinedTransformations() {

        LinkedList<EditorTransformationSettings> userEditors = getUserDefinedEditors();
        if (userEditors != null && userEditors.size() > 0) {
            IPath metaPath = KSBasEPlugin.getDefault().getStateLocation();
            for (EditorTransformationSettings editor : userEditors) {
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(new FileOutputStream(metaPath.append(
                            editor.getEditor() + ".sbase").toFile()));
                    oos.writeObject(editor);

                } catch (FileNotFoundException e) {
                    LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "Error while storing settings: file not found."));
                } catch (IOException e) {
                    LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                            "Error while parsing settings: file could not be read."));
                } finally {
                    if (oos != null) {
                        try {
                            oos.flush();
                            oos.close();
                        } catch (IOException e) {
                          //ignoring nested exception
                        }
                    }
                }
            }

        }
    }

    /**
     * Loads the editor settings either from the extension point settings or the preference store.
     */
    public void initializeTransformations() {
        registeredEditors = new LinkedList<EditorTransformationSettings>();

        // From extension points first:
        IConfigurationElement[] configurations = Platform.getExtensionRegistry()
                .getConfigurationElementsFor("de.cau.cs.kieler.ksbase.configuration");
        if (configurations == null) {
            LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                    "Invalid KSBasE extension point found."));
            return;
        }
        for (IConfigurationElement settings : configurations) {
            // Check for valid Configuration:
            if (settings.getAttribute("editorId") == null
                    || settings.getAttribute("packageName") == null) {
                LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                        "Invalid KSBasE extension point found."));
                continue;
            }

            EditorTransformationSettings editor = new EditorTransformationSettings(settings
                    .getAttribute("editorId"));
            editor.setContributor(settings.getContributor());
            editor.setContext(settings.getAttribute("contextId"));
            editor.setDefaultIcon(settings.getAttribute("defautlIcon"));
            editor.setModelPackageClass(settings.getAttribute("packageName"));

            IConfigurationElement[] transformations = settings.getChildren("transformations");
            if (transformations != null && transformations.length > 0) {
                // since we only allowed one single <transformations> child, we
                // are using it w/o iteration
                for (IConfigurationElement t : transformations[0].getChildren("transformation")) {
                    // Check for valid Configuration:
                    if (t == null || t.getAttribute("name") == null
                            || t.getAttribute("transformation") == null
                            || t.getAttribute("transformationId") == null) {
                        LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Invalid KSBasE configuration found. Please check "
                                        + "transformations defined for " + editor.getEditor()));
                    }
                    Transformation transformation = new Transformation(t.getAttribute("name"), t
                            .getAttribute("transformation"));
                    transformation.setKeyboardShortcut(t.getAttribute("keyboardShortcut"));
                    transformation.setTransformationId(t.getAttribute("transformationId"));
                    transformation.setIcon(t.getAttribute("icon"));
                    editor.addTransformation(transformation);
                }
            }
            // Read menu contributions
            IConfigurationElement[] menus = settings.getChildren("menus");

            if (menus != null && menus.length > 0) {
                // since we only allowed one single <menuContribution> child, we
                // are using it w/o iteration
                for (IConfigurationElement c : menus[0].getChildren("menuContribution")) {
                    KSBasEMenuContribution contrib = new KSBasEMenuContribution(c
                            .getAttribute("locationURI"));
                    for (IConfigurationElement m : c.getChildren("menu")) {
                        KSBasEMenuContribution menu = new KSBasEMenuContribution(m
                                .getAttribute("id"));
                        menu.setLabel(m.getAttribute("label"));
                        for (IConfigurationElement com : m.getChildren()) {
                            menu.addCommand(com.getAttribute("transformationId"));
                        }
                        contrib.addSubMenu(menu);
                    }
                    for (IConfigurationElement com : c.getChildren("transformationCommand")) {
                        contrib.addCommand(com.getAttribute("transformationId"));
                    }
                    editor.addMenuContribution(contrib);
                }
            }

            // Read Xtend file from extension point configuration
            InputStream path;
            StringBuffer contentBuffer = new StringBuffer();
            try {
                if (settings.getContributor() != null && settings.getAttribute("XtendFile") != null) {
                    Bundle bundle = Platform.getBundle(settings.getContributor().getName());
                    if (bundle != null) {
                        URL urlPath = bundle.getEntry("/" + settings.getAttribute("XtendFile"));
                        if (urlPath != null) {
                            path = urlPath.openStream();
                            while (path.available() > 0) {
                                contentBuffer.append((char) path.read());

                            }
                        }
                    }
                    if (contentBuffer != null) {
                        editor.setExtFile(contentBuffer.toString(), false);
                    }
                }
            } catch (IOException e) {
                LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                        "KSBasE configuration exception: Can't read Xtend file."));
            }

            registeredEditors.add(editor);
        }
        // Now let's load the settings from the state location:
        File metaPath = KSBasEPlugin.getDefault().getStateLocation().toFile();
        if (metaPath != null && metaPath.isDirectory()) {

            File[] files = metaPath.listFiles(new XtendFileNameFilter());

            if (files != null && files.length > 0) {
                for (File file : files) {
                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        Object content = ois.readObject();
                        if (content instanceof EditorTransformationSettings) {
                            registeredEditors.add((EditorTransformationSettings) content);
                        }
                    } catch (FileNotFoundException e) {
                        LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings: file not found."));
                    } catch (IOException e) {
                        LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings: file not found."));
                    } catch (ClassNotFoundException e) {
                        LOG.log(new Status(IStatus.WARNING, KSBasEPlugin.PLUGIN_ID,
                                "Error while parsing settings."));
                    } finally {
                        if (ois != null) {
                            try {
                                ois.close();
                            } catch (IOException e) {
                                //ignoring nested exception
                            }
                        }
                    }
                }
            }
        }
        isInitialized = true;
    }
}
