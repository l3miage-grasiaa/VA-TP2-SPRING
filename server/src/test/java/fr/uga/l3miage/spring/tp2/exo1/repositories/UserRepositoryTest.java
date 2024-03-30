package fr.uga.l3miage.spring.tp2.exo1.repositories;

import fr.uga.l3miage.spring.tp2.exo1.models.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/*
* utiliser MOCK (karena gamau manggil requete nya dari luar, tapi tetep butuh injection de dependance untuk repository
* utiliser les bases de données H2
* pake @SpingBootTest karena tester avec un springBoot de test dan afin de nous permettre de faire de l'injection
* Cette annotation (SpringBootTest) nous permet de récrire des paramètres présents dans le fichier application.yml pour le context de test
*/

@AutoConfigureTestDatabase // permet de configurer automatiquement la base
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
class UserRepositoryTest {

    // faire de l'injection par attribut via l'annotation @Autowired
    @Autowired
    private UserRepository userRepository; // nous allons avoir besoin du repository, il nous faut donc l'injecter

    // créer notre test testRequestFindAllByMailContaining en utilisant l'annotation @Test
    @Test
    void testRequestFindAllByMailContaining(){
        //given
        UserEntity userEntity = UserEntity
                .builder()
                .mail("test@gmail.com")
                .name("google 1")
                .build();

        UserEntity userEntity2 = UserEntity
                .builder()
                .mail("test@test.com")
                .name("non google")
                .build();

        // savegarde les entity
        userRepository.save(userEntity); // insertion dans la bd
        userRepository.save(userEntity2);

        // exécuter la requête
        // when
        Set<UserEntity> userEntitiesResponses = userRepository.findAllByMailContaining("gmail"); // selection de la bd

        //then
        assertThat(userEntitiesResponses).hasSize(1);
        assertThat(userEntitiesResponses.stream().findFirst().get().getMail()).isEqualTo("test@gmail.com");
    }
}
