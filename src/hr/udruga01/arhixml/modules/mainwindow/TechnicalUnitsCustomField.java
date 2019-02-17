package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.ObjectFactory;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;
import hr.udruga01.arhixml.datamodel.TechnicalUnit;

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

public class TechnicalUnitsCustomField extends CustomField {
    private static final long serialVersionUID = 1L;
    
    private final Logger logger = LoggerFactory.getLogger(TechnicalUnitsCustomField.class.getName());
    private Table technicalUnitsTable;
    private static final int NUMBER_OF_VISIBLE_ROWS = 4;
    private static final String TECHNICAL_UNIT_TYPE_ID_PROPERTY = "technicalUnitTypeId";
    private static final String AMOUNT_PROPERTY = "amount";
    private static final String CHARACTERISTICS_PROPERTY = "characteristics";
    private TechnicalUnitsCustomFieldController controller = new TechnicalUnitsCustomFieldController(this);
    private BeanItemContainer<TechnicalUnit> technicalUnitContainer = new BeanItemContainer<TechnicalUnit>(TechnicalUnit.class);

    public TechnicalUnitsCustomField(String caption) {
        logger.trace("Entering TechnicalUnitsCustomField()");

        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        technicalUnitsTable = new Table();
        technicalUnitsTable.setSelectable(true);
        technicalUnitsTable.setEditable(true);
        technicalUnitsTable.setTableFieldFactory(new TechnicalUnitsTableFieldFactory());
        technicalUnitsTable.setMultiSelect(true);
        technicalUnitsTable.setSizeFull();
        technicalUnitsTable.setImmediate(true);
        technicalUnitsTable.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        technicalUnitsTable.setContainerDataSource(technicalUnitContainer);
        technicalUnitsTable.setVisibleColumns(new Object[] { TECHNICAL_UNIT_TYPE_ID_PROPERTY, AMOUNT_PROPERTY, CHARACTERISTICS_PROPERTY });
        technicalUnitsTable.setColumnHeader(TECHNICAL_UNIT_TYPE_ID_PROPERTY, "Vrsta Tehničke Jedinice");
        technicalUnitsTable.setColumnHeader(AMOUNT_PROPERTY, "Količina");
        technicalUnitsTable.setColumnHeader(CHARACTERISTICS_PROPERTY, "Tvarne Značajke");
        technicalUnitsTable.addActionHandler(controller);
        technicalUnitsTable.addListener((ItemClickListener) controller);
        layout.addComponent(technicalUnitsTable);
        layout.setExpandRatio(technicalUnitsTable, 1f);

        setCompositionRoot(layout);

        logger.trace("Exiting TechnicalUnitsCustomField()");
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
     * <code>technicalUnits</code> property of {@link RegistrationUnit} class. Our
     * <code>technicalUnits</code> property is a {@link List} of {@link TechnicalUnit}
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
     *            - {@link TechnicalUnit} which needs to be added to selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<TechnicalUnit> selectedItems = ((Set<TechnicalUnit>) technicalUnitsTable.getValue());

        if (selectedItems.contains(item) == false) {
            technicalUnitsTable.setValue(null);
        }

        technicalUnitsTable.select(item);

        logger.trace("Exiting selectTableItem()");
    }
    
    /**
     * Removes selected items in a table from the technical unit container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<TechnicalUnit> selectedItems = ((Set<TechnicalUnit>) technicalUnitsTable.getValue());

        for (TechnicalUnit item : selectedItems) {
            technicalUnitsTable.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }
    
    /**
     * Adds new item of {@link TechnicalUnit} type to the technical unit container.
     */
    public void addNewItem() {
        logger.trace("Entering addNewItem()");
        
        TechnicalUnit technicalUnit = ObjectFactory.createTechnicalUnit();
        technicalUnitsTable.addItem(technicalUnit);
        
        technicalUnitsTable.setValue(null);
        technicalUnitsTable.setCurrentPageFirstItemId(technicalUnit);
        technicalUnitsTable.select(technicalUnit);
        
        logger.trace("Exiting addNewItem()");
    }
    
    /**
     * This method is automatically called by the framework when
     * <code>technicalUnits</code> property of the {@link RegistrationUnit} object is
     * bounded to {@link Field}. This happens in
     * {@link RegistrationUnitFieldFactory}.
     * <p>
     * This method is used to populate the container which holds
     * {@link TechnicalUnit} objects.
     */
    @Override
    public void setPropertyDataSource(Property propertyDataSource) {
        logger.trace("Entering setPropertyDataSource()");

        super.setPropertyDataSource(propertyDataSource);

        @SuppressWarnings("unchecked")
        List<TechnicalUnit> technicalUnits = (List<TechnicalUnit>) propertyDataSource.getValue();
        technicalUnitContainer.removeAllItems();
        technicalUnitContainer.addAll(technicalUnits);

        logger.trace("Exiting setPropertyDataSource()");
    }
    
    /**
     * This method is automatically called by the framework when the
     * {@link Form} is commited or when there is a need to read the value from
     * the field (user selected another item from the table).
     * <p>
     * Method will return every {@link TechnicalUnit} from the container.
     */
    @Override
    public Object getValue() {
        logger.trace("Entering getValue()");

        ArrayList<TechnicalUnit> technicalUnits = new ArrayList<TechnicalUnit>();

        for (Object itemId : technicalUnitContainer.getItemIds()) {
            technicalUnits.add(technicalUnitContainer.getItem(itemId).getBean());
        }

        logger.trace("Exiting getValue()");

        return technicalUnits;
    }
    
    /**
     * This method is automatically called by the framework when there is a need
     * to discard the value from a field. For example, user clicked on
     * "Discard changes" button located in the form.
     * <p>
     * This will simply overwrite the {@link TechnicalUnit} container with the
     * original list of technical units available from the time when the form
     * initially bound the <code>technicalUnits</code> property of
     * {@link RegistrationUnit} object.
     */
    @Override
    public void discard() throws SourceException {
        logger.trace("Entering discard()");

        super.discard();

        Property propertyDataSource = getPropertyDataSource();

        if (propertyDataSource != null) {
            @SuppressWarnings("unchecked")
            List<TechnicalUnit> technicalUnits = (List<TechnicalUnit>) propertyDataSource.getValue();
            technicalUnitContainer.removeAllItems();
            technicalUnitContainer.addAll(technicalUnits);
        }

        logger.trace("Exiting discard()");
    }
}
