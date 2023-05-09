package camila.rodriguez.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import camila.rodriguez.model.Categoria;
import camila.rodriguez.repository.CategoriasRepository;
import camila.rodriguez.service.ICategoriasService;

@Service
@Primary
public class CategoriaServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepo;
	@Override
	public void guardar(Categoria categoria) {
		categoriasRepo.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		// TODO Auto-generated method stub
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		Optional<Categoria> optional =categoriasRepo.findById(idCategoria);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		categoriasRepo.deleteById(idCategoria);
	}

}
