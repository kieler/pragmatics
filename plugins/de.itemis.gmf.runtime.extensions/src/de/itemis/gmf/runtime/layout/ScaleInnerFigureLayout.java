/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.itemis.gmf.runtime.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

/**
 * A layout that centers a figure's children inside scaled inner bounds. The
 * scale factor can be given as CustomLayoutAttribute sclaeFactor. Use sqrt(2)
 * for ellipses and 2 for diamond figures.
 * 
 * @author koehnlein
 */
public class ScaleInnerFigureLayout extends ConstrainedToolbarLayout implements LayoutManager {
	private double scaleFactor = Math.sqrt(2);
	private int majorAlignment;

	public ScaleInnerFigureLayout() {
		setVertical(false);
		setMajorAlignment(ALIGN_CENTER);
		setMinorAlignment(ALIGN_CENTER);
		setStretchMajorAxis(false);
		setStretchMinorAxis(false);
	}

	public void setScaleFactor(double scaleFactor) {
		this.scaleFactor = scaleFactor;
	}

	public void setMajorAlignment(int majorAlignment) {
		this.majorAlignment = majorAlignment;
	}

	@Override
	public Dimension calculateMinimumSize(IFigure container, int hint, int hint2) {
		final Dimension minimumSize = super.calculateMinimumSize(container,
				hint, hint2);
		minimumSize.scale(scaleFactor).expand(1, 1);
		return minimumSize;
	}

	@Override
	protected Dimension calculatePreferredSize(IFigure container, int hint,
			int hint2) {
		final Dimension preferredSize = super.calculatePreferredSize(container,
				hint, hint2);
		preferredSize.scale(scaleFactor).expand(1, 1);
		return preferredSize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void layout(IFigure parent) {
		if (!parent.isVisible())
			return;
		List children = getChildren(parent);
		int numChildren = children.size();
		Rectangle clientArea = transposer.t(parent.getClientArea());
		int offsetX = (int) ((clientArea.width * (1. - 1. / scaleFactor)) / 2.);
		int offsetY = (int) ((clientArea.height * (1. - 1. / scaleFactor)) / 2.);
		int x = clientArea.x;
		int y = clientArea.y + offsetY;
		int availableWidth = (int) (clientArea.width / scaleFactor) + 1;
		int availableHeight = (int) (clientArea.height / scaleFactor) + 1;

		Dimension prefSizes[] = new Dimension[numChildren];
		Dimension minSizes[] = new Dimension[numChildren];
		Dimension maxSizes[] = new Dimension[numChildren];

		// These hints will be passed to the children of the parent
		// figure when getting their preferred size.
		int wHint = (isStretchWidth()) ? parent
				.getClientArea(Rectangle.SINGLETON).width : -1;
		int hHint = (isStretchHeight()) ? parent
				.getClientArea(Rectangle.SINGLETON).height : -1;

		/*
		 * Calculate sum of preferred heights of all children(totalHeight).
		 * Calculate sum of minimum heights of all children(minHeight). Cache
		 * Preferred Sizes and Minimum Sizes of all children.
		 * 
		 * totalHeight is the sum of the preferred heights of all children
		 * totalMinHeight is the sum of the minimum heights of all children
		 * prefMinSumHeight is the sum of the difference between all children's
		 * preferred heights and minimum heights. (This is used as a ratio to
		 * calculate how much each child will shrink).
		 */
		IFigure child;
		int totalHeight = 0;
		int totalMinHeight = 0;
		double totalMaxHeight = 0;
		int prefMinSumHeight = 0;
		double prefMaxSumHeight = 0;

		for (int i = 0; i < numChildren; i++) {
			child = (IFigure) children.get(i);

			prefSizes[i] = transposer.t(child.getPreferredSize(wHint, hHint));
			minSizes[i] = transposer.t(child.getMinimumSize(wHint, hHint));
			maxSizes[i] = transposer.t(child.getMaximumSize());

			if (getConstraint(child) != null) {
				double ratio = ((Double) getConstraint(child)).doubleValue();
				int prefHeight = (int) (ratio * availableHeight);
				prefHeight = Math.max(prefHeight, minSizes[i].height);
				prefHeight = Math.min(prefHeight, maxSizes[i].height);
				prefSizes[i].height = prefHeight;
			}

			totalHeight += prefSizes[i].height;
			totalMinHeight += minSizes[i].height;
			totalMaxHeight += maxSizes[i].height;
		}
		totalHeight += (numChildren - 1) * spacing;
		totalMinHeight += (numChildren - 1) * spacing;
		totalMaxHeight += (numChildren - 1) * spacing;
		prefMinSumHeight = totalHeight - totalMinHeight;
		prefMaxSumHeight = totalMaxHeight - totalHeight;

		/*
		 * The total amount that the children must be shrunk is the sum of the
		 * preferred Heights of the children minus Max(the available area and
		 * the sum of the minimum heights of the children).
		 * 
		 * amntShrinkHeight is the combined amount that the children must shrink
		 * amntShrinkCurrentHeight is the amount each child will shrink
		 * respectively
		 */
		int amntShrinkHeight = totalHeight
				- Math.max(availableHeight, totalMinHeight);

		if (!isStretchHeight()) {
			int adjust = Math.max(availableHeight - totalHeight, 0);
			switch (getVerticalAlignment()) {
			case ALIGN_TOPLEFT:
				break;
			case ALIGN_BOTTOMRIGHT:
				y += adjust;
				break;
			case ALIGN_CENTER:
			default:
				y += adjust / 2;
			}
			amntShrinkHeight += adjust;
		}
		for (int i = 0; i < numChildren; i++) {
			int amntShrinkCurrentHeight = 0;
			int prefHeight = prefSizes[i].height;
			int minHeight = minSizes[i].height;
			int maxHeight = maxSizes[i].height;
			int prefWidth = prefSizes[i].width;
			int maxWidth = maxSizes[i].width;
			Rectangle newBounds = new Rectangle(x, y, prefWidth, prefHeight);

			child = (IFigure) children.get(i);
			if (isStretchHeight()) {
				if (amntShrinkHeight > 0 && prefMinSumHeight != 0)
					amntShrinkCurrentHeight = (prefHeight - minHeight)
							* amntShrinkHeight / (prefMinSumHeight);
				else if (amntShrinkHeight < 0 && totalHeight != 0)
					amntShrinkCurrentHeight = (int) (((maxHeight - prefHeight) / prefMaxSumHeight) * amntShrinkHeight);
			}

			int width = Math.min(prefWidth, maxWidth);
			if (isStretchWidth())
				width = maxWidth;
			width = Math.min(availableWidth, width);
			newBounds.width = width;

			int adjust = availableWidth - width;
			switch (getHorizontalAlignment()) {
			case ALIGN_TOPLEFT:
				adjust = 0;
				break;
			case ALIGN_CENTER:
				adjust /= 2;
				break;
			case ALIGN_BOTTOMRIGHT:
				break;
			}
			newBounds.x += adjust + offsetX;
			if (newBounds.height - amntShrinkCurrentHeight > maxHeight)
				amntShrinkCurrentHeight = newBounds.height - maxHeight;
			newBounds.height -= amntShrinkCurrentHeight;
			child.setBounds(transposer.t(newBounds));

			amntShrinkHeight -= amntShrinkCurrentHeight;
			prefMinSumHeight -= (prefHeight - minHeight);
			prefMaxSumHeight -= (maxHeight - prefHeight);
			totalHeight -= prefHeight;
			y += newBounds.height + spacing;
		}
	}

	/**
	 * Gets the list of children after applying the layout options of ignore
	 * invisible children & reverse children
	 */
	@SuppressWarnings("unchecked")
	private List getChildren(IFigure container) {
		List children = new ArrayList(container.getChildren());
		if (getIgnoreInvisibleChildren()) {
			Iterator iter = children.iterator();
			while (iter.hasNext()) {
				IFigure f = (IFigure) iter.next();
				if (!f.isVisible())
					iter.remove();
			}
		}
		if (isReversed())
			Collections.reverse(children);
		return children;
	}

	protected boolean isStretchHeight() {
		return (isHorizontal() && getStretchMinorAxis())
				|| getStretchMajorAxis();
	}

	protected boolean isStretchWidth() {
		return (isHorizontal() && getStretchMajorAxis())
				|| getStretchMinorAxis();
	}
	
	protected int getHorizontalAlignment() {
		return isHorizontal() ? majorAlignment : minorAlignment;
	}

	protected int getVerticalAlignment() {
		return isHorizontal() ? minorAlignment : majorAlignment;
	}
}
