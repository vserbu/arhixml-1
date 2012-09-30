package hr.udruga01.arhixml.datamodel;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface in the hr.udruga01.arhixml.datamodel package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {
    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: hr.udruga01.arhixml.datamodel
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Label }
     */
    public Label createLabel() {
        return new Label();
    }

    /**
     * Create an instance of {@link Material }
     */
    public Material createMaterial() {
        return new Material();
    }

    /**
     * Create an instance of {@link TechnicalUnit }
     */
    public TechnicalUnit createTechnicalUnit() {
        return new TechnicalUnit();
    }

    /**
     * Create an instance of {@link Arhinet }
     */
    public Arhinet createArhinet() {
        return new Arhinet();
    }

    /**
     * Create an instance of {@link ArchiveUnit }
     */
    public ArchiveUnit createArchiveUnit() {
        return new ArchiveUnit();
    }

    /**
     * Create an instance of {@link Maker }
     */
    public Maker createMaker() {
        return new Maker();
    }

    /**
     * Create an instance of {@link Medium }
     */
    public Medium createMedium() {
        return new Medium();
    }

    /**
     * Create an instance of {@link RegistrationUnit }
     */
    public RegistrationUnit createRegistrationUnit() {
        return new RegistrationUnit();
    }
}
