package de.scheidtbachmann.osgimodel.visualization

import de.cau.cs.kieler.klighd.SynthesisOption

/**
 * A collection of all options that are used in the OSGi synthesis and their required enums.
 * 
 * @author nre
 */
class OsgiOptions {
//    /** The Bundle category */
//    public static final SynthesisOption BUNDLE = SynthesisOption.createCategory("Bundle Options")
//    
//    /** The Overview category */
//    public static final SynthesisOption OVERVIEW= SynthesisOption.createCategory("Overview Options")
    
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
    
    /** Option for filtering an overview by only bundles starting with the id in the option. */
    public static final SynthesisOption FILTER_BY = SynthesisOption.createTextOption(
        "Filter by IDs starting with 'de.scheidtbachmann")
        
    /** Option for limiting the length of descriptive texts. */
    public static final SynthesisOption DESCRIPTION_LENGTH = SynthesisOption.createRangeOption(
        "Description text length", 0, 500, 1, 20)
}