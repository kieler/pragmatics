package de.cau.cs.kieler.overlapRemoval.helper;

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

	private List<KGrid> split() {
		final int newLevel = this.getLevel() + 1;

		List<KGrid> quads = new ArrayList<KGrid>();
		quads.add(new KGrid(newLevel, new Bounds(this.getBounds().getXPos(), this.getBounds().getYPos(),
				this.getBounds().getWidth() / 2, this.getBounds().getHeight() / 2)));
		quads.add(new KGrid(newLevel, new Bounds(this.getBounds().getXPos() + this.getBounds().getWidth() / 2,
				this.getBounds().getYPos(), this.getBounds().getWidth() / 2, this.getBounds().getHeight() / 2)));
		quads.add(new KGrid(newLevel,
				new Bounds(this.getBounds().getXPos(), this.getBounds().getYPos() + this.getBounds().getHeight() / 2,
						this.getBounds().getWidth() / 2, this.getBounds().getHeight() / 2)));
		quads.add(new KGrid(newLevel,
				new Bounds(this.getBounds().getXPos() + this.getBounds().getWidth() / 2,
						this.getBounds().getYPos() + this.getBounds().getHeight() / 2, this.getBounds().getWidth() / 2,
						this.getBounds().getHeight() / 2)));

		return quads;
	}

	public void createGrid(KGrid grid, int desiredLevels) {
		if (desiredLevels > 0) {
			if (grid.getFields().size() == 0) {
				List<KGrid> quads = this.split();
				// this.getFields().addAll(this.split());
				for (KGrid quad : quads) {
					this.createGrid(quad, desiredLevels - 1);
				}
				grid.getFields().addAll(quads);
			}
		}
	}

	public void insertNodes(KNode nodes, KGrid grid) {
		this.insertNode(nodes, grid);
	}

	private boolean insertNode(KNode node, KGrid grid) {
		// System.out.println(grid.getFields().size());
		if (grid.getFields().size() > 0) {
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
			final Point nodePoint = new Point(node);
			// System.out.println(grid.getBounds());

			if (grid.getBounds().inBounds(nodePoint)) {
				grid.getNodes().add(nodePoint);
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
}
