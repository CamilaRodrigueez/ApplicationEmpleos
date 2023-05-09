package camila.rodriguez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import camila.rodriguez.model.Solicitud;
import camila.rodriguez.model.Usuario;
import camila.rodriguez.model.Vacante;
import camila.rodriguez.service.ISolicitudesServices;
import camila.rodriguez.service.IUsuariosServices;
import camila.rodriguez.service.IVacantesService;
import camila.rodriguez.util.Utileria;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

	@Value("${empleosapp.ruta.cv}")
	private String rutaCv;
	@Autowired
	private IVacantesService servicesVacante;
	@Autowired
	private ISolicitudesServices servicesSolicitudes;
	@Autowired
	private IUsuariosServices servicesUsuarios;
	@GetMapping("/create/{idVacante}")
	public String crear(Solicitud solicitud, @PathVariable("idVacante") Integer idVacante, Model model) {
		
		Vacante vacante = servicesVacante.buscarPorId(idVacante);
		
		model.addAttribute("vacante",vacante);
		System.out.println("IdVacante: " + idVacante);
		return "solicitudes/formSolicitud";
	}
	
	
	@PostMapping("/save")
	public String guardar(Solicitud solicitud, BindingResult result,@RequestParam("archivoCV") MultipartFile multipart, Authentication authentication,
			RedirectAttributes attributes) {
		
		String username = authentication.getName();
		
		
		if (result.hasErrors()) {
			System.out.println("Existen errores: ");
			return "solicitudes/formSolicitud";
		}
		if (!multipart.isEmpty()) {
			
			String nombreArchivo = Utileria.guardarArchivo(multipart, rutaCv);
			if (nombreArchivo != null) { // El archivo si se subio
				// Procesamos la variable nombreArchivo
				solicitud.setArchivo(nombreArchivo);
			}
		}
		
		
		Usuario usuario = servicesUsuarios.buscarPorUsername(username);
		solicitud.setUsuario(usuario);
		attributes.addFlashAttribute("msg","Gracias por enviar tú CV!");
		
		servicesSolicitudes.guardar(solicitud);
		System.out.println("Solicitud: " + solicitud);
		return "redirect:/";
	}
	
	@GetMapping("/indexPaginate")
	public String mostarIndexPaginado(Model model, Pageable page) {
		Page<Solicitud> lista = servicesSolicitudes.buscarTodas(page);
		model.addAttribute("solicitudes",lista);
		return "solicitudes/listSolicitudes";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {
		servicesSolicitudes.eliminar(idSolicitud);
		attributes.addFlashAttribute("msg","La solicitud fue eliminada!");
		return "redirect:/solicitudes/indexPaginate";
	}
}
