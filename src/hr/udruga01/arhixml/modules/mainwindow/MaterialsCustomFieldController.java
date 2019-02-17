package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;

public class MaterialsCustomFieldController implements Handler, ItemClickListener {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MaterialsCustomFieldController.class.getName());

    private MaterialsCustomField materialsCustomField;

    private static Action actionAdd = new Action("Dodaj");
    private static Action actionRemove = new Action("Obriši");

    public MaterialsCustomFieldController(MaterialsCustomField materialsCustomField) {
        logger.trace("Entering MaterialsCustomFieldController()");

        this.materialsCustomField = materialsCustomField;

        actionAdd.setIcon(new ThemeResource("icons/add.png"));
        actionRemove.setIcon(new ThemeResource("icons/remove.png"));

        logger.trace("Exiting MaterialsCustomFieldController()");
    }

    /**
     * This method is automatically called by the framework for every item in a
     * table of {@link Material} objects.
     * <p>
     * This is to ensure that the correct menu item will appear in context menu
     * for each item in the table.
     * <p>
     * The sad thing is that we don't need the above functionality. Instead, we
     * need to show or hide context menu item depending of table selection
     * state. For example, if there is one or more items selected on the table,
     * the "Remove" item must be presented in context menu. Another case is if
     * there are no selected items on the table and user triggers the context
     * menu the "Remove" item must be removed from the context menu.
     */
    @Override
    public Action[] getActions(Object target, Object sender) {
        logger.trace("Entering getActions()");

        Action[] actions = new Action[] { actionAdd, actionRemove };

        logger.trace("Exiting getActions()");

        return actions;
    }

    /**
     * This method is automatically called by the framework when user chooses
     * one of the context menu items.
     */
    @Override
    public void handleAction(Action action, Object sender, Object target) {
        logger.trace("Entering handleAction()");

        if (action == actionRemove) {
            materialsCustomField.removeSelectedItems();
        }

        if (action == actionAdd) {
            materialsCustomField.addNewItem();
        }

        logger.trace("Exiting handleAction()");
    }

    /**
     * This method is automatically called by the framework when user select an
     * item from the table which holds {@link Material} objects.
     * <p>
     * Method will ensure that when the user triggers the context menu on some
     * item, the item will also be selected prior to showing the context menu.
     */
    @Override
    public void itemClick(ItemClickEvent event) {
        logger.trace("Entering itemClick()");

        if (event.getButton() == ItemClickEvent.BUTTON_RIGHT) {
            materialsCustomField.selectTableItem(event.getItemId());
        }

        logger.trace("Exiting itemClick()");
    }
}
