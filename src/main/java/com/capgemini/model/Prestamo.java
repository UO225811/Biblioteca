package com.capgemini.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Prestamo {

	private LocalDate inicio;
	private LocalDate fin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk_lector")
	private Lector lector;
}
