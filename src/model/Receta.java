package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the recetas database table.
 * 
 */
@Entity
@Table(name="recetas")
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer idrect;

	@Column(nullable=false, length=100)
	private String categoria;

	@Column(nullable=false, length=5000)
	private String descripcion;

	@Temporal(TemporalType.DATE)
	private Date fechapubli;

	@Column(nullable=true, length=2000)
	private String imagen;

	@Column(nullable=false, length=4000)
	private String ingredientes;

	@Column(nullable=false, length=500)
	private String nombrerect;

	@Column(nullable=false, length=4000)
	private String resumen;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="receta")
	private List<Comentario> comentarios;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuarioid", nullable=false)
	private Usuario usuario;

	public Receta() {
	}

	public Integer getIdrect() {
		return this.idrect;
	}

	public void setIdrect(Integer idrect) {
		this.idrect = idrect;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechapubli() {
		return this.fechapubli;
	}

	public void setFechapubli(Date fechapubli) {
		this.fechapubli = fechapubli;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	

	public String getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNombrerect() {
		return this.nombrerect;
	}

	public void setNombrerect(String nombrerect) {
		this.nombrerect = nombrerect;
	}

	public String getResumen() {
		return this.resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setReceta(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setReceta(null);

		return comentario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}