package camila.rodriguez.service;

import java.util.List;


import camila.rodriguez.model.Usuario;

public interface IUsuariosServices {
	void guardar(Usuario usuario);
	List<Usuario> buscarTodos();
	Usuario buscarPorId(Integer idUsuario);	
	void eliminar(Integer idUsuario);
	
	Usuario buscarPorUsername(String username);
}
