package Com.Uts.Parcial.Brayan.Repository;

import Com.Uts.Parcial.Brayan.Entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByEmail(String email);
}
