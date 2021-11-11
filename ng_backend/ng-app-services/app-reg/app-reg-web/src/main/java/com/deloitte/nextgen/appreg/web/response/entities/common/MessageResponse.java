package com.deloitte.nextgen.appreg.web.response.entities.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
/**
 * Message Responses
 */
public class MessageResponse {
    List<String> info;
    List<String> warning;
    List<String> error;
    List<String> success;
}
