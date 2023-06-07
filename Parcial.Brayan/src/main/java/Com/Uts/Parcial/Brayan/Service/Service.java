package Com.Uts.Parcial.Brayan.Service;

import Com.Uts.Parcial.Brayan.Crypto.AESCryptoUtil;
import Com.Uts.Parcial.Brayan.Entity.Login;
import Com.Uts.Parcial.Brayan.Repository.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class Service {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("login")
    public String login(){
        return "Login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String email = (String) session.getAttribute("usuario");
        if (email != null) {
            try {
                Login user = loginRepository.findByEmail(email);
                if (user != null) {
                    model.addAttribute("id", user.getId());
                    model.addAttribute("name", user.getName());
                    model.addAttribute("surname", user.getSurname());
                    model.addAttribute("email", user.getEmail());
                    return "Dashboard";
                }
            } catch (Exception e) {
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        try {
            Login user = loginRepository.findByEmail(email);
            if (user != null) {
                String decryptedPassword = AESCryptoUtil.decrypt(user.getPassword());
                if (password.equals(decryptedPassword)) {
                    session.setAttribute("usuario", email);
                    return "redirect:/dashboard";
                }
            }
            model.addAttribute("error", "Invalid email or password");
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred");
        }
        return "login";
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Login());
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") Login user) {
        try {
            String encryptedPassword = AESCryptoUtil.encrypt(user.getPassword());
            user.setPassword(encryptedPassword);
            loginRepository.save(user);
        } catch (Exception e) {
            return "redirect:/error";
        }
        return "redirect:/login";
    }

}


