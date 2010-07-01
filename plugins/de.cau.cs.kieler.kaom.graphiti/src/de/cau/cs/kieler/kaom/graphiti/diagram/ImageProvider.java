package de.cau.cs.kieler.kaom.graphiti.diagram;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class ImageProvider extends AbstractImageProvider {

    protected static final String PREFIX = "org.eclipse.graphiti.examples.tutorial.";
    
    public static final String IMAGE_EREFERENCE = PREFIX + "ereference";
   
    
    @Override
    protected void addAvailableImages() {
        // TODO Auto-generated method stub
        addImageFilePath(IMAGE_EREFERENCE,"/icons/ereference.gif");
    }

}
