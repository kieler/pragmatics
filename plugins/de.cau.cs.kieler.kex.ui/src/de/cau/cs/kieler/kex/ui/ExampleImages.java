package de.cau.cs.kieler.kex.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author pkl
 */
public class ExampleImages {

    private static final URL baseURL = Platform.getBundle(KEXUIPlugin.PLUGIN_ID)
            .getEntry("/icons/"); //$NON-NLS-1$

    public static final ImageDescriptor OVERLAY_WARNING_32 = create("message_warning.gif"); //$NON-NLS-1$

    public static final ImageDescriptor MESSAGE_INFO = create("message_info.gif"); //$NON-NLS-1$

    private static ImageDescriptor create(String name) {
        try {
            if (baseURL == null) {
                throw new MalformedURLException();
            }
            return ImageDescriptor.createFromURL(new URL(baseURL, name));
        } catch (MalformedURLException e) {
            return ImageDescriptor.getMissingImageDescriptor();
        }
    }
}
