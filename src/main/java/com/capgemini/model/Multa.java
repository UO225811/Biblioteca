package com.capgemini.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="multas")
public class Multa {

	@Column
	private LocalDate fInicio;
	@Column
	private LocalDate fFin;
	
	@OneToOne(mappedBy="multa",targetEntity=Lector.class, cascade=CascadeType.ALL)
	private Lector lector;
}
