package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TreeTable;

/**
 * {@link RegistrationUnitContainer} is the container for the {@link TreeTable}
 * component.
 * <p>
 * Basically, it is a {@link BeanItemContainer} which implements
 * {@link Container.Hierarchical}. This allows that our container maps to the
 * JavaBeans properties automatically and that our JavaBeans can have some kind
 * of hierarchy. In our case, JavaBeans are a collection of
 * {@link RegistrationUnit} objects.
 * <p>
 * The hierarchy is neccesery because each {@link RegistrationUnit} object can
 * have multiple of {@link RegistrationUnit} objects.
 */
class RegistrationUnitContainer extends BeanItemContainer<RegistrationUnit> implements Container.Hierarchical {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(RegistrationUnitContainer.class.getName());

    private Arhinet root;
    private static final int HIGEST_LEVEL_ID = 5;

    public RegistrationUnitContainer() {
        super(RegistrationUnit.class);
        logger.trace("Entering RegistrationUnitContainer()");

        root = new Arhinet();
        logger.trace("Exiting RegistrationUnitContainer()");
    }

    public RegistrationUnitContainer(Arhinet root) {
        super(RegistrationUnit.class);
        logger.trace("Entering RegistrationUnitContainer()");

        for (RegistrationUnit registrationUnit : root.getRegistrationUnits()) {
            addBean(registrationUnit);
        }

        logger.trace("Exiting RegistrationUnitContainer()");
    }

    @Override
    public BeanItem<RegistrationUnit> addBean(RegistrationUnit bean) {
        logger.trace("Entering addBean()");

        BeanItem<RegistrationUnit> addedBean = super.addBean(bean);

        List<RegistrationUnit> registrationUnits = bean.getRegistrationUnits();
        if (registrationUnits.size() > 0) {
            for (RegistrationUnit registrationUnit : registrationUnits) {
                addBean(registrationUnit);
            }
        }

        logger.trace("Exiting addBean()");

        return addedBean;
    }

    @Override
    public Collection<?> getChildren(Object itemId) {
        logger.trace("Entering getChildren()");
        List<RegistrationUnit> registrationUnits = ((RegistrationUnit) itemId).getRegistrationUnits();

        for (RegistrationUnit registrationUnit : registrationUnits) {
            registrationUnit.setParentRegistrationUnit((RegistrationUnit) itemId);
        }

        logger.trace("Exiting getChildren()");
        return registrationUnits;
    }

    @Override
    public Object getParent(Object itemId) {
        logger.trace("Entering getParent()");
        logger.trace("Exiting getParent()");
        return ((RegistrationUnit) itemId).getParentRegistrationUnit();
    }

    @Override
    public Collection<?> rootItemIds() {
        logger.trace("Entering rootItemIds()");
        logger.trace("Exiting rootItemIds()");
        return root.getRegistrationUnits();
    }

    @Override
    public boolean setParent(Object itemId, Object newParentId) throws UnsupportedOperationException {
        logger.trace("Entering setParent()");

        // First we should get a reference to the old parent.
        RegistrationUnit oldParentId = ((RegistrationUnit) itemId).getParentRegistrationUnit();

        if (oldParentId != null) {
            // The item has a parent which should be replaced with the new one.
            // But before we replace it with the new parent, we must remove the
            // item from the container.
            oldParentId.getRegistrationUnits().remove(itemId);
        } else {
            // The item has no parent. This means that this is the new item or
            // the item is on the root level.
            // Anyway, remove the item from the root level (if any).
            root.getRegistrationUnits().remove(itemId);
        }

        // Now it is the time to set the new parent for the item.
        ((RegistrationUnit) itemId).setParentRegistrationUnit((RegistrationUnit) newParentId);

        // After setting the parent, we must now add the item into container.
        if (newParentId == null) {
            root.getRegistrationUnits().add((RegistrationUnit) itemId);
        } else {
            ((RegistrationUnit) newParentId).getRegistrationUnits().add((RegistrationUnit) itemId);
        }

        // Container is changed, fire the event so that the table can update
        // itself.
        fireItemSetChange();

        logger.trace("Exiting setParent()");

        return true;
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        logger.trace("Entering areChildrenAllowed()");
        int levelId = ((RegistrationUnit) itemId).getLevelId();

        if (levelId == HIGEST_LEVEL_ID) {
            logger.trace("Exiting areChildrenAllowed()");
            return false;
        } else {
            logger.trace("Exiting areChildrenAllowed()");
            return true;
        }
    }

    @Override
    public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed) throws UnsupportedOperationException {
        logger.trace("Entering setChildrenAllowed()");
        logger.trace("Exiting setChildrenAllowed()");
        return true;
    }

    @Override
    public boolean isRoot(Object itemId) {
        logger.trace("Entering isRoot()");

        RegistrationUnit parentRegistrationUnit = ((RegistrationUnit) itemId).getParentRegistrationUnit();

        if (parentRegistrationUnit != null) {
            logger.trace("Exiting isRoot()");

            return false;
        } else {
            logger.trace("Exiting isRoot()");

            return true;
        }
    }

    @Override
    public boolean hasChildren(Object itemId) {
        logger.trace("Entering hasChildren()");
        logger.trace("Exiting hasChildren()");
        return false;
    }

    public Arhinet getData() {
        return root;
    }

    public void setData(Arhinet root) {
        logger.trace("Entering setData()");
        this.root = root;

        for (RegistrationUnit registrationUnit : root.getRegistrationUnits()) {
            addBean(registrationUnit);
        }

        logger.trace("Exiting setData()");
    }

    @Override
    public boolean removeItem(Object itemId) {
        removeRegistrationUnit(root.getRegistrationUnits(), itemId);

        return true;
    }

    private void removeRegistrationUnit(List<RegistrationUnit> list, Object itemId) {
        Iterator<RegistrationUnit> iterator = list.iterator();

        while (iterator.hasNext()) {
            RegistrationUnit item = (RegistrationUnit) iterator.next();

            if (item == itemId) {
                iterator.remove();
                super.removeItem(itemId);

                break;
            } else {
                removeRegistrationUnit(item.getRegistrationUnits(), itemId);
            }
        }
    }

    public void moveAfterSibling(Object itemId, Object siblingId) {
        // The itemId and siblingId have the same parent. So we need to find out
        // the parent of any of those items.
        RegistrationUnit parentRegistrationUnit = ((RegistrationUnit) itemId).getParentRegistrationUnit();
        // Now that we have a parent first we need to remove the old child which
        // is represented as itemId.
        List<RegistrationUnit> registrationUnitList = null;
        
        if (parentRegistrationUnit != null) {
            registrationUnitList = parentRegistrationUnit.getRegistrationUnits();
        } else {
            registrationUnitList = root.getRegistrationUnits();
        }
        
        registrationUnitList.remove(itemId);
        // Than we need to add the same child but after the sibling.
        int siblingIndex = registrationUnitList.indexOf(siblingId);
        registrationUnitList.add(siblingIndex + 1, (RegistrationUnit) itemId);
        
        fireItemSetChange();
    }
}
