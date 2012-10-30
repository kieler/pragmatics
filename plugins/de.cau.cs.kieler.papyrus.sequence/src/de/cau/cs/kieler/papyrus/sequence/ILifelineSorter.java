package de.cau.cs.kieler.papyrus.sequence;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;

public interface ILifelineSorter {
    public List<SLifeline> sortLifelines(SGraph graph, LGraph lgraph);
}
