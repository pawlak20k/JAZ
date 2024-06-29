package pl.pjatk.pawkle.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SearchData {
    @Schema(description = "The code of the currency")
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Start date")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "End date")
    private Date endDate;
}
