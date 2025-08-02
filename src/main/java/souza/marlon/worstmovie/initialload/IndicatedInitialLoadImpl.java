package souza.marlon.worstmovie.initialload;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import souza.marlon.worstmovie.config.WorstProjectConfiguration;
import souza.marlon.worstmovie.model.Indicated;
import souza.marlon.worstmovie.service.IndicatedServiceImpl;
import souza.marlon.worstmovie.service.ProducerService;
import souza.marlon.worstmovie.service.StudioService;
import souza.marlon.worstmovie.utils.CsvUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class IndicatedInitialLoadImpl implements IndicatedInitialLoad {

    private final IndicatedServiceImpl indicatedService;
    private final StudioService studioService;
    private final ProducerService producerService;
    private final WorstProjectConfiguration worstProjectConfiguration;

    @Transactional
    @PostConstruct
    public void init() throws BadRequestException {
        log.info("File initial load: {}", worstProjectConfiguration.getInitialLoadFile());
        var indicatedData = CsvUtils.getIndicatedDataFromCsv(worstProjectConfiguration.getInitialLoadFile());
        for (IndicatedData indicatedDatum : indicatedData) {
            process(indicatedDatum);
        }
    }

    @Override
    public void process(IndicatedData indicatedData) throws BadRequestException {

        var producers = Arrays.stream(indicatedData.getProducer().split(" and "))
                .flatMap(p -> Arrays.stream(p.split(",")))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(producerService::findByNameOrNew)
                .map(producerService::save)
                .collect(Collectors.toSet());

        var studio = studioService.findByNameOrNew(indicatedData.getStudio());
        var studioRef = studioService.save(studio);

        var indicated = Indicated.builder()
                .title(indicatedData.getTitle())
                .producers(producers)
                .studio(studioRef)
                .yearAt(indicatedData.getYear())
                .isWinner(indicatedData.isWinner())
                .build();

        var saved = indicatedService.save(indicated);

        log.info("Indicated saved: {}", saved.getTitle());

    }
}
