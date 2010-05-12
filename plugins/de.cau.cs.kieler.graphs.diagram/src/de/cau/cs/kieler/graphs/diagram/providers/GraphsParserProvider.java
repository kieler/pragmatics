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
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel12EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel13EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel14EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel15EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel16EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel17EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel18EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel1EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel22EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel23EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel24EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel25EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel26EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel27EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel28EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeHeadLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel3EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel4EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel5EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel6EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel7EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabel8EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeMidLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel12EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel13EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel14EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel15EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel16EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel17EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel18EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel1EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel22EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel23EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel24EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel25EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel26EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel27EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel28EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.EdgeTailLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeLabel2EditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.NodeNodeLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.edit.parts.PortPortLabelEditPart;
import de.cau.cs.kieler.graphs.diagram.parsers.MessageFormatParser;
import de.cau.cs.kieler.graphs.diagram.part.GraphsVisualIDRegistry;

/**
 * @generated
 */
public class GraphsParserProvider extends AbstractProvider implements
        IParserProvider {

    /**
     * @generated
     */
    private IParser nodeNodeLabel_5003Parser;

    /**
     * @generated
     */
    private IParser getNodeNodeLabel_5003Parser() {
        if (nodeNodeLabel_5003Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getNode_NodeLabel() };
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
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getNode_NodeLabel() };
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
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getPort_PortLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            portPortLabel_5001Parser = parser;
        }
        return portPortLabel_5001Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6001Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6001Parser() {
        if (edgeHeadLabel1_6001Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6001Parser = parser;
        }
        return edgeHeadLabel1_6001Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6002Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6002Parser() {
        if (edgeHeadLabel2_6002Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6002Parser = parser;
        }
        return edgeHeadLabel2_6002Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6003Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6003Parser() {
        if (edgeMidLabel_6003Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6003Parser = parser;
        }
        return edgeMidLabel_6003Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6004Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6004Parser() {
        if (edgeTailLabel1_6004Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6004Parser = parser;
        }
        return edgeTailLabel1_6004Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6005Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6005Parser() {
        if (edgeTailLabel2_6005Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6005Parser = parser;
        }
        return edgeTailLabel2_6005Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6006Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6006Parser() {
        if (edgeHeadLabel1_6006Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6006Parser = parser;
        }
        return edgeHeadLabel1_6006Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6007Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6007Parser() {
        if (edgeHeadLabel2_6007Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6007Parser = parser;
        }
        return edgeHeadLabel2_6007Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6008Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6008Parser() {
        if (edgeMidLabel_6008Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6008Parser = parser;
        }
        return edgeMidLabel_6008Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6009Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6009Parser() {
        if (edgeTailLabel1_6009Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6009Parser = parser;
        }
        return edgeTailLabel1_6009Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6010Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6010Parser() {
        if (edgeTailLabel2_6010Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6010Parser = parser;
        }
        return edgeTailLabel2_6010Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6011Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6011Parser() {
        if (edgeHeadLabel1_6011Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6011Parser = parser;
        }
        return edgeHeadLabel1_6011Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6012Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6012Parser() {
        if (edgeHeadLabel2_6012Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6012Parser = parser;
        }
        return edgeHeadLabel2_6012Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6013Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6013Parser() {
        if (edgeMidLabel_6013Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6013Parser = parser;
        }
        return edgeMidLabel_6013Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6014Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6014Parser() {
        if (edgeTailLabel1_6014Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6014Parser = parser;
        }
        return edgeTailLabel1_6014Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6015Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6015Parser() {
        if (edgeTailLabel2_6015Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6015Parser = parser;
        }
        return edgeTailLabel2_6015Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6016Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6016Parser() {
        if (edgeHeadLabel1_6016Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6016Parser = parser;
        }
        return edgeHeadLabel1_6016Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6017Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6017Parser() {
        if (edgeHeadLabel2_6017Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6017Parser = parser;
        }
        return edgeHeadLabel2_6017Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6018Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6018Parser() {
        if (edgeMidLabel_6018Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6018Parser = parser;
        }
        return edgeMidLabel_6018Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6019Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6019Parser() {
        if (edgeTailLabel1_6019Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6019Parser = parser;
        }
        return edgeTailLabel1_6019Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6020Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6020Parser() {
        if (edgeTailLabel2_6020Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6020Parser = parser;
        }
        return edgeTailLabel2_6020Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6021Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6021Parser() {
        if (edgeHeadLabel1_6021Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6021Parser = parser;
        }
        return edgeHeadLabel1_6021Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6022Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6022Parser() {
        if (edgeHeadLabel2_6022Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6022Parser = parser;
        }
        return edgeHeadLabel2_6022Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6023Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6023Parser() {
        if (edgeMidLabel_6023Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6023Parser = parser;
        }
        return edgeMidLabel_6023Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6024Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6024Parser() {
        if (edgeTailLabel1_6024Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6024Parser = parser;
        }
        return edgeTailLabel1_6024Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6025Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6025Parser() {
        if (edgeTailLabel2_6025Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6025Parser = parser;
        }
        return edgeTailLabel2_6025Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6026Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6026Parser() {
        if (edgeHeadLabel1_6026Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6026Parser = parser;
        }
        return edgeHeadLabel1_6026Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6027Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6027Parser() {
        if (edgeHeadLabel2_6027Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6027Parser = parser;
        }
        return edgeHeadLabel2_6027Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6028Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6028Parser() {
        if (edgeMidLabel_6028Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6028Parser = parser;
        }
        return edgeMidLabel_6028Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6029Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6029Parser() {
        if (edgeTailLabel1_6029Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6029Parser = parser;
        }
        return edgeTailLabel1_6029Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6030Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6030Parser() {
        if (edgeTailLabel2_6030Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6030Parser = parser;
        }
        return edgeTailLabel2_6030Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6031Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6031Parser() {
        if (edgeHeadLabel1_6031Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6031Parser = parser;
        }
        return edgeHeadLabel1_6031Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6032Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6032Parser() {
        if (edgeHeadLabel2_6032Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6032Parser = parser;
        }
        return edgeHeadLabel2_6032Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6033Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6033Parser() {
        if (edgeMidLabel_6033Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6033Parser = parser;
        }
        return edgeMidLabel_6033Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6034Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6034Parser() {
        if (edgeTailLabel1_6034Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6034Parser = parser;
        }
        return edgeTailLabel1_6034Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6035Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6035Parser() {
        if (edgeTailLabel2_6035Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6035Parser = parser;
        }
        return edgeTailLabel2_6035Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel1_6036Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel1_6036Parser() {
        if (edgeHeadLabel1_6036Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel1_6036Parser = parser;
        }
        return edgeHeadLabel1_6036Parser;
    }

    /**
     * @generated
     */
    private IParser edgeHeadLabel2_6037Parser;

    /**
     * @generated
     */
    private IParser getEdgeHeadLabel2_6037Parser() {
        if (edgeHeadLabel2_6037Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_HeadLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeHeadLabel2_6037Parser = parser;
        }
        return edgeHeadLabel2_6037Parser;
    }

    /**
     * @generated
     */
    private IParser edgeMidLabel_6038Parser;

    /**
     * @generated
     */
    private IParser getEdgeMidLabel_6038Parser() {
        if (edgeMidLabel_6038Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_MidLabel() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeMidLabel_6038Parser = parser;
        }
        return edgeMidLabel_6038Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel1_6039Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel1_6039Parser() {
        if (edgeTailLabel1_6039Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel1() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel1_6039Parser = parser;
        }
        return edgeTailLabel1_6039Parser;
    }

    /**
     * @generated
     */
    private IParser edgeTailLabel2_6040Parser;

    /**
     * @generated
     */
    private IParser getEdgeTailLabel2_6040Parser() {
        if (edgeTailLabel2_6040Parser == null) {
            EAttribute[] features = new EAttribute[] { GraphsPackage.eINSTANCE
                    .getEdge_TailLabel2() };
            MessageFormatParser parser = new MessageFormatParser(features);
            edgeTailLabel2_6040Parser = parser;
        }
        return edgeTailLabel2_6040Parser;
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
        case EdgeHeadLabel1EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6001Parser();
        case EdgeHeadLabel2EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6002Parser();
        case EdgeMidLabelEditPart.VISUAL_ID:
            return getEdgeMidLabel_6003Parser();
        case EdgeTailLabel1EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6004Parser();
        case EdgeTailLabel2EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6005Parser();
        case EdgeHeadLabel12EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6006Parser();
        case EdgeHeadLabel22EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6007Parser();
        case EdgeMidLabel2EditPart.VISUAL_ID:
            return getEdgeMidLabel_6008Parser();
        case EdgeTailLabel12EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6009Parser();
        case EdgeTailLabel22EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6010Parser();
        case EdgeHeadLabel13EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6011Parser();
        case EdgeHeadLabel23EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6012Parser();
        case EdgeMidLabel3EditPart.VISUAL_ID:
            return getEdgeMidLabel_6013Parser();
        case EdgeTailLabel13EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6014Parser();
        case EdgeTailLabel23EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6015Parser();
        case EdgeHeadLabel14EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6016Parser();
        case EdgeHeadLabel24EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6017Parser();
        case EdgeMidLabel4EditPart.VISUAL_ID:
            return getEdgeMidLabel_6018Parser();
        case EdgeTailLabel14EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6019Parser();
        case EdgeTailLabel24EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6020Parser();
        case EdgeHeadLabel15EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6021Parser();
        case EdgeHeadLabel25EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6022Parser();
        case EdgeMidLabel5EditPart.VISUAL_ID:
            return getEdgeMidLabel_6023Parser();
        case EdgeTailLabel15EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6024Parser();
        case EdgeTailLabel25EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6025Parser();
        case EdgeHeadLabel16EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6026Parser();
        case EdgeHeadLabel26EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6027Parser();
        case EdgeMidLabel6EditPart.VISUAL_ID:
            return getEdgeMidLabel_6028Parser();
        case EdgeTailLabel16EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6029Parser();
        case EdgeTailLabel26EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6030Parser();
        case EdgeHeadLabel17EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6031Parser();
        case EdgeHeadLabel27EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6032Parser();
        case EdgeMidLabel7EditPart.VISUAL_ID:
            return getEdgeMidLabel_6033Parser();
        case EdgeTailLabel17EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6034Parser();
        case EdgeTailLabel27EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6035Parser();
        case EdgeHeadLabel18EditPart.VISUAL_ID:
            return getEdgeHeadLabel1_6036Parser();
        case EdgeHeadLabel28EditPart.VISUAL_ID:
            return getEdgeHeadLabel2_6037Parser();
        case EdgeMidLabel8EditPart.VISUAL_ID:
            return getEdgeMidLabel_6038Parser();
        case EdgeTailLabel18EditPart.VISUAL_ID:
            return getEdgeTailLabel1_6039Parser();
        case EdgeTailLabel28EditPart.VISUAL_ID:
            return getEdgeTailLabel2_6040Parser();
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
