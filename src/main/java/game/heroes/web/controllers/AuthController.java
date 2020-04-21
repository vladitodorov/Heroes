package game.heroes.web.controllers;

import game.heroes.service.models.LoginUserServiceModel;
import game.heroes.service.models.auth.RegisterUserServiceModel;
import game.heroes.service.services.AuthService;
import game.heroes.web.models.RegisterUserModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;
    private final ModelMapper mapper;

    public AuthController(AuthService authService, ModelMapper mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "auth/login.html";
    }

    @GetMapping("/register")
    public String getRegisterForm(){
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterUserModel model){
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        authService.register(serviceModel);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute RegisterUserModel model, HttpSession httpSession) {
        RegisterUserServiceModel serviceModel = mapper.map(model, RegisterUserServiceModel.class);
        try{
            LoginUserServiceModel loginUserServiceModel = authService.login(serviceModel);
            httpSession.setAttribute("user", loginUserServiceModel);
            return "redirect:/";
        }catch (Exception ex){
            return "redirect:/users/login";
        }
    }
}
