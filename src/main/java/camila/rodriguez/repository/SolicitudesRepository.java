package camila.rodriguez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import camila.rodriguez.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
