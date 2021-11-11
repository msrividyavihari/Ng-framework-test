package com.deloitte.nextgen.ha.appeals.overview.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Range;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class AppealOverviewRequestDto {

    private List<String> appealActionCode = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @Schema(name="dateRange", hidden = true)
    private Range<ChronoLocalDate> dateRange = Range.unbounded();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setDateRangeFrom(@NotNull LocalDate fromDt) {
        dateRange = Range.of(Range.Bound.inclusive(fromDt), dateRange.getUpperBound());
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public void setDateRangeTo(@NotNull LocalDate toDt) {
        dateRange = Range.of(dateRange.getLowerBound(), Range.Bound.inclusive(toDt));
    }
}
