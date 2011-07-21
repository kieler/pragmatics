/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
 *
 * http://www.informatik.uni-kiel.de/rtsys/kieler/
 *
 * Copyright 2008 by
 * + Christian-Albrechts-University of Kiel
 *     + Department of Computer Science
 *         + Real-Time and Embedded Systems Group
 *
 * This code is provided under the terms of the Eclipse Public License (EPL).
 * See the file epl-v10.html for the license text.
 */

package de.cau.cs.kieler.kwebs.client.ui;

import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import de.cau.cs.kieler.kwebs.client.ui.LogView.ViewContentComparator.SortProperty;
import de.cau.cs.kieler.kwebs.logging.ILoggerListener;
import de.cau.cs.kieler.kwebs.logging.Logger;
import de.cau.cs.kieler.kwebs.logging.Logger.LoggerEvent;

/**
 * This class defines a log view which. It registers itself to the {@link Logger} as
 * listener at instantiation time.
 *
 * @kieler.rating  2011-05-04 red
 * @author  swe
 */
public class LogView extends ViewPart implements ILoggerListener {

    /** */
    private TableViewer viewer;

    /** */
    private ViewContentComparator comparator;

    /**
     *
     */
    public LogView() {
        super();
        Logger.addLoggerListener(this);
    }

    /**
     *
     */
    @Override
    public final void dispose() {
        Logger.removeLoggerListener(this);
        super.dispose();
    }

    /**
     *
     * @author swe
     *
     */
    class ViewContentProvider implements IStructuredContentProvider {

        /**
         *
         * @param v
         * @param oldInput
         * @param newInput
         */
        public void inputChanged(final Viewer v, final Object oldInput,
            final Object newInput) {
        }

        /**
         *
         */
        public void dispose() {
        }

        /**
         *
         * @param parent
         */
        public Object[] getElements(final Object parent) {
            if (parent instanceof Vector) {
                return entries.toArray();
            }
            return new Object[0];
        }

    }

    /**
     *
     * @author swe
     *
     */
    static class ViewContentComparator extends ViewerComparator {

        /** Show entries in ascending order. */
        private static final int ASCENDING
            = 0;

        /** Show entries in decending order. */
        private static final int DESCENDING
            = 1;

        /** Enum for selecting the property to sort about. */
        public enum SortProperty {
            DATE,
            LEVEL,
            MESSAGE,
            CLASS,
            METHOD,
            LINE,
            EXCEPTION
        }

        /** Order by this priority. */
        private SortProperty sortProperty
            = SortProperty.DATE;

        /** The selected order. */
        private int direction
            = ASCENDING;

        /**
         *
         * @param theproperty
         */
        public final void setPropertyToCompare(final SortProperty theproperty) {
            if (sortProperty == theproperty) {
                this.direction = (this.direction == DESCENDING
                        ? ASCENDING
                                : DESCENDING
                );
            } else {
                sortProperty  = theproperty;
                direction = DESCENDING;
            }
        }

        /**
         *
         */
        @Override
        public final int compare(final Viewer theviewer, final Object e1,
            final Object e2) {
            LoggerEvent event1 = (LoggerEvent) e1;
            LoggerEvent event2 = (LoggerEvent) e2;

            int result = 0;
            switch (sortProperty) {
                case DATE:
                    result = event1.getDate().compareTo(event2.getDate());
                    break;
                case LEVEL:
                    result = event1.getSeverity().compareTo(event2.getSeverity());
                    break;
                case MESSAGE:
                    result = event1.getMessage().compareTo(event2.getMessage());
                    break;
                case CLASS:
                    result = event1.getClas().compareTo(event2.getClas());
                    break;
                case METHOD:
                    result = event1.getMethod().compareTo(event2.getMethod());
                    break;
                case LINE:
                    result = event1.getLine().compareTo(event2.getLine());
                    break;
                case EXCEPTION:
                    Throwable t1 = event1.getThrowable();
                    Throwable t2 = event2.getThrowable();
                    String msg1 = (t1 != null ? t1.getMessage() : "");
                    msg1 = (msg1 != null ? msg1 : "");
                    String msg2 = (t2 != null ? t2.getMessage() : "");
                    msg2 = (msg2 != null ? msg2 : "");
                    result = msg1.compareTo(msg2);
                    break;
                default:
                    result = 0;
            }
            //flip direction on descending order
            if (direction == DESCENDING) {
                result = -result;
            }
            return result;
        }

    }

    /**
     *
     * @param column
     * @param index
     * @return
     */
    private SelectionAdapter getSelectionAdapter(final TableColumn column,
        final int index) {
        SelectionAdapter selectionAdapter = new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                comparator.setPropertyToCompare(SortProperty.values()[index]);
                int dir = viewer.getTable().getSortDirection();
                if (viewer.getTable().getSortColumn() == column) {
                    dir = (dir == SWT.UP ? SWT.DOWN : SWT.UP);
                } else {
                    dir = SWT.DOWN;
                }
                viewer.getTable().setSortDirection(dir);
                viewer.getTable().setSortColumn(column);
                viewer.refresh();
            }
        };
        return selectionAdapter;
    }

    /**
     *
     * @author swe
     *
     */
    public class LoggerEventDetailDialog extends ApplicationWindow {

        /** */
        private LoggerEvent event;

        /**
         *
         * @param parentShell
         * @param theevent
         */
        public LoggerEventDetailDialog(final Shell parentShell,
            final LoggerEvent theevent) {
            super(parentShell);
            parentShell.setText("Details on the logging event");
            event = theevent;
        }

        /** margin width for layouts. */
        private static final int MARGIN_WIDTH
            = 5;

        /** margin width for layouts. */
        private static final int MARGIN_HEIGHT
            = 5;

        /** margin width for layouts. */
        private static final int DIALOG_WIDTH
            = 500;

        /** margin width for layouts. */
        private static final int DIALOG_HEIGHT
            = 400;

        /**
         * Returns a predefined fill layout.
         * 
         * @return a predefined fill layout
         */
        private FillLayout getFillLayout() {
            FillLayout layout = new FillLayout();
            layout.marginWidth = MARGIN_WIDTH;
            layout.marginHeight = MARGIN_HEIGHT;
            return layout;
        }

        /**
         * Creates the ui elements of the detail view for a logger event.
         * 
         * @param parent
         *            the parent ui element
         * @return
         */
        protected final Control createContents(final Composite parent) {

            parent.setSize(new Point(DIALOG_WIDTH, DIALOG_HEIGHT));

            Composite top = new Composite(parent, SWT.NONE);

            top.setLayout(getFillLayout());

            TabFolder tf = new TabFolder(top, SWT.NONE);

            TabItem tiBasic = new TabItem(tf, SWT.NONE);
            tiBasic.setText("Basic info");
            tiBasic.setControl(createBasicComposite(tf));

            TabItem tiAdditional = null;
            if (event.getData() != null) {
                tiAdditional = new TabItem(tf, SWT.NONE);
                tiAdditional.setText("Additional data");
                tiAdditional.setControl(createAdditionalComposite(tf));
            }

            TabItem tiException = null;
            if (event.getThrowable() != null) {
                tiException = new TabItem(tf, SWT.NONE);
                tiException.setText("Exception occurred");
                tiException.setControl(createExceptionComposite(tf));
            }

            return super.createContents(parent);

        }

        /**
         *
         * @param tf
         * @param throwable
         * @return
         */
        private Composite createBasicComposite(final TabFolder tf) {
            Composite composite = new Composite(tf, SWT.NONE);
            Text text = new Text(composite,
                SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            );
            composite.setLayout(getFillLayout());
            text.setEditable(false);
            text.setEnabled(true);
            String[] eventProps = (String[]) event.toObjectArray();
            String data = "";

            //CHECKSTYLEOFF MagicNumber

            data += "Date:  " + eventProps[0] + "\n\n";
            data += "Level:  " + eventProps[1] + "\n\n";
            data += "Message:  " + eventProps[2] + "\n\n";
            data += "Class:  " + eventProps[3] + "\n\n";
            data += "Method:  " + eventProps[4] + "\n\n";
            data += "Line:  " + eventProps[5] + "\n\n";
            data += "Exception:  " + eventProps[7] + "\n\n";

            //CHECKSTYLEON MagicNumber

            text.append(data);
            return composite;
        }

        /**
         *
         * @param tf
         * @return
         */
        private Composite createExceptionComposite(final TabFolder tf) {
            Composite composite = new Composite(tf, SWT.NONE);
            Text text = new Text(composite,
                SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            );
            Throwable throwable = event.getThrowable();
            composite.setLayout(getFillLayout());
            text.setEditable(false);
            text.setEnabled(true);
            text.append(throwable.getMessage() + "\n");
            for (StackTraceElement element : throwable.getStackTrace()) {
                text.append(element.toString() + "\n");
            }
            return composite;
        }

        /**
         *
         * @param tf
         * @return
         */
        private Composite createAdditionalComposite(final TabFolder tf) {
            Composite composite = new Composite(tf, SWT.NONE);
            Text text = new Text(composite,
                SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            );
            String data = event.getData();
            composite.setLayout(getFillLayout());
            text.setEditable(false);
            text.setEnabled(true);
            text.append(data + "\n");
            return composite;
        }

    }

    /**
     * {@inheritDoc}
     */
    public final void createPartControl(final Composite parent) {
        viewer = new TableViewer(parent,
            SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
        );
        createColumns(parent, viewer);

        Table table = viewer.getTable();

        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setInput(entries);

        comparator = new ViewContentComparator();
        viewer.setComparator(comparator);

        viewer.addDoubleClickListener(
            new IDoubleClickListener() {
                public void doubleClick(final DoubleClickEvent event) {
                    ISelection sel = event.getSelection();
                    Object obj
                        = ((IStructuredSelection) sel).getFirstElement();
                    if (obj instanceof LoggerEvent) {
                        Display display = Display.getCurrent();
                        Shell shell = new Shell(display);
                        ApplicationWindow detail = new LoggerEventDetailDialog(
                            shell, (LoggerEvent) obj);
                        detail.setBlockOnOpen(true);
                        detail.open();
                    }
                }
            }
        );

        Menu menu = new Menu(table);
        MenuItem item = new MenuItem(menu, SWT.NONE);
        item.setText("Clear log");
        item.addSelectionListener(
            new SelectionAdapter() {
                @Override
                public void widgetSelected(final SelectionEvent e) {
                    clearModel();
                    super.widgetSelected(e);
                }
            }
        );

        table.setMenu(menu);
    }

    /** The date formatter used. */
    private static final SimpleDateFormat DATE_FORMATTER
        = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss Z");

    /** The names of the properties of a logger event. */
    private static final String[] EVENTLOCALNAMES_ARRAY
        = new String[] { "Date", "Level", "Message", "Class", "Method", "Line", "Exception" };

    /** The predefined widths of the log view columns. */
    private static final int[] TABLECOLUMN_WIDTHS
        = new int[] {180, 80, 600, 100, 100, 100, 100};

    /**
     * 
     * @param parent
     * @param theviewer
     */
    private void createColumns(final Composite parent,
        final TableViewer theviewer) {
        TableViewerColumn col = null;

        //CHECKSTYLEOFF MagicNumber

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[0], TABLECOLUMN_WIDTHS[0], 0);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                return DATE_FORMATTER.format(event.getDate());
            }
        });

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[1], TABLECOLUMN_WIDTHS[1], 1);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                return event.getSeverity().name();
            }
        });

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[2], TABLECOLUMN_WIDTHS[2], 2);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                return event.getMessage();
            }
        });

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[3], TABLECOLUMN_WIDTHS[3], 3);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                return event.getClas();
            }
        });

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[4], TABLECOLUMN_WIDTHS[4], 4);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                return event.getMethod();
            }
        });

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[5], TABLECOLUMN_WIDTHS[5], 5);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                return event.getLine();
            }
        });

        col = createColumn(theviewer, EVENTLOCALNAMES_ARRAY[6], TABLECOLUMN_WIDTHS[6], 6);
        col.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(final Object element) {
                LoggerEvent event = (LoggerEvent) element;
                Throwable throwable = event.getThrowable();
                if (throwable != null) {
                    return throwable.getMessage();
                }
                return null;
            }
        });

        //CHECKSTYLEON MagicNumber

    }

    /**
     *
     * @param title
     * @param width
     * @return
     */
    private TableViewerColumn createColumn(final TableViewer theviewer,
            final String title, final int width, final int index) {
        final TableViewerColumn viewerColumn
            = new TableViewerColumn(theviewer, SWT.NONE);
        final TableColumn column
            = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(width);
        column.setResizable(true);
        column.setMoveable(true);
        column.addSelectionListener(getSelectionAdapter(column, index));
        return viewerColumn;
    }

    ////////////////////

    /**
     * Passing the focus request to the viewer's control.
     */
    public final void setFocus() {
        viewer.getControl().setFocus();
    }

    ////////////////////

    /** Default maximum of visible log entries. */
    private static final int DEFAULT_MAXENTRIES
        = 200;

    /** Default factor which defines the number of log
     *  entries to be removed when {@link maxEntries}
     *  log entries are reached. The number of log entries
     *  which will be removed is {@code maxEntries *
     *  DEFAULT_CLEARFACTOR}.*/
    private static final double DEFAULT_CLEARFACTOR
        = 0.75;

    /** Runtime maximum of visible log entries. Default
     *  value is set to DEFAULT_MAXENTRIES. */
    private int maxEntries
        = DEFAULT_MAXENTRIES;

    /**
     * Returns the maximum number of entries displayed in this log view.
     * 
     * @return the maximum number of entries
     */
    public final int getMaxEntries() {
        return maxEntries;
    }

    /**
     * Sets the maximum number of entries displayed in this log view.
     * 
     * @param themaxEntries
     *            the maximum number of entries
     */
    public final void setMaxEntries(final int themaxEntries) {
        maxEntries = themaxEntries;
        entriesLock.lock();
        updateModel();
        entriesLock.unlock();
    }

    /** The model of the table viewer. */
    private Vector<LoggerEvent> entries
        = new Vector<LoggerEvent>();

    /** Lock for synchronizing model changes. */
    private Lock entriesLock
        = new ReentrantLock();

    /**
     * Adds a logger event to the underlying model of the table viewer and displays it in the view.
     * 
     * @param event
     *            the logger event
     *         
     */
    public final void loggerEvent(final LoggerEvent event) {
        entriesLock.lock();
        entries.add(event);
        updateModel();
        entriesLock.unlock();
    }

    /**
     * Updates the underlying model of the table viewer and optionally refreshes its view.
     */
    private void updateModel() {
        if (entries.size() >= maxEntries) {
            int remEntries = (int) (maxEntries * DEFAULT_CLEARFACTOR);
            entries = new Vector<LoggerEvent>(
                entries.subList(remEntries, entries.size())
            );
            updateView();
        } else {
            refreshView();
        }
    }

    /**
     * Clears the underlying model of the table viewer and updates its view.
     */
    private void clearModel() {
        entriesLock.lock();
        entries = new Vector<LoggerEvent>();
        updateView();
        entriesLock.unlock();
    }

    /**
     * Sets a new model for the table viewer.
     */
    private void updateView() {
        try {
            Display display = Display.getDefault();
            if (!display.isDisposed()) {
                display.asyncExec(new Runnable() {
                    public void run() { viewer.setInput(entries); } }
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Refreshes the table viewer.
     */
    private void refreshView() {
        try {
            Display display = Display.getDefault();
            if (!display.isDisposed()) {
                display.asyncExec(new Runnable() {
                    public void run() { viewer.refresh(); } }
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
