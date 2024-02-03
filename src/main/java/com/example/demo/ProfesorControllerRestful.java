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
@RequestMapping(path="/profesores")
public class ProfesorControllerRestful {

	@Autowired
	private IProfesorService profesorService;

	@GetMapping(path ="{id}")
	public Profesor consultar(@PathVariable Integer id) {
		return this.profesorService.buscar(id);
	}
	@GetMapping
	public List<Profesor> consultarTodos(@RequestParam(required = false, defaultValue = "M") String genero){
		return this.profesorService.buscarTodos(genero);	
	}
	
	@PostMapping
	public void guardar(@RequestBody Profesor profesor) {
		this.profesorService.guardar(profesor);
	}

	@PutMapping(path="/{id}")
	public void actualizar(@RequestBody Profesor profesor, @PathVariable Integer id) {
		profesor.setId(id);
		this.profesorService.actualizar(profesor);
	}
	@PatchMapping(path="/{id}")
	public void actualizarParcial(@RequestBody Profesor profesor, @PathVariable(name = "id") Integer id) {
		this.profesorService.actualizarParcial(profesor.getApellido(), profesor.getNombre(), profesor.getId());
	}
	@DeleteMapping(path="/{id}")
	public void borrar(@PathVariable Integer id) {
		this.profesorService.borrar(id);
	}
}
