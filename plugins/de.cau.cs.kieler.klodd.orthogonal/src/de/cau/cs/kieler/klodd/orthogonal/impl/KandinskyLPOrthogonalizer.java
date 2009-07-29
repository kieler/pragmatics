/******************************************************************************
 * KIELER - Kiel Integrated Environment for Layout for the Eclipse RCP
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 * 
 * Copyright 2009 by
 * + Christian-Albrechts-University of Kiel
 *   + Department of Computer Science
 *     + Real-Time and Embedded Systems Group
 * 
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */
package de.cau.cs.kieler.klodd.orthogonal.impl;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import lpsolve.AbortListener;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import de.cau.cs.kieler.core.alg.AbstractAlgorithm;
import de.cau.cs.kieler.core.kgraph.KEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge;
import de.cau.cs.kieler.core.slimgraph.KSlimFace;
import de.cau.cs.kieler.core.slimgraph.KSlimGraph;
import de.cau.cs.kieler.core.slimgraph.KSlimNode;
import de.cau.cs.kieler.core.slimgraph.KSlimEdge.Bend;
import de.cau.cs.kieler.kiml.layout.options.LayoutOptions;
import de.cau.cs.kieler.kiml.layout.options.PortSide;
import de.cau.cs.kieler.kiml.layout.util.KimlLayoutUtil;
import de.cau.cs.kieler.klodd.orthogonal.Messages;
import de.cau.cs.kieler.klodd.orthogonal.modules.IOrthogonalizer;
import de.cau.cs.kieler.klodd.orthogonal.structures.*;

/**
 * Orthogonalizer implementation that uses LP methods to solve the
 * Kandinsky approach.
 * 
 * @author <a href="mailto:msp@informatik.uni-kiel.de">Miro Sp&ouml;nemann</a>
 */
public class KandinskyLPOrthogonalizer extends AbstractAlgorithm implements
		IOrthogonalizer {

	/** timeout in milliseconds after which the ILP solver is aborted */
	private static final long SOLVE_TIMEOUT = 3000;
	
	/**
	 * Abort listener implementation used to control execution of the
	 * lp_solve library.
	 */
	private static class LpSolveAborter implements AbortListener {
		/** indicates whether a timeout has occurred */
		boolean timeoutActive = false;
		/** start time for timeout, set at creation time */
		private long startTime = System.currentTimeMillis();
		
		/*
		 * (non-Javadoc)
		 * @see lpsolve.AbortListener#abortfunc(lpsolve.LpSolve, java.lang.Object)
		 */
		public boolean abortfunc(LpSolve problem, Object userhandle)
				throws LpSolveException {
			timeoutActive = System.currentTimeMillis() - startTime > SOLVE_TIMEOUT;
			return timeoutActive;
		}
	}
	
	/* (non-Javadoc)
	 * @see de.cau.cs.kieler.klodd.orthogonal.modules.IOrthogonalizer#orthogonalize(de.cau.cs.kieler.klodd.orthogonal.structures.TSMGraph)
	 */
	public void orthogonalize(KSlimGraph graph) {
		getMonitor().begin("Kandinsky orthogonalization", 4);
		// exit immediately in the trivial case
		if (graph.edges.isEmpty()) {
			getMonitor().done();
			return;
		}
		
		LpSolve ilp = null;
		try {
			// construct an ILP from the given graph
			ilp = makeIlp(graph);
			LpSolveAborter aborter = new LpSolveAborter();
			ilp.putAbortfunc(aborter, null);
			getMonitor().worked(1);
			
			// execute the solver on the ILP
			int result = ilp.solve();
			getMonitor().worked(2);
			
			if (result == LpSolve.OPTIMAL || result == LpSolve.SUBOPTIMAL) {
				// apply the solution to the input graph
				applySolution(graph, ilp);
			}
			else {
				if (result == LpSolve.USERABORT && aborter.timeoutActive)
					result = LpSolve.TIMEOUT;
				throw new RuntimeException(getErrorMessage(result));
			}
		}
		catch (LpSolveException exception) {
			throw new RuntimeException(Messages.getString("orthog.0"), exception); //$NON-NLS-1$
		}
		finally {
			if (ilp != null)
				ilp.deleteLp();
			getMonitor().done();
		}
	}
	
	/**
	 * Returns a readable error message for an lp_solve error.
	 * 
	 * @param returnValue return value of lp_solve
	 * @return readable message
	 */
	private String getErrorMessage(int returnValue) {
		switch (returnValue) {
		case LpSolve.NOMEMORY:
			return Messages.getString("orthog.1"); //$NON-NLS-1$
		case LpSolve.INFEASIBLE:
			return Messages.getString("orthog.2"); //$NON-NLS-1$
		case LpSolve.UNBOUNDED:
			return Messages.getString("orthog.3"); //$NON-NLS-1$
		case LpSolve.DEGENERATE:
			return Messages.getString("orthog.4"); //$NON-NLS-1$
		case LpSolve.NUMFAILURE:
			return Messages.getString("orthog.5"); //$NON-NLS-1$
		case LpSolve.USERABORT:
			return Messages.getString("orthog.6"); //$NON-NLS-1$
		case LpSolve.TIMEOUT:
			return Messages.getString("orthog.7")
					+ (SOLVE_TIMEOUT / 1000.0) + " s."; //$NON-NLS-1$ //$NON-NLS-2$
		case LpSolve.PROCFAIL:
			return Messages.getString("orthog.9"); //$NON-NLS-1$
		case LpSolve.PROCBREAK:
			return Messages.getString("orthog.10");	 //$NON-NLS-1$
		case LpSolve.NOFEASFOUND:
			return Messages.getString("orthog.11"); //$NON-NLS-1$
		default:
			return Messages.getString("orthog.12") + returnValue + ")."; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	/**
	 * Data structure that describes a section of an ILP problem, that
	 * is a part of the rows or of the columns of the problem matrix.
	 */
	private static class IlpSection {
		/** starting index of the section */
		int start;
		/** number of rows / columns of the section */
		int count;
		
		/**
		 * Creates an ILP section with starting value 1.
		 * @param count the number of elements of the new section
		 */
		IlpSection(int count) {
			this.start = 1;
			this.count = count;
		}
		
		/**
		 * Creates an ILP section that follows the given section.
		 * @param pred the predecessor of the new section
		 * @param count the number of elements of the new section
		 */
		IlpSection(IlpSection pred, int count) {
			this.start = pred.start + pred.count;
			this.count = count;
		}
	}
	
	/**
	 * Structure describing the dimensions of the rows in the Kandinsky ILP. 
	 */
	private static class KandinskyRows {
		IlpSection k1, k2, k3, k4, k5, u12, u4;
		int count;
		
		/**
		 * Creates a rows dimensions structure.
		 * 
		 * @param nodeCount number of nodes with degree > 0 in the graph
		 * @param edgeCount number of edges in the graph
		 * @param faceCount number of internal faces in the graph
		 */
		KandinskyRows(int nodeCount, int edgeCount, int faceCount) {
			k1 = new IlpSection(nodeCount);
			k2 = new IlpSection(k1, faceCount + 1);
			k3 = new IlpSection(k2, 2 * edgeCount);
			k4 = new IlpSection(k3, 2 * edgeCount);
			k5 = new IlpSection(k4, 6 * edgeCount);
			u12 = new IlpSection(k5, nodeCount);
			u4 = new IlpSection(u12, edgeCount);
			count = k5.start + k5.count - 1;
		}
	}
	
	/**
	 * Structure describing the dimensions of the columns in the
	 * Kandinsky ILP.
	 */
	private static class KandinskyCols {
		IlpSection forwSourceLeft, forwSourceRight, forwFaceLeft, forwFaceRight,
		    forwTargetLeft, forwTargetRight, backSourceLeft, backSourceRight,
		    backFaceLeft, backFaceRight, backTargetLeft, backTargetRight,
		    sourceAnchor, targetAnchor, anchorPos, modHelp1, objFn;
		int count;
		
		/**
		 * Creates a rows dimensions structure.
		 * 
		 * @param nodeCount number of nodes in the graph
		 * @param edgeCount number of edges in the graph
		 */
		KandinskyCols(int nodeCount, int edgeCount) {
			forwSourceLeft = new IlpSection(edgeCount);
			forwSourceRight = new IlpSection(forwSourceLeft, edgeCount);
			forwFaceLeft = new IlpSection(forwSourceRight, edgeCount);
			forwFaceRight = new IlpSection(forwFaceLeft, edgeCount);
			forwTargetLeft = new IlpSection(forwFaceRight, edgeCount);
			forwTargetRight = new IlpSection(forwTargetLeft, edgeCount);
			objFn = new IlpSection(6 * edgeCount);
			backSourceLeft = new IlpSection(forwTargetRight, edgeCount);
			backSourceRight = new IlpSection(backSourceLeft, edgeCount);
			backFaceLeft = new IlpSection(backSourceRight, edgeCount);
			backFaceRight = new IlpSection(backFaceLeft, edgeCount);
			backTargetLeft = new IlpSection(backFaceRight, edgeCount);
			backTargetRight = new IlpSection(backTargetLeft, edgeCount);
			sourceAnchor = new IlpSection(backTargetRight, edgeCount);
			targetAnchor = new IlpSection(sourceAnchor, edgeCount);
			anchorPos = new IlpSection(targetAnchor, nodeCount);
			modHelp1 = new IlpSection(anchorPos, edgeCount);
			count = modHelp1.start + modHelp1.count - 1;
		}
		
		/**
		 * Configures the given ILP to set bounds for a section of columns.
		 * 
		 * @param ilp ILP to configure
		 * @param section section of columns
		 * @param lower lower bound
		 * @param upper upper bound
		 * @throws LpSolveException if the lp_solve library reports a failure
		 */
		void setBounds(LpSolve ilp, IlpSection section, int lower, int upper)
				throws LpSolveException {
			int end = section.start + section.count;
			for (int j = section.start; j < end; j++)
				ilp.setBounds(j, lower, upper);
		}
		
		/**
		 * Configures the given ILP to remove bounds for a section of columns.
		 * 
		 * @param ilp ILP to configure
		 * @param section section of columns
		 * @throws LpSolveException if the lp_solve library reports a failure
		 */
		void setUnbounded(LpSolve ilp, IlpSection section)
				throws LpSolveException {
			int end = section.start + section.count;
			for (int j = section.start; j < end; j++)
				ilp.setUnbounded(j);
		}
	}
	
	/** the rows needed for the Kandinsky ILP */
	private KandinskyRows rows;
	/** the columns needed for the Kandinsky ILP */
	private KandinskyCols cols;
	
	/**
	 * Constructs an ILP using the Kandinsky model as a basis.
	 * 
	 * @param graph graph for which the ILP should be created
	 * @return an ILP containing all constraints needed for the input graph
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private LpSolve makeIlp(KSlimGraph graph) throws LpSolveException {
		// count only nodes with degree > 0
		int nodeCount = 0;
		for (KSlimNode node : graph.nodes) {
			if (!node.incidence.isEmpty())
				nodeCount++;
		}
		int edgeCount = graph.edges.size();
		int faceCount = graph.faces.size();
		// create dimensions of rows and columns
		rows = new KandinskyRows(nodeCount, edgeCount, faceCount);
		cols = new KandinskyCols(nodeCount, edgeCount);

		// create instance of ILP
		LpSolve ilp = LpSolve.makeLp(rows.count, cols.count);
		ilp.setMinim();
		
		// set objective function
		double[] objFn = new double[cols.objFn.count];
		int[] objFnIndex = new int[cols.objFn.count];
		for (int j = cols.objFn.start, jx = 0; jx < cols.objFn.count; j++, jx++) {
			objFn[jx] = 1;
			objFnIndex[jx] = j;
		}
		ilp.setObjFnex(cols.objFn.count, objFn, objFnIndex);
		
		// set variables types
		for (int j = 1; j <= cols.count; j++)
			ilp.setInt(j, true);
		
		// set variables bounds
		cols.setBounds(ilp, cols.forwSourceLeft, 0, 1);
		cols.setBounds(ilp, cols.forwSourceRight, 0, 1);
		cols.setBounds(ilp, cols.forwTargetLeft, 0, 1);
		cols.setBounds(ilp, cols.forwTargetRight, 0, 1);
		cols.setBounds(ilp, cols.backSourceLeft, 0, 1);
		cols.setBounds(ilp, cols.backSourceRight, 0, 1);
		cols.setBounds(ilp, cols.backTargetLeft, 0, 1);
		cols.setBounds(ilp, cols.backTargetRight, 0, 1);
		cols.setBounds(ilp, cols.sourceAnchor, 0, 4);
		cols.setBounds(ilp, cols.targetAnchor, 0, 4);
		cols.setBounds(ilp, cols.anchorPos, 0, 4);
		cols.setUnbounded(ilp, cols.modHelp1);
		
		// set constraint K1
		int i = rows.k1.start;
		for (KSlimNode node : graph.nodes) {
			if (!node.incidence.isEmpty()) {
				setK1Constraint(node, ilp, i++);
			}
		}
		
		// set constraint K2
		i = rows.k2.start;
		setK2Constraint(graph.externalFace, true, ilp, i++);
		for (KSlimFace face : graph.faces) {
			setK2Constraint(face, false, ilp, i++);
		}
		
		// set constraint K3
		i = rows.k3.start;
		for (KSlimEdge edge : graph.edges) {
			setK3Constraint(edge, ilp, i);
			i += 2;
		}
		
		// set constraint K4
		i = rows.k4.start;
		for (KSlimNode node : graph.nodes) {
			if (!node.incidence.isEmpty()) {
				Iterator<KSlimNode.IncEntry> edgeIter = node.incidence.iterator();
				KSlimNode.IncEntry currentEdge = edgeIter.next();
				while (edgeIter.hasNext()) {
					KSlimNode.IncEntry nextEdge = edgeIter.next();
					setK4Constraint(node, currentEdge, nextEdge, ilp, i++);
					currentEdge = nextEdge;
				}
				setK4Constraint(node, currentEdge, node.incidence.get(0),
						ilp, i);
			}
		}
		
		// set constraint K5
		i = rows.k5.start;
		for (KSlimEdge edge : graph.edges) {
			setK5Constraint(edge, ilp, i);
			i += 6;
		}
		
		// set constraint U1/2
		i = rows.u12.start;
		for (KSlimNode node : graph.nodes) {
			if (!node.incidence.isEmpty()) {
				setU12Constraint(node, ilp, i++);
			}
		}
		
		// set constraint U4
		i = rows.u4.start;
		for (KSlimEdge edge : graph.edges) {
			setU4Constraint(edge, ilp, i++);
		}
		
		// enter row entry mode for flexible constraints
		ilp.setAddRowmode(true);
		
		// add side constraints
		for (KSlimNode node : graph.nodes) {
			if (((TSMNode)node).type == TSMNode.Type.LAYOUT) {
				for (KSlimNode.IncEntry edgeEntry : node.incidence) {
					TSMEdge tsmEdge = (TSMEdge)edgeEntry.edge;
					KEdge layoutEdge = (KEdge)tsmEdge.object;
					if (edgeEntry.type == KSlimNode.IncEntry.Type.OUT) {
						if (layoutEdge.getSourcePort() != null)
							addSideConstraint(edgeEntry.edge,
							        LayoutOptions.getPortSide(
							        KimlLayoutUtil.getShapeLayout(
							        layoutEdge.getSourcePort())), ilp, false);
					}
					else {
						if (layoutEdge.getTargetPort() != null)
							addSideConstraint(edgeEntry.edge,
							        LayoutOptions.getPortSide(
		                            KimlLayoutUtil.getShapeLayout(
		                            layoutEdge.getTargetPort())), ilp, true);
					}
				}
			}
		}
		
		// add constraints for dummy nodes
		for (KSlimNode node : graph.nodes) {
			if (((TSMNode)node).type == TSMNode.Type.CROSSING)
				addDummyNodeConstraint(node, ilp);
		}
		
		// exit row entry mode
		ilp.setAddRowmode(false);

		return ilp;
	}
	
	/**
	 * Creates a K1 node constraint for the given node. The node must have
	 * degree > 0.
	 * 
	 * @param node node for which the constraint shall be created
	 * @param ilp currently processed ILP instance
	 * @param i index of the row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setK1Constraint(KSlimNode node, LpSolve ilp, int i)
			throws LpSolveException {
		int localEdgeCount = node.incidence.size();
		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 4);
		double[] row = new double[localEdgeCount];
		int[] rowIndex = new int[localEdgeCount];
		int jx = 0;
		for (KSlimNode.IncEntry edgeEntry : node.incidence) {
			row[jx] = 1;
			rowIndex[jx] = (edgeEntry.type == KSlimNode.IncEntry.Type.OUT
					? cols.sourceAnchor.start : cols.targetAnchor.start)
					+ edgeEntry.edge.id;
			jx++;
		}
		ilp.setRowex(i, localEdgeCount, row, rowIndex);
	}
	
	/**
	 * Creates a K2 face constraint for the given face.
	 * 
	 * @param face face for which the constraint shall be created
	 * @param external if true, the given face is external
	 * @param ilp currently processed ILP instance
	 * @param i index of the row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setK2Constraint(KSlimFace face, boolean external,
			LpSolve ilp, int i) throws LpSolveException {
		int edgeCount = 0; 
		for (List<KSlimFace.BorderEntry> border : face.borders) {
			edgeCount += border.size();
		}
		int colCount = 7 * edgeCount;
		
		ilp.setConstrType(i, LpSolve.EQ);
		if (external)
			ilp.setRh(i, 2 * edgeCount + 4);
		else
			ilp.setRh(i, 2 * edgeCount - 4);
		double[] row = new double[colCount];
		int[] rowIndex = new int[colCount];
		int jx = 0;
		for (List<KSlimFace.BorderEntry> border : face.borders) {
			for (KSlimFace.BorderEntry entry : border) {
				int edgeId = entry.edge.id;
				if (entry.forward) {
					row[jx] = 1;
					rowIndex[jx++] = cols.sourceAnchor.start + edgeId;
					row[jx] = 1;
					rowIndex[jx++] = cols.forwSourceLeft.start + edgeId;
					row[jx] = 1;
					rowIndex[jx++] = cols.forwFaceLeft.start + edgeId;
					row[jx] = 1;
					rowIndex[jx++] = cols.forwTargetLeft.start + edgeId;
					row[jx] = -1;
					rowIndex[jx++] = cols.forwSourceRight.start + edgeId;
					row[jx] = -1;
					rowIndex[jx++] = cols.forwFaceRight.start + edgeId;
					row[jx] = -1;
					rowIndex[jx++] = cols.forwTargetRight.start + edgeId;
				}
				else {
					row[jx] = 1;
					rowIndex[jx++] = cols.targetAnchor.start + edgeId;
					row[jx] = 1;
					rowIndex[jx++] = cols.backSourceLeft.start + edgeId;
					row[jx] = 1;
					rowIndex[jx++] = cols.backFaceLeft.start + edgeId;
					row[jx] = 1;
					rowIndex[jx++] = cols.backTargetLeft.start + edgeId;
					row[jx] = -1;
					rowIndex[jx++] = cols.backSourceRight.start + edgeId;
					row[jx] = -1;
					rowIndex[jx++] = cols.backFaceRight.start + edgeId;
					row[jx] = -1;
					rowIndex[jx++] = cols.backTargetRight.start + edgeId;
				}
			}
		}
		ilp.setRowex(i, colCount, row, rowIndex);
	}
	
	/**
	 * Creates two K3 edge constraints for the given edge. The first
	 * row is used for forward orientation, the second for backwards
	 * orientation.
	 * 
	 * @param edge the edge for which constraints shall be created
	 * @param ilp currently processed ILP instance
	 * @param i index of the first row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setK3Constraint(KSlimEdge edge, LpSolve ilp, int i)
			throws LpSolveException {
		double[] row = new double[2];
		int[] rowIndex = new int[2];

		ilp.setConstrType(i, LpSolve.LE);
		ilp.setRh(i, 1);
		row[0] = 1;
		rowIndex[0] = cols.forwSourceLeft.start + edge.id;
		row[1] = 1;
		rowIndex[1] = cols.forwSourceRight.start + edge.id;
		ilp.setRowex(i, 2, row, rowIndex);
		
		ilp.setConstrType(i + 1, LpSolve.LE);
		ilp.setRh(i + 1, 1);
		row[0] = 1;
		rowIndex[0] = cols.backSourceLeft.start + edge.id;
		row[1] = 1;
		rowIndex[1] = cols.backSourceRight.start + edge.id;
		ilp.setRowex(i + 1, 2, row, rowIndex);
	}
	
	/**
	 * Creates a K4 constraint for the two given edges.
	 * 
	 * @param node node incident to both edges
	 * @param leftEdge the left edge 
	 * @param rightEdge the right edge
	 * @param ilp currently processed ILP instance
	 * @param i index of the first row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setK4Constraint(KSlimNode node, KSlimNode.IncEntry leftEdge,
			KSlimNode.IncEntry rightEdge, LpSolve ilp, int i) throws LpSolveException {
		ilp.setConstrType(i, LpSolve.GE);
		ilp.setRh(i, 1);
		double[] row = new double[3];
		int[] rowIndex = new int[3];
		row[0] = 1;
		rowIndex[0] = (leftEdge.type == KSlimNode.IncEntry.Type.OUT
				? cols.forwSourceLeft.start : cols.backSourceLeft.start)
				+ leftEdge.edge.id;
		row[1] = 1;
		row[2] = 1;
		if (rightEdge.type == KSlimNode.IncEntry.Type.OUT) {
			rowIndex[1] = cols.forwSourceRight.start + rightEdge.edge.id;
			rowIndex[2] = cols.sourceAnchor.start + rightEdge.edge.id;
		}
		else {
			rowIndex[1] = cols.backSourceRight.start + rightEdge.edge.id;
			rowIndex[2] = cols.targetAnchor.start + rightEdge.edge.id;
		}
		ilp.setRowex(i, 3, row, rowIndex);
	}
	
	/**
	 * Creates six K5 edge constraints for the given edge. The first
	 * three rows are used for forward orientation, the second for
	 * backwards orientation.
	 * 
	 * @param edge the edge for which constraints shall be created
	 * @param ilp currently processed ILP instance
	 * @param i index of the first row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setK5Constraint(KSlimEdge edge, LpSolve ilp, int i)
			throws LpSolveException {
		double[] row = new double[2];
		int[] rowIndex = new int[2];

		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 0);
		row[0] = 1;
		rowIndex[0] = cols.forwSourceLeft.start + edge.id;
		row[1] = -1;
		rowIndex[1] = cols.backTargetRight.start + edge.id;
		ilp.setRowex(i++, 2, row, rowIndex);
		
		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 0);
		row[0] = 1;
		rowIndex[0] = cols.forwFaceLeft.start + edge.id;
		row[1] = -1;
		rowIndex[1] = cols.backFaceRight.start + edge.id;
		ilp.setRowex(i++, 2, row, rowIndex);
		
		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 0);
		row[0] = 1;
		rowIndex[0] = cols.forwTargetLeft.start + edge.id;
		row[1] = -1;
		rowIndex[1] = cols.backSourceRight.start + edge.id;
		ilp.setRowex(i++, 2, row, rowIndex);
		
		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 0);
		row[0] = 1;
		rowIndex[0] = cols.backSourceLeft.start + edge.id;
		row[1] = -1;
		rowIndex[1] = cols.forwTargetRight.start + edge.id;
		ilp.setRowex(i++, 2, row, rowIndex);
		
		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 0);
		row[0] = 1;
		rowIndex[0] = cols.backFaceLeft.start + edge.id;
		row[1] = -1;
		rowIndex[1] = cols.forwFaceRight.start + edge.id;
		ilp.setRowex(i++, 2, row, rowIndex);
		
		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 0);
		row[0] = 1;
		rowIndex[0] = cols.backTargetLeft.start + edge.id;
		row[1] = -1;
		rowIndex[1] = cols.forwSourceRight.start + edge.id;
		ilp.setRowex(i++, 2, row, rowIndex);
	}
	
	/**
	 * Creates a U1/2 node constraint for the given node. The node must have
	 * degree > 0.
	 * 
	 * @param node the node for which constraints shall be created
	 * @param ilp currently processed ILP instance
	 * @param i index of the row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setU12Constraint(KSlimNode node, LpSolve ilp, int i)
			throws LpSolveException {
		int rowSize = node.incidence.size();
		double[] row = new double[rowSize];
		int[] rowIndex = new int[rowSize];

		ilp.setConstrType(i, LpSolve.LE);
		ilp.setRh(i, 4);
		row[0] = 1;
		rowIndex[0] = cols.anchorPos.start + node.id;
		int j = 1;
		ListIterator<KSlimNode.IncEntry> edgeIter = node.incidence.listIterator(1);
		while (edgeIter.hasNext()) {
			KSlimNode.IncEntry nextEntry = edgeIter.next();
			row[j] = 1;
			rowIndex[j] = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
					? cols.sourceAnchor.start : cols.targetAnchor.start)
					+ nextEntry.edge.id;
			j++;
		}
		ilp.setRowex(i, rowSize, row, rowIndex);
	}
	
	/**
	 * Creates a U4 edge constraints for the given edge.
	 * 
	 * @param edge the edge for which constraints shall be created
	 * @param ilp currently processed ILP instance
	 * @param i index of the first row that shall be modified
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void setU4Constraint(KSlimEdge edge, LpSolve ilp, int i)
			throws LpSolveException {
		ListIterator<KSlimNode.IncEntry> sourceIter = edge.source.getIterator(
				edge, true);
		ListIterator<KSlimNode.IncEntry> targetIter = edge.target.getIterator(
				edge, false);
		int rowSize = sourceIter.nextIndex() + targetIter.nextIndex() + 7;
		double[] row = new double[rowSize];
		int[] rowIndex = new int[rowSize];

		ilp.setConstrType(i, LpSolve.EQ);
		ilp.setRh(i, 2);
		int j = 0;
		while (sourceIter.previousIndex() > 0) {
			KSlimNode.IncEntry nextEntry = sourceIter.previous();
			row[j] = 1;
			rowIndex[j] = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
					? cols.sourceAnchor.start : cols.targetAnchor.start)
					+ nextEntry.edge.id;
			j++;
		}
		row[j] = 1;
		rowIndex[j++] = cols.anchorPos.start + edge.source.id;
		row[j] = 1;
		rowIndex[j++] = cols.forwSourceLeft.start + edge.id;
		row[j] = 1;
		rowIndex[j++] = cols.forwFaceLeft.start + edge.id;
		row[j] = 1;
		rowIndex[j++] = cols.forwTargetLeft.start + edge.id;
		row[j] = -1;
		rowIndex[j++] = cols.forwSourceRight.start + edge.id;
		row[j] = -1;
		rowIndex[j++] = cols.forwFaceRight.start + edge.id;
		row[j] = -1;
		rowIndex[j++] = cols.forwTargetRight.start + edge.id;
		while (targetIter.previousIndex() > 0) {
			KSlimNode.IncEntry nextEntry = targetIter.previous();
			row[j] = -1;
			rowIndex[j] = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
					? cols.sourceAnchor.start : cols.targetAnchor.start)
					+ nextEntry.edge.id;
			j++;
		}
		row[j] = -1;
		rowIndex[j++] = cols.anchorPos.start + edge.target.id;
		row[j] = 4;
		rowIndex[j++] = cols.modHelp1.start + edge.id;
		ilp.setRowex(i, rowSize, row, rowIndex);
	}
	
	/**
	 * Creates a side constraint for the given edge.
	 * 
	 * @param edge edge for which the constraint shall be created
	 * @param side side on which the edge is incident
	 * @param ilp currently processed ILP instance
	 * @param isTarget indicates whether the target or the source is processed
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void addSideConstraint(KSlimEdge edge, PortSide side,
			LpSolve ilp, boolean isTarget) throws LpSolveException {
		int sideValue = -1;
		switch (side) {
		case NORTH:
			sideValue = 0;
			break;
		case EAST:
			sideValue = 1;
			break;
		case SOUTH:
			sideValue = 2;
			break;
		case WEST:
			sideValue = 3;
			break;
		default:
			return;
		}
		
		ListIterator<KSlimNode.IncEntry> edgeIter = isTarget ? edge.target
				.getIterator(edge, false) : edge.source.getIterator(edge, true);
		int rowSize = edgeIter.nextIndex();
		double[] row = new double[rowSize];
		int[] rowIndex = new int[rowSize];

		int j = 0;
		while (edgeIter.previousIndex() > 0) {
			KSlimNode.IncEntry nextEntry = edgeIter.previous();
			row[j] = 1;
			rowIndex[j] = (nextEntry.type == KSlimNode.IncEntry.Type.OUT
					? cols.sourceAnchor.start : cols.targetAnchor.start)
					+ nextEntry.edge.id;
			j++;
		}
		row[j] = 1;
		rowIndex[j++] = cols.anchorPos.start + (isTarget ? edge.target.id
				: edge.source.id);
		ilp.addConstraintex(rowSize, row, rowIndex, LpSolve.EQ, sideValue);
	}
	
	/**
	 * Creates a dummy node constraint for the given node. This adds four
	 * new rows to the ILP instance.
	 * 
	 * @param node node for which the constraint shall be created
	 * @param ilp currently processed ILP instance
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void addDummyNodeConstraint(KSlimNode node, LpSolve ilp)
			throws LpSolveException {
		assert node.incidence.size() == 4;
		double[] row = new double[1];
		int[] rowIndex = new int[1];
		for (KSlimNode.IncEntry edgeEntry : node.incidence) {
			row[0] = 1;
			if (edgeEntry.type == KSlimNode.IncEntry.Type.OUT)
				rowIndex[0] = cols.sourceAnchor.start + edgeEntry.edge.id;
			else
				rowIndex[0] = cols.targetAnchor.start + edgeEntry.edge.id;
			ilp.addConstraintex(1, row, rowIndex, LpSolve.EQ, 1);
		}
	}
	
	/**
	 * Applies the ILP solution to the given graph.
	 * 
	 * @param graph graph to which the solution should be applied
	 * @param ilp ILP with solution vector
	 * @throws LpSolveException if the lp_solve library reports a failure
	 */
	private void applySolution(KSlimGraph graph, LpSolve ilp) throws LpSolveException {
		int rowCount = ilp.getNrows();
		double[] solution = new double[1 + rowCount + ilp.getNcolumns()];
		ilp.getPrimalSolution(solution);
		// retrieve bend points from ILP solution
		for (KSlimEdge edge : graph.edges) {
			if (solution[rowCount + cols.forwSourceLeft.start + edge.id] > 0.5)
				edge.bends.add(new Bend(edge, Bend.Type.LEFT));
			if (solution[rowCount + cols.forwSourceRight.start + edge.id] > 0.5)
				edge.bends.add(new Bend(edge, Bend.Type.RIGHT));
			long faceLeftBends = Math.round(solution[rowCount
			        + cols.forwFaceLeft.start + edge.id]);
			for (int i = 0; i < faceLeftBends; i++) {
				edge.bends.add(new Bend(edge, Bend.Type.LEFT));
			}
			long faceRightBends = Math.round(solution[rowCount
			        + cols.forwFaceRight.start + edge.id]);
			for (int i = 0; i < faceRightBends; i++) {
				edge.bends.add(new Bend(edge, Bend.Type.RIGHT));
			}
			if (solution[rowCount + cols.forwTargetLeft.start + edge.id] > 0.5)
				edge.bends.add(new Bend(edge, Bend.Type.LEFT));
			if (solution[rowCount + cols.forwTargetRight.start + edge.id] > 0.5)
				edge.bends.add(new Bend(edge, Bend.Type.RIGHT));
		}
		
		// retrieve port sides from ILP solution
		for (KSlimNode node : graph.nodes) {
			if (!node.incidence.isEmpty()) {
				int sideValue = (int)solution[rowCount + cols.anchorPos.start + node.id];
				ListIterator<KSlimNode.IncEntry> edgeIter = node.incidence.listIterator();
				KSlimNode.IncEntry nextEntry = edgeIter.next();
				if (nextEntry.type == KSlimNode.IncEntry.Type.OUT)
					nextEntry.edge.sourceSide = getSide(sideValue);
				else
					nextEntry.edge.targetSide = getSide(sideValue);
				while (edgeIter.hasNext()) {
					nextEntry = edgeIter.next();
					if (nextEntry.type == KSlimNode.IncEntry.Type.OUT) {
						sideValue += (int)solution[rowCount
						        + cols.sourceAnchor.start + nextEntry.edge.id];
						nextEntry.edge.sourceSide = getSide(sideValue);
					}
					else {
						sideValue += (int)solution[rowCount
						        + cols.targetAnchor.start + nextEntry.edge.id];
						nextEntry.edge.targetSide = getSide(sideValue);
					}
				}
			}
		}
	}
	
	/**
	 * Returns the corresponding node side for a side value.
	 * 
	 * @param sideValue integer value 0...4
	 * @return corresponding node side
	 */
	private KSlimNode.Side getSide(int sideValue) {
		switch (sideValue) {
		case 0:
			return KSlimNode.Side.NORTH;
		case 1:
			return KSlimNode.Side.EAST;
		case 2:
			return KSlimNode.Side.SOUTH;
		case 3:
			return KSlimNode.Side.WEST;
		default:
			return KSlimNode.Side.UNDEFINED;
		}
	}

}
