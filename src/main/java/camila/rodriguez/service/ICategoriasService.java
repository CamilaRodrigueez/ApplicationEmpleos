package camila.rodriguez.service;

import java.util.List;
import camila.rodriguez.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);	
	void eliminar(Integer idCategoria);
}