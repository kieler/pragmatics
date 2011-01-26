package de.cau.cs.kieler.kex.ui.wizards.importing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.WorkbenchJob;

public class FilteredCheckboxTree extends FilteredTree {

    // TODO check if, refreshJob is needed
    private WorkbenchJob refreshJob;

    public FilteredCheckboxTree(Composite parent, int treeStyle, PatternFilter filter) {
        // TODO check if useful, uses new look
        super(parent, treeStyle, filter, true);
    }

    @Override
    protected TreeViewer doCreateTreeViewer(Composite parent, int style) {
        return new FilterableCheckboxTreeViewer(parent, style);
    }

    @Override
    protected WorkbenchJob doCreateRefreshJob() {
        WorkbenchJob job = super.doCreateRefreshJob();
        this.refreshJob = job;
        return job;
    }

    public void resetFilter() {
        getFilterControl().setText(this.initialText);
        refreshJob.cancel();
        refreshJob.runInUIThread(new NullProgressMonitor());
    }

    int getTreeLocationOffset() {
        GridLayout layout = (GridLayout) getLayout();
        return layout.horizontalSpacing + layout.marginTop
                + ((GridData) getLayoutData()).verticalIndent + getFilterControl().getSize().y + 1;
    }

    interface PreRefreshNotifier {
        public void preRefresh(FilterableCheckboxTreeViewer viewer, boolean filtered);

    }

    public class FilterableCheckboxTreeViewer extends CheckboxTreeViewer {

        static final String NONE = "none";
        static final String CHECKED = "checked";
        static final String GREYED = "greyed";
        static final String CHECKED_GREYED = "checked_greyed";

        class FilteredCheckboxTreeItem {
            Object data;
            String state;
            List<Object> children = new ArrayList<Object>();

            public FilteredCheckboxTreeItem(Object dataParam, String stateParam,
                    Map<Object, FilteredCheckboxTreeItem> itemCache, FilteredCheckboxTreeItem parent) {
                this.data = dataParam;
                this.state = stateParam;
                itemCache.put(data, this);
                if (parent != null) {
                    parent.children.add(this);
                }
            }
        }

        Map<Object, FilteredCheckboxTreeItem> itemCache = new HashMap<Object, FilteredCheckboxTreeItem>();

        List<PreRefreshNotifier> refreshingListeners = new ArrayList<PreRefreshNotifier>();

        @Override
        protected void unmapAllElements() {
            itemCache = new HashMap<Object, FilteredCheckboxTreeItem>();
            super.unmapAllElements();
        }

        public FilterableCheckboxTreeViewer(Composite parent, int style) {
            super(parent, style);
            addCheckStateListener(new ICheckStateListener() {

                public void checkStateChanged(CheckStateChangedEvent event) {
                    FilteredCheckboxTreeItem item = itemCache.get(event.getElement());
                    if (item != null) {
                        item.state = event.getChecked() ? CHECKED : NONE;
                    }
                }
            });
        }

        public void addPreRefreshNotifier(PreRefreshNotifier notifier) {
            if (refreshingListeners.contains(notifier)) {
                return;
            }
            refreshingListeners.add(notifier);
        }

        @Override
        public boolean getChecked(Object element) {
            Widget testFindItem = getViewer().testFindItem(element);
            if (testFindItem == null) {
                if (itemCache.containsKey(element)) {
                    FilteredCheckboxTreeItem item = itemCache.get(element);
                    if (item.state.equals(CHECKED) || item.state.equals(CHECKED_GREYED)
                            || item.state.equals(GREYED)) {
                        return true;
                    } else if (item.state.equals(NONE)) {
                        return false;
                    }
                }
            }
            return super.getChecked(element);
        }

        public Object[] getCheckedChildren(Object element) {
            FilteredCheckboxTreeItem item = itemCache.get(element);
            List<Object> checkedChildren = new ArrayList<Object>();
            if (item != null) {
                for (Object object : item.children) {
                    FilteredCheckboxTreeItem child = (FilteredCheckboxTreeItem) object;
                    if (child.state == CHECKED) {
                        checkedChildren.add(child.data);
                    }
                }
            }
            return checkedChildren.toArray();
        }

        @Override
        public Object[] getCheckedElements() {
            List<Object> checkedElements = new LinkedList<Object>();
            for (Object object : itemCache.values()) {
                FilteredCheckboxTreeItem item = (FilteredCheckboxTreeItem) object;
                Widget testFindItem = getViewer().testFindItem(item.data);
                // TODO that goes wrong, think about and correct it.
                if (testFindItem == null) {
                    if (item.state.equals(CHECKED) || item.state.equals(CHECKED_GREYED)
                            || item.state.equals(GREYED)) {
                        checkedElements.add(item.data);
                    }
                } else {
                    if (((TreeItem) testFindItem).getChecked()) {
                        checkedElements.add(testFindItem.getData());
                    }
                }
            }
            return checkedElements.toArray();
        }

        @Override
        public boolean setChecked(Object element, boolean state) {
            if (itemCache.containsKey(element)) {
                FilteredCheckboxTreeItem item = itemCache.get(element);
                item.state = state ? CHECKED : NONE;
            }
            return super.setChecked(element, state);
        }

        @Override
        public void setCheckedElements(Object[] elements) {
            Set<Object> s = new HashSet<Object>(itemCache.keySet());
            s.removeAll(new HashSet<Object>(Arrays.asList(elements)));
            for (int i = 0; i < elements.length; i++) {
                FilteredCheckboxTreeItem item = itemCache.get(elements[i]);
                if (item != null) {
                    item.state = CHECKED;
                }
            }
            for (Iterator<Object> it = s.iterator(); it.hasNext();) {
                Object object = it.next();
                FilteredCheckboxTreeItem item = itemCache.get(object);
                if (item != null) {
                    item.state = NONE;
                }
            }
            super.setCheckedElements(elements);
        }

        @Override
        public boolean setSubtreeChecked(Object element, boolean state) {
            String newState = state ? CHECKED : NONE;
            TreeItem item = (TreeItem) testFindItem(element);
            FilteredCheckboxTreeItem checkBoxItem = itemCache.get(element);
            if (item != null && checkBoxItem != null) {
                checkBoxItem.state = newState;
                TreeItem[] items = item.getItems();
                for (int i = 0; i < items.length; i++) {
                    item = items[i];
                    if (item != null) {
                        checkBoxItem = itemCache.get(item.getData());
                        if (checkBoxItem != null) {
                            checkBoxItem.state = newState;

                        }
                    }
                }
            }
            return super.setSubtreeChecked(element, state);
        }

        @Override
        protected void preservingSelection(Runnable updateCode) {
            super.preservingSelection(updateCode);

            for (TreeItem item : getAllTreeItems(treeViewer.getTree().getItems())) {
                doApplyCheckedState(item, item.getData());
            }
        }

        @Override
        protected void internalRefresh(Object element, boolean updateLabels) {
            String text = FilteredCheckboxTree.this.getFilterString();
            boolean initial = initialText != null && initialText.equals(text);
            boolean filtered = (text.length() > 0 && !initial);

            for (Object ob : refreshingListeners) {
                PreRefreshNotifier notifier = (PreRefreshNotifier) ob;
                notifier.preRefresh(FilterableCheckboxTreeViewer.this, filtered);
            }
            saveCheckedState();
            super.internalRefresh(element, updateLabels);
            treeViewer.expandAll();
        }

        private void doApplyCheckedState(TreeItem item, Object element) {
            super.doUpdateItem(item, element);

            TreeItem treeItem = item;
            if (itemCache.containsKey(element)) {
                String state = (itemCache.get(element)).state;
                if (state.equals(CHECKED_GREYED)) {
                    treeItem.setGrayed(true);
                    treeItem.setChecked(true);
                } else if (state.equals(CHECKED_GREYED)) {
                    treeItem.setGrayed(false);
                    treeItem.setChecked(true);
                } else if (state.equals(GREYED)) {
                    treeItem.setGrayed(true);
                    treeItem.setChecked(false);
                } else {
                    treeItem.setGrayed(false);
                    treeItem.setChecked(false);
                }
            }
        }

        private ArrayList<TreeItem> getAllTreeItems(TreeItem[] roots) {
            ArrayList<TreeItem> list = new ArrayList<TreeItem>();
            for (int i = 0; i < roots.length; i++) {
                TreeItem item = roots[i];
                list.add(item);
                list.addAll(getAllTreeItems(item.getItems()));
            }
            return list;
        }

        /**
         * Saves the checked state of all the elements in the tree
         */
        private void saveCheckedState() {
            TreeItem[] items = treeViewer.getTree().getItems();
            for (int i = 0; i < items.length; i++) {
                TreeItem item = items[i];
                if (!itemCache.containsKey(item.getData())) {
                    new FilteredCheckboxTreeItem(item.getData(), getItemState(item), itemCache,
                            null);
                }
                FilteredCheckboxTreeItem filteredCheckboxTreeItem = itemCache.get(item.getData());
                filteredCheckboxTreeItem.state = getItemState(item);
                saveCheckedState(filteredCheckboxTreeItem, item);
            }
        }

        private void saveCheckedState(FilteredCheckboxTreeItem parent, TreeItem parentItem) {
            TreeItem[] items = parentItem.getItems();
            for (int i = 0; i < items.length; i++) {
                TreeItem item = items[i];
                if (!itemCache.containsKey(item.getData())) {
                    new FilteredCheckboxTreeItem(item.getData(), getItemState(item), itemCache,
                            parent);
                }
                FilteredCheckboxTreeItem filteredCheckboxTreeItem = itemCache.get(item.getData());
                filteredCheckboxTreeItem.state = getItemState(item);
                saveCheckedState(filteredCheckboxTreeItem, item);
            }

        }

        private String getItemState(TreeItem item) {
            if (item.getChecked() && item.getGrayed()) {
                return CHECKED_GREYED;
            } else if (item.getChecked()) {
                return CHECKED;
            } else if (item.getGrayed()) {
                return GREYED;
            } else {
                return NONE;
            }
        }

    }
}
