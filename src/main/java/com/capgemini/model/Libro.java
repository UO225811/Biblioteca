package com.capgemini.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

enum TipoLibro {
	NOVELA, TEATRO, POESIA, ENSAYO
}

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
}
