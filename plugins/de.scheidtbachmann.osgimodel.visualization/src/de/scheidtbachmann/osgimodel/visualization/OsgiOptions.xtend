package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.SynthesisOption

/**
 * A collection of all options that are used in the OSGi synthesis and their required enums.
 * 
 * @author nre
 */
class OsgiOptions {
    /** Category option containing bundle specific options. */
    
    public static final SynthesisOption BUNDLE_CATEGORY = SynthesisOption.createCategory("Bundle", false)
    
    
    /** The enum holding all possible values for the {@link #BUNDLE_TEXT} option. */
    enum SimpleTextType {
        Id, Name
    }
    
    /** Option for setting the main text to be shown in unexpanded objects. */
    public static final SynthesisOption SIMPLE_TEXT = SynthesisOption.createChoiceOption("Shown text",
        #[SimpleTextType.Id, SimpleTextType.Name], SimpleTextType.Id)
        
    /** Option for shortening all IDs by the prefix in the option. */
    public static final SynthesisOption SHORTEN_BY = SynthesisOption.createTextOption(
        "Shorten IDs by")
    
    /** Option for limiting the length of descriptive texts. */
    public static final SynthesisOption DESCRIPTION_LENGTH = SynthesisOption.createRangeOption(
        "Description text length", 0, 500, 1, 20)
    
    /** Option indicating whether service components should be shown in bundles. */
    public static final SynthesisOption BUNDLE_SHOW_SERVICE_COMPONENTS = SynthesisOption.createCheckOption(
        "Show Service Components", true).setCategory(BUNDLE_CATEGORY)
    
    /** Category option containing options for text filtering. */
    public static final SynthesisOption FILTER_CATEGORY = SynthesisOption.createCategory("Filter", false)
    
    /** Option for filtering an overview by only elements matching the regular expression in the option. */
    public static final SynthesisOption FILTER_BY = SynthesisOption.createTextOption(
        "Filter by IDs with (Java regex)", ".*.*").setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown bundles. */
    public static final SynthesisOption FILTER_BUNDLES = SynthesisOption.createCheckOption(
        "Apply to bundles", true).setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown bundle categories. */
    public static final SynthesisOption FILTER_BUNDLE_CATEGORIES = SynthesisOption.createCheckOption(
        "Apply to bundle categories", false).setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown features. */
    public static final SynthesisOption FILTER_FEATURES = SynthesisOption.createCheckOption(
        "Apply to features", true).setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown package objects. */
    public static final SynthesisOption FILTER_PACKAGE_OBJECTS = SynthesisOption.createCheckOption(
        "Apply to package objects", true).setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown products. */
    public static final SynthesisOption FILTER_PRODUCTS = SynthesisOption.createCheckOption(
        "Apply to products", true).setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown service components. */
    public static final SynthesisOption FILTER_SERVICE_COMPONENTS = SynthesisOption.createCheckOption(
        "Apply to service components", false).setCategory(FILTER_CATEGORY)
    
    /** Option that indicates if the {@link #FILTER_BY} option should be applied to shown service interfaces. */
    public static final SynthesisOption FILTER_SERVICE_INTERFACES = SynthesisOption.createCheckOption(
        "Apply to service interfaces", true).setCategory(FILTER_CATEGORY)
}