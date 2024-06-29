package pl.pjatk.pawkle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Details about the NBP")
public class Nbp {

    @Schema(description = "The table of the currency")
    @SerializedName("table")
    @Expose
    private String table;

    @Schema(description = "The name of the currency")
    @SerializedName("currency")
    @Expose
    private String currency;

    @Schema(description = "The code of the currency")
    @SerializedName("code")
    @Expose
    private String code;

    @Schema(description = "The rates of the currency")
    @SerializedName("rates")
    @Expose
    private List<Rate> rates = null;

}
