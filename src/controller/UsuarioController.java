package controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import model.Comentario;
import model.Receta;
import model.Usuario;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UsuarioService;

@Controller
@RequestMapping("/usuario/*")
public class UsuarioController {
	final static Logger milogger = Logger.getLogger(UsuarioController .class);
	@Autowired // esto es necesario para crear el constructor
	private UsuarioService usuarioService;

	@RequestMapping("autenticar")   //este es el nombre que tiene y se llama desde el formulario
	public String autenticar(@RequestParam String nomusuario, @RequestParam String clave, HttpSession session, Model model) {
		milogger.info("entro en autenticar");		
		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);
		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
		model.addAttribute("recetasP", listaRecetasPostres);
		
		//Ingresar usuario
		List<String> errores = new ArrayList<String>();
		Usuario u = null;
		try {
			milogger.info("al llamar al usuario service autenticar");
			u = usuarioService.autenticar(nomusuario, clave);
			
		} catch (Exception e) {
		
			milogger.error("Error autenticando el usuario ",e);
			errores.add("Error autenticando el usuario");
		}

		if (u == null)
			errores.add("Usuario o contraseña invalido ");
		else {  session.setAttribute("usuario", u);	  }
			
		if (errores.size() > 0) {	
			model.addAttribute("errores", errores);
			return "/WEB-INF/jsp/usuario/index.jsp"; //hay que crear estas carpetas como indica
		} else
			if(nomusuario.equals("anonimo")){   return "/WEB-INF/jsp/usuario/index.jsp";  }
			else{  return "redirect:/action/admin/"; }	
	}

	@RequestMapping("")
	public String index(Model model) { //esto es un inicio, a mostrar
		milogger.info("entro en el inicio de usuario");
		
	
		    //la consulta de todas las recetas de primero
					List<Receta> listaRecetasTodas = new ArrayList<Receta>();
					milogger.info("al llamar al usuario service Obtener todos Receta de primero ");
					listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
					model.addAttribute("recetas", listaRecetasTodas);

					// todas las recetas de Postre
					List<Receta> listaRecetasPostres = new ArrayList<Receta>();
					milogger.info("al llamar al usuario service obtener Todos Recetas de postre");
					listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
					model.addAttribute("recetasP", listaRecetasPostres);
				   
	
		return "/WEB-INF/jsp/usuario/index.jsp"; 
	
	}
	
	@RequestMapping("cerrar")
	public String sesionCerrar(Model model,HttpSession session) {
		milogger.info("entro en la sesion de cerrar para salir como administrador");
         //la consulta de todas las recetas de primero
			List<Receta> listaRecetasTodas = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service obtener todos las recetas de primero");
			listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
			model.addAttribute("recetas", listaRecetasTodas);

			
			// todas las recetas de Postre
			List<Receta> listaRecetasPostres = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service obtener todas la receta de Postre");
			listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
			model.addAttribute("recetasP", listaRecetasPostres);

		session.invalidate();
	
		return "/WEB-INF/jsp/usuario/index.jsp";
		
	}
	
	
	@RequestMapping("iriniciarsesion")
	public String iriniciarsesion(Model model) {
		milogger.info("entro para enviar al al index para autenticar usuario");
        //la consulta de todas las recetas de primero
			List<Receta> listaRecetasTodas = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service de todas recetas de primero");
			listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
			model.addAttribute("recetas", listaRecetasTodas);


			// todas las recetas de Postre
			List<Receta> listaRecetasPostres = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service obtener todas la receta de Postre");
			listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
			model.addAttribute("recetasP", listaRecetasPostres);

		return "/WEB-INF/jsp/InicioSesion/index.jsp";
	}


	@RequestMapping("irRegistrate")
	public String irRegistrate(Model model) {
		milogger.info("Me envia al index para registrarte");
       //la consulta de todas las recetas de primero
			List<Receta> listaRecetasTodas = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service de todas recetas de primero");
			listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
			model.addAttribute("recetas", listaRecetasTodas);

			
			// todas las recetas de Postre
			List<Receta> listaRecetasPostres = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service obtener todas la receta de Postre");
			listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
			model.addAttribute("recetasP", listaRecetasPostres);

		return "/WEB-INF/jsp/InicioRegistro/index.jsp";
	}

	@RequestMapping("agregar")   //este es el nombre que tiene y se llama desde el formulario de dar de alta
	public String registrar(@RequestParam String nomusuario, @RequestParam String clave, HttpSession session, Model model) {
		milogger.info("Entro en agregar para registrar un nuevo usuario ");

          //la consulta de todas las recetas de primero
			List<Receta> listaRecetasTodas = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service de todas recetas de primero");
			listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
			model.addAttribute("recetas", listaRecetasTodas);

			
			// todas las recetas de Postre
			List<Receta> listaRecetasPostres = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service de todas recetas de postre");
			listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
			model.addAttribute("recetasP", listaRecetasPostres);
		
		
		List<String> errores = new ArrayList<String>();
		Usuario u = new Usuario();
		if(usuarioService.usuarioExiste(nomusuario)==false){
           u.setNombreusuario(nomusuario);
           u.setClave(clave);
       	milogger.info("al llamar al usuario service de agregar usuario");
           usuarioService.agregarUsuario(u);
	       session.setAttribute("usuario", u);
	      
		}
		else{
			milogger.error("Usuario ya existe en el sistema");
			errores.add("Usuario ya existe en el sistema");
	
		}

	 
	if (errores.size() > 0) {
		
			model.addAttribute("errores", errores);
			 return "/WEB-INF/jsp/usuario/index.jsp"; //hay que crear estas carpetas como indica
		} else
			  return "redirect:/action/admin/";
	}
	
	
	@RequestMapping("mostrar")
	public String iriniciarsesion(@RequestParam Integer id,Model model, HttpSession session) {
		List<String> errores = new ArrayList<String>();

     milogger.info("Entra en mostrar todos los datos de la receta ");
		
	//La consulta para devolver solo una receta de la receta 	
		Receta listaRecetas = new Receta();
		milogger.info("al llamar al usuario service para obtener por id la receta que le corresponde");
		listaRecetas =usuarioService.obtenerPorId(id);  
		model.addAttribute("recetasID", listaRecetas);
		
	
		//la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);
			
    // todas las recetas de Postre
			List<Receta> listaRecetasPostres = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service de todas recetas de postre");
			listaRecetasPostres = usuarioService.obtenerTodosRecetaP();
			model.addAttribute("recetasP", listaRecetasPostres); 
			
     //la consulta de todos los comentarios de la receta
		
		int idReceta=listaRecetas.getIdrect();
	    List<Comentario> listaComentario=new ArrayList<Comentario>();
   
		try{
			milogger.info("al llamar al usuario service de obtener por nombre comentario su lista de comentario");
			 listaComentario=usuarioService.obtenerPorNombreComentario(idReceta);
		     model.addAttribute("comentarioT",listaComentario);		 
		}
		catch(Exception e){
		
			errores.add("Error, no hay comentario");
			milogger.error("Error, no hay comentario", e);
		}
	

		return "/WEB-INF/jsp/adminRecetas/index.jsp";
	}
	
	@RequestMapping("agregarComentario") //tener cuidado para refrescar esta pagina
	public String agregarComentario(@RequestParam int personaid,@RequestParam int recetaid,@RequestParam String comentarios, Model model, HttpSession session){
		
		milogger.info("Es para agregar un comentario nuevo");
		
		List<String> errores = new ArrayList<String>();
		
		//la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);
		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de postre");
		listaRecetasPostres = usuarioService.obtenerTodosRecetaP();
		model.addAttribute("recetasP", listaRecetasPostres); 
		       
	   //Para agregar comentario
		Comentario c=new Comentario();
		c.setDescripcoment(comentarios);
		
		Receta r=new Receta();
		milogger.info("al llamar al usuario service para obtener por id de la recetaid ");
		r=usuarioService.obtenerPorId(recetaid);
		c.setReceta(r);
	 
		  Usuario u = new Usuario();
			milogger.info("al llamar al usuario service para obteber por el usuario su id");
		  u =usuarioService.obtenerPorIdusuario(personaid);
		  c.setUsuario(u);
			milogger.info("al llamar al usuario service para agrega comentario");
	      usuarioService.agregarComentario(c);
		
		//La consulta para devolver solo una receta de la receta 	
				Receta listaRecetas = new Receta();
				milogger.info("al llamar al usuario service para obtener por id la receta que le corresponde");
				listaRecetas = usuarioService.obtenerPorId(recetaid);
				model.addAttribute("recetasID", listaRecetas);		
		  
 //la consulta de todos los comentarios de la receta
		
		int idReceta=listaRecetas.getIdrect();
	    List<Comentario> listaComentario=new ArrayList<Comentario>();
	
		try{
			milogger.info("al llamar al usuario service para obtener por nombre comentario");
			 listaComentario=usuarioService.obtenerPorNombreComentario(idReceta);
			
		    model.addAttribute("comentarioT",listaComentario);
			 
		}
		catch(Exception e){
			e.printStackTrace();
			errores.add("Error, no hay comentario");
			milogger.error("Error, no hay comentario", e);
			
		}
		
		return "/WEB-INF/jsp/adminRecetas/index.jsp";
	
	}
	
	
}

