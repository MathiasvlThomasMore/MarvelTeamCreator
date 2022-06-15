package be.thomasmore.party.controller;

import be.thomasmore.party.model.Team;
import be.thomasmore.party.repositories.CharacterRepository;
import be.thomasmore.party.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
}
