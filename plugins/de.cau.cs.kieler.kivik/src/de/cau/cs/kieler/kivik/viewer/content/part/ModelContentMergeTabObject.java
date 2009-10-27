/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.kivik.viewer.content.part;

import org.eclipse.emf.compare.ui.util.EMFCompareConstants;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TreeItem;

import de.cau.cs.kieler.kivik.viewer.content.ModelContentMergeViewer;


/**
 * This class will be used to wrap {@link Object} subclasses such as
 * {@link TreeItem} and {@link TableItem} to allow us to call methods such as
 * <tt>getBounds</tt> without explicitely casting each time we do so.
 * <p>
 * This wrapper will also hold UI information about the way connectors should be
 * drawn between tabs.
 * </p>
 * Initial implementation by <a href="mailto:laurent.goubet@obeo.fr">Laurent
 * Goubet</a> in EMF Compare, changed to support Objects instead of Items by <a
 * href="mailto:ars@informatik.uni-kiel.de">Arne Schipper</a>.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class ModelContentMergeTabObject {
	
	/** The item this instance has actually been created for. */
	private final Object actualObject;

	/** Key of the color to use for UI components using this item. */
	private String curveColorID;

	/** Key of the color to use for UI components using this item. */
	private String backgroundColorID;

	/** Size of the center curve connecting for this item. */
	private int curveSize;

	/**
	 * Y coordinate at which the center curve should be connected for this item.
	 */
	private int curveY;

	/** Height of the item's control's header. */
	private int headerHeight;

	/** The visible item on which will be drawn UI marquees. */
	private final Object visibleObject;

	/**
	 * Constructs a wrapper around the given object. This constructor specifies
	 * the color to use when drawing UI components with this object.
	 * 
	 * @param actual
	 *            The effective object this instance describes.
	 * @param visible
	 *            The visible item this instance holds UI information for.
	 *            Defaults as <tt>actual</tt> if <code>null</code>.
	 * @param drawingColor
	 *            Key of the color to use when drawing UI components for this
	 *            item.
	 */
	public ModelContentMergeTabObject(Object actual, Object visible,
			String drawingColor) {
		this(actual, visible, drawingColor, -1, -1);
	}

	public ModelContentMergeTabObject(Object actual, Object visible,
			String drawingColor, String backgroundColor) {
		this(actual, visible, drawingColor, -1, -1);
		this.backgroundColorID = backgroundColor;
	}

	/**
	 * Constructs a wrapper around the given item. This constructor specifies
	 * the color to use when drawing UI components with this item as well as the
	 * Y coordinate and size of the center curve connected to this item.
	 * 
	 * @param actual
	 *            The effective object this instance describes.
	 * @param visible
	 *            The visible object this instance holds UI information for.
	 *            Defaults as <tt>actual</tt> if <code>null</code>.
	 * @param drawingColor
	 *            Key of the color to use when drawing UI components for this
	 *            object.
	 * @param curveExpectedY
	 *            Y coordinate of the center curve for this object.
	 * @param curveExpectedSize
	 *            Size of the center curve for this object.
	 */
	public ModelContentMergeTabObject(Object actual, Object visible,
			String drawingColor, int curveExpectedY, int curveExpectedSize) {
		actualObject = actual;
		visibleObject = visible;
		if (drawingColor == null) {
			curveColorID = EMFCompareConstants.PREFERENCES_KEY_CHANGED_COLOR;
		} else
			curveColorID = drawingColor;
		curveY = curveExpectedY;
		curveSize = curveExpectedSize;
	}

	/**
	 * Constructs a wrapper around the given object. This constructor specifies
	 * the color to use when drawing UI components with this object.
	 * 
	 * @param actual
	 *            The effective object this instance describes.
	 * @param drawingColor
	 *            Key of the color to use when drawing UI components for this
	 *            object.
	 */
	public ModelContentMergeTabObject(Object actual, String drawingColor) {
		this(actual, actual, drawingColor, -1, -1);
	}

	/**
	 * Returns the actual object this instance has been created for.
	 * 
	 * @return The actual object this instance has been created for.
	 */
	public Object getActualObject() {
		return actualObject;
	}

	/**
	 * Returns the color which should be used when drawing the center curve and
	 * other UI colored components using this object.
	 * <p>
	 * If no colors are specified for this object,
	 * {@link ModelContentMergeViewer#getChangedColor()} will be used as
	 * default.
	 * </p>
	 * 
	 * @return The color which should be used when drawing UI components.
	 */
	public String getCurveColor() {
		return curveColorID;
	}

	/**
	 * Returns the size of the center connecting curve.
	 * <p>
	 * If it is not specified or is negative, no curve will be drawn.
	 * </p>
	 * 
	 * @return The size of the center connecting curve.
	 */
	public int getCurveSize() {
		return curveSize;
	}

	/**
	 * Returns the Y coordinate at which the center connecting curve should be
	 * drawn on this object.
	 * <p>
	 * If it is not specified or is negative, no curve will be drawn.
	 * </p>
	 * 
	 * @return The Y coordinate of the center curve connected to this object.
	 */
	public int getCurveY() {
		return curveY;
	}

	public String getBackgroundColor() {
		return backgroundColorID;
	}

	/**
	 * Returns the height of the object's control header.
	 * 
	 * @return The height of the object's control header.
	 */
	public int getHeaderHeight() {
		return headerHeight;
	}

	/**
	 * Returns the visible object on which UI marquees should be drawn.
	 * 
	 * @return The visible object on which UI marquees should be drawn.
	 */
	public Object getVisibleObject() {
		return visibleObject;
	}

	/**
	 * Sets a new value for this object's center curve size.
	 * 
	 * @param newCurveSize
	 *            New size to affect to the curve.
	 */
	public void setCurveSize(int newCurveSize) {
		curveSize = newCurveSize;
	}

	/**
	 * Sets a new value for this object's center curve Y ccordinate.
	 * 
	 * @param newCurveY
	 *            New Y coordinate of the center curve for this point.
	 */
	public void setCurveY(int newCurveY) {
		curveY = newCurveY;
	}

	/**
	 * Returns the value of the object's control header height.
	 * 
	 * @param newHeaderHeight
	 *            The value of the object's control header height.
	 */
	public void setHeaderHeight(int newHeaderHeight) {
		headerHeight = newHeaderHeight;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = "actual item="; //$NON-NLS-1$
		result += actualObject;
		result += "\tvisible item="; //$NON-NLS-1$
		result += visibleObject;
		result += "\t(curveColor="; //$NON-NLS-1$
		result += curveColorID;
		result += ", curveSize="; //$NON-NLS-1$
		result += curveSize;
		result += ", curveY="; //$NON-NLS-1$
		result += curveY;
		result += ')';
		return result;
	}
}
