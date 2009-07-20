package de.cau.cs.kieler.kiml.zest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.zest.layouts.*;
import org.eclipse.zest.layouts.constraints.BasicEntityConstraint;

import de.cau.cs.kieler.core.KielerException;
import de.cau.cs.kieler.core.alg.IKielerProgressMonitor;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.kgraph.KNode;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KEdgeLayout;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KInsets;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KLayoutDataFactory;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KPoint;
import de.cau.cs.kieler.kiml.layout.klayoutdata.KShapeLayout;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.kiml.zest.graph.*;
import de.cau.cs.kieler.kiml.zest.preferences.ZestLayouterPreferencePage;


/**
 * Wrapper class that translates KIML layout graphs into the Zest graph
 * structure.
 * 
 * @author msp
 */
public class ZestAlgorithmWrapper {
	
	// value added to the size of each parent node
	private static final float SIZE_ADDITION = 20.0f;
	
	// layout algorithm used internally
	private LayoutAlgorithm layoutAlgorithm;
	
	// mapping of nodes to layout entities
	private Map<KNode, LayoutEntity> layoutNode2EntityMap = new HashMap<KNode, LayoutEntity>();
	// sum of the widths of all nodes
	private float totalWidth = 0.0f;
	// sum of the heights of all nodes
	private float totalHeight = 0.0f;
	
	/**
	 * Creates a wrapper for a given Zest layout algorithm.
	 * 
	 * @param theLayoutAlgorithm Zest layout algorithm to use
	 */
	public ZestAlgorithmWrapper(LayoutAlgorithm theLayoutAlgorithm) {
		this.layoutAlgorithm = theLayoutAlgorithm;
	}
	
	/**
	 * Applies the predefined algorithm on the given layout node.
	 * 
	 * @param layoutNode layout node to be layouted
	 * @param progressMonitor monitor used to keep track of progress
	 */
	public void doLayout(KNode layoutNode,
			IKielerProgressMonitor progressMonitor) throws KielerException {
		progressMonitor.begin("Zest layout", 15);
		// build layout entities and relationships
		LayoutEntity[] entities = buildEntities(layoutNode);
		LayoutRelationship[] relationships = buildRelationships(layoutNode);
		progressMonitor.worked(3);
		
		// compute preferred height and width of the graph
		float scaleBase = Activator.getDefault().getPreferenceStore()
				.getFloat(ZestLayouterPreferencePage.PREF_SCALE_BASE);
		float width = (float)Math.sqrt(totalWidth / entities.length) * scaleBase;
		float height = (float)Math.sqrt(totalHeight / entities.length) * scaleBase;
		
		// executes the layout algorithm
		try {
			layoutAlgorithm.applyLayout(entities, relationships,
					SIZE_ADDITION, SIZE_ADDITION, width, height, false, false);
		}
		catch (InvalidLayoutConfiguration exception) {
			throw new KielerException(exception.getMessage(),
					KielerException.Type.EXTERNAL_ERROR);
		}
		progressMonitor.worked(8);
		
		// transfer layout results to the original graph
		transferLayoutResult(layoutNode, entities, relationships);
		progressMonitor.done();
	}
	
	/**
	 * Builds a list of entities of a parent layout node.
	 * 
	 * @param parentNode parent layout node
	 * @return entities in that layout node
	 */
	private LayoutEntity[] buildEntities(KNode parentNode) {
		for (KNode child : parentNode.getChildren()) {
		    KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(child);
			float x = shapeLayout.getXpos();
			float y = shapeLayout.getYpos();
			float width = shapeLayout.getWidth();
			float height = shapeLayout.getHeight();
			AdvancedEntity entity = new AdvancedEntity(child, x, y, width, height);
			totalWidth += width;
			totalHeight += height;
			BasicEntityConstraint entityConstraint = new BasicEntityConstraint();
			entityConstraint.preferredWidth = width; 
			entityConstraint.preferredHeight = height;
			entityConstraint.hasPreferredSize = true;
			entityConstraint.hasPreferredLocation = false;
			entity.populateLayoutConstraint(entityConstraint);
			layoutNode2EntityMap.put(child, entity);
		}
		return layoutNode2EntityMap.values().toArray(new LayoutEntity[0]);
	}
	
	/**
	 * Builds a list of relationships of a parent layout node.
	 * 
	 * @param parentNode parent layout node
	 * @return relationships in that layout node
	 */
	private LayoutRelationship[] buildRelationships(KNode parentNode) {
		LinkedList<LayoutRelationship> edgeList = new LinkedList<LayoutRelationship>();
		for (KNode sourceNode : parentNode.getChildren()) {
			for (KEdge edge : sourceNode.getOutgoingEdges()) {
				KNode targetNode = edge.getTarget();
				if (targetNode != null) {
					LayoutEntity sourceEntity = layoutNode2EntityMap.get(sourceNode);
					LayoutEntity targetEntity = layoutNode2EntityMap.get(targetNode);
					LayoutRelationship relationship = new AdvancedRelationship(edge, sourceEntity, targetEntity);
					edgeList.add(relationship);
				}
			}
		}
		return edgeList.toArray(new LayoutRelationship[0]);
	}
	
	/**
	 * Copies layout results back to the original layout graph.
	 * 
	 * @param entities list of layouted entities
	 * @param relationships list of layouted relationships
	 */
	private void transferLayoutResult(KNode parentNode,
			LayoutEntity[] entities, LayoutRelationship[] relationships) {
		float maxX = 0.0f, maxY = 0.0f;
		
		// transfer entities layouts
		for (LayoutEntity entity : entities) {
			KNode layoutNode = (KNode)((AdvancedEntity)entity).getRealObject();
			KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(layoutNode);
			float x = (float)entity.getXInLayout();
			float y = (float)entity.getYInLayout();
			float width = shapeLayout.getWidth();
			float height = shapeLayout.getHeight();
			if (x + width > maxX) maxX = x + width;
			if (y + height > maxY) maxY = y + height;
			shapeLayout.setXpos(x);
			shapeLayout.setYpos(y);
		}
		
		// transfer relationships layouts
		for (LayoutRelationship relationship : relationships) {
			KEdge edge = (KEdge)((AdvancedRelationship)relationship).getRealObject();
			KEdgeLayout edgeLayout = KimlLayoutUtil.getEdgeLayout(edge);
			edgeLayout.getBendPoints().clear();
			for (LayoutBendPoint bendPoint : ((AdvancedRelationship)relationship).getBendPoints()) {
				KPoint point = KLayoutDataFactory.eINSTANCE.createKPoint();
				float x = (float)bendPoint.getX();
				float y = (float)bendPoint.getY();
				if (x > maxX) maxX = x;
				if (y > maxY) maxY = y;
				point.setX(x);
				point.setY(y);
				edgeLayout.getBendPoints().add(point);
			}
		}
		
		// determine size of the parent group
		KShapeLayout shapeLayout = KimlLayoutUtil.getShapeLayout(parentNode);
		KInsets insets = LayoutOptions.getInsets(shapeLayout);
		shapeLayout.setWidth(maxX + insets.getLeft()
				+ insets.getRight() + SIZE_ADDITION);
		shapeLayout.setHeight(maxY + insets.getTop()
				+ insets.getBottom() + SIZE_ADDITION);
	}

}
