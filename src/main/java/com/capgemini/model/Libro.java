package com.capgemini.model;

import java.util.Set;

import com.capgemini.model.enums.EstadoCopia;
import com.capgemini.model.enums.TipoLibro;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="libros")
public class Libro {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String titulo;
	@Column
	private TipoLibro tipo;
	@Column
	private String editorial;
	@Column
	private int anyo;
	
	@JsonManagedReference
	@OneToMany(mappedBy="libro", targetEntity=Copia.class, cascade=CascadeType.ALL)
	private Set<Copia> copias;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_autor")
	private Autor autor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public TipoLibro getTipo() {
		return tipo;
	}

	public void setTipo(TipoLibro tipo) {
		this.tipo = tipo;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public Set<Copia> getCopias() {
		return copias;
	}

	public void setCopias(Set<Copia> copias) {
		this.copias = copias;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public int getNcopias() {
		return copias.size();
	}
	
	public int getNcopiasDisponibles() {
		int n = 0;
		for(Copia c: copias) {
			if(c.getEstado() == (EstadoCopia.BIBLIOTECA))
				n++;
		}
		return n;
	}
}
