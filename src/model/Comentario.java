package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comentarios database table.
 * 
 */
@Entity
@Table(name="comentarios")
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer idcoment;

	@Column(nullable=false, length=500)
	private String descripcoment;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="personaid", nullable=false)
	private Usuario usuario;

	//bi-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="recetaid", nullable=false)
	private Receta receta;

	public Comentario() {
	}

	public Integer getIdcoment() {
		return this.idcoment;
	}

	public void setIdcoment(Integer idcoment) {
		this.idcoment = idcoment;
	}

	public String getDescripcoment() {
		return this.descripcoment;
	}

	public void setDescripcoment(String descripcoment) {
		this.descripcoment = descripcoment;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

}