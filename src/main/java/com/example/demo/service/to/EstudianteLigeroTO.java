package com.example.demo.service.to;

import java.io.Serializable;
import org.springframework.hateoas.RepresentationModel;

public class EstudianteLigeroTO extends RepresentationModel<EstudianteLigeroTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;

	// get y set
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

}
