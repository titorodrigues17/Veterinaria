package Com.Uts.Parcial.Brayan.Service;

import Com.Uts.Parcial.Brayan.Crypto.AESCryptoUtil;
import Com.Uts.Parcial.Brayan.Entity.Diagnostic;
import Com.Uts.Parcial.Brayan.Entity.User;
import Com.Uts.Parcial.Brayan.Repository.LoginRepository;
import Com.Uts.Parcial.Brayan.Repository.RoleRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ServiceUser {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/usuario")
    public String listarUsuarios(Model model, HttpSession session) {

        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            List<User> users = loginRepository.findAll();
            model.addAttribute("usuarios", users);
            return "Usuario";
        }
        return "redirect:/";
    }

    @GetMapping("/add-usuario")
    public String showUsuario(Model model, HttpSession session) {
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            model.addAttribute("usuario", new User());
            model.addAttribute("rols", roleRepository.findAll());
            return "Add-usuario";
        }
        return "redirect:/";
    }

    @PostMapping("/add-usuario")
    public String addUsuario(@ModelAttribute User user, Model model) {
        loginRepository.save(user);

        return "redirect:/usuario";
    }

    @GetMapping("/edit-usuario/{id}")
    public String showEditUsuario(@PathVariable("id") Long id, Model model, HttpSession session) {
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            User user = loginRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                    "Invalid" +
                            " " +
                            "usuario" +
                            " " +
                            "Id:" + id));
            model.addAttribute("usuario", user);
            model.addAttribute("rols", roleRepository.findAll());
            return "Add-usuario";
        }
        return "redirect:/";
    }

    @GetMapping("/delete-usuario/{id}")
    public String deleteUsuario(@PathVariable("id") Long id, Model model) {
        User user = loginRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid" +
                " " +
                "usuario" +
                " " +
                "Id:" + id));
        loginRepository.delete(user);
        return "redirect:/usuario";
    }

}


