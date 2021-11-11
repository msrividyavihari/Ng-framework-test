package com.deloitte.nextgen.framework.automate.source;

/**
 * @author nishmehta
 * @since 05/07/2021 6:32 PM
 */
public class NameConstants {

    private NameConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String GENERATED_PACKAGE = ".generated";

    public static final String REPOSITORY_SUFFIX = "Repository";

    public static final String SERVICE_SUFFIX = "Service";

    public static final String IMPL_SUFFIX = "Impl";

    public static final String RESOURCE_SUFFIX = "Resource";

    public static final String REPOSITORY_PACKAGE = ".".concat(REPOSITORY_SUFFIX.toLowerCase());

    public static final String SERVICE_PACKAGE = ".".concat(SERVICE_SUFFIX.toLowerCase());

    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE.concat(".").concat(IMPL_SUFFIX.toLowerCase());

    public static final String RESOURCE_PACKAGE = ".".concat(RESOURCE_SUFFIX.toLowerCase());

    public static final String REPOSITORY_FIELD_NAME = REPOSITORY_SUFFIX.toLowerCase();

    public static final String SERVICE_FIELD_NAME = SERVICE_SUFFIX.toLowerCase();

    public static final String MAPPER_FIELD_NAME = "mapper";

    public static final String ENTITY_FIELD_NAME = "entity";

    public static final String REQUEST_FIELD_NAME = "request";

    public static final String RESPONSE_FIELD_NAME = "response";

}
