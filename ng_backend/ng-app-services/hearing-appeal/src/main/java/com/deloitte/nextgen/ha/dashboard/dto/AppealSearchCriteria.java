package com.deloitte.nextgen.ha.dashboard.dto;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
public class AppealSearchCriteria {

    @Setter(AccessLevel.NONE)
    @Schema(name="filedDtRange", hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
    private Range<ChronoLocalDate> filedDtRange = Range.unbounded();

    @Setter(AccessLevel.NONE)
    @Schema(name="lastUpdateDtRange", hidden = true, accessMode = Schema.AccessMode.READ_ONLY)
    private Range<ChronoLocalDate> lastUpdateDtRange = Range.unbounded();

    private List<String> appealStatusCds;
    private List<String> appealTypeCds;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setFiledDtFrom(LocalDate fromDt) {
        filedDtRange = Range.of(Range.Bound.inclusive(fromDt), filedDtRange.getUpperBound());
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setFiledDtTo(LocalDate toDt) {
        filedDtRange = Range.of(filedDtRange.getLowerBound(), Range.Bound.inclusive(toDt));
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setLastUpdateDtFrom(LocalDate fromDt) {
        lastUpdateDtRange = Range.of(Range.Bound.inclusive(fromDt), lastUpdateDtRange.getUpperBound());
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setLastUpdateDtTo(LocalDate toDt) {
        lastUpdateDtRange = Range.of(lastUpdateDtRange.getLowerBound(), Range.Bound.inclusive(toDt));
    }

}