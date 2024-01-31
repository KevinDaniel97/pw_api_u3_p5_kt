package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.modelo.Estudiante;

public interface IEstudianteService {
	
	public void guardar(Estudiante estudiante);
	
	public void actualizar(Estudiante estudiante);
	
	public void actualizarParcial(String apellido, String nombre,  Integer id);
	
	public Estudiante buscar(Integer id);
	
	public void borrar(Integer id);
}
