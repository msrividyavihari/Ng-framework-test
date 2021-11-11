package com.deloitte.nextgen.ha.common;


import com.deloitte.nextgen.framework.commons.exceptions.ApiException;
import com.deloitte.nextgen.framework.commons.payload.response.ApiResponse;
import com.google.common.base.Preconditions;
import org.springframework.http.HttpStatus;

import java.util.function.Consumer;

public class WebClientApiResponseTypeConsumer<T> implements Consumer<ApiResponse<? super T>> {

    private final String endPoint;

    private WebClientApiResponseTypeConsumer(String endPoint) {
        this.endPoint = Preconditions.checkNotNull(endPoint, "End Point URL should not be null");
    }

    public static <T> WebClientApiResponseTypeConsumer<T> endPoint(String endPoint) {
        return new WebClientApiResponseTypeConsumer<>(endPoint);
    }

    @Override
    public void accept(ApiResponse<? super T> apiResponse) {
        if (apiResponse != null )  return;
        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    String.format("Weird... %s returned unknown Response Body Type. Expected Type: %s", endPoint, ApiResponse.class));
    }
}
