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

    public void setTableData(Arhinet arhinet) {
        registrationUnitContainer.setData(arhinet);
    }

    public void setFormData(RegistrationUnit registrationUnit) {
        BeanItem<RegistrationUnit> item = new BeanItem<RegistrationUnit>(registrationUnit);
        registrationUnitDetails.setItemDataSource(item);
    }
}
