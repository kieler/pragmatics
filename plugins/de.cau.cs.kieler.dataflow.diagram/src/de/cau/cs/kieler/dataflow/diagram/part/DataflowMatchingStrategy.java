/*
* KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
* 
* http://www.informatik.uni-kiel.de/rtsys/kieler/
* 
* Copyright 2009 by
* + Christian-Albrechts-University of Kiel
*   + Department of Computer Science
*     + Real-Time and Embedded Systems Group
* 
* This code is provided under the terms of the Eclipse Public License (EPL).
* See the file epl-v10.html for the license text.
*/
package de.cau.cs.kieler.dataflow.diagram.part;

import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;

/**
 * @generated
 */
public class DataflowMatchingStrategy implements IEditorMatchingStrategy {

    /**
     * @generated
     */
    public boolean matches(IEditorReference editorRef, IEditorInput input) {
        IEditorInput editorInput;
        try {
            editorInput = editorRef.getEditorInput();
        }
        catch (PartInitException e) {
            return false;
        }

        if (editorInput.equals(input)) {
            return true;
        }
        if (editorInput instanceof URIEditorInput && input instanceof URIEditorInput) {
            return ((URIEditorInput) editorInput).getURI()
                    .equals(((URIEditorInput) input).getURI());
        }
        return false;
    }

}
