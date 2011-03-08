package de.cau.cs.kieler.kex.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author pkl
 */
public final class KEXUIImages {

    private static final URL baseURL = Platform.getBundle(KEXUIPlugin.PLUGIN_ID)
            .getEntry("/icons/"); //$NON-NLS-1$

    public static final ImageDescriptor OVERLAY_WARNING_32 = create("message_warning.gif"); //$NON-NLS-1$

    public static final ImageDescriptor MESSAGE_INFO = create("message_info.gif"); //$NON-NLS-1$

    public static final ImageDescriptor FIND_CLEAR = create("find-clear.gif"); //$NON-NLS-1$

    public static final ImageDescriptor FIND_CLEAR_DISABLED = create("finde-clear-disabled.gif"); //$NON-NLS-1$

    private static ImageDescriptor create(final String name) {
        try {
            if (baseURL == null) {
                throw new MalformedURLException();
            }
            return ImageDescriptor.createFromURL(new URL(baseURL, name));
        } catch (MalformedURLException e) {
            return ImageDescriptor.getMissingImageDescriptor();
        }
    }

    private KEXUIImages() {
        // should not be called.
    }
}
