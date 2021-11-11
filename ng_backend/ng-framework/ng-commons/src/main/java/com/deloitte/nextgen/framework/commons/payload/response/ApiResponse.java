package com.deloitte.nextgen.framework.commons.payload.response;

import com.deloitte.nextgen.framework.commons.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

/**
 * @author nishmehta on 26/10/2020 3:34 PM
 * @project ng-framework
 */
@ToString
@NoArgsConstructor
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    @Getter
    Instant timestamp = Instant.now();

    @Getter
    @JsonIgnore
    HttpStatus httpStatus;

    @Getter
    Message message;

    @Getter
    T data;

    @Getter
    @JsonProperty("error")
    ErrorResponse errorDetails;

    private ApiResponse(HttpStatus httpStatus, Message message, T data) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    private ApiResponse(HttpStatus httpStatus, Message message, ErrorResponse errorDetails) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public static ResponseBuilder with(HttpStatus httpStatus, int messageCode) {
        return new DefaultResponseBuilder(httpStatus, messageCode);
    }

    public static ResponseBuilder success(int messageCode) {
        return new DefaultResponseBuilder(HttpStatus.OK, messageCode);
    }

    public static ResponseBuilder created(int messageCode) {
        return new DefaultResponseBuilder(HttpStatus.CREATED, messageCode);
    }

    public static ResponseBuilder updated(int messageCode) {
        return success(messageCode);
    }

    public static <T> ResponseEntity<ApiResponse<T>> deleted(int messageCode) {
        return noContent(messageCode);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent(int messageCode) {
        DefaultResponseBuilder dfb = new DefaultResponseBuilder(HttpStatus.NO_CONTENT, messageCode);
        return dfb.build();
    }

    public static ResponseBuilder notFound(int messageCode) {
        return new DefaultResponseBuilder(HttpStatus.NOT_FOUND, messageCode);
    }


    public static ResponseBuilder conflict(int messageCode) {
        return new DefaultResponseBuilder(HttpStatus.CONFLICT, messageCode);
    }


    public static ResponseBuilder badRequest(int messageCode) {
        return new DefaultResponseBuilder(HttpStatus.BAD_REQUEST, messageCode);
    }

    public static ResponseBuilder error(int errorCode) {
        return new DefaultResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
    }

    public static ResponseBuilder error(int errorCode, String message) {
        return new DefaultResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, message);
    }

    public static ResponseBuilder forbidden(int errorCode) {
        return new DefaultResponseBuilder(HttpStatus.FORBIDDEN, errorCode);
    }

    public static ResponseBuilder unauthorized(int errorCode) {
        return new DefaultResponseBuilder(HttpStatus.UNAUTHORIZED, errorCode);
    }

    public static <T> ResponseEntity<ApiResponse<PagedData<T>>> pageable(Page<T> page) {

        PagedData<T> pagedData = PagedData.<T>with(
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalPages(),
                page.getTotalElements(), 0)
                .content(page.getContent());

        return new DefaultResponseBuilder(HttpStatus.OK, 100, pagedData).page();
    }

    public interface ResponseBuilder {
        ResponseBuilder code(int messageCode);

        ResponseBuilder description(String description);

        ResponseBuilder notify(boolean show);

        ResponseBuilder type(MessageType messageType);

        <T> ResponseEntity<ApiResponse<T>> data(T data);

        ApiResponse<ErrorResponse> error(ErrorResponse data);

        <T> ResponseEntity<ApiResponse<T>> build();
    }


    private static class DefaultResponseBuilder implements ResponseBuilder {

        HttpStatus httpStatus;

        Message message;

        PagedData pagedData;

        public DefaultResponseBuilder(HttpStatus httpStatus, int messageCode) {
            this.httpStatus = httpStatus;
            this.message = new Message(messageCode);
        }

        public DefaultResponseBuilder(HttpStatus httpStatus, int messageCode, String message) {
            this.httpStatus = httpStatus;
            this.message = new Message(messageCode, message);
        }

        public DefaultResponseBuilder(HttpStatus httpStatus, int messageCode, PagedData pagedData) {
            this.httpStatus = httpStatus;
            this.message = new Message(messageCode);
            this.pagedData = pagedData;
        }

        @Override
        public ResponseBuilder code(int messageCode) {
            this.message.setCode(messageCode);
            return this;
        }

        @Override
        public ResponseBuilder description(String description) {
            this.message.setDescription(description);
            return this;
        }

        @Override
        public ResponseBuilder notify(boolean show) {
            this.message.setNotify(show);
            return this;
        }

        @Override
        public ResponseBuilder type(MessageType messageType) {
            this.message.setType(messageType);
            return this;
        }

        public <T> ResponseEntity<ApiResponse<PagedData<T>>> page() {
            return data(this.pagedData);
        }

        @Override
        public <T> ResponseEntity<ApiResponse<T>> data(T data) {
            ApiResponse response = new ApiResponse(httpStatus, message, data);
            return ResponseEntity.status(httpStatus).body(response);
        }

        @Override
        public ApiResponse<ErrorResponse> error(ErrorResponse errorDetails) {
            return new ApiResponse(httpStatus, message, errorDetails);
        }

        @Override
        public <T> ResponseEntity<ApiResponse<T>> build() {
            return data(null);
        }
    }
}
