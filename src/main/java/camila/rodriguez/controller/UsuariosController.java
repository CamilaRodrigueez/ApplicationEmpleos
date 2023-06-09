package camila.rodriguez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import camila.rodriguez.model.Usuario;
import camila.rodriguez.service.IUsuariosServices;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

	
	
	@Autowired
	private IUsuariosServices serviceUsuarios;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Usuario> lista = serviceUsuarios.buscarTodos();
    	model.addAttribute("usuarios", lista);
		return "usuarios/listUsuarios";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		System.out.println("Borrando usuario con id: " + idUsuario);
		serviceUsuarios.eliminar(idUsuario);
		//model.addAttribute("id", idVacante);
		attributes.addFlashAttribute("msg", "Registro eliminado exitosamente");
		return "redirect:/usuarios/index";
	}
}
