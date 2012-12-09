package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.AvailableMediums;
import hr.udruga01.arhixml.datamodel.Medium;

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

public class MediumsTableFieldFactory extends DefaultFieldFactory {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MediumsTableFieldFactory.class.getName());

    private BeanContainer<Integer, Medium> mediumContainer;

    public MediumsTableFieldFactory() {
        logger.trace("Entering MediumsTableFieldFactory()");

        mediumContainer = new BeanContainer<Integer, Medium>(Medium.class);
        mediumContainer.setBeanIdProperty("mediumTypeId");
        mediumContainer.addAll(AvailableMediums.getAvailableMediums());

        logger.trace("Exiting MediumsTableFieldFactory()");
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

        final ComboBox mediumsList = new ComboBox();
        mediumsList.setItemCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
        mediumsList.setItemCaptionPropertyId("label");
        mediumsList.setTextInputAllowed(false);
        mediumsList.setNullSelectionAllowed(false);
        mediumsList.setImmediate(true);
        mediumsList.setWidth("95%");
        mediumsList.setContainerDataSource(mediumContainer);

        mediumsList.setReadOnly(true);

        mediumsList.addListener(new FocusListener() {
            private static final long serialVersionUID = 1L;

            public void focus(FocusEvent event) {
                logger.trace("Entering focus()");

                mediumsList.setReadOnly(false);

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

        mediumsList.addListener(new BlurListener() {
            private static final long serialVersionUID = 1L;

            public void blur(BlurEvent event) {
                logger.trace("Entering blur()");

                mediumsList.setReadOnly(true);

                logger.trace("Exiting blur()");
            }
        });

        logger.trace("Exiting createField()");

        return mediumsList;
    }
}
