package de.cau.cs.kieler.rail.editor;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class ImageProvider extends AbstractImageProvider {
 
    // The prefix for all identifiers of this image provider
    protected static final String PREFIX =
              "de.cau.cs.kieler.rail.editor.";
 
    // The image identifier for an EReference.
    public static final String IMG_TOGGLE= PREFIX + "toggle";

	public static final String IMG_ROTATE = PREFIX + "rotate";
 
    @Override
    protected void addAvailableImages() {
        // register the path for each image identifier
        addImageFilePath(IMG_TOGGLE, "/icons/toggle_icon.ico");
        addImageFilePath(IMG_ROTATE, "/icons/rotate_icon.ico");
    }
}
