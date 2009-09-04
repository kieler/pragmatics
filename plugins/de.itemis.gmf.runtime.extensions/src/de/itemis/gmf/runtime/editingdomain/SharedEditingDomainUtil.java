/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.itemis.gmf.runtime.editingdomain;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class SharedEditingDomainUtil {

	public static final String TYPE_REGISTRY_CLIENT_CONTEXT_ID = "SharedClientContext";

	public static TransactionalEditingDomain getSharedEditingDomain(String id) {
		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE
				.getEditingDomain(id);
		addResourceTrackingModificationAdapter(editingDomain);
		return editingDomain;
	}

	private static final class SetTrackModificationAdapter implements Adapter {
		private final NotificationFilter diagramResourceModifiedFilter;
		private Notifier myTarget;

		private SetTrackModificationAdapter(
				NotificationFilter diagramResourceModifiedFilter) {
			this.diagramResourceModifiedFilter = diagramResourceModifiedFilter;
		}

		public Notifier getTarget() {
			return myTarget;
		}

		public boolean isAdapterForType(Object type) {
			return type == SetTrackModificationAdapter.class;
		}

		public void notifyChanged(Notification notification) {
			if (diagramResourceModifiedFilter.matches(notification)) {
				Object value = notification.getNewValue();
				if (value instanceof Resource) {
					((Resource) value).setTrackingModification(true);
				}
			}
		}

		public void setTarget(Notifier newTarget) {
			myTarget = newTarget;
		}

	}

	private static void addResourceTrackingModificationAdapter(
			TransactionalEditingDomain editingDomain) {
		EList<Adapter> adapters = editingDomain.getResourceSet().eAdapters();
		Adapter adapter = EcoreUtil.getAdapter(adapters,
				SetTrackModificationAdapter.class);
		if (adapter == null) {
			final NotificationFilter diagramResourceModifiedFilter = NotificationFilter
					.createNotifierFilter(editingDomain.getResourceSet()).and(
							NotificationFilter
									.createEventTypeFilter(Notification.ADD))
					.and(
							NotificationFilter.createFeatureFilter(
									ResourceSet.class,
									ResourceSet.RESOURCE_SET__RESOURCES));
			adapters.add(new SetTrackModificationAdapter(
					diagramResourceModifiedFilter));
		}
	}
}
