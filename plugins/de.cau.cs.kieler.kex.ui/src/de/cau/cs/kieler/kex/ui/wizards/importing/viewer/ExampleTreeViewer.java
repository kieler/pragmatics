package de.cau.cs.kieler.kex.ui.wizards.importing.viewer;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.kex.model.Category;
import de.cau.cs.kieler.kex.model.Example;

public class ExampleTreeViewer extends TreeViewer {

    private final ExampleContentProvider contentProvider = new ExampleContentProvider();

    private final ExampleLabelProvider labelProvider = new ExampleLabelProvider();

    private class ExampleContentProvider implements ITreeContentProvider {

        public void dispose() {
            // TODO Auto-generated method stub

        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // TODO Auto-generated method stub

        }

        public Object[] getElements(Object inputElement) {
            // TODO Auto-generated method stub
            return null;
        }

        public Object[] getChildren(Object parentElement) {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getParent(Object element) {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean hasChildren(Object element) {
            // TODO Auto-generated method stub
            return false;
        }

    }

    private class ExampleLabelProvider implements ILabelProvider {

        public void addListener(final ILabelProviderListener listener) {
            // TODO Auto-generated method stub

        }

        public void dispose() {
            // TODO Auto-generated method stub

        }

        public boolean isLabelProperty(final Object element, final String property) {
            // TODO Auto-generated method stub
            return false;
        }

        public void removeListener(final ILabelProviderListener listener) {
            // TODO Auto-generated method stub

        }

        public Image getImage(final Object element) {
            if (element instanceof Category) {
                return computeIconImage(((Category) element).getIconPath(),
                        ((Category) element).getNamespaceId());
            }

            if (element instanceof Example) {
                return computeIconImage(((Example) element).getOverviewPic(),
                        ((Example) element).getNamespaceId());
            }

            return null;
        }

        private Image computeIconImage(final String imagePath, final String nameSpaceId) {
            if (imagePath != null && imagePath.length() > 0) {
                Bundle bundle = Platform.getBundle(nameSpaceId);
                URL resource = bundle.getEntry(imagePath);
                if (resource != null) {
                    ImageDescriptor descriptor = ImageDescriptor.createFromURL(resource);
                    Image image = descriptor.createImage();
                    if (image != null) {
                        return image;
                    }
                }
            }
            return null;
        }

        public String getText(final Object element) {
            if (element instanceof Category) {
                return ((Category) element).getTitle();
            }
            if (element instanceof Example) {
                return ((Example) element).getTitle();
            }
            return null;
        }

    }

    public ExampleTreeViewer(Composite parent) {
        super(parent);
        super.setContentProvider(contentProvider);
        super.setLabelProvider(labelProvider);
        super.setInput(getInitalInput());
        super.expandAll();
    }

    private Object getInitalInput() {
        return null;
    }
}
