package com.example.demo.service;

import java.util.List;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;

public interface IEstudianteService {
	
	public void guardar(Estudiante estudiante);
	public void actualizar(Estudiante estudiante);
	public void actualizarParcial(String apellido, String nombre,  Integer id);
	public Estudiante buscar(Integer id);
	public void borrar(Integer id);

	public List<Estudiante> buscarTodos(String genero);
	public List<EstudianteTO> buscarTodosTO();
	public EstudianteTO buscarTO(Integer id);

	public List<EstudianteLigeroTO> buscarTodosLigeroTO();
	public EstudianteLigeroTO buscarLigeroTO(Integer id);
}
