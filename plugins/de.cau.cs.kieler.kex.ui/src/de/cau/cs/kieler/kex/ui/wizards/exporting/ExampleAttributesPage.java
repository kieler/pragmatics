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
import org.eclipse.swt.events.TypedEvent;
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

    private static final int EX_DESC_HEIGHT = 100;
    private static final int EX_DESC_MINHEIGHT = 80;
    private static final int MIN_WIDTH = 540;
    private static final int MIN_HEIGHT = 600;

    private Text exampleTitle;
    private static final int EXAMPLE_TITLE_MIN = 4;

    private Text author;
    private static final int AUTHOR_MIN = 3;

    private Text exampleDescription;
    private static final int DESCRIPTION_MIN = 10;

    private Text contact;
    private static final int CONTACT_MIN = 5;

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
        setDescription(Messages.getString("attributePageDesc"));
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
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        setControl(composite);
        addAttributeFields(composite);
        getShell().setMinimumSize(MIN_WIDTH, MIN_HEIGHT);
        setPageComplete(false);
    }

    /**
     * creates attribute fields and adds them to given parent composite.
     * 
     * @param parent
     *            , Composite
     */
    private void addAttributeFields(final Composite parent) {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        parent.setLayout(layout);
        Label titleLab = new Label(parent, SWT.NONE);
        titleLab.setText("Title:");
        titleLab.setToolTipText("Think about a meaningful title of the new example.");
        exampleTitle = new Text(parent, SWT.BORDER);
        exampleTitle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        exampleTitle.setText("ExportedExample1");
        exampleTitle.setToolTipText("Think about a meaningful title of the new example.");
        doSingleCheck(exampleTitle, EXAMPLE_TITLE_MIN);
        exampleTitle.addModifyListener(new TextBoxValidator(exampleTitle, Messages.getString(
                "titleToShort", EXAMPLE_TITLE_MIN)) {

            @Override
            public boolean check(final TypedEvent e) {
                return doCheck((Text) e.getSource(), EXAMPLE_TITLE_MIN);
            }
        });

        Label authorLab = new Label(parent, SWT.NONE);
        authorLab.setText("Author:");
        authorLab.setToolTipText("The person or organisation who created that example.");
        author = new Text(parent, SWT.BORDER);
        author.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        String user = System.getProperty("user.name");
        author.setText(System.getProperty("user.name"));
        author.setToolTipText("The person or organisation/group who created that example.");
        doSingleCheck(author, AUTHOR_MIN);
        author.addModifyListener(new TextBoxValidator(author, Messages.getString("titleToShort",
                AUTHOR_MIN)) {
            @Override
            public boolean check(final TypedEvent e) {
                return doCheck((Text) e.getSource(), AUTHOR_MIN);
            }
        });

        Label contactLab = new Label(parent, SWT.NONE);
        contactLab.setText("Contact:");
        contactLab.setToolTipText("Here you usually give an emailaddress or a url "
                + "of a homepage for support and additional informations.");
        contact = new Text(parent, SWT.BORDER);
        contact.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        contact.setText((user != null && user.length() > 1 ? user + "@informatik.uni-kiel.de" : ""));
        contact.setToolTipText("Here you usually give an emailaddress or a "
                + "url of a homepage for support and additional informations.");
        doSingleCheck(contact, CONTACT_MIN);
        contact.addModifyListener(new TextBoxValidator(contact, Messages.getString("titleToShort",
                CONTACT_MIN)) {
            @Override
            public boolean check(final TypedEvent e) {
                return doCheck((Text) e.getSource(), CONTACT_MIN);
            }
        });

        Label descLab = new Label(parent, SWT.NONE);
        descLab.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
        descLab.setText("Description:");
        descLab.setToolTipText("The description gives an overview about the "
                + "created example. This should help users at finding the desired example.");
        exampleDescription = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
                | SWT.H_SCROLL);
        GridData descData = new GridData(SWT.FILL, SWT.FILL, true, true);
        descData.heightHint = EX_DESC_HEIGHT;
        descData.minimumHeight = EX_DESC_MINHEIGHT;
        exampleDescription.setLayoutData(descData);
        exampleDescription.setToolTipText("The description gives an overview "
                + "about the created example. This should help users "
                + "at finding the desired example.");
        doSingleCheck(exampleDescription, DESCRIPTION_MIN);
        exampleDescription.addModifyListener(new TextBoxValidator(exampleDescription, Messages
                .getString("titleToShort", DESCRIPTION_MIN)) {
            @Override
            public boolean check(final TypedEvent e) {
                return doCheck((Text) e.getSource(), DESCRIPTION_MIN);
            }
        });

    }

    private boolean doSingleCheck(final Text field, final int minLength) {
        boolean decorate = field.getText().length() < minLength;
        field.setData(TextBoxValidator.WANTS_COMPLETE, !decorate);
        return decorate;
    }

    private boolean doCheck(final Text field, final int minLength) {
        boolean decorate = doSingleCheck(field, minLength);
        triggerPageComplete();
        return decorate;
    }

    private void triggerPageComplete() {
        setPageComplete((Boolean) exampleTitle.getData(TextBoxValidator.WANTS_COMPLETE)
                && (Boolean) author.getData(TextBoxValidator.WANTS_COMPLETE)
                && (Boolean) contact.getData(TextBoxValidator.WANTS_COMPLETE)
                && (Boolean) exampleDescription.getData(TextBoxValidator.WANTS_COMPLETE));
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
