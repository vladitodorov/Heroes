package game.heroes.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndex(HttpSession httpSession){
        return "home/index.html";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home/home.html";
    }
}
