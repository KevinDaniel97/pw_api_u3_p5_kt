package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;

@RestController
@RequestMapping(path="estudiantes")
public class EstudianteControllerRestful {

	@Autowired
	private IEstudianteService estudianteService;
	//GET
	@GetMapping(path ="/buscar")
	public Estudiante consultar() {
		return this.estudianteService.buscar(10);
	}
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	
	
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
	@PutMapping(path="/borrar")
	public void borrar() {
		this.estudianteService.borrar(10);
	}
}





