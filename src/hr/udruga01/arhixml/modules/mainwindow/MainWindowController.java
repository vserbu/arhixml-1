package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.io.OutputStream;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

class MainWindowController implements Receiver, SucceededListener, ItemClickListener {
    private static final long serialVersionUID = 1L;
    
    private MainWindowModel model = new MainWindowModel();
    private MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * This method will be called when user tries to upload an XML file.
     * <p>
     * The choosen XML file is then uploaded on the server.
     */
    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        return model.setUpUpload(filename);
    }

    /**
     * This method will be called when the upload of the XML file is succesfully finished.
     * <p>
     * After this, file is unmarshalled to an Java object of the type {@link Arhinet}
     * and the object is set as the data source for the {@link TreeTable} component.
     */
    @Override
    public void uploadSucceeded(SucceededEvent event) {
        Arhinet arhinet = model.unmarshalFile();
        mainWindow.setTableData(arhinet);
    }

    /**
     * This method will be called when user selects item from the TreeTable.
     * <p>
     * Each item in the TreeTable represents the {@link RegistrationUnit}.
     * The selected item is then handed over to {@link Form} component as a data source.
     */
    @Override
    public void itemClick(ItemClickEvent event) {
        RegistrationUnit registrationUnit = (RegistrationUnit) event.getItemId();
        mainWindow.setFormData(registrationUnit);
    }
}
