package fr.uga.l3miage.spring.tp2.exo1.component;

import fr.uga.l3miage.spring.tp2.exo1.components.SongComponent;
import fr.uga.l3miage.spring.tp2.exo1.exceptions.technical.NotFoundPlaylistEntityException;
import fr.uga.l3miage.spring.tp2.exo1.exceptions.technical.NotFoundSongEntityException;
import fr.uga.l3miage.spring.tp2.exo1.models.PlaylistEntity;
import fr.uga.l3miage.spring.tp2.exo1.models.SongEntity;
import fr.uga.l3miage.spring.tp2.exo1.repositories.SongRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SongComponentTest {
    @Autowired
    private SongComponent songComponent;

    @MockBean
    private SongRepository songRepository;


    @Test
    void getSongEntityByIdNotFound(){
        // Given
        when(songRepository.findById(anyString())).thenReturn(Optional.empty());

        // then - when
        assertThrows(NotFoundSongEntityException.class, ()->songComponent.getSongEntityById("test"));
    };


    @Test
    void getSongEntityByIdFound(){
        //Given
        SongEntity songEntity = new SongEntity();
        songEntity.setTitle("Sentuh Hatiku");
        songEntity.setDuration(Duration.ofMinutes(3).plusSeconds(43));

        when(songRepository.findById(anyString())).thenReturn(Optional.of(songEntity));

        // when - then
        assertDoesNotThrow(()->songComponent.getSongEntityById("test"));
    }
}
