/*
 * KIELER - Kiel Integrated Environment for Layout Eclipse RichClient
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
package de.cau.cs.kieler.kex.ui.wizards.exporting;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class contains all elements to opens a {@link WizardPage} with example attributes.
 * 
 * @author pkl
 */
public class ExampleAttributesPage extends WizardPage {

    private Text exampleTitle;

    private Text author;

    private Text contact;

    private Text exampleDescription;

    private static final int EX_DESC_HEIGHT = 100;

    private static final int EX_DESC_MINHEIGHT = 80;

    /**
     * Constructor for {@link ExampleAttributesPage}.
     * 
     * @param pageName
     *            , {@link String}
     * @param selection
     *            , {@link IStructuredSelection}
     */
    public ExampleAttributesPage(final String pageName, final IStructuredSelection selection) {
        super(pageName);
        setTitle(pageName);
        setDescription("Please enter example attributes.");
    }

    /**
     * creates the control composite and adds attributes field for it.
     * 
     * @param parent
     *            , parent composite
     */
    public void createControl(final Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout());
        setControl(composite);
        addAttributeFields(composite);
        getShell().setMinimumSize(500, 600);
    }

    /**
     * creates attribute fields and adds them to given parent composite.
     * 
     * @param parent
     *            , Composite
     */
    private void addAttributeFields(final Composite parent) {
        Composite composite = new Composite(parent, SWT.BORDER);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        new Label(composite, SWT.NONE).setText("Title:");
        exampleTitle = new Text(composite, SWT.BORDER);
        exampleTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        // TODO check before example titles and set number like that.
        exampleTitle.setText("ExportedExample1");
        String user = System.getProperty("user.name");

        new Label(composite, SWT.NONE).setText("Author:");
        author = new Text(composite, SWT.BORDER);
        author.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        author.setText(user);

        new Label(composite, SWT.NONE).setText("Contact:");
        contact = new Text(composite, SWT.BORDER);
        contact.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        contact.setText((user != null && user.length() > 1 ? user + "@informatik.uni-kiel.de" : ""));
        new Label(composite, SWT.NONE).setText("Description:");
        exampleDescription = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
                | SWT.H_SCROLL);
        GridData descData = new GridData(GridData.FILL_HORIZONTAL);
        descData.heightHint = EX_DESC_HEIGHT;
        descData.minimumHeight = EX_DESC_MINHEIGHT;
        exampleDescription.setLayoutData(descData);

    }

    /**
     * setter for example title.
     * 
     * @param exampleTitleParam
     *            , Text
     */
    public void setExampleTitle(final Text exampleTitleParam) {
        this.exampleTitle = exampleTitleParam;
    }

    /**
     * getter for example title.
     * 
     * @return String
     */
    public String getExampleTitle() {
        return exampleTitle.getText();
    }

    /**
     * setter for author.
     * 
     * @param authorParam
     *            , Text
     */
    public void setAuthor(final Text authorParam) {
        this.author = authorParam;
    }

    /**
     * getter for author.
     * 
     * @return String
     */
    public String getAuthor() {
        return this.author.getText();
    }

    /**
     * setter for example description.
     * 
     * @param exampleDescriptionParam
     *            , Text
     */
    public void setExampleDescription(final Text exampleDescriptionParam) {
        this.exampleDescription = exampleDescriptionParam;
    }

    /**
     * getter for example description.
     * 
     * @return String
     */
    public String getExampleDescription() {
        return exampleDescription.getText();
    }

    /**
     * setter for contact.
     * 
     * @param contactParam
     *            , Text
     */
    public void setContact(final Text contactParam) {
        this.contact = contactParam;
    }

    /**
     * getter for contact.
     * 
     * @return String
     */
    public String getContact() {
        return contact.getText();
    }
}
