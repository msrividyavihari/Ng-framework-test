package com.deloitte.nextgen.ha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * A default implements of {@lik CodeDescriptionPair code description}
 * to hold together code and description (key and value)
 *
 * This may be used as request or response in Web.
 *
 * The UI team expecting {@link #getCode() code} and {@link #getDescription() description}
 * but in business this may not always suitable {for example worker Id and worker name}
 * hence a JSON properties are added for code and value
 *
 *
 * @author uala
 *
 */
public interface CodeDescriptionPair<C, V> extends Serializable{

    @Nullable
    @JsonProperty("code")
    C getCode();

    @Nullable
    @JsonProperty("description")
    V getDescription();

}