package souza.marlon.worstmovie.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import souza.marlon.worstmovie.model.Producer;
import souza.marlon.worstmovie.repository.ProducerRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducerServiceImplTest {

    @InjectMocks
    private ProducerServiceImpl producerService;

    @Mock
    private ProducerRepository repository;

    @Test
    void testSaveSucess() {
        var toSave = Producer.of("Producer");
        var saved = Producer.of("Producer");
        saved.setId(UUID.fromString("7c86675e-53a7-4c66-9a0c-14d241b26457"));
        when(repository.save(any())).thenReturn(saved);
        var producer = producerService.save(toSave);

        Mockito.verify(repository).save(any(Producer.class));
        assertNotNull(producer);
        assertNotNull(producer.getId());
    }

    @Test
    void testFindByName() {

        var founded = Producer.of("FOUNDED");
        founded.setId(UUID.fromString("e4032bc4-e4ac-4455-9bb6-9f86e345eae3"));
        Mockito.when(repository.findByName(anyString())).thenReturn(Optional.of(founded));

        var producer = producerService.findByNameOrNew("MARLON");

        assertNotNull(producer);
        assertNotNull(producer.getId());
        assertEquals("FOUNDED", producer.getName());
    }

    @Test
    void testGetNewEntity() {
        var producer = producerService.findByNameOrNew("NEW");

        assertNotNull(producer);
        assertNull(producer.getId());
        assertEquals("NEW", producer.getName());
    }
}