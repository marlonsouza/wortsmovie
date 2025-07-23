package souza.marlon.worstmovie.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Table(name = "indicated")
@Entity
@Data
public class Indicated {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @NotBlank(message = "Title not informed")
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull(message = "Producer must be not null")
    @JoinColumn(name = "producer_id", nullable = false)
    @ManyToOne
    private Producer producer;

    @NotNull(message = "Studio must be not null")
    @JoinColumn(name = "studio_id", nullable = false)
    @ManyToOne
    private Studio studio;

    @Column(nullable = false)
    private long yearAt;

    @Column(name = "winner")
    private boolean isWinner;

}
