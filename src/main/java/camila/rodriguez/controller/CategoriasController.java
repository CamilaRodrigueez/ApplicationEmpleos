package camila.rodriguez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import camila.rodriguez.model.Categoria;
import camila.rodriguez.model.Vacante;
import camila.rodriguez.service.ICategoriasService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {
	
	@Autowired
	//@Qualifier("categoriaServiceJpa")
   	private ICategoriasService serviceCategorias;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = serviceCategorias.buscarTodas();
    	model.addAttribute("categorias", lista);
		return "categorias/listCategorias";		
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "categorias/formCategoria";
		}	
		
		// Guadamos el objeto categoria en la bd
		serviceCategorias.guardar(categoria);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");		
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes attributes) {
		System.out.println("Borrando categoria con id: " + idCategoria);
		serviceCategorias.eliminar(idCategoria);
		//model.addAttribute("id", idVacante);
		attributes.addFlashAttribute("msg", "Registro eliminado exitosamente");
		return "redirect:/categorias/index";
	}
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idCategoria, Model model) {
		Categoria categoria = serviceCategorias.buscarPorId(idCategoria);
		model.addAttribute("categoria",categoria);
		
		return "categorias/formCategoria";
	}
	
}
