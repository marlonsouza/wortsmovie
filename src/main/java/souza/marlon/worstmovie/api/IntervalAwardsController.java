package souza.marlon.worstmovie.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import souza.marlon.worstmovie.dto.TopIntervalAwards;
import souza.marlon.worstmovie.service.IntervalAwardsService;

@AllArgsConstructor
@RestController
public class IntervalAwardsController {

    private final IntervalAwardsService service;

    @GetMapping("interval-awards")
    public TopIntervalAwards getTopIntervalAwarsds(@RequestParam(required = false) Long limit){
        return service.listTopIntervalAwarsds(limit);
    }

}
