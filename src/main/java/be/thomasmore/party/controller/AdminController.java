package be.thomasmore.party.controller;

import be.thomasmore.party.model.Team;
import be.thomasmore.party.repositories.CharacterRepository;
import be.thomasmore.party.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private TeamRepository teamRepository;



    @ModelAttribute("team")
    public Team findTeam(@PathVariable(required = false) Integer id) {
        if (id == null) return new Team();
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent())
            return optionalTeam.get();
        return null;
    }

    @GetMapping("/teamedit/{id}")
    public String partyEdit(Model model,
                            @PathVariable int id) {
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("characters", characterRepository.findAll());
        return "admin/teamedit";
    }
    @PostMapping("/teamedit/{id}")
    public String partyEditPost(Model model,
                                @PathVariable int id,
                                @Validated @ModelAttribute("team") Team team,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamRepository.findAll());
            model.addAttribute("characters", characterRepository.findAll());
            return "admin/partyedit";
        }
        teamRepository.save(team);
        return "redirect:/teamDetails/" + id;
    }

    @GetMapping("/teamnew")
    public String teamNew(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("characters", characterRepository.findAll());
        return "admin/teamnew";
    }

    @PostMapping("/teamnew")
    public String teamNewPost(Model model,
                               @Validated @ModelAttribute("team") Team team,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("team", new Team());
            model.addAttribute("characters", characterRepository.findAll());
            return "admin/teamnew";
        }
        Team newTeam = teamRepository.save(team);
        return "redirect:/teamDetails/" + newTeam.getId();
    }

}
