package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Oznaka")
public class Label implements Serializable {
    private final static long serialVersionUID = 1L;
    
    @XmlAttribute(name = "Naziv", required = true)
    protected String name;
    @XmlAttribute(name = "VrstaOstaleOznakeId")
    protected Integer remainingLabelIdType;
    
    @XmlTransient
    protected String label;

    /**
     * Gets the value of the name property.
     * 
     * @return possible object is {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the remainingLabelIdType property.
     * 
     * @return possible object is {@link Integer }
     */
    public Integer getRemainingLabelIdType() {
        return remainingLabelIdType;
    }

    /**
     * Sets the value of the remainingLabelIdType property.
     * 
     * @param value
     *            allowed object is {@link Integer }
     */
    public void setRemainingLabelIdType(Integer value) {
        this.remainingLabelIdType = value;
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
