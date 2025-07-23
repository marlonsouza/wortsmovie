package souza.marlon.worstmovie.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import souza.marlon.worstmovie.model.Indicated;
import souza.marlon.worstmovie.model.Producer;
import souza.marlon.worstmovie.model.Studio;
import souza.marlon.worstmovie.repository.IndicatedRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IndicatedServiceImplTest {

    @InjectMocks
    private IndicatedServiceImpl indicatedService;

    @Mock
    private IndicatedRepository repository;

    @Test
    void testSaveSucess() {
        var indicatedBuilder = Indicated.builder()
                .isWinner(false)
                .title("Teste")
                .producer(Producer.of("Producer"))
                .studio(Studio.of("Studio"));

        var toSave = indicatedBuilder.build();
        var saved = indicatedBuilder.id(UUID.fromString("913117eb-c703-40fc-8142-9a7f42122757")).build();
        Mockito.when(repository.save(any(Indicated.class))).thenReturn(saved);
        var indicated = indicatedService.save(toSave);

        verify(repository).save(any(Indicated.class));
        assertNotNull(indicated);
        assertNotNull(indicated.getId());
        assertEquals(toSave.getTitle(), indicated.getTitle());
    }
}