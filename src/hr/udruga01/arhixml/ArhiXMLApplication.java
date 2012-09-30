package hr.udruga01.arhixml;

import hr.udruga01.arhixml.modules.mainwindow.MainWindow;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class ArhiXMLApplication extends Application {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        Window mainWindow = new MainWindow("ArhiXML");
        setMainWindow(mainWindow);
    }
}
