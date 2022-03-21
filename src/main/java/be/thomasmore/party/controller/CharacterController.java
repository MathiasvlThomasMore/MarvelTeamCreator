package be.thomasmore.party.controller;

import be.thomasmore.party.model.Character;
import be.thomasmore.party.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CharacterController {
    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping({"/characterList"})
    public String characterList(Model model) {
        Iterable<Character> characters = characterRepository.findAll();
        model.addAttribute("characters", characters);
        return "characterList";
    }

    @GetMapping({"/characterDetails/{id}","/characterDetails"})
    public String characterDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "characterDetails";
        Optional<Character> characterFromDb = characterRepository.findById(id);
        if (characterFromDb.isPresent()){
            model.addAttribute("character", characterFromDb.get());
        }

        return "characterDetails";
    }
}
