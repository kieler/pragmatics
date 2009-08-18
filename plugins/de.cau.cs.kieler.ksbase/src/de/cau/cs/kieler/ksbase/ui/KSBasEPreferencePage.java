package de.cau.cs.kieler.ksbase.ui;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.internal.core.ResolvedSourceType;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ControlEditor;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.part.FileEditorInput;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.transformations.EditorTransformationSettings;
import de.cau.cs.kieler.ksbase.transformations.Transformation;
import de.cau.cs.kieler.ksbase.transformations.TransformationManager;

@SuppressWarnings("restriction")
public class KSBasEPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	protected static final String DIAGRAM_EDITORS[] = new String[] { "org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor" };
	protected static final String DIAGRAM_EDIT_PARTS[] = new String[] {
			"org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart",
			"org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart" };

	
	private class CheckBoxDialog extends Dialog {

		protected Object result;
		Button btMenu, btContext, btToolbar, btBalloon;

		public CheckBoxDialog(Shell parent, int style) {
			super(parent, style);
		}

		public Object open(boolean[] initalSelections) {
			if (initalSelections == null | initalSelections.length != 4)
				return null;

			Shell parent = getParent();
			final Shell shell = new Shell(parent, SWT.DIALOG_TRIM
					| SWT.APPLICATION_MODAL);
			shell.setText("Select visibility");

			Composite pane = new Composite(shell, SWT.NONE);
			pane.setLayout(new GridLayout(4, false));

			new Label(pane, SWT.NONE).setText("Show in Menu");
			btMenu = new Button(pane, SWT.CHECK);
			btMenu.setSelection(initalSelections[0]);

			new Label(pane, SWT.NONE).setText("Show in Context");
			btContext = new Button(pane, SWT.CHECK);
			btContext.setSelection(initalSelections[1]);

			new Label(pane, SWT.NONE).setText("Show in Toolbar");
			btToolbar = new Button(pane, SWT.CHECK);
			btToolbar.setSelection(initalSelections[2]);

			new Label(pane, SWT.NONE).setText("Show in Balloon");
			btBalloon = new Button(pane, SWT.CHECK);
			btBalloon.setSelection(initalSelections[3]);
			new Label(pane, SWT.NONE);

			Button btCancel = new Button(pane, SWT.NONE);
			btCancel.setText("Cancel");
			btCancel.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub

				}

				public void widgetSelected(SelectionEvent e) {
					result = null;
					shell.dispose();
				}

			});
			Button btOK = new Button(pane, SWT.NONE);
			btOK.setText("OK");
			btOK.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
				}

				public void widgetSelected(SelectionEvent e) {
					result = new boolean[] { btMenu.getSelection(),
							btContext.getSelection(), btToolbar.getSelection(),
							btBalloon.getSelection() };
					shell.dispose();
				}

			});
			shell.setLayout(new GridLayout(1, false));
			shell.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			// pane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			shell.layout();
			shell.pack();
			shell.open();

			Display display = parent.getDisplay();

			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}

			return result;
		}
	}

	protected Text sfMetaModel, sfMenu, sfMenuLoc, sfToolbarLoc, sfXtendFile;
	protected Combo cbEditors;
	protected Button bfShowMenu, bfShowToolbar, bfShowPopup, bfShowBalloon,
			bfAutoLayout, btBrowseXtend;
	FileFieldEditor dfDefaultIcon, xtendFileEditor, btTableRemove, btTableAdd;
	protected EditorTransformationSettings activeEditor;
	protected Table table;
	Composite tableComp, browserContainer, btComp;

	public KSBasEPreferencePage() {
		setDescription("Preferences for the KIELER Structure Based Editing Features.");
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);

		Label editorLabel = new Label(container, SWT.NONE);
		editorLabel.setText("Editor :");
		cbEditors = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		cbEditors.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				EditorTransformationSettings editor = TransformationManager
						.getEditorByName(((Combo) e.getSource()).getText());
				activeEditor = editor;
				if (activeEditor != null) {
					sfMetaModel.setText(editor.getModelURI());
					sfMenu.setText(editor.getMenuName());
					sfMenuLoc.setText(editor.getMenuLocation());
					sfToolbarLoc.setText(editor.getToolbarLocation());
					sfXtendFile.setText(editor.getExtFile());
					bfShowMenu.setSelection(editor.isShownInMenu());
					bfShowPopup.setSelection(editor.isShownInContext());
					bfShowToolbar.setSelection(editor.isShownIToolbar());
					bfShowBalloon.setSelection(editor.isShownInBalloon());
					bfAutoLayout.setSelection(editor.isPerformAutoLayout());
					dfDefaultIcon.setStringValue(editor.getDefaultIconURI()
							.toString());

					table.removeAll();
					for (Transformation t : editor.getTransformations()) {
						TableItem tItem = new TableItem(table, SWT.NONE);
						String showIn = "";
						if (t.getVisiblity() == 15)
							showIn = "All";
						else {
							if (t.isShownInMenu())
								showIn = "Menu";
							if (t.isShownInContext()) {
								if (showIn.length() > 0)
									showIn += "|";
								showIn += "Popup";
							}
							if (t.isShownIToolbar()) {
								if (showIn.length() > 0)
									showIn += "|";
								showIn += "Toolbar";
							}
							if (t.isShownInBalloon()) {
								if (showIn.length() > 0)
									showIn += "|";
								showIn += "Balloon";
							}
						}
						tItem.setText(new String[] { t.getName(),
								t.getTransformationName(), showIn,
								t.getPartConfigList(),
								String.valueOf(t.getNumSelections()),
								t.getIconString() });
					}

					sfMetaModel.setEnabled(true);
					sfMenu.setEnabled(true);
					sfMenuLoc.setEnabled(true);
					sfToolbarLoc.setEnabled(true);
					browserContainer.setEnabled(true);
					tableComp.setEnabled(true);
					btComp.setEnabled(true);
					bfShowMenu.setEnabled(true);
					bfShowToolbar.setEnabled(true);
					bfShowPopup.setEnabled(true);
					bfShowBalloon.setEnabled(true);
					bfAutoLayout.setEnabled(true);
				}
			}

		});
		new Label(container, SWT.NONE);

		GridData gData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		cbEditors.setLayoutData(gData);

		Composite editContainer = new Composite(container, SWT.NONE);
		editContainer.setLayout(new RowLayout());
		Button btAddEditor = new Button(editContainer, SWT.RIGHT);
		btAddEditor.setText("Add");
		btAddEditor.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				String[] res = openElementSelectionDialog(DIAGRAM_EDITORS,
						false);
				if (res != null) {
					EditorTransformationSettings editor = TransformationManager
							.addEditor(res[0]);
					cbEditors.add(editor.getEditor());
					cbEditors.select(cbEditors.indexOf(editor.getEditor()));
					cbEditors.notifyListeners(SWT.Selection, null);
				}
			}

		});
		Button btModifyEditor = new Button(editContainer, SWT.RIGHT);
		btModifyEditor.setText("Edit");
		btModifyEditor.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if (cbEditors.getText().length() > 0) {
					String[] res = openElementSelectionDialog(DIAGRAM_EDITORS,
							false);
					if (res != null) {
						TransformationManager.getEditorByName(
								cbEditors.getText()).setEditor(res[0]);
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				}
			}

		});
		Button btDelEditor = new Button(editContainer, SWT.RIGHT);
		btDelEditor.setText("Delete");
		btDelEditor.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if (cbEditors.getText().length() > 0) {
					TransformationManager.removeEditor(cbEditors.getText());

					cbEditors.remove(cbEditors.getText());
					if (cbEditors.getItemCount() > 0) {
						cbEditors.select(0);
					} else {
						sfMetaModel.setText("");
						sfMetaModel.setEnabled(false);
						sfMenu.setEnabled(false);
						sfMenu.setText("");
						sfMenuLoc.setEnabled(false);
						sfMenuLoc.setText("");
						sfToolbarLoc.setEnabled(false);
						sfToolbarLoc.setText("");
						browserContainer.setEnabled(false);
						tableComp.setEnabled(false);
						btComp.setEnabled(false);
						bfShowMenu.setEnabled(false);
						bfShowToolbar.setEnabled(false);
						bfShowPopup.setEnabled(false);
						bfShowBalloon.setEnabled(false);
						bfAutoLayout.setEnabled(false);
						table.removeAll();
					}
					cbEditors.notifyListeners(SWT.Selection, null);
				}
			}

		});
		new Label(container, SWT.NONE).setText("Editors Model URI");
		sfMetaModel = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfMetaModel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		sfMetaModel.setEnabled(false);

		new Label(container, SWT.NONE).setText("Menu Name");
		sfMenu = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfMenu.setTextLimit(50);
		sfMenu
				.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
						false));
		sfMenu.setEnabled(false);

		new Label(container, SWT.NONE).setText("Menu location");
		sfMenuLoc = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfMenuLoc.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		sfMenuLoc.setEnabled(false);

		new Label(container, SWT.NONE).setText("Toolbar location");
		sfToolbarLoc = new Text(container, SWT.SINGLE | SWT.BORDER);
		sfToolbarLoc.setTextLimit(Text.LIMIT);
		sfToolbarLoc.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));
		sfToolbarLoc.setEnabled(false);

		browserContainer = new Composite(parent, SWT.NONE);
		browserContainer.setLayout(new GridLayout(3, true));

		dfDefaultIcon = new FileFieldEditor("dfDefaultIcon", "Default icon",
				browserContainer);
		dfDefaultIcon.setFileExtensions(new String[] { "*.png", "*.ico" });

		new Label(browserContainer, SWT.NONE).setText("Xtend File");
		sfXtendFile = new Text(browserContainer, SWT.SINGLE | SWT.BORDER
				| SWT.READ_ONLY);

		btBrowseXtend = new Button(browserContainer, SWT.NONE);
		btBrowseXtend.setText("Browse...");
		btBrowseXtend.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(getShell());
				dlg.setFilterPath(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
				dlg.setFileName("extension.ext");
				dlg.setFilterExtensions(new String[] { "*.ext" } );
				dlg.setText("Select Xtend file");
				String result = dlg.open();
				if ( result != null) {
				TransformationManager.getEditorByName(
						cbEditors.getText()).setExtFile(result);

				cbEditors.notifyListeners(SWT.Selection, null);
				}
			}

		});
		sfXtendFile.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true,
				false));

		browserContainer.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING,
				true, false));
		browserContainer.setEnabled(false);

		bfShowMenu = new Button(container, SWT.CHECK);
		bfShowMenu.setText("Show Menu");
		bfShowMenu.setSelection(true);
		bfShowMenu.setEnabled(false);

		bfShowToolbar = new Button(container, SWT.CHECK);
		bfShowToolbar.setText("Show Toolbar");
		bfShowToolbar.setSelection(true);
		bfShowToolbar.setEnabled(false);

		bfShowPopup = new Button(container, SWT.CHECK);
		bfShowPopup.setText("Show Popup");
		bfShowPopup.setSelection(true);
		bfShowPopup.setEnabled(false);

		bfShowBalloon = new Button(container, SWT.CHECK);
		bfShowBalloon.setText("Show Balloon");
		bfShowBalloon.setSelection(true);
		bfShowBalloon.setEnabled(false);

		bfAutoLayout = new Button(container, SWT.CHECK);
		bfAutoLayout.setText("Perform auto-layout after execution");
		bfAutoLayout.setSelection(true);
		bfAutoLayout.setEnabled(false);

		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false,
				false));

		tableComp = new Composite(parent, SWT.NONE);
		tableComp.setLayout(new GridLayout(1, false));
		Label tableLabel = new Label(tableComp, SWT.NONE);
		tableLabel.setText("Configure Transformations");

		table = new Table(tableComp, SWT.BORDER | SWT.SINGLE);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn[] titleCols = new TableColumn[7];
		titleCols[0] = new TableColumn(table, SWT.NONE);
		titleCols[0].setText("Name");
		titleCols[0].setToolTipText("Name of menu entry");
		titleCols[0].setWidth(150);

		titleCols[1] = new TableColumn(table, SWT.NONE);
		titleCols[1].setText("Transformation");
		titleCols[1].setToolTipText("Name of the xtend method");
		titleCols[1].setWidth(150);

		titleCols[2] = new TableColumn(table, SWT.NONE);
		titleCols[2].setText("visibleIn");
		titleCols[2].setToolTipText("Visibility of entry");
		titleCols[2].setWidth(75);

		titleCols[3] = new TableColumn(table, SWT.NONE);
		titleCols[3].setText("SelectedElements");
		titleCols[3]
				.setToolTipText("Diagram element types for which the transformation is defined");
		titleCols[3].setWidth(150);

		titleCols[4] = new TableColumn(table, SWT.NONE);
		titleCols[4].setText("Selections");
		titleCols[4].setToolTipText("Number of selected Elements");
		titleCols[4].setWidth(60);

		titleCols[5] = new TableColumn(table, SWT.NONE);
		titleCols[5].setText("Icon");
		titleCols[5].setWidth(50);

		titleCols[6] = new TableColumn(table, SWT.NONE);
		titleCols[6].setText("Shortcut");
		titleCols[6].setWidth(75);
		titleCols[6].setToolTipText("Keyboard shorcut");

		// Cursor to edit Table cells
		final TableCursor cursor = new TableCursor(table, SWT.NONE);
		final ControlEditor editor = new ControlEditor(cursor);
		editor.grabHorizontal = true;
		editor.grabVertical = true;

		cursor.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				table.setSelection(new TableItem[] { cursor.getRow() });
			}

			public void widgetSelected(SelectionEvent e) {

				TableItem row = cursor.getRow();
				int col = cursor.getColumn();
				Transformation transformation = activeEditor
						.getTransformations().get(table.indexOf(row));

				if (col == 0 || col == 4) { // Text editor
					final Text text = new Text(cursor, SWT.NONE);
					text.addKeyListener(new KeyAdapter() {

						@Override
						public void keyPressed(KeyEvent e) {
							if (e.character == SWT.CR) {
								TableItem innerRow = cursor.getRow();
								int col = cursor.getColumn();
								if (col == 4) { // Number of selections has to
									// be an int-value
									try {
										Integer.parseInt(text.getText());
										innerRow.setText(col, text.getText());
									} catch (NumberFormatException exec) {
										// ignore invalid input
									}
								} else {
									innerRow.setText(col, text.getText());
								}
								text.dispose();
							}

						}
					});
					text.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent e) {
							text.dispose();
						}
					});
					text.setText(row.getText(col));
					editor.setEditor(text);
					text.setFocus();
				} else if (col == 2) { // VisibleIn
					CheckBoxDialog dlg = new CheckBoxDialog(getShell(),
							SWT.NONE);
					row.getText(col);
					Object result = dlg.open(new boolean[] {
							transformation.isShownInMenu(),
							transformation.isShownInContext(),
							transformation.isShownIToolbar(),
							transformation.isShownInBalloon() });
					if (result != null) {
						boolean[] bRes = (boolean[]) result;
						int visible = 0;
						if (bRes[0])
							visible += KSBasEPlugin.SHOW_MENU;
						if (bRes[1])
							visible += KSBasEPlugin.SHOW_CONTEXT;
						if (bRes[2])
							visible += KSBasEPlugin.SHOW_TOOLBAR;
						if (bRes[3])
							visible += KSBasEPlugin.SHOW_BALLOON;

						transformation.setVisibility(visible);
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				} else if (col == 3) { // Editors
					String[] res = openElementSelectionDialog(
							DIAGRAM_EDIT_PARTS, true);
					;
					if (res != null) {
						transformation.setPartConfig(res);
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				} else if (col == 4) { // Icon
					FileDialog dlg = new FileDialog(getShell(), SWT.OPEN);
					dlg.setFilterExtensions(new String[] { "*.png", "*.ico" });
					String fileName = dlg.open();
					if (fileName != null) {
						transformation.setIconURI(URI.create(fileName));
						cbEditors.notifyListeners(SWT.Selection, null);
					}
				} else { // Keyboard shortcut
					// TODO: Keyboard shortcut selection dialog
					// Simple input dialog : textfield, button clear, ok
					// key pressed => map to string rep, add +
				}
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tableComp.setEnabled(false);

		btComp = new Composite(parent, SWT.NONE);
		btComp.setLayout(new GridLayout(2, false));
		btComp.setEnabled(false);

		Button btTableAdd = new Button(btComp, SWT.NONE);
		btTableAdd.setText("Edit Transformations");
		btTableAdd.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				if (sfXtendFile.getText().length() > 0) {
					MessageBox box = new MessageBox(getShell(),SWT.OK | SWT.CANCEL);
					box.setMessage("The preferences will now be closed and the transformation file will be opened in an editor window. Please reopen the preferences after modifing the file");
					box.setText("Warning");
					if ( box.open() == SWT.OK) {
					try {
						IPath path = new Path(sfXtendFile.getText());
						
						IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
								//.getFile(new Path(sfXtendFile.getText()));
						if (!file.exists()) {
							file.create(new ByteArrayInputStream(new byte[0]), IResource.NONE, null);
						}
						IEditorDescriptor desc = PlatformUI.getWorkbench().
				        getEditorRegistry().getDefaultEditor(file.getName());
						IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
						page.openEditor(new FileEditorInput(file), desc.getId());
						performOk();
						
						getShell().setVisible(false); //TODO: Is this the right way of closing the pref page?
					} catch (PartInitException exec) {
						// Put your exception handler here if you wish to
					} catch (CoreException exec) {
					}
					}
				}
			}

		});

		if (TransformationManager.getEditors() != null) {
			for (EditorTransformationSettings s : TransformationManager
					.getEditors()) {
				cbEditors.add(s.getEditor());
			}
			cbEditors.select(0);
			cbEditors.notifyListeners(SWT.Selection, null);
		}
		return null;
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(KSBasEPlugin.getDefault().getPreferenceStore());
	}

	@Override
	public boolean performOk() {
		if (activeEditor != null) {
			activeEditor.setModelURI(sfMetaModel.getText());
			activeEditor.setMenuName(sfMenu.getText());
			activeEditor.setMenuLocation(sfMenuLoc.getText());
			activeEditor.setToolbarLocation(sfToolbarLoc.getText());
			int flags = 0;
			if (bfShowMenu.getSelection())
				flags |= KSBasEPlugin.SHOW_MENU;
			if (bfShowPopup.getSelection())
				flags |= KSBasEPlugin.SHOW_CONTEXT;
			if (bfShowToolbar.getSelection())
				flags |= KSBasEPlugin.SHOW_TOOLBAR;
			if (bfShowBalloon.getSelection())
				flags |= KSBasEPlugin.SHOW_BALLOON;

			activeEditor.setVisibilityFlags(flags);
			activeEditor.setPerformAutoLayout(bfAutoLayout.getSelection());
			activeEditor.setDefaultIconURI(URI.create(dfDefaultIcon
					.getStringValue()));
			TransformationManager.storeTransformations();
		}
		return super.performOk();
	}

	protected String[] openElementSelectionDialog(String[] types,
			boolean multiple) {
		try {
			final LinkedList<Object> diagrams = new LinkedList<Object>();
			for (String type : types) {
				SearchPattern p = SearchPattern.createPattern(type,
						IJavaSearchConstants.TYPE,
						IJavaSearchConstants.IMPLEMENTORS,
						SearchPattern.R_EXACT_MATCH);
				IJavaSearchScope scope = SearchEngine.createWorkspaceScope();

				SearchRequestor req = new SearchRequestor() {

					@Override
					public void acceptSearchMatch(SearchMatch match)
							throws CoreException {
						ResolvedSourceType type = ((ResolvedSourceType) match
								.getElement());
						diagrams.add(type.getFullyQualifiedName());
					}

				};
				SearchEngine engine = new SearchEngine();
				engine.search(p, new SearchParticipant[] { SearchEngine
						.getDefaultSearchParticipant() }, scope, req, null);
			}
			if (diagrams.size() > 0) {
				ElementListSelectionDialog listDlg = new ElementListSelectionDialog(
						getShell(), new LabelProvider() {
							@Override
							public String getText(Object element) {
								if (element instanceof String) {
									String elem = (String) element;
									if (elem.contains("."))
										return elem.substring(elem
												.lastIndexOf('.') + 1, elem
												.length());
									else
										return elem;
								}
								return super.getText(element);
							}
						});
				listDlg.setAllowDuplicates(false);
				listDlg.setEmptySelectionMessage("Please select at least one Element");
				listDlg.setEmptyListMessage("No Element found, please check your workspace settings");
				listDlg.setMultipleSelection(multiple);
				listDlg.setTitle("Select a target element");
				listDlg.setElements(diagrams.toArray());
				if (listDlg.open() == ElementListSelectionDialog.OK) {
					Object[] dlgRes = listDlg.getResult();
					String[] result = new String[dlgRes.length];
					System.arraycopy(dlgRes, 0, result, 0, dlgRes.length);
					return result;
				}
			}

		} catch (CoreException exc) {
			exc.printStackTrace();
		}

		return null;
	}
}
