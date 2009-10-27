/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 **/
package de.itemis.gmf.runtime.layout;

import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;

public class ResizableFlowLayout extends ConstrainedToolbarLayout {

	public ResizableFlowLayout() {
		setStretchMinorAxis(true);
		setStretchMajorAxis(true);
		setMinorAlignment(ToolbarLayout.ALIGN_TOPLEFT);
		setSpacing(3);
		setVertical(true);
	}
}
