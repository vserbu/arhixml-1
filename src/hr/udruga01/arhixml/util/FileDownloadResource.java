package hr.udruga01.arhixml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.Application;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.FileResource;
import com.vaadin.terminal.Resource;

/**
 * Class represents downloadable {@link FileResource}.
 * <p>
 * Class extends the {@link FileResource} for the sole purpose of setting
 * <code>Content-Disposition</code> MIME parameter. We use the
 * <code>attachment</code> disposition type to indicate that the file is not to
 * be rendered inside of a web browser. In other words, this will indicate a web
 * browser to ask the user what he wants to do with this file (download it, open
 * it in some configured application...).
 */
public class FileDownloadResource extends FileResource {
    private static final long serialVersionUID = 1L;
    private final Logger logger = LoggerFactory.getLogger(FileDownloadResource.class.getName());

    public FileDownloadResource(File sourceFile, Application application) {
        super(sourceFile, application);
        logger.trace("Entering FileDownloadResource()");
        logger.trace("Exiting FileDownloadResource()");
    }

    /**
     * Method returns the {@link DownloadStream} object which framework calls
     * when there is a need to open a {@link Resource}.
     * <p>
     * This method is overridden for the purpose of setting
     * <code>Content-Disposition</code> parameter to the {@link DownloadStream}.
     * This will instruct web browser to acctually download file instead of rendering it.
     * object.
     */
    @Override
    public DownloadStream getStream() {
        logger.trace("Entering getStream()");
        try {
            DownloadStream ds = new DownloadStream(new FileInputStream(getSourceFile()), getMIMEType(), getFilename());
            ds.setParameter("Content-Disposition", "attachment; filename=\"" + getFilename() + "\"");

            logger.trace("Exiting getStream()");

            return ds;
        } catch (final FileNotFoundException exception) {
            // No logging for non-existing files at this level.
            logger.trace("Exiting getStream()");

            return null;
        }
    }
}
