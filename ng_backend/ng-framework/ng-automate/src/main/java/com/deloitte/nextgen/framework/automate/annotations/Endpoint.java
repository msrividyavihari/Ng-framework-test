package com.deloitte.nextgen.framework.automate.annotations;

import com.deloitte.nextgen.framework.persistence.entity.BaseEntity;

import java.lang.annotation.*;

/**
 * This annotation is used to generate Resource, Service and Repository
 * also adding all the CRUD operations for the given entity with appropriate
 * request and response class provided.
 *
 * @author nishmehta
 * @since 29/06/2021 11:34 AM
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Repeatable(Endpoints.class)
public @interface Endpoint {

    /**
     * name of endpoint
     * @return
     */
    String name();

    /**
     * Entity to be used in while generating file
     *
     * @return
     */
    Class<? extends BaseEntity> entity();

    /**
     * Request class to be used.
     *
     * @return
     */
    Class<?> request();

    /**
     * Response class to be used.
     *
     * @return
     */
    Class<?> response();

    /**
     * mapper to be used.
     *
     * @return
     */
    Class<?> mapper();

}
