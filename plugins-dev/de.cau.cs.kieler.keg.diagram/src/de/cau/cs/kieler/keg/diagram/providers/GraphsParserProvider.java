package de.cau.cs.kieler.keg.diagram.providers;

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

import de.cau.cs.kieler.keg.KEGPackage;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabel8EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeHeadLabelEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabel8EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeMidLabelEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel3EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel4EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel5EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel6EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel7EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabel8EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.EdgeTailLabelEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeLabel2EditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.NodeNodeLabelEditPart;
import de.cau.cs.kieler.keg.diagram.edit.parts.PortPortLabelEditPart;
import de.cau.cs.kieler.keg.diagram.parsers.MessageFormatParser;
import de.cau.cs.kieler.keg.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class GraphsParserProvider extends AbstractProvider implements IParserProvider {

    /**
     * @generated
     */
    private IParser nodeNodeLabel_5003Parser;

    /**
     * @generated
     */
    private IParser getNodeNodeLabel_5003Parser() {
        if (nodeNodeLabel_5003Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getNode_NodeLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            nodeNodeLabel_5003Parser = parser;
        }
        return nodeNodeLabel_5003Parser;
    }

    /**
     * @generated
     */
    private IParser nodeNodeLabel_5002Parser;

    /**
     * @generated
     */
    private IParser getNodeNodeLabel_5002Parser() {
        if (nodeNodeLabel_5002Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getNode_NodeLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            nodeNodeLabel_5002Parser = parser;
        }
        return nodeNodeLabel_5002Parser;
    }

    /**
     * @generated
     */
    private IParser portPortLabel_5001Parser;

    /**
     * @generated
     */
    private IParser getPortPortLabel_5001Parser() {
        if (portPortLabel_5001Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getPort_PortLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            portPortLabel_5001Parser = parser;
        }
        return portPortLabel_5001Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6001Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6001Parser() {
        if (edgeMidLabel_6001Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6001Parser = parser;
        }
        return edgeMidLabel_6001Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6002Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6002Parser() {
        if (edgeHeadLabel_6002Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6002Parser = parser;
        }
        return edgeHeadLabel_6002Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6003Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6003Parser() {
        if (edgeTailLabel_6003Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6003Parser = parser;
        }
        return edgeTailLabel_6003Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6006Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6006Parser() {
        if (edgeMidLabel_6006Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6006Parser = parser;
        }
        return edgeMidLabel_6006Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6007Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6007Parser() {
        if (edgeHeadLabel_6007Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6007Parser = parser;
        }
        return edgeHeadLabel_6007Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6008Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6008Parser() {
        if (edgeTailLabel_6008Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6008Parser = parser;
        }
        return edgeTailLabel_6008Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6011Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6011Parser() {
        if (edgeMidLabel_6011Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6011Parser = parser;
        }
        return edgeMidLabel_6011Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6012Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6012Parser() {
        if (edgeHeadLabel_6012Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6012Parser = parser;
        }
        return edgeHeadLabel_6012Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6013Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6013Parser() {
        if (edgeTailLabel_6013Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6013Parser = parser;
        }
        return edgeTailLabel_6013Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6016Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6016Parser() {
        if (edgeMidLabel_6016Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6016Parser = parser;
        }
        return edgeMidLabel_6016Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6017Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6017Parser() {
        if (edgeHeadLabel_6017Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6017Parser = parser;
        }
        return edgeHeadLabel_6017Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6018Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6018Parser() {
        if (edgeTailLabel_6018Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6018Parser = parser;
        }
        return edgeTailLabel_6018Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6021Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6021Parser() {
        if (edgeMidLabel_6021Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6021Parser = parser;
        }
        return edgeMidLabel_6021Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6022Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6022Parser() {
        if (edgeHeadLabel_6022Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6022Parser = parser;
        }
        return edgeHeadLabel_6022Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6023Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6023Parser() {
        if (edgeTailLabel_6023Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6023Parser = parser;
        }
        return edgeTailLabel_6023Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6026Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6026Parser() {
        if (edgeMidLabel_6026Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6026Parser = parser;
        }
        return edgeMidLabel_6026Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6027Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6027Parser() {
        if (edgeHeadLabel_6027Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6027Parser = parser;
        }
        return edgeHeadLabel_6027Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6028Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6028Parser() {
        if (edgeTailLabel_6028Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6028Parser = parser;
        }
        return edgeTailLabel_6028Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6031Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6031Parser() {
        if (edgeMidLabel_6031Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6031Parser = parser;
        }
        return edgeMidLabel_6031Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6032Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6032Parser() {
        if (edgeHeadLabel_6032Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6032Parser = parser;
        }
        return edgeHeadLabel_6032Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6033Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6033Parser() {
        if (edgeTailLabel_6033Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6033Parser = parser;
        }
        return edgeTailLabel_6033Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6036Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6036Parser() {
        if (edgeMidLabel_6036Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6036Parser = parser;
        }
        return edgeMidLabel_6036Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel_6037Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel_6037Parser() {
        if (edgeHeadLabel_6037Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_HeadLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel_6037Parser = parser;
        }
        return edgeHeadLabel_6037Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel_6038Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel_6038Parser() {
        if (edgeTailLabel_6038Parser == null) {
            EAttribute[] features = new EAttribute[] { KEGPackage.eINSTANCE.getEdge_TailLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel_6038Parser = parser;
        }
        return edgeTailLabel_6038Parser;
    }

    /**
     * @generated
     */
    protected IParser getParser(int visualID) {
        switch (visualID) {
        case NodeNodeLabelEditPart.VISUAL_ID:
            return getNodeNodeLabel_5003Parser();
        case NodeNodeLabel2EditPart.VISUAL_ID:
            return getNodeNodeLabel_5002Parser();
        case PortPortLabelEditPart.VISUAL_ID:
            return getPortPortLabel_5001Parser();
        case EdgeMidLabelEditPart.VISUAL_ID:
            return getEdgeMidLabel_6001Parser();
        case EdgeHeadLabelEditPart.VISUAL_ID:
            return getEdgeHeadLabel_6002Parser();
        case EdgeTailLabelEditPart.VISUAL_ID:
            return getEdgeTailLabel_6003Parser();
        case EdgeMidLabel2EditPart.VISUAL_ID:
            return getEdgeMidLabel_6006Parser();
        case EdgeHeadLabel2EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6007Parser();
        case EdgeTailLabel2EditPart.VISUAL_ID:
            return getEdgeTailLabel_6008Parser();
        case EdgeMidLabel3EditPart.VISUAL_ID:
            return getEdgeMidLabel_6011Parser();
        case EdgeHeadLabel3EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6012Parser();
        case EdgeTailLabel3EditPart.VISUAL_ID:
            return getEdgeTailLabel_6013Parser();
        case EdgeMidLabel4EditPart.VISUAL_ID:
            return getEdgeMidLabel_6016Parser();
        case EdgeHeadLabel4EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6017Parser();
        case EdgeTailLabel4EditPart.VISUAL_ID:
            return getEdgeTailLabel_6018Parser();
        case EdgeMidLabel5EditPart.VISUAL_ID:
            return getEdgeMidLabel_6021Parser();
        case EdgeHeadLabel5EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6022Parser();
        case EdgeTailLabel5EditPart.VISUAL_ID:
            return getEdgeTailLabel_6023Parser();
        case EdgeMidLabel6EditPart.VISUAL_ID:
            return getEdgeMidLabel_6026Parser();
        case EdgeHeadLabel6EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6027Parser();
        case EdgeTailLabel6EditPart.VISUAL_ID:
            return getEdgeTailLabel_6028Parser();
        case EdgeMidLabel7EditPart.VISUAL_ID:
            return getEdgeMidLabel_6031Parser();
        case EdgeHeadLabel7EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6032Parser();
        case EdgeTailLabel7EditPart.VISUAL_ID:
            return getEdgeTailLabel_6033Parser();
        case EdgeMidLabel8EditPart.VISUAL_ID:
            return getEdgeMidLabel_6036Parser();
        case EdgeHeadLabel8EditPart.VISUAL_ID:
            return getEdgeHeadLabel_6037Parser();
        case EdgeTailLabel8EditPart.VISUAL_ID:
            return getEdgeTailLabel_6038Parser();
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
        @SuppressWarnings("rawtypes")
        public Object getAdapter(Class adapter) {
            if (IElementType.class.equals(adapter)) {
                return elementType;
            }
            return super.getAdapter(adapter);
        }
    }

}
