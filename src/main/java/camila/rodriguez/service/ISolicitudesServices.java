package camila.rodriguez.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import camila.rodriguez.model.Solicitud;

public interface ISolicitudesServices {

	void guardar(Solicitud solicitud);
	void eliminar(Integer idSolcitud);
	List<Solicitud> buscarTodas();
	Solicitud buscarPorId(Integer idSolicitud);
	Page<Solicitud> buscarTodas(Pageable page);
}
