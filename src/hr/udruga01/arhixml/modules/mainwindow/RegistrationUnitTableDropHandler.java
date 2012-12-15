package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.Collection;
import java.util.Iterator;

import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.terminal.gwt.client.ui.dd.VerticalDropLocation;
import com.vaadin.ui.AbstractSelect.AbstractSelectTargetDetails;
import com.vaadin.ui.TreeTable;

public class RegistrationUnitTableDropHandler implements DropHandler {
    private static final long serialVersionUID = 1L;

    @Override
    public AcceptCriterion getAcceptCriterion() {
        return AcceptAll.get();
    }

    @Override
    public void drop(DragAndDropEvent event) {
        // Getting selected items which needs to be droped.
        DataBoundTransferable transferable = (DataBoundTransferable) event.getTransferable();
        TreeTable registrationUnitTable = (TreeTable) transferable.getSourceComponent();
        @SuppressWarnings("unchecked")
        Collection<RegistrationUnit> registrationUnitCollection = (Collection<RegistrationUnit>) registrationUnitTable.getValue();
        Iterator<RegistrationUnit> it = registrationUnitCollection.iterator();

        // Getting the referenced item on which the user droped his selection.
        AbstractSelectTargetDetails targetDetails = (AbstractSelectTargetDetails) event.getTargetDetails();
        RegistrationUnit targetRegistrationUnit = (RegistrationUnit) targetDetails.getItemIdOver();

        if (registrationUnitCollection.contains(targetRegistrationUnit)) {
            // If the draged collection contains the target item than this is
            // the invalid drop. We should just ignore this drop.

            return;
        }

        // Check that the target is not in the subtree of the dragged items
        // itself
        for (Object itemId = targetRegistrationUnit; itemId != null; itemId = registrationUnitTable.getParent(itemId)) {
            if (registrationUnitCollection.contains(itemId)) {
                return;
            }
        }

        // Check to see where the user specifically droped his items relative to
        // the referenced item.
        VerticalDropLocation dropLocation = targetDetails.getDropLocation();

        if (dropLocation == VerticalDropLocation.TOP) {
            // Drop happend above the referenced item.
            // Insert draged items just above the referenced item.

            RegistrationUnitContainer container = (RegistrationUnitContainer) transferable.getSourceContainer();

            while (it.hasNext()) {
                RegistrationUnit registrationUnit = (RegistrationUnit) it.next();
                RegistrationUnit parentRegistrationUnit = targetRegistrationUnit.getParentRegistrationUnit();
                // Set a new parent for the draged item.
                registrationUnitTable.setParent(registrationUnit, parentRegistrationUnit);
                container.moveAfterSibling(registrationUnit, targetRegistrationUnit);
                container.moveAfterSibling(targetRegistrationUnit, registrationUnit);
            }

        } else if (dropLocation == VerticalDropLocation.MIDDLE) {
            // Drop happend exactlly on the referenced item.
            // Insert draged items into referenced item.

            while (it.hasNext()) {
                // Set each draged item a new parent.
                RegistrationUnit registrationUnit = (RegistrationUnit) it.next();
                registrationUnitTable.setParent(registrationUnit, targetRegistrationUnit);
            }

        } else if (dropLocation == VerticalDropLocation.BOTTOM) {
            // Drop happend bellow the referenced item.
            // Insert draged items just bellow the referenced item.

            RegistrationUnitContainer container = (RegistrationUnitContainer) transferable.getSourceContainer();

            while (it.hasNext()) {
                RegistrationUnit registrationUnit = (RegistrationUnit) it.next();
                RegistrationUnit parentRegistrationUnit = targetRegistrationUnit.getParentRegistrationUnit();
                // Set a new parent for the draged item.
                registrationUnitTable.setParent(registrationUnit, parentRegistrationUnit);
                // Move the item just bellow the sibling.
                container.moveAfterSibling(registrationUnit, targetRegistrationUnit);
            }
        }
    }
}
