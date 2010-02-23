package de.cau.cs.kieler.graphs.diagram.providers;

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

import de.cau.cs.kieler.graphs.GraphsPackage;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.CompoundNodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.parsers.MessageFormatParser;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class GraphsParserProvider extends AbstractProvider implements IParserProvider {

    /**
     * @generated
     */
    private IParser nodeLabel_5005Parser;

    /**
     * @generated
     */
    private IParser getNodeLabel_5005Parser() {
        if (nodeLabel_5005Parser == null) {
            EAttribute[] features = new EAttribute[] {GraphsPackage.eINSTANCE.getNode_Label()};
            MessageFormatParser parser = new MessageFormatParser(features);
            nodeLabel_5005Parser = parser;
        }
        return nodeLabel_5005Parser;
    }

    /**
     * @generated
     */
    private IParser compoundNodeLabel_5008Parser;

    /**
     * @generated
     */
    private IParser getCompoundNodeLabel_5008Parser() {
        if (compoundNodeLabel_5008Parser == null) {
            EAttribute[] features = new EAttribute[] {GraphsPackage.eINSTANCE.getNode_Label()};
            MessageFormatParser parser = new MessageFormatParser(features);
            compoundNodeLabel_5008Parser = parser;
        }
        return compoundNodeLabel_5008Parser;
    }

    /**
     * @generated
     */
    private IParser nodeLabel_5006Parser;

    /**
     * @generated
     */
    private IParser getNodeLabel_5006Parser() {
        if (nodeLabel_5006Parser == null) {
            EAttribute[] features = new EAttribute[] {GraphsPackage.eINSTANCE.getNode_Label()};
            MessageFormatParser parser = new MessageFormatParser(features);
            nodeLabel_5006Parser = parser;
        }
        return nodeLabel_5006Parser;
    }

    /**
     * @generated
     */
    private IParser compoundNodeLabel_5007Parser;

    /**
     * @generated
     */
    private IParser getCompoundNodeLabel_5007Parser() {
        if (compoundNodeLabel_5007Parser == null) {
            EAttribute[] features = new EAttribute[] {GraphsPackage.eINSTANCE.getNode_Label()};
            MessageFormatParser parser = new MessageFormatParser(features);
            compoundNodeLabel_5007Parser = parser;
        }
        return compoundNodeLabel_5007Parser;
    }

    /**
     * @generated
     */
    private IParser edgeLabel_6002Parser;

    /**
     * @generated
     */
    private IParser getEdgeLabel_6002Parser() {
        if (edgeLabel_6002Parser == null) {
            EAttribute[] features = new EAttribute[] {GraphsPackage.eINSTANCE.getEdge_Label()};
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeLabel_6002Parser = parser;
        }
        return edgeLabel_6002Parser;
    }

    /**
     * @generated
     */
    protected IParser getParser(int visualID) {
        switch (visualID) {
        case NodeLabelEditPart.VISUAL_ID:
            return getNodeLabel_5005Parser();
        case CompoundNodeLabelEditPart.VISUAL_ID:
            return getCompoundNodeLabel_5008Parser();
        case NodeLabel2EditPart.VISUAL_ID:
            return getNodeLabel_5006Parser();
        case CompoundNodeLabel2EditPart.VISUAL_ID:
            return getCompoundNodeLabel_5007Parser();
        case EdgeLabelEditPart.VISUAL_ID:
            return getEdgeLabel_6002Parser();
        }
        return null;
    }

    /**
     * Utility method that consults ParserService
     * @generated
     */
    public static IParser getParser(IElementType type, EObject object, String parserHint) {
        return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
    }

    /**
     * @generated
     */
    public IParser getParser(IAdaptable hint) {
        String vid = (String) hint.getAdapter(String.class);
        if (vid != null) {
            return getParser(GraphsVisualIDRegistry.getVisualID(vid));
        }
        View view = (View) hint.getAdapter(View.class);
        if (view != null) {
            return getParser(GraphsVisualIDRegistry.getVisualID(view));
        }
        return null;
    }

    /**
     * @generated
     */
    public boolean provides(IOperation operation) {
        if (operation instanceof GetParserOperation) {
            IAdaptable hint = ((GetParserOperation) operation).getHint();
            if (GraphsElementTypes.getElement(hint) == null) {
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
