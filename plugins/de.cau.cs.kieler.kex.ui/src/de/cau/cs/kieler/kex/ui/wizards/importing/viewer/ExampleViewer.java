package de.cau.cs.kieler.kex.ui.wizards.importing.viewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.themes.IThemeManager;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.kex.controller.ExampleManager;
import de.cau.cs.kieler.kex.model.Example;
import de.cau.cs.kieler.kex.ui.KEXUIImages;
import de.cau.cs.kieler.kex.ui.wizards.importing.Messages;

public class ExampleViewer {

    public class ExampleBorderPaintListener implements PaintListener {
        public void paintControl(PaintEvent e) {
            Composite composite = (Composite) e.widget;
            Rectangle bounds = composite.getBounds();
            GC gc = e.gc;
            gc.setLineStyle(SWT.LINE_DOT);
            gc.drawLine(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y);
        }
    }

    private class ExampleDescriptorItemUi implements PropertyChangeListener, Runnable {

        private final Example example;

        private final Button checkbox;

        private final Label iconLabel;

        private final Label nameLabel;

        private final ToolItem infoButton;

        private final Link providerLabel;

        private final Label description;

        private final Composite checkboxContainer;

        private final Composite exampleContainer;

        private final Display display;

        private Image iconImage;

        public ExampleDescriptorItemUi(final Example example, Composite categoryChildrenContainer,
                Color background) {
            display = categoryChildrenContainer.getDisplay();
            this.example = example;
            exampleContainer = new Composite(categoryChildrenContainer, SWT.NULL);

            configureLook(exampleContainer, background);
            GridDataFactory.fillDefaults().grab(true, false).applyTo(exampleContainer);
            GridLayout layout = new GridLayout(4, false);
            layout.marginLeft = 7;
            layout.marginTop = 2;
            layout.marginBottom = 2;
            exampleContainer.setLayout(layout);

            checkboxContainer = new Composite(exampleContainer, SWT.NULL);
            configureLook(checkboxContainer, background);
            GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.BEGINNING).span(1, 2)
                    .applyTo(checkboxContainer);
            GridLayoutFactory.fillDefaults().spacing(1, 1).numColumns(2).applyTo(checkboxContainer);

            checkbox = new Button(checkboxContainer, SWT.CHECK);
            checkbox.setText(" "); //$NON-NLS-1$
            // help UI tests
            // TODO add example id to example again, title should only used for representation.
            checkbox.setData("exampleId", example.getTitle()); //$NON-NLS-1$
            configureLook(checkbox, background);
            checkbox.setSelection(installableExamples.contains(example));
            checkbox.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    bodyScrolledComposite.showControl(exampleContainer);
                }
            });

            GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(checkbox);

            iconLabel = new Label(checkboxContainer, SWT.NULL);
            configureLook(iconLabel, background);
            GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(iconLabel);

            // if (example.getPreviewPic() != null) {
            // iconImage = computeIconImage(example.getPreviewPic(), ICON_SIZE, false);
            // if (iconImage != null) {
            // iconLabel.setImage(iconImage);
            // }
            // }

            nameLabel = new Label(exampleContainer, SWT.NULL);
            configureLook(nameLabel, background);
            GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.CENTER)
                    .applyTo(nameLabel);
            nameLabel.setFont(h2Font);
            nameLabel.setText(example.getTitle());

            providerLabel = new Link(exampleContainer, SWT.RIGHT);
            configureLook(providerLabel, background);
            GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).applyTo(providerLabel);
            ToolBar toolBar = new ToolBar(exampleContainer, SWT.FLAT);
            toolBar.setBackground(background);

            infoButton = new ToolItem(toolBar, SWT.PUSH);
            infoButton.setImage(infoImage);
            infoButton.setToolTipText(Messages.Tooltip_showOverview);
            hookTooltip(toolBar, infoButton, exampleContainer, nameLabel, example, null);
            GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).applyTo(toolBar);

            description = new Label(exampleContainer, SWT.NULL | SWT.WRAP);
            configureLook(description, background);

            GridDataFactory.fillDefaults().grab(true, false).span(3, 1).hint(100, SWT.DEFAULT)
                    .applyTo(description);
            String descriptionText = example.getDescription();
            int maxDescriptionLength = 162;
            if (descriptionText.length() > maxDescriptionLength) {
                descriptionText = descriptionText.substring(0, maxDescriptionLength);
            }
            description.setText(descriptionText.replaceAll("(\\r\\n)|\\n|\\r", " ")); //$NON-NLS-1$ //$NON-NLS-2$

            // always disabled color to make it less prominent
            providerLabel.setForeground(colorDisabled);

            checkbox.addSelectionListener(new SelectionListener() {
                public void widgetDefaultSelected(SelectionEvent e) {
                    widgetSelected(e);
                }

                public void widgetSelected(SelectionEvent e) {
                    modifySelection(checkbox.getSelection());
                }
            });
            MouseListener exampleItemMouseListener = new MouseAdapter() {
                @Override
                public void mouseUp(MouseEvent e) {
                    boolean selected = !checkbox.getSelection();
                    modifySelection(selected);
                    checkbox.setSelection(selected);
                }
            };
            checkboxContainer.addMouseListener(exampleItemMouseListener);
            exampleContainer.addMouseListener(exampleItemMouseListener);
            iconLabel.addMouseListener(exampleItemMouseListener);
            nameLabel.addMouseListener(exampleItemMouseListener);
            description.addMouseListener(exampleItemMouseListener);
        }

        public void propertyChange(PropertyChangeEvent evt) {
            display.asyncExec(this);
        }

        public void run() {
        }

        protected void modifySelection(boolean selected) {
            ExampleViewer.this.modifySelection(example, selected);
        }

    }

    // e3.5 replace with SWT.ICON_CANCEL
    public static final int ICON_CANCEL = 1 << 8;

    private static final int MINIMUM_HEIGHT = 100;

    private static boolean useNativeSearchField(Composite composite) {
        if (useNativeSearchField == null) {
            useNativeSearchField = Boolean.FALSE;
            Text testText = null;
            try {
                testText = new Text(composite, SWT.SEARCH | ICON_CANCEL);
                useNativeSearchField = new Boolean((testText.getStyle() & ICON_CANCEL) != 0);
            } finally {
                if (testText != null) {
                    testText.dispose();
                }
            }

        }
        return useNativeSearchField;
    }

    private static final String COLOR_WHITE = "white"; //$NON-NLS-1$

    private static final String COLOR_DARK_GRAY = "DarkGray"; //$NON-NLS-1$

    private static final String GRADIENT_START = "GradientStartGray"; //$NON-NLS-1$

    private static final String GRADIENT_END = "GradientEndGray"; //$NON-NLS-1$

    private static Boolean useNativeSearchField;

    private final List<Example> installableExamples = new ArrayList<Example>();

    private Composite body;

    private final List<Resource> disposables;

    private Font h2Font;

    private Font h1Font;

    private Color colorWhite;

    private Text filterText;

    private WorkbenchJob refreshJob;

    private String previousFilterText = ""; //$NON-NLS-1$

    private Pattern filterPattern;

    private Label clearFilterTextControl;

    private Set<String> installedFeatures;

    private Image infoImage;

    private Cursor handCursor;

    private Color colorCategoryGradientStart;

    private Color colorCategoryGradientEnd;

    private Color colorDisabled;

    private ScrolledComposite bodyScrolledComposite;

    private boolean verifyUpdateSiteAvailability;

    private final Map<ExampleDescriptorKind, Boolean> exampleDescriptorKindToVisibility = new HashMap<ExampleDescriptorKind, Boolean>();

    {
        for (ExampleDescriptorKind kind : ExampleDescriptorKind.values()) {
            exampleDescriptorKindToVisibility.put(kind, true);
        }
    }

    private Dictionary<Object, Object> environment;

    private boolean complete;

    private final IRunnableContext context;

    private final IShellProvider shellProvider;

    private final SelectionProviderAdapter selectionProvider;

    private Control control;

    private List<Example> allExamples;

    private int minimumHeight;

    private final List<ViewerFilter> filters = new ArrayList<ViewerFilter>();

    public ExampleViewer(IShellProvider shellProvider, IRunnableContext context) {
        this.shellProvider = shellProvider;
        this.context = context;
        this.selectionProvider = new SelectionProviderAdapter();
        this.allExamples = Collections.emptyList();
        this.disposables = new ArrayList<Resource>();
        setMinimumHeight(MINIMUM_HEIGHT);
        setComplete(false);
    }

    public void addSelectionChangedListener(ISelectionChangedListener listener) {
        selectionProvider.addSelectionChangedListener(listener);
    }

    private void clearDisposables() {
        disposables.clear();
        h1Font = null;
        h2Font = null;
        infoImage = null;
        handCursor = null;
        colorCategoryGradientStart = null;
        colorCategoryGradientEnd = null;
    }

    private void clearFilterText() {
        filterText.setText(""); //$NON-NLS-1$
        filterTextChanged();
    }

    private Image computeIconImage(Example example, int dimension, boolean fallback) {
        String imagePath = example.getPreviewPic();
        if (imagePath != null && imagePath.length() > 0) {
            Bundle bundle = Platform.getBundle(example.getNamespaceId());
            URL resource = bundle.getEntry(imagePath);
            if (resource != null) {
                ImageDescriptor descriptor = ImageDescriptor.createFromURL(resource);
                Image image = descriptor.createImage();
                if (image != null) {
                    disposables.add(image);
                    return image;
                }
            }
        }
        return null;
    }

    private void configureLook(final Control control, Color background) {
        control.setBackground(background);
    }

    /**
     * cause the UI to respond to a change in visibility filters.
     * 
     */
    public void exampleDescriptorKindVisibilityUpdated() {
        createBodyContents();
    }

    public void createBodyContents() {
        // remove any existing contents
        for (Control child : body.getChildren()) {
            child.dispose();
        }
        clearDisposables();
        allExamples = new ArrayList<Example>();
        initializeCursors();
        initializeImages();
        initializeFonts();
        initializeColors();

        GridLayoutFactory.fillDefaults().applyTo(body);

        bodyScrolledComposite = new ScrolledComposite(body, SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.BORDER);

        configureLook(bodyScrolledComposite, colorWhite);
        GridDataFactory.fillDefaults().grab(true, true).applyTo(bodyScrolledComposite);

        final Composite scrolledContents = new Composite(bodyScrolledComposite, SWT.NONE);
        configureLook(scrolledContents, colorWhite);
        scrolledContents.setRedraw(false);
        try {
            createExampleContents(scrolledContents);
        } finally {
            scrolledContents.layout(true);
            scrolledContents.setRedraw(true);
        }
        Point size = scrolledContents.computeSize(body.getSize().x, SWT.DEFAULT, true);
        scrolledContents.setSize(size);

        bodyScrolledComposite.setExpandHorizontal(true);
        bodyScrolledComposite.setMinWidth(100);
        bodyScrolledComposite.setExpandVertical(true);
        bodyScrolledComposite.setMinHeight(1);

        bodyScrolledComposite.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(final ControlEvent e) {
                // XXX small offset in case list has a scroll bar
                Point size = scrolledContents.computeSize(body.getSize().x - 20, SWT.DEFAULT, true);
                scrolledContents.setSize(size);
                bodyScrolledComposite.setMinHeight(size.y);
            }
        });

        bodyScrolledComposite.setContent(scrolledContents);

        Dialog.applyDialogFont(body);
        // we've changed it so it needs to know
        body.layout(true);
    }

    private Label createClearFilterTextControl(final Composite filterContainer,
            final Text filterText) {
        final Image inactiveImage = KEXUIImages.FIND_CLEAR_DISABLED.createImage();
        final Image activeImage = KEXUIImages.FIND_CLEAR.createImage();
        final Image pressedImage = new Image(filterContainer.getDisplay(), activeImage,
                SWT.IMAGE_GRAY);

        final Label clearButton = new Label(filterContainer, SWT.NONE);
        clearButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        clearButton.setImage(inactiveImage);
        clearButton
                .setToolTipText(de.cau.cs.kieler.kex.ui.wizards.importing.Messages.ClearButton_toolTip);
        clearButton.setBackground(filterContainer.getDisplay().getSystemColor(
                SWT.COLOR_LIST_BACKGROUND));
        clearButton.addMouseListener(new MouseAdapter() {
            private MouseMoveListener fMoveListener;

            private boolean isMouseInButton(final MouseEvent e) {
                Point buttonSize = clearButton.getSize();
                return 0 <= e.x && e.x < buttonSize.x && 0 <= e.y && e.y < buttonSize.y;
            }

            @Override
            public void mouseDown(final MouseEvent e) {
                clearButton.setImage(pressedImage);
                fMoveListener = new MouseMoveListener() {
                    private boolean fMouseInButton = true;

                    public void mouseMove(final MouseEvent e) {
                        boolean mouseInButton = isMouseInButton(e);
                        if (mouseInButton != fMouseInButton) {
                            fMouseInButton = mouseInButton;
                            clearButton.setImage(mouseInButton ? pressedImage : inactiveImage);
                        }
                    }
                };
                clearButton.addMouseMoveListener(fMoveListener);
            }

            @Override
            public void mouseUp(MouseEvent e) {
                if (fMoveListener != null) {
                    clearButton.removeMouseMoveListener(fMoveListener);
                    fMoveListener = null;
                    boolean mouseInButton = isMouseInButton(e);
                    clearButton.setImage(mouseInButton ? activeImage : inactiveImage);
                    if (mouseInButton) {
                        clearFilterText();
                        filterText.setFocus();
                    }
                }
            }
        });
        clearButton.addMouseTrackListener(new MouseTrackListener() {
            public void mouseEnter(MouseEvent e) {
                clearButton.setImage(activeImage);
            }

            public void mouseExit(MouseEvent e) {
                clearButton.setImage(inactiveImage);
            }

            public void mouseHover(MouseEvent e) {
            }
        });
        clearButton.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                inactiveImage.dispose();
                activeImage.dispose();
                pressedImage.dispose();
            }
        });
        clearButton.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            @Override
            public void getName(AccessibleEvent e) {
                e.result = de.cau.cs.kieler.kex.ui.wizards.importing.Messages.ClearButton_accessibleListener;
            }
        });
        clearButton.getAccessible().addAccessibleControlListener(new AccessibleControlAdapter() {
            @Override
            public void getRole(AccessibleControlEvent e) {
                e.detail = ACC.ROLE_PUSHBUTTON;
            }
        });
        return clearButton;
    }

    public void createControl(Composite parent) {
        createRefreshJob();

        Composite container = new Composite(parent, SWT.NULL);
        container.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(final DisposeEvent e) {
                refreshJob.cancel();
                if (disposables != null) {
                    for (Resource resource : disposables) {
                        resource.dispose();
                    }
                    clearDisposables();
                }
            }
        });
        GridLayout layout = new GridLayout(1, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        container.setLayout(layout);
        //
        { // header
            Composite header = new Composite(container, SWT.NULL);
            GridLayoutFactory.fillDefaults().applyTo(header);
            GridDataFactory.fillDefaults().grab(true, false).applyTo(header);

            // TODO: refresh button?
            Composite filterContainer = new Composite(header, SWT.NULL);
            GridDataFactory.fillDefaults().grab(true, false).applyTo(filterContainer);

            Label label = new Label(filterContainer, SWT.NULL);
            label.setText(Messages.FilterLabel);

            Composite textFilterContainer;
            boolean nativeSearch = useNativeSearchField(header);
            if (nativeSearch) {
                textFilterContainer = new Composite(filterContainer, SWT.NULL);
            } else {
                textFilterContainer = new Composite(filterContainer, SWT.BORDER);
                textFilterContainer.setBackground(header.getDisplay().getSystemColor(
                        SWT.COLOR_LIST_BACKGROUND));
            }
            GridDataFactory.fillDefaults().grab(true, false).applyTo(textFilterContainer);
            GridLayoutFactory.fillDefaults().numColumns(2).applyTo(textFilterContainer);

            if (nativeSearch) {
                filterText = new Text(textFilterContainer, SWT.SINGLE | SWT.BORDER | SWT.SEARCH
                        | ICON_CANCEL);
            } else {
                filterText = new Text(textFilterContainer, SWT.SINGLE);
            }

            filterText.addModifyListener(new ModifyListener() {
                public void modifyText(final ModifyEvent e) {
                    filterTextChanged();
                }
            });
            if (nativeSearch) {
                filterText.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetDefaultSelected(final SelectionEvent e) {
                        if (e.detail == ICON_CANCEL) {
                            clearFilterText();
                        }
                    }
                });
                GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(filterText);
            } else {
                GridDataFactory.fillDefaults().grab(true, false).applyTo(filterText);
                clearFilterTextControl = createClearFilterTextControl(textFilterContainer,
                        filterText);
                clearFilterTextControl.setVisible(false);
            }

            // filter
            // buttons

            for (final ExampleDescriptorKind kind : ExampleDescriptorKind.values()) {
                final Button checkbox = new Button(filterContainer, SWT.CHECK);
                checkbox.setSelection(isVisible(kind));
                checkbox.setText(getFilterLabel(kind));
                checkbox.addSelectionListener(new SelectionListener() {
                    public void widgetDefaultSelected(final SelectionEvent e) {
                        widgetSelected(e);
                    }

                    public void widgetSelected(SelectionEvent e) {
                        boolean selection = checkbox.getSelection();
                        setVisibility(kind, selection);
                        exampleDescriptorKindVisibilityUpdated();
                    }
                });
            }

            GridLayoutFactory.fillDefaults().numColumns(filterContainer.getChildren().length)
                    .applyTo(filterContainer);
        }
        { // container
            body = new Composite(container, SWT.NULL);
            GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, minimumHeight)
                    .applyTo(body);
        }
        Dialog.applyDialogFont(container);
        setControl(container);
    }

    public void setMinimumHeight(int minimumHeight) {
        this.minimumHeight = minimumHeight;
        if (body != null) {
            GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, minimumHeight)
                    .applyTo(body);
        }
    }

    public static int getMinimumHeight() {
        return MINIMUM_HEIGHT;
    }

    private void createExampleContents(final Composite container) {

        Color background = container.getBackground();
        GridLayoutFactory.fillDefaults().numColumns(2).spacing(0, 0).applyTo(container);

        List<String> categories = new ArrayList<String>(ExampleManager.get()
                .getNonEmptyCategories());

        Composite categoryChildrenContainer = null;
        for (String category : categories) {
            // TODO think about isEmpty in categories here
            // if (isEmpty(category)) {
            // don't add empty categories
            // continue;
            // }
            { // category header
                final GradientCanvas categoryHeaderContainer = new GradientCanvas(container,
                        SWT.NONE);
                categoryHeaderContainer.setSeparatorVisible(true);
                categoryHeaderContainer.setSeparatorAlignment(SWT.TOP);
                categoryHeaderContainer.setBackgroundGradient(new Color[] {
                        colorCategoryGradientStart, colorCategoryGradientEnd }, new int[] { 100 },
                        true);
                categoryHeaderContainer.putColor(IFormColors.H_BOTTOM_KEYLINE1,
                        colorCategoryGradientStart);
                categoryHeaderContainer.putColor(IFormColors.H_BOTTOM_KEYLINE2,
                        colorCategoryGradientEnd);

                GridDataFactory.fillDefaults().span(2, 1).applyTo(categoryHeaderContainer);
                GridLayoutFactory.fillDefaults().numColumns(3).margins(5, 5).equalWidth(false)
                        .applyTo(categoryHeaderContainer);

                // TODO think about icon / picture handling
                Label iconLabel = new Label(categoryHeaderContainer, SWT.NULL);
                // if (category.getIcon() != null) {
                // Image image = computeIconImage(category.getSource(), category.getIcon(),
                // 48, true);
                // if (image != null) {
                // iconLabel.setImage(image);
                // }
                // }
                iconLabel.setBackground(null);
                GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.BEGINNING).span(1, 2)
                        .applyTo(iconLabel);

                Label nameLabel = new Label(categoryHeaderContainer, SWT.NULL);
                nameLabel.setFont(h1Font);
                nameLabel.setText(category);
                nameLabel.setBackground(null);

                GridDataFactory.fillDefaults().grab(true, false).applyTo(nameLabel);
                // if (hasTooltip(category)) {
                // ToolBar toolBar = new ToolBar(categoryHeaderContainer, SWT.FLAT);
                // toolBar.setBackground(null);
                // ToolItem infoButton = new ToolItem(toolBar, SWT.PUSH);
                // infoButton.setImage(infoImage);
                // infoButton.setToolTipText(Messages.Tooltip_showOverview);
                // hookTooltip(toolBar, infoButton, categoryHeaderContainer, nameLabel,
                // .getSource(), category.getOverview(), null);
                // GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).applyTo(toolBar);
                // } else {
                new Label(categoryHeaderContainer, SWT.NULL).setText(" "); //$NON-NLS-1$
                // }
                Label description = new Label(categoryHeaderContainer, SWT.WRAP);
                GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(100, SWT.DEFAULT)
                        .applyTo(description);
                description.setBackground(null);
                description.setText(category);
            }

            categoryChildrenContainer = new Composite(container, SWT.NULL);
            configureLook(categoryChildrenContainer, background);
            GridDataFactory.fillDefaults().span(2, 1).grab(true, false)
                    .applyTo(categoryChildrenContainer);
            GridLayoutFactory.fillDefaults().spacing(0, 0).applyTo(categoryChildrenContainer);

            int numChildren = 0;
            for (Example example : ExampleManager.get().getExamples().values()) {
                if (!example.contains(category)) {
                    continue;
                }
                // TODO think of alphabetic sorting of examples.
                // Collections.sort(examples, new DiscoveryConnectorComparator(category));
                if (isFiltered(example)) {
                    continue;
                }

                if (++numChildren > 1) {
                    // a separator between example descriptors
                    Composite border = new Composite(categoryChildrenContainer, SWT.NULL);
                    GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 1)
                            .applyTo(border);
                    GridLayoutFactory.fillDefaults().applyTo(border);
                    border.addPaintListener(new ExampleBorderPaintListener());
                }
                new ExampleDescriptorItemUi(example, categoryChildrenContainer, background);
                allExamples.add(example);
            }
            // }
            // last one gets a border
            Composite border = new Composite(categoryChildrenContainer, SWT.NULL);
            GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 1).applyTo(border);
            GridLayoutFactory.fillDefaults().applyTo(border);
            border.addPaintListener(new ExampleBorderPaintListener());
        }
        container.layout(true);
        container.redraw();
    }

    protected Pattern createPattern(String filterText) {
        if (filterText == null || filterText.length() == 0) {
            return null;
        }
        String regex = filterText;
        regex.replace("\\", "\\\\").replace("?", ".").replace("*", ".*?"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    }

    private void createRefreshJob() {
        refreshJob = new WorkbenchJob("filter") { //$NON-NLS-1$

            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
                if (filterText.isDisposed()) {
                    return Status.CANCEL_STATUS;
                }
                String text = filterText.getText();
                text = text.trim();

                if (!previousFilterText.equals(text)) {
                    previousFilterText = text;
                    filterPattern = createPattern(previousFilterText);
                    if (clearFilterTextControl != null) {
                        clearFilterTextControl.setVisible(filterPattern != null);
                    }
                    createBodyContents();
                }
                return Status.OK_STATUS;
            }
        };
        refreshJob.setSystem(true);
    }

    private boolean filterMatches(String text) {
        return text != null && filterPattern.matcher(text).find();
    }

    private void filterTextChanged() {
        refreshJob.cancel();
        refreshJob.schedule(200L);
    }

    public Control getControl() {
        return control;
    }

    private String getFilterLabel(ExampleDescriptorKind kind) {
        switch (kind) {
        case DESCRIPTION:
            return Messages.Filter_description;
        case CATEGORY:
            return Messages.Filter_category;
        default:
            throw new IllegalStateException(kind.name());
        }
    }

    public List<Example> getInstallableExamples() {
        return installableExamples;
    }

    public IStructuredSelection getSelection() {
        return (IStructuredSelection) selectionProvider.getSelection();
    }

    private Shell getShell() {
        return shellProvider.getShell();
    }

    public boolean getVerifyUpdateSiteAvailability() {
        return verifyUpdateSiteAvailability;
    }

    private void hookRecursively(Control control, Listener listener) {
        control.addListener(SWT.Dispose, listener);
        control.addListener(SWT.MouseHover, listener);
        control.addListener(SWT.MouseMove, listener);
        control.addListener(SWT.MouseExit, listener);
        control.addListener(SWT.MouseDown, listener);
        control.addListener(SWT.MouseWheel, listener);
        if (control instanceof Composite) {
            for (Control child : ((Composite) control).getChildren()) {
                hookRecursively(child, listener);
            }
        }
    }

    private void hookTooltip(final Control parent, final Widget tipActivator,
            final Control exitControl, final Control titleControl, Example source, Image image) {
        final OverviewToolTip toolTip = new OverviewToolTip(parent, source, image);
        Listener listener = new Listener() {
            public void handleEvent(Event event) {
                switch (event.type) {
                case SWT.MouseHover:
                    toolTip.show(titleControl);
                    break;
                case SWT.Dispose:
                case SWT.MouseWheel:
                    toolTip.hide();
                    break;
                }

            }
        };
        tipActivator.addListener(SWT.Dispose, listener);
        tipActivator.addListener(SWT.MouseWheel, listener);
        if (image != null) {
            tipActivator.addListener(SWT.MouseHover, listener);
        }
        Listener selectionListener = new Listener() {
            public void handleEvent(Event event) {
                toolTip.show(titleControl);
            }
        };
        tipActivator.addListener(SWT.Selection, selectionListener);
        Listener exitListener = new Listener() {
            public void handleEvent(Event event) {
                switch (event.type) {
                case SWT.MouseWheel:
                    toolTip.hide();
                    break;
                case SWT.MouseExit:
                    /*
                     * Check if the mouse exit happened because we move over the tooltip
                     */
                    Rectangle containerBounds = exitControl.getBounds();
                    Point displayLocation = exitControl.getParent().toDisplay(containerBounds.x,
                            containerBounds.y);
                    containerBounds.x = displayLocation.x;
                    containerBounds.y = displayLocation.y;
                    if (containerBounds.contains(Display.getCurrent().getCursorLocation())) {
                        break;
                    }
                    toolTip.hide();
                    break;
                }
            }
        };
        hookRecursively(exitControl, exitListener);
    }

    private void initializeColors() {
        ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
        if (colorWhite == null) {
            if (!colorRegistry.hasValueFor(COLOR_WHITE)) {
                colorRegistry.put(COLOR_WHITE, new RGB(255, 255, 255));
            }
            colorWhite = colorRegistry.get(COLOR_WHITE);
        }
        if (colorDisabled == null) {

            if (!colorRegistry.hasValueFor(COLOR_DARK_GRAY)) {
                colorRegistry.put(COLOR_DARK_GRAY, new RGB(0x69, 0x69, 0x69));
            }
            colorDisabled = colorRegistry.get(COLOR_DARK_GRAY);
        }
        // if (colorCategoryGradientStart == null) {
        // if (!colorRegistry.hasValueFor(GRADIENT_START)) {
        // colorRegistry.put(GRADIENT_START, new RGB(240, 240, 240));
        // }
        // colorCategoryGradientStart = colorRegistry.get(GRADIENT_START);
        // if (!colorRegistry.hasValueFor(GRADIENT_END)) {
        // colorRegistry.put(GRADIENT_END, new RGB(220, 220, 220));
        // }
        // colorCategoryGradientStart = colorRegistry.get(GRADIENT_END);
        // }
        // TODO generate colors self not with mylyn.
        IThemeManager themeManager = PlatformUI.getWorkbench().getThemeManager();
        if (colorCategoryGradientStart == null) {
            colorCategoryGradientStart = themeManager.getCurrentTheme().getColorRegistry()
                    .get("org.eclipse.mylyn.tasks.ui.colors.category.gradient.start");
            colorCategoryGradientEnd = themeManager.getCurrentTheme().getColorRegistry()
                    .get("org.eclipse.mylyn.tasks.ui.colors.category.gradient.start");
        }
    }

    private void initializeCursors() {
        if (handCursor == null) {
            handCursor = new Cursor(getShell().getDisplay(), SWT.CURSOR_HAND);
            disposables.add(handCursor);
        }
    }

    private FontDescriptor createFontDescriptor(int style, float heightMultiplier) {
        Font baseFont = JFaceResources.getDialogFont();
        FontData[] fontData = baseFont.getFontData();
        FontData[] newFontData = new FontData[fontData.length];
        for (int i = 0; i < newFontData.length; i++) {
            newFontData[i] = new FontData(fontData[i].getName(),
                    (int) (fontData[i].getHeight() * heightMultiplier), fontData[i].getStyle()
                            | style);
        }
        return FontDescriptor.createFrom(newFontData);
    }

    private void initializeFonts() {
        // create a level-2 heading font
        if (h2Font == null) {
            h2Font = new Font(Display.getCurrent(), createFontDescriptor(SWT.BOLD, 1.25f)
                    .getFontData());
            disposables.add(h2Font);
        }
        // create a level-1 heading font
        if (h1Font == null) {
            h1Font = new Font(Display.getCurrent(), createFontDescriptor(SWT.BOLD, 1.35f)
                    .getFontData());
            disposables.add(h1Font);
        }
    }

    private void initializeImages() {
        if (infoImage == null) {
            infoImage = KEXUIImages.MESSAGE_INFO.createImage();
            disposables.add(infoImage);
        }
    }

    public boolean isComplete() {
        return complete;
    }

    public void addFilter(ViewerFilter filter) {
        filters.add(filter);
    }

    public void removeFilter(ViewerFilter filter) {
        filters.remove(filter);
    }

    private boolean isFiltered(Example example) {
        if (filterPattern != null) {
            if (!(filterMatches(example.getTitle()) || filterMatches(example.getDescription())
                    || filterMatches(example.getAuthor()) || filterMatches(example.getContact()))) {
                return true;
            }
        }

        // TODO think of the filtered Examples.
        for (ViewerFilter filter : filters) {
            if (!filter.select(null, null, example)) {
                return true;
            }
        }
        return false;
    }

    /**
     * indicate if the given kind of example is currently visible in the wizard
     * 
     */
    public boolean isVisible(ExampleDescriptorKind kind) {
        if (kind == null) {
            throw new IllegalArgumentException();
        }
        return exampleDescriptorKindToVisibility.get(kind);
    }

    private void modifySelection(final Example example, boolean selected) {
        modifySelectionInternal(example, selected);
        updateState();
    }

    private void modifySelectionInternal(final Example example, final boolean selected) {
        if (selected) {
            installableExamples.add(example);
        } else {
            installableExamples.remove(example);
        }
    }

    public void setComplete(final boolean completeParam) {
        this.complete = completeParam;
    }

    protected void setControl(final Control controlParam) {
        this.control = controlParam;
    }

    public void removeSelectionChangedListener(ISelectionChangedListener listener) {
        selectionProvider.removeSelectionChangedListener(listener);
    }

    public void setVerifyUpdateSiteAvailability(final boolean verifyUpdateSiteAvailabilityParam) {
        this.verifyUpdateSiteAvailability = verifyUpdateSiteAvailabilityParam;
    }

    /**
     * configure the page to show or hide example descriptors of the given kind
     */
    public void setVisibility(ExampleDescriptorKind kind, boolean visible) {
        if (kind == null) {
            throw new IllegalArgumentException();
        }
        exampleDescriptorKindToVisibility.put(kind, visible);
    }

    public void updateContents() {
        selectionProvider.setSelection(StructuredSelection.EMPTY);
        createBodyContents();
    }

    private void updateState() {
        setComplete(!installableExamples.isEmpty());
        selectionProvider.setSelection(new StructuredSelection(getInstallableExamples()));
    }

}
