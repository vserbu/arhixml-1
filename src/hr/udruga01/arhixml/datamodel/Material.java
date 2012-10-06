package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gradja")
public class Material implements Serializable {
    private final static long serialVersionUID = 1L;
    
    @XmlAttribute(name = "KomPodvrstaId", required = true)
    protected int subtypeId;

    /**
     * Gets the value of the subtypeId property.
     */
    public int getSubtypeId() {
        return subtypeId;
    }

    /**
     * Sets the value of the subtypeId property.
     */
    public void setSubtypeId(int subtypeId) {
        this.subtypeId = subtypeId;
    }
}
