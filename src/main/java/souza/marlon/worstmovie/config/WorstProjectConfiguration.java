package souza.marlon.worstmovie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "worst")
@Data
public class WorstProjectConfiguration {

    private String initialLoadFile;

}
