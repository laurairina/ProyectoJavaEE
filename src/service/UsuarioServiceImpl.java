package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ComentarioDao;
import dao.RecetaDao;
import dao.UsuarioDao;
import exception.BlogException;
import model.Comentario;
import model.Receta;
import model.Usuario;

@Component   //esto gestiona las instancias en general, y spring lo crea
@Transactional   //spring
@Service //nuevo
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired //spring va inyentar
	private UsuarioDao usuarioDao; //se usa la interfaz

	@Autowired
	private RecetaDao recetaDao; //se usa la interfaz

	@Autowired
	private ComentarioDao comentarioDao; //se usa la interfaz

/*---UsuarioDAO--*/	
	@Override
	public void agregarUsuario(Usuario u) {    
		//Con esto se obtiene el usuario 
		
        Usuario uBD = usuarioDao.obtenerPorNombreUsuario(u.getNombreusuario());
		
		if (uBD != null) //si este usuario ya existe en la base de datos
			throw new BlogException("Ya existe un usuario con ese nombre de usuario");
		
		 usuarioDao.agregar(u); //sino se agrega, es el metodo generico 

	}
	@Override
	public Usuario autenticar(String nomusuario, String clave) {
		Usuario u = usuarioDao.obtenerPorNombreUsuario(nomusuario); //este metodo esta primero en la interfaz
		
		if (u == null || !u.getClave().equals(clave))  
			return null;
		else
			return u;
	}
	@Override
	public boolean usuarioExiste(String nomusuario) {
		Usuario u = usuarioDao.obtenerPorNombreUsuario(nomusuario); //este metodo esta primero en la interfaz
		
		if (u == null)  
			return false; //el usuario no existe
		else
			return true; //el usuario existe
	}
	
	 public Usuario obtenerPorIdusuario(int id) {  return usuarioDao.obtener(id); 	}
	 
	/*---RecetasDAO--*/		
	@Override
	public void agregarReceta(Receta RI) {   recetaDao.agregar(RI); 	}
	@Override
	public void EliminarReceta(Integer idreceta) {    
	    comentarioDao.EliminarListaComentario(idreceta);
		recetaDao.eliminar(idreceta);
	}
	
	public Receta obtenerPorId(int id) { return recetaDao.obtener(id);   	}
	
	public List<Receta> obtenerTodosRecetaP(){  return recetaDao.obtenerTodosRecetaP(); 	}
	
	public List<Receta> obtenerTodosReceta() { 	return recetaDao.obtenerTodosReceta(); 	}
	
	public List<Receta> obtenerPorNombreReceta(int id){
		return recetaDao.obtenerPorNombreReceta(id);
	}
	
	
	/*---ComentarioDAO--*/		
	public List<Comentario> obtenerPorNombreComentario(int idReceta) {
		return comentarioDao.obtenerPorNombreComentario(idReceta);
	}
	 
	@Override
	public void agregarComentario(Comentario c) {    	
		comentarioDao.agregar(c);  

	}
	
	@Override
	public void eliminarComentario(int idcoment) {    	
		comentarioDao. EliminarComentario(idcoment);  

	}
	public Comentario recetaComentario(int id){
		return comentarioDao.idRecetaComentario(id);
	}		
}
