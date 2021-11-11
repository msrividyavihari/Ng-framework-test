package com.deloitte.nextgen.framework.commons.mapper;

import java.util.Optional;

/**
 * A util mapper to map any Optional<T> to T.
 * Note this class can be deleted after mapstruct adds this feature.. https://github.com/mapstruct/mapstruct/issues/2477
 *
 * @author Uday Ala
 * @since v0.0.1-SNAPSHOT
 */
public class OptionalMapper {

    private OptionalMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T fromOptional(Optional<T> optional) {
        return optional.orElse(null);
    }
}