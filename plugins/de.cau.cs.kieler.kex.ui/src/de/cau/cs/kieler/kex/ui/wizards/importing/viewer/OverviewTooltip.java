package de.cau.cs.kieler.kex.ui.wizards.importing.viewer;

import java.net.URL;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.mylyn.internal.provisional.commons.ui.GradientToolTip;
import org.eclipse.mylyn.internal.provisional.commons.ui.WorkbenchUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.ui.wizards.importing.Messages;

/**
 * @author pkl
 */
class OverviewToolTip extends GradientToolTip {

    private final Example source;

    private final Control parent;

    private final Image leftImage;

    public OverviewToolTip(Control control, Example source, Image leftImage) {
        super(control, ToolTip.RECREATE, true);
        Assert.isNotNull(source);
        this.parent = control;
        this.source = source;
        this.leftImage = leftImage;
        setHideOnMouseDown(false); // required for links to work
    }

    @Override
    protected Composite createToolTipArea(Event event, final Composite parent) {
        GridLayoutFactory.fillDefaults().applyTo(parent);

        Composite container = new Composite(parent, SWT.NULL);
        container.setBackground(null);

        Image image = null;
        if (source.getPreviewPic() != null) {
            image = computeImage(source);
            if (image != null) {
                final Image fimage = image;
                container.addDisposeListener(new DisposeListener() {
                    public void widgetDisposed(DisposeEvent e) {
                        fimage.dispose();
                    }
                });
            }
        }
        final boolean hasLearnMoreLink = source.getContact() != null
                && source.getContact().length() > 0;

        final int borderWidth = 1;
        final int fixedImageHeight = 240;
        final int fixedImageWidth = 320;
        final int heightHint = fixedImageHeight + (borderWidth * 2);
        final int widthHint = fixedImageWidth;

        final int containerWidthHintWithImage = 650;
        final int containerWidthHintWithoutImage = 500;

        GridDataFactory
                .fillDefaults()
                .grab(true, true)
                .hint(image == null ? containerWidthHintWithoutImage : containerWidthHintWithImage,
                        SWT.DEFAULT).applyTo(container);

        GridLayoutFactory.fillDefaults().numColumns((leftImage != null) ? 3 : 2).margins(5, 5)
                .spacing(3, 0).applyTo(container);

        if (leftImage != null) {
            Label imageLabel = new Label(container, SWT.NONE);
            imageLabel.setImage(leftImage);
            int imageWidthHint = leftImage.getBounds().width + 5;
            GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING)
                    .hint(imageWidthHint, SWT.DEFAULT).applyTo(imageLabel);
        }

        String summary = source.getDescription();

        Composite summaryContainer = new Composite(container, SWT.NULL);
        summaryContainer.setBackground(null);
        GridLayoutFactory.fillDefaults().applyTo(summaryContainer);

        GridDataFactory gridDataFactory = GridDataFactory.fillDefaults().grab(true, true)
                .span(image == null ? 2 : 1, 1);
        if (image != null) {
            gridDataFactory.hint(widthHint, heightHint);
        }
        gridDataFactory.applyTo(summaryContainer);

        StyledText summaryLabel = new StyledText(summaryContainer, SWT.WRAP | SWT.READ_ONLY
                | SWT.NO_FOCUS);
        summaryLabel.setText(summary);
        Point size = summaryLabel.computeSize(widthHint, SWT.DEFAULT);
        if (size.y > heightHint - 20) {
            summaryLabel.dispose();
            summaryLabel = new StyledText(summaryContainer, SWT.WRAP | SWT.READ_ONLY | SWT.NO_FOCUS
                    | SWT.V_SCROLL);
            summaryLabel.setText(summary);
        }
        summaryLabel.setBackground(null);

        GridDataFactory.fillDefaults().grab(true, true).align(SWT.BEGINNING, SWT.BEGINNING)
                .applyTo(summaryLabel);

        if (image != null) {
            final Composite imageContainer = new Composite(container, SWT.BORDER);
            GridLayoutFactory.fillDefaults().applyTo(imageContainer);

            GridDataFactory.fillDefaults().grab(false, false).align(SWT.CENTER, SWT.BEGINNING)
                    .hint(widthHint + (borderWidth * 2), heightHint).applyTo(imageContainer);

            Label imageLabel = new Label(imageContainer, SWT.NULL);
            GridDataFactory.fillDefaults().hint(widthHint, fixedImageHeight)
                    .indent(borderWidth, borderWidth).applyTo(imageLabel);
            imageLabel.setImage(image);
            imageLabel.setBackground(null);
            imageLabel.setSize(widthHint, fixedImageHeight);

            // creates a border
            imageContainer.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
        }
        if (hasLearnMoreLink) {
            Link link = new Link(summaryContainer, SWT.NULL);
            GridDataFactory.fillDefaults().grab(false, false).align(SWT.BEGINNING, SWT.CENTER)
                    .applyTo(link);
            link.setText(Messages.ToolTip_detailsLink);
            link.setBackground(null);
            link.setToolTipText(NLS.bind(Messages.ToolTip_detailsLink_tooltip, source.getContact()));
            link.addSelectionListener(new SelectionListener() {
                public void widgetSelected(SelectionEvent e) {
                    WorkbenchUtil.openUrl(source.getContact(), IWorkbenchBrowserSupport.AS_EXTERNAL);
                }

                public void widgetDefaultSelected(SelectionEvent e) {
                    widgetSelected(e);
                }
            });
        }
        if (image == null) {
            // prevent overviews with no image from providing unlimited text.
            Point optimalSize = summaryContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
            if (optimalSize.y > (heightHint + 10)) {
                ((GridData) summaryContainer.getLayoutData()).heightHint = heightHint;
                container.layout(true);
            }
        }
        // hack: cause the tooltip to gain focus so that we can capture the escape key
        // this must be done async since the tooltip is not yet visible.
        Display.getCurrent().asyncExec(new Runnable() {
            public void run() {
                if (!parent.isDisposed()) {
                    parent.setFocus();
                }
            }
        });
        return container;
    }

    private Image computeImage(Example example) {
        Bundle bundle = Platform.getBundle(example.getNamespaceId());
        URL resource = bundle.getEntry(example.getPreviewPic());
        if (resource != null) {
            ImageDescriptor descriptor = ImageDescriptor.createFromURL(resource);
            Image image = descriptor.createImage();
            return image;
        }
        return null;
    }

    public void show(Control titleControl) {
        Point titleAbsLocation = titleControl.getParent().toDisplay(titleControl.getLocation());
        Point containerAbsLocation = parent.getParent().toDisplay(parent.getLocation());
        Rectangle bounds = titleControl.getBounds();
        int relativeX = titleAbsLocation.x - containerAbsLocation.x;
        int relativeY = titleAbsLocation.y - containerAbsLocation.y;

        relativeY += bounds.height + 3;
        show(new Point(relativeX, relativeY));
    }

}
