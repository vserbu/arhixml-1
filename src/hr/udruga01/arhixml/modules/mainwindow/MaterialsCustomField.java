package hr.udruga01.arhixml.modules.mainwindow;

import java.util.ArrayList;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class MaterialsCustomField extends CustomField {
    private static final long serialVersionUID = 1L;

    private static final int NUMBER_OF_VISIBLE_ROWS = 5;
    private VerticalLayout layout;
    private Table materialsList;

    public MaterialsCustomField(String caption) {
        setCaption(caption);

        layout = new VerticalLayout();
        materialsList = new Table();
        materialsList.setSelectable(true);
        materialsList.setMultiSelect(true);
        materialsList.setWidth("10%");
        materialsList.setImmediate(true);
        materialsList.setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        materialsList.setPageLength(NUMBER_OF_VISIBLE_ROWS);
        layout.addComponent(materialsList);

        setCompositionRoot(layout);
    }

    public void setContainerDataSource(Container container) {
        materialsList.setContainerDataSource(container);
    }

    @Override
    public Class<?> getType() {
        return ArrayList.class;
    }
}
