package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "registrationUnits" })
@XmlRootElement(name = "ARHiNET")
public class Arhinet implements Serializable {
    private final static long serialVersionUID = 1L;

    @XmlElement(name = "RegistraturnaJedinica")
    protected List<RegistrationUnit> registrationUnits;

    /**
     * Gets the value of the registrationUnits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is no a
     * <CODE>set</CODE> method for the registrationUnits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getRegistrationUnits().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegistrationUnit }
     */
    public List<RegistrationUnit> getRegistrationUnits() {
        if (registrationUnits == null) {
            registrationUnits = new ArrayList<RegistrationUnit>();
        }

        return this.registrationUnits;
    }
    
    public void setRegistrationUnits(List<RegistrationUnit> registrationUnits) {
        this.registrationUnits = registrationUnits;
    }
}
