package com.deloitte.nextgen.appreg.web.entities.sequence.generators;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;
@Slf4j
public class NgSequenceGenerator extends SequenceStyleGenerator {
    public static final String CLASS_PATH = "com.deloitte.nextgen.persistence.entities.sequence.generators.NgSequenceGenerator";
    public static final String SEQUENCE_PREFIX = "prefix";
    public static final String NUMBER_FORMAT = "format";
    private static final String SEQUENCE_PREFIX_DEFAULT = "";
    private static final String NUMBER_FORMAT_DEFAULT = "%d";
    private String prefix;
    private String format;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        prefix = ConfigurationHelper.getString(SEQUENCE_PREFIX,
                params, SEQUENCE_PREFIX_DEFAULT);
        format = ConfigurationHelper.getString(NUMBER_FORMAT,
                params, NUMBER_FORMAT_DEFAULT);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        StringBuilder sequence = new StringBuilder(prefix);
        sequence.append(String.format(format, super.generate(session, object)));
        log.info("Generated App Number is : "+sequence);
        return sequence.toString();
    }
}
