package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.exo1.enums.GenreMusical;
import fr.uga.l3miage.spring.tp2.exo1.models.ArtistEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testRequestcountByGenreMusicalEquals(){
        // given
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("Hugh Jackman");
        artistEntity.setBiography("Hugh Jackman's Biography");
        artistEntity.setGenreMusical(GenreMusical.CLASSIC);

        ArtistEntity artistEntity1 = new ArtistEntity();
        artistEntity1.setName("Iwa K");
        artistEntity1.setBiography("Mengenal Iwa");
        artistEntity1.setGenreMusical(GenreMusical.RAP);

        ArtistEntity artistEntity2 = new ArtistEntity();
        artistEntity2.setName("Anne Hatheway");
        artistEntity2.setBiography("Anne Hatheway");
        artistEntity2.setGenreMusical(GenreMusical.CLASSIC);

        artistRepository.save(artistEntity);
        artistRepository.save(artistEntity1);
        artistRepository.save(artistEntity2);

        // when
        int countClassicArtist = artistRepository.countByGenreMusicalEquals(GenreMusical.CLASSIC);
        int countRANDBArtist = artistRepository.countByGenreMusicalEquals(GenreMusical.RAP);

        // then
        assertEquals(2, countClassicArtist);
        assertEquals(1, countRANDBArtist);
    }

}
