package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Maker;
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

public class MakersCustomField extends CustomField {
    private static final long serialVersionUID = 1L;
    
    private final Logger logger = LoggerFactory.getLogger(MakersCustomField.class.getName());
    private Table makersTable;
    private static final int NUMBER_OF_VISIBLE_ROWS = 4;
    private static final String MAKER_NOTE_PROPERTY = "makerNote";
    private static final String MAKER_ID_PROPERTY = "makerId";
    private static final String ROLE_ID_PROPERTY = "roleId";
    private static final String PERIOD_PROPERTY = "period";
    private MakersCustomFieldController controller = new MakersCustomFieldController(this);
    private BeanItemContainer<Maker> makerContainer = new BeanItemContainer<Maker>(Maker.class);
    
    public MakersCustomField(String caption) {
        logger.trace("Entering MakersCustomField()");

        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        makersTable = new Table();
        makersTable.setSelectable(true);
        makersTable.setEditable(true);
        makersTable.setTableFieldFactory(new MakersTableFieldFactory());
        makersTable.setMultiSelect(true);
        makersTable.setSizeFull();
        makersTable.setImmediate(true);
        makersTable.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        makersTable.setContainerDataSource(makerContainer);
        makersTable.setVisibleColumns(new Object[] { MAKER_ID_PROPERTY, ROLE_ID_PROPERTY, PERIOD_PROPERTY, MAKER_NOTE_PROPERTY });
        makersTable.setColumnHeader(MAKER_ID_PROPERTY, "Šifra Stvaratelja");
        makersTable.setColumnHeader(ROLE_ID_PROPERTY, "Uloge");
        makersTable.setColumnHeader(PERIOD_PROPERTY, "Razdoblje");
        makersTable.setColumnHeader(MAKER_NOTE_PROPERTY, "Napomena o Stvaratelju");
        makersTable.addActionHandler(controller);
        makersTable.addListener((ItemClickListener) controller);
        layout.addComponent(makersTable);
        layout.setExpandRatio(makersTable, 1f);

        setCompositionRoot(layout);

        logger.trace("Exiting MakersCustomField()");
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
     * <code>makers</code> property of {@link RegistrationUnit} class. Our
     * <code>makers</code> property is a {@link List} of {@link Maker}
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
     *            - {@link Maker} which needs to be added to selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<Maker> selectedItems = ((Set<Maker>) makersTable.getValue());

        if (selectedItems.contains(item) == false) {
            makersTable.setValue(null);
        }

        makersTable.select(item);

        logger.trace("Exiting selectTableItem()");
    }
    
    /**
     * Removes selected items in a table from the maker container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<Maker> selectedItems = ((Set<Maker>) makersTable.getValue());

        for (Maker item : selectedItems) {
            makersTable.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }
    
    /**
     * Adds new item of {@link Maker} type to the maker container.
     */
    public void addNewItem() {
        logger.trace("Entering addNewItem()");
        
        Maker maker = ObjectFactory.createMaker();
        makersTable.addItem(maker);
        
        makersTable.setValue(null);
        makersTable.setCurrentPageFirstItemId(maker);
        makersTable.select(maker);
        
        logger.trace("Exiting addNewItem()");
    }
    
    /**
     * This method is automatically called by the framework when
     * <code>makers</code> property of the {@link RegistrationUnit} object is
     * bounded to {@link Field}. This happens in
     * {@link RegistrationUnitFieldFactory}.
     * <p>
     * This method is used to populate the container which holds
     * {@link Maker} objects.
     */
    @Override
    public void setPropertyDataSource(Property propertyDataSource) {
        logger.trace("Entering setPropertyDataSource()");

        super.setPropertyDataSource(propertyDataSource);

        @SuppressWarnings("unchecked")
        List<Maker> makers = (List<Maker>) propertyDataSource.getValue();
        makerContainer.removeAllItems();
        makerContainer.addAll(makers);

        logger.trace("Exiting setPropertyDataSource()");
    }
    
    /**
     * This method is automatically called by the framework when the
     * {@link Form} is commited or when there is a need to read the value from
     * the field (user selected another item from the table).
     * <p>
     * Method will return every {@link Maker} from the container.
     */
    @Override
    public Object getValue() {
        logger.trace("Entering getValue()");

        ArrayList<Maker> makers = new ArrayList<Maker>();

        for (Object itemId : makerContainer.getItemIds()) {
            makers.add(makerContainer.getItem(itemId).getBean());
        }

        logger.trace("Exiting getValue()");

        return makers;
    }
    
    /**
     * This method is automatically called by the framework when there is a need
     * to discard the value from a field. For example, user clicked on
     * "Discard changes" button located in the form.
     * <p>
     * This will simply overwrite the {@link Maker} container with the
     * original list of makers available from the time when the form
     * initially bound the <code>makers</code> property of
     * {@link RegistrationUnit} object.
     */
    @Override
    public void discard() throws SourceException {
        logger.trace("Entering discard()");

        super.discard();

        Property propertyDataSource = getPropertyDataSource();

        if (propertyDataSource != null) {
            @SuppressWarnings("unchecked")
            List<Maker> makers = (List<Maker>) propertyDataSource.getValue();
            makerContainer.removeAllItems();
            makerContainer.addAll(makers);
        }

        logger.trace("Exiting discard()");
    }
}
