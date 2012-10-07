package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Material;

import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.TextField;

/**
 * The custom {@link FormFieldFactory} for the form in which {@link RegistrationUnit} is bound to.
 * <p>
 * By default, {@link Form} will render default components for each JavaBean property.
 * Registering this class on our form will instruct it to use our specific component for each {@link RegistrationUnit} property.
 * <p>
 * This works in a way that {@link Form} will automatically call <code>createField()</code> defined in this class for each {@link RegistrationUnit} property.
 * When this happens we will create our own component that will represent this property and return it as a {@link Field}.
 */
public class RegistrationUnitFieldFactory implements FormFieldFactory {
    private static final long serialVersionUID = 1L;

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

    public RegistrationUnitFieldFactory() {
        contentsField = new TextField("Sadržaj");
        contentsField.setWidth("100%");
        contentsField.setNullRepresentation("");

        holderIdField = new TextField("Šifra imatelja");
        holderIdField.setNullRepresentation("");

        levelIdField = new TextField("Šifra razine");
        levelIdField.setNullRepresentation("");

        nameField = new TextField("Naziv");
        nameField.setWidth("100%");
        nameField.setNullRepresentation("");

        noteField = new TextField("Napomena");
        noteField.setWidth("100%");
        noteField.setNullRepresentation("");

        signatureField = new TextField("Signatura");
        signatureField.setNullRepresentation("");

        timePeriodNoteField = new TextField("Napomena o razdoblju");
        timePeriodNoteField.setWidth("100%");
        timePeriodNoteField.setNullRepresentation("");

        yearFromField = new TextField("Godina od");
        yearFromField.setNullRepresentation("");

        yearToField = new TextField("Godina do");
        yearToField.setNullRepresentation("");

        materialField = new MaterialsCustomField("Graða");
    }

    /**
     * This method will be automatically called by {@link Form} for each {@link RegistrationUnit} property.
     * Our job here is that we create component and return it to the caller for each property.
     */
    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {
        String beanProperty = (String) propertyId;

        if ("contents".equals(beanProperty)) {
            return contentsField;
        } else if ("holderId".equals(beanProperty)) {
            return holderIdField;
        } else if ("levelId".equals(beanProperty)) {
            return levelIdField;
        } else if ("name".equals(beanProperty)) {
            return nameField;
        } else if ("note".equals(beanProperty)) {
            return noteField;
        } else if ("signature".equals(beanProperty)) {
            return signatureField;
        } else if ("timePeriodNote".equals(beanProperty)) {
            return timePeriodNoteField;
        } else if ("yearFrom".equals(beanProperty)) {
            return yearFromField;
        } else if ("yearTo".equals(beanProperty)) {
            return yearToField;
        } else if ("materials".equals(beanProperty)) {
            BeanItemContainer<Material> container = new BeanItemContainer<Material>(Material.class);
            @SuppressWarnings("unchecked")
            List<Material> materials = (List<Material>) item.getItemProperty("materials").getValue();
            container.addAll(materials);

            materialField.setContainerDataSource(container);
            
            return materialField;
        }

        return null;
    }
}
