package com.capgemini.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lectores")
public class Lector {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long nSocio;
	@Column
	private String nombre;
	@Column
	private String telefono;
	@Column
	private String direccion;
	
	
	public boolean devolver(Long id, LocalDate fechaAct) {
		// precondition --> prestamos.notEmpty()
		return true; // if id is found, else false?
	}
	
	public boolean prestar(Long id, LocalDate fechaAct) {
		//precondition --> multa == 0
		return true; // if book by id is available, false if not
	}
	
	private void multar(int dias) {
		
	}
}
