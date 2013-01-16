package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Label;
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
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

public class LabelCustomField extends CustomField {
    private static final long serialVersionUID = 1L;

    private final Logger logger = LoggerFactory.getLogger(LabelCustomField.class.getName());
    private Table labelsTable;
    private static final int NUMBER_OF_VISIBLE_ROWS = 4;
    private static final String NAME_PROPERTY = "name";
    private static final String REMAINING_LABEL_ID_TYPE_PROPERTY = "remainingLabelIdType";
    private LabelsCustomFieldController controller = new LabelsCustomFieldController(this);
    private BeanItemContainer<Label> labelContainer = new BeanItemContainer<Label>(Label.class);

    public LabelCustomField(String caption) {
        logger.trace("Entering LabelCustomField()");

        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);

        labelsTable = new Table();
        labelsTable.setSelectable(true);
        labelsTable.setEditable(true);
        labelsTable.setTableFieldFactory(new LabelsTableFieldFactory());
        labelsTable.setMultiSelect(true);
        labelsTable.setSizeFull();
        labelsTable.setImmediate(true);
        labelsTable.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        labelsTable.setContainerDataSource(labelContainer);
        labelsTable.setVisibleColumns(new Object[] { REMAINING_LABEL_ID_TYPE_PROPERTY, NAME_PROPERTY });
        labelsTable.setColumnHeader(NAME_PROPERTY, "Naziv");
        labelsTable.setColumnHeader(REMAINING_LABEL_ID_TYPE_PROPERTY, "Vrsta Oznake");
        labelsTable.addActionHandler(controller);
        labelsTable.addListener((ItemClickListener) controller);
        layout.addComponent(labelsTable);
        layout.setExpandRatio(labelsTable, 1f);

        setCompositionRoot(layout);

        logger.trace("Exiting LabelCustomField()");
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
     *            - {@link Label} which needs to be added to selection.
     */
    void selectTableItem(Object item) {
        logger.trace("Entering selectTableItem()");

        @SuppressWarnings("unchecked")
        Set<Label> selectedItems = ((Set<Label>) labelsTable.getValue());

        if (selectedItems.contains(item) == false) {
            labelsTable.setValue(null);
        }

        labelsTable.select(item);

        logger.trace("Exiting selectTableItem()");
    }
    
    /**
     * Removes selected items in a table from the label container.
     */
    void removeSelectedItems() {
        logger.trace("Entering removeSelectedItems()");

        @SuppressWarnings("unchecked")
        Set<Label> selectedItems = ((Set<Label>) labelsTable.getValue());

        for (Label item : selectedItems) {
            labelsTable.removeItem(item);
        }

        logger.trace("Exiting removeSelectedItems()");
    }
    
    /**
     * Adds new item of {@link Label} type to the label container.
     */
    public void addNewItem() {
        logger.trace("Entering addNewItem()");
        
        Label label = ObjectFactory.createLabel();
        labelsTable.addItem(label);
        
        labelsTable.setValue(null);
        labelsTable.setCurrentPageFirstItemId(label);
        labelsTable.select(label);
        
        logger.trace("Exiting addNewItem()");
    }
    
    /**
     * This method is automatically called by the framework when
     * <code>labels</code> property of the {@link RegistrationUnit} object is
     * bounded to {@link Field}. This happens in
     * {@link RegistrationUnitFieldFactory}.
     * <p>
     * This method is used to populate the container which holds
     * {@link Label} objects.
     */
    @Override
    public void setPropertyDataSource(Property propertyDataSource) {
        logger.trace("Entering setPropertyDataSource()");

        super.setPropertyDataSource(propertyDataSource);

        @SuppressWarnings("unchecked")
        List<Label> labels = (List<Label>) propertyDataSource.getValue();
        labelContainer.removeAllItems();
        labelContainer.addAll(labels);

        logger.trace("Exiting setPropertyDataSource()");
    }
    
    /**
     * This method is automatically called by the framework when the
     * {@link Form} is commited or when there is a need to read the value from
     * the field (user selected another item from the table).
     * <p>
     * Method will return every {@link Label} from the container.
     */
    @Override
    public Object getValue() {
        logger.trace("Entering getValue()");

        ArrayList<Label> labels = new ArrayList<Label>();

        for (Object itemId : labelContainer.getItemIds()) {
            labels.add(labelContainer.getItem(itemId).getBean());
        }

        logger.trace("Exiting getValue()");

        return labels;
    }
    
    /**
     * This method is automatically called by the framework when there is a need
     * to discard the value from a field. For example, user clicked on
     * "Discard changes" button located in the form.
     * <p>
     * This will simply overwrite the {@link Label} container with the
     * original list of labels available from the time when the form
     * initially bound the <code>labels</code> property of
     * {@link RegistrationUnit} object.
     */
    @Override
    public void discard() throws SourceException {
        logger.trace("Entering discard()");

        super.discard();

        Property propertyDataSource = getPropertyDataSource();

        if (propertyDataSource != null) {
            @SuppressWarnings("unchecked")
            List<Label> labels = (List<Label>) propertyDataSource.getValue();
            labelContainer.removeAllItems();
            labelContainer.addAll(labels);
        }

        logger.trace("Exiting discard()");
    }
}
