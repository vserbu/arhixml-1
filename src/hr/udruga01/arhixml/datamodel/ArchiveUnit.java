package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KolicinaArhivskihJedinica")
public class ArchiveUnit implements Serializable {
    private final static long serialVersionUID = 1L;
    
    @XmlAttribute(name = "MjernaJedinicaId", required = true)
    protected int measurementUnitId;
    @XmlAttribute(name = "Kolicina")
    protected BigDecimal amount;

    /**
     * Gets the value of the measurementUnitId property.
     */
    public int getMeasurementUnitId() {
        return measurementUnitId;
    }

    /**
     * Sets the value of the measurementUnitId property.
     * @param value - The value which will be stored in measurementUnitId property.
     */
    public void setMeasurementUnitId(int value) {
        this.measurementUnitId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return possible object is {@link BigDecimal}
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value - The value which will be stored in amount property. Allowed object is {@link BigDecimal}.
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }
}
