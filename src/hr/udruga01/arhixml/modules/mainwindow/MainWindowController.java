package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;

import java.io.OutputStream;

import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

class MainWindowController implements Receiver, SucceededListener {
    private static final long serialVersionUID = 1L;
    
    private MainWindowModel model = new MainWindowModel();
    private MainWindow mainWindow;

    public MainWindowController(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        return model.uploadFile(filename);
    }

    @Override
    public void uploadSucceeded(SucceededEvent event) {
        Arhinet arhinet = model.unmarshallFile();
        mainWindow.setTableData(arhinet);
    }
}
