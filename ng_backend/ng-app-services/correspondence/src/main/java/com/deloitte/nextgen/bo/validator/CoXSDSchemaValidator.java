package com.deloitte.nextgen.bo.validator;

import com.deloitte.nextgen.util.xsd.schema.notices.IESCorrespondence;
import com.deloitte.nextgen.util.xsd.schema.notices.MetaData;
import lombok.NoArgsConstructor;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class CoXSDSchemaValidator {


    private static JAXBContext jaxbContext;
    static {
        try {
            jaxbContext = JAXBContext.newInstance(IESCorrespondence.class);
        } catch (JAXBException ej) {
            /*CoDebugger.debugException(
                    "failed to create IESCorrespondence.class", ej);*/
        }
    }

    /** The Constant XSD_VALIDATION_MSGXSD_VALIDATION_MSG. */
    private static final String XSD_VALIDATION_MSG = "Notices validation faild for the Document# %s and Case Number# %s : %s";

    /** The Constant EMPTY_STRING. */
    private static final String EMPTY_STRING = "";

    /** The Constant SCHEMA_VALIDATOR_LOCATION. */
    private static final String SCHEMA_VALIDATOR_LOCATION = "/META-INF/xsd/%s_IESFormXMLSchemaValidator.xsd";

    /**
     * The Constant docIds to validate the binding object against an xsd schema.
     */
    private static final String[] docIds = new String[] { "NGG0014", "NGG0027",
            "NGG0053", "NGGA0002", "NGGA0023", "NGG0033", "NGGA0046",
            "NGG0016", "NGG0045", "NGG060", "NGG1065", "NGGA0047", "NGG0019",
            "NGGA0033", "NGGA0030","NGGG24","NGG0057","NGG1056","NGG0037","NGGA0021","NGGA0034","NGGA826","NGGA826A",
            "NGG0011", "NGG0012", "NGG0013","NGGA0050"};


    /** The Constant docIdList. */
    private static final List<String> docIdList = Arrays.asList(docIds);

    /**
     * Validate.
     *
     * @param iesCorrespondence
     *            the ies correspondence
     * @return the string
     * @throws Exception
     *             the exception
     */
    public String validate(final IESCorrespondence iesCorrespondence)
            throws Exception {
        final CoXSDErrorHandler coXSDErrorHandler = new CoXSDErrorHandler();
        try {
            final MetaData metaData=iesCorrespondence.getMetaData();
            String templateId = metaData.getTemplateId();
            if(templateId.contains("NGGA0034")){
                templateId = "NGGA0034";
            }
            if (!docIdList.contains(templateId)) {
                return EMPTY_STRING;
            }
            final String caseNum=metaData.getCaseNum();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            if (null == jaxbContext || sf == null) {
                throw new Exception("jaxbContext or SchemaFactory is not intialized");
            }
            final JAXBSource source = new JAXBSource(jaxbContext, iesCorrespondence);

            final String fileLocation = String.format(SCHEMA_VALIDATOR_LOCATION, templateId);
            URL url = CoXSDSchemaValidator.class.getClassLoader()
                    .getResource(fileLocation);
            Schema schema;
            if (url == null) {
                url=CoXSDSchemaValidator.class.getResource(fileLocation);
            }
            schema = sf.newSchema(url);
            final Validator validator = schema.newValidator();
            validator.setErrorHandler(coXSDErrorHandler);
            validator.validate(source);
            String message = coXSDErrorHandler.getMessage();
            final String[] params=new String[]{templateId,caseNum,message};
            if (!EMPTY_STRING.equals(message)) {
                message = String.format(XSD_VALIDATION_MSG, (Object[]) params);
                throw new Exception(message);
            }
        } catch (JAXBException | SAXException | IOException e) {
            throw new Exception(e.getMessage(), e);
        }
        return coXSDErrorHandler.getMessage();
    }
}
