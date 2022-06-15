package be.thomasmore.party.controller;

import be.thomasmore.party.model.Character;
import be.thomasmore.party.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class CharacterController {
    private final Logger logger = LoggerFactory.getLogger(CharacterController.class);
    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping({"/characterList"})
    public String characterList(Model model) {
        Iterable<Character> characters = characterRepository.findAll();
        model.addAttribute("characters", characters);
        return "characterList";
    }

    @GetMapping({"/characterList/filter"})
    public String characterFilter(Model model,
                                  @RequestParam(required = false) String filterPowerType,
                                  @RequestParam(required = false) String filterHero,
                                  @RequestParam(required = false) String filterVillain) {
        List<Character> characters = characterRepository.findByFilter(filterPowerType, filterStringToBoolean(filterHero),filterStringToBoolean(filterVillain));
        model.addAttribute("filter", true);
        model.addAttribute("characters", characters);
        model.addAttribute("filterPowerType", filterPowerType);
        model.addAttribute("filterHero", filterHero);
        model.addAttribute("filterVillain",filterVillain);
        logger.info(String.format("characterfilter: powertype=%s,hero=%s", filterPowerType, filterHero));
        return "characterList";
    }

    @GetMapping({"/characterDetails/{id}", "/characterDetails"})
    public String characterDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "characterDetails";
        Optional<Character> characterFromDb = characterRepository.findById(id);
        if (characterFromDb.isPresent()) {
            model.addAttribute("character", characterFromDb.get());
        }
        return "characterDetails";
    }

    private Boolean filterStringToBoolean(String filterString) {
        return (filterString == null || filterString.equals("all")) ? null : filterString.equals("yes");
    }

    @GetMapping({"characterDetails/{id}/prev"})
    public String detailsPrev(Model model, @PathVariable int id) {
        Optional<Character> prevCharacterFromDb = characterRepository.findFirstByIdLessThanOrderByIdDesc(id);
        if (prevCharacterFromDb.isPresent())
            return String.format("redirect:/characterDetails/%d", prevCharacterFromDb.get().getId());
        Optional<Character> lastVenueFromDb = characterRepository.findFirstByOrderByIdDesc();
        if (lastVenueFromDb.isPresent())
            return String.format("redirect:/characterDetails/%d", lastVenueFromDb.get().getId());
        return "characterDetails";
    }

    @GetMapping({"characterDetails/{id}/next"})
    public String detailsNext(Model model, @PathVariable int id) {
        Optional<Character> nextCharacterFromDb = characterRepository.findFirstByIdGreaterThanOrderByIdAsc(id);
        if (nextCharacterFromDb.isPresent())
            return String.format("redirect:/characterDetails/%d", nextCharacterFromDb.get().getId());
        Optional<Character> lastVenueFromDb = characterRepository.findFirstByOrderByIdAsc();
        if (lastVenueFromDb.isPresent())
            return String.format("redirect:/characterDetails/%d", lastVenueFromDb.get().getId());
        return "characterDetails";
    }

}
