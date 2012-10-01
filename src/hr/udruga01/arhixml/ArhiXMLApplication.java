package hr.udruga01.arhixml;

import hr.udruga01.arhixml.modules.mainwindow.MainWindow;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class ArhiXMLApplication extends Application {
    private static final long serialVersionUID = 1L;

    /**
     * This is the application entry point.
     * The framework calls this method once for each session.
     * This is where the application is creating the main window, setups the components and event listeners.
     */
    @Override
    public void init() {
        Window mainWindow = new MainWindow("ArhiXML");
        setMainWindow(mainWindow);
    }
}
