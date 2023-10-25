package com.capgemini.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="inicio", column=@Column(name="fecha_inicio")),
		@AttributeOverride(name="fin", column=@Column(name="fecha_fin")),
	})
	private Prestamo prestamo;
	
}
