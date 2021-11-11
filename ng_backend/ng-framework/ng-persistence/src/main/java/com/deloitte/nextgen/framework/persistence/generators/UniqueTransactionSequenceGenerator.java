package com.deloitte.nextgen.framework.persistence.generators;

import org.hibernate.tuple.ValueGenerator;

/**
 *
 * The sequence generator class to generate the value for UNIQUE_TRANS_ID column
 *
 * @author nishmehta on 20/10/2020 8:03 PM
 * @author mukepatel on 24/07/2021 12:47 PM
 * @project ng-framework
 */
public class UniqueTransactionSequenceGenerator extends NGSequenceGenerator implements ValueGenerator<Long> {

    protected String generateSequenceName(String className) {
        return className.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase() + "_0SQ";
    }

}
