/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse Rich Client
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2011 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.graphs.klighd;

import org.eclipse.elk.core.util.Maybe;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.osgi.framework.BundleContext;

import com.google.common.base.Function;

import de.cau.cs.kieler.klighd.ISourceProxy;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author chsch
 */
public class GraphsPlugin extends AbstractUIPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "de.cau.cs.kieler.graphs.klighd"; //$NON-NLS-1$

    // The shared instance
    private static GraphsPlugin plugin;

    /**
     * The constructor.
     */
    public GraphsPlugin() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static GraphsPlugin getDefault() {
        return plugin;
    }

    /**
     * Factory method providing an implementation of {@link ISourceProxy} enabling the execution of
     * KLighD operations properly within Xtext's {@link IUnitOfWork IUnitOfWorks}.
     * 
     * @param editorPart
     *            the {@link XtextEditor} whose model shall be accessed
     * @return the desired {@link ISourceProxy} implementation
     */
    public static ISourceProxy getXtextModelAccessProxy(final IEditorPart editorPart) {
        if (editorPart instanceof XtextEditor) {
            final XtextEditor xtextEditor = (XtextEditor) editorPart;

            return new ISourceProxy() {

                public <T> T execute(final Function<Object, T> function) {
                    if (xtextEditor.getDocument() == null) {
                        return null;
                    }

                    final Maybe<T> result = new Maybe<T>();

                    xtextEditor.getDocument().readOnly(new IUnitOfWork.Void<XtextResource>() {

                        @Override
                        public void process(final XtextResource state) throws Exception {
                            if (!state.getContents().isEmpty()) {
                                result.set(function.apply(state.getContents().get(0)));
                            }
                        };
                    });

                    return result.get();
                }
            };

        } else {
            return null;
        }
    }
}
