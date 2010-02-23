package de.cau.cs.kieler.graphs.diagram.navigator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class GraphsNavigatorItem extends GraphsAbstractNavigatorItem {

	/**
	 * @generated
	 */
	static {
		final Class[] supportedTypes = new Class[] { View.class, EObject.class };
		Platform.getAdapterManager().registerAdapters(new IAdapterFactory() {

			public Object getAdapter(Object adaptableObject, Class adapterType) {
				if (adaptableObject instanceof de.cau.cs.kieler.graphs.diagram.navigator.GraphsNavigatorItem
						&& (adapterType == View.class || adapterType == EObject.class)) {
					return ((de.cau.cs.kieler.graphs.diagram.navigator.GraphsNavigatorItem) adaptableObject)
							.getView();
				}
				return null;
			}

			public Class[] getAdapterList() {
				return supportedTypes;
			}
		}, de.cau.cs.kieler.graphs.diagram.navigator.GraphsNavigatorItem.class);
	}

	/**
	 * @generated
	 */
	private View myView;

	/**
	 * @generated
	 */
	private boolean myLeaf = false;

	/**
	 * @generated
	 */
	public GraphsNavigatorItem(View view, Object parent, boolean isLeaf) {
		super(parent);
		myView = view;
		myLeaf = isLeaf;
	}

	/**
	 * @generated
	 */
	public View getView() {
		return myView;
	}

	/**
	 * @generated
	 */
	public boolean isLeaf() {
		return myLeaf;
	}

	/**
	 * @generated
	 */
	public boolean equals(Object obj) {
		if (obj instanceof de.cau.cs.kieler.graphs.diagram.navigator.GraphsNavigatorItem) {
			return EcoreUtil
					.getURI(getView())
					.equals(
							EcoreUtil
									.getURI(((de.cau.cs.kieler.graphs.diagram.navigator.GraphsNavigatorItem) obj)
											.getView()));
		}
		return super.equals(obj);
	}

	/**
	 * @generated
	 */
	public int hashCode() {
		return EcoreUtil.getURI(getView()).hashCode();
	}

}
