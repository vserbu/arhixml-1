package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Material;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Container;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * This class represents the {@link CustomField} for the <code>materials</code>
 * property of {@link RegistrationUnit} class. Component consist of
 * {@link Table} and {@link Button} for creating new and deleting selected
 * {@link Material} items from a table.
 */
class MaterialsCustomField extends CustomField {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MaterialsCustomField.class.getName());

    private static final int NUMBER_OF_VISIBLE_ROWS = 5;

    private Table materialsList;
    private Button newMaterialButton;
    private Button deleteMaterialButton;

    public MaterialsCustomField(String caption) {
        logger.trace("Entering MaterialsCustomField()");
        setCaption(caption);

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("20%");
        layout.setSpacing(true);

        materialsList = new Table();
        materialsList.setSelectable(true);
        materialsList.setMultiSelect(true);
        materialsList.setWidth("100%");
        materialsList.setImmediate(true);
        materialsList.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        materialsList.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        layout.addComponent(materialsList);

        VerticalLayout buttonsLayout = new VerticalLayout();

        newMaterialButton = new Button();
        newMaterialButton.setStyleName("small");
        newMaterialButton.setIcon(new ThemeResource("icons/add.png"));
        buttonsLayout.addComponent(newMaterialButton);

        deleteMaterialButton = new Button();
        deleteMaterialButton.setStyleName("small");
        deleteMaterialButton.setIcon(new ThemeResource("icons/remove.png"));
        buttonsLayout.addComponent(deleteMaterialButton);

        layout.addComponent(buttonsLayout);

        setCompositionRoot(layout);
        logger.trace("Exiting MaterialsCustomField()");
    }

    /**
     * This method will allow setting the {@link Container} for the
     * {@link Table}.
     * 
     * @param container
     *            - {@link Container} that holds the {@link Material} objects.
     */
    public void setContainerDataSource(Container container) {
        logger.trace("Entering setContainerDataSource()");
        materialsList.setContainerDataSource(container);
        logger.trace("Exiting setContainerDataSource()");
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
}
