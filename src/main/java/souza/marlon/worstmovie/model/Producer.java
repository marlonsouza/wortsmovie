package souza.marlon.worstmovie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "producer")
@Entity
@Data
public class Producer {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    private String name;

    public static Producer of(String name){
        var self = new Producer();
        self.name = name;
        return self;
    }

}
