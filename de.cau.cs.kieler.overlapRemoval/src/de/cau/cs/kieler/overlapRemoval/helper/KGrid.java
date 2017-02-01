package de.cau.cs.kieler.overlapRemoval.helper;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.cau.cs.kieler.klighd.kgraph.KNode;

public class KGrid {
	private Bounds bounds;
	private int level = 0;

	private List<KGrid> fields = new ArrayList<KGrid>();
	private List<Point> nodes = new ArrayList<Point>();

	public KGrid(KNode foundation, int desiredLevels) {
		this.setBounds(
				new Bounds(foundation.getXpos(), foundation.getYpos(), foundation.getWidth(), foundation.getHeight()));
	}

	public KGrid(int level, Bounds bounds) {
		this.setLevel(level);
		this.setBounds(bounds);
	}

	public void split() {
		final int newLevel = this.getLevel() + 1;

		this.getFields().add(0, new KGrid(newLevel, new Bounds(this.getBounds().getXPos(), this.getBounds().getYPos(),
				this.getBounds().getWidth() / 2f, this.getBounds().getHeight() / 2f)));
		this.getFields().add(1, new KGrid(newLevel, new Bounds(this.getBounds().getXPos() + this.getBounds().getWidth() / 2f,
				this.getBounds().getYPos(), this.getBounds().getWidth() / 2f, this.getBounds().getHeight() / 2f)));
		this.getFields().add(2, new KGrid(newLevel,
				new Bounds(this.getBounds().getXPos(), this.getBounds().getYPos() + this.getBounds().getHeight() / 2f,
						this.getBounds().getWidth() / 2f, this.getBounds().getHeight() / 2f)));
		this.getFields().add(3, new KGrid(newLevel,
				new Bounds(this.getBounds().getXPos() + this.getBounds().getWidth() / 2f,
						this.getBounds().getYPos() + this.getBounds().getHeight() / 2f, this.getBounds().getWidth() / 2f,
						this.getBounds().getHeight() / 2f)));
	}

	public void createGrid(KGrid grid, int desiredLevels) {
		if (desiredLevels > 0) {
			if (grid.getFields().size() == 0) {
				grid.split();
				for(KGrid quad : grid.getFields()) {
					grid.createGrid(quad, desiredLevels-1);
				}
			}
		}
	}

	public void insertNodes(Point nodes) {
		this.insertNode(nodes, this);
	}

	private boolean insertNode(Point node, KGrid grid) {
		// System.out.println(grid.getFields().size());
//		if (grid.getNodes().size() > 0) {
//			if(grid.getFields().size() == 0) {
//				grid.split();
//				Point point = grid.getNodes().get(0);
////				System.out.println("n: "+ grid.getNodes().size());
////				grid.getNodes().remove(0);
////				insertNode(point, grid.getFields().get(0));
////				insertNode(point, grid.getFields().get(1));
////				insertNode(point, grid.getFields().get(2));
////				insertNode(point, grid.getFields().get(3));
////				return false;
//			}
//		}
		
		if (grid.getNodes().size() > 0) {
			if(grid.getFields().size() == 0) {
				grid.split();
			}
			
			Point point = grid.getNodes().get(0);
			grid.getNodes().remove(0);
			grid.insertNodes(point);

				if (insertNode(node, grid.getFields().get(0)) == true)
					return true;
				else if (insertNode(node, grid.getFields().get(1)) == true)
					return true;
				else if (insertNode(node, grid.getFields().get(2)) == true)
					return true;
				else if (insertNode(node, grid.getFields().get(3)) == true)
					return true;
				else
					return false;
			
		} else {

			if (grid.getBounds().inBounds(node)) {
					grid.getNodes().add(node);
//					System.out.println("hier: "+node);
					return true;
			}

			return false;
		}
	}

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public List<KGrid> getFields() {
		return fields;
	}

	public void setFields(List<KGrid> fields) {
		this.fields = fields;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Point> getNodes() {
		return nodes;
	}

	public void setNodes(List<Point> nodes) {
		this.nodes = nodes;
	}
	
	public void draw(Graphics gr, KGrid grid) {
		if(grid.getFields().size() > 0) {
			grid.draw(gr, grid.getFields().get(0));
			grid.draw(gr, grid.getFields().get(1));
			grid.draw(gr, grid.getFields().get(2));
			grid.draw(gr, grid.getFields().get(3));
		}
		
		
//		System.out.println(grid.getBounds());
		if(grid.getNodes().size() > 0) {
		grid.getNodes().get(0).draw(gr);
		}
		grid.getBounds().draw(gr);
//		grid.getBounds().draw(gr);
	}
	
	public int countPointsInRect(KGrid root) {

	    // Entire bound of current node outside of given rectangle?

	    // Part, or whole of current bound inside given rectangle:
	    // Recurse on each subtree
		int sum = 0;
		
		if(root.getFields().size() < 1) {
			return 1;
		}

			sum += countPointsInRect(root.getFields().get(0));
			sum += countPointsInRect(root.getFields().get(1));
			sum += countPointsInRect(root.getFields().get(2));
			sum += countPointsInRect(root.getFields().get(3));
			    

	    return sum;
	}
	
	public List<Point> nearestNeighbors(KGrid grid, Point start, float distance) {
		List<Point> neighbors = new ArrayList<Point>();
		
		return neighbors;
	}
	
	
	public List<Point> getOrderedList() {
		List<Point> elements = new ArrayList<Point>();
		
		elements.addAll(this.getNodes());
		if(this.getFields().size() > 0) {
			elements.addAll(this.getFields().get(0).getOrderedList());
			elements.addAll(this.getFields().get(1).getOrderedList());
			elements.addAll(this.getFields().get(2).getOrderedList());
			elements.addAll(this.getFields().get(3).getOrderedList());
		}
		
//		elements.addAll(this.getNodes());
		
		return elements;
	}
}
