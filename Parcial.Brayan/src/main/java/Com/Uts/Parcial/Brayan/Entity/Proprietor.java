package Com.Uts.Parcial.Brayan.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "proprietor")
public class Proprietor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String identification;
    @NotEmpty
    private String cellphone;
    @NotEmpty
    private String location;
    private String email;




    public Proprietor() {
    }

    public Proprietor(String name, String identification, String cellphone, String location, String email) {
        this.name = name;
        this.identification = identification;
        this.cellphone = cellphone;
        this.location = location;
        this.email = email;

    }

    public Proprietor(Long id, String name, String identification, String cellphone, String location, String email) {
        this.id = id;
        this.name = name;
        this.identification = identification;
        this.cellphone = cellphone;
        this.location = location;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
