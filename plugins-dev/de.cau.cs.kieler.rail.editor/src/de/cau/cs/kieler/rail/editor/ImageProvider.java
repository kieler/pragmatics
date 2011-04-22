package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

/**
 * Imageprovider to show the custom images for the context menue
 * @author hdw
 *
 */
public class ImageProvider extends AbstractImageProvider {
 
    // The prefix for all identifiers of this image provider
    /**
     * PREFIX path
     */
     protected static final String PREFIX =
              "de.cau.cs.kieler.rail.editor.";

    // The image identifier for an EReference.
    /**
     * Icon path for IMG_TOGGLE
     */
    public static final String IMG_TOGGLE = PREFIX + "toggle";

    /**
     * Icon path for IMG_ROTATE
     */
     public static final String IMG_ROTATE = PREFIX + "rotate";

    @Override
    protected final void addAvailableImages() {
        // register the path for each image identifier
        addImageFilePath(IMG_TOGGLE, "/icons/toggle_icon.ico");
        addImageFilePath(IMG_ROTATE, "/icons/rotate_icon.ico");
    }
}
