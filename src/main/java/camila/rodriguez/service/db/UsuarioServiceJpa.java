package camila.rodriguez.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import camila.rodriguez.model.Usuario;

import camila.rodriguez.repository.UsuariosRepository;
import camila.rodriguez.service.IUsuariosServices;
@Service
public class UsuarioServiceJpa implements IUsuariosServices {

	
	@Autowired
	private UsuariosRepository usuariosRepo;
	
	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		usuariosRepo.save(usuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		// TODO Auto-generated method stub
		Optional<Usuario> optional = usuariosRepo.findById(idUsuario);

		if (optional.isPresent()) {
			return optional.get();
		}

		return null;
	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		usuariosRepo.deleteById(idUsuario);
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		return usuariosRepo.findByUsername(username);
		
	}

}
