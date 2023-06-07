package Com.Uts.Parcial.Brayan.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String breed;
    @NotEmpty
    private int age;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String vaccines;

    @ManyToOne
    @JoinColumn(name = "proprietor_id")
    private Proprietor proprietor;

    public Pet() {
    }

    public Pet(String name, String breed, int age, String gender, String vaccines, Proprietor proprietor) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.vaccines = vaccines;
        this.proprietor = proprietor;
    }

    public Pet(Long id, String name, String breed, int age, String gender, String vaccines, Proprietor proprietor) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.vaccines = vaccines;
        this.proprietor = proprietor;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVaccines() {
        return vaccines;
    }

    public void setVaccines(String vaccines) {
        this.vaccines = vaccines;
    }

    public Proprietor getPropietario() {
        return proprietor;
    }

    public void setPropietario(Proprietor proprietor) {
        this.proprietor = proprietor;
    }
}
