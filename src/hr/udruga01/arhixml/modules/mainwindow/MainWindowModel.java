package hr.udruga01.arhixml.modules.mainwindow;

import hr.udruga01.arhixml.datamodel.Arhinet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

class MainWindowModel {
    private File file;
    private Arhinet arhinet;
    
    public OutputStream uploadFile(String filename) {
        FileOutputStream fos = null;

        try {
            file = File.createTempFile(filename, null);
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();

            return null;
        } catch (IOException exception) {
            exception.printStackTrace();

            return null;
        }

        return fos;
    }

    public Arhinet unmarshallFile() {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = MainWindowModel.class.getResource("/hr/udruga01/arhixml/datamodel/schema/ARHiNET.xsd");
            Schema schema = sf.newSchema(schemaURL); 
            JAXBContext jc = JAXBContext.newInstance(Arhinet.class);
            Unmarshaller um = jc.createUnmarshaller();
            um.setSchema(schema);
            arhinet = (Arhinet) um.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            
            return null;
        } catch (SAXException e) {
            e.printStackTrace();
            
            return null;
        }
        
        return arhinet;
    }
}
