package Com.Uts.Parcial.Brayan.Service;

import Com.Uts.Parcial.Brayan.Crypto.AESCryptoUtil;
import Com.Uts.Parcial.Brayan.Entity.Proprietor;
import Com.Uts.Parcial.Brayan.Entity.Role;
import Com.Uts.Parcial.Brayan.Entity.User;
import Com.Uts.Parcial.Brayan.Repository.LoginRepository;
import Com.Uts.Parcial.Brayan.Repository.ProprietorRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceProprietor {

    @Autowired
    private ProprietorRepository proprietorRepository;

    @GetMapping("/propietario")
    public String listarPropietarios(Model model) {
        List<Proprietor> propietarios = proprietorRepository.findAll();
        model.addAttribute("propietarios", propietarios);
        return "Propietario";
    }

    @GetMapping("/add-propietario")
    public String showPropietario(Model model) {
        model.addAttribute("propietario", new Proprietor());
        return "Add-propietario";
    }

    @PostMapping("/add-propietario")
    public String addPropietario(@ModelAttribute Proprietor proprietor, Model model) {
        proprietorRepository.save(proprietor);

        return "redirect:/propietario";
    }

    @GetMapping("/edit-propietario/{id}")
    public String showEditPropietario(@PathVariable("id") Long id, Model model) {
        Proprietor proprietor = proprietorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid propietario Id:" + id));
        model.addAttribute("propietario", proprietor);
        return "Add-propietario";
    }

    @GetMapping("/delete-propietario/{id}")
    public String deletePropietario(@PathVariable("id") Long id, Model model) {
        Proprietor proprietor = proprietorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid propietario Id:" + id));
        proprietorRepository.delete(proprietor);
        return "redirect:/propietario";
    }

}


