package Com.Uts.Parcial.Brayan.Repository;

import Com.Uts.Parcial.Brayan.Entity.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
}
