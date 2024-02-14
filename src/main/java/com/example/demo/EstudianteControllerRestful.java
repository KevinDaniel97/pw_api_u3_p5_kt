package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Estudiante;
import com.example.demo.service.IEstudianteService;
import com.example.demo.service.IMateriaService;
import com.example.demo.service.to.EstudianteLigeroTO;
import com.example.demo.service.to.EstudianteTO;
import com.example.demo.service.to.MateriaTO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(path="/estudiantes")
public class EstudianteControllerRestful {

	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IMateriaService iMateriaService;

	@GetMapping(path ="/{id}", produces="application/json")
	public ResponseEntity<EstudianteTO> consultar(@PathVariable Integer id) {
		EstudianteTO estu=this.estudianteService.buscarTO(id);
		
		Link link = linkTo(methodOn(EstudianteControllerRestful.class).consultarMateriasPorId(estu.getId()))
				.withRel("materia");
		Link link2 = linkTo(methodOn(EstudianteControllerRestful.class).consultar(estu.getId()))
				.withSelfRel();
		estu.add(link);
		estu.add(link2);
		
		return ResponseEntity.status(HttpStatus.OK).body(estu);
	}
	@GetMapping(path = "/ligero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteLigeroTO> buscarLigeroTO(@PathVariable Integer id) {
		EstudianteLigeroTO estuTo = this.estudianteService.buscarLigeroTO(id);
		Link link = linkTo(methodOn(EstudianteControllerRestful.class).buscarLigeroTO(estuTo.getId()))
				.withSelfRel();
		estuTo.add(link);
		return ResponseEntity.status(HttpStatus.OK).body(estuTo);
	}
	
	@GetMapping(path = "/ligero", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EstudianteLigeroTO>> consultarTodosHateoasLigero() {
		List<EstudianteLigeroTO> ls = this.estudianteService.buscarTodosLigeroTO();
		for (EstudianteLigeroTO est : ls) {
			Link link = linkTo(methodOn(EstudianteControllerRestful.class).consultarMateriasPorId(est.getId())).withSelfRel();
			est.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ls);
	}
	//filtar un conjunto de datos RequestParam
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/{cedula} GET
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
		List<EstudianteTO> lista = this.estudianteService.buscarTodosTO();
		for(EstudianteTO est: lista){
			Link link = linkTo(methodOn(EstudianteControllerRestful.class).consultarMateriasPorId(est.getId())).withRel("materia");
			est.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/1/materias
	// http://localhost:8080/API/v1.0/Matricula/materias/estudiantes/1/materias GET
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




