package de.cau.cs.kieler.kex.ui.wizards.importing;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "de.cau.cs.kieler.kex.ui.wizards.importing.messages"; //$NON-NLS-1$

    public static String ExampleCategory_must_specify_connectorCategory_id;

    public static String Message_with_cause;

    public static String ClearButton_toolTip;

    public static String ExampleAuthor;

    public static String ClearButton_accessibleListener;

    public static String FilterLabel;

    public static String NoMatchingItems_withFilterText;

    public static String NoMatchingItems_filteredType;

    public static String NoMatchingItems_noFilter;

    public static String Filter_description;

    public static String Filter_category;

    public static String UnexpectedException;

    public static String Tooltip_showOverview;

    public static String ToolTip_detailsLink;

    public static String ToolTip_detailsLink_tooltip;

    public static String MainPage_pageDescription;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
