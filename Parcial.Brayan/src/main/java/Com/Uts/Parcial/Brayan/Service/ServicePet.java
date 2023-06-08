package Com.Uts.Parcial.Brayan.Service;

import Com.Uts.Parcial.Brayan.Entity.Diagnostic;
import Com.Uts.Parcial.Brayan.Entity.Pet;
import Com.Uts.Parcial.Brayan.Entity.User;
import Com.Uts.Parcial.Brayan.Repository.PetRepository;
import Com.Uts.Parcial.Brayan.Repository.ProprietorRepository;
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
public class ServicePet {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ProprietorRepository proprietorRepository;
    @GetMapping("/mascota")
    public String listarMascotas(Model model, HttpSession session) {
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            List<Pet> pets = petRepository.findAll();
            model.addAttribute("mascotas", pets);
            return "Mascota";
        }
        return "redirect:/";
    }

    @GetMapping("/add-mascota")
    public String showMascota(Model model, HttpSession session) {
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            model.addAttribute("mascota", new Pet());
            model.addAttribute("propietarios", proprietorRepository.findAll());
            return "Add-mascota";
        }
        return "redirect:/";
    }

    @PostMapping("/add-mascota")
    public String addMascota(@ModelAttribute Pet pet) {
        petRepository.save(pet);
        return "redirect:/mascota";
    }

    @GetMapping("/edit-mascota/{id}")
    public String showEditMascota(@PathVariable("id") Long id, Model model, HttpSession session) {
        User oldUser = (User) session.getAttribute("usuario");
        if (oldUser != null) {
            model.addAttribute("id", oldUser.getId());
            model.addAttribute("name", oldUser.getName());
            model.addAttribute("surname", oldUser.getSurname());
            model.addAttribute("email", oldUser.getEmail());
            model.addAttribute("role", oldUser.getRole().getName());
            Pet pet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid "));
            model.addAttribute("mascota", pet);
            model.addAttribute("propietarios", proprietorRepository.findAll());
            return "Add-mascota";
        }
        return "redirect:/";
    }

    @GetMapping("/delete-mascota/{id}")
    public String deleteMascota(@PathVariable("id") Long id, Model model) {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid " +
                "mascota" +
                " " +
                "Id:" + id));
        petRepository.delete(pet);
        return "redirect:/mascota";
    }

}


