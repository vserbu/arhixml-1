package hr.udruga01.arhixml.modules.mainwindow;

import java.util.ArrayList;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * This class represents the {@link CustomField} for the <code>materials</code> property of {@link RegistrationUnit} class.
 * Component consist of {@link Table} and {@link Button} for creating new and deleting selected {@link Material} items from a table.
 */
public class MaterialsCustomField extends CustomField {
    private static final long serialVersionUID = 1L;

    private static final int NUMBER_OF_VISIBLE_ROWS = 5;
    private VerticalLayout layout;
    private Table materialsList;

    public MaterialsCustomField(String caption) {
        setCaption(caption);

        layout = new VerticalLayout();
        materialsList = new Table();
        materialsList.setSelectable(true);
        materialsList.setMultiSelect(true);
        materialsList.setWidth("10%");
        materialsList.setImmediate(true);
        materialsList.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        materialsList.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        layout.addComponent(materialsList);

        setCompositionRoot(layout);
    }

    /**
     * This method will allow setting the {@link Container} for the {@link Table}.
     * 
     * @param container - {@link Container} that holds the {@link Material} objects.
     */
    public void setContainerDataSource(Container container) {
        materialsList.setContainerDataSource(container);
    }

    /**
     * This method is automatically called when there is a need to read and set the value to a table.
     * <p>
     * Basically, it is overriden because our {@link Table} is in a multiselect mode.
     * This again means that the table will return the {@link Set} of the selected items.
     * And this is the sole reason that <code>getType()</code> is always returning <code>ArrayList.class</code>.
     * <p>
     * If we do not override this method and return <code>ArrayList.class</code>, framework data binding will not be able to set the value
     * for the <code>materials</code> property of {@link RegistrationUnit} class. Our <code>materials</code> property is a {@link List} of 
     * {@link Material} objects. The {@link List} interface is not compatible to {@link Set} interface and the framework will throw exception.
     */
    @Override
    public Class<?> getType() {
        return ArrayList.class;
    }
}
