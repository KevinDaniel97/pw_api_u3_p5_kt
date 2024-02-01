package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(path="estudiantes")
public class EstudianteControllerRestful {

	@Autowired
	private IEstudianteService estudianteService;
	
	
	
	
	
	@GetMapping(path ="/buscar/{id}")
	public Estudiante consultar(@PathVariable Integer id) {
		return this.estudianteService.buscar(id);
	}
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	
	
	//filtar un conjunto de datos RequestParam
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/consultarTodos?genero=M&edad=10&apellido=Toapanta
	@GetMapping(path="/consultarTodos")
	public List<Estudiante> consultarTodos(@RequestParam String genero, @RequestParam Integer edad,@RequestParam String apellido){
		return this.estudianteService.buscarTodos(genero);	
	}
	
	
	//metodos capacidades
	@PostMapping(path="/guardar")
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar

	@PutMapping(path="/actualizar")
	public void actualizar(@RequestBody Estudiante estudiante) {
		this.estudianteService.actualizar(estudiante);
	}
	@PutMapping(path="/actualizarParcial")
	public void actualizarParcial(@RequestBody Estudiante estudiante) {
		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), estudiante.getId());
	}
	@DeleteMapping(path="/borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}
	
}




