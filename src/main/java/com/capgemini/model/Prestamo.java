package com.capgemini.model;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;

@Embeddable
public class Prestamo {

	private LocalDate inicio;
	private LocalDate fin;
}
