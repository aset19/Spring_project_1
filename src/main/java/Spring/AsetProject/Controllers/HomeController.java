package Spring.AsetProject.Controllers;


import Spring.AsetProject.Entities.User;
import Spring.AsetProject.Service.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {

    private final Service service;

    private final PasswordEncoder passwordEncoder;

    public HomeController(Service service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") User user){
        return "register";
    }

    @PostMapping("/register")
    public String registration(@ModelAttribute("user") User user){
        String bCryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(bCryptPassword);
        service.register(user);
        return "redirect:/login";
    }







}
