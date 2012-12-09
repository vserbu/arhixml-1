package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Medij")
public class Medium implements Serializable {
    private final static long serialVersionUID = 1L;
    
    @XmlAttribute(name = "VrstaMedijaId", required = true)
    protected int mediumTypeId;
    
    @XmlTransient
    protected String label;

    /**
     * Gets the value of the mediumTypeId property.
     */
    public int getMediumTypeId() {
        return mediumTypeId;
    }

    /**
     * Sets the value of the mediumTypeId property.
     */
    public void setMediumTypeId(int value) {
        this.mediumTypeId = value;
    }
    
    /**
     * Gets the value of <code>label</code> property.
     */
    public String getLabel() {
        return label;
    }
    
    /**
     * Sets the value of the <code>label</code> property.
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
