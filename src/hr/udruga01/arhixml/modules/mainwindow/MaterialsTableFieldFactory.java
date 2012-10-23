package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.AvailableMaterials;
import hr.udruga01.arhixml.datamodel.Material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;

/**
 * Custom {@link DefaultFieldFactory} used for emmbeding {@link ComboBox}
 * component inside of a {@link Table} of {@link Material} objects.
 */
public class MaterialsTableFieldFactory extends DefaultFieldFactory {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MaterialsTableFieldFactory.class.getName());

    private BeanContainer<Integer, Material> materialContainer;

    public MaterialsTableFieldFactory() {
        logger.trace("Entering MaterialsTableFieldFactory()");

        materialContainer = new BeanContainer<Integer, Material>(Material.class);
        materialContainer.setBeanIdProperty("subtypeId");
        materialContainer.addAll(AvailableMaterials.getAvailableMaterials());

        logger.trace("Exiting MaterialsTableFieldFactory()");
    }

    /**
     * This method is automatically called by the framework for each item in a
     * table.
     * <p>
     * There is a need to put a {@link ComboBox} as an cell editor for each of
     * these items. This is why we created a custom {@link DefaultFieldFactory}.
     */
    @Override
    public Field createField(Container container, final Object itemId, Object propertyId, final Component uiContext) {
        logger.trace("Entering createField()");

        final ComboBox materialsList = new ComboBox();
        materialsList.setItemCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
        materialsList.setItemCaptionPropertyId("label");
        materialsList.setTextInputAllowed(false);
        materialsList.setNullSelectionAllowed(false);
        materialsList.setImmediate(true);
        materialsList.setWidth("95%");
        materialsList.setContainerDataSource(materialContainer);

        materialsList.setReadOnly(true);

        materialsList.addListener(new FocusListener() {
            private static final long serialVersionUID = 1L;

            public void focus(FocusEvent event) {
                logger.trace("Entering focus()");

                materialsList.setReadOnly(false);

                // FIXME Below is a rudimentary implementation of table row
                // selection.
                // It doesn't support multi selection despite we set a
                // multiselection for a table. This should be fixed to have a
                // natural multiselection for a table.
                ((Table) uiContext).setValue(null);
                ((Table) uiContext).select(itemId);

                logger.trace("Exiting focus()");
            }
        });

        materialsList.addListener(new BlurListener() {
            private static final long serialVersionUID = 1L;

            public void blur(BlurEvent event) {
                logger.trace("Entering blur()");

                materialsList.setReadOnly(true);

                logger.trace("Exiting blur()");
            }
        });

        logger.trace("Exiting createField()");

        return materialsList;
    }
}
