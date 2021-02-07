package dao;
import model.Comentario;
import java.util.List;
public interface ComentarioDao extends GenericDao<Comentario, Integer> {


	public  List<Comentario> obtenerPorNombreComentario(int id);
	public Comentario obtenerPorIdComentario(int idReceta);
	public Integer EliminarListaComentario(int id);
	public Integer EliminarComentario(int id);
	public Comentario idRecetaComentario(int id);
}
