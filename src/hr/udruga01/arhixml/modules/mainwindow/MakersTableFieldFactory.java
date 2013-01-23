package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.AvailableMakers;
import hr.udruga01.arhixml.datamodel.Maker;

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
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;

public class MakersTableFieldFactory implements TableFieldFactory {
    private static final long serialVersionUID = 1L;
    
    private final Logger logger = LoggerFactory.getLogger(MakersTableFieldFactory.class.getName());
    private BeanContainer<Integer, Maker> makerContainer;

    public MakersTableFieldFactory() {
        logger.trace("Entering MakersTableFieldFactory()");
        
        makerContainer = new BeanContainer<Integer, Maker>(Maker.class);
        makerContainer.setBeanIdProperty("roleId");
        makerContainer.addAll(AvailableMakers.getAvailableMakers());
        
        logger.trace("Exiting MakersTableFieldFactory()");
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
        
        if ("makerId".equals(propertyId)) {
            final TextField makerIdTextField = new TextField();
            makerIdTextField.setWidth("100%");
            makerIdTextField.setReadOnly(true);
            makerIdTextField.setNullRepresentation("");
            makerIdTextField.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    makerIdTextField.setReadOnly(false);
    
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
    
            makerIdTextField.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    makerIdTextField.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
            
            tableField = makerIdTextField;
        } else if ("roleId".equals(propertyId)) {
            final ComboBox makersList = new ComboBox();
            makersList.setItemCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
            makersList.setItemCaptionPropertyId("label");
            makersList.setTextInputAllowed(false);
            makersList.setNullSelectionAllowed(false);
            makersList.setImmediate(true);
            makersList.setWidth("95%");
            makersList.setContainerDataSource(makerContainer);
    
            makersList.setReadOnly(true);
    
            makersList.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    makersList.setReadOnly(false);
    
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
    
            makersList.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    makersList.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
    
            tableField = makersList;
        } else if ("period".equals(propertyId)) {
            final TextField periodTextField = new TextField();
            periodTextField.setWidth("100%");
            periodTextField.setReadOnly(true);
            periodTextField.setNullRepresentation("");
            periodTextField.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    periodTextField.setReadOnly(false);
    
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
    
            periodTextField.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    periodTextField.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
            
            tableField = periodTextField;
        } else if ("makerNote".equals(propertyId)) {
            final TextField makerNoteTextField = new TextField();
            makerNoteTextField.setWidth("100%");
            makerNoteTextField.setReadOnly(true);
            makerNoteTextField.setNullRepresentation("");
            makerNoteTextField.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    makerNoteTextField.setReadOnly(false);
    
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
    
            makerNoteTextField.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    makerNoteTextField.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
            
            tableField = makerNoteTextField;
        }
        
        logger.trace("Exiting createField()");
        
        return tableField;
    }
}
