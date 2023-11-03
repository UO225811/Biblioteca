package com.capgemini.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "lectores")
public class Lector {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nSocio;
	@Column(unique = true)
	private String email;
	@Column
	private String nombre;
	@Column
	private String telefono;
	@Column
	private String direccion;

	private String role;

	private String password;
	
	@Transient
	private boolean isMultado;

	@Transient
	private String passwordConfirm;

	@JsonManagedReference
	@OneToMany(mappedBy = "lector", targetEntity = Prestamo.class, cascade = CascadeType.ALL)
	private Set<Prestamo> prestamos;

	@OneToMany(mappedBy = "lector", targetEntity = Multa.class, cascade = CascadeType.ALL)
	private Set<Multa> multas;

	public boolean devolver(Long id, LocalDate fechaAct) {
		// precondition --> prestamos.notEmpty()
		return true; // if id is found, else false?
	}

	public boolean prestar(Long id, LocalDate fechaAct) {
		// precondition --> multa == 0
		return true; // if book by id is available, false if not
	}

	public void multar(int dias) {

	}

	public Long getnSocio() {
		return nSocio;
	}

	public void setnSocio(Long nSocio) {
		this.nSocio = nSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Set<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Multa> getMultas() {
		return multas;
	}

	public void setMultas(Set<Multa> multas) {
		this.multas = multas;
	}
	
	public void addMulta(Multa m) {
		multas.add(m);
	}

	public boolean isMultado() {
		return isMultado;
	}

	public void setMultado(boolean isMultado) {
		this.isMultado = isMultado;
	}

}
