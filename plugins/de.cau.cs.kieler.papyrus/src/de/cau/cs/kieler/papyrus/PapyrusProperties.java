package de.cau.cs.kieler.papyrus;

import java.util.List;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.core.properties.IProperty;
import de.cau.cs.kieler.core.properties.Property;

public class PapyrusProperties {
    public static final IProperty<String> MESSAGE_TYPE = new Property<String>(
            "de.cau.cs.kieler.papyrus.messageType");

    public static final IProperty<String> NODE_TYPE = new Property<String>(
            "de.cau.cs.kieler.papyrus.nodeType");

    public static final IProperty<List<SequenceExecution>> EXECUTIONS = new Property<List<SequenceExecution>>(
            "de.cau.cs.kieler.papyrus.executionSpecifications");

    public static final IProperty<SequenceExecution> EXECUTION = new Property<SequenceExecution>(
            "de.cau.cs.kieler.papyrus.executionSpecification");

    public static final IProperty<List<SequenceArea>> AREAS = new Property<List<SequenceArea>>(
            "de.cau.cs.kieler.papyrus.area");

    public static final IProperty<List<Object>> ATTACHED_TO = new Property<List<Object>>(
            "de.cau.cs.kieler.papyrus.attachedTo");

    public static final IProperty<KNode> DESTRUCTION = new Property<KNode>(
            "de.cau.cs.kieler.papyrus.destruction");

    public static final IProperty<String> ATTACHED_ELEMENT = new Property<String>(
            "de.cau.cs.kieler.papyrus.attachedElement");
}
