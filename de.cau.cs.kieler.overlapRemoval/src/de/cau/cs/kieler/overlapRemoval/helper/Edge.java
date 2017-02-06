package de.cau.cs.kieler.overlapRemoval.helper;

import java.awt.Graphics2D;
import java.util.Objects;

public abstract class Edge<V> {
	private final V source;
	private final V target;
	private int weight;
	private boolean undirected;

	public Edge(V pSource, V pTarget) {
		this.source = pSource;
		this.target = pTarget;
		this.weight = 1;
	}

	public Edge(V pSource, V pTarget, int pWeight) {
		this.source = pSource;
		this.target = pTarget;
		this.weight = pWeight;
	}
	
	public Edge(V pSource, V pTarget, int pWeight, boolean undirected) {
		this.source = pSource;
		this.target = pTarget;
		this.weight = pWeight;
		this.setUndirected(undirected);
	}

	public boolean hasVertex(V vertex) {
		return (source.equals(vertex) || target.equals(vertex));
	}


	@Override
	public String toString() {
		return "Source: " + source + " Target: " + target;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Edge<V> other = (Edge<V>) obj;

		return Objects.equals(this, other) || Objects.equals(this, other.revert());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.source, this.target);
	}

	public V getSource() {
		return this.source;
	}

	public V getTarget() {
		return this.target;
	}

	public boolean isUndirected() {
		return undirected;
	}

	public void setUndirected(boolean undirected) {
		this.undirected = undirected;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public abstract boolean sameSide(Point a, Point b);

	public abstract void draw(Graphics2D g2d);

	public abstract Edge<V> revert();
}
