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
     * Constructor has <code>private</code> visiblity. Class is not ment to be
     * instantiated. User code must use static methods to create data model
     * objects.
     */
    private ObjectFactory() {
    }

    /**
     * Create an instance of {@link Label}
     */
    public static Label createLabel() {
        return new Label();
    }

    /**
     * Create an instance of {@link Material}
     */
    public static Material createMaterial() {
        return new Material();
    }

    /**
     * Create an instance of {@link TechnicalUnit}
     */
    public static TechnicalUnit createTechnicalUnit() {
        return new TechnicalUnit();
    }

    /**
     * Create an instance of {@link Arhinet}
     */
    public static Arhinet createArhinet() {
        return new Arhinet();
    }

    /**
     * Create an instance of {@link ArchiveUnit}
     */
    public static ArchiveUnit createArchiveUnit() {
        return new ArchiveUnit();
    }

    /**
     * Create an instance of {@link Maker}
     */
    public static Maker createMaker() {
        return new Maker();
    }

    /**
     * Create an instance of {@link Medium}
     */
    public static Medium createMedium() {
        return new Medium();
    }

    /**
     * Create an instance of {@link RegistrationUnit}
     */
    public static RegistrationUnit createRegistrationUnit() {
        return new RegistrationUnit();
    }
}
