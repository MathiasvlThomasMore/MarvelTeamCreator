package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Character;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharacterRepository extends CrudRepository<Character,Integer> {

}
