package be.thomasmore.party.controller;

import be.thomasmore.party.model.Team;
import be.thomasmore.party.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping({"/teamList"})
    public String team(Model model) {
        Iterable<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "teamList";
    }

    @GetMapping({"/teamDetails", "/teamDetails/{id}"})
    public String teamDetails(Model model,
                              @PathVariable(required = false) Integer id) {
        if (id == null) return "teamdetails";
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            model.addAttribute("team", optionalTeam.get());
        }


        return "teamDetails";
    }
}
