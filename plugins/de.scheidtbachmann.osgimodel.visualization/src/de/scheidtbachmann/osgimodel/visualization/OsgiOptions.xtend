package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.SynthesisOption

/**
 * A collection of all options that are used in the OSGi synthesis and their required enums.
 */
class OsgiOptions {
    /** The Bundle category */
    public static final SynthesisOption BUNDLE = SynthesisOption.createCategory("Bundle Options")
    
    /** The Overview category */
    public static final SynthesisOption OVERVIEW= SynthesisOption.createCategory("Overview Options")
    
    /** The enum holding all possible values for the {@link #BUNDLE_TEXT} option. */
    enum BundleTextType {
        Id, Name
    }
    
    /** Option for setting the main text to be shown in unexpanded bundles. */
    public static final SynthesisOption BUNDLE_TEXT = SynthesisOption.createChoiceOption("Bundle text",
        #[BundleTextType.Id, BundleTextType.Name], BundleTextType.Id).setCategory(BUNDLE)
        
    /** Option for shortening all IDs by the prefix 'de.scheidtbachmann */
    public static final SynthesisOption SHORTEN_BY_DE_SCHEIDTBACHMANN = SynthesisOption.createCheckOption(
        "Shorten IDs by 'de.scheidtbachmann'", false).setCategory(OVERVIEW)
    
    /** Option for filtering an overview by only bundles starting with 'de.scheidtbachmann' */
    public static final SynthesisOption FILTER_BY_DE_SCHEIDTBACHMANN = SynthesisOption.createCheckOption(
        "Filter by IDs starting with 'de.scheidtbachmann", false).setCategory(OVERVIEW)
        
    /** Option for limiting the length of descriptive texts. */
    public static final SynthesisOption DESCRIPTION_LENGTH = SynthesisOption.createRangeOption(
        "Description text length", 0, 500, 1, 20).setCategory(BUNDLE)
}