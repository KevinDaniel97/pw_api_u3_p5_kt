package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

@RestController
@RequestMapping(path="/estudiantes")
public class EstudianteControllerRestful {

	@Autowired
	private IEstudianteService estudianteService;

	@GetMapping(path ="/{id}")
	public Estudiante consultar(@PathVariable Integer id) {
		return this.estudianteService.buscar(id);
	}
	//filtar un conjunto de datos RequestParam
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/{cedula} GET
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/GET
	@GetMapping
	public List<Estudiante> consultarTodos(@RequestParam(required = false, defaultValue = "M") String genero){
		return this.estudianteService.buscarTodos(genero);	
	}
	//metodos capacidades
	@PostMapping
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	@PutMapping(path="/{id}")
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
	}
	@PatchMapping(path="/{id}")
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), estudiante.getId());
	}
	@DeleteMapping(path="/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}
	
}




