package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Character;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends CrudRepository<Character, Integer> {
    @Query("SELECT c from Character c where " +
            "(:powerType is null or c.powerType=:powerType) AND " +
            "(:hero is null or c.hero=:hero)"
    )
    List<Character> findByFilter(@Param("powerType") String powerType,
                                 @Param("hero") Boolean hero
    );

    Optional<Character> findFirstByIdLessThanOrderByIdDesc(Integer id);

    Optional<Character> findFirstByOrderByIdDesc();

    Optional<Character> findFirstByIdGreaterThanOrderByIdAsc(Integer id);

    Optional<Character> findFirstByOrderByIdAsc();
}
