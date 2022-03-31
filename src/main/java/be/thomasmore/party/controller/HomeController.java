package be.thomasmore.party.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    @GetMapping({"/", "/home"})
    public String home(Model model, Principal principal) {
        final String loginName = (principal != null) ? principal.getName():"Nobody";
        model.addAttribute("loginName", loginName);
        logger.info("Login @home = "+loginName);
        return "home";
    }
    @GetMapping({ "/about"})
    public String about(Model model) {
        return "about";
    }

}
