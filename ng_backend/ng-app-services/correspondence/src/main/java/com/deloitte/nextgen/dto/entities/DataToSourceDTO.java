package com.deloitte.nextgen.dto.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataToSourceDTO {
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private java.sql.Date startDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private java.sql.Date endDate;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
//    @JsonFormat(JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime endDate;
    private String agencyId;
}
