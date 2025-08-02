package souza.marlon.worstmovie.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import souza.marlon.worstmovie.initialload.IndicatedData;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@UtilityClass
public class CsvUtils {

    public List<IndicatedData> getIndicatedDataFromCsv(String csv){
        var resource = new ClassPathResource(csv);
        try {
            return new CsvToBeanBuilder<IndicatedData>(new InputStreamReader(resource.getInputStream()))
                    .withType(IndicatedData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (IOException e) {
            log.error("Get csv erros:{}", e.getMessage(), e);
            return List.of();
        }
    }
}
