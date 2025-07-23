package souza.marlon.worstmovie.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import souza.marlon.worstmovie.model.Studio;
import souza.marlon.worstmovie.repository.StudioRepository;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class StudioServiceImplTest {

    @InjectMocks
    private StudioServiceImpl studioService;

    @Mock
    private StudioRepository repository;

    @Test
    void testSaveSucess() {
        var toSave = Studio.of("Studio");
        var saved = Studio.of("Studio");
        saved.setId(UUID.fromString("67788c03-b6aa-4d5c-a3a9-fe425ff35136"));
        Mockito.when(repository.save(any(Studio.class))).thenReturn(saved);
        var studio = studioService.save(toSave);

        Mockito.verify(repository).save(any(Studio.class));
        assertNotNull(studio);
        assertNotNull(studio.getId());
    }

    @Test
    void testFindByName() {

        var founded = Studio.of("FOUNDED");
        founded.setId(UUID.fromString("e4032bc4-e4ac-4455-9bb6-9f86e345eae3"));
        Mockito.when(repository.findByName(anyString())).thenReturn(Optional.of(founded));

        var producer = studioService.findByNameOrNew("MARLON");

        assertNotNull(producer);
        assertNotNull(producer.getId());
        assertEquals("FOUNDED", producer.getName());
    }

    @Test
    void testGetNewEntity() {
        var producer = studioService.findByNameOrNew("NEW");

        assertNotNull(producer);
        assertNull(producer.getId());
        assertEquals("NEW", producer.getName());
    }
}