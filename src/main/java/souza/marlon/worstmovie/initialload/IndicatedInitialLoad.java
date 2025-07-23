package souza.marlon.worstmovie.initialload;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import souza.marlon.worstmovie.config.WorstProjectConfiguration;
import souza.marlon.worstmovie.model.Indicated;
import souza.marlon.worstmovie.service.IndicatedServiceImpl;
import souza.marlon.worstmovie.service.ProducerService;
import souza.marlon.worstmovie.service.StudioService;

import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
@AllArgsConstructor
public class IndicatedInitialLoad {

    private final IndicatedServiceImpl indicatedService;
    private final StudioService studioService;
    private final ProducerService producerService;
    private final WorstProjectConfiguration worstProjectConfiguration;

    @Transactional
    @PostConstruct
    public void init(){
        log.info("File initial load: {}", worstProjectConfiguration.getInitialLoadFile());
        var resource = new ClassPathResource(worstProjectConfiguration.getInitialLoadFile());
        try {
            var indicatedData = new CsvToBeanBuilder<IndicatedData>(new InputStreamReader(resource.getInputStream()))
                    .withType(IndicatedData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build()
                    .parse();

            for (IndicatedData indicatedDatum : indicatedData) {
                process(indicatedDatum);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(IndicatedData indicatedData) throws BadRequestException {

        var producer = producerService.findByNameOrNew(indicatedData.getProducer());
        var studio = studioService.findByNameOrNew(indicatedData.getStudio());

        var producerRef = producerService.save(producer);
        var studioRef = studioService.save(studio);

        var indicated = Indicated.builder()
                .title(indicatedData.getTitle())
                .producer(producerRef)
                .studio(studioRef)
                .yearAt(indicatedData.getYear())
                .isWinner(indicatedData.isWinner())
                .build();

        var saved = indicatedService.save(indicated);

        log.info("Indicated saved: {}", saved.getTitle());

    }
}
