package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

/**
 * {@link MainWindow} represents the main application window.
 * <p>
 * The user interface of this window consists of two parts:
 * <li>Upper part holds the table of {@link RegistrationUnit} items. Selecting
 * an item from this table revails the lower part of the window.
 * <li>Lower part holds the details of selected {@link RegistrationUnit}. This
 * is where user is able to see all the details of the selected
 * {@link RegistrationUnit}.
 */
public class MainWindow extends Window {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MainWindow.class.getName());

    private MainWindowController controller = new MainWindowController(this);
    private static final String LEVEL_ID_PROPERTY = "levelId";
    private static final String SIGNATURE_PROPERTY = "signature";
    private static final String NAME_PROPERTY = "name";
    private static final String YEAR_FROM_PROPERTY = "yearFrom";
    private static final String YEAR_TO_PROPERTY = "yearTo";
    private static final String HOLDER_ID_PROPERTY = "holderId";
    private RegistrationUnitContainer registrationUnitContainer = new RegistrationUnitContainer();
    private VerticalLayout verticalLayout;
    private VerticalSplitPanel splitPanel;
    private TreeTable registrationUnitTable;
    private Form registrationUnitDetails;

    public MainWindow(String caption) {
        logger.trace("Entering MainWindow()");
        setCaption(caption);

        verticalLayout = new VerticalLayout();
        setContent(verticalLayout);
        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);

        HorizontalLayout buttonToolbar = new HorizontalLayout();
        buttonToolbar.setSpacing(true);

        Upload loadFileButton = new Upload(null, controller);
        loadFileButton.addListener(controller);
        loadFileButton.setImmediate(true);
        loadFileButton.setButtonCaption("Load File");
        buttonToolbar.addComponent(loadFileButton);

        Button saveFileButton = new Button("Save File");
        saveFileButton.addListener((ClickListener) controller);
        buttonToolbar.addComponent(saveFileButton);

        verticalLayout.addComponent(buttonToolbar);

        splitPanel = new VerticalSplitPanel();
        splitPanel.setSizeFull();
        splitPanel.setSplitPosition(100, Sizeable.UNITS_PERCENTAGE);

        registrationUnitTable = new TreeTable();
        registrationUnitTable.setImmediate(true);
        registrationUnitTable.setMultiSelect(true);
        registrationUnitTable.setSizeFull();

        registrationUnitTable.setContainerDataSource(registrationUnitContainer);
        registrationUnitTable.setVisibleColumns(new Object[] { NAME_PROPERTY, LEVEL_ID_PROPERTY, SIGNATURE_PROPERTY, HOLDER_ID_PROPERTY, YEAR_FROM_PROPERTY, YEAR_TO_PROPERTY });

        registrationUnitTable.setColumnHeader(LEVEL_ID_PROPERTY, "Razina Id");
        registrationUnitTable.setColumnHeader(SIGNATURE_PROPERTY, "Signatura");
        registrationUnitTable.setColumnHeader(NAME_PROPERTY, "Naziv");
        registrationUnitTable.setColumnHeader(YEAR_FROM_PROPERTY, "Godina Od");
        registrationUnitTable.setColumnHeader(YEAR_TO_PROPERTY, "Godina Do");
        registrationUnitTable.setColumnHeader(HOLDER_ID_PROPERTY, "Imatelj Id");

        registrationUnitTable.setSelectable(true);
        registrationUnitTable.addListener((ItemClickListener) controller);
        registrationUnitTable.addListener((ValueChangeListener) controller);

        splitPanel.setFirstComponent(registrationUnitTable);

        registrationUnitDetails = new Form();
        registrationUnitDetails.setVisible(false);
        registrationUnitDetails.setWriteThrough(false);
        registrationUnitDetails.setWidth("100%");
        registrationUnitDetails.setCaption("Registraturna Jedinica");
        registrationUnitDetails.setFormFieldFactory(new RegistrationUnitFieldFactory());

        HorizontalLayout formButtonsLayout = new HorizontalLayout();
        formButtonsLayout.setSizeUndefined();
        formButtonsLayout.setSpacing(true);

        Button saveDetailsButton = new Button("Save", registrationUnitDetails, "commit");
        saveDetailsButton.setIcon(new ThemeResource("icons/save.png"));
        formButtonsLayout.addComponent(saveDetailsButton);

        Button cancelDetailsButton = new Button("Cancel", registrationUnitDetails, "discard");
        cancelDetailsButton.setIcon(new ThemeResource("icons/cancel.png"));
        formButtonsLayout.addComponent(cancelDetailsButton);

        Layout formFooter = registrationUnitDetails.getFooter();
        formFooter.setWidth("100%");
        formFooter.addComponent(formButtonsLayout);
        ((HorizontalLayout) formFooter).setComponentAlignment(formButtonsLayout, Alignment.MIDDLE_CENTER);

        splitPanel.setSecondComponent(registrationUnitDetails);

        verticalLayout.addComponent(splitPanel);
        verticalLayout.setExpandRatio(splitPanel, 1);

        logger.debug("Main window initialization finalized.");
        logger.trace("Exiting MainWindow()");
    }

    public Arhinet getTableData() {
        logger.trace("Entering getTableData()");
        logger.trace("Exiting getTableData()");
        return registrationUnitContainer.getData();
    }

    /**
     * This method is called after the unmarshaling process of the XML file is
     * succesfull.
     * <p>
     * It will populate the table container with all {@link RegistrationUnit}
     * objects in {@link Arhinet}.
     * 
     * @param arhinet
     *            - This is the object that the unmarshaler creates out of the
     *            XML file.
     */
    public void setTableData(Arhinet arhinet) {
        logger.trace("Entering setTableData()");
        registrationUnitContainer.setData(arhinet);
        logger.debug("Table data updated with the new contents.");
        logger.trace("Exiting setTableData()");
    }

    /**
     * This method is called when user selects a {@link RegistrationUnit} item
     * from the table.
     * <p>
     * It will bind the properties from {@link RegistrationUnit} object to a
     * form.
     * 
     * @param tableItem
     *            - The selected table item that will be bound to a form.
     */
    public void setFormData(Item tableItem) {
        logger.trace("Entering setFormData()");
        registrationUnitDetails.setItemDataSource(tableItem);
        logger.debug("Form data updated with the new contents.");
        logger.trace("Exiting setFormData()");
    }

    /**
     * Returns the table of {@link RegistrationUnit} items selection state.
     * 
     * @return - If there are selected items in a table, method will return
     *         <code>true</code>. Otherwise it will return <code>false</code>.
     */
    public boolean isTableItemSelected() {
        logger.trace("Entering isTableItemSelected()");
        @SuppressWarnings("unchecked")
        Set<RegistrationUnit> itemList = (Set<RegistrationUnit>) registrationUnitTable.getValue();

        if (itemList.size() == 0) {
            logger.trace("Exiting isTableItemSelected()");

            return false;
        } else {
            logger.trace("Exiting isTableItemSelected()");

            return true;
        }
    }

    /**
     * Setting the visible state for the form which contains selected
     * {@link RegistrationUnit} details.
     * 
     * @param isVisible
     *            - If set to <code>true</code>, form which contains the details
     *            for the selected {@link RegistrationUnit} will be set visible.
     *            Otherwise the form will be hidden.
     */
    public void setFormVisible(boolean isVisible) {
        logger.trace("Entering setFormVisible()");

        if (isVisible) {
            splitPanel.setSplitPosition(40, Sizeable.UNITS_PERCENTAGE);
            registrationUnitDetails.setVisible(true);
        } else {
            splitPanel.setSplitPosition(100, Sizeable.UNITS_PERCENTAGE);
            registrationUnitDetails.setVisible(false);
        }

        logger.trace("Exiting setFormVisible()");
    }
}
