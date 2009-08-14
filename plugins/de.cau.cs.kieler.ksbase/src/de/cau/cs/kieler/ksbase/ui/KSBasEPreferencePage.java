package de.cau.cs.kieler.ksbase.ui;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.mwe.internal.core.debug.mwe.ReflectionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.core.util.Util;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.ITypeNameRequestor;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipse.jdt.internal.compiler.impl.ITypeRequestor;
import org.eclipse.jdt.internal.core.BinaryType;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.corext.refactoring.typeconstraints.ITypeConstraint;
import org.eclipse.jdt.internal.corext.util.SearchUtils;
import org.eclipse.jdt.internal.ui.callhierarchy.SearchUtil;
import org.eclipse.jdt.internal.ui.dialogs.OpenTypeSelectionDialog;
import org.eclipse.jdt.ui.IJavaElementSearchConstants;
import org.eclipse.jdt.ui.ITypeHierarchyViewPart;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.ui.actions.FindImplementorsAction;
import org.eclipse.jdt.ui.dialogs.ITypeInfoImageProvider;
import org.eclipse.jdt.ui.dialogs.ITypeInfoRequestor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.SelectionDialog;
import org.eclipse.ui.dialogs.TypeFilteringDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.Bundle;

import de.cau.cs.kieler.ksbase.KSBasEPlugin;
import de.cau.cs.kieler.ksbase.transformations.TransformationManager;

public class KSBasEPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	/**
	 * FileEditor which opens a JavaUI type selection dialog to select a class
	 * TODO: Maybe add filter for diagrams?
	 * @author mim
	 */
	private class ClassSelectionFieldEditor extends StringButtonFieldEditor {

		private Shell shell;
		private IType type;
		
		public ClassSelectionFieldEditor(String name, String label, Composite parent, Shell shell) {
			super(name,label,parent);
			this.shell = shell;
			
		}
		
		@SuppressWarnings("restriction")
		@Override
		protected String changePressed() {
			try {
			    SelectionDialog dlg;
			    if ( type != null) {
			        
				dlg = JavaUI.createTypeDialog(shell, new ProgressMonitorDialog(shell), SearchEngine.createWorkspaceScope() , IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES, false,"");
			    }
			    else {
			        //IType type = BinaryType.TYPE;
			        dlg = JavaUI.createTypeDialog(shell, new ProgressMonitorDialog(shell), SearchEngine.createWorkspaceScope(), IJavaElementSearchConstants.CONSIDER_CLASSES_AND_INTERFACES, false);
			    }
				if ( dlg.open() == SelectionDialog.OK ) {
					String res = "";
					//TODO: Store classes
					for ( Object result : dlg.getResult()) {
					   // IType type = (IType)result;
//class DiagramDocumentEditor [in DiagramDocumentEditor.class [in org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts [in /home/java/eclipse_3.5/plugins/org.eclipse.gmf.runtime.diagram.ui.resources.editor_1.2.0.v20090526-1925.jar]]]					    
						res += ((SourceType)result).getFullyQualifiedName() + ",";
					}		
											//trunc the last ,
					this.setStringValue(res.substring(0, res.length()-1));
				}
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
	public KSBasEPreferencePage() {
		setDescription("Preferences for the KIELER Structure Based Editing Features.");
	}
	
	@Override
	protected Control createContents(Composite parent) {
		// create composite and set layout
		
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2,false);
		
		StringFieldEditor sfMetaModel = new StringFieldEditor("MetaModel", "Editors Model URI", container);
		sfMetaModel.setEmptyStringAllowed(false);
		
		StringFieldEditor sfMenu = new StringFieldEditor("MenuName", "Menu name", container);
		sfMenu.setEmptyStringAllowed(false);
		sfMenu.setTextLimit(50);
		
		StringFieldEditor sfMenuLoc = new StringFieldEditor("MenuLocation", "Menu/Toolbar location", container);
		sfMenuLoc.setEmptyStringAllowed(false);
		sfMenuLoc.setTextLimit(StringFieldEditor.UNLIMITED);
		
		BooleanFieldEditor bfShowMenu = new BooleanFieldEditor("ShowMenu", "Show menu", container);
		bfShowMenu.setEnabled(true, container);
		
		BooleanFieldEditor bfShowToolbar = new BooleanFieldEditor("ShowToolbar", "Show toolbar", container);
		bfShowToolbar.setEnabled(true, container);

		
		BooleanFieldEditor bfShowPopup = new BooleanFieldEditor("ShowPopup", "Show popup menu", container);
		bfShowPopup.setEnabled(true, container);
		
		BooleanFieldEditor bfShowBalloon = new BooleanFieldEditor("ShowBalloon", "Show balloon menu", container);
		bfShowBalloon.setEnabled(true, container);
		
		Composite browserContainer = new Composite(parent, SWT.NONE);
		GridLayout biggerlayout = new GridLayout(3,false);
		
		FileFieldEditor dfDefaultIcon = new FileFieldEditor("dfDefaultIcon", "Default icon", browserContainer);
		dfDefaultIcon.setFileExtensions(new String[] { "*.png","*.ico"} );
		
		ClassSelectionFieldEditor csClass = new ClassSelectionFieldEditor("csClass", "Editor restrictions", browserContainer, parent.getShell());
		
		csClass.setChangeButtonText("Edit");
		browserContainer.setLayout(biggerlayout);
		browserContainer.setLayoutData(new GridData(SWT.FILL,SWT.BEGINNING,false,false));
		
		container.setLayout(layout);
		container.setLayoutData(new GridData(SWT.FILL,SWT.BEGINNING,false,false));
		
		return null;
	}

	public void init(IWorkbench workbench) {
	    setPreferenceStore(KSBasEPlugin.getDefault().getPreferenceStore());
	}
	
	@Override
	public boolean performOk() {
	    IPreferenceStore store = getPreferenceStore();
	    
	    return super.performOk();
	}

}
/*
SearchEngine engine = new SearchEngine();
char[] typeName = "org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor".toCharArray();
TypeNameRequestor requestor = new TypeNameRequestor() {
    
};

engine.searchAllTypeNames(new char[] {'*'},SearchPattern.R_PATTERN_MATCH, typeName, SearchPattern.R_PATTERN_MATCH, IJavaSearchConstants.CLASS, SearchEngine.createWorkspaceScope(), requestor, IJavaSearchConstants.FORCE_IMMEDIATE_SEARCH, new NullProgressMonitor());
*/