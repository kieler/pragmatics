package de.cau.cs.kieler.formats.sd;

import java.util.List;
import java.util.function.Predicate;

import org.eclipse.elk.core.util.IGraphElementVisitor;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import com.google.common.collect.Lists;

import de.cau.cs.kieler.formats.AbstractEmfHandler;
import de.cau.cs.kieler.formats.IGraphTransformer;
import de.cau.cs.kieler.formats.TransformationData;
import de.cau.cs.kieler.graphs.testcases.ITestCaseGraphProvider;
import de.cau.cs.kieler.kiesl.klighd.SequenceDiagramSynthesis;
import de.cau.cs.kieler.kiesl.text.kiesl.Interaction;
import de.cau.cs.kieler.klighd.LightDiagramServices;
import de.cau.cs.kieler.klighd.ViewContext;
import de.cau.cs.kieler.klighd.util.KlighdSynthesisProperties;

public class SequenceDiagramFormatHandler extends AbstractEmfHandler<Interaction>
    implements ITestCaseGraphProvider {

	public static final String SD_EXTENSION = "sd";

	private IGraphTransformer<Interaction, ElkNode> importer = new Importer();

	/** The {@link ViewContext} that has been used by klighd. */
	private ViewContext viewContext;

	@Override
	public IGraphTransformer<Interaction, ElkNode> getImporter() {
		return importer;
	}

	@Override
	public IGraphTransformer<ElkNode, Interaction> getExporter() {
		throw new UnsupportedOperationException("Exporting Sequence Diagrams is not supported.");
	}

	@Override
	protected ResourceSet createResourceSet() {
		return new ResourceSetImpl();
	}

	@Override
	public ViewContext getViewContext() {
		return this.viewContext;
	}

	@Override
	public Iterable<Predicate<ElkNode>> getGraphFilters() {
		return null;
	}

	@Override
	public Iterable<IGraphElementVisitor> getGraphModifiers() {
		List<IGraphElementVisitor> visis = Lists.newArrayList();

		return visis;
	}

	private class Importer implements IGraphTransformer<Interaction, ElkNode> {
		
		@Override
		public void transform(TransformationData<Interaction, ElkNode> data) {
			Interaction model = data.getSourceGraph();
			
			// Configure the synthesis
			KlighdSynthesisProperties props = KlighdSynthesisProperties.create();
			
			props.configureSynthesisOptionValue(
			        SequenceDiagramSynthesis.STYLE, SequenceDiagramSynthesis.STYLE_BORING);
			props.configureSynthesisOptionValue(
					SequenceDiagramSynthesis.LLSORT, SequenceDiagramSynthesis.LLSORT_INTERACTIVE);
			props.configureSynthesisOptionValue(
					SequenceDiagramSynthesis.LABELS, SequenceDiagramSynthesis.LABELS_CONSISTENT);
			props.configureSynthesisOptionValue(
			        SequenceDiagramSynthesis.MANAGEMENT, SequenceDiagramSynthesis.MANAGEMENT_OFF);
			props.configureSynthesisOptionValue(SequenceDiagramSynthesis.VERTICAL_COMPACTION, true);

			// Translate the model and extract the laid-out ELK graph
			ViewContext vc = LightDiagramServices.translateModel2(model, null, props);
			ElkNode elkGraph = layoutAndToElkNode(vc);

			data.getTargetGraphs().clear();
			data.getTargetGraphs().add(elkGraph);
		}

		@Override
		public void transferLayout(TransformationData<Interaction, ElkNode> data) {
			throw new UnsupportedOperationException(
			        "Applying layout to a sequence diagram file is not supported.");
		}
	}

}
