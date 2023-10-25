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

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Lector getLector() {
		return lector;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}
}
