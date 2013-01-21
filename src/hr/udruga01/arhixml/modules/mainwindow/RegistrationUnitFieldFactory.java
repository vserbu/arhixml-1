package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Item;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

/**
 * The custom {@link FormFieldFactory} for the form in which
 * {@link RegistrationUnit} is bound to.
 * <p>
 * By default, {@link Form} will render default components for each JavaBean
 * property. Registering this class on our form will instruct it to use our
 * specific component for each {@link RegistrationUnit} property.
 * <p>
 * This works in a way that {@link Form} will automatically call
 * <code>createField()</code> defined in this class for each
 * {@link RegistrationUnit} property. When this happens we will create our own
 * component that will represent this property and return it as a {@link Field}.
 */
class RegistrationUnitFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(RegistrationUnitFieldFactory.class.getName());

    private TextField contentsField;
    private TextField holderIdField;
    private TextField levelIdField;
    private TextField nameField;
    private TextField noteField;
    private TextField signatureField;
    private TextField timePeriodNoteField;
    private TextField yearFromField;
    private TextField yearToField;
    private MaterialsCustomField materialField;
    private MediumCustomField mediumField;
    private IntegerValidator holderIdValidator;
    private LabelCustomField labelField;
    private ArchiveUnitCustomField archiveUnitField;

    public RegistrationUnitFieldFactory() {
        logger.trace("Entering RegistrationUnitFieldFactory()");
        
        contentsField = new TextField("Sadržaj");
        contentsField.setWidth("100%");
        contentsField.setNullRepresentation("");

        holderIdField = new TextField("Šifra imatelja");
        holderIdField.setImmediate(true);
        holderIdField.setWidth("100%");
        holderIdField.setRequiredError("Molim unesite šifru imatelja");
        holderIdField.setNullRepresentation("");
        holderIdValidator = new IntegerValidator("Šifra imatelja mora biti brojèana vrijednost");
        
        levelIdField = new TextField("Šifra razine");
        levelIdField.setWidth("100%");
        levelIdField.setRequired(true);
        levelIdField.setRequiredError("Molim unesite šifru razine");
        levelIdField.setNullRepresentation("");
        levelIdField.addValidator(new IntegerValidator("Šifra razine mora biti brojèana vrijednost"));

        nameField = new TextField("Naziv");
        nameField.setWidth("100%");
        nameField.setRequired(true);
        nameField.setRequiredError("Molim unesite naziv registraturne jedinice");
        nameField.setNullRepresentation("");

        noteField = new TextField("Napomena");
        noteField.setWidth("100%");
        noteField.setNullRepresentation("");

        signatureField = new TextField("Signatura");
        signatureField.setWidth("100%");
        signatureField.setRequired(true);
        signatureField.setRequiredError("Molim unesite šifru signature");
        signatureField.setNullRepresentation("");
        signatureField.addValidator(new IntegerValidator("Vrijednost signature mora biti brojèana vrijednost"));

        timePeriodNoteField = new TextField("Napomena o razdoblju");
        timePeriodNoteField.setWidth("100%");
        timePeriodNoteField.setNullRepresentation("");

        yearFromField = new TextField("Godina od");
        yearFromField.setWidth("100%");
        yearFromField.setNullRepresentation("");
        yearFromField.setNullSettingAllowed(true);
        yearFromField.addValidator(new IntegerValidator("Vrijednost godine mora biti brojèana vrijednost"));

        yearToField = new TextField("Godina do");
        yearToField.setWidth("100%");
        yearToField.setNullRepresentation("");
        yearToField.setNullSettingAllowed(true);
        yearToField.addValidator(new IntegerValidator("Vrijednost godine mora biti brojèana vrijednost"));

        materialField = new MaterialsCustomField("Graða");
        
        mediumField = new MediumCustomField("Medij");
        
        labelField = new LabelCustomField("Oznaka");
        
        archiveUnitField = new ArchiveUnitCustomField("Arhivske Jedinice");
        
        logger.trace("Exiting RegistrationUnitFieldFactory()");
    }

    /**
     * This method will be automatically called by {@link Form} for each
     * {@link RegistrationUnit} property. Our job here is that we create
     * component and return it to the caller for each property.
     */
    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {
        logger.trace("Entering createField()");
        
        Field formField = null;
        String beanProperty = (String) propertyId;

        if ("contents".equals(beanProperty)) {
            formField = contentsField;
        } else if ("holderId".equals(beanProperty)) {
            logger.trace("Exiting createField()");
            
            // Get the parent registration unit.
            Object parentRegistrationUnit = item.getItemProperty("parentRegistrationUnit").getValue();
            
            if (parentRegistrationUnit == null) {
                // There is no parent which means that item is a top level registration unit.
                // This means that holderId field must be enabled on the form.
                holderIdField.setEnabled(true);
                holderIdField.setRequired(true);
                holderIdField.addValidator(holderIdValidator);
            } else {
                // This item is not top level registration unit.
                // We need to disable the holderId field.
                holderIdField.setEnabled(false);
                holderIdField.setRequired(false);
                holderIdField.removeAllValidators();
            }
            
            formField = holderIdField;
        } else if ("levelId".equals(beanProperty)) {
            formField = levelIdField;
        } else if ("name".equals(beanProperty)) {
            formField = nameField;
        } else if ("note".equals(beanProperty)) {
            formField = noteField;
        } else if ("signature".equals(beanProperty)) {
            formField = signatureField;
        } else if ("timePeriodNote".equals(beanProperty)) {
            formField = timePeriodNoteField;
        } else if ("yearFrom".equals(beanProperty)) {
            formField = yearFromField;
        } else if ("yearTo".equals(beanProperty)) {
            formField = yearToField;
        } else if ("materials".equals(beanProperty)) {
            formField = materialField;
        } else if ("mediums".equals(beanProperty)) {
            formField = mediumField;
        } else if ("labels".equals(beanProperty)) {
            formField = labelField;
        } else if ("archiveUnits".equals(beanProperty)) {
            formField = archiveUnitField;
        }

        logger.trace("Exiting createField()");
        
        return formField;
    }
}
