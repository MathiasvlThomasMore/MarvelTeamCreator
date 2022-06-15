package be.thomasmore.party.controller;

import be.thomasmore.party.model.Team;
import be.thomasmore.party.model.User;
import be.thomasmore.party.repositories.CharacterRepository;
import be.thomasmore.party.repositories.TeamRepository;
import be.thomasmore.party.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        if (principal != null) return "redirect:/user/login";
        return "user/register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, Principal principal,
                               @RequestParam String username,
                               @RequestParam String password) {
        if (principal != null) return "redirect:/home";
        if (username == null || username.isBlank()) return "redirect:/home";
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) return "redirect:/home";
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setRole("USER");
        String encode = passwordEncoder.encode(password);
        newUser.setPassword(encode);
        userRepository.save(newUser);
        return "redirect:/user/login";
    }

    @ModelAttribute("team")
    public Team findTeam(@PathVariable(required = false) Integer id) {
        if (id == null) return new Team();
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent())
            return optionalTeam.get();
        return null;
    }

    @GetMapping("/teamedit/{id}")
    public String teamEdit(Model model,
                           @PathVariable int id) {
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("characters", characterRepository.findAll());
        return "user/teamedit";
    }

    @PostMapping("/teamedit/{id}")
    public String teamEditPost(Model model,
                               @PathVariable int id,
                               @Valid @ModelAttribute("team") Team team,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("teams", teamRepository.findAll());
            model.addAttribute("characters", characterRepository.findAll());
            return "user/teamedit";
        }
        teamRepository.save(team);
        return "redirect:/teamDetails/" + id;
    }

    @GetMapping("/teamnew")
    public String teamNew(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("characters", characterRepository.findAll());
        return "user/teamnew";
    }

    @PostMapping("/teamnew")
    public String teamNewPost(Model model,
                              @Valid @ModelAttribute("team") Team team,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("team", new Team());
            model.addAttribute("characters", characterRepository.findAll());
            return "user/teamnew";
        }
        Team newTeam = teamRepository.save(team);
        return "redirect:/teamDetails/" + newTeam.getId();
    }

    @GetMapping({"/login"})
    public String login(Model model, Principal principal) {
        if (principal != null) return "redirect:/home";
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(Model model, Principal principal) {
        if (principal == null) return "redirect:/home";
        return "user/logout";
    }
}
