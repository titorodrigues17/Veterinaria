package Com.Uts.Parcial.Brayan.Service;

import Com.Uts.Parcial.Brayan.Entity.Diagnostic;
import Com.Uts.Parcial.Brayan.Entity.User;
import Com.Uts.Parcial.Brayan.Repository.DiagnosticRepository;
import Com.Uts.Parcial.Brayan.Repository.PetRepository;
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
public class ServiceDiagnostic {

    @Autowired
    private DiagnosticRepository diagnosticRepository;
    @Autowired
    private PetRepository petRepository;


    @GetMapping("/diagnostico")
    public String listarDiagnosticos(Model model, HttpSession session) {
        List<Diagnostic> diagnostics = diagnosticRepository.findAll();
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            model.addAttribute("diagnosticos", diagnostics);

            return "Diagnostico";
        }
        return "redirect:/";
    }

    @GetMapping("/add-diagnostico")
    public String showDiagnostico(Model model, HttpSession session){

        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            model.addAttribute("diagnostico", new Diagnostic());
            model.addAttribute("mascotas", petRepository.findAll());
            return "Add-diagnostico";
        }
        return "redirect:/";
    }

    @PostMapping("/add-diagnostico")
    public String addDiagnostico(@ModelAttribute Diagnostic diagnostic, Model model) {
        diagnosticRepository.save(diagnostic);

        return "redirect:/diagnostico";
    }

    @GetMapping("/edit-diagnostico/{id}")
    public String showEditDiagnostico(@PathVariable("id") Long id, Model model, HttpSession session) {

        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            Diagnostic diagnostic = diagnosticRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                    "Invalid" +
                            " " +
                            "diagnostico" +
                            " " +
                            "Id:" + id));
            model.addAttribute("diagnostico", diagnostic);
            model.addAttribute("mascotas", petRepository.findAll());

            return "Add-diagnostico";
        }
        return "redirect:/";
    }

    @GetMapping("/delete-diagnostico/{id}")
    public String deleteDiagnostico(@PathVariable("id") Long id, Model model) {
        Diagnostic diagnostic = diagnosticRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "Invalid" +
                " " +
                "diagnostico" +
                " " +
                "Id:" + id));
        diagnosticRepository.delete(diagnostic);
        return "redirect:/diagnostico";
    }

}


