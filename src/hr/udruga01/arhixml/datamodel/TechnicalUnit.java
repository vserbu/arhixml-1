package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KolicinaTehnickihJedinica", propOrder = { "characteristics" })
public class TechnicalUnit implements Serializable {
    private final static long serialVersionUID = 1L;
    
    @XmlElement(name = "TvarneZnacajke")
    protected String characteristics;
    @XmlAttribute(name = "VrstaTehnickeJediniceId", required = true)
    protected int technicalUnitTypeId;
    @XmlAttribute(name = "Kolicina")
    protected BigDecimal amount;
    
    @XmlTransient
    protected String label;

    /**
     * Gets the value of the characteristics property.
     * 
     * @return possible object is {@link String }
     */
    public String getCharacteristics() {
        return characteristics;
    }

    /**
     * Sets the value of the characteristics property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setCharacteristics(String value) {
        this.characteristics = value;
    }

    /**
     * Gets the value of the technicalUnitTypeId property.
     */
    public int getTechnicalUnitTypeId() {
        return technicalUnitTypeId;
    }

    /**
     * Sets the value of the technicalUnitTypeId property.
     */
    public void setTechnicalUnitTypeId(int value) {
        this.technicalUnitTypeId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return possible object is {@link BigDecimal }
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *            allowed object is {@link BigDecimal }
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
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
