package Com.Uts.Parcial.Brayan.Service;

import Com.Uts.Parcial.Brayan.Crypto.AESCryptoUtil;
import Com.Uts.Parcial.Brayan.Entity.Role;
import Com.Uts.Parcial.Brayan.Entity.User;
import Com.Uts.Parcial.Brayan.Repository.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceLogin {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/")
    public String login(Model model, HttpSession session){
        try {
            User user = (User) session.getAttribute("usuario");
            if (user != null) return "redirect:/dashboard";
            return "Login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("usuario", null);
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            try {
                User user = loginRepository.findByEmail(oldUser.getEmail());
                if (user != null) {
                    model.addAttribute("id", user.getId());
                    model.addAttribute("name", user.getName());
                    model.addAttribute("surname", user.getSurname());
                    model.addAttribute("email", user.getEmail());
                    model.addAttribute("role", user.getRole().getName());
                    return "Dashboard";
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        try {
            User user = loginRepository.findByEmail(email);
            if (user != null) {
                String decryptedPassword = AESCryptoUtil.decrypt(user.getPassword());
                if (password.equals(decryptedPassword)) {
                    session.setAttribute("usuario", user);
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
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        try {
            String encryptedPassword = AESCryptoUtil.encrypt(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setRole(new Role(3L));
            loginRepository.save(user);
        } catch (Exception e) {
            return "redirect:/error";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "*", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public String error() {
        return "Error";
    }

}


