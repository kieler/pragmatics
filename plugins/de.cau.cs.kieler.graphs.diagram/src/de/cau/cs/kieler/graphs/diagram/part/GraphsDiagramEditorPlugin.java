package de.cau.cs.kieler.graphs.diagram.part;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.cau.cs.kieler.core.kgraph.provider.KGraphItemProviderAdapterFactory;
import de.cau.cs.kieler.graphs.diagram.edit.policies.GraphsBaseItemSemanticEditPolicy;
import de.cau.cs.kieler.graphs.diagram.expressions.GraphsOCLFactory;
import de.cau.cs.kieler.graphs.diagram.providers.ElementInitializers;
import de.cau.cs.kieler.graphs.provider.GraphsItemProviderAdapterFactory;

/**
 * @generated
 */
public class GraphsDiagramEditorPlugin extends AbstractUIPlugin {

    /**
     * @generated
     */
    public static final String ID = "de.cau.cs.kieler.graphs.diagram"; //$NON-NLS-1$

    /**
     * @generated
     */
    public static final PreferencesHint DIAGRAM_PREFERENCES_HINT =
            new PreferencesHint(ID);

    /**
     * @generated
     */
    private static GraphsDiagramEditorPlugin instance;

    /**
     * @generated
     */
    private ComposedAdapterFactory adapterFactory;

    /**
     * @generated
     */
    private GraphsDocumentProvider documentProvider;

    /**
     * @generated
     */
    private GraphsBaseItemSemanticEditPolicy.LinkConstraints linkConstraints;

    /**
     * @generated
     */
    private ElementInitializers initializers;

    /**
     * @generated
     */
    private GraphsOCLFactory oclFactory;

    /**
     * @generated
     */
    public GraphsDiagramEditorPlugin() {
    }

    /**
     * @generated
     */
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        PreferencesHint.registerPreferenceStore(DIAGRAM_PREFERENCES_HINT,
                getPreferenceStore());
        adapterFactory = createAdapterFactory();
    }

    /**
     * @generated
     */
    public void stop(BundleContext context) throws Exception {
        adapterFactory.dispose();
        adapterFactory = null;
        linkConstraints = null;
        initializers = null;
        oclFactory = null;
        instance = null;
        super.stop(context);
    }

    /**
     * @generated
     */
    public static GraphsDiagramEditorPlugin getInstance() {
        return instance;
    }

    /**
     * @generated
     */
    protected ComposedAdapterFactory createAdapterFactory() {
        ArrayList<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        fillItemProviderFactories(factories);
        return new ComposedAdapterFactory(factories);
    }

    /**
     * @generated
     */
    protected void fillItemProviderFactories(List<AdapterFactory> factories) {
        factories.add(new GraphsItemProviderAdapterFactory());
        factories.add(new EcoreItemProviderAdapterFactory());
        factories.add(new KGraphItemProviderAdapterFactory());
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());
    }

    /**
     * @generated
     */
    public AdapterFactory getItemProvidersAdapterFactory() {
        return adapterFactory;
    }

    /**
     * @generated
     */
    public ImageDescriptor getItemImageDescriptor(Object item) {
        IItemLabelProvider labelProvider =
                (IItemLabelProvider) adapterFactory.adapt(item,
                        IItemLabelProvider.class);
        if (labelProvider != null) {
            return ExtendedImageRegistry.getInstance().getImageDescriptor(
                    labelProvider.getImage(item));
        }
        return null;
    }

    /**
     * Returns an image descriptor for the image file at the given
     * plug-in relative path.
     *
     * @generated
     * @param path the path
     * @return the image descriptor
     */
    public static ImageDescriptor getBundledImageDescriptor(String path) {
        return AbstractUIPlugin.imageDescriptorFromPlugin(ID, path);
    }

    /**
     * Respects images residing in any plug-in. If path is relative,
     * then this bundle is looked up for the image, otherwise, for absolute 
     * path, first segment is taken as id of plug-in with image
     *
     * @generated
     * @param path the path to image, either absolute (with plug-in id as first segment), or relative for bundled images
     * @return the image descriptor
     */
    public static ImageDescriptor findImageDescriptor(String path) {
        final IPath p = new Path(path);
        if (p.isAbsolute() && p.segmentCount() > 1) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(p.segment(0), p
                    .removeFirstSegments(1).makeAbsolute().toString());
        } else {
            return getBundledImageDescriptor(p.makeAbsolute().toString());
        }
    }

    /**
     * Returns an image for the image file at the given plug-in relative path.
     * Client do not need to dispose this image. Images will be disposed automatically.
     *
     * @generated
     * @param path the path
     * @return image instance
     */
    public Image getBundledImage(String path) {
        Image image = getImageRegistry().get(path);
        if (image == null) {
            getImageRegistry().put(path, getBundledImageDescriptor(path));
            image = getImageRegistry().get(path);
        }
        return image;
    }

    /**
     * Returns string from plug-in's resource bundle
     *
     * @generated
     */
    public static String getString(String key) {
        return Platform.getResourceString(getInstance().getBundle(), "%" + key); //$NON-NLS-1$
    }

    /**
     * @generated
     */
    public GraphsDocumentProvider getDocumentProvider() {
        if (documentProvider == null) {
            documentProvider = new GraphsDocumentProvider();
        }
        return documentProvider;
    }

    /**
     * @generated
     */
    public GraphsBaseItemSemanticEditPolicy.LinkConstraints getLinkConstraints() {
        return linkConstraints;
    }

    /**
     * @generated
     */
    public void setLinkConstraints(
            GraphsBaseItemSemanticEditPolicy.LinkConstraints lc) {
        this.linkConstraints = lc;
    }

    /**
     * @generated
     */
    public ElementInitializers getElementInitializers() {
        return initializers;
    }

    /**
     * @generated
     */
    public void setElementInitializers(ElementInitializers i) {
        this.initializers = i;
    }

    /**
     * @generated
     */
    public GraphsOCLFactory getGraphsOCLFactory() {
        return oclFactory;
    }

    /**
     * @generated
     */
    public void setGraphsOCLFactory(GraphsOCLFactory f) {
        this.oclFactory = f;
    }

    /**
     * @generated
     */
    public void logError(String error) {
        logError(error, null);
    }

    /**
     * @generated
     */
    public void logError(String error, Throwable throwable) {
        if (error == null && throwable != null) {
            error = throwable.getMessage();
        }
        getLog().log(
                new Status(IStatus.ERROR, GraphsDiagramEditorPlugin.ID,
                        IStatus.OK, error, throwable));
        debug(error, throwable);
    }

    /**
     * @generated
     */
    public void logInfo(String message) {
        logInfo(message, null);
    }

    /**
     * @generated
     */
    public void logInfo(String message, Throwable throwable) {
        if (message == null && throwable != null) {
            message = throwable.getMessage();
        }
        getLog().log(
                new Status(IStatus.INFO, GraphsDiagramEditorPlugin.ID,
                        IStatus.OK, message, throwable));
        debug(message, throwable);
    }

    /**
     * @generated
     */
    private void debug(String message, Throwable throwable) {
        if (!isDebugging()) {
            return;
        }
        if (message != null) {
            System.err.println(message);
        }
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}
