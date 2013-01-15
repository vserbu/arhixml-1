package hr.udruga01.arhixml.modules.mainwindow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

public class RegistrationUnitForm extends Form {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(RegistrationUnitForm.class.getName());

    private VerticalLayout layout;
    private GridLayout headerLayout;
    private GridLayout idsLayout;
    private GridLayout notesLayout;
    private GridLayout technicalDataLayout;
    
    public RegistrationUnitForm() {
        logger.trace("Entering RegistrationUnitForm()");
        
        layout = new VerticalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);
        
        headerLayout = new GridLayout(2, 1);
        headerLayout.setColumnExpandRatio(0, 0.3f);
        headerLayout.setColumnExpandRatio(1, 0.7f);
        headerLayout.setWidth("100%");
        headerLayout.setSpacing(true);
        layout.addComponent(headerLayout);
        
        idsLayout = new GridLayout(5, 1);
        idsLayout.setColumnExpandRatio(0, 0.2f);
        idsLayout.setColumnExpandRatio(1, 0.2f);
        idsLayout.setColumnExpandRatio(2, 0.2f);
        idsLayout.setColumnExpandRatio(3, 0.2f);
        idsLayout.setColumnExpandRatio(4, 0.2f);
        idsLayout.setWidth("100%");
        idsLayout.setSpacing(true);
        layout.addComponent(idsLayout);
        
        notesLayout = new GridLayout(1, 2);
        notesLayout.setWidth("100%");
        notesLayout.setSpacing(true);
        layout.addComponent(notesLayout);
        
        technicalDataLayout = new GridLayout(3, 1);
        technicalDataLayout.setColumnExpandRatio(0, 0.33f);
        technicalDataLayout.setColumnExpandRatio(1, 0.33f);
        technicalDataLayout.setColumnExpandRatio(2, 0.33f);
        technicalDataLayout.setWidth("100%");
        technicalDataLayout.setSpacing(true);
        layout.addComponent(technicalDataLayout);
        
        setLayout(layout);
        
        logger.trace("Exiting RegistrationUnitForm()");
    }
    
    @Override
    protected void attachField(Object propertyId, Field field) {
        logger.trace("Entering attachField()");
        
        if ("name".equals(propertyId)) {
            headerLayout.addComponent(field, 0, 0);
        } else if ("contents".equals(propertyId)) {
            headerLayout.addComponent(field, 1, 0);
        } else if ("levelId".equals(propertyId)) {
            idsLayout.addComponent(field, 0, 0);
        } else if ("signature".equals(propertyId)) {
            idsLayout.addComponent(field, 1, 0);
        } else if ("holderId".equals(propertyId)) {
            idsLayout.addComponent(field, 2, 0);
        } else if ("yearFrom".equals(propertyId)) {
            idsLayout.addComponent(field, 3, 0);
        } else if ("yearTo".equals(propertyId)) {
            idsLayout.addComponent(field, 4, 0);
        } else if ("note".equals(propertyId)) {
            notesLayout.addComponent(field, 0, 0);
        } else if ("timePeriodNote".equals(propertyId)) {
            notesLayout.addComponent(field, 0, 1);
        } else if ("materials".equals(propertyId)) {
            technicalDataLayout.addComponent(field, 0, 0);
        } else if ("mediums".equals(propertyId)) {
            technicalDataLayout.addComponent(field, 1, 0);
        } else if ("labels".equals(propertyId)) {
            technicalDataLayout.addComponent(field, 2, 0);
        }
        
        logger.trace("Exiting attachField()");
    }
}
