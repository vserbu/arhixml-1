package hr.udruga01.arhixml.modules.mainwindow;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

/**
 * This class is a handler for validation process.
 * It is used to extract details of validation error, if any.
 */
public class XMLValidationEventHandler implements ValidationEventHandler {
    private String errorMessage;
    private int lineNumber;

    @Override
    public boolean handleEvent(ValidationEvent event) {
        errorMessage = event.getMessage();
        lineNumber = event.getLocator().getLineNumber();

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<br/>");
        sb.append("Opis greške: ");
        sb.append("<i>");
        sb.append(errorMessage);
        sb.append("</i>");
        sb.append("<br/>");

        if (lineNumber != -1) {
            sb.append("Broj linije u dokumentu: ");
            sb.append("<i>");
            sb.append(lineNumber);
            sb.append("</i>");
            sb.append("<br/>");
        }

        return sb.toString();
    }
}
