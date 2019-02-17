package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.ArchiveUnit;
import hr.udruga01.arhixml.datamodel.ObjectFactory;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

public class ArchiveUnitCustomField extends CustomField {
    private static final long serialVersionUID = 1L;
    
    private final Logger logger = LoggerFactory.getLogger(ArchiveUnitCustomField.class.getName());
    private Table archiveUnitsTable;
    private static final int NUMBER_OF_VISIBLE_ROWS = 4;
    private static final String MEASUREMENT_UNIT_ID_PROPERTY = "measurementUnitId";
    private static final String AMOUNT_PROPERTY = "amount";
    private ArchiveUnitsCustomFieldController controller = new ArchiveUnitsCustomFieldController(this);
    private BeanItemContainer<ArchiveUnit> archiveUnitContainer = new BeanItemContainer<ArchiveUnit>(ArchiveUnit.class);
    
    public ArchiveUnitCustomField(String caption) {
        logger.trace("Entering ArchiveUnitCustomField()");

        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        archiveUnitsTable = new Table();
        archiveUnitsTable.setSelectable(true);
        archiveUnitsTable.setEditable(true);
        archiveUnitsTable.setTableFieldFactory(new ArchiveUnitsTableFieldFactory());
        archiveUnitsTable.setMultiSelect(true);
        archiveUnitsTable.setSizeFull();
        archiveUnitsTable.setImmediate(true);
        archiveUnitsTable.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        archiveUnitsTable.setContainerDataSource(archiveUnitContainer);
        archiveUnitsTable.setVisibleColumns(new Object[] { MEASUREMENT_UNIT_ID_PROPERTY, AMOUNT_PROPERTY });
        archiveUnitsTable.setColumnHeader(MEASUREMENT_UNIT_ID_PROPERTY, "Mjerna Jedinica");
        archiveUnitsTable.setColumnHeader(AMOUNT_PROPERTY, "Količina");
        archiveUnitsTable.addActionHandler(controller);
        archiveUnitsTable.addListener((ItemClickListener) controller);
        layout.addComponent(archiveUnitsTable);
        layout.setExpandRatio(archiveUnitsTable, 1f);

        setCompositionRoot(layout);

        logger.trace("Exiting ArchiveUnitCustomField()");
    }

    /**
     * This method is automatically called when there is a need to read and set
     * the value to a table.
     * <p>
     * Basically, it is overriden because our {@link Table} is in a multiselect
     * mode. This again means that the table will return the {@link Set} of the
     * selected items. And this is the sole reason that <code>getType()</code>
     * is always returning <code>ArrayList.class</code>.
     * <p>
     * If we do not override this method and return <code>ArrayList.class</code>
     * , framework data binding will not be able to set the value for the
     * <code>archiveUnits</code> property of {@link RegistrationUnit} class. Our
     * <code>archiveUnits</code> property is a {@link List} of {@link ArchiveUnit}
     * objects. The {@link List} interface is not compatible to {@link Set}
     * interface and the framework will throw exception.
     */
    @Override
    public Class<?> getType() {
        logger.trace("Entering getType()");
        logger.trace("Exiting getType()");
        return ArrayList.class;
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
     *            - {@link ArchiveUnit} which needs to be added to selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<ArchiveUnit> selectedItems = ((Set<ArchiveUnit>) archiveUnitsTable.getValue());

        if (selectedItems.contains(item) == false) {
            archiveUnitsTable.setValue(null);
        }

        archiveUnitsTable.select(item);

        logger.trace("Exiting selectTableItem()");
    }
    
    /**
     * Removes selected items in a table from the archive unit container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<ArchiveUnit> selectedItems = ((Set<ArchiveUnit>) archiveUnitsTable.getValue());

        for (ArchiveUnit item : selectedItems) {
            archiveUnitsTable.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }
    
    /**
     * Adds new item of {@link ArchiveUnit} type to the archive unit container.
     */
    public void addNewItem() {
        logger.trace("Entering addNewItem()");
        
        ArchiveUnit archiveUnit = ObjectFactory.createArchiveUnit();
        archiveUnitsTable.addItem(archiveUnit);
        
        archiveUnitsTable.setValue(null);
        archiveUnitsTable.setCurrentPageFirstItemId(archiveUnit);
        archiveUnitsTable.select(archiveUnit);
        
        logger.trace("Exiting addNewItem()");
    }
    
    /**
     * This method is automatically called by the framework when
     * <code>archiveUnits</code> property of the {@link RegistrationUnit} object is
     * bounded to {@link Field}. This happens in
     * {@link RegistrationUnitFieldFactory}.
     * <p>
     * This method is used to populate the container which holds
     * {@link ArchiveUnit} objects.
     */
    @Override
    public void setPropertyDataSource(Property propertyDataSource) {
        logger.trace("Entering setPropertyDataSource()");

        super.setPropertyDataSource(propertyDataSource);

        @SuppressWarnings("unchecked")
        List<ArchiveUnit> archiveUnits = (List<ArchiveUnit>) propertyDataSource.getValue();
        archiveUnitContainer.removeAllItems();
        archiveUnitContainer.addAll(archiveUnits);

        logger.trace("Exiting setPropertyDataSource()");
    }
    
    /**
     * This method is automatically called by the framework when the
     * {@link Form} is commited or when there is a need to read the value from
     * the field (user selected another item from the table).
     * <p>
     * Method will return every {@link ArchiveUnit} from the container.
     */
    @Override
    public Object getValue() {
        logger.trace("Entering getValue()");

        ArrayList<ArchiveUnit> archiveUnits = new ArrayList<ArchiveUnit>();

        for (Object itemId : archiveUnitContainer.getItemIds()) {
            archiveUnits.add(archiveUnitContainer.getItem(itemId).getBean());
        }

        logger.trace("Exiting getValue()");

        return archiveUnits;
    }
    
    /**
     * This method is automatically called by the framework when there is a need
     * to discard the value from a field. For example, user clicked on
     * "Discard changes" button located in the form.
     * <p>
     * This will simply overwrite the {@link ArchiveUnit} container with the
     * original list of archive units available from the time when the form
     * initially bound the <code>archiveUnits</code> property of
     * {@link RegistrationUnit} object.
     */
    @Override
    public void discard() throws SourceException {
        logger.trace("Entering discard()");

        super.discard();

        Property propertyDataSource = getPropertyDataSource();

        if (propertyDataSource != null) {
            @SuppressWarnings("unchecked")
            List<ArchiveUnit> labels = (List<ArchiveUnit>) propertyDataSource.getValue();
            archiveUnitContainer.removeAllItems();
            archiveUnitContainer.addAll(labels);
        }

        logger.trace("Exiting discard()");
    }
}
