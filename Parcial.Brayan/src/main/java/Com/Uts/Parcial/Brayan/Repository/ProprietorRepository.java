package Com.Uts.Parcial.Brayan.Repository;

import Com.Uts.Parcial.Brayan.Entity.Proprietor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietorRepository extends JpaRepository<Proprietor,Long> {
}
