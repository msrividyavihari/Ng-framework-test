package com.deloitte.ng.reftables;

import lombok.Data;

/**
 * The class ResponseVO
 *
 * @author mukepatel on 20/06/2020 2:36 PM
 * @project ng-spring-boot-autoconfigure
 */
@Data
public class ResponseVO {

    private Object data;

    private String status;

    public ResponseVO() {
    }

    public ResponseVO(Object data, String status) {
        this.data = data;
        this.status = status;
    }

}
