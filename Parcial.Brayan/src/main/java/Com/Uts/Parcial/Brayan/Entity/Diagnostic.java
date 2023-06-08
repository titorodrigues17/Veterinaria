package Com.Uts.Parcial.Brayan.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "diagnostic")
public class Diagnostic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pet pet;

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
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }
  
    public Pet getPet() {
      return pet;
    }
  
    public void setPet(Pet pet) {
      this.pet = pet;
    }
  
    public Diagnostic(Long id, String name, String description, Pet pet) {
      this.id = id;
      this.name = name;
      this.description = description;
      this.pet = pet;
    }
  
    public Diagnostic() {
    }
}
