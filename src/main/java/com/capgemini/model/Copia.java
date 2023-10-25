package com.capgemini.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

enum EstadoCopia {
	PRESTADO, RETRASO, BIBLIOTECA, REPARACION
}

@Entity
@Table(name="copias")
public class Copia {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private EstadoCopia estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_libro")
	private Libro libro;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="inicio", column=@Column(name="fecha_inicio")),
		@AttributeOverride(name="fin", column=@Column(name="fecha_fin")),
	})
	private Prestamo prestamo;

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

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	
}
