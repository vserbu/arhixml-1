package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Material;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

/**
 * This class represents the {@link CustomField} for the <code>materials</code>
 * property of {@link RegistrationUnit} class. Component consist of
 * {@link Table} and {@link Button} for creating new and deleting selected
 * {@link Material} items from a table.
 */
class MaterialsCustomField extends CustomField {
    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(MaterialsCustomField.class.getName());
    private MaterialsCustomFieldController controller = new MaterialsCustomFieldController(this);
    private BeanItemContainer<Material> materialContainer = new BeanItemContainer<Material>(Material.class);

    private static final int NUMBER_OF_VISIBLE_ROWS = 4;
    private static final String SUBTYPEID_PROPERTY = "subtypeId";

    private Table materialsList;

    public MaterialsCustomField(String caption) {
        logger.trace("Entering MaterialsCustomField()");

        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        materialsList = new Table();
        materialsList.setSelectable(true);
        materialsList.setEditable(true);
        materialsList.setTableFieldFactory(new MaterialsTableFieldFactory());
        materialsList.setMultiSelect(true);
        materialsList.setSizeFull();
        materialsList.setImmediate(true);
        materialsList.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        materialsList.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        materialsList.setContainerDataSource(materialContainer);
        materialsList.setVisibleColumns(new Object[] {SUBTYPEID_PROPERTY});
        materialsList.addActionHandler(controller);
        materialsList.addListener((ItemClickListener) controller);
        layout.addComponent(materialsList);
        layout.setExpandRatio(materialsList, 1f);

        setCompositionRoot(layout);

        logger.trace("Exiting MaterialsCustomField()");
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
     * <code>materials</code> property of {@link RegistrationUnit} class. Our
     * <code>materials</code> property is a {@link List} of {@link Material}
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
     *            - {@link Material} which needs to be added to selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<Material> selectedItems = ((Set<Material>) materialsList.getValue());

        if (selectedItems.contains(item) == false) {
            materialsList.setValue(null);
        }

        materialsList.select(item);

        logger.trace("Exiting selectTableItem()");
    }

    /**
     * Removes selected items in a table from the material container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<Material> selectedItems = ((Set<Material>) materialsList.getValue());

        for (Material item : selectedItems) {
            materialsList.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }

    /**
     * Adds new item of {@link Material} type to the material container.
     */
    public void addNewItem() {
        Material material = ObjectFactory.createMaterial();
        materialsList.addItem(material);
        
        materialsList.setValue(null);
        materialsList.setCurrentPageFirstItemId(material);
        materialsList.select(material);
    }

    /**
     * This method is automatically called by the framework when
     * <code>materials</code> property of the {@link RegistrationUnit} object is
     * bounded to {@link Field}. This happens in
     * {@link RegistrationUnitFieldFactory}.
     * <p>
     * This method is used to populate the container which holds
     * {@link Material} objects.
     */
    @Override
    public void setPropertyDataSource(Property propertyDataSource) {
        logger.trace("Entering setPropertyDataSource()");

        super.setPropertyDataSource(propertyDataSource);

        @SuppressWarnings("unchecked")
        List<Material> materials = (List<Material>) propertyDataSource.getValue();
        materialContainer.removeAllItems();
        materialContainer.addAll(materials);

        logger.trace("Exiting setPropertyDataSource()");
    }

    /**
     * This method is automatically called by the framework when the
     * {@link Form} is commited or when there is a need to read the value from
     * the field (user selected another item from the table).
     * <p>
     * Method will return every {@link Material} from the container.
     */
    @Override
    public Object getValue() {
        logger.trace("Entering getValue()");

        ArrayList<Material> materials = new ArrayList<Material>();

        for (Object itemId : materialContainer.getItemIds()) {
            materials.add(materialContainer.getItem(itemId).getBean());
        }

        logger.trace("Exiting getValue()");

        return materials;
    }

    /**
     * This method is automatically called by the framework when there is a need
     * to discard the value from a field. For example, user clicked on
     * "Discard changes" button located in the form.
     * <p>
     * This will simply overwrite the {@link Material} container with the
     * original list of materials available from the time when the form
     * initially bound the <code>materials</code> property of
     * {@link RegistrationUnit} object.
     */
    @Override
    public void discard() throws SourceException {
        logger.trace("Entering discard()");

        super.discard();

        Property propertyDataSource = getPropertyDataSource();

        if (propertyDataSource != null) {
            @SuppressWarnings("unchecked")
            List<Material> materials = (List<Material>) propertyDataSource.getValue();
            materialContainer.removeAllItems();
            materialContainer.addAll(materials);
        }

        logger.trace("Exiting discard()");
    }
}
