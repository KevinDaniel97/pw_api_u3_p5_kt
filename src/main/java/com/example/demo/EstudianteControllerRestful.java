package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;

@RestController
@RequestMapping(path="/estudiantes")
public class EstudianteControllerRestful {

	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IMateriaService iMateriaService;

	@GetMapping(path ="/{id}", produces="application/xml")
	public ResponseEntity<Estudiante> consultar(@PathVariable Integer id) {
		//240: grupo satisfactorias  
		//240: Recurso estudiante encontrado satisfactoriamente
		Estudiante estu=this.estudianteService.buscar(id);
		//200 OK
		//401 Autenticacion
		//Contrato de la API (1. documento pdf, Swagger.io)
		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}
	//filtar un conjunto de datos RequestParam
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/{cedula} GET
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/GET
	@GetMapping(path = "/tmp", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Estudiante>> consultarTodos(){
		List<Estudiante> lista =this.estudianteService.buscarTodos("M");	
		HttpHeaders cabeceras = new HttpHeaders();
	      cabeceras.add("mensaje_242", "lista consultada de manera satisfactoria");
	      cabeceras.add("mensaje info", "el sistema va estar enmantenimiento");
	      return new ResponseEntity<>(lista, cabeceras,242);

	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteTO>> consultarTodosHateoas() {
		List<EstudianteTO> ls = this.estudianteService.buscarTodosTO();
		return ResponseEntity.status(HttpStatus.OK).body(ls);
	}
	
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/1/materias
	@GetMapping(path = "/{id}/materias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MateriaTO>> consultarMateriasPorId(@PathVariable Integer id){
		List<MateriaTO> ls = this.iMateriaService.buscarPorIdEstudiante(id);
		return ResponseEntity.status(HttpStatus.OK).body(ls);
	}
	
	
	
	//metodos capacidades
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardar(@RequestBody Estudiante estudiante) {
		this.estudianteService.guardar(estudiante);
	}
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	@PutMapping(path="/{id}", consumes =MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		estudiante.setId(id);
		this.estudianteService.actualizar(estudiante);
	}
	@PatchMapping(path="/{id}", consumes =MediaType.APPLICATION_JSON_VALUE)
	public void actualizarParcial(@RequestBody Estudiante estudiante, @PathVariable Integer id) {
		this.estudianteService.actualizarParcial(estudiante.getApellido(), estudiante.getNombre(), estudiante.getId());
	}
	@DeleteMapping(path="/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}
	
}




