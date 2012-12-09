package hr.udruga01.arhixml.modules.mainwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import hr.udruga01.arhixml.datamodel.Medium;
import hr.udruga01.arhixml.datamodel.ObjectFactory;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

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

public class MediumCustomField extends CustomField {
    private static final long serialVersionUID = 1L;
    private static final int NUMBER_OF_VISIBLE_ROWS = 5;
    private static final String MEDIUM_TYPE_ID_PROPERTY = "mediumTypeId";
    
    private final Logger logger = LoggerFactory.getLogger(MediumCustomField.class.getName());
    private MediumCustomFieldController controller = new MediumCustomFieldController(this);
    private BeanItemContainer<Medium> mediumContainer = new BeanItemContainer<Medium>(Medium.class);
    private Table mediumsList;

    public MediumCustomField(String caption) {
        logger.trace("Entering MediumCustomField()");
        
        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("40%");
        layout.setSpacing(true);
        
        mediumsList = new Table();
        mediumsList.setSelectable(true);
        mediumsList.setEditable(true);
        mediumsList.setTableFieldFactory(new MediumsTableFieldFactory());
        mediumsList.setMultiSelect(true);
        mediumsList.setWidth("100%");
        mediumsList.setImmediate(true);
        mediumsList.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        mediumsList.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        mediumsList.setContainerDataSource(mediumContainer);
        mediumsList.setVisibleColumns(new Object[] {MEDIUM_TYPE_ID_PROPERTY});
        mediumsList.addActionHandler(controller);
        mediumsList.addListener((ItemClickListener) controller);
        layout.addComponent(mediumsList);
        
        setCompositionRoot(layout);
        
        logger.trace("Exiting MediumCustomField()");
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
     * If we do not override this method and return <code>ArrayList.class</code>,
     * framework data binding will not be able to set the value for the
     * <code>mediums</code> property of {@link RegistrationUnit} class. Our
     * <code>mediums</code> property is a {@link List} of {@link Medium}
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
     *            - {@link Medium} which needs to be added to selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<Medium> selectedItems = ((Set<Medium>) mediumsList.getValue());

        if (selectedItems.contains(item) == false) {
            mediumsList.setValue(null);
        }

        mediumsList.select(item);

        logger.trace("Exiting selectTableItem()");
    }
    
    /**
     * Removes selected items in a table from the medium container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<Medium> selectedItems = ((Set<Medium>) mediumsList.getValue());

        for (Medium item : selectedItems) {
            mediumsList.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }
    
    /**
     * Adds new item of {@link Medium} type to the medium container.
     */
    public void addNewItem() {
        Medium medium = ObjectFactory.createMedium();
        mediumsList.addItem(medium);
        
        mediumsList.setValue(null);
        mediumsList.setCurrentPageFirstItemId(medium);
        mediumsList.select(medium);
    }
    
    /**
     * This method is automatically called by the framework when
     * <code>mediums</code> property of the {@link RegistrationUnit} object is
     * bounded to {@link Field}. This happens in
     * {@link RegistrationUnitFieldFactory}.
     * <p>
     * This method is used to populate the container which holds
     * {@link Medium} objects.
     */
    @Override
    public void setPropertyDataSource(Property propertyDataSource) {
        logger.trace("Entering setPropertyDataSource()");

        super.setPropertyDataSource(propertyDataSource);

        @SuppressWarnings("unchecked")
        List<Medium> materials = (List<Medium>) propertyDataSource.getValue();
        mediumContainer.removeAllItems();
        mediumContainer.addAll(materials);

        logger.trace("Exiting setPropertyDataSource()");
    }
    
    /**
     * This method is automatically called by the framework when the
     * {@link Form} is commited or when there is a need to read the value from
     * the field (user selected another item from the table).
     * <p>
     * Method will return every {@link Medium} from the container.
     */
    @Override
    public Object getValue() {
        logger.trace("Entering getValue()");

        ArrayList<Medium> mediums = new ArrayList<Medium>();

        for (Object itemId : mediumContainer.getItemIds()) {
            mediums.add(mediumContainer.getItem(itemId).getBean());
        }

        logger.trace("Exiting getValue()");

        return mediums;
    }
    
    /**
     * This method is automatically called by the framework when there is a need
     * to discard the value from a field. For example, user clicked on
     * "Discard changes" button located in the form.
     * <p>
     * This will simply overwrite the {@link Medium} container with the
     * original list of mediums available from the time when the form
     * initially bound the <code>mediums</code> property of
     * {@link RegistrationUnit} object.
     */
    @Override
    public void discard() throws SourceException {
        logger.trace("Entering discard()");

        super.discard();

        Property propertyDataSource = getPropertyDataSource();

        if (propertyDataSource != null) {
            @SuppressWarnings("unchecked")
            List<Medium> materials = (List<Medium>) propertyDataSource.getValue();
            mediumContainer.removeAllItems();
            mediumContainer.addAll(materials);
        }

        logger.trace("Exiting discard()");
    }
}
