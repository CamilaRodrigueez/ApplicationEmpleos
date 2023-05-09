package camila.rodriguez.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import camila.rodriguez.model.Solicitud;

import camila.rodriguez.repository.SolicitudesRepository;
import camila.rodriguez.service.ISolicitudesServices;
@Service
public class SolicitudesServicesJpa implements ISolicitudesServices {

	
	@Autowired
	private SolicitudesRepository solicitudesRepo;
	@Override
	public void guardar(Solicitud solicitud) {
		// TODO Auto-generated method stub
		
		solicitudesRepo.save(solicitud);

	}

	@Override
	public void eliminar(Integer idSolcitud) {
		// TODO Auto-generated method stub
		solicitudesRepo.deleteById(idSolcitud);

	}

	@Override
	public List<Solicitud> buscarTodas() {
		// TODO Auto-generated method stub
		return solicitudesRepo.findAll();
	}

	@Override
	public Solicitud buscarPorId(Integer idSolicitud) {
		// TODO Auto-generated method stub
		Optional<Solicitud> optional = solicitudesRepo.findById(idSolicitud);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	
	}

	@Override
	public Page<Solicitud> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return solicitudesRepo.findAll(page);
	}

}
