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

import com.example.demo.repository.modelo.Profesor;
import com.example.demo.service.IProfesorService;

@RestController
@RequestMapping(path="profesores")
public class ProfesorControllerRestful {

	@Autowired
	private IProfesorService profesorService;

	@GetMapping(path ="/buscar/{id}")
	public Profesor consultar(@PathVariable Integer id) {
		return this.profesorService.buscar(id);
	}
	//http://localhost:8080/API/v1.0/Matricula/profesores/buscar
	
	
	//filtar un conjunto de datos RequestParam
	//http://localhost:8080/API/v1.0/Matricula/profesores/consultarTodos?genero=M
	@GetMapping(path="/consultarTodos")
	public List<Profesor> consultarTodos(@RequestParam String genero){
		return this.profesorService.buscarTodos(genero);	
	}
	
		@PostMapping(path="/guardar")
	public void guardar(@RequestBody Profesor profesor) {
		this.profesorService.guardar(profesor);
	}

	@PutMapping(path="/actualizar")
	public void actualizar(@RequestBody Profesor profesor) {
		this.profesorService.actualizar(profesor);
	}
	@PatchMapping(path="/actualizarParcial")
	public void actualizarParcial(@RequestBody Profesor profesor) {
		this.profesorService.actualizarParcial(profesor.getApellido(), profesor.getNombre(), profesor.getId());
	}
	@DeleteMapping(path="/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.profesorService.borrar(id);
	}
}
