package hr.udruga01.arhixml.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;

/**
 * This class holds the enumeration of {@link ArchiveUnit} <code>measurementUnitId</code>
 * ids.
 * <p>
 * It is used to populate {@link ComboBox} component inside {@link Table} of
 * {@link Label} objects. This way, {@link Form} automatically makes data
 * bounding to these objects.
 */
public class AvailableArchiveUnits {
    private static List<ArchiveUnit> availableArchiveUnits;
    
    static {
        // FIXME Currently, we are creating a list of all available archive units by
        // means of manually creating archive unit one by one hardcoded in this
        // class. This is not a good way for more reasons. First, it is tedious
        // to mantain this list because of possible schema changes. Second, the
        // list may contain false data because of human error. This should be
        // fixed by reading the list directly from the schema.
        availableArchiveUnits = new ArrayList<ArchiveUnit>();

        ArchiveUnit archiveUnitOne = ObjectFactory.createArchiveUnit();
        archiveUnitOne.setMeasurementUnitId(1);
        archiveUnitOne.setLabel("metar");
        availableArchiveUnits.add(archiveUnitOne);
        
        ArchiveUnit archiveUnitTwo = ObjectFactory.createArchiveUnit();
        archiveUnitTwo.setMeasurementUnitId(3);
        archiveUnitTwo.setLabel("komad");
        availableArchiveUnits.add(archiveUnitTwo);
        
        ArchiveUnit archiveUnitThree = ObjectFactory.createArchiveUnit();
        archiveUnitThree.setMeasurementUnitId(5);
        archiveUnitThree.setLabel("minuta");
        availableArchiveUnits.add(archiveUnitThree);
        
        ArchiveUnit archiveUnitFour = ObjectFactory.createArchiveUnit();
        archiveUnitFour.setMeasurementUnitId(6);
        archiveUnitFour.setLabel("snimka");
        availableArchiveUnits.add(archiveUnitFour);
        
        ArchiveUnit archiveUnitFive = ObjectFactory.createArchiveUnit();
        archiveUnitFive.setMeasurementUnitId(267);
        archiveUnitFive.setLabel("milimetar");
        availableArchiveUnits.add(archiveUnitFive);
        
        ArchiveUnit archiveUnitSix = ObjectFactory.createArchiveUnit();
        archiveUnitSix.setMeasurementUnitId(268);
        archiveUnitSix.setLabel("centimetar");
        availableArchiveUnits.add(archiveUnitSix);
        
        ArchiveUnit archiveUnitSeven = ObjectFactory.createArchiveUnit();
        archiveUnitSeven.setMeasurementUnitId(269);
        archiveUnitSeven.setLabel("decimetar");
        availableArchiveUnits.add(archiveUnitSeven);
        
        ArchiveUnit archiveUnitEight = ObjectFactory.createArchiveUnit();
        archiveUnitEight.setMeasurementUnitId(270);
        archiveUnitEight.setLabel("metar");
        availableArchiveUnits.add(archiveUnitEight);
        
        ArchiveUnit archiveUnitNine = ObjectFactory.createArchiveUnit();
        archiveUnitNine.setMeasurementUnitId(681);
        archiveUnitNine.setLabel("piksel");
        availableArchiveUnits.add(archiveUnitNine);
    }
    
    /**
     * Returns all available archive units defined in schema.
     */
    public static List<ArchiveUnit> getAvailableArchiveUnits() {
        return availableArchiveUnits;
    }
}
