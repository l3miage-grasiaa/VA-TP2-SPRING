package fr.uga.l3miage.spring.tp2.exo1.component;

import fr.uga.l3miage.spring.tp2.exo1.components.PlaylistComponent;
import fr.uga.l3miage.spring.tp2.exo1.exceptions.technical.NotFoundPlaylistEntityException;
import fr.uga.l3miage.spring.tp2.exo1.models.PlaylistEntity;
import fr.uga.l3miage.spring.tp2.exo1.repositories.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PlaylistComponentTest {
    @Autowired
    private PlaylistComponent playlistComponent;

    @MockBean
    private PlaylistRepository playlistRepository;

    /* Ini adalah metode pengujian untuk menguji kasus ketika playlist tidak ditemukan.
        1. Jadi fungsi getPlaylistNotFound() itu buat ngecek playlistRepository dan playlistComponent?
        2. Kenapa engga bikin terpisah? PlaylistRepositoryTest.java dan PlaylistComponentTest.java?
    */
    @Test
    void getPlaylistNotFound(){
        /*
        * Ini adalah konfigurasi mock bean. Ini mengatakan bahwa ketika findById()
        * dipanggil pada mock PlaylistRepository dengan argumen apa pun, itu harus
        * mengembalikan Optional kosong, menirukan perilaku saat playlist
        * tidak ditemukan.
        */
        // Given
        when(playlistRepository.findById(anyString())).thenReturn(Optional.empty());

        /*
        * Ini menguji apakah pemanggilan getPlaylist("test") pada
        * PlaylistComponent menghasilkan pengecualian
        * NotFoundPlaylistEntityException, seperti yang diharapkan ketika
        * playlist tidak ditemukan.
        */
        // then - when
        assertThrows(NotFoundPlaylistEntityException.class, ()->playlistComponent.getPlaylist("test"));
    };

    /*
    * Ini adalah metode pengujian untuk menguji kasus ketika playlist ditemukan.
    * */
    @Test
    void getPlaylistFound(){
        /*
        * Di sini, sebuah objek PlaylistEntity dibuat sebagai contoh playlist yang
        * ditemukan. Ini digunakan untuk mengkonfigurasi perilaku mock bean.
        * Ketika findById() dipanggil pada mock PlaylistRepository dengan argumen
        * apa pun, itu harus mengembalikan Optional yang berisi objek
        * playlistEntity, menirukan perilaku saat playlist ditemukan.
        */
        //Given
        PlaylistEntity playlistEntity = PlaylistEntity.builder()
                .songEntities(Set.of())
                .description("test")
                .build();
        when(playlistRepository.findById(anyString())).thenReturn(Optional.of(playlistEntity));

        /*
        * Ini menguji apakah pemanggilan getPlaylist("test") pada
        * PlaylistComponent berhasil, tanpa menyebabkan pengecualian, seperti
        * yang diharapkan ketika playlist ditemukan.
        */
        // when - then
        assertDoesNotThrow(()->playlistComponent.getPlaylist("test"));
    }
}
