package souza.marlon.worstmovie.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "studio")
@Data
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    public static Studio of(String name){
        var self = new Studio();
        self.name = name;
        return self;
    }
}
