package Com.Uts.Parcial.Brayan.Repository;

import Com.Uts.Parcial.Brayan.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
