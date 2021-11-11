package com.deloitte.nextgen.framework.automate.processor;

import com.squareup.javapoet.TypeName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author nishmehta
 * @since 09/08/2021 1:18 PM
 */

@Data
@AllArgsConstructor
public class IdField {

    private String name;

    private TypeName type;
}
