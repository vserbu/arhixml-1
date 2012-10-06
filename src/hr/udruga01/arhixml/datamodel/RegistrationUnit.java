package hr.udruga01.arhixml.datamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistraturnaJedinica", propOrder = { "timePeriodNote", "contents", "note", "labels", "makers", "materials", "mediums", "archiveUnits", "technicalUnits", "registrationUnits" })
public class RegistrationUnit implements Serializable {
    private final static long serialVersionUID = 1L;
    
    @XmlElement(name = "NapomenaORazdoblju")
    protected String timePeriodNote;
    @XmlElement(name = "Sadrzaj")
    protected String contents;
    @XmlElement(name = "Napomena")
    protected String note;
    @XmlElement(name = "Oznaka")
    protected List<Label> labels;
    @XmlElement(name = "Stvaratelj")
    protected List<Maker> makers;
    @XmlElement(name = "Gradja")
    protected List<Material> materials;
    @XmlElement(name = "Medij")
    protected List<Medium> mediums;
    @XmlElement(name = "KolicinaArhivskihJedinica")
    protected List<ArchiveUnit> archiveUnits;
    @XmlElement(name = "KolicinaTehnickihJedinica")
    protected List<TechnicalUnit> technicalUnits;
    @XmlElement(name = "RegistraturnaJedinica")
    protected List<RegistrationUnit> registrationUnits;
    @XmlAttribute(name = "RazinaId", required = true)
    protected int levelId;
    @XmlAttribute(name = "Signatura", required = true)
    protected int signature;
    @XmlAttribute(name = "Naziv", required = true)
    protected String name;
    @XmlAttribute(name = "ImateljId")
    protected Integer holderId;
    @XmlAttribute(name = "GodinaOd")
    protected Integer yearFrom;
    @XmlAttribute(name = "GodinaDo")
    protected Integer yearTo;
    
    @XmlTransient
    protected RegistrationUnit parentRegistrationUnit;

    /**
     * Gets the value of the timePeriodNote property.
     * 
     * @return possible object is {@link String }
     */
    public String getTimePeriodNote() {
        return timePeriodNote;
    }

    /**
     * Sets the value of the timePeriodNote property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setTimePeriodNote(String value) {
        this.timePeriodNote = value;
    }

    /**
     * Gets the value of the contents property.
     * 
     * @return possible object is {@link String }
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets the value of the contents property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setContents(String value) {
        this.contents = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return possible object is {@link String }
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the labels property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the labels property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getLabels().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Label }
     */
    public List<Label> getLabels() {
        if (labels == null) {
            labels = new ArrayList<Label>();
        }

        return this.labels;
    }

    /**
     * Gets the value of the makers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the makers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMakers().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Maker }
     */
    public List<Maker> getMakers() {
        if (makers == null) {
            makers = new ArrayList<Maker>();
        }

        return this.makers;
    }

    /**
     * Gets the value of the materials property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the gradja property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMaterials().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Material }
     */
    public List<Material> getMaterials() {
        if (materials == null) {
            materials = new ArrayList<Material>();
        }

        return this.materials;
    }
    
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    /**
     * Gets the value of the mediums property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the mediums property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMediums().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Medium }
     */
    public List<Medium> getMediums() {
        if (mediums == null) {
            mediums = new ArrayList<Medium>();
        }

        return this.mediums;
    }

    /**
     * Gets the value of the kolicinaArhivskihJedinica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the kolicinaArhivskihJedinica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getKolicinaArhivskihJedinica().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ArchiveUnit }
     */
    public List<ArchiveUnit> getArchiveUnits() {
        if (archiveUnits == null) {
            archiveUnits = new ArrayList<ArchiveUnit>();
        }

        return this.archiveUnits;
    }

    /**
     * Gets the value of the technicalUnits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the technicalUnits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getTechnicalUnits().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TechnicalUnit }
     */
    public List<TechnicalUnit> getTechnicalUnits() {
        if (technicalUnits == null) {
            technicalUnits = new ArrayList<TechnicalUnit>();
        }

        return this.technicalUnits;
    }

    /**
     * Gets the value of the registrationUnits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
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

    /**
     * Gets the value of the levelId property.
     */
    public int getLevelId() {
        return levelId;
    }

    /**
     * Sets the value of the levelId property.
     */
    public void setlevelId(int value) {
        this.levelId = value;
    }

    /**
     * Gets the value of the signature property.
     */
    public int getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     */
    public void setSignature(int value) {
        this.signature = value;
    }

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
     * Gets the value of the holderId property.
     * 
     * @return possible object is {@link Integer }
     */
    public Integer getHolderId() {
        return holderId;
    }

    /**
     * Sets the value of the holderId property.
     * 
     * @param value
     *            allowed object is {@link Integer }
     */
    public void setHolderId(Integer value) {
        this.holderId = value;
    }

    /**
     * Gets the value of the yearFrom property.
     * 
     * @return possible object is {@link Integer }
     */
    public Integer getYearFrom() {
        return yearFrom;
    }

    /**
     * Sets the value of the yearFrom property.
     * 
     * @param value
     *            allowed object is {@link Integer }
     */
    public void setYearFrom(Integer value) {
        this.yearFrom = value;
    }

    /**
     * Gets the value of the yearTo property.
     * 
     * @return possible object is {@link Integer }
     */
    public Integer getYearTo() {
        return yearTo;
    }

    /**
     * Sets the value of the yearTo property.
     * 
     * @param value
     *            allowed object is {@link Integer }
     */
    public void setYearTo(Integer value) {
        this.yearTo = value;
    }
    
    public RegistrationUnit getParentRegistrationUnit() {
        return parentRegistrationUnit;
    }
    
    public void setParentRegistrationUnit(RegistrationUnit registrationUnit) {
        parentRegistrationUnit = registrationUnit;
    }
}
