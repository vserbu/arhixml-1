package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.AvailableTechnicalUnits;
import hr.udruga01.arhixml.datamodel.TechnicalUnit;

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

public class TechnicalUnitsTableFieldFactory implements TableFieldFactory {
private static final long serialVersionUID = 1L;
    
    private final Logger logger = LoggerFactory.getLogger(TechnicalUnitsTableFieldFactory.class.getName());
    private BeanContainer<Integer, TechnicalUnit> technicalUnitContainer;

    public TechnicalUnitsTableFieldFactory() {
        logger.trace("Entering TechnicalUnitsTableFieldFactory()");
        
        technicalUnitContainer = new BeanContainer<Integer, TechnicalUnit>(TechnicalUnit.class);
        technicalUnitContainer.setBeanIdProperty("technicalUnitTypeId");
        technicalUnitContainer.addAll(AvailableTechnicalUnits.getAvailableTechnicalUnits());
        
        logger.trace("Exiting TechnicalUnitsTableFieldFactory()");
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
        
        if ("technicalUnitTypeId".equals(propertyId)) {
            final ComboBox archiveUnitsList = new ComboBox();
            archiveUnitsList.setItemCaptionMode(ComboBox.ITEM_CAPTION_MODE_PROPERTY);
            archiveUnitsList.setItemCaptionPropertyId("label");
            archiveUnitsList.setTextInputAllowed(false);
            archiveUnitsList.setNullSelectionAllowed(false);
            archiveUnitsList.setImmediate(true);
            archiveUnitsList.setWidth("95%");
            archiveUnitsList.setContainerDataSource(technicalUnitContainer);
    
            archiveUnitsList.setReadOnly(true);
    
            archiveUnitsList.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    archiveUnitsList.setReadOnly(false);
    
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
    
            archiveUnitsList.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    archiveUnitsList.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
    
            tableField = archiveUnitsList;
        } else if ("amount".equals(propertyId)) {
            final TextField amountTextField = new TextField();
            amountTextField.setWidth("100%");
            amountTextField.setReadOnly(true);
            amountTextField.setNullRepresentation("");
            amountTextField.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    amountTextField.setReadOnly(false);
    
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
    
            amountTextField.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    amountTextField.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
            
            tableField = amountTextField;
        } else if ("characteristics".equals(propertyId)) {
            final TextField characteristicsTextField = new TextField();
            characteristicsTextField.setWidth("100%");
            characteristicsTextField.setReadOnly(true);
            characteristicsTextField.setNullRepresentation("");
            characteristicsTextField.addListener(new FocusListener() {
                private static final long serialVersionUID = 1L;
    
                public void focus(FocusEvent event) {
                    logger.trace("Entering focus()");
    
                    characteristicsTextField.setReadOnly(false);
    
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
    
            characteristicsTextField.addListener(new BlurListener() {
                private static final long serialVersionUID = 1L;
    
                public void blur(BlurEvent event) {
                    logger.trace("Entering blur()");
    
                    characteristicsTextField.setReadOnly(true);
    
                    logger.trace("Exiting blur()");
                }
            });
            
            tableField = characteristicsTextField;
        }
        
        logger.trace("Exiting createField()");
        
        return tableField;
    }
}
