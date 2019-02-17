package hr.udruga01.arhixml.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;

/**
 * This class holds the enumeration of {@link Maker} <code>makerId</code>
 * ids.
 * <p>
 * It is used to populate {@link ComboBox} component inside {@link Table} of
 * {@link Maker} objects. This way, {@link Form} automatically makes data
 * bounding to these objects.
 */
public class AvailableMakers {
    private static List<Maker> availableMakers;
    
    static {
        // FIXME Currently, we are creating a list of all available makers by
        // means of manually creating maker one by one hardcoded in this
        // class. This is not a good way for more reasons. First, it is tedious
        // to mantain this list because of possible schema changes. Second, the
        // list may contain false data because of human error. This should be
        // fixed by reading the list directly from the schema.
        availableMakers = new ArrayList<Maker>();

        Maker makerOne = ObjectFactory.createMaker();
        makerOne.setRoleId(1);
        makerOne.setLabel("stvaratelj");
        availableMakers.add(makerOne);
        
        Maker makerTwo = ObjectFactory.createMaker();
        makerTwo.setRoleId(2);
        makerTwo.setLabel("autor");
        availableMakers.add(makerTwo);
        
        Maker makerThree = ObjectFactory.createMaker();
        makerThree.setRoleId(3);
        makerThree.setLabel("primatelj pisma");
        availableMakers.add(makerThree);
        
        Maker makerFour = ObjectFactory.createMaker();
        makerFour.setRoleId(4);
        makerFour.setLabel("pošiljatelj");
        availableMakers.add(makerFour);
        
        Maker makerFive = ObjectFactory.createMaker();
        makerFive.setRoleId(5);
        makerFive.setLabel("pisar");
        availableMakers.add(makerFive);
        
        Maker makerSix = ObjectFactory.createMaker();
        makerSix.setRoleId(6);
        makerSix.setLabel("potpisnik");
        availableMakers.add(makerSix);
        
        Maker makerSeven = ObjectFactory.createMaker();
        makerSeven.setRoleId(7);
        makerSeven.setLabel("urednik");
        availableMakers.add(makerSeven);
        
        Maker makerEight = ObjectFactory.createMaker();
        makerEight.setRoleId(8);
        makerEight.setLabel("nakladnik");
        availableMakers.add(makerEight);
        
        Maker makerNine = ObjectFactory.createMaker();
        makerNine.setRoleId(9);
        makerNine.setLabel("prevoditelj");
        availableMakers.add(makerNine);
        
        Maker makerTen = ObjectFactory.createMaker();
        makerTen.setRoleId(10);
        makerTen.setLabel("kartograf");
        availableMakers.add(makerTen);
        
        Maker makerEleven = ObjectFactory.createMaker();
        makerEleven.setRoleId(11);
        makerEleven.setLabel("graver");
        availableMakers.add(makerEleven);
        
        Maker makerTwelve = ObjectFactory.createMaker();
        makerTwelve.setRoleId(12);
        makerTwelve.setLabel("bakrorezac");
        availableMakers.add(makerTwelve);
        
        Maker makerThirteen = ObjectFactory.createMaker();
        makerThirteen.setRoleId(13);
        makerThirteen.setLabel("litograf");
        availableMakers.add(makerThirteen);
        
        Maker makerFourteen = ObjectFactory.createMaker();
        makerFourteen.setRoleId(14);
        makerFourteen.setLabel("graver u kovini");
        availableMakers.add(makerFourteen);
        
        Maker makerFifteen = ObjectFactory.createMaker();
        makerFifteen.setRoleId(15);
        makerFifteen.setLabel("tiskar");
        availableMakers.add(makerFifteen);
        
        Maker makerSixteen = ObjectFactory.createMaker();
        makerSixteen.setRoleId(16);
        makerSixteen.setLabel("tiskar gravura");
        availableMakers.add(makerSixteen);
        
        Maker makerSeventeen = ObjectFactory.createMaker();
        makerSeventeen.setRoleId(17);
        makerSeventeen.setLabel("izvođač");
        availableMakers.add(makerSeventeen);
        
        Maker makerEighteen = ObjectFactory.createMaker();
        makerEighteen.setRoleId(18);
        makerEighteen.setLabel("fotograf");
        availableMakers.add(makerEighteen);
        
        Maker makerNineteen = ObjectFactory.createMaker();
        makerNineteen.setRoleId(19);
        makerNineteen.setLabel("drvorezac");
        availableMakers.add(makerNineteen);
        
        Maker makerTwenty = ObjectFactory.createMaker();
        makerTwenty.setRoleId(20);
        makerTwenty.setLabel("redatelj");
        availableMakers.add(makerTwenty);
        
        Maker makerTwentyOne = ObjectFactory.createMaker();
        makerTwentyOne.setRoleId(21);
        makerTwentyOne.setLabel("scenarist");
        availableMakers.add(makerTwentyOne);
        
        Maker makerTwentyTwo = ObjectFactory.createMaker();
        makerTwentyTwo.setRoleId(22);
        makerTwentyTwo.setLabel("prerađivać");
        availableMakers.add(makerTwentyTwo);
        
        Maker makerTwentyThree = ObjectFactory.createMaker();
        makerTwentyThree.setRoleId(23);
        makerTwentyThree.setLabel("autor predloška");
        availableMakers.add(makerTwentyThree);
        
        Maker makerTwentyFour = ObjectFactory.createMaker();
        makerTwentyFour.setRoleId(24);
        makerTwentyFour.setLabel("skladatelj");
        availableMakers.add(makerTwentyFour);
        
        Maker makerTwentyFive = ObjectFactory.createMaker();
        makerTwentyFive.setRoleId(25);
        makerTwentyFive.setLabel("dirigent");
        availableMakers.add(makerTwentyFive);
        
        Maker makerTwentySix = ObjectFactory.createMaker();
        makerTwentySix.setRoleId(26);
        makerTwentySix.setLabel("organizator izložbe");
        availableMakers.add(makerTwentySix);
        
        Maker makerTwentySeven = ObjectFactory.createMaker();
        makerTwentySeven.setRoleId(27);
        makerTwentySeven.setLabel("nesigurno autorstvo");
        availableMakers.add(makerTwentySeven);
        
        Maker makerTwentyEight = ObjectFactory.createMaker();
        makerTwentyEight.setRoleId(28);
        makerTwentyEight.setLabel("filmski montažer");
        availableMakers.add(makerTwentyEight);
        
        Maker makerTwentyNine = ObjectFactory.createMaker();
        makerTwentyNine.setRoleId(29);
        makerTwentyNine.setLabel("iluminator");
        availableMakers.add(makerTwentyNine);
        
        Maker makerThirty = ObjectFactory.createMaker();
        makerThirty.setRoleId(30);
        makerThirty.setLabel("ilustrator");
        availableMakers.add(makerThirty);
        
        Maker makerThirtyOne = ObjectFactory.createMaker();
        makerThirtyOne.setRoleId(31);
        makerThirtyOne.setLabel("pripovjedač");
        availableMakers.add(makerThirtyOne);
        
        Maker makerThirtyTwo = ObjectFactory.createMaker();
        makerThirtyTwo.setRoleId(32);
        makerThirtyTwo.setLabel("zaćetnik");
        availableMakers.add(makerThirtyTwo);
        
        Maker makerThirtyThree = ObjectFactory.createMaker();
        makerThirtyThree.setRoleId(33);
        makerThirtyThree.setLabel("drugo");
        availableMakers.add(makerThirtyThree);
        
        Maker makerThirtyFour = ObjectFactory.createMaker();
        makerThirtyFour.setRoleId(34);
        makerThirtyFour.setLabel("podnositelj zahtjeva za patent");
        availableMakers.add(makerThirtyFour);
        
        Maker makerThirtyFive = ObjectFactory.createMaker();
        makerThirtyFive.setRoleId(35);
        makerThirtyFive.setLabel("vlasnik patenta");
        availableMakers.add(makerThirtyFive);
        
        Maker makerThirtySix = ObjectFactory.createMaker();
        makerThirtySix.setRoleId(36);
        makerThirtySix.setLabel("producent");
        availableMakers.add(makerThirtySix);
        
        Maker makerThirtySeven = ObjectFactory.createMaker();
        makerThirtySeven.setRoleId(37);
        makerThirtySeven.setLabel("recenzent");
        availableMakers.add(makerThirtySeven);
        
        Maker makerThirtyEight = ObjectFactory.createMaker();
        makerThirtyEight.setRoleId(38);
        makerThirtyEight.setLabel("glumac");
        availableMakers.add(makerThirtyEight);
        
        Maker makerThirtyNine = ObjectFactory.createMaker();
        makerThirtyNine.setRoleId(39);
        makerThirtyNine.setLabel("aranžer");
        availableMakers.add(makerThirtyNine);
        
        Maker makerFourty = ObjectFactory.createMaker();
        makerFourty.setRoleId(40);
        makerFourty.setLabel("umjetnik");
        availableMakers.add(makerFourty);
        
        Maker makerFourtyOne = ObjectFactory.createMaker();
        makerFourtyOne.setRoleId(41);
        makerFourtyOne.setLabel("sakupljač");
        availableMakers.add(makerFourtyOne);
        
        Maker makerFourtyTwo = ObjectFactory.createMaker();
        makerFourtyTwo.setRoleId(43);
        makerFourtyTwo.setLabel("investitor");
        availableMakers.add(makerFourtyTwo);
        
        Maker makerFourtyThree = ObjectFactory.createMaker();
        makerFourtyThree.setRoleId(44);
        makerFourtyThree.setLabel("projektant");
        availableMakers.add(makerFourtyThree);
        
        Maker makerFourtyFour = ObjectFactory.createMaker();
        makerFourtyFour.setRoleId(45);
        makerFourtyFour.setLabel("destinatar");
        availableMakers.add(makerFourtyFour);
    }
    
    /**
     * Returns all available makers defined in schema.
     */
    public static List<Maker> getAvailableMakers() {
        return availableMakers;
    }
}
