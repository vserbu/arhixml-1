package hr.udruga01.arhixml.datamodel;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;

/**
 * This class holds the enumeration of {@link Label} <code>remainingLabelIdType</code>
 * ids.
 * <p>
 * It is used to populate {@link ComboBox} component inside {@link Table} of
 * {@link Label} objects. This way, {@link Form} automatically makes data
 * bounding to these objects.
 */
public class AvailableLabels {
    private static List<Label> availableLabels;
    
    static {
        // FIXME Currently, we are creating a list of all available labels by
        // means of manually creating label one by one hardcoded in this
        // class. This is not a good way for more reasons. First, it is tedious
        // to mantain this list because of possible schema changes. Second, the
        // list may contain false data because of human error. This should be
        // fixed by reading the list directly from the schema.
        availableLabels = new ArrayList<Label>();

        Label LabelOne = ObjectFactory.createLabel();
        LabelOne.setRemainingLabelIdType(3);
        LabelOne.setLabel("Stara signatura");
        availableLabels.add(LabelOne);

        Label LabelTwo = ObjectFactory.createLabel();
        LabelTwo.setRemainingLabelIdType(4);
        LabelTwo.setLabel("Izvorna uredska oznaka - Klasa");
        availableLabels.add(LabelTwo);
        
        Label LabelThree = ObjectFactory.createLabel();
        LabelThree.setRemainingLabelIdType(5);
        LabelThree.setLabel("Izvorna uredska oznaka - Urudžbeni broj");
        availableLabels.add(LabelThree);
        
        Label LabelFour = ObjectFactory.createLabel();
        LabelFour.setRemainingLabelIdType(6);
        LabelFour.setLabel("Registraturna oznaka");
        availableLabels.add(LabelFour);
        
        Label LabelFive = ObjectFactory.createLabel();
        LabelFive.setRemainingLabelIdType(7);
        LabelFive.setLabel("Inventarni broj");
        availableLabels.add(LabelFive);
    }
    
    /**
     * Returns all available labels defined in schema.
     */
    public static List<Label> getAvailableLabels() {
        return availableLabels;
    }
}
