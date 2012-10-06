package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;
import hr.udruga01.arhixml.datamodel.RegistrationUnit;

import java.util.Collection;
import java.util.List;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TreeTable;

/**
 * {@link RegistrationUnitContainer} is the container for the {@link TreeTable} component.
 * <p>
 * Basically, it is a {@link BeanItemContainer} which implements {@link Container.Hierarchical}.
 * This allows that our container maps to the JavaBeans properties automatically and that our JavaBeans can have some kind of hierarchy.
 * In our case, JavaBeans are a collection of {@link RegistrationUnit} objects.
 * <p>
 * The hierarchy is neccesery because each {@link RegistrationUnit} object can have multiple of {@link RegistrationUnit} objects. 
 */
public class RegistrationUnitContainer extends BeanItemContainer<RegistrationUnit> implements Container.Hierarchical {
    private static final long serialVersionUID = 1L;
    
    private Arhinet root;
    private static final int HIGEST_LEVEL_ID = 5;

    public RegistrationUnitContainer() {
        super(RegistrationUnit.class);
        
        root = new Arhinet();
    }
    
    public RegistrationUnitContainer(Arhinet root) {
        super(RegistrationUnit.class);
        
        for (RegistrationUnit registrationUnit : root.getRegistrationUnits()) {
            addBean(registrationUnit);
        }
    }

    @Override
    public BeanItem<RegistrationUnit> addBean(RegistrationUnit bean) {
        BeanItem<RegistrationUnit> addedBean = super.addBean(bean);
        
        List<RegistrationUnit> registrationUnits = bean.getRegistrationUnits();
        if (registrationUnits.size() > 0) {
            for (RegistrationUnit registrationUnit : registrationUnits) {
                addBean(registrationUnit);
            }
        }
        
        return addedBean;
    }
    
    @Override
    public Collection<?> getChildren(Object itemId) {
        List<RegistrationUnit> registrationUnits = ((RegistrationUnit) itemId).getRegistrationUnits();
        
        for(RegistrationUnit registrationUnit : registrationUnits) {
            registrationUnit.setParentRegistrationUnit((RegistrationUnit) itemId);
        }
        
        return registrationUnits;
    }

    @Override
    public Object getParent(Object itemId) {
        return ((RegistrationUnit) itemId).getParentRegistrationUnit();
    }

    @Override
    public Collection<?> rootItemIds() {
        return root.getRegistrationUnits();
    }

    @Override
    public boolean setParent(Object itemId, Object newParentId) throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean areChildrenAllowed(Object itemId) {
        int levelId = ((RegistrationUnit) itemId).getLevelId();
        
        if (levelId == HIGEST_LEVEL_ID) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean setChildrenAllowed(Object itemId, boolean areChildrenAllowed) throws UnsupportedOperationException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isRoot(Object itemId) {
        int levelId = ((RegistrationUnit) itemId).getLevelId();
        
        if (levelId == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasChildren(Object itemId) {
        // TODO Auto-generated method stub
        return false;
    }

    public void setData(Arhinet root) {
        this.root = root;
        
        for (RegistrationUnit registrationUnit : root.getRegistrationUnits()) {
            addBean(registrationUnit);
        }
    }
}
