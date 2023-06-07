package Com.Uts.Parcial.Brayan.Repository;

import Com.Uts.Parcial.Brayan.Entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
