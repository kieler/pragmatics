/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Kiel University
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 * 
 *****************************************************************************/
package de.cau.cs.kieler.ksbase.core;

import org.eclipse.osgi.util.NLS;

/**
 * The externalized messages used in the de.cau.cs.kieler.ksbase package.
 * 
 * @author Michael Matzen
 * 
 */
public final class Messages extends NLS {
    /** Name of the target bundle. **/
    private static final String BUNDLE_NAME = "de.cau.cs.kieler.ksbase.core.messages"; //$NON-NLS-1$
    
    // CHECKSTYLEOFF VisibilityModifier
    /** Name of the localized message in messages.properties. **/
    public static String transformationCommandWorkflowInitializationError;
    /** Name of the localized message in messages.properties. **/
    public static String executeTransformationCommandWorkflowInvokeError;
    /** Name of the localized message in messages.properties. **/
    public static String executeTransformationEditPolicyTransformationCommandName;
    /** Name of the localized message in messages.properties. **/
    public static String workflowInitializationError;
    
    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
