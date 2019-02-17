package hr.udruga01.arhixml.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;

/**
 * This class holds the enumeration of {@link TechnicalUnit} <code>technicalUnitTypeId</code>
 * ids.
 * <p>
 * It is used to populate {@link ComboBox} component inside {@link Table} of
 * {@link TechnicalUnit} objects. This way, {@link Form} automatically makes data
 * bounding to these objects.
 */
public class AvailableTechnicalUnits {
    private static List<TechnicalUnit> availableTechnicalUnits;
    
    static {
        // FIXME Currently, we are creating a list of all available technical units by
        // means of manually creating technical unit one by one hardcoded in this
        // class. This is not a good way for more reasons. First, it is tedious
        // to mantain this list because of possible schema changes. Second, the
        // list may contain false data because of human error. This should be
        // fixed by reading the list directly from the schema.
        availableTechnicalUnits = new ArrayList<TechnicalUnit>();

        TechnicalUnit technicalUnitOne = ObjectFactory.createTechnicalUnit();
        technicalUnitOne.setTechnicalUnitTypeId(2);
        technicalUnitOne.setLabel("album");
        availableTechnicalUnits.add(technicalUnitOne);
        
        TechnicalUnit technicalUnitTwo = ObjectFactory.createTechnicalUnit();
        technicalUnitTwo.setTechnicalUnitTypeId(4);
        technicalUnitTwo.setLabel("ambalažna kutija");
        availableTechnicalUnits.add(technicalUnitTwo);
        
        TechnicalUnit technicalUnitThree = ObjectFactory.createTechnicalUnit();
        technicalUnitThree.setTechnicalUnitTypeId(7);
        technicalUnitThree.setLabel("audio kazeta");
        availableTechnicalUnits.add(technicalUnitThree);
        
        TechnicalUnit technicalUnitFour = ObjectFactory.createTechnicalUnit();
        technicalUnitFour.setTechnicalUnitTypeId(10);
        technicalUnitFour.setLabel("bilježnica");
        availableTechnicalUnits.add(technicalUnitFour);
        
        TechnicalUnit technicalUnitFive = ObjectFactory.createTechnicalUnit();
        technicalUnitFive.setTechnicalUnitTypeId(12);
        technicalUnitFive.setLabel("komad");
        availableTechnicalUnits.add(technicalUnitFive);
        
        TechnicalUnit technicalUnitSix = ObjectFactory.createTechnicalUnit();
        technicalUnitSix.setTechnicalUnitTypeId(16);
        technicalUnitSix.setLabel("CD");
        availableTechnicalUnits.add(technicalUnitSix);
        
        TechnicalUnit technicalUnitSeven = ObjectFactory.createTechnicalUnit();
        technicalUnitSeven.setTechnicalUnitTypeId(22);
        technicalUnitSeven.setLabel("disk");
        availableTechnicalUnits.add(technicalUnitSeven);
        
        TechnicalUnit technicalUnitEight = ObjectFactory.createTechnicalUnit();
        technicalUnitEight.setTechnicalUnitTypeId(23);
        technicalUnitEight.setLabel("fascikl");
        availableTechnicalUnits.add(technicalUnitEight);
        
        TechnicalUnit technicalUnitNine = ObjectFactory.createTechnicalUnit();
        technicalUnitNine.setTechnicalUnitTypeId(24);
        technicalUnitNine.setLabel("mikrooblik II");
        availableTechnicalUnits.add(technicalUnitNine);
        
        TechnicalUnit technicalUnitTen = ObjectFactory.createTechnicalUnit();
        technicalUnitTen.setTechnicalUnitTypeId(26);
        technicalUnitTen.setLabel("kutija");
        availableTechnicalUnits.add(technicalUnitTen);
        
        TechnicalUnit technicalUnitEleven = ObjectFactory.createTechnicalUnit();
        technicalUnitEleven.setTechnicalUnitTypeId(27);
        technicalUnitEleven.setLabel("DVD");
        availableTechnicalUnits.add(technicalUnitEleven);
        
        TechnicalUnit technicalUnitTwelve = ObjectFactory.createTechnicalUnit();
        technicalUnitTwelve.setTechnicalUnitTypeId(32);
        technicalUnitTwelve.setLabel("film");
        availableTechnicalUnits.add(technicalUnitTwelve);
        
        TechnicalUnit technicalUnitThirteen = ObjectFactory.createTechnicalUnit();
        technicalUnitThirteen.setTechnicalUnitTypeId(33);
        technicalUnitThirteen.setLabel("filmski svitak");
        availableTechnicalUnits.add(technicalUnitThirteen);
        
        TechnicalUnit technicalUnitFourteen = ObjectFactory.createTechnicalUnit();
        technicalUnitFourteen.setTechnicalUnitTypeId(36);
        technicalUnitFourteen.setLabel("foto-album");
        availableTechnicalUnits.add(technicalUnitFourteen);
        
        TechnicalUnit technicalUnitFifteen = ObjectFactory.createTechnicalUnit();
        technicalUnitFifteen.setTechnicalUnitTypeId(39);
        technicalUnitFifteen.setLabel("gramofonska plo�a");
        availableTechnicalUnits.add(technicalUnitFifteen);
        
        TechnicalUnit technicalUnitSixteen = ObjectFactory.createTechnicalUnit();
        technicalUnitSixteen.setTechnicalUnitTypeId(42);
        technicalUnitSixteen.setLabel("kartoteka");
        availableTechnicalUnits.add(technicalUnitSixteen);
        
        TechnicalUnit technicalUnitSeventeen = ObjectFactory.createTechnicalUnit();
        technicalUnitSeventeen.setTechnicalUnitTypeId(43);
        technicalUnitSeventeen.setLabel("knjiga");
        availableTechnicalUnits.add(technicalUnitSeventeen);
        
        TechnicalUnit technicalUnitEighteen = ObjectFactory.createTechnicalUnit();
        technicalUnitEighteen.setTechnicalUnitTypeId(48);
        technicalUnitEighteen.setLabel("omotnica");
        availableTechnicalUnits.add(technicalUnitEighteen);
        
        TechnicalUnit technicalUnitNineteen = ObjectFactory.createTechnicalUnit();
        technicalUnitNineteen.setTechnicalUnitTypeId(56);
        technicalUnitNineteen.setLabel("ladica");
        availableTechnicalUnits.add(technicalUnitNineteen);
        
        TechnicalUnit technicalUnitTwenty = ObjectFactory.createTechnicalUnit();
        technicalUnitTwenty.setTechnicalUnitTypeId(61);
        technicalUnitTwenty.setLabel("list");
        availableTechnicalUnits.add(technicalUnitTwenty);
        
        TechnicalUnit technicalUnitTwentyOne = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyOne.setTechnicalUnitTypeId(63);
        technicalUnitTwentyOne.setLabel("magnetofonska traka");
        availableTechnicalUnits.add(technicalUnitTwentyOne);
        
        TechnicalUnit technicalUnitTwentyTwo = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyTwo.setTechnicalUnitTypeId(67);
        technicalUnitTwentyTwo.setLabel("mapa");
        availableTechnicalUnits.add(technicalUnitTwentyTwo);
        
        TechnicalUnit technicalUnitTwentyThree = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyThree.setTechnicalUnitTypeId(69);
        technicalUnitTwentyThree.setLabel("mikrofilmski svitak");
        availableTechnicalUnits.add(technicalUnitTwentyThree);
        
        TechnicalUnit technicalUnitTwentyFour = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyFour.setTechnicalUnitTypeId(74);
        technicalUnitTwentyFour.setLabel("neg.");
        availableTechnicalUnits.add(technicalUnitTwentyFour);
        
        TechnicalUnit technicalUnitTwentyFive = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyFive.setTechnicalUnitTypeId(80);
        technicalUnitTwentyFive.setLabel("ormar*");
        availableTechnicalUnits.add(technicalUnitTwentyFive);
        
        TechnicalUnit technicalUnitTwentySix = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentySix.setTechnicalUnitTypeId(91);
        technicalUnitTwentySix.setLabel("registrator");
        availableTechnicalUnits.add(technicalUnitTwentySix);
        
        TechnicalUnit technicalUnitTwentySeven = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentySeven.setTechnicalUnitTypeId(94);
        technicalUnitTwentySeven.setLabel("svitak*");
        availableTechnicalUnits.add(technicalUnitTwentySeven);
        
        TechnicalUnit technicalUnitTwentyEight = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyEight.setTechnicalUnitTypeId(97);
        technicalUnitTwentyEight.setLabel("sanduk*");
        availableTechnicalUnits.add(technicalUnitTwentyEight);
        
        TechnicalUnit technicalUnitTwentyNine = ObjectFactory.createTechnicalUnit();
        technicalUnitTwentyNine.setTechnicalUnitTypeId(105);
        technicalUnitTwentyNine.setLabel("svezak");
        availableTechnicalUnits.add(technicalUnitTwentyNine);
        
        TechnicalUnit technicalUnitThirty = ObjectFactory.createTechnicalUnit();
        technicalUnitThirty.setTechnicalUnitTypeId(108);
        technicalUnitThirty.setLabel("sve�anj");
        availableTechnicalUnits.add(technicalUnitThirty);
        
        TechnicalUnit technicalUnitThirtyOne = ObjectFactory.createTechnicalUnit();
        technicalUnitThirtyOne.setTechnicalUnitTypeId(118);
        technicalUnitThirtyOne.setLabel("vrpca");
        availableTechnicalUnits.add(technicalUnitThirtyOne);
        
        TechnicalUnit technicalUnitThirtyTwo = ObjectFactory.createTechnicalUnit();
        technicalUnitThirtyTwo.setTechnicalUnitTypeId(121);
        technicalUnitThirtyTwo.setLabel("video kazeta");
        availableTechnicalUnits.add(technicalUnitThirtyTwo);
        
        TechnicalUnit technicalUnitThirtyThree = ObjectFactory.createTechnicalUnit();
        technicalUnitThirtyThree.setTechnicalUnitTypeId(122);
        technicalUnitThirtyThree.setLabel("vre�ica");
        availableTechnicalUnits.add(technicalUnitThirtyThree);
    }

    /**
     * Returns all available technical units defined in schema.
     */
    public static List<TechnicalUnit> getAvailableTechnicalUnits() {
        return availableTechnicalUnits;
    }
}
