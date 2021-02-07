package controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import  java.io.*;


@Controller
@RequestMapping("/admin/*")
public class AdminController {
	final static Logger milogger = Logger.getLogger(AdminController .class);		
	@Autowired
	private UsuarioService usuarioService;
	
	//Cuando uso el cuestionario que hay en InicioSesion viene aqui
	@RequestMapping("")
	public String admin(Model model, HttpSession session) {		
		milogger.info("Entro en la parte del administrador, que solo entrar el usuario");
		
		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);
		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de postre");
		listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
		model.addAttribute("recetasP", listaRecetasPostres);
					
	 //	Todas las recetas que tiene el usuario
		Usuario u = new Usuario();
		
		u = (Usuario) session.getAttribute("usuario");		
	
		try {
			Integer id = u.getIdusu();

			List<Receta> listaRecetas = new ArrayList<Receta>();
			milogger.info("al llamar al usuario service para obtener la recetas que tiene el usuario");
			listaRecetas =usuarioService.obtenerPorNombreReceta(id); 
			model.addAttribute("recetasU", listaRecetas);
			
			return "/WEB-INF/jsp/admin/index.jsp";   //es donde se redirige para que se muestre
		} catch (NullPointerException e) {
			return "redirect:/action/usuario/";
		}	
		
 }
	

	@RequestMapping("mostrar")
	public String iriniciarsesion(@RequestParam Integer id,Model model, HttpSession session) {
		milogger.info("Entro en mostrar que ");
		
		List<String> errores = new ArrayList<String>();

		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info(" llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);

		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		milogger.info("llamar al usuario service de todas recetas de postre");
		listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
		model.addAttribute("recetasP", listaRecetasPostres); 

	 //Recetas del usuario
		Receta listaRecetas = new Receta();
		milogger.info(" llamar al usuario service para obtener una receta en concreto");
		listaRecetas =usuarioService.obtenerPorId(id); 
		
		model.addAttribute("recetasID", listaRecetas);
		
		     //la consulta de todos los comentarios de la receta
			
			int idReceta=listaRecetas.getIdrect();
		    List<Comentario> listaComentario=new ArrayList<Comentario>();
	   
			try{
				milogger.info(" llamar al usuario service para obtener todos los comentarios de la receta ");
				 listaComentario=usuarioService.obtenerPorNombreComentario(idReceta);
				 model.addAttribute("comentarioT",listaComentario);	 
			}
			catch(Exception e){
				milogger.error("Error, no hay comentario", e);
				errores.add("Error, no hay comentario");			
			}	
		return "/WEB-INF/jsp/adminRecetas/index.jsp";
 }
	
	@RequestMapping("ingresarRecetas")
	public String ingresarRecetas (@RequestParam Integer personaid,Model model, HttpSession session ){
		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);

		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de postre");
		listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
		model.addAttribute("recetasP", listaRecetasPostres); 
		
		return "/WEB-INF/jsp/adminIngresar/index.jsp";
	}
	
	public void cargarFoto(Receta RI, String ruta, int n, int numeroAleatorio){	
	    String base64Image = ruta.split(",")[1];

	 // Convierte la image code to bytes.
	 byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

	 BufferedImage bufferedImage = null;
	try {
		bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes)); } catch (IOException e2) {e2.printStackTrace(); }

	// /opt/tomcat8/webapps/Imagenes/         //esta ruta se usa en cuando se va usar el servidor
	// /Users/laurairinamendoza/Desktop/      //esta ruta se usa en el ordenador 
	 
	String rutaFinal="/opt/tomcat8/webapps/Imagenes/"+RI.getNombrerect()+n+numeroAleatorio+".png";
	
	 // "/Users/laurairinamendoza/Desktop/ImagePrueba.png"  //en esta ruta esta la imagen guardada en el ordenador
	 // "http://alumnos.iesvjp.es:16181/Imagenes/ImagePrueba.png" //esta ruta es en la que esta imagen el servidor
	
	 File imageFile = new File(rutaFinal);
	 try {
		ImageIO.write(bufferedImage, "png", imageFile);
	} catch (IOException e1) { e1.printStackTrace(); 	}
	 	RI.setImagen(imageFile.getName());
	}

	
	@RequestMapping("agregarReceta")  
	public String agregarReceta (@RequestParam Integer personaid,@RequestParam String nomReceta,@RequestParam String nomResumen,@RequestParam String nomCategoria,@RequestParam String nomIngredientes,@RequestParam String nomDescipcion,@RequestParam String ruta,Model model, HttpSession session){
	    Usuario u = new Usuario();
	    Receta RI= new Receta();
	    
	    if(((nomResumen.equals("")==false) && (nomReceta.equals("")==false) ) && (nomIngredientes.equals("")==false) && (nomDescipcion.equals("")==false) ){  	
           try {
        	     Date fechaCorrecta = new Date();
        	     SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");  
                  
        	        String strFecha=formatoDelTexto.format(fechaCorrecta);
                    fechaCorrecta = formatoDelTexto.parse(strFecha);
                   milogger.info("llama usuario service para obtener un usuario en concreto"); 
                    u =usuarioService.obtenerPorIdusuario(personaid);
            		RI.setNombrerect(nomReceta);
            		RI.setResumen(nomResumen);
            		RI.setCategoria(nomCategoria);
            		RI.setIngredientes(nomIngredientes);
            		RI.setDescripcion(nomDescipcion);
            		RI.setFechapubli(fechaCorrecta); 
            		
            		int numeroAleatorio = (int) (Math.random()*4000+1);
                     cargarFoto(RI ,ruta,personaid,numeroAleatorio);
       
                     RI.setUsuario(u);
            		milogger.info("llama usuario service para agregar una nueva receta");
            		usuarioService.agregarReceta(RI);

              } catch (ParseException e) {    milogger.equals(e);  }
        }
         	
   		List<Receta> listaRecetas = new ArrayList<Receta>();
		milogger.info(" llamar al usuario service para obtener la recetas que tiene el usuario");
   		listaRecetas =usuarioService.obtenerPorNombreReceta(personaid); 
   		model.addAttribute("recetasU", listaRecetas);
   		
   		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);
   		
   		// todas las recetas de Postre
   			List<Receta> listaRecetasPostres = new ArrayList<Receta>();
   			milogger.info("al llamar al usuario service de todas recetas de postre");
   			listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
   			model.addAttribute("recetasP", listaRecetasPostres); 

           return "/WEB-INF/jsp/admin/index.jsp";

	}
	
	@RequestMapping("eliminarReceta")
	
	public String eliminarReceta(@RequestParam Integer idreceta ,Model model, HttpSession session){
	
		
	    Usuario u = new Usuario();
		u = (Usuario) session.getAttribute("usuario");
		
		Integer id = u.getIdusu();
		milogger.info("llamar al usuario service para eliminar una receta en concreto");
	    usuarioService.EliminarReceta(idreceta);

	//recetas del usuario
	   List<Receta> listaRecetas = new ArrayList<Receta>();
		milogger.info(" llamar al usuario service para obtener la recetas que tiene el usuario");
   		listaRecetas =usuarioService.obtenerPorNombreReceta(id);
   		model.addAttribute("recetasU", listaRecetas);

   		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);

		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de postre");
		listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
		model.addAttribute("recetasP", listaRecetasPostres); 
		
           return "/WEB-INF/jsp/admin/index.jsp";
	}
	
	
	@RequestMapping("agregarComentario") //tener cuidado para refrescar esta pagina
	public String agregarComentario(@RequestParam int personaid,@RequestParam int recetaid,@RequestParam String comentarios, Model model, HttpSession session){
		
		List<String> errores = new ArrayList<String>();
		//	la consulta de todas las recetas de primero
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
		milogger.info(" llamar al usuario service para obtener una receta en concreto");
		r=usuarioService.obtenerPorId(recetaid);
		c.setReceta(r);

		  Usuario u = new Usuario();
			milogger.info(" llamar al usuario service para obtener un usuario por su id");
		  u =usuarioService.obtenerPorIdusuario(personaid);
		  c.setUsuario(u);
		  
			milogger.info(" llamar al usuario service para agregar comentario");
	      usuarioService.agregarComentario(c);
		
		//La consulta para devolver solo una receta de la receta 	
				Receta listaRecetas = new Receta();
				milogger.info("llamar al usuario service para obtener por id la receta que le corresponde");
				listaRecetas = usuarioService.obtenerPorId(recetaid);
				model.addAttribute("recetasID", listaRecetas);		
		 	
 //la consulta de todos los comentarios de la receta		
	
		int idReceta=listaRecetas.getIdrect();
	    List<Comentario> listaComentario=new ArrayList<Comentario>();
		try{
			milogger.info("al llamar al usuario service de obtener por nombre comentario su lista de comentario");
			 listaComentario=usuarioService.obtenerPorNombreComentario(idReceta);
		     model.addAttribute("comentarioT",listaComentario);
			 
		}
		catch(Exception e){
			milogger.error("Error, no hay comentario",e);
			errores.add("Error, no hay comentario");	
		}
		return "/WEB-INF/jsp/adminRecetas/index.jsp";
	}
	
	@RequestMapping("eliminarComent")
	
	public String eliminarComent(@RequestParam Integer id,Model model, HttpSession session){
		List<String> errores = new ArrayList<String>();
		
		//	la consulta de todas las recetas de primero
		List<Receta> listaRecetasTodas = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de primero");
		listaRecetasTodas =usuarioService.obtenerTodosReceta(); 
		model.addAttribute("recetas", listaRecetasTodas);

		
		// todas las recetas de Postre
		List<Receta> listaRecetasPostres = new ArrayList<Receta>();
		milogger.info("al llamar al usuario service de todas recetas de postre");
		listaRecetasPostres =usuarioService.obtenerTodosRecetaP(); 
		model.addAttribute("recetasP", listaRecetasPostres); 
	  
		//La consulta para devolver solo una receta de la receta 	
				Receta listaRecetas = new Receta();
				Comentario c=new Comentario();
			try{
				milogger.info("llamar al usuario service para obtener la receta del comentario");
				c=usuarioService.recetaComentario(id);
				int recetaid=c.getReceta().getIdrect();
				
				milogger.info("llamar al usuario service para obtener por id la receta que le corresponde");
				listaRecetas = usuarioService.obtenerPorId(recetaid);
				model.addAttribute("recetasID", listaRecetas);
				
				//Para eliminar comentario	 
				milogger.info("llama usuario service para eliminar un comentario");
			      usuarioService.eliminarComentario(id);
				
			}
			catch(Exception e){
				milogger.error("Error, no hay comentario",e);
				errores.add("Error, no hay comentario");
				
			}
 //la consulta de todos los comentarios de la receta
		
		int idReceta=listaRecetas.getIdrect();
	    List<Comentario> listaComentario=new ArrayList<Comentario>();
	
		try{
			milogger.info("al llamar al usuario service de obtener por nombre comentario su lista de comentario");
			 listaComentario=usuarioService.obtenerPorNombreComentario(idReceta);
			
		    model.addAttribute("comentarioT",listaComentario);
			 
		}
		catch(Exception e){
			//e.printStackTrace();
			errores.add("Error, no hay comentario");
			milogger.error("Error, no hay comentario", e);
			
		}
		return "/WEB-INF/jsp/adminRecetas/index.jsp";
           
           
           
	}
	
	


	
	
}
