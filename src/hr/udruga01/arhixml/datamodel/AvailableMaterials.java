package hr.udruga01.arhixml.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;

/**
 * This class holds the enumeration of {@link Material} <code>subtypeId</code>
 * ids.
 * <p>
 * It is used to populate {@link ComboBox} component inside {@link Table} of
 * {@link Material} objects. This way, {@link Form} automatically makes data
 * bounding to these objects.
 */
public class AvailableMaterials {
    private static List<Material> availableMaterials;

    static {
        // FIXME Currently, we are creating a list of all available materials by
        // means of manually creating material one by one hardcoded in this
        // class. This is not a good way for more reasons. First, it is tedious
        // to mantain this list because of possible schema changes. Second, the
        // list may contain false data because of human error. This should be
        // fixed by reading the list directly from the schema.
        availableMaterials = new ArrayList<Material>();

        Material materialOne = ObjectFactory.createMaterial();
        materialOne.setSubtypeId(1);
        materialOne.setLabel("Spis");
        availableMaterials.add(materialOne);

        Material materialTwo = ObjectFactory.createMaterial();
        materialTwo.setSubtypeId(2);
        materialTwo.setLabel("Isprava");
        availableMaterials.add(materialTwo);

        Material materialThree = ObjectFactory.createMaterial();
        materialThree.setSubtypeId(3);
        materialThree.setLabel("Rukopis");
        availableMaterials.add(materialThree);

        Material materialFour = ObjectFactory.createMaterial();
        materialFour.setSubtypeId(4);
        materialFour.setLabel("Knjiga");
        availableMaterials.add(materialFour);

        Material materialFive = ObjectFactory.createMaterial();
        materialFive.setSubtypeId(5);
        materialFive.setLabel("Crtež");
        availableMaterials.add(materialFive);

        Material materialSix = ObjectFactory.createMaterial();
        materialSix.setSubtypeId(6);
        materialSix.setLabel("Fototisak");
        availableMaterials.add(materialSix);

        Material materialSeven = ObjectFactory.createMaterial();
        materialSeven.setSubtypeId(7);
        materialSeven.setLabel("Plakat");
        availableMaterials.add(materialSeven);

        Material materialEight = ObjectFactory.createMaterial();
        materialEight.setSubtypeId(8);
        materialEight.setLabel("Razglednica");
        availableMaterials.add(materialEight);

        Material materialNine = ObjectFactory.createMaterial();
        materialNine.setSubtypeId(9);
        materialNine.setLabel("Slika");
        availableMaterials.add(materialNine);

        Material materialTen = ObjectFactory.createMaterial();
        materialTen.setSubtypeId(10);
        materialTen.setLabel("Otisak");
        availableMaterials.add(materialTen);

        Material materialEleven = ObjectFactory.createMaterial();
        materialEleven.setSubtypeId(11);
        materialEleven.setLabel("Zemljopisna karta");
        availableMaterials.add(materialEleven);

        Material materialTwelve = ObjectFactory.createMaterial();
        materialTwelve.setSubtypeId(12);
        materialTwelve.setLabel("Atlas");
        availableMaterials.add(materialTwelve);

        Material materialThirteen = ObjectFactory.createMaterial();
        materialThirteen.setSubtypeId(13);
        materialThirteen.setLabel("Dijagram");
        availableMaterials.add(materialThirteen);

        Material materialFourteen = ObjectFactory.createMaterial();
        materialFourteen.setSubtypeId(14);
        materialFourteen.setLabel("Globus");
        availableMaterials.add(materialFourteen);

        Material materialFifteen = ObjectFactory.createMaterial();
        materialFifteen.setSubtypeId(15);
        materialFifteen.setLabel("Model");
        availableMaterials.add(materialFifteen);

        Material materialSixteen = ObjectFactory.createMaterial();
        materialSixteen.setSubtypeId(16);
        materialSixteen.setLabel("Prezentacijski list");
        availableMaterials.add(materialSixteen);

        Material materialSeventeen = ObjectFactory.createMaterial();
        materialSeventeen.setSubtypeId(17);
        materialSeventeen.setLabel("Skica");
        availableMaterials.add(materialSeventeen);

        Material materialEighteen = ObjectFactory.createMaterial();
        materialEighteen.setSubtypeId(18);
        materialEighteen.setLabel("Pogled");
        availableMaterials.add(materialEighteen);

        Material materialNineteen = ObjectFactory.createMaterial();
        materialNineteen.setSubtypeId(19);
        materialNineteen.setLabel("Plan");
        availableMaterials.add(materialNineteen);

        Material materialTwenty = ObjectFactory.createMaterial();
        materialTwenty.setSubtypeId(20);
        materialTwenty.setLabel("Arhitektonski nacrt");
        availableMaterials.add(materialTwenty);

        Material materialTwentyOne = ObjectFactory.createMaterial();
        materialTwentyOne.setSubtypeId(21);
        materialTwentyOne.setLabel("Dijagram");
        availableMaterials.add(materialTwentyOne);

        Material materialTwentyTwo = ObjectFactory.createMaterial();
        materialTwentyTwo.setSubtypeId(22);
        materialTwentyTwo.setLabel("Model");
        availableMaterials.add(materialTwentyTwo);

        Material materialTwentyThree = ObjectFactory.createMaterial();
        materialTwentyThree.setSubtypeId(23);
        materialTwentyThree.setLabel("Prezentacijski list");
        availableMaterials.add(materialTwentyThree);

        Material materialTwentyFour = ObjectFactory.createMaterial();
        materialTwentyFour.setSubtypeId(24);
        materialTwentyFour.setLabel("Skica");
        availableMaterials.add(materialTwentyFour);

        Material materialTwentyFive = ObjectFactory.createMaterial();
        materialTwentyFive.setSubtypeId(25);
        materialTwentyFive.setLabel("Tehnièki nacrt");
        availableMaterials.add(materialTwentyFive);

        Material materialTwentySix = ObjectFactory.createMaterial();
        materialTwentySix.setSubtypeId(26);
        materialTwentySix.setLabel("Ostalo");
        availableMaterials.add(materialTwentySix);

        Material materialTwentySeven = ObjectFactory.createMaterial();
        materialTwentySeven.setSubtypeId(27);
        materialTwentySeven.setLabel("Neodreðeno");
        availableMaterials.add(materialTwentySeven);

        Material materialTwentyEight = ObjectFactory.createMaterial();
        materialTwentyEight.setSubtypeId(28);
        materialTwentyEight.setLabel("Film");
        availableMaterials.add(materialTwentyEight);

        Material materialThirtyThree = ObjectFactory.createMaterial();
        materialThirtyThree.setSubtypeId(33);
        materialThirtyThree.setLabel("Video snimka");
        availableMaterials.add(materialThirtyThree);

        Material materialThirtyFour = ObjectFactory.createMaterial();
        materialThirtyFour.setSubtypeId(34);
        materialThirtyFour.setLabel("Vizualna projekcija");
        availableMaterials.add(materialThirtyFour);

        Material materialThirtySeven = ObjectFactory.createMaterial();
        materialThirtySeven.setSubtypeId(37);
        materialThirtySeven.setLabel("Podaci");
        availableMaterials.add(materialThirtySeven);

        Material materialThirtyEight = ObjectFactory.createMaterial();
        materialThirtyEight.setSubtypeId(38);
        materialThirtyEight.setLabel("Program");
        availableMaterials.add(materialThirtyEight);

        Material materialThirtyNine = ObjectFactory.createMaterial();
        materialThirtyNine.setSubtypeId(39);
        materialThirtyNine.setLabel("Grafièki prikaz");
        availableMaterials.add(materialThirtyNine);

        Material materialForty = ObjectFactory.createMaterial();
        materialForty.setSubtypeId(40);
        materialForty.setLabel("Dokument");
        availableMaterials.add(materialForty);

        Material materialFortyOne = ObjectFactory.createMaterial();
        materialFortyOne.setSubtypeId(41);
        materialFortyOne.setLabel("Font");
        availableMaterials.add(materialFortyOne);

        Material materialFortyTwo = ObjectFactory.createMaterial();
        materialFortyTwo.setSubtypeId(42);
        materialFortyTwo.setLabel("Igra");
        availableMaterials.add(materialFortyTwo);

        Material materialFortyThree = ObjectFactory.createMaterial();
        materialFortyThree.setSubtypeId(43);
        materialFortyThree.setLabel("Zvuk");
        availableMaterials.add(materialFortyThree);

        Material materialFortyFour = ObjectFactory.createMaterial();
        materialFortyFour.setSubtypeId(44);
        materialFortyFour.setLabel("Mrežni sustav");
        availableMaterials.add(materialFortyFour);

        Material materialFortyFive = ObjectFactory.createMaterial();
        materialFortyFive.setSubtypeId(45);
        materialFortyFive.setLabel("Mješano");
        availableMaterials.add(materialFortyFive);

        Material materialFortySix = ObjectFactory.createMaterial();
        materialFortySix.setSubtypeId(46);
        materialFortySix.setLabel("Ostalo");
        availableMaterials.add(materialFortySix);

        Material materialFortySeven = ObjectFactory.createMaterial();
        materialFortySeven.setSubtypeId(47);
        materialFortySeven.setLabel("Plan");
        availableMaterials.add(materialFortySeven);

        Material materialFortyEight = ObjectFactory.createMaterial();
        materialFortyEight.setSubtypeId(48);
        materialFortyEight.setLabel("Položajni nacrt");
        availableMaterials.add(materialFortyEight);
    }

    /**
     * Returns all available materials defined in schema.
     */
    public static List<Material> getAvailableMaterials() {
        return availableMaterials;
    }
}
