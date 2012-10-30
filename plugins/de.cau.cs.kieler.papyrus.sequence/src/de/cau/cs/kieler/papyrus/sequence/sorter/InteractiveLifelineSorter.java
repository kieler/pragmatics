package de.cau.cs.kieler.papyrus.sequence.sorter;

import java.util.List;

import de.cau.cs.kieler.klay.layered.graph.LGraph;
import de.cau.cs.kieler.papyrus.sequence.ILifelineSorter;
import de.cau.cs.kieler.papyrus.sequence.graph.SGraph;
import de.cau.cs.kieler.papyrus.sequence.graph.SLifeline;

public class InteractiveLifelineSorter  implements ILifelineSorter {

    public List<SLifeline> sortLifelines(SGraph graph, LGraph lgraph) {
        List<SLifeline> lifelines = (List<SLifeline>) graph.getLifelines();
        java.util.Collections.sort(lifelines);
        for (int i = 0; i < lifelines.size(); i++){
            lifelines.get(i).setPosition(i);
        }
        return lifelines;
    }

}
