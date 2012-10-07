package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * {@link MainWindow} represents the main application window.
 * <p>
 * The user interface of this window consists of two parts:
 * <li>Upper part holds the table of {@link RegistrationUnit} items.
 * Selecting an item from this table revails the lower part of the window.
 * <li>Lower part holds the details of selected {@link RegistrationUnit}.
 * This is where user is able to see all the details of the selected {@link RegistrationUnit}.
 */
public class MainWindow extends Window {
    private static final long serialVersionUID = 1L;
    
    private MainWindowController controller = new MainWindowController(this);
    private static final String LEVEL_ID_PROPERTY = "levelId";
    private static final String SIGNATURE_PROPERTY = "signature";
    private static final String NAME_PROPERTY = "name";
    private static final String YEAR_FROM_PROPERTY = "yearFrom";
    private static final String YEAR_TO_PROPERTY = "yearTo";
    private static final String HOLDER_ID_PROPERTY = "holderId";
    private RegistrationUnitContainer registrationUnitContainer = new RegistrationUnitContainer();
    private TreeTable registrationUnitTable;
    private Form registrationUnitDetails;

    public MainWindow(String caption) {
        setCaption(caption);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);
        
        HorizontalLayout buttonToolbar = new HorizontalLayout();
        Upload loadFileButton = new Upload(null, controller);
        loadFileButton.addListener(controller);
        loadFileButton.setImmediate(true);
        loadFileButton.setButtonCaption("Load File");
        buttonToolbar.addComponent(loadFileButton);
        
        verticalLayout.addComponent(buttonToolbar);
        
        registrationUnitTable = new TreeTable();
        registrationUnitTable.setImmediate(true);
        registrationUnitTable.setMultiSelect(true);
        registrationUnitTable.setWidth("100%");

        registrationUnitTable.setContainerDataSource(registrationUnitContainer);
        registrationUnitTable.setVisibleColumns(new Object[] {NAME_PROPERTY, LEVEL_ID_PROPERTY, SIGNATURE_PROPERTY, HOLDER_ID_PROPERTY, YEAR_FROM_PROPERTY, YEAR_TO_PROPERTY});
        
        registrationUnitTable.setColumnHeader(LEVEL_ID_PROPERTY, "Razina Id");
        registrationUnitTable.setColumnHeader(SIGNATURE_PROPERTY, "Signatura");
        registrationUnitTable.setColumnHeader(NAME_PROPERTY, "Naziv");
        registrationUnitTable.setColumnHeader(YEAR_FROM_PROPERTY, "Godina Od");
        registrationUnitTable.setColumnHeader(YEAR_TO_PROPERTY, "Godina Do");
        registrationUnitTable.setColumnHeader(HOLDER_ID_PROPERTY, "Imatelj Id");
        
        registrationUnitTable.setSelectable(true);
        registrationUnitTable.addListener(controller);
        
        verticalLayout.addComponent(registrationUnitTable);
        
        registrationUnitDetails = new Form();
        registrationUnitDetails.setWidth("100%");
        registrationUnitDetails.setCaption("Registraturna Jedinica");
        registrationUnitDetails.setFormFieldFactory(new RegistrationUnitFieldFactory());
        
        verticalLayout.addComponent(registrationUnitDetails);
        
        addComponent(verticalLayout);
    }

    /**
     * This method is called after the unmarshaling process of the XML file is succesfull.
     * <p>
     * It will populate the table container with all {@link RegistrationUnit} objects in {@link Arhinet}.
     * 
     * @param arhinet - This is the object that the unmarshaler creates out of the XML file.
     */
    public void setTableData(Arhinet arhinet) {
        registrationUnitContainer.setData(arhinet);
    }

    /**
     * This method is called when user selects a {@link RegistrationUnit} item from the table.
     * <p>
     * It will bind the properties from {@link RegistrationUnit} object to a form.
     * 
     * @param registrationUnit - The selected table item that will be bound to a form.
     */
    public void setFormData(RegistrationUnit registrationUnit) {
        BeanItem<RegistrationUnit> item = new BeanItem<RegistrationUnit>(registrationUnit);
        registrationUnitDetails.setItemDataSource(item);
    }
}
