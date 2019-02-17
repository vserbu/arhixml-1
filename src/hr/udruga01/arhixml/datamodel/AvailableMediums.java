package hr.udruga01.arhixml.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;

/**
 * This class holds the enumeration of {@link Medium} <code>mediumTypeId</code>
 * ids.
 * <p>
 * It is used to populate {@link ComboBox} component inside {@link Table} of
 * {@link Medium} objects. This way, {@link Form} automatically makes data
 * bounding to these objects.
 */
public class AvailableMediums {
    private static List<Medium> availableMediums;

    static {
        // FIXME Currently, we are creating a list of all available mediums by
        // means of manually creating medium one by one hardcoded in this
        // class. This is not a good way for more reasons. First, it is tedious
        // to mantain this list because of possible schema changes. Second, the
        // list may contain false data because of human error. This should be
        // fixed by reading the list directly from the schema.
        availableMediums = new ArrayList<Medium>();

        Medium mediumOne = ObjectFactory.createMedium();
        mediumOne.setMediumTypeId(1);
        mediumOne.setLabel("Papir");
        availableMediums.add(mediumOne);
        
        Medium mediumTwo = ObjectFactory.createMedium();
        mediumTwo.setMediumTypeId(2);
        mediumTwo.setLabel("Pergamena");
        availableMediums.add(mediumTwo);
        
        Medium mediumThree = ObjectFactory.createMedium();
        mediumThree.setMediumTypeId(3);
        mediumThree.setLabel("Mikrooblik");
        availableMediums.add(mediumThree);
        
        Medium mediumFour = ObjectFactory.createMedium();
        mediumFour.setMediumTypeId(3);
        mediumFour.setLabel("Fotografija");
        availableMediums.add(mediumFour);
        
        Medium mediumFive = ObjectFactory.createMedium();
        mediumFive.setMediumTypeId(7);
        mediumFive.setLabel("Dijapozitiv");
        availableMediums.add(mediumFive);
        
        Medium mediumSix = ObjectFactory.createMedium();
        mediumSix.setMediumTypeId(8);
        mediumSix.setLabel("Filmska vrpca");
        availableMediums.add(mediumSix);
        
        Medium mediumSeven = ObjectFactory.createMedium();
        mediumSeven.setMediumTypeId(9);
        mediumSeven.setLabel("Magnetofonska traka");
        availableMediums.add(mediumSeven);
        
        Medium mediumEight = ObjectFactory.createMedium();
        mediumEight.setMediumTypeId(10);
        mediumEight.setLabel("Digitalni medij");
        availableMediums.add(mediumEight);
        
        Medium mediumNine = ObjectFactory.createMedium();
        mediumNine.setMediumTypeId(11);
        mediumNine.setLabel("Video kazeta");
        availableMediums.add(mediumNine);
        
        Medium mediumTen = ObjectFactory.createMedium();
        mediumTen.setMediumTypeId(12);
        mediumTen.setLabel("Audio kazeta");
        availableMediums.add(mediumTen);
        
        Medium mediumEleven = ObjectFactory.createMedium();
        mediumEleven.setMediumTypeId(13);
        mediumEleven.setLabel("Staklo");
        availableMediums.add(mediumEleven);
        
        Medium mediumTwelve = ObjectFactory.createMedium();
        mediumTwelve.setMediumTypeId(14);
        mediumTwelve.setLabel("Predmet");
        availableMediums.add(mediumTwelve);
        
        Medium mediumThirteen = ObjectFactory.createMedium();
        mediumThirteen.setMediumTypeId(15);
        mediumThirteen.setLabel("Gramofonska ploča");
        availableMediums.add(mediumThirteen);
        
        Medium mediumFourteen = ObjectFactory.createMedium();
        mediumFourteen.setMediumTypeId(16);
        mediumFourteen.setLabel("Platno");
        availableMediums.add(mediumFourteen);
    }

    /**
     * Returns all available materials defined in schema.
     */
    public static List<Medium> getAvailableMediums() {
        return availableMediums;
    }
}
