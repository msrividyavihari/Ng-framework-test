package com.deloitte.nextgen.bo.validator;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

@NoArgsConstructor
@Data
public class CoXSDErrorHandler implements ErrorHandler {

    private String message = "";
    @Override
    public void error(final SAXParseException exception) {
        message = exception.getMessage();
    }

    @Override
    public void fatalError(final SAXParseException exception) {
    }

    /**
     * Receive notification of a warning.
     *
     * <p>SAX parsers will use this method to report conditions that
     * are not errors or fatal errors as defined by the XML
     * recommendation.  The default behaviour is to take no
     * action.</p>
     *
     * <p>The SAX parser must continue to provide normal parsing events
     * after invoking this method: it should still be possible for the
     * application to process the document through to the end.</p>
     *
     * <p>Filters may use this method to report other, non-XML warnings
     * as well.</p>
     *
     * @param exception The warning information encapsulated in a
     *                  SAX parse exception.
     * @see SAXParseException
     */
    @Override
    public void warning(SAXParseException exception) {
    }
}
