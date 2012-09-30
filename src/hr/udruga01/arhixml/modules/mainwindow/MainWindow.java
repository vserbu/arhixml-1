package hr.udruga01.arhixml.modules.mainwindow;

import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MainWindow extends Window {
    private static final long serialVersionUID = 1L;

    public MainWindow(String caption) {
        setCaption(caption);

        VerticalLayout verticalLayout = new VerticalLayout();
        TreeTable registrationUnitTable = new TreeTable();
        registrationUnitTable.setWidth("100%");
        verticalLayout.addComponent(registrationUnitTable);

        addComponent(verticalLayout);
    }
}
