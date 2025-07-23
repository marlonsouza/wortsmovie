package souza.marlon.worstmovie.initialload;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class IndicatedData {

    @CsvBindByName(column = "year")
    private long year;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "studios")
    private String studio;

    @CsvBindByName(column = "producers")
    private String producer;

    @CsvBindByName(column = "winner")
    private boolean isWinner;
}
