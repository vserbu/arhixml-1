package hr.udruga01.arhixml.modules.mainwindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.udruga01.arhixml.datamodel.AvailableLabels;
import hr.udruga01.arhixml.datamodel.Label;
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
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;

/**
 * Custom {@link DefaultFieldFactory} used for emmbeding {@link ComboBox}
 * component inside of a {@link Table} of {@link Label} objects.
 */
public class LabelsTableFieldFactory implements TableFieldFactory {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(LabelsTableFieldFactory.class.getName());
    
    private BeanContainer<Integer, Label> labelContainer;

    public LabelsTableFieldFactory() {
        logger.trace("Entering LabelsTableFieldFactory()");
        
        labelContainer = new BeanContainer<Integer, Label>(Label.class);
        labelContainer.setBeanIdProperty("remainingLabelIdType");
        labelContainer.addAll(AvailableLabels.getAvailableLabels());
        
        logger.trace("Exiting LabelsTableFieldFactory()");
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

        Field tableField = null;
        
        if ("remainingLabelIdType".equals(propertyId)) {
            final ComboBox labelsList = new ComboBox();
            labelsList.setItemCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
            labelsList.setItemCaptionPropertyId("label");
            labelsList.setTextInputAllowed(false);
            labelsList.setNullSelectionAllowed(false);
            labelsList.setImmediate(true);
            labelsList.setWidth("95%");
            labelsList.setContainerDataSource(labelContainer);
    
            labelsList.setReadOnly(true);
    
            labelsList.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    labelsList.setReadOnly(false);
    
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
    
            labelsList.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    labelsList.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
    
            tableField = labelsList;
        } else if ("name".equals(propertyId)) {
            final TextField nameTextField = new TextField();
            nameTextField.setWidth("100%");
            nameTextField.setReadOnly(true);
            nameTextField.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    nameTextField.setReadOnly(false);
    
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
    
            nameTextField.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    nameTextField.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
            
            tableField = nameTextField;
        }
        
        logger.trace("Exiting createField()");
        
        return tableField;
    }
}
