package de.cau.cs.kieler.klighd.kgraph.dsp

import de.cau.cs.kieler.klighd.kgraph.KGraphData
import de.cau.cs.kieler.klighd.kgraph.KInsets
import io.typefox.sprotty.api.SEdge
import io.typefox.sprotty.api.SGraph
import io.typefox.sprotty.api.SLabel
import io.typefox.sprotty.api.SNode
import io.typefox.sprotty.api.SPort
import io.typefox.sprotty.server.xtext.tracing.Traceable
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class SKNode extends SNode implements Traceable {
    String trace
    List<KGraphData> data
    KInsets insets
}
@Accessors
class SKLabel extends SLabel {
    List<KGraphData> data
    KInsets insets
}
@Accessors
class SKEdge extends SEdge {
    List<KGraphData> data
}
@Accessors
class SKPort extends SPort {
    List<KGraphData> data
    KInsets insets
}
@Accessors
class SKGraph extends SGraph {
    List<KGraphData> data
    KInsets insets
}