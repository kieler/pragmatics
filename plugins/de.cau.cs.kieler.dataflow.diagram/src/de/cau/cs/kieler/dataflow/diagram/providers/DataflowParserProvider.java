/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.dataflow.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import de.cau.cs.kieler.dataflow.DataflowPackage;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxName2EditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.BoxNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.InputPortNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.edit.parts.OutputPortNameEditPart;
import de.cau.cs.kieler.dataflow.diagram.parsers.MessageFormatParser;
import de.cau.cs.kieler.dataflow.diagram.part.DataflowVisualIDRegistry;

/**
 * @generated
 */
public class DataflowParserProvider extends AbstractProvider implements
        IParserProvider {

    /**
     * @generated
     */
    private IParser boxName_5004Parser;

    /**
     * @generated
     */
    private IParser getBoxName_5004Parser() {
        if (boxName_5004Parser == null) {
            EAttribute[] features = new EAttribute[] { DataflowPackage.eINSTANCE
                    .getDataflowModel_Name() };
            MessageFormatParser parser = new MessageFormatParser(features);
            boxName_5004Parser = parser;
        }
        return boxName_5004Parser;
    }

    /**
     * @generated
     */
    private IParser inputPortName_5001Parser;

    /**
     * @generated
     */
    private IParser getInputPortName_5001Parser() {
        if (inputPortName_5001Parser == null) {
            EAttribute[] features = new EAttribute[] { DataflowPackage.eINSTANCE
                    .getPort_Name() };
            MessageFormatParser parser = new MessageFormatParser(features);
            inputPortName_5001Parser = parser;
        }
        return inputPortName_5001Parser;
    }

    /**
     * @generated
     */
    private IParser outputPortName_5002Parser;

    /**
     * @generated
     */
    private IParser getOutputPortName_5002Parser() {
        if (outputPortName_5002Parser == null) {
            EAttribute[] features = new EAttribute[] { DataflowPackage.eINSTANCE
                    .getPort_Name() };
            MessageFormatParser parser = new MessageFormatParser(features);
            outputPortName_5002Parser = parser;
        }
        return outputPortName_5002Parser;
    }

    /**
     * @generated
     */
    private IParser boxName_5003Parser;

    /**
     * @generated
     */
    private IParser getBoxName_5003Parser() {
        if (boxName_5003Parser == null) {
            EAttribute[] features = new EAttribute[] { DataflowPackage.eINSTANCE
                    .getDataflowModel_Name() };
            MessageFormatParser parser = new MessageFormatParser(features);
            boxName_5003Parser = parser;
        }
        return boxName_5003Parser;
    }

    /**
     * @generated
     */
    protected IParser getParser(int visualID) {
        switch (visualID) {
        case BoxNameEditPart.VISUAL_ID:
            return getBoxName_5004Parser();
        case InputPortNameEditPart.VISUAL_ID:
            return getInputPortName_5001Parser();
        case OutputPortNameEditPart.VISUAL_ID:
            return getOutputPortName_5002Parser();
        case BoxName2EditPart.VISUAL_ID:
            return getBoxName_5003Parser();
        }
        return null;
    }

    /**
     * Utility method that consults ParserService
     * @generated
     */
    public static IParser getParser(IElementType type, EObject object,
            String parserHint) {
        return ParserService.getInstance().getParser(
                new HintAdapter(type, object, parserHint));
    }

    /**
     * @generated
     */
    public IParser getParser(IAdaptable hint) {
        String vid = (String) hint.getAdapter(String.class);
        if (vid != null) {
            return getParser(DataflowVisualIDRegistry.getVisualID(vid));
        }
        View view = (View) hint.getAdapter(View.class);
        if (view != null) {
            return getParser(DataflowVisualIDRegistry.getVisualID(view));
        }
        return null;
    }

    /**
     * @generated
     */
    public boolean provides(IOperation operation) {
        if (operation instanceof GetParserOperation) {
            IAdaptable hint = ((GetParserOperation) operation).getHint();
            if (DataflowElementTypes.getElement(hint) == null) {
                return false;
            }
            return getParser(hint) != null;
        }
        return false;
    }

    /**
     * @generated
     */
    private static class HintAdapter extends ParserHintAdapter {

        /**
         * @generated
         */
        private final IElementType elementType;

        /**
         * @generated
         */
        public HintAdapter(IElementType type, EObject object, String parserHint) {
            super(object, parserHint);
            assert type != null;
            elementType = type;
        }

        /**
         * @generated
         */
        public Object getAdapter(Class adapter) {
            if (IElementType.class.equals(adapter)) {
                return elementType;
            }
            return super.getAdapter(adapter);
        }
    }

}
