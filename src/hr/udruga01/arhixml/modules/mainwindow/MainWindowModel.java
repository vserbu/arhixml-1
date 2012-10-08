package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

class MainWindowModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(MainWindowModel.class.getName());

    private File file;
    private Arhinet arhinet;

    /**
     * Sets up the upload destination for the framework.
     * <p>
     * The only thing that the framework is interested is the
     * {@link OutputStream} object. We tell the framework that we want to use
     * {@link FileOutputStream}, hence the uploaded bytes will end up in a file
     * on a server disk.
     * 
     * @param filename
     *            - The filename that the user has choosen.
     * @return {@link FileOutputStream} created out of the {@link File}
     */
    public OutputStream setUpUpload(String filename) {
        logger.trace("Entering setUpUpload()");
        logger.debug("Configuring the upload of the XML file.");
        FileOutputStream fos = null;

        try {
            logger.debug("Creating temporary file to hold uploaded data.");
            file = File.createTempFile(filename, null);
            fos = new FileOutputStream(file);
            logger.trace("Exiting setUpUpload()");
            logger.debug("File upload successfully configured.");

            return fos;
        } catch (FileNotFoundException exception) {
            logger.error("File {} can not be opened for writing.", filename);

            logger.trace("Exiting setUpUpload()");
            return null;
        } catch (IOException exception) {
            logger.error("IO error when uploading a file: {}", exception);

            logger.trace("Exiting setUpUpload()");
            return null;
        }
    }

    /**
     * Unmarshal uploaded XML file using JAXB {@link Unmarshaller}.
     * <p>
     * Unmarshaller will return Java POJO of the type {@link Arhinet}. The whole
     * process of unmarshaling is backed up with validation using the XML
     * schema.
     * 
     * @return The Java POJO of type {@link Arhinet} created out of the uploaded
     *         XML file.
     */
    public Arhinet unmarshalFile() {
        logger.trace("Entering unmarshalFile()");
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            logger.debug("Reading XML schema for the data model.");
            URL schemaURL = MainWindowModel.class.getResource("/hr/udruga01/arhixml/datamodel/schema/ARHiNET.xsd");
            Schema schema = sf.newSchema(schemaURL);
            logger.debug("XML schema initialized.");
            JAXBContext jc = JAXBContext.newInstance(Arhinet.class);
            Unmarshaller um = jc.createUnmarshaller();
            um.setSchema(schema);
            logger.debug("Unmarshaling XML file.");
            arhinet = (Arhinet) um.unmarshal(file);
            logger.debug("File successfully unmarshaled.");
            logger.trace("Exiting unmarshalFile()");

            return arhinet;
        } catch (JAXBException exception) {
            logger.error("Error when unmarshaling a file: {}", exception);

            logger.trace("Exiting unmarshalFile()");
            return null;
        } catch (SAXException exception) {
            logger.error("XML schema is wrong: {}", exception);

            logger.trace("Exiting unmarshalFile()");
            return null;
        }
    }

    /**
     * Marshal {@link Arhinet} object fetched from the table into file.
     * <p>
     * Marshaller will return file generated from {@link Arhinet}. The whole
     * process of marshaling is backed up with validation using the XML schema.
     * 
     * @param arhinet
     *            - {@link Arhinet} object fetched from the table
     * @return - {@link File} which marshaller generated out of the
     *         {@link Arhinet} object.
     */
    public File marshalToFile(Arhinet arhinet) {
        logger.trace("Entering marshalToFile()");
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            logger.debug("Reading XML schema for the data model.");
            URL schemaURL = MainWindowModel.class.getResource("/hr/udruga01/arhixml/datamodel/schema/ARHiNET.xsd");
            Schema schema = sf.newSchema(schemaURL);
            logger.debug("XML schema initialized.");
            JAXBContext jc = JAXBContext.newInstance(Arhinet.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setSchema(schema);
            logger.debug("Marshaling object to XML file.");
            File file = File.createTempFile("ArhiXML", ".xml");
            m.marshal(arhinet, file);
            logger.debug("Object successfully marshaled.");
            logger.trace("Exiting marshalToFile()");

            return file;
        } catch (JAXBException exception) {
            logger.error("Error when unmarshaling a file: {}", exception);
            logger.trace("Exiting marshalToFile()");

            return null;
        } catch (SAXException exception) {
            logger.error("XML schema is wrong: {}", exception);
            logger.trace("Exiting marshalToFile()");

            return null;
        } catch (IOException exception) {
            logger.error("File can not be created.");
            logger.trace("Exiting marshalToFile()");

            return null;
        }
    }
}
