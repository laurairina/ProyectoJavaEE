package service;

import java.util.List;
import model.Comentario;
import model.Receta;
import model.Usuario;

public interface UsuarioService {

	public void agregarUsuario(Usuario u);
	public Usuario autenticar(String nomusuario, String clave);
	public Usuario obtenerPorIdusuario(int id);
	public boolean usuarioExiste(String nomusuario);
	
	public Receta obtenerPorId(int id);
	public List<Receta> obtenerTodosRecetaP();
	public void agregarReceta(Receta RI);
	public void EliminarReceta(Integer idreceta);
	public List<Receta> obtenerTodosReceta();
	public List<Receta> obtenerPorNombreReceta(int id);

	
	
	void agregarComentario(Comentario c);
	public List<Comentario> obtenerPorNombreComentario(int idReceta);
	public void eliminarComentario(int idcoment);
	public Comentario recetaComentario(int id);
	
	
}
