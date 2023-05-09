package camila.rodriguez.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import camila.rodriguez.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
}
