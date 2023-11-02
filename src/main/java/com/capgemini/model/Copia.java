package com.capgemini.model;

import com.capgemini.model.enums.EstadoCopia;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="copias")
public class Copia {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private EstadoCopia estado;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_libro")
	private Libro libro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstadoCopia getEstado() {
		return estado;
	}

	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	
}
