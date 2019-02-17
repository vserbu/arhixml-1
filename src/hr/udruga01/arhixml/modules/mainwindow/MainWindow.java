package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.ObjectFactory;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractSplitPanel.SplitterClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Table.TableDragMode;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededListener;
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
    private float spliterPosition;

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
        loadFileButton.addListener((SucceededListener) controller);
        loadFileButton.setImmediate(true);
        loadFileButton.setButtonCaption("Učitaj datoteku");
        buttonToolbar.addComponent(loadFileButton);

        Button saveFileButton = new Button("Skini datoteku");
        saveFileButton.setData("saveFileButton");
        saveFileButton.addListener((ClickListener) controller);
        buttonToolbar.addComponent(saveFileButton);

        // Create clear table button and add it to button toolbar.
        Button clearTableButton = new Button("Isprazni tablicu");
        clearTableButton.setData("clearTableButton");
        clearTableButton.addListener((ClickListener) controller);
        buttonToolbar.addComponent(clearTableButton);

        verticalLayout.addComponent(buttonToolbar);

        splitPanel = new VerticalSplitPanel();
        splitPanel.addListener((SplitterClickListener) controller);
        splitPanel.setSizeFull();
        spliterPosition = 55;
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

        registrationUnitTable.setColumnExpandRatio(LEVEL_ID_PROPERTY, 1);
        registrationUnitTable.setColumnExpandRatio(SIGNATURE_PROPERTY, 1);
        registrationUnitTable.setColumnExpandRatio(NAME_PROPERTY, 3);
        registrationUnitTable.setColumnExpandRatio(YEAR_FROM_PROPERTY, 1);
        registrationUnitTable.setColumnExpandRatio(YEAR_TO_PROPERTY, 1);
        registrationUnitTable.setColumnExpandRatio(HOLDER_ID_PROPERTY, 1);

        registrationUnitTable.setSelectable(true);
        registrationUnitTable.setDragMode(TableDragMode.MULTIROW);
        registrationUnitTable.setDropHandler(new RegistrationUnitTableDropHandler());
        registrationUnitTable.addListener((ItemClickListener) controller);
        registrationUnitTable.addListener((ValueChangeListener) controller);
        registrationUnitTable.addActionHandler((Handler) controller);

        splitPanel.setFirstComponent(registrationUnitTable);

        registrationUnitDetails = new RegistrationUnitForm();
        registrationUnitDetails.setVisible(false);
        registrationUnitDetails.setWriteThrough(false);
        registrationUnitDetails.setWidth("100%");
        registrationUnitDetails.setCaption("Registraturna Jedinica");
        registrationUnitDetails.setFormFieldFactory(new RegistrationUnitFieldFactory());

        HorizontalLayout formButtonsLayout = new HorizontalLayout();
        formButtonsLayout.setSizeUndefined();
        formButtonsLayout.setSpacing(true);

        Button updateDetailsButton = new Button("Ažuriraj");
        updateDetailsButton.setData("updateDetailsButton");
        updateDetailsButton.addListener((ClickListener) controller);
        updateDetailsButton.setIcon(new ThemeResource("icons/save.png"));
        formButtonsLayout.addComponent(updateDetailsButton);

        Button discardDetailsButton = new Button("Poni�ti", registrationUnitDetails, "discard");
        discardDetailsButton.setIcon(new ThemeResource("icons/cancel.png"));
        formButtonsLayout.addComponent(discardDetailsButton);

        Layout formFooter = registrationUnitDetails.getFooter();
        formFooter.setWidth("100%");
        formFooter.addComponent(formButtonsLayout);
        ((HorizontalLayout) formFooter).setComponentAlignment(formButtonsLayout, Alignment.BOTTOM_CENTER);

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
        logger.debug("Expanding contents of a table.");
        expandAllNodes(arhinet.getRegistrationUnits());
        logger.debug("Content is fully expanded.");

        logger.trace("Exiting setTableData()");
    }

    /**
     * After contents of {@link Arhinet} is loaded into table, each
     * {@link RegistrationUnit} node inside {@link Arhinet} object should be
     * fully expanded.
     * 
     * This results in more pleasent and convenient experience for the user.
     * 
     * @param registrationUnits
     *            - Reference to a {@link RegistrationUnit} object.
     */
    private void expandAllNodes(List<RegistrationUnit> registrationUnits) {
        for (RegistrationUnit registrationUnit : registrationUnits) {
            registrationUnitTable.setCollapsed(registrationUnit, false);
            expandAllNodes(registrationUnit.getRegistrationUnits());
        }
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
     * @return - If there is only one selected item in a table, method will
     *         return <code>true</code>. For all other selection (multiple
     *         selection or no selection at all) it will return
     *         <code>false</code>.
     */
    public boolean isSingleSelection() {
        logger.trace("Entering isTableItemSelected()");

        // Get the list of selected items.
        @SuppressWarnings("unchecked")
        Set<RegistrationUnit> selectedItems = (Set<RegistrationUnit>) registrationUnitTable.getValue();

        if (selectedItems.size() == 1) {
            // If there is only one selected item than return true.
            logger.trace("Exiting isTableItemSelected()");

            return true;
        } else {
            // If there is a multiple selection or no selection at all, return
            // false.
            logger.trace("Exiting isTableItemSelected()");

            return false;
        }
    }

    /**
     * Method handles the selection of items.
     * <p>
     * This is modeled against the usual way that various operating systems
     * behave. For example, if user makes a multiple selection and triggers
     * context menu on one of the selected item, this needs to show a context
     * menu but without deselection of items.
     * <p>
     * But if the user has selected more than one item and triggers the context
     * menu on item which is not in the selection list, application will show
     * the context menu but it will also deselect every item and instead select
     * the one that user tried to trigger context menu on.
     * 
     * @param item
     *            - {@link RegistrationUnit} which needs to be added to
     *            selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<RegistrationUnit> selectedItems = ((Set<RegistrationUnit>) registrationUnitTable.getValue());

        if (selectedItems.contains(item) == false) {
            registrationUnitTable.setValue(null);
        }

        registrationUnitTable.select(item);

        logger.trace("Exiting selectTableItem()");
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
        
        // Reset the form data. This will update the state of form controls.
        registrationUnitDetails.setItemDataSource(registrationUnitDetails.getItemDataSource());

        if (isVisible) {
            splitPanel.setSplitPosition(spliterPosition, Sizeable.UNITS_PERCENTAGE);
            registrationUnitDetails.setVisible(true);
        } else {
            splitPanel.setSplitPosition(100, Sizeable.UNITS_PERCENTAGE);
            registrationUnitDetails.setVisible(false);
        }

        logger.trace("Exiting setFormVisible()");
    }

    /**
     * Remembers current splitter position on screen.
     * <p>
     * This is for the user convenience. When user hides the form and shows it
     * again, the application will restore the splitter position like user left
     * it.
     */
    public void rememberSplitterPosition() {
        logger.trace("Entering rememberSplitterPosition()");

        float position = splitPanel.getSplitPosition();

        if (position < splitPanel.getMaxSplitPosition()) {
            spliterPosition = splitPanel.getSplitPosition();
        }

        logger.trace("Exiting rememberSplitterPosition()");
    }

    /**
     * Removes selected items in a table from the registration unit container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<RegistrationUnit> selectedItems = ((Set<RegistrationUnit>) registrationUnitTable.getValue());

        for (RegistrationUnit item : selectedItems) {
            registrationUnitTable.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }

    /**
     * Adds new item of {@link RegistrationUnit} type to the registration unit
     * container.
     */
    public void addNewItem(Object target) {
        logger.trace("Entering addNewItem()");
        
        RegistrationUnit registrationUnit = ObjectFactory.createRegistrationUnit();

        registrationUnitContainer.setParent(registrationUnit, target);
        Item item = registrationUnitContainer.addBean(registrationUnit);

        registrationUnitTable.setCollapsed(target, false);
        registrationUnitTable.setValue(null);
        registrationUnitTable.setCurrentPageFirstItemId(registrationUnit);
        registrationUnitTable.select(registrationUnit);

        setFormData(item);
        
        logger.trace("Exiting addNewItem()");
    }

    public void commitForm() {
        logger.trace("Entering commitForm()");
        
        registrationUnitDetails.commit();
        
        logger.trace("Exiting commitForm()");
    }

    /**
     * Clears the table which holds the {@link RegistrationUnit} items.
     */
    public void emptyTable() {
        logger.trace("Entering emptyTable()");
        
        registrationUnitTable.removeAllItems();
        
        logger.trace("Exiting emptyTable()");
    }
}
