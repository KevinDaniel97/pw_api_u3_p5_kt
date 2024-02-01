package com.example.demo.repository.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {
	@Id
	@GeneratedValue(generator = "seq_profesor",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="seq_profesor",sequenceName ="seq_profesor", allocationSize = 1 )
	@Column(name="prof_id")
	private Integer id;
	
	@Column(name="prof_nombre")
	private String nombre;
	
	@Column(name="prof_apellido")
	private String apellido;
	
	@Column(name="prof_genero")
	private String genero;
	
	@Column(name="prof_cedula")
	private String cedula;
	
	@Column(name="prof_materia")
	private String materia;
	
	@Column(name="prof_salario")
	private double salario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
}
