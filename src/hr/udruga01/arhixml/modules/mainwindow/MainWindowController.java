package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;
import hr.udruga01.arhixml.util.FileDownloadResource;

import java.io.File;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.AbstractSplitPanel.SplitterClickEvent;
import com.vaadin.ui.AbstractSplitPanel.SplitterClickListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window.Notification;

class MainWindowController implements Receiver, SucceededListener, ItemClickListener, ClickListener, ValueChangeListener, SplitterClickListener {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MainWindowController.class.getName());

    private MainWindowModel model = new MainWindowModel();
    private MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        logger.trace("Entering MainWindowController()");
        this.mainWindow = mainWindow;
        logger.trace("Exiting MainWindowController()");
    }

    /**
     * This method will be called when user tries to upload an XML file.
     * <p>
     * The choosen XML file is then uploaded on the server.
     */
    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        logger.trace("Entering receiveUpload()");
        logger.trace("Exiting receiveUpload()");

        return model.setUpUpload(filename);
    }

    /**
     * This method will be called when the upload of the XML file is succesfully
     * finished.
     * <p>
     * After this, file is unmarshalled to an Java object of the type
     * {@link Arhinet} and the object is set as the data source for the
     * {@link TreeTable} component.
     */
    @Override
    public void uploadSucceeded(SucceededEvent event) {
        logger.trace("Entering uploadSucceeded()");

        Arhinet arhinet = model.unmarshalFile();

        if (arhinet == null) {
            logger.error("Validation of the uploaded XML file failed.");
            mainWindow.showNotification("Validation of the uploaded XML file failed", Notification.TYPE_ERROR_MESSAGE);
            logger.trace("Exiting uploadSucceeded()");

            return;
        }

        mainWindow.setTableData(arhinet);

        logger.trace("Exiting uploadSucceeded()");
    }

    /**
     * This method will be called when user selects item from the TreeTable.
     * <p>
     * Each item in the TreeTable represents the {@link RegistrationUnit}. The
     * selected item is then handed over to {@link Form} component as a data
     * source.
     */
    @Override
    public void itemClick(ItemClickEvent event) {
        logger.trace("Entering itemClick()");
        mainWindow.setFormData(event.getItem());
        logger.trace("Exiting itemClick()");
    }

    /**
     * This method will be automatically called by the framework when user
     * clicks on "Save File" button.
     * <p>
     * This will trigger marshaling process of the table data object and
     * downloading the created file from the marshal process.
     */
    @Override
    public void buttonClick(ClickEvent event) {
        logger.trace("Entering buttonClick()");

        Arhinet arhinet = mainWindow.getTableData();
        logger.debug("Fetched table data.");
        File file = model.marshalToFile(arhinet);
        mainWindow.open(new FileDownloadResource(file, mainWindow.getApplication()));
        logger.debug("File offered for downloading.");

        logger.trace("Exiting buttonClick()");
    }

    /**
     * This method is automatically called by the framework when user changes
     * selection on a table.
     * <p>
     * Difference between this method and the
     * {@link #itemClick(ItemClickEvent event) itemClick()} is in processing of
     * selection state. The {@link #itemClick(ItemClickEvent event) itemClick()}
     * will report the old selection state, whereas this method will report
     * current (correct) selection change.
     */
    @Override
    public void valueChange(ValueChangeEvent event) {
        logger.trace("Entering valueChange()");

        mainWindow.rememberSplitterPosition();

        if (mainWindow.isTableItemSelected() == true) {
            mainWindow.setFormVisible(true);
        } else {
            mainWindow.setFormVisible(false);
        }

        logger.trace("Exiting valueChange()");
    }

    /**
     * This method is automatically called by the framework when user makes a
     * double click on splitter on {@link VerticalSplitPanel}.
     * <p>
     * Double click will hide the form.
     */
    @Override
    public void splitterClick(SplitterClickEvent event) {
        logger.trace("Entering splitterClick()");

        if (event.isDoubleClick()) {
            mainWindow.setFormVisible(false);
        }

        logger.trace("Exiting splitterClick()");
    }
}
